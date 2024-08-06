package com.catherineorbe.mathlearningapp;


import android.content.Context;
import android.widget.TextView;

public class TextSizeUtils {
    // Static method to set text size
    // Static method to set text size
    public static void setTextSize(Context context, TextView textView, boolean isBig) {
        float textSize = isBig
                ? context.getResources().getDimension(R.dimen.text_size_big)
                : context.getResources().getDimension(R.dimen.text_size_small);

        textView.setTextSize(textSize);
    }
}
