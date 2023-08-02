package com.android.inputmethod.keyboard.suggestionmode;

import android.content.Context;
import android.graphics.Typeface;
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

public class SuggestionModeKeyboardKeywordsView extends SuggestionModePhase implements View.OnClickListener{

    /*
    Adapted from https://programtown.com/how-to-make-qwerty-keyboard-layout-with-buttons-functionality-in-android-kotlin/ into Java for a quick Keyboard Solution
     */

    private Boolean caps = true;
    private int maxChars = 25; //used "m" as the widest letter, 25 m's fill up the screen

    TextView textContainer;
    Button capsButton;
    Button deleteButton;
    Button spaceButton;
    Button A;
    Button B;
    Button C;
    Button D;
    Button E;
    Button F;
    Button G;
    Button H;
    Button I;
    Button J;
    Button K;
    Button L;
    Button M;
    Button N;
    Button O;
    Button P;
    Button Q;
    Button r;
    Button S;
    Button T;
    Button U;
    Button V;
    Button W;
    Button X;
    Button Y;
    Button Z;
    

    public SuggestionModeKeyboardKeywordsView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.suggestionModeKeyboardViewStyle);
    }

    public SuggestionModeKeyboardKeywordsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.suggestion_keyboard_keywords, this);

        textContainer = findViewById(R.id.textContainer);
        capsButton = findViewById(R.id.CAPS);
        deleteButton = findViewById(R.id.DEL);
        spaceButton = findViewById(R.id.SPACE);
        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        E = findViewById(R.id.E);
        F = findViewById(R.id.F);
        G = findViewById(R.id.G);
        H = findViewById(R.id.H);
        I = findViewById(R.id.I);
        J = findViewById(R.id.J);
        K = findViewById(R.id.K);
        L = findViewById(R.id.L);
        M = findViewById(R.id.M);
        N = findViewById(R.id.N);
        O = findViewById(R.id.O);
        P = findViewById(R.id.P);
        Q = findViewById(R.id.Q);
        r = findViewById(R.id.r);
        S = findViewById(R.id.S);
        T = findViewById(R.id.T);
        U = findViewById(R.id.U);
        V = findViewById(R.id.V);
        W = findViewById(R.id.W);
        X = findViewById(R.id.X);
        Y = findViewById(R.id.Y);
        Z = findViewById(R.id.Z);

        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        E.setOnClickListener(this);
        F.setOnClickListener(this);
        G.setOnClickListener(this);
        H.setOnClickListener(this);
        I.setOnClickListener(this);
        J.setOnClickListener(this);
        K.setOnClickListener(this);
        L.setOnClickListener(this);
        M.setOnClickListener(this);
        N.setOnClickListener(this);
        O.setOnClickListener(this);
        P.setOnClickListener(this);
        Q.setOnClickListener(this);
        r.setOnClickListener(this);
        S.setOnClickListener(this);
        T.setOnClickListener(this);
        U.setOnClickListener(this);
        V.setOnClickListener(this);
        W.setOnClickListener(this);
        X.setOnClickListener(this);
        Y.setOnClickListener(this);
        Z.setOnClickListener(this);
        capsButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        spaceButton.setOnClickListener(this);
    }

    public void phaseSetup() {

    }

    public void phaseConclusion() {
        SuggestionModeKeyboardView.finalKeywords = (String)textContainer.getText();
    }

    /*private void onClickButton(final View view) {
        if (view.getId() == R.id.SPACE) { onSpace(); }
        else if(view.getId() == R.id.CAPS) { changeCaps(); }
        else if (view.getId() == R.id.DEL) { onDelete(); }
        else if (view.getId() == R.id.A) { onType("A"); }
        else if (view.getId() == R.id.B) { onType("B"); }
        else if (view.getId() == R.id.C) { onType("C"); }
        else if (view.getId() == R.id.D) { onType("D"); }
        else if (view.getId() == R.id.E) { onType("E"); }
        else if (view.getId() == R.id.F) { onType("F"); }
        else if (view.getId() == R.id.G) { onType("G"); }
        else if (view.getId() == R.id.H) { onType("H"); }
        else if (view.getId() == R.id.I) { onType("I"); }
        else if (view.getId() == R.id.J) { onType("J"); }
        else if (view.getId() == R.id.K) { onType("K"); }
        else if (view.getId() == R.id.L) { onType("L"); }
        else if (view.getId() == R.id.M) { onType("M"); }
        else if (view.getId() == R.id.N) { onType("N"); }
        else if (view.getId() == R.id.O) { onType("O"); }
        else if (view.getId() == R.id.P) { onType("P"); }
        else if (view.getId() == R.id.Q) { onType("Q"); }
        else if (view.getId() == R.id.r) { onType("R"); }
        else if (view.getId() == R.id.S) { onType("S"); }
        else if (view.getId() == R.id.T) { onType("T"); }
        else if (view.getId() == R.id.U) { onType("U"); }
        else if (view.getId() == R.id.V) { onType("V"); }
        else if (view.getId() == R.id.W) { onType("W"); }
        else if (view.getId() == R.id.X) { onType("X"); }
        else if (view.getId() == R.id.Y) { onType("Y"); }
        else if (view.getId() == R.id.Z) { onType("Z"); }
    }*/

    private void onType(String character) {
        Log.i("Keywords","Typing in for " + character);
        String character1 = character;
        String text = textContainer.getText().toString();
        if (text.length() < maxChars) {
            if (caps) {
                character1 = character1.toUpperCase();
                changeCaps();
            } else {
                character1 = character1.toLowerCase();
            }
            textContainer.setText(text + character1);
            Log.i("Keywords",(String)textContainer.getText()); }
        else
            Toast.makeText(getContext(),"Maximum character count reached! (" + maxChars + ")",Toast.LENGTH_SHORT).show();
    }

    private void onDelete() {
        String text = textContainer.getText().toString();
        if (text.length() > 0) {
            textContainer.setText(removeLastChar(text));
            if (textContainer.getText().length() == 0 && !caps) {
                changeCaps();
            }
        }
    }

    private void onSpace() {
        String text = textContainer.getText().toString();
        textContainer.setText(text + " ");
    }

    private void changeCaps() {
        caps = !caps;
        setCaps(caps);
    }

    private void setCaps(Boolean caps) {
        if (caps) {
            largeCaps();
            capsButton.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            smallCaps();
            capsButton.setTypeface(Typeface.DEFAULT);
        }
    }

    private void largeCaps() {
        A.setText("A");
        B.setText("B");
        C.setText("C");
        D.setText("D");
        E.setText("E");
        F.setText("F");
        G.setText("G");
        H.setText("H");
        I.setText("I");
        J.setText("J");
        K.setText("K");
        L.setText("L");
        M.setText("M");
        N.setText("N");
        O.setText("O");
        P.setText("P");
        Q.setText("Q");
        r.setText("R");
        S.setText("S");
        T.setText("T");
        U.setText("U");
        V.setText("V");
        W.setText("W");
        X.setText("X");
        Y.setText("Y");
        Z.setText("Z");
    }

    private void smallCaps() {
        A.setText("a");
        B.setText("b");
        C.setText("c");
        D.setText("d");
        E.setText("e");
        F.setText("f");
        G.setText("g");
        H.setText("h");
        I.setText("i");
        J.setText("j");
        K.setText("k");
        L.setText("l");
        M.setText("m");
        N.setText("n");
        O.setText("o");
        P.setText("p");
        Q.setText("q");
        r.setText("r");
        S.setText("s");
        T.setText("t");
        U.setText("u");
        V.setText("v");
        W.setText("w");
        X.setText("x");
        Y.setText("y");
        Z.setText("z");
    }

    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    public void onClick(final View view) {
        //Toast.makeText(getContext(), "text here",Toast.LENGTH_SHORT).show();
        AudioAndHapticFeedbackManager.getInstance().performHapticAndAudioFeedback(
                Constants.CODE_UNSPECIFIED, this);
        Log.i("Keywords","Detecting input key now");
        if (view.getId() == R.id.SPACE) { onSpace(); }
        else if(view.getId() == R.id.CAPS) { changeCaps(); }
        else if (view.getId() == R.id.DEL) { onDelete(); }
        else if (view.getId() == R.id.A) { onType("A"); }
        else if (view.getId() == R.id.B) { onType("B"); }
        else if (view.getId() == R.id.C) { onType("C"); }
        else if (view.getId() == R.id.D) { onType("D"); }
        else if (view.getId() == R.id.E) { onType("E"); }
        else if (view.getId() == R.id.F) { onType("F"); }
        else if (view.getId() == R.id.G) { onType("G"); }
        else if (view.getId() == R.id.H) { onType("H"); }
        else if (view.getId() == R.id.I) { onType("I"); }
        else if (view.getId() == R.id.J) { onType("J"); }
        else if (view.getId() == R.id.K) { onType("K"); }
        else if (view.getId() == R.id.L) { onType("L"); }
        else if (view.getId() == R.id.M) { onType("M"); }
        else if (view.getId() == R.id.N) { onType("N"); }
        else if (view.getId() == R.id.O) { onType("O"); }
        else if (view.getId() == R.id.P) { onType("P"); }
        else if (view.getId() == R.id.Q) { onType("Q"); }
        else if (view.getId() == R.id.r) { onType("R"); }
        else if (view.getId() == R.id.S) { onType("S"); }
        else if (view.getId() == R.id.T) { onType("T"); }
        else if (view.getId() == R.id.U) { onType("U"); }
        else if (view.getId() == R.id.V) { onType("V"); }
        else if (view.getId() == R.id.W) { onType("W"); }
        else if (view.getId() == R.id.X) { onType("X"); }
        else if (view.getId() == R.id.Y) { onType("Y"); }
        else if (view.getId() == R.id.Z) { onType("Z"); }    }

}
