package com.android.inputmethod.keyboard.suggestionmode;

import static com.android.inputmethod.latin.common.Constants.NOT_A_COORDINATE;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;

import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.inputmethod.keyboard.KeyboardActionListener;
import com.android.inputmethod.keyboard.KeyboardLayoutSet;
import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.android.inputmethod.keyboard.MainKeyboardView;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.utils.ResourceUtils;

import java.util.ArrayList;

public class SuggestionModeKeyboardView extends LinearLayout {

    private static final String TAG = SuggestionModeKeyboardView.class.getSimpleName();
    private SuggestionModeKeyboardRelevantMessagesView mRelevantMessagesView;
    private SuggestionModeKeyboardKeywordsView mKeywordsView;
    private SuggestionModeKeyboardToneView mToneView;
    private SuggestionModeKeyboardSuggestionPaletteView mSuggestionPaletteView;

    private ArrayList<SuggestionModePhase> phases = new ArrayList<SuggestionModePhase>();

    private ArrayList<String> phaseText = new ArrayList<String>();

    private SuggestionModeKeyboardHeaderView mSuggestionModeKeyboardHeaderView;
    private TextView mSuggestionModeHeaderText;

    private int currentPhase;
    private final int noMorePagesLowInt = -1;
    private final int noMorePagesHighInt = 4;
    private final int mRelevantMessagesViewInt = 0;
    private final int mKeywordsViewInt = 1;
    private final int mToneViewInt = 2;
    private final int mSuggetionPaletteViewInt = 3;

    private final int mFunctionalKeyBackgroundId;
    private final int mSpacebarBackgroundId;
    private final DeleteKeyOnTouchListener mDeleteKeyOnTouchListener;
    private KeyboardActionListener mKeyboardActionListener = KeyboardActionListener.EMPTY_LISTENER;

    // Keyboard Phase Views
    private KeyboardSwitcher mKeyboardSwitcher = LatinIME.mKeyboardSwitcher;
    private MainKeyboardView mMainKeyboardView;

    public static ArrayList<String> finalRelevantMessages = new ArrayList<String>();
    public static String finalTone = "";
    public static String finalKeywords = "";


