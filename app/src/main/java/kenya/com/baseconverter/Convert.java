package kenya.com.baseconverter;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;


public class Convert extends Activity {

    private TextView tvHex;
    private TextView tvDex;
    private TextView tvBin;
    private EditText etHexadecimal;
    private EditText etDecimal;
    private EditText etBinary;
    private Button bClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_convert);

        /* Set up all TextViews */
        this.tvHex = (TextView) this.findViewById(R.id.hexTextView);
        this.tvDex = (TextView) this.findViewById(R.id.decimalTextView);
        this.tvBin = (TextView) this.findViewById(R.id.binaryTextView);

        /* set up all EditTexts */
        this.etHexadecimal = (EditText) this.findViewById(R.id.hexEditText);
        this.etDecimal = (EditText) this.findViewById(R.id.decimalEditText);
        this.etBinary = (EditText) this.findViewById(R.id.binaryEditText);

        /* set up Clear Button */
        this.bClear = (Button) this.findViewById(R.id.clearButton);

        /*
        Converting Hexadecimal numbers to decimal and binary
         */
        this.etHexadecimal.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                Editable hexNumber = Convert.this.etHexadecimal.getText();
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String hex = hexNumber.toString();
                    try {
                        Long decRep = Long.parseLong(hex, 16);
                        String binRep = Long.toBinaryString(Long.parseLong(hex, 16));
                        Convert.this.etDecimal.setText(Long.toString(decRep));
                        Convert.this.etBinary.setText(binRep);
                        handled = true;
                    } catch (NumberFormatException e){
                        Context context = getApplicationContext();
                        CharSequence warning = "Number is too big!";
                        int duration = Toast.LENGTH_LONG;
                        Toast.makeText(context,warning, duration).show();
                        handled = true;
                    }
                }
                return handled;
            }
        });

        /*
        converting decimal to Hex and Binary
         */
        this.etDecimal.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                boolean handled = false;
                Editable decNumber = Convert.this.etDecimal.getText();
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String dec = decNumber.toString();
                    try {
                        Long decRep = Long.parseLong(dec);
                        String hexRep = Long.toHexString(decRep);
                        String binRep = Long.toBinaryString(Long.parseLong(dec, 10));
                        Convert.this.etHexadecimal.setText(hexRep
                                .toUpperCase(Locale.US));
                        Convert.this.etBinary.setText(binRep);
                        handled = true;
                    }
                    catch (NumberFormatException e) {
                        Context context = getApplicationContext();
                        CharSequence warning = "Number is too big!";
                        int duration = Toast.LENGTH_LONG;
                        Toast.makeText(context,warning, duration).show();
                        handled = true;
                    }

                }
                return handled;
            }
        });

        /*
        Converting binary to Hex and decimal
         */
        this.etBinary.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                Editable binNumber = Convert.this.etBinary.getText();
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String binRep = binNumber.toString();
                    try {
                        Long decRep = Long.parseLong(binRep, 2);
                        String hexRep = Long.toHexString(decRep);
                        Convert.this.etDecimal.setText(decRep.toString());
                        Convert.this.etHexadecimal.setText(hexRep
                                .toUpperCase(Locale.US));
                        handled = true;
                    } catch (NumberFormatException e) {
                        Context context = getApplicationContext();
                        CharSequence warning = "Number is too big!";
                        int duration = Toast.LENGTH_LONG;
                        Toast.makeText(context,warning, duration).show();
                        handled = true;
                    }

                }
                return handled;
            }

        });

        /* Set up Clear Button */
        this.bClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Convert.this.etHexadecimal.setText("");
                Convert.this.etDecimal.setText("");
                Convert.this.etBinary.setText("");
            }

        });

    }/* End Bracket */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.convert, menu);
        return true;
    }

}
