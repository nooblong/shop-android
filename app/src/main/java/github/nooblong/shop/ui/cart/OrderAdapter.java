package github.nooblong.shop.ui.cart;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import github.nooblong.shop.entity.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    List<Order> orders;

    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder{

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
