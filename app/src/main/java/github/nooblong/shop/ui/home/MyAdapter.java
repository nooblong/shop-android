package github.nooblong.shop.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import github.nooblong.shop.R;
import github.nooblong.shop.entity.Product;
import github.nooblong.shop.util.LoadImageTask;

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
        holder.textViewId.setText(String.valueOf(product.getId()));
        holder.textViewName.setText(product.getName());
        holder.textViewDesc.setText("库存: " + product.getStock());
        new LoadImageTask(holder.imageView).execute(product.getImg());

        if (HomeFragment.toBuy.containsKey(Integer.valueOf(holder.textViewId.getText().toString())))
            holder.itemNum.setText(String.valueOf(HomeFragment.toBuy.get(Integer.valueOf(holder.textViewId.getText().toString()))));

    }

    @Override
    public int getItemCount() {
        if (allProduct == null)
            return 0;
        return allProduct.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewName, textViewDesc;
        ImageView imageView;
        ImageButton add, remove;
        TextView itemNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewItemId);
            textViewName = itemView.findViewById(R.id.textViewItemName);
            textViewDesc = itemView.findViewById(R.id.textViewItemDesc);
            imageView = itemView.findViewById(R.id.imageViewItem);
            add = itemView.findViewById(R.id.imageButtonItemAdd);
            remove = itemView.findViewById(R.id.imageButtonItemRemove);
            itemNum = itemView.findViewById(R.id.textViewItemNum);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer before = Integer.valueOf(itemNum.getText().toString());
//                    Log.d("tmp", itemNum.getText().toString());
                    itemNum.setText(String.valueOf(before + 1));
                    Map<Integer, Integer> toBuy = HomeFragment.toBuy;
                    if (!toBuy.containsKey(Integer.valueOf(textViewId.getText().toString()))) {
                        toBuy.put(Integer.valueOf(textViewId.getText().toString()), 1);
                    } else {
                        toBuy.put(Integer.valueOf(textViewId.getText().toString()),
                                toBuy.get(Integer.valueOf(textViewId.getText().toString())) + 1);
                    }
                    Log.d("tmp", HomeFragment.toBuy.toString());
                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer before = Integer.valueOf(itemNum.getText().toString());
//                    Log.d("tmp", itemNum.getText().toString());
                    itemNum.setText(String.valueOf(before - 1));
                    Map<Integer, Integer> toBuy = HomeFragment.toBuy;
                    if (!toBuy.containsKey(Integer.valueOf(textViewId.getText().toString()))) {
                        return;
                    } else {
                        if (toBuy.get(Integer.valueOf(textViewId.getText().toString())) >= 1) {
                            toBuy.put(Integer.valueOf(textViewId.getText().toString()),
                                    toBuy.get(Integer.valueOf(textViewId.getText().toString())) - 1);
                        }
                    }
                    Log.d("tmp", HomeFragment.toBuy.toString());
                }
            });
        }
    }

}
