package ph.ubx.xeatsv4.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ph.ubx.xeatsv4.R;
import ph.ubx.xeatsv4.customerFoodPanelFragment.CustomerCartFragment;
import ph.ubx.xeatsv4.customerFoodPanelFragment.CustomerHomeFragment;
import ph.ubx.xeatsv4.customerFoodPanelFragment.CustomerOrdersFragment;
import ph.ubx.xeatsv4.customerFoodPanelFragment.CustomerProfileFragment;
import ph.ubx.xeatsv4.customerFoodPanelFragment.CustomerTrackFragment;

public class customerDashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);
        BottomNavigationView navigationView = findViewById(R.id.customer_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.customer_home:
                fragment = new CustomerHomeFragment();
                break;
        }

        switch (item.getItemId()) {
            case R.id.customer_cart:
                fragment = new CustomerCartFragment();
                break;
        }

        switch (item.getItemId()) {
            case R.id.customer_orders:
                fragment = new CustomerOrdersFragment();
                break;
        }

        switch (item.getItemId()) {
            case R.id.customer_track:
                fragment = new CustomerTrackFragment();
                break;
        }

        switch (item.getItemId()) {
            case R.id.customer_profile:
                fragment = new CustomerProfileFragment();
                break;
        }

        return loadCustomerFragment(fragment);
    }

    private boolean loadCustomerFragment(Fragment fragment) {

        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.customer_fragment_container, fragment).commit();
            return true;
        }

        return false;
    }
}