package ph.ubx.xeatsv4.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import ph.ubx.xeatsv4.MainMenu;
import ph.ubx.xeatsv4.R;
import ph.ubx.xeatsv4.Utils.ReusableCodes;

public class customerVerifyPhone extends AppCompatActivity {

    String verificationId;
    FirebaseAuth FAuth;
    Button verify , resend;
    TextView txt;
    EditText enterCode;
    String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_verify_phone);

        phonenum = getIntent().getStringExtra("phonenumber").trim();

        enterCode = (EditText)findViewById(R.id.customervpOtp);
        txt = (TextView)findViewById(R.id.customervpText);
        resend = (Button)findViewById(R.id.customervpResendBtn);
        verify = (Button)findViewById(R.id.customervpBtn);
        FAuth = FirebaseAuth.getInstance();

        resend.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

        sendVerificationCode(phonenum);

        verify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String code = enterCode.getText().toString().trim();
                resend.setVisibility(View.INVISIBLE);

                if (code.isEmpty() && code.length() <6 ) {
                    enterCode.setError("Enter Code");
                    enterCode.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

        new CountDownTimer(60000, 1000){

            @Override
            public void onTick(long l) {

                txt.setVisibility(View.VISIBLE);
                txt.setText("Resend code within" +l/1000+ "Seconds");

            }

            @Override
            public void onFinish() {

                resend.setVisibility(View.VISIBLE);
                txt.setVisibility(View.INVISIBLE);

            }
        }.start();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resend.setVisibility(View.INVISIBLE);
                ResendOtp(phonenum);

                new CountDownTimer(60000,1000){

                    @Override
                    public void onTick(long l) {

                        txt.setVisibility(View.VISIBLE);
                        txt.setText("Resend Code Within"+l/1000+"Seconds");

                    }

                    public void onFinish() {
                        resend.setVisibility(View.VISIBLE);
                        txt.setVisibility(View.INVISIBLE);

                    }
                }.start();
            }
        });
    }

    private void ResendOtp(String phonenum) {

        sendVerificationCode(phonenum);
    }

    private void sendVerificationCode(String number) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(

                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mcallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                enterCode.setText(code);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(customerVerifyPhone.this, e.getMessage(),Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s,forceResendingToken);

            verificationId = s;

        }

    };

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        linkCredentials(credential);

    }

    private void linkCredentials(PhoneAuthCredential credentials) {

        FAuth.getCurrentUser().linkWithCredential(credentials)
                .addOnCompleteListener(customerVerifyPhone.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intent = new Intent(customerVerifyPhone.this, MainMenu.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ReusableCodes.ShowAlert(customerVerifyPhone.this, "Error",task.getException().getMessage());
                        }
                    }
                });

    }
}