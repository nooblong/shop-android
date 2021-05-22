package github.nooblong.shop.ui.order;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import github.nooblong.shop.R;
import github.nooblong.shop.entity.Order;
import github.nooblong.shop.ui.home.HomeFragment;
import github.nooblong.shop.ui.login.LoginFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderFragment extends Fragment {

    private OrderViewModel orderViewModel;
    private TextView name, room, tele, remark;
    private Button submit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_order, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        orderViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        name = root.findViewById(R.id.orderName);
        room = root.findViewById(R.id.orderRoom);
        tele = root.findViewById(R.id.orderTele);
        remark = root.findViewById(R.id.orderRemark);
        submit = root.findViewById(R.id.orderSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Order order = new Order(name.getText().toString(),
                        room.getText().toString(),
                        tele.getText().toString(),
                        remark.getText().toString(),
                        HomeFragment.toBuy);
                String json = gson.toJson(order);
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .connectTimeout(10000, TimeUnit.MILLISECONDS)
                        .build();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
                final Request request = new Request.Builder()
                        .post(requestBody)
                        .addHeader("cookie", LoginFragment.session)
                        .url(LoginFragment.BASE_URL + "/api/order/add")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("order", "fail");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("order", "success");
                    }
                });
            }
        });

        return root;
    }
}