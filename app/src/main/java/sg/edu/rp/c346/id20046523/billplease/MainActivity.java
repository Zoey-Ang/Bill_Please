package sg.edu.rp.c346.id20046523.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tvAmt;
    EditText etAmt;
    TextView tvNumPax;
    EditText etNumPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView tvDiscount;
    EditText etDiscount;
    RadioGroup rgPaymentMethods;
    Button split;
    Button reset;
    TextView tvBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAmt = findViewById(R.id.amountTextView);
        etAmt = findViewById(R.id.Amount);
        svs = findViewById(R.id.toggleButtonSVS);
        gst = findViewById(R.id.toggleButtonGST);
        tvDiscount = findViewById(R.id.DiscountTextView);
        etDiscount = findViewById(R.id.Discount);
        rgPaymentMethods = findViewById(R.id.radioGroupPaymentMethod);
        split = findViewById(R.id.splitButton);
        reset =findViewById(R.id.resetButton);
        tvBill = findViewById(R.id.totalBillTextView);

        split.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                double totalBill = 0;
                double individual= 0;
                if(svs.isChecked() && gst.isChecked())
                {
                    totalBill = Double.parseDouble(etAmt.getText().toString())*1.17;
                }
                else if(svs.isChecked() && gst.isChecked()==false)
                {
                    totalBill = Double.parseDouble(etAmt.getText().toString()) * 1.10;
                }
                else if(svs.isChecked()==false && gst.isChecked())
                {
                    totalBill = Double.parseDouble(etAmt.getText().toString()) *1.07;
                }
                else
                {
                    totalBill = Double.parseDouble(etAmt.getText().toString());
                }

                if(etDiscount.getText().toString().length() !=0)
                {
                    double dis=Double.parseDouble(etDiscount.getText().toString()) /100;
                    totalBill= totalBill * dis;
                }

                if(Integer.parseInt(etNumPax.getText().toString()) > 1)
                {
                    individual=totalBill / Integer.parseInt(etNumPax.getText().toString());
                }

                String output=" ";
                int checkedRadioId = rgPaymentMethods.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.radioButtonCash)
                {
                    output="Each Pays: $" + individual + " in cash";
                }
                else
                {
                    output="Each Pays: $" + individual + " via PayNow to 912345678";
                }
                tvAmt.setText(String.valueOf(totalBill));
                tvBill.setText(output);
            }
        }
        );


        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                etAmt.setText(" ");
                etNumPax.setText(" ");
                svs.setChecked(false);
                gst.setChecked(false);
                rgPaymentMethods.check(R.id.radioButtonCash);
                etDiscount.setText(" ");
            }
        });

        if(etAmt == null || etNumPax == null || etDiscount == null)
        {
            Toast.makeText(MainActivity.this, "Please fill in all blanks", Toast.LENGTH_SHORT).show();
        }


    }
}