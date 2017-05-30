package com.ruchitiwari.github.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Ruchi Tiwari on 29-05-2017.
 */

public class Util {

    public static void hideSoftKeyboard(Context context, View currentFocusedView) {
        if (Validate.notNull(currentFocusedView)) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), 0);
        }
    }

}
