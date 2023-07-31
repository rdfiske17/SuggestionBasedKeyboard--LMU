package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.suggestionkeyboardresearchapplication.R;
import java.util.ArrayList;

public class SuggestionModeKeyboardToneView extends SuggestionModePhase {

    ToggleButton toneButtonr1c1;
    ToggleButton toneButtonr1c2;
    ToggleButton toneButtonr1c3;
    ToggleButton toneButtonr2c1;
    ToggleButton toneButtonr2c2;
    ToggleButton toneButtonr2c3;
    ToggleButton toneButtonr3c1;
    ToggleButton toneButtonr3c2;
    ToggleButton toneButtonr3c3;

    Drawable toneButtonr1c1Selected;
    Drawable toneButtonr1c1Unselected;
    Drawable toneButtonr1c2Selected;
    Drawable toneButtonr1c2Unselected;
    Drawable toneButtonr1c3Selected;
    Drawable toneButtonr1c3Unselected;
    Drawable toneButtonr2c1Selected;
    Drawable toneButtonr2c1Unselected;
    Drawable toneButtonr2c2Selected;
    Drawable toneButtonr2c2Unselected;
    Drawable toneButtonr2c3Selected;
    Drawable toneButtonr2c3Unselected;
    Drawable toneButtonr3c1Selected;
    Drawable toneButtonr3c1Unselected;
    Drawable toneButtonr3c2Selected;
    Drawable toneButtonr3c2Unselected;
    Drawable toneButtonr3c3Selected;
    Drawable toneButtonr3c3Unselected;

    private ArrayList<Drawable> toggleButtonBackgrounds = new ArrayList<Drawable>();

    private ArrayList<ToggleButton> toggleButtons = new ArrayList<ToggleButton>();

    //private ArrayList<String> toneText = new ArrayList<String>();
    private String toneText = "";


