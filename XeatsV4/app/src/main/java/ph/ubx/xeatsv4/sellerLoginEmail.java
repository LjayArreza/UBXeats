package ph.ubx.xeatsv4;

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

public class sellerLoginEmail extends AppCompatActivity {

    TextInputLayout email,pass;
    Button Signin,SigninPhone;
    TextView Forgotpassword , signup;
    FirebaseAuth Fauth;
    String emailid,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login_email);

        try{

            email = (TextInputLayout)findViewById(R.id.sellerLoginEmail);
            pass = (TextInputLayout)findViewById(R.id.sellerLoginPassword);
            Signin = (Button)findViewById(R.id.sellerLoginBtn);
            signup = (TextView)findViewById(R.id.textview6);
            Forgotpassword = (TextView)findViewById(R.id.forgotPasswordSeller);
            SigninPhone = (Button)findViewById(R.id.btnphone);

            Fauth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if(isValid()) {

                        final ProgressDialog mDialog = new ProgressDialog(sellerLoginEmail.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Please wait...");
                        mDialog.show();

                        Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    mDialog.dismiss();

                                    if(Fauth.getCurrentUser().isEmailVerified()) {
                                        mDialog.dismiss();
                                        Toast.makeText(sellerLoginEmail.this, "You have login Succesfully", Toast.LENGTH_LONG).show();
                                        Intent s = new Intent(sellerLoginEmail.this,sellerDashboard.class);
                                        startActivity(s);
                                        finish();

                                    } else  {

                                        ReusableCodes.ShowAlert(sellerLoginEmail.this, "Verification Failed", "Please verify your email");

                                    }
                                } else {
                                    mDialog.dismiss();
                                    ReusableCodes.ShowAlert(sellerLoginEmail.this, "Error",task.getException().getMessage());
                                }
                            }
                        });
                    }

                }
            });
            signup.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(sellerLoginEmail.this,sellerRegistration.class));
                    finish();
                }
            });

            Forgotpassword.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(sellerLoginEmail.this,sellerForgotPassword.class));
                    finish();
                }
            });

            SigninPhone.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(sellerLoginEmail.this,sellerLoginPhone.class));
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

        if(TextUtils.isEmpty(emailid)) {
            email.setErrorEnabled(true);
            email.setError("Email is required");
        } else  {
            if(emailid.matches(emailpattern)) {
                isvalidemail = true;
            } else {
                email.setErrorEnabled(true);
                email.setError("Invalid email address");
            }
        }

        if(TextUtils.isEmpty(pwd)) {

            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        } else {
            isvalidpassword = true;
        }

        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;
    }
}