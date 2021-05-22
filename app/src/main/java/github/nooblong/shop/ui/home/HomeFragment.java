package github.nooblong.shop.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import github.nooblong.shop.MainActivity;
import github.nooblong.shop.R;
import github.nooblong.shop.entity.Product;
import github.nooblong.shop.ui.login.LoginFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public RecyclerView recyclerView;
    public MyAdapter myAdapter1, myAdapter2;
    public static Map<Integer, Integer> toBuy = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.setHomeFragment(this);
//        loginFragment = (LoginFragment) getActivity().getSupportFragmentManager().findFragmentByTag("login");


        recyclerView = root.findViewById(R.id.home_recycler_view);
        myAdapter1 = new MyAdapter(false);
        myAdapter2 = new MyAdapter(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(myAdapter1);
        myAdapter1.setAllProduct(new ArrayList<>(Arrays.asList(
                new Product())));
        myAdapter2.allProduct = myAdapter1.allProduct;

        if (LoginFragment.home_config == 0){
            recyclerView.setAdapter(myAdapter2);
        } else if (LoginFragment.home_config == 1)
            recyclerView.setAdapter(myAdapter1);

        homeViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                myAdapter1.setAllProduct(products);
                myAdapter1.notifyDataSetChanged();
                myAdapter2.allProduct = myAdapter1.allProduct;
                myAdapter2.notifyDataSetChanged();
            }
        });

        return root;
    }

}