package ph.ubx.xeatsv4.customerFoodPanelFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import ph.ubx.xeatsv4.Model.UpdateDishModel;
import ph.ubx.xeatsv4.R;

public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder> {

    private Context mContext;
    private List<UpdateDishModel>updateDishModellist;
    DatabaseReference databaseReference;

    public CustomerHomeAdapter(Context context, List<UpdateDishModel>updateDishModelslist) {

        this.updateDishModellist = updateDishModelslist;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CustomerHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.customer_menu_dish, parent, false);
        return new CustomerHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHomeAdapter.ViewHolder holder, int position) {

        final UpdateDishModel updateDishModel = updateDishModellist.get(position);
        Glide.with(mContext).load(updateDishModel.getImageURL()).into(holder.imageView);
        holder.dishName.setText(updateDishModel.getPrice());
        updateDishModel.getRandomUID();
        updateDishModel.getSellerId();
        holder.price.setText("Price"+updateDishModel.getPrice()+"Php");


    }

    @Override
    public int getItemCount() {
        return updateDishModellist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView dishName, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.customer_menu_image);
            dishName = itemView.findViewById(R.id.customer_dish_name);
            price = itemView.findViewById(R.id.customer_dish_price);

        }
    }
}