    public SuggestionModeKeyboardView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionModeKeyboardViewStyle);
    }

    public SuggestionModeKeyboardView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray keyboardViewAttr = context.obtainStyledAttributes(attrs,
                R.styleable.KeyboardView, defStyle, R.style.KeyboardView);
        final int keyBackgroundId = keyboardViewAttr.getResourceId(
                R.styleable.KeyboardView_keyBackground, 0);
        mFunctionalKeyBackgroundId = keyboardViewAttr.getResourceId(
                R.styleable.KeyboardView_functionalKeyBackground, keyBackgroundId);
        mSpacebarBackgroundId = keyboardViewAttr.getResourceId(
                R.styleable.KeyboardView_spacebarBackground, keyBackgroundId);
        keyboardViewAttr.recycle();
        final KeyboardLayoutSet.Builder builder = new KeyboardLayoutSet.Builder(
                context, null /* editorInfo */);
        final Resources res = context.getResources();
        builder.setSubtype(RichInputMethodSubtype.getNoLanguageSubtype());
        builder.setKeyboardGeometry(ResourceUtils.getDefaultKeyboardWidth(res),
                ResourceUtils.getDefaultKeyboardHeight(res));
        final KeyboardLayoutSet layoutSet = builder.build();
        mDeleteKeyOnTouchListener = new DeleteKeyOnTouchListener();
    }

    @Override
    protected void onFinishInflate() {
        mRelevantMessagesView = findViewById(R.id.suggestion_keyboard_relevant_messages_view);
        mKeywordsView = findViewById(R.id.suggestion_keyboard_keywords_view);
        mToneView = findViewById(R.id.suggestion_keyboard_tone_view);
        mSuggestionPaletteView = findViewById(R.id.suggestion_keyboard_suggestion_palette_view);
        mSuggestionModeKeyboardHeaderView = (SuggestionModeKeyboardHeaderView)findViewById(R.id.suggestion_keyboard_view_header);
        mSuggestionModeHeaderText = findViewById(R.id.suggestion_keyboard_header_text_desc);
        currentPhase = mRelevantMessagesViewInt;

        phases.add(mRelevantMessagesView);
        phases.add(mKeywordsView);
        phases.add(mToneView);
        phases.add(mSuggestionPaletteView);

        phaseText.add("Relevant Messages");
        phaseText.add("Keywords");
        phaseText.add("Tone");
        phaseText.add("Generated Suggestions");

        //mMainKeyboardView = findViewById(R.id.suggestionmode_keywords_keyboard_view);
    }

    public void startSuggestionGenerationSequence() {
        //mRelevantMessagesView.setVisibility(View.VISIBLE);
        currentPhase = mRelevantMessagesViewInt;
        updateSuggestionGenerationPhase(0);
    }

    public void stopSuggestionGenerationSequence() {
        hideAllSuggestionPhases();
    }

    public void updateSuggestionGenerationPhase(int direction) {
        currentPhase = currentPhase + direction;
        /*if(currentPhase < noMorePagesLowInt) {
            currentPhase = noMorePagesLowInt;
        }
        else if(currentPhase > noMorePagesHighInt) {
            currentPhase = noMorePagesHighInt;
        }*/
        //Log.i("SuggestionKeyboardView","phaseToUpdate: " + currentPhase);
        if (currentPhase == noMorePagesLowInt) {
            stopSuggestionGenerationSequence();
            //mSuggestionModeHeaderText.setText("TODO: go back to Alphabet Keyboard");
            mKeyboardSwitcher.setAlphabetKeyboard();
        }
        else if (currentPhase == noMorePagesHighInt) {
            stopSuggestionGenerationSequence();
            //mSuggestionModeHeaderText.setText("TODO: go back to Alphabet Keyboard");
            mKeyboardSwitcher.setAlphabetKeyboard();
        }
        else if (currentPhase == mRelevantMessagesViewInt) {
            //Log.i("SuggestionKeyboardView","Setting Relevant Messages View");
            updateSuggestionGenerationVisibility(mRelevantMessagesView);
        }
        else if (currentPhase == mKeywordsViewInt) {
            updateSuggestionGenerationVisibility(mKeywordsView);
        }
        else if (currentPhase == mToneViewInt) {
            updateSuggestionGenerationVisibility(mToneView);
        }
        else if (currentPhase == mSuggetionPaletteViewInt) {
            updateSuggestionGenerationVisibility(mSuggestionPaletteView);
        }
    }

    public void updateSuggestionGenerationVisibility(SuggestionModePhase viewToShow) {
        int position = 0;
        boolean found = false;
        //Log.i("SuggestionKeyboardView","beginning search process");
        for(SuggestionModePhase phase : phases) {
            //Log.i("SuggestionKeyboardView","Ids: phase: " + phase.getId() + " | viewToShow: " + viewToShow.getId());
            if(phase.getId() == viewToShow.getId()) {
                //Log.i("SuggestionKeyboardView",position + ": found the page to show");
                if(position != mRelevantMessagesViewInt) {
                    phases.get(position-1).phaseConclusion();
                }
                phase.phaseSetup();
                Log.i("SuggestionKeyboardView","Done setting up phase " + position);
                phase.setVisibility(View.VISIBLE);
                found = true;
                mSuggestionModeHeaderText.setText(phaseText.get(position));
            }
            else {
                //Log.i("SuggestionKeyboardView",position + ": not the page to show");
                phase.setVisibility(View.GONE);
                if(!found) {
                    position++;
                }
            }
        }
    }

    public void hideAllSuggestionPhases() {
        for(SuggestionModePhase phase : phases) {
            phase.setVisibility(View.GONE);
        }
    }

    public void setHardwareAcceleratedDrawingEnabled(final boolean enabled) {
        if (!enabled) return;
        // TODO: Should use LAYER_TYPE_SOFTWARE when hardware acceleration is off?
        setLayerType(LAYER_TYPE_HARDWARE, null);
    }

    public void setKeyboardActionListener(final KeyboardActionListener listener) {
        mKeyboardActionListener = listener;
        mDeleteKeyOnTouchListener.setKeyboardActionListener(listener);
    }

    private static class DeleteKeyOnTouchListener implements OnTouchListener {
        private KeyboardActionListener mKeyboardActionListener =
                KeyboardActionListener.EMPTY_LISTENER;

        public void setKeyboardActionListener(final KeyboardActionListener listener) {
            mKeyboardActionListener = listener;
        }

        @Override
        public boolean onTouch(final View v, final MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    onTouchDown(v);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    final float x = event.getX();
                    final float y = event.getY();
                    if (x < 0.0f || v.getWidth() < x || y < 0.0f || v.getHeight() < y) {
                        // Stop generating key events once the finger moves away from the view area.
                        onTouchCanceled(v);
                    }
                    return true;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    onTouchUp(v);
                    return true;
            }
            return false;
        }

        private void onTouchDown(final View v) {
            mKeyboardActionListener.onPressKey(Constants.CODE_DELETE,
                    0 /* repeatCount */, true /* isSinglePointer */);
            v.setPressed(true /* pressed */);
        }

        private void onTouchUp(final View v) {
            mKeyboardActionListener.onCodeInput(Constants.CODE_DELETE,
                    NOT_A_COORDINATE, NOT_A_COORDINATE, false /* isKeyRepeat */);
            mKeyboardActionListener.onReleaseKey(Constants.CODE_DELETE, false /* withSliding */);
            v.setPressed(false /* pressed */);
        }

        private void onTouchCanceled(final View v) {
            v.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
