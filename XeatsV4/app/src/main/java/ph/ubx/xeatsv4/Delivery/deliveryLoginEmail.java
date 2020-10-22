package ph.ubx.xeatsv4.Delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ph.ubx.xeatsv4.Customer.customerDashboard;
import ph.ubx.xeatsv4.Customer.customerForgotPassword;
import ph.ubx.xeatsv4.Customer.customerLoginEmail;
import ph.ubx.xeatsv4.Customer.customerLoginPhone;
import ph.ubx.xeatsv4.Customer.customerRegistration;
import ph.ubx.xeatsv4.R;
import ph.ubx.xeatsv4.Utils.ReusableCodes;

public class deliveryLoginEmail extends AppCompatActivity {

    TextInputLayout email,pass;
    Button loginBtn,signInPhoneBtn;
    TextView forgotPassword , newCustomerSignUp;
    FirebaseAuth FAuth;
    String emailId,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login_email);

        try {

            email = (TextInputLayout)findViewById(R.id.deliveryLeEmailTextField);
            pass = (TextInputLayout)findViewById(R.id.deliveryLePasswordTextField);
            signInPhoneBtn = (Button)findViewById(R.id.deliveryLeSignInPhone);
            loginBtn = (Button)findViewById(R.id.deliveryLeLoginButton);
            newCustomerSignUp = (TextView)findViewById(R.id.deliveryLeSignUpText);
            forgotPassword = (TextView)findViewById(R.id.deliveryLeForgotPassword);


            FAuth = FirebaseAuth.getInstance();

            loginBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    emailId = email.getEditText().getText().toString().trim();
                    password = pass.getEditText().getText().toString().trim();

                    if(isValid()) {

                        final ProgressDialog mDialog = new ProgressDialog(deliveryLoginEmail.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Please wait...");
                        mDialog.show();

                        FAuth.signInWithEmailAndPassword(emailId,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    mDialog.dismiss();

                                    if(FAuth.getCurrentUser().isEmailVerified()) {
                                        mDialog.dismiss();
                                        Toast.makeText(deliveryLoginEmail.this, "You have login Succesfully", Toast.LENGTH_LONG).show();
                                        Intent z = new Intent(deliveryLoginEmail.this, deliveryDashboard.class);
                                        startActivity(z);
                                        finish();

                                    } else  {

                                        ReusableCodes.ShowAlert(deliveryLoginEmail.this, "Verification Failed", "Please verify your email");

                                    }
                                } else {
                                    mDialog.dismiss();
                                    ReusableCodes.ShowAlert(deliveryLoginEmail.this, "Error",task.getException().getMessage());
                                }
                            }
                        });
                    }

                }
            });

            newCustomerSignUp.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(deliveryLoginEmail.this, deliveryRegistration.class));
                    finish();
                }
            });

            forgotPassword.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(deliveryLoginEmail.this, deliveryForgotPassword.class));
                    finish();
                }
            });

            signInPhoneBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(deliveryLoginEmail.this, deliveryLoginPhone.class));
                    finish();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid(){
        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalid=false,isvalidemail=false,isvalidpassword=false;

        if(TextUtils.isEmpty(emailId)) {
            email.setErrorEnabled(true);
            email.setError("Email is required");
        } else  {
            if(emailId.matches(emailpattern)) {
                isvalidemail = true;
            } else {
                email.setErrorEnabled(true);
                email.setError("Invalid email address");
            }
        }

        if(TextUtils.isEmpty(password)) {

            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        } else {
            isvalidpassword = true;
        }

        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;
    }
}