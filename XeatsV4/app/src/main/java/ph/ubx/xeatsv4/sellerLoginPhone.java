package ph.ubx.xeatsv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class sellerLoginPhone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login_phone);

        num = (EditText)findViewById(R.id.numberCP);
        sendotp = (Button)findViewById(R.id.sendOtpBtn);
        cpp = (CountryCodePicker)findViewById(R.id.CountryCodeSP);
        signinemail = (Button)findViewById(R.id.btnemailSP);
        signup = (TextView)findViewById(R.id.textview7SP);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                number = num.getText().toString().trim();
                String Phonenum = cpp.getSelectedCountryCodeWithPlus()+number;
                Intent b = new Intent(sellerLoginPhone.this,sellerSendOtp.class);

                b.putExtra("Phonenum", Phonenum);
                startActivity(b);
                finish();
            }
        });

        signinemail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(sellerLoginPhone.this,sellerLoginEmail.class));
                finish();
            }
        });

    }

}