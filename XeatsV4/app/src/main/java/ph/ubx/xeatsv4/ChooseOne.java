package ph.ubx.xeatsv4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseOne extends AppCompatActivity {

    Button sellerBtn,customerbtn,deliverybtn;
    ImageView foodbgImage;
    Intent intent;
    String type;
    ConstraintLayout bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        final Animation zoomin = AnimationUtils.loadAnimation(this,R.anim.zoomin);
        final Animation zoomout = AnimationUtils.loadAnimation(this,R.anim.zoomout);

        foodbgImage=findViewById(R.id.back4);
        foodbgImage.setAnimation(zoomin);
        foodbgImage.setAnimation(zoomout);

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                foodbgImage.startAnimation(zoomin);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                foodbgImage.startAnimation(zoomout);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        sellerBtn=(Button)findViewById(R.id.seller);
        customerbtn=(Button)findViewById(R.id.customer);
        deliverybtn=(Button)findViewById(R.id.delivery);

        sellerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("Email")) {
                    Intent sLoginemail = new Intent(ChooseOne.this,sellerLoginEmail.class);
                    startActivity(sLoginemail);
                    finish();
                }

                if(type.equals("Phone")) {
                    Intent sLoginphone = new Intent(ChooseOne.this,sellerLoginPhone.class);
                    startActivity(sLoginphone);
                    finish();
                }

                if(type.equals("SignUp")) {
                    Intent sRegister = new Intent(ChooseOne.this,sellerRegistration.class);
                    startActivity(sRegister);
                    finish();
                }
            }
        });

        customerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("Email")) {
                    Intent cLoginemail = new Intent(ChooseOne.this,customerLoginEmail.class);
                    startActivity(cLoginemail);
                    finish();
                }

                if(type.equals("Phone")) {
                    Intent cLoginphone = new Intent(ChooseOne.this,customerLoginPhone.class);
                    startActivity(cLoginphone);
                    finish();
                }

                if(type.equals("SignUp")) {
                    Intent cRegister = new Intent(ChooseOne.this,customerRegistration.class);
                    startActivity(cRegister);
                    finish();
                }
            }
        });

        deliverybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("Email")) {
                    Intent dLoginemail = new Intent(ChooseOne.this,deliveryLoginEmail.class);
                    startActivity(dLoginemail);
                    finish();
                }

                if(type.equals("Phone")) {
                    Intent dLoginphone = new Intent(ChooseOne.this,deliveryLoginPhone.class);
                    startActivity(dLoginphone);
                    finish();
                }

                if(type.equals("SignUp")) {
                    Intent dRegister = new Intent(ChooseOne.this,deliveryRegistration.class);
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