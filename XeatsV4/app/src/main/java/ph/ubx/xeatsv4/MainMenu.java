package ph.ubx.xeatsv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainMenu extends AppCompatActivity {

    Button signinemail, signinphone, signup;
    ImageView bgimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Log.d("ubxEats_Token","" + FirebaseInstanceId.getInstance().getToken());
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");

        //cTElzd1kTwmWhfNC6E39qn:APA91bEgjKH0e2BSzXMJFt_gZ-3c3OobKkDAkLVQ63sOjVLQOh2IkOIumD6Yyn5mQ-Dq6oorEqJYvmXlXF53zaP4u-BVtoMM7aKkoxABxWDPiJO0x0Ld9HhnyTMoMBWPj6k4VHeVm3sF


        final Animation zoomin = AnimationUtils.loadAnimation(this,R.anim.zoomin);
        final Animation zoomout = AnimationUtils.loadAnimation(this,R.anim.zoomout);

        bgimage=findViewById(R.id.back2);
        bgimage.setAnimation(zoomin);
        bgimage.setAnimation(zoomout);

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage.startAnimation(zoomin);

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
                bgimage.startAnimation(zoomout);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        signinemail=(Button)findViewById(R.id.SignwithEmail);
        signinphone=(Button)findViewById(R.id.SigninWithPhone);
        signup=(Button)findViewById(R.id.SignUpBtn);

        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signemail = new Intent(MainMenu.this,ChooseOne.class);
                signemail.putExtra("Home","Email");
                startActivity(signemail);
                finish();
            }
        });

        signinphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signphone = new Intent(MainMenu.this,ChooseOne.class);
                signphone.putExtra("Home","Phone");
                startActivity(signphone);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(MainMenu.this,ChooseOne.class);
                signup.putExtra("Home","SignUp");
                startActivity(signup);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}