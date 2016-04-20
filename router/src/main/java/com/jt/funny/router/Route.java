package com.jt.funny.router;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class Route {

    private static final int DEFAULT_REQUEST_CODE = -1;

    /**
     * uri
     */
    private Uri mUri;

    /**
     * {@link Intent#addFlags(int)}
     */
    private int mFlags;

    /**
     * {@link Activity#overridePendingTransition(int, int)}
     */
    private int mEnterAnim;

    /**
     * {@link Activity#overridePendingTransition(int, int)}
     */
    private int mExitAnim;

    /**
     * {@link Activity#startActivityForResult(Intent, int)}
     */
    private int mRequestCode = -1;

    /**
     * current activity
     */
    private Activity mActivity;

    /**
     * uri
     *
     * @return uri
     */
    public Uri getUri() {
        return mUri;
    }

    /**
     * uri
     *
     * @param uri uri
     */
    void setUri(Uri uri) {
        mUri = uri;
    }

    /**
     * flags
     *
     * @return flags
     */
    public int getFlags() {
        return mFlags;
    }

    /**
     * flags
     *
     * @param flags flags
     */
    void setFlags(int flags) {
        mFlags = flags;
    }

    /**
     * enter animation
     *
     * @return animation
     */
    public int getEnterAnim() {
        return mEnterAnim;
    }

    /**
     * enter animation
     *
     * @param enterAnim enterAnim
     */
    void setEnterAnim(int enterAnim) {
        mEnterAnim = enterAnim;
    }

    /**
     * exit animation
     *
     * @return animation
     */
    public int getExitAnim() {
        return mExitAnim;
    }

    /**
     * exit animation
     *
     * @param exitAnimation exitAnimation
     */
    void setExitAnim(int exitAnimation) {
        mExitAnim = exitAnimation;
    }

    /**
     * request code
     *
     * @param requestCode requestCode
     */
    void setRequestCode(int requestCode) {
        mRequestCode = requestCode;
    }

    /**
     * request code
     *
     * @return request code
     */
    public int getRequestCode() {
        return mRequestCode;
    }

    /**
     * open
     *
     * @return open success or failed
     */
    public boolean open() {
        return Routers.getInstances().open(this);
    }

    /**
     * activity
     *
     * @param activity activity
     */
    void setActivity(Activity activity) {
        mActivity = activity;
    }

    /**
     * activity
     *
     * @return activity
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * Helper class for building or manipulating URI references. Not safe for
     * concurrent use.
     * <p>
     * <p>An absolute hierarchical URI reference follows the pattern:
     * {@code <scheme>://<host><absolute path>?<query>#<fragment>}
     * <p>
     * <p>Relative URI references (which are always hierarchical) follow one
     * of two patterns: {@code <relative or absolute path>?<query>#<fragment>}
     * or {@code //<host><absolute path>?<query>#<fragment>}
     * <p>
     * <p>An opaque URI follows this pattern:
     * {@code <scheme>:<opaque part>#<fragment>}
     * <p>
     * <p>Use {@link Uri#buildUpon()} to obtain a builder representing an existing URI.
     */
    public static final class Builder {

        private int mFlags;
        private int mEnterAnim;
        private int mExitAnim;
        private int mRequestCode = DEFAULT_REQUEST_CODE;
        private Activity mActivity;

        Uri.Builder mBuilder = new Uri.Builder();

        /**
         * Constructs a new Builder.
         */
        public Builder() {
        }

        /**
         * Sets the scheme.
         *
         * @param scheme name or {@code null} if this is a relative Uri
         */
        public Builder scheme(String scheme) {
            mBuilder.scheme(scheme);
            return this;
        }

        /**
         * Encodes and sets the host.
         */
        public Builder host(String authority) {
            mBuilder.authority(authority);
            return this;
        }

        /**
         * Sets the path. Leaves '/' characters intact but encodes others as
         * necessary.
         * <p>
         * <p>If the path is not null and doesn't start with a '/', and if
         * you specify a scheme and/or host, the builder will prepend the
         * given path with a '/'.
         */
        public Builder path(String path) {
            mBuilder.path(path);
            return this;
        }

        /**
         * Encodes the given segment and appends it to the path.
         */
        public Builder appendPath(String newSegment) {
            mBuilder.appendPath(newSegment);
            return this;
        }

        /**
         * Encodes and sets the query.
         */
        public Builder query(String query) {
            mBuilder.query(query);
            return this;
        }

        /**
         * Encodes the key and value and then appends the parameter to the
         * query string.
         *
         * @param key   which will be encoded
         * @param value which will be encoded
         */
        public Builder appendQueryParameter(String key, String value) {
            mBuilder.appendQueryParameter(key, value);
            return this;
        }

        /**
         * Encodes the key and value and then appends the parameter to the
         * query string.
         *
         * @param key   which will be encoded
         * @param value which will be encoded
         */
        public Builder appendQueryParameter(String key, Number value) {
            mBuilder.appendQueryParameter(key, value.toString());
            return this;
        }

        /**
         * Encodes the key and value and then appends the parameter to the
         * query string.
         *
         * @param key   which will be encoded
         * @param value which will be encoded
         */
        public Builder appendQueryParameter(String key, Boolean value) {
            mBuilder.appendQueryParameter(key, value.toString());
            return this;
        }

        public Builder with(Activity activity) {
            mActivity = activity;
            return this;
        }

        public Builder with(Fragment fragment) {
            mActivity = fragment.getActivity();
            return this;
        }

        /**
         * open url
         *
         * @param url url
         * @return builder
         */
        public Builder withUrl(String url) {
            mBuilder = Uri.parse(url).buildUpon();
            return this;
        }

        /**
         * flags
         * <p>
         * {@link Intent#addFlags(int)}
         *
         * @param flags flags
         * @return this
         */
        public Builder addFlags(int flags) {
            mFlags = flags;
            return this;
        }

        /**
         * enterAnim
         * <p>
         * {@link Activity#overridePendingTransition(int, int)}
         *
         * @param anim anim
         * @return Builder
         */
        public Builder enterAnim(int anim) {
            mEnterAnim = anim;
            return this;
        }

        /**
         * exitAnim
         * <p>
         * {@link Activity#overridePendingTransition(int, int)}
         *
         * @param anim anim
         * @return Builder
         */
        public Builder exitAnim(int anim) {
            mExitAnim = anim;
            return this;
        }

        /**
         * request code
         * {@link Activity#startActivityForResult(Intent, int)}
         *
         * @param requestCode requestCode
         * @return Builder
         */
        public Builder requestCode(int requestCode) {
            mRequestCode = requestCode;
            return this;
        }

        /**
         * Constructs a IRoute with the current attributes.
         */
        public Route build() {
            if (mActivity == null) {
                if (Routers.getInstances().isDebug()) {
                    throw new IllegalArgumentException("route activity is null!");
                }
                return new Route();
            }

            Route route = new Route();
            route.setUri(mBuilder.build());
            route.setFlags(mFlags);
            route.setEnterAnim(mEnterAnim);
            route.setExitAnim(mExitAnim);
            route.setRequestCode(mRequestCode);
            route.setActivity(mActivity);
            return route;
        }

        @Override
        public String toString() {
            return build().toString();
        }
    }

}
