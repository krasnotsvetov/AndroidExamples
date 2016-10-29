package com.calculator.flanir.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.xml.sax.DTDHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Asus on 21.10.2015.
 */
public class CalcMain extends Activity {

    BigDecimal ans, num;
    char f = 'n';
    boolean clear = false;
    String text = "";
    public void func(View view) {
        Button b = (Button)view;
        String t = b.getText().toString();
        if (!text.equals("error")) {
            switch (t.charAt(0)) {
                case 'A':

                    text = "0";
                    clear = false;
                    magic = false;
                    f = 'n';
                    break;

                case 'C':
                    if (text.length() == 1) {
                        text = "0";
                    } else {
                        text = text.substring(0, text.length() - 1);
                    }
                    ans = new BigDecimal(text);
                    break;
                case '+':
                    magic = false;
                    if (t.length() > 1) {
                        if (ans != null) {
                            ans = ans.multiply(new BigDecimal("-1"));
                        }
                        if ((text.length() > 1 || text.charAt(0) != '0') && text.charAt(0) != '-') {
                            text = "-" + text;
                        } else if (text.charAt(0) == '-') {
                            text = text.substring(1);
                        }
                    } else {

                        if (!clear) {
                            clear = true;

                            num = new BigDecimal(text);
                            apply();
                            f = '+';
                            //apply();
                            this.text = ans.toString();
                        }

                        f = '+';
                    }
                    break;
                case '-':
                    magic = false;
                    if (!clear) {
                        clear = true;

                        num = new BigDecimal(text);
                        apply();

                        //num = new BigDecimal(text);
                        f = '-';
                        //num = new BigDecimal(text);
                        //apply();
                        this.text = ans.toString();
                    }

                    f = '-';
                    break;
                case '*':
                    magic = false;
                    if (!clear) {
                        clear = true;
                        num = new BigDecimal(text);


                        apply();
                        f = '*';
                        //apply();
                        this.text = ans.toString();
                    }

                    f = '*';
                    break;
                case '/':
                    magic = false;
                    if (!clear) {
                        clear = true;

                        num = new BigDecimal(text);
                        apply();
                        // f = '/';
                        //num = new BigDecimal(text);
                        if (!text.equals("error")) {
                            this.text = ans.toString();
                        }
                    }

                    f = '/';
                    break;
                case '=':

                    clear = true;
                    if (!magic) {
                        num = new BigDecimal(text);
                    }
                    magic = true;
                    apply();
                    if (!text.equals("error")) {
                        this.text = ans.toString();
                    }
                    break;
            }
        } else {
            if (t.charAt(0) == 'A') {
                text = "0";
                clear = false;
                magic = false;
                f = 'n';
            }
        }
        TextView tv = (TextView)findViewById(R.id.textView);


        tv.setText(text);
    }

    boolean magic = false;
    void apply() {

        if (!text.equals("error")) {
            switch (f) {
                case '+':
                    ans = ans.add(num);
                    break;
                case '-':
                    ans = ans.subtract(num);
                    break;
                case '*':
                    ans = ans.multiply(num);
                    break;
                case '/':
                    System.out.println(ans);
                    System.out.println(num);
                    if (num.equals(new BigDecimal(0))) {
                        text = "error";
                        TextView t = (TextView)findViewById(R.id.textView);

                        t.setText(text);

                    } else {
                        ans = ans.divide(num, 8, RoundingMode.HALF_UP);
                    }
                    break;
                case 'n':
                    ans = new BigDecimal(text);

                    break;
            }
        }
    }
    public void add(View view) {
        if (!text.equals("error")) {
            if (clear) {
                text = "0";
                clear = false;
            }
            if (magic) {
                f = 'n';
            }
            magic = false;
            Button b = (Button) view;
            if (text.length() == 1 && text.charAt(0) == '0') {
                text = b.getText().toString();
            } else {
                text += b.getText();
            }


            TextView t = (TextView) findViewById(R.id.textView);
            int k = 0;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '.') {
                    k++;
                }
            }
            if (k > 1) {
                text = text.substring(0, text.length() - 1);
            }
            t.setText(text);
        }
    }

    @Override
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            magic = savedInstanceState.getBoolean("magic");
            clear = savedInstanceState.getBoolean("clear");
            text = savedInstanceState.getString("text");
            f = savedInstanceState.getChar("f");
            ans = savedInstanceState.getString("ans").length() == 0 ? null : new BigDecimal(savedInstanceState.getString("ans"));
            num = savedInstanceState.getString("num").length() == 0 ? null : new BigDecimal(savedInstanceState.getString("num"));
//            magic = Data.magic;
//            clear = Data.clear;
//            text = Data.text;
//            ans = Data.ans;
//            num = Data.num;
//            f = Data.f;
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.layout);

        TextView t = (TextView)findViewById(R.id.textView);

        t.setText(text);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("magic", magic);
        outState.putBoolean("clear", clear);
        outState.putString("text", text);
        outState.putChar("f", f);
        outState.putString("ans", ans == null ? "" : ans.toString());
        outState.putString("num", num == null ? "" : num.toString());
        //Data.magic = magic;
        //Data.clear = clear;
        //Data.text = text;
        //Data.ans = ans;
        //Data.num = num;
        //Data.f = f;
}
}
