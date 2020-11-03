package ph.ubx.xeatsv4.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (name != null) {
            if (name.equalsIgnoreCase("HomePage")) {
                loadFragment(new CustomerHomeFragment());
            } else if (name.equalsIgnoreCase("TrackingPage")) {
                loadFragment(new CustomerTrackFragment());
            } else if (name.equalsIgnoreCase("OrderPage")) {
                loadFragment(new CustomerOrdersFragment());
            } else if (name.equalsIgnoreCase("ThankYouPage")) {
                loadFragment(new CustomerHomeFragment());
            } else {
                loadFragment(new CustomerHomeFragment());
            }
        }
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

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.customer_fragment_container, fragment).commit();
            return true;
        } return false;
    }
}