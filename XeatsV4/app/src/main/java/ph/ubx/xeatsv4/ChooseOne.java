package ph.ubx.xeatsv4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import ph.ubx.xeatsv4.Customer.customerLoginEmail;
import ph.ubx.xeatsv4.Customer.customerLoginPhone;
import ph.ubx.xeatsv4.Customer.customerRegistration;
import ph.ubx.xeatsv4.Delivery.deliveryLoginEmail;
import ph.ubx.xeatsv4.Delivery.deliveryLoginPhone;
import ph.ubx.xeatsv4.Delivery.deliveryRegistration;
import ph.ubx.xeatsv4.Seller.sellerLoginEmail;
import ph.ubx.xeatsv4.Seller.sellerLoginPhone;
import ph.ubx.xeatsv4.Seller.sellerRegistration;

public class ChooseOne extends AppCompatActivity {

    Button sellerBtn,customerbtn,deliverybtn;
    Intent intent;
    String type;
    ConstraintLayout bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.sellerbg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.customerbg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.deliverybg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.foodbg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.foodbg2), 3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        bgImage = findViewById(R.id.back3);
        bgImage.setBackground(animationDrawable);
        animationDrawable.start();

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        sellerBtn=(Button)findViewById(R.id.seller);
        customerbtn=(Button)findViewById(R.id.customer);
        deliverybtn=(Button)findViewById(R.id.delivery);

        sellerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("Email")) {
                    Intent sLoginemail = new Intent(ChooseOne.this, sellerLoginEmail.class);
                    startActivity(sLoginemail);
                    finish();
                }

                if(type.equals("Phone")) {
                    Intent sLoginphone = new Intent(ChooseOne.this, sellerLoginPhone.class);
                    startActivity(sLoginphone);
                    finish();
                }

                if(type.equals("SignUp")) {
                    Intent sRegister = new Intent(ChooseOne.this, sellerRegistration.class);
                    startActivity(sRegister);
                    finish();
                }
            }
        });

        customerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("Email")) {
                    Intent cLoginemail = new Intent(ChooseOne.this, customerLoginEmail.class);
                    startActivity(cLoginemail);
                    finish();
                }

                if(type.equals("Phone")) {
                    Intent cLoginphone = new Intent(ChooseOne.this, customerLoginPhone.class);
                    startActivity(cLoginphone);
                    finish();
                }

                if(type.equals("SignUp")) {
                    Intent cRegister = new Intent(ChooseOne.this, customerRegistration.class);
                    startActivity(cRegister);
                    finish();
                }
            }
        });

        deliverybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("Email")) {
                    Intent dLoginemail = new Intent(ChooseOne.this, deliveryLoginEmail.class);
                    startActivity(dLoginemail);
                    finish();
                }

                if(type.equals("Phone")) {
                    Intent dLoginphone = new Intent(ChooseOne.this, deliveryLoginPhone.class);
                    startActivity(dLoginphone);
                    finish();
                }

                if(type.equals("SignUp")) {
                    Intent dRegister = new Intent(ChooseOne.this, deliveryRegistration.class);
                    startActivity(dRegister);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}