package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.example.suggestionkeyboardresearchapplication.R;
import java.util.ArrayList;

public class SuggestionModeKeyboardRelevantMessagesView extends SuggestionModePhase {

    private ToggleButton message1;
    private ToggleButton message2;
    private ToggleButton message3;
    private ToggleButton message4;


    private ArrayList<ToggleButton> toggleButtons = new ArrayList<ToggleButton>();

    private ArrayList<String> relevantMessagesText = new ArrayList<String>();

    private SuggestionModeKeyboardView mSuggestionModeKeyboardView = KeyboardSwitcher.mSuggestionPagesView;

    public SuggestionModeKeyboardRelevantMessagesView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionModeKeyboardViewStyle);
    }

    public SuggestionModeKeyboardRelevantMessagesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.suggestion_keyboard_relevant_messages, this);

        message1 = findViewById(R.id.relevant_messages_message1_button);
        message2 = findViewById(R.id.relevant_messages_message2_button);
        message3 = findViewById(R.id.relevant_messages_message3_button);
        message4 = findViewById(R.id.relevant_messages_message4_button);

        toggleButtons.add(message1);
        toggleButtons.add(message2);
        toggleButtons.add(message3);
        toggleButtons.add(message4);

        for(ToggleButton button : toggleButtons) {
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        setToggleButton(compoundButton, true);
                    } else {
                        setToggleButton(compoundButton, false);
                    }
                }
            }); //https://stackoverflow.com/questions/11776423/android-togglebutton-listener
        }
    }


    public void setToggleButton(CompoundButton toggleButton, Boolean checked) {
        ToggleButton newToggleButton = (ToggleButton)toggleButton;
        if(checked) {
            newToggleButton.setBackgroundResource(R.drawable.button_selected);
            addOrRemoveStringToArrayList((String)newToggleButton.getTextOn());
        } else {
            newToggleButton.setBackgroundResource(R.drawable.button_unselected);
            addOrRemoveStringToArrayList((String)newToggleButton.getTextOff());
        }
    }

    public void phaseSetup() {
        //TODO: Here is where the text message data will be pulled from the messaging application and injected into the text views
        //TODO: Make sure to set both TextOn and TextOff!!!
    }

    public void phaseConclusion() {
        SuggestionModeKeyboardView.finalRelevantMessages = relevantMessagesText;
    }

    public void addOrRemoveStringToArrayList(String textToAdd) {
        boolean inTheArrayList = false;
        for(String text : relevantMessagesText) {
            if(text.equals(textToAdd)) inTheArrayList = true;
        }
        if(!inTheArrayList) relevantMessagesText.add(textToAdd);
        else if(inTheArrayList) relevantMessagesText.remove(textToAdd);

        String fullArray = "NO PROVIDED MESSAGES";
        int counter = 0;
        for(String string : relevantMessagesText) {
            if(counter != 0) { fullArray = fullArray + " | \"" + string + "\""; }
            else { fullArray = "\"" + string + "\""; }
            counter++;
        }
        Log.i("RelevantMessages",fullArray);
    }

}
