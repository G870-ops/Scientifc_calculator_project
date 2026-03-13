package com.example.scientificcalculator;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView display, historyDisplay;
    private String input = "";
    private ArrayList<String> historyList = new ArrayList<>();

    // Physics Constants
    private static final double PLANCK_H = 6.62607015e-34;
    private static final double LIGHT_C = 299792458;
    private static final double GRAVITY_G = 6.67430e-11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.txtDisplay);
        historyDisplay = findViewById(R.id.txtHistory);
        GridLayout grid = findViewById(R.id.calculatorGrid);

        // Smooth fade animation
        if (grid != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
            fadeIn.setDuration(1200);
            grid.startAnimation(fadeIn);
        }
    }

    private void animateButton(View view, int flashColor) {

        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        ValueAnimator colorAnim = ValueAnimator.ofObject(
                new ArgbEvaluator(),
                flashColor,
                Color.TRANSPARENT
        );

        colorAnim.setDuration(300);

        colorAnim.addUpdateListener(animator -> {
            if (view.getBackground() != null) {
                view.getBackground().setColorFilter(
                        (int) animator.getAnimatedValue(),
                        PorterDuff.Mode.SRC_ATOP
                );
            }
        });

        colorAnim.start();
    }

    public void onButtonClick(View view) {
        animateButton(view, Color.parseColor("#00E5FF"));

        Button button = (Button) view;
        String text = button.getText().toString();

        if (text.contains("()")) {
            handleBaseConversion(text.replace("()", ""));
            return;
        }

        if (text.equals("x²")) input += "^2";
        else if (text.equals("√")) input += "sqrt(";
        else if (text.equals("sin") || text.equals("cos") ||
                text.equals("tan") || text.equals("log"))
            input += text + "(";
        else input += text;

        display.setText(input);
    }

    public void onConstantClick(View view) {
        animateButton(view, Color.parseColor("#B2FF59"));

        Button b = (Button) view;
        String val = b.getText().toString();

        switch (val) {
            case "π":
                input += Math.PI;
                break;
            case "e":
                input += Math.E;
                break;
            case "h":
                input += PLANCK_H;
                break;
            case "c":
                input += LIGHT_C;
                break;
            case "G":
                input += GRAVITY_G;
                break;
        }

        display.setText(input);
    }

    public void onClear(View view) {
        animateButton(view, Color.RED);
        input = "";
        display.setText("0");
        if (historyDisplay != null) historyDisplay.setText("");
    }

    public void onDelete(View view) {
        animateButton(view, Color.YELLOW);

        if (!input.isEmpty()) {
            input = input.substring(0, input.length() - 1);
            display.setText(input.isEmpty() ? "0" : input);
        }
    }

    @SuppressLint("SetTextI18n")
    public void onEquals(View view) {
        animateButton(view, Color.GREEN);

        try {
            double result = eval(input);

            String formattedResult =
                    (result == (long) result)
                            ? String.valueOf((long) result)
                            : String.valueOf(result);

            if (historyDisplay != null)
                historyDisplay.setText(input + " =");

            historyList.add(input + " = " + formattedResult);

            input = formattedResult;
            display.setText(input);

        } catch (Exception e) {
            display.setText("Error");
            input = "";
        }
    }

    private void handleBaseConversion(String base) {
        try {
            int val = (int) eval(input);

            String result = "";

            switch (base) {
                case "bin":
                    result = Integer.toBinaryString(val);
                    break;
                case "hex":
                    result = Integer.toHexString(val).toUpperCase();
                    break;
                case "oct":
                    result = Integer.toOctalString(val);
                    break;
            }

            display.setText(result);
            input = result;

        } catch (Exception e) {
            Toast.makeText(this,
                    "Result must be an integer",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // =============================
    // ADVANCED EXPRESSION PARSER
    // =============================
    public double eval(final String str) {

        return new Object() {

            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length())
                    throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {

                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;

                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                }

                else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                }

                else if ((ch >= 'a' && ch <= 'z') || ch == '√') {
                    while ((ch >= 'a' && ch <= 'z') || ch == '√') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();

                    switch (func) {
                        case "sqrt":
                        case "√":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        case "log":
                            x = Math.log10(x);
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                }

                else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }

        }.parse();
    }
}
