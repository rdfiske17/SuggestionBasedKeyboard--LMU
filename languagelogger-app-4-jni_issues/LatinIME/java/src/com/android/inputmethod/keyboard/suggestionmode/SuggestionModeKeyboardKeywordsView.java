package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.Constants;

public class SuggestionModeKeyboardKeywordsView extends SuggestionModePhase implements View.OnClickListener {

    public SuggestionModeKeyboardKeywordsView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionModeKeyboardViewStyle);
    }

    public SuggestionModeKeyboardKeywordsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.suggestion_keyboard_keywords, this);
    }

    public void onClick(final View view) {
        //Toast.makeText(getContext(), "text here",Toast.LENGTH_SHORT).show();
        AudioAndHapticFeedbackManager.getInstance().performHapticAndAudioFeedback(
                Constants.CODE_UNSPECIFIED, this);
        //TODO: make the buttons do something!
    }

}
