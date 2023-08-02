package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.Constants;

public class SuggestionModeKeyboardSuggestionPaletteView extends SuggestionModePhase implements View.OnClickListener {

    private String finalRelevantMessages = "";
    private TextView text1;
    private Button suggestedMessage1Button;
    private Button suggestedMessage2Button;
    private Button suggestedMessage3Button;
    private Button suggestedMessage4Button;

    public SuggestionModeKeyboardSuggestionPaletteView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionModeKeyboardViewStyle);
    }

    public SuggestionModeKeyboardSuggestionPaletteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.suggestion_keyboard_suggestion_palette_view, this);

        text1 = findViewById(R.id.suggested_messages_text);
        suggestedMessage1Button = findViewById(R.id.suggested_messages_message1_button);
        suggestedMessage1Button.setOnClickListener(this);
        suggestedMessage2Button = findViewById(R.id.suggested_messages_message2_button);
        suggestedMessage2Button.setOnClickListener(this);
        suggestedMessage3Button = findViewById(R.id.suggested_messages_message3_button);
        suggestedMessage3Button.setOnClickListener(this);
        suggestedMessage4Button = findViewById(R.id.suggested_messages_message4_button);
        suggestedMessage4Button.setOnClickListener(this);
    }

    public void phaseSetup() {
        int counter = 0;
        for(String string : SuggestionModeKeyboardView.finalRelevantMessages) {
            if(counter != 0) { finalRelevantMessages = finalRelevantMessages + " , \"" + string + "\""; }
            else { finalRelevantMessages = "\"" + string + "\""; }
            counter++;
        }
        text1.setText("Final Relevant Messages: " + finalRelevantMessages + " \n" + "Final Keywords: " + SuggestionModeKeyboardView.finalKeywords + "\n" + "Final Relevant Tone: " + SuggestionModeKeyboardView.finalTone);
        Log.i("SuggestionPalettes","Final Relevant Messages: " + finalRelevantMessages + " | Final Keywords: " + SuggestionModeKeyboardView.finalKeywords + " | Final Relevant Tone: " + SuggestionModeKeyboardView.finalTone);
    }

    public void onClick(final View view) {
        Button pressedButton = (Button) view;
        Log.i("SuggestionPalette","User has selected: " + pressedButton.getText());
        Toast.makeText(getContext(), "You have selected: " + pressedButton.getText(), Toast.LENGTH_SHORT).show();
        AudioAndHapticFeedbackManager.getInstance().performHapticAndAudioFeedback(
                Constants.CODE_UNSPECIFIED, this);
        //TODO: make the buttons do something!
    }

}
