package ph.ubx.xeatsv4.Delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

import ph.ubx.xeatsv4.Customer.customerLoginEmail;
import ph.ubx.xeatsv4.Customer.customerLoginPhone;
import ph.ubx.xeatsv4.Customer.customerRegistration;
import ph.ubx.xeatsv4.Customer.customerVerifyPhone;
import ph.ubx.xeatsv4.R;
import ph.ubx.xeatsv4.Utils.ReusableCodes;

public class deliveryRegistration extends AppCompatActivity {

    String[] MetroManila = {"Quezon City", "Mandaluyong City", "Pasig City", "Makati City", "Pasay City"};
    String[] Cavite = {"Bacoor", "Dasma", "Imus"};

    TextInputLayout Fname,Lname,Email,Pass,Cpass,Mobile,LocalAddress,Subd,ZipCode;
    Spinner Statepin,Citypin;
    Button signup, email, phone;
    CountryCodePicker Ccp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname,lname,emailid,password,confpassword,mobile,localAddress,subd,zipCode,state,city;
    String role= "Delivery";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_registration);

        Fname = (TextInputLayout)findViewById(R.id.deliveryRegFirstName);
        Lname = (TextInputLayout)findViewById(R.id.deliveryRegLastName);
        Email = (TextInputLayout)findViewById(R.id.deliveryRegEmail);
        Pass = (TextInputLayout)findViewById(R.id.deliveryRegPassword);
        Cpass = (TextInputLayout)findViewById(R.id.deliveryRegConfirmPassword);
        Mobile = (TextInputLayout)findViewById(R.id.deliveryRegMobileNumber);
        LocalAddress = (TextInputLayout)findViewById(R.id.deliveryRegLocalAddress);
        Subd = (TextInputLayout)findViewById(R.id.deliveryRegSubd);
        ZipCode = (TextInputLayout)findViewById(R.id.deliveryRegZipCode);
        Statepin = (Spinner)findViewById(R.id.deliveryRegProvince);
        Citypin = (Spinner)findViewById(R.id.deliveryRegCity);

        signup = (Button)findViewById(R.id.deliveryRegSignUpButton);
        email = (Button)findViewById(R.id.deliveryRegSignUpWithEmail);
        phone = (Button)findViewById(R.id.deliveryRegSignUpWithPhone);

        Ccp = (CountryCodePicker)findViewById(R.id.deliveryRegCcp);

        Statepin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Object value = adapterView.getItemAtPosition(i);
                state = value.toString().trim();

                // province - city
                if(state.equals("MetroManila")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : MetroManila) {
                        list.add(cities);
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(deliveryRegistration.this,android.R.layout.simple_spinner_dropdown_item,list);
                    Citypin.setAdapter(arrayAdapter);
                }

                if(state.equals("Cavite")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Cavite) {
                        list.add(cities);
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(deliveryRegistration.this,android.R.layout.simple_spinner_dropdown_item,list);
                    Citypin.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Citypin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object value = adapterView.getItemAtPosition(i);
                city = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        databaseReference = firebaseDatabase.getInstance().getReference("Delivery");
        FAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = Fname.getEditText().getText().toString().trim();
                lname = Lname.getEditText().getText().toString().trim();
                emailid = Email.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                confpassword = Cpass.getEditText().getText().toString().trim();
                mobile = Mobile.getEditText().getText().toString().trim();
                localAddress = LocalAddress.getEditText().getText().toString().trim();
                subd = Subd.getEditText().getText().toString().trim();
                zipCode = ZipCode.getEditText().getText().toString().trim();

                if (isValid()){
                    final ProgressDialog mDialog = new ProgressDialog(deliveryRegistration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait...");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("Role", role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        HashMap<String, String> hashMap1 = new HashMap<>();
                                        hashMap1.put("First Name", fname);
                                        hashMap1.put("Last Name", lname);
                                        hashMap1.put("Mobile No", mobile);
                                        hashMap1.put("EmailId", emailid);
                                        hashMap1.put("City", city);
                                        hashMap1.put("Password", password);
                                        hashMap1.put("State", state);
                                        hashMap1.put("Confirm Password", confpassword);
                                        hashMap1.put("Local Address", localAddress);
                                        hashMap1.put("Subd", subd);
                                        hashMap1.put("Zip Code", zipCode);

                                        firebaseDatabase.getInstance().getReference("Delivery")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mDialog.dismiss();

                                                FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if(task.isSuccessful()) {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(deliveryRegistration.this);
                                                            builder.setMessage("You have registered to UBXeats! Please verify your email");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                                    dialogInterface.dismiss();

                                                                    String  phoneNumber = Ccp.getSelectedCountryCodeWithPlus() + mobile;
                                                                    Intent b = new Intent(deliveryRegistration.this, deliveryVerifyPhone.class);
                                                                    b.putExtra("phonenumber", phoneNumber);
                                                                    startActivity(b);

                                                                }
                                                            });

                                                            AlertDialog Alert = builder.create();
                                                            Alert.show();
                                                        } else {
                                                            mDialog.dismiss();
                                                            ReusableCodes.ShowAlert(deliveryRegistration.this,"Error",task.getException().getMessage());
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            } else  {
                                mDialog.dismiss();
                                ReusableCodes.ShowAlert(deliveryRegistration.this,"Error",task.getException().getMessage());
                            }

                        }
                    });
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(deliveryRegistration.this, deliveryLoginEmail.class));
                finish();
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(deliveryRegistration.this, deliveryLoginPhone.class));
                finish();
            }
        });
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        Cpass.setErrorEnabled(false);
        Cpass.setError("");
        Mobile.setErrorEnabled(false);
        Mobile.setError("");
        ZipCode.setErrorEnabled(false);
        ZipCode.setError("");
        LocalAddress.setErrorEnabled(false);
        LocalAddress.setError("");
        Subd.setErrorEnabled(false);
        Subd.setError("");

        boolean isValid=false,
                isValidLname=false,
                isValidFname=false,
                isValidEmail=false,
                isValidPassword=false,
                isValidConfPassword=false,
                isValidMobileNum=false,
                isValidZipCode=false,
                isValidLocalAddress=false,
                isValidSubd=false;

        if(TextUtils.isEmpty(fname)) {
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        } else {
            isValidFname = true;
        }

        if(TextUtils.isEmpty(lname)) {
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        } else {
            isValidLname = true;
        }

        if(TextUtils.isEmpty(emailid)) {
            Email.setErrorEnabled(true);
            Email.setError("Enter Email");
        } else {
            if (emailid.matches(emailpattern)) {
                isValidEmail = true;
            } else {
                Email.setErrorEnabled(true);
                Email.setError("Enter a valid Email Id");
            }
        }

        if(TextUtils.isEmpty(password)) {
            Pass.setErrorEnabled(true);
            Pass.setError("Enter Password");
        } else {
            if (password.length()<8) {
                Pass.setErrorEnabled(true);
                Pass.setError("Password is Weak");
            } else {
                isValidPassword = true;
            }
        }

        if(TextUtils.isEmpty(confpassword)) {
            Cpass.setErrorEnabled(true);
            Cpass.setError("Confirm Password");
        } else {
            if (!password.equals(confpassword)) {
                Cpass.setErrorEnabled(true);
                Cpass.setError("Password Not Match");
            } else {
                isValidConfPassword = true;
            }
        }

        if(TextUtils.isEmpty(mobile)) {
            Mobile.setErrorEnabled(true);
            Mobile.setError("Enter Mobile");
        } else {
            if(mobile.length()<10) {
                Mobile.setErrorEnabled(true);
                Mobile.setError("Invalid Mobile Number");
            } else {
                isValidMobileNum = true;
            }
        }

        if(TextUtils.isEmpty(localAddress)) {
            LocalAddress.setErrorEnabled(true);
            LocalAddress.setError("Enter House Number/Street");
        } else {
            isValidLocalAddress = true;
        }

        if(TextUtils.isEmpty(subd)) {
            Subd.setErrorEnabled(true);
            Subd.setError("Enter Subdivision/Compound");
        } else {
            isValidSubd = true;
        }

        if(TextUtils.isEmpty(zipCode)) {
            ZipCode.setErrorEnabled(true);
            ZipCode.setError("Enter Zip-Code");
        } else {
            isValidZipCode = true;
        }

        isValid = (isValidLname && isValidEmail && isValidPassword && isValidConfPassword && isValidLocalAddress && isValidMobileNum && isValidSubd && isValidZipCode && isValidFname) ? true : false;
        return isValid;
    }
}