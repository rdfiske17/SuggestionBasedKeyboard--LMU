package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.LatinIME;
import com.example.suggestionkeyboardresearchapplication.R;import com.android.inputmethod.latin.common.Constants;

public class SuggestionModeKeyboardHeaderView extends LinearLayout implements View.OnClickListener {

    /*public interface Listener{
        public void onCodeInput(int primaryCode, int x, int y, boolean isKeyRepeat);
    }*/

    private Button mBackButton;
    private Button mForwardButton;
    private TextView mSuggestionPageTitleText;
    private KeyboardSwitcher mKeyboardSwitcher = LatinIME.mKeyboardSwitcher;
    private SuggestionModeKeyboardView mSuggestionModeKeyboardView = KeyboardSwitcher.mSuggestionPagesView;
    //Listener mListener;

    private final int forward = 1;
    private final int backward = -1;

    public SuggestionModeKeyboardHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionModeKeyboardHeaderStyle);
    }

    public SuggestionModeKeyboardHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.suggestion_keyboard_header, this);

        //mSuggestionModeKeyboardView = (SuggestionModeKeyboardView)findViewById(R.id.suggestion_mode_view);

        mBackButton = (Button)findViewById(R.id.suggestion_keyboard_header_backbutton);
        mBackButton.setOnClickListener(this);

        mSuggestionPageTitleText = (TextView)findViewById(R.id.suggestion_keyboard_header_text_desc);

        mForwardButton = (Button)findViewById(R.id.suggestion_keyboard_header_forwardbutton);
        mForwardButton.setOnClickListener(this);

    }

    /*public void setListener(final Listener listener) {
        mListener = listener;
    }*/

    @Override
    public void onClick(final View view) {
        //Toast.makeText(getContext(), "text here",Toast.LENGTH_SHORT).show();
        AudioAndHapticFeedbackManager.getInstance().performHapticAndAudioFeedback(
                Constants.CODE_UNSPECIFIED, this);
        if (view == mBackButton) {
            //Log.i("Header","It's going through here!");
            //mSuggestionModeKeyboardView.updateSuggestionGenerationPhase(backward);
            //mSuggestionModeKeyboardView.updateSuggestionGenerationPhase(backward);
            mKeyboardSwitcher.updateSuggestionKeyboard(backward);
            //mSuggestionPageTitleText.setText("Backwards!");
            return;
        }
        if (view == mForwardButton) {
            //mSuggestionModeKeyboardView.updateSuggestionGenerationPhase(forward);
            //mSuggestionModeKeyboardView.updateSuggestionGenerationPhase(forward);
            mKeyboardSwitcher.updateSuggestionKeyboard(forward);
            //mSuggestionPageTitleText.setText("Forwards!");
            return;
        }
    }
}
