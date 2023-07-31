package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.suggestionkeyboardresearchapplication.R;
public class SuggestionModePhase extends LinearLayout implements View.OnClickListener {

    public SuggestionModePhase(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionModeKeyboardViewStyle);
    }

    public SuggestionModePhase(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    public void phaseSetup() {

    }

    public void phaseConclusion() {

    }

    @Override
    public void onClick(View view) {

    }
}
