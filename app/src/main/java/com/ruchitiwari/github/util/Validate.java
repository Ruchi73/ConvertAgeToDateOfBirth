package com.ruchitiwari.github.util;

/**
 * Created by Ruchi Tiwari on 29-05-2017.
 */


import android.text.TextUtils;

import java.util.List;


/**
 * This class assists in validating arguments.
 */

public class Validate {
    /**
     * Validate that the specified argument is {@code null};
     *
     * @param <T>    the object type
     * @param object the object to check
     * @return <code>true</code> if the object {@code null}, <code>false</code> otherwise.
     */
    public static <T> boolean isNull(final T object) {
        return !Validate.notNull(object);
    }

    /**
     * Validate that the specified argument is not {@code null};
     *
     * @param <T>    the object type
     * @param object the object to check
     * @return <code>false</code> if the object is {@code null}, <code>true</code> otherwise.
     */
    public static <T> boolean notNull(final T object) {
        return (object != null);
    }

    /**
     * Returns true if the string is not {@code null} or 0-length.
     *
     * @param string the string to be examined
     * @return <code>false</code> if string is {@code null} or zero length, <code>true</code> otherwise.
     */
    public static boolean notEmpty(final String string) {
        return !TextUtils.isEmpty(string);
    }

    /**
     * Returns true if the array is not {@code null} and 0-length.
     *
     * @param <T> the array type
     * @return <code>false</code> if array is {@code null} or zero length, <code>true</code> otherwise.
     */
    public static <T> boolean notEmpty(final T[] array) {
        return Validate.notNull(array) && array.length > 0;
    }

    /**
     * Returns true if the list is not {@code null} and 0-Size.
     *
     * @param <T> the list type
     * @return <code>false</code> if list is {@code null} or zero Size, <code>true</code> otherwise.
     */
    public static <T> boolean notEmpty(final List<T> list) {
        return Validate.notNull(list) && list.size() > 0;
    }
}
