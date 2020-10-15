package ph.ubx.xeatsv4;

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
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class sellerSendOtp extends AppCompatActivity {

    String verificationId;
    FirebaseAuth FAuth;
    Button verify , resend;
    TextView txt;
    EditText enterCode;
    String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_send_otp);

        phonenum = getIntent().getStringExtra("Phonenum").trim();

        enterCode = (EditText)findViewById(R.id.otpField);
        txt = (TextView)findViewById(R.id.textOtp);
        resend = (Button)findViewById(R.id.resendOtpBtn);
        verify = (Button)findViewById(R.id.verifyOtpBtn);
        FAuth = FirebaseAuth.getInstance();

        resend.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

        sendVerificationCode(phonenum);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = enterCode.getText().toString().trim();
                resend.setVisibility(View.INVISIBLE);

                if (code.isEmpty() && code.length()<6){
                    enterCode.setError("Enter code");
                    enterCode.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {

                txt.setVisibility(View.VISIBLE);
                txt.setText("Resend Code Within "+l/1000+" Seconds");

            }

            @Override
            public void onFinish() {
                resend.setVisibility(View.VISIBLE);
                txt.setVisibility(View.INVISIBLE);

            }
        }.start();

        resend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                resend.setVisibility(View.INVISIBLE);
                Resendotp(phonenum);

                new CountDownTimer(60000, 1000) {

                    @Override
                    public void onTick(long l) {

                        txt.setVisibility(View.VISIBLE);
                        txt.setText("Resend Code Within"+l/1000+"Seconds");

                    }

                    @Override
                    public void onFinish() {

                        resend.setVisibility(View.VISIBLE);
                        txt.setVisibility(View.INVISIBLE);

                    }
                }.start();

            }
        });
    }

    private void Resendotp(String phonenum) {

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

            Toast.makeText(sellerSendOtp.this, e.getMessage(),Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s,forceResendingToken);

            verificationId = s;

        }

    };

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhone(credential);

    }

    private void signInWithPhone(PhoneAuthCredential credentials) {

        FAuth.signInWithCredential(credentials)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            startActivity(new Intent(sellerSendOtp.this,sellerDashboard.class));
                        } else {
                            ReusableCodes.ShowAlert(sellerSendOtp.this, "Error",task.getException().getMessage());
                        }
                    }
                });

    }
}