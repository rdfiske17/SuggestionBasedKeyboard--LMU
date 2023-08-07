package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.android.inputmethod.latin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SuggestionModeKeyboardRelevantMessagesView extends SuggestionModePhase {


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ToggleButton message1;
    private ToggleButton message2;
    private ToggleButton message3;
    private ToggleButton message4;

    private boolean isResearcher = true;     //TODO Change this to switch between researcher and participant views

    private ArrayList<String> relevantMessages = new ArrayList<String>();


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

        Toast toast = Toast.makeText(getContext(), "Please wait while messages are extracted", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        getRecentMessages();

        /*synchronized (this) {
            while (!textUpdatedYet) {
                try {
                    Log.i("RelevantMessages", "waiting...");
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
        Log.i("RelevantMessages","Done setting-up");
    }

    private void getRecentMessages() {
        ArrayList<String> recentMessagesRaw = new ArrayList<String>();

        db.collection("messages")
                .whereEqualTo("ForParticipant",isResearcher)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i("RelevantMessages", "Raw Relevant Message: " + document.getData().toString());
                                recentMessagesRaw.add(document.getData().toString());
                            }
                            Log.i("RelevantMessages","Done extracting");
                            relevantMessages = cleanRecentMessages(recentMessagesRaw);
                            setTextValues(relevantMessages);
                            /*synchronized (this) {
                                notify();
                            }*/
                        }
                        else {
                            Log.i("RelevantMessages","Error getting document: " + task.getException());
                        }
                    }
                });
    }

    private ArrayList<String> cleanRecentMessages(ArrayList<String> recentMessages) {
        ArrayList<String> cleanedMessages = new ArrayList<String>();

        Log.i("Relevant Messages","Beginning cleaning...");
        int i = 0;
        for(String message : recentMessages) {
            String findIt = "MessageText=";
            int index = message.indexOf(findIt);
            cleanedMessages.add(message.substring(index+findIt.length(),message.length()-1));
            i++;
            Log.i("Relevant Messages","Cleaned Relevant Message for position " + i +": " + message.substring(index+findIt.length(),message.length()-1));
        }
        return cleanedMessages;
    }

    private void setTextValues(ArrayList<String> messages) {
        Log.i("RelevantMessages","changing text...");
        message1.setTextOn(messages.get(0));
        message1.setTextOff(messages.get(0));
        message2.setTextOn(messages.get(1));
        message2.setTextOff(messages.get(1));
        message3.setTextOn(messages.get(2));
        message3.setTextOff(messages.get(2));
        message4.setTextOn(messages.get(3));
        message4.setTextOff(messages.get(3));
        Log.i("RelevantMessages","Button 4 actual text: " + message4.getTextOn());
        message1.toggle(); message1.toggle();
        message2.toggle(); message2.toggle();
        message3.toggle(); message3.toggle();
        message4.toggle(); message4.toggle();
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
