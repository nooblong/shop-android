package github.nooblong.shop.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import github.nooblong.shop.R;
import github.nooblong.shop.entity.Product;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public boolean useCardView;

    public List<Product> allProduct = new ArrayList<>();

    public MyAdapter(boolean useCardView) {
        this.useCardView = useCardView;
    }

    public void setAllProduct(List<Product> allProduct) {
        this.allProduct = allProduct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_normal, parent, false);
        if (useCardView) {
            itemView = layoutInflater.inflate(R.layout.item_card, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = allProduct.get(position);
        holder.textViewName.setText(String.valueOf(product.getId()));
        holder.textViewName.setText(product.getName());
        holder.textViewDesc.setText(product.getImg());
    }

    @Override
    public int getItemCount() {
        if (allProduct == null)
            return 0;
        return allProduct.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewName, textViewDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewItemId);
            textViewName = itemView.findViewById(R.id.textViewItemName);
            textViewDesc = itemView.findViewById(R.id.textViewItemDesc);
        }
    }

}