    public SuggestionModeKeyboardToneView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionModeKeyboardViewStyle);
    }

    public SuggestionModeKeyboardToneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.suggestion_keyboard_tone, this);

        toneButtonr1c1 = findViewById(R.id.suggestion_keyboard_tone_r1c1);
        toneButtonr1c2 = findViewById(R.id.suggestion_keyboard_tone_r1c2);
        toneButtonr1c3 = findViewById(R.id.suggestion_keyboard_tone_r1c3);
        toneButtonr2c1 = findViewById(R.id.suggestion_keyboard_tone_r2c1);
        toneButtonr2c2 = findViewById(R.id.suggestion_keyboard_tone_r2c2);
        toneButtonr2c3 = findViewById(R.id.suggestion_keyboard_tone_r2c3);
        toneButtonr3c1 = findViewById(R.id.suggestion_keyboard_tone_r3c1);
        toneButtonr3c2 = findViewById(R.id.suggestion_keyboard_tone_r3c2);
        toneButtonr3c3 = findViewById(R.id.suggestion_keyboard_tone_r3c3);

        toneButtonr1c1Selected = getResources().getDrawable(R.drawable.button_happy_selected);
        toneButtonr1c1Unselected = getResources().getDrawable(R.drawable.button_happy_unselected);
        toneButtonr1c2Selected = getResources().getDrawable(R.drawable.button_love_selected);
        toneButtonr1c2Unselected = getResources().getDrawable(R.drawable.button_love_unselected);
        toneButtonr1c3Selected = getResources().getDrawable(R.drawable.button_enthusiastic_selected);
        toneButtonr1c3Unselected = getResources().getDrawable(R.drawable.button_enthusiastic_unselected);
        toneButtonr2c1Selected = getResources().getDrawable(R.drawable.button_goodsurprise_selected);
        toneButtonr2c1Unselected = getResources().getDrawable(R.drawable.button_goodsurprise_unselected);
        toneButtonr2c2Selected = getResources().getDrawable(R.drawable.button_neutral_selected);
        toneButtonr2c2Unselected = getResources().getDrawable(R.drawable.button_neutral_unselected);
        toneButtonr2c3Selected = getResources().getDrawable(R.drawable.button_badsurprise_selected);
        toneButtonr2c3Unselected = getResources().getDrawable(R.drawable.button_badsurprise_unselected);
        toneButtonr3c1Selected = getResources().getDrawable(R.drawable.button_sad_selected);
        toneButtonr3c1Unselected = getResources().getDrawable(R.drawable.button_sad_unselected);
        toneButtonr3c2Selected = getResources().getDrawable(R.drawable.button_anger_selected);
        toneButtonr3c2Unselected = getResources().getDrawable(R.drawable.button_anger_unselected);
        toneButtonr3c3Selected = getResources().getDrawable(R.drawable.button_disgust_selected);
        toneButtonr3c3Unselected = getResources().getDrawable(R.drawable.button_disgust_unselected);

        toggleButtons.add(toneButtonr1c1);
        toggleButtons.add(toneButtonr1c2);
        toggleButtons.add(toneButtonr1c3);
        toggleButtons.add(toneButtonr2c1);
        toggleButtons.add(toneButtonr2c2);
        toggleButtons.add(toneButtonr2c3);
        toggleButtons.add(toneButtonr3c1);
        toggleButtons.add(toneButtonr3c2);
        toggleButtons.add(toneButtonr3c3);


        toggleButtonBackgrounds.add(toneButtonr1c1Selected);
        toggleButtonBackgrounds.add(toneButtonr1c1Unselected);
        toggleButtonBackgrounds.add(toneButtonr1c2Selected);
        toggleButtonBackgrounds.add(toneButtonr1c2Unselected);
        toggleButtonBackgrounds.add(toneButtonr1c3Selected);
        toggleButtonBackgrounds.add(toneButtonr1c3Unselected);
        toggleButtonBackgrounds.add(toneButtonr2c1Selected);
        toggleButtonBackgrounds.add(toneButtonr2c1Unselected);
        toggleButtonBackgrounds.add(toneButtonr2c2Selected);
        toggleButtonBackgrounds.add(toneButtonr2c2Unselected);
        toggleButtonBackgrounds.add(toneButtonr2c3Selected);
        toggleButtonBackgrounds.add(toneButtonr2c3Unselected);
        toggleButtonBackgrounds.add(toneButtonr3c1Selected);
        toggleButtonBackgrounds.add(toneButtonr3c1Unselected);
        toggleButtonBackgrounds.add(toneButtonr3c2Selected);
        toggleButtonBackgrounds.add(toneButtonr3c2Unselected);
        toggleButtonBackgrounds.add(toneButtonr3c3Selected);
        toggleButtonBackgrounds.add(toneButtonr3c3Unselected);

        for(ToggleButton button : toggleButtons) {
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        setToggleButton(compoundButton);
                    } else {
                        //setToggleButton(compoundButton, false);
                    }
                    //setToggleButton(compoundButton);
                }
            }); //https://stackoverflow.com/questions/11776423/android-togglebutton-listener
        }
    }

    public void setToggleButton(CompoundButton toggleButton) {
        ToggleButton newToggleButton = (ToggleButton)toggleButton;
        Log.i("Tone","Entering the background switching loop");
        int ticker = 0;
        for(ToggleButton t : toggleButtons) {
            if(t.isChecked()) {
                if (t.equals(newToggleButton)) {
                    t.setBackground(toggleButtonBackgrounds.get(ticker));
                    setToneText((String)t.getTextOn());
                } else {
                    t.setChecked(false);
                    t.setBackground(toggleButtonBackgrounds.get(ticker + 1));
                }
            }
            else {
                t.setBackground(toggleButtonBackgrounds.get(ticker + 1));
            }
            ticker = ticker + 2;
        }
        Log.i("Tone","Current Tone: " + toneText);
    }

    /*public void addOrRemoveStringToArrayList(String textToAdd, boolean add) {
        boolean inTheArrayList = false;

        for(String text : toneText) {
            if(text.equals(textToAdd)) inTheArrayList = true;
        }
        if(add && !inTheArrayList) toneText.add(textToAdd);
        else if(!add && inTheArrayList) toneText.remove(textToAdd);


        String fullArray = "NO PROVIDED TONES";
        int counter = 0;
        for(String string : toneText) {
            if(counter != 0) { fullArray = fullArray + " | \"" + string + "\""; }
            else { fullArray = "\"" + string + "\""; }
            counter++;
        }
        Log.i("Tones",fullArray);
    }*/

    public void setToneText(String textToAdd) {
        toneText = textToAdd;
    }

    public void phaseSetup() {
        //TODO:
    }

    public void phaseConclusion() {
        SuggestionModeKeyboardView.finalTone = toneText;
    }
}
