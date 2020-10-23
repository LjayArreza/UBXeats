package ph.ubx.xeatsv4.Delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ph.ubx.xeatsv4.R;
import ph.ubx.xeatsv4.customerFoodPanelFragment.CustomerCartFragment;
import ph.ubx.xeatsv4.customerFoodPanelFragment.CustomerHomeFragment;
import ph.ubx.xeatsv4.deliveryFoodPanelFragment.DeliveryPendingFragment;
import ph.ubx.xeatsv4.deliveryFoodPanelFragment.DeliveryShippedFragment;

public class deliveryDashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_dashboard);

        BottomNavigationView navigationView = findViewById(R.id.delivery_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.delivery_pending_orders:
                fragment = new DeliveryPendingFragment();
                break;
        }

        switch (item.getItemId()) {
            case R.id.delivery_ship_orders:
                fragment = new DeliveryShippedFragment();
                break;
        }

        return loadDeliveryFragment(fragment);
    }


    private boolean loadDeliveryFragment(Fragment fragment) {

        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.delivery_fragment_container, fragment).commit();
            return true;
        }

        return false;
    }
}