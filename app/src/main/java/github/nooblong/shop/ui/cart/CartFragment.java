package github.nooblong.shop.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.nooblong.shop.R;
import github.nooblong.shop.entity.OrderDetail;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;

    public OrderAdapter orderAdapter;
    public RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        cartViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        recyclerView = root.findViewById(R.id.orderRecycleView);
        orderAdapter = new OrderAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(orderAdapter);
//        orderAdapter.setOrderDetails(new ArrayList<>(Arrays.asList(new OrderDetail())));

        cartViewModel.getOrderDetailsLiveData().observe(this, new Observer<List<OrderDetail>>() {
            @Override
            public void onChanged(List<OrderDetail> orderDetails) {
                orderAdapter.setOrderDetails(orderDetails);
                orderAdapter.notifyDataSetChanged();
            }
        });

        return root;
    }
}