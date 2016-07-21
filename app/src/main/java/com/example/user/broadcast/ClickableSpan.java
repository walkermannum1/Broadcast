package com.example.user.broadcast;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.view.View;

/**
 * Created by user on 2016/7/21.
 */
public abstract class ClickableSpan extends CharacterStyle implements UpdateAppearance{

    public abstract void onClick(View widget);

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}
