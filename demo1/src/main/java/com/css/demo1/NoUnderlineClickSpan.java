package com.css.demo1;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

/**
 * @author lidajun
 * @email solidajun@gmail.com
 * @date 16/5/25 16:08.
 * @desc: 无下划线的ClickableSpan
 */
public abstract class NoUnderlineClickSpan extends ClickableSpan {
    private int mColor;

    public NoUnderlineClickSpan(int color) {
        this.mColor = color;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(mColor);
        ds.setUnderlineText(false);
    }
}
