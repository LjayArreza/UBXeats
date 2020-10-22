package ph.ubx.xeatsv4.sellerFoodPanelFragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import ph.ubx.xeatsv4.R;

public class sellerProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Button postDish;
        ConstraintLayout backImg;

        View v = inflater.inflate(R.layout.fragment_seller_post_dish, null);
        getActivity().setTitle("Post Dish");

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.sellerbg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.customerbg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.deliverybg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.foodbg), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.foodbg2), 3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        backImg = v.findViewById(R.id.seller_post_dish_back);
        backImg.setBackground(animationDrawable);
        animationDrawable.start();

        postDish = (Button)v.findViewById(R.id.post_dish_btn);

        postDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), seller_postdish.class));
            }
        });

        return v;
    }
}
