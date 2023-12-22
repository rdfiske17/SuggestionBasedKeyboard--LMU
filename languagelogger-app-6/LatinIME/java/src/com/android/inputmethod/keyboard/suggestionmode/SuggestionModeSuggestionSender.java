package com.android.inputmethod.keyboard.suggestionmode;

import android.inputmethodservice.InputMethodService;
import android.util.Log;

import com.android.inputmethod.latin.RichInputConnection;
import com.android.inputmethod.latin.inputlogic.InputLogic;

public class SuggestionModeSuggestionSender extends InputMethodService {

    // Idea from: https://www.youtube.com/watch?v=cHzU8LfGSYA

    private String textToPost;
    private String lastText = "";
    private RichInputConnection connection = InputLogic.mConnection;

    public SuggestionModeSuggestionSender(String text) {
        textToPost = text;
    }

    public void setText(String text) {
        textToPost = text;
    }

    public String getText() {
        return textToPost;
    }

    public void postText() {
        if (connection == null) {
            Log.i("SuggestionSender","Could not post text: " + textToPost);
            //Log.i("SuggestionSender",getCurrentInputConnection().toString());
            return;
        }
        else {
            Log.i("SuggestionSender", "Committing text: " + textToPost);
            connection.deleteTextBeforeCursor(RichInputConnection.mCommittedTextBeforeComposingText.length());
            connection.commitText(textToPost,1);
            lastText = textToPost;
            Log.i("SuggestionSender", "Committed text: " + textToPost);
        }
    }
}
