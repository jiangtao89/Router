package com.jt.funny.router;

import com.google.auto.service.AutoService;
import com.jt.funny.router.annotation.Route;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
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
        return Collections.singleton(Route.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> targets = roundEnv.getElementsAnnotatedWith(Route.class);

        MethodSpec main = MethodSpec.methodBuilder("routes")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        for (Element target : targets) {
            if (target.getKind() != ElementKind.CLASS) {
                error(target.getSimpleName() + " is not a class");
                return false;
            }

            Route route = target.getAnnotation(Route.class);


        }


        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.jt.helloworld", helloWorld)
                .build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void error(String error) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, error);
    }
}
