package ph.ubx.xeatsv4.Delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

import ph.ubx.xeatsv4.Customer.CustomerSendOtp;
import ph.ubx.xeatsv4.Customer.customerLoginEmail;
import ph.ubx.xeatsv4.Customer.customerLoginPhone;
import ph.ubx.xeatsv4.R;

public class deliveryLoginPhone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker ccp;
    FirebaseAuth FAuth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login_phone);

        num = (EditText)findViewById(R.id.deliveryLpNumber);
        sendotp = (Button)findViewById(R.id.deliveryLpSendOtpBtn);
        ccp = (CountryCodePicker)findViewById(R.id.deliveryLpCcp);
        signinemail = (Button)findViewById(R.id.deliveryLpEmailBtn);
        signup = (TextView)findViewById(R.id.deliveryLpSignUpText);

        FAuth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                number = num.getText().toString().trim();
                String Phonenumber = ccp.getSelectedCountryCodeWithPlus()+number;
                Intent b = new Intent(deliveryLoginPhone.this, deliverySendOtp.class);

                b.putExtra("Phonenumber", Phonenumber);
                startActivity(b);
                finish();
            }
        });

        signinemail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(deliveryLoginPhone.this, deliveryLoginEmail.class));
                finish();
            }
        });
    }
}