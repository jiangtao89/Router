package com.jt.funny.router;

import com.google.auto.service.AutoService;
import com.jt.funny.router.annotation.IRoute;
import com.jt.funny.router.annotation.IRouter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * Created by jiangtao on 16/4/20.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
@AutoService(Processor.class)
public class PathProcessor extends AbstractProcessor {

    private Messager mMessager;
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(IRoute.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        MethodSpec.Builder builder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class);

        if (registerRoute(roundEnv, builder)) {
            return false;
        }

        if (registerRouter(roundEnv, builder)) {
            return false;
        }

        MethodSpec inject = builder.build();
        TypeSpec routeInject = TypeSpec.classBuilder("RoutersInject")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(inject)
                .build();

        JavaFile javaFile = JavaFile.builder("com.jt.funny.router", routeInject)
                .build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean registerRoute(RoundEnvironment roundEnv, MethodSpec.Builder builder) {
        Set<? extends Element> targets = roundEnv.getElementsAnnotatedWith(IRoute.class);
        for (Element target : targets) {
            if (target.getKind() != ElementKind.CLASS) {
                error(target.getSimpleName() + " is not a class");
                return true;
            }

            IRoute route = target.getAnnotation(IRoute.class);

            String schema = route.schema();
            String host = route.host();
            String[] paths = route.path();
            for (String path : paths) {
                String uri = schema + "://" + host + "/" + path;
                builder.addStatement("/** {@link $S} */"
                        , uri);
                builder.addStatement("Routers.getInstances().registerRoute($S, $T.class)"
                        , uri
                        , ClassName.get((TypeElement) target));
            }
        }
        return false;
    }

    private boolean registerRouter(RoundEnvironment roundEnv, MethodSpec.Builder builder) {
        Set<? extends Element> targets = roundEnv.getElementsAnnotatedWith(IRouter.class);
        for (Element target : targets) {
            if (target.getKind() != ElementKind.CLASS) {
                error(target.getSimpleName() + " is not a class");
                return true;
            }

            IRouter router = target.getAnnotation(IRouter.class);

            String[] schemas = router.schema();
            for (String schema : schemas) {
                builder.addStatement("/** {@link $S} */"
                        , schema);
                builder.addStatement("Routers.getInstances().registerRouter($S, $T.class)"
                        , schema
                        , ClassName.get((TypeElement) target));
            }
        }
        return false;
    }

    private void error(String error) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, error);
    }
}
