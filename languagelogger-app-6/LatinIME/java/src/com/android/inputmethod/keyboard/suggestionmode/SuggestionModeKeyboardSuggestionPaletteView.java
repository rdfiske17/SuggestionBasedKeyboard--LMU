package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.android.inputmethod.latin.AudioAndHapticFeedbackManager;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.Constants;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import de.lmu.ifi.researchime.module.BuildConfig;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SuggestionModeKeyboardSuggestionPaletteView extends SuggestionModePhase implements View.OnClickListener {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    private String OPENAPI_KEY = getResources().getString(R.string.OpenAPI_Key);

    private String finalRelevantMessages = "";
    private TextView text1;
    private Button suggestedMessage1Button;
    private Button suggestedMessage2Button;
    private Button suggestedMessage3Button;
    private Button suggestedMessage4Button;
    private String[] suggestedMessages = new String[4];
    private boolean suggestedMessagesLoaded = false;
    private String recentQueryText;


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

        Toast toast = Toast.makeText(getContext(), "Generating Responses...Thank you for your patience", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        int counter = 0;
        for(String string : SuggestionModeKeyboardView.finalRelevantMessages) {
            if(counter != 0) { finalRelevantMessages = finalRelevantMessages + " , \"" + string + "\""; }
            else { finalRelevantMessages = "\"" + string + "\""; }
            counter++;
        }
        text1.setText("Final Relevant Messages: " + finalRelevantMessages + " \n" + "Final Keywords: " + SuggestionModeKeyboardView.finalKeywords + "\n" + "Final Relevant Tone: " + SuggestionModeKeyboardView.finalTone);
        Log.i("SuggestionPalettes","Final Relevant Messages: " + finalRelevantMessages + " | Final Keywords: " + SuggestionModeKeyboardView.finalKeywords + " | Final Relevant Tone: " + SuggestionModeKeyboardView.finalTone);

        String query = "Create 4 text message responses to these relevant messages: " + finalRelevantMessages + ". The responses tone should be " + SuggestionModeKeyboardView.finalTone + ". Incorporate these keywords into the idea of the message: " + SuggestionModeKeyboardView.finalKeywords;
        recentQueryText = query;

        callApi(query);
        //int i = 0;
        while(!suggestedMessagesLoaded) {
            //Log.i("SuggestionPalette", "Number of times waited " + i);
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //i++;
        }
        Log.i("SuggestionPalettee", "" + suggestedMessagesLoaded);
        Log.i("SuggestionPalette","Configuring suggested messages into views");
        addMessages(suggestedMessages);
        suggestedMessagesLoaded = false;
    }

    public void callApi(String query) {
        //utilizing okhttp

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "gpt-3.5-turbo-instruct");
            jsonBody.put("prompt",query);
            jsonBody.put("max_tokens",500);
            jsonBody.put("temperature",0);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAPI_KEY)
                .post(body)
                .build();

        Log.i("SuggestionPalette","Request String: " + request.toString());

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    Log.i("SuggestionPalette","API Call Successful");
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String results = jsonArray.getJSONObject(0).getString("text");
                        Log.i("SuggestionPalette",response.toString());
                        sortQueryString(results);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    //response.toString();
                    Log.i("SuggestionPalette","API Response received unsuccessfully");
                    Log.i("SuggestionPalette",response.toString());
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("SuggestionPalette","API Call Overall Failed");
            }
        });
    }

    public void sortQueryString(String results) {
        //TODO
        Log.i("SuggestionPalette",results);
        //text1.setText(results);
        String prefix = "1)";

        int startIndex = results.indexOf(prefix);

        for (int i = 0; i < 4; i++) {
            int nextPrefixIndex = results.indexOf((i + 2) + ")", startIndex + 1);
            if (nextPrefixIndex == -1) {
                nextPrefixIndex = results.length();
            }
            String message = results.substring(startIndex + prefix.length(), nextPrefixIndex).trim();
            suggestedMessages[i] = message.replaceAll("\"","");
            startIndex = nextPrefixIndex;
        }
        Log.i("SuggestionPalette","Setting to true to begin the view changing");
        suggestedMessagesLoaded = true;
        synchronized (this) {
            notify();
        }
    }

    public void addMessages(String[] messages) {
        suggestedMessage1Button.setText(messages[0]);
        suggestedMessage2Button.setText(messages[1]);
        suggestedMessage3Button.setText(messages[2]);
        suggestedMessage4Button.setText(messages[3]);
        text1.setText("Suggested Message Query: " + recentQueryText);
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
