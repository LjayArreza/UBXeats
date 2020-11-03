package ph.ubx.xeatsv4.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ph.ubx.xeatsv4.R;
import ph.ubx.xeatsv4.sellerFoodPanelFragment.sellerHomeFragment;
import ph.ubx.xeatsv4.sellerFoodPanelFragment.sellerOrderFragment;
import ph.ubx.xeatsv4.sellerFoodPanelFragment.sellerPendingOrdersFragment;
import ph.ubx.xeatsv4.sellerFoodPanelFragment.sellerProfileFragment;

public class sellerDashboard extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        BottomNavigationView navigationView = findViewById(R.id.seller_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        if (name != null) {
            if (name.equalsIgnoreCase("OrderPage")) {
                loadSellerFragment(new sellerPendingOrdersFragment());
            } else if (name.equalsIgnoreCase("ConfirmPage")) {
                loadSellerFragment(new sellerOrderFragment());
            } else if (name.equalsIgnoreCase("AcceptOrderPage")) {
                loadSellerFragment(new sellerOrderFragment());
            } else if (name.equalsIgnoreCase("DeliveredPage")) {
                loadSellerFragment(new sellerOrderFragment());
            }
        } else {
            loadSellerFragment(new sellerHomeFragment());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.sellerHome:
                fragment=new sellerHomeFragment();
                break;
            case R.id.sellerPendingOrders:
                fragment=new sellerPendingOrdersFragment();
                break;
            case R.id.sellerOrders:
                fragment=new sellerOrderFragment();
                break;
            case R.id.sellerPostDish:
                fragment=new sellerProfileFragment();
                break;

        }

        return loadSellerFragment(fragment);
    }

    private boolean loadSellerFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.seller_fragment_container,fragment).commit();
            return true;
        }

        return false;
    }
}