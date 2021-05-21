package github.nooblong.shop.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import github.nooblong.shop.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;

    public static final String BASE_URL = "http://192.168.1.178:5000";
    public static final String TAG = "TEST";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        root.findViewById(R.id.button_test).setOnClickListener( (view) -> {
            get();
        });

        return root;
    }

    public void get() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();

        final Request request = new Request.Builder()
                .get()
                .url(BASE_URL + "/api/goods")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "fail->", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                ResponseBody body = response.body();
                Log.d(TAG, "code->" + code);
                Log.d(TAG, "body->" + body.string());
            }
        });
    }
}
