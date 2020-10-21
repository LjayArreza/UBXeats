package ph.ubx.xeatsv4.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

import ph.ubx.xeatsv4.R;
import ph.ubx.xeatsv4.Seller.sellerLoginEmail;
import ph.ubx.xeatsv4.Seller.sellerLoginPhone;
import ph.ubx.xeatsv4.Seller.sellerSendOtp;

public class customerLoginPhone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker ccp;
    FirebaseAuth FAuth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_phone);

        num = (EditText)findViewById(R.id.customerPhoneLoginNumber);
        sendotp = (Button)findViewById(R.id.customerPhoneLoginSendOtpBtn);
        ccp = (CountryCodePicker)findViewById(R.id.customerPhoneLoginCcp);
        signinemail = (Button)findViewById(R.id.customerPhoneLoginLoginEmailBtn);
        signup = (TextView)findViewById(R.id.customerPhoneLoginSingUpText);

        FAuth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                number = num.getText().toString().trim();
                String Phonenumber = ccp.getSelectedCountryCodeWithPlus()+number;
                Intent b = new Intent(customerLoginPhone.this, CustomerSendOtp.class);

                b.putExtra("Phonenumber", Phonenumber);
                startActivity(b);
                finish();
            }
        });

        signinemail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(customerLoginPhone.this, customerLoginEmail.class));
                finish();
            }
        });
    }
}