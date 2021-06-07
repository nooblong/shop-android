package github.nooblong.shop.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import github.nooblong.shop.R;
import github.nooblong.shop.ui.home.HomeFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;

public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;

    public static final String BASE_URL = "http://10.0.2.2:5000";
    public static final String TAG = "TEST";
    Switch aSwitch;
    Button loginButton;
    TextView username, password;
    public static int home_config = 0;

    public static String session = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        root.findViewById(R.id.button_test).setOnClickListener( (view) -> {
            get();
        });

        aSwitch = root.findViewById(R.id.home_switch);
        aSwitch.setChecked(home_config == 0);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    home_config = 0;
                else
                    home_config = 1;
            }
        });

        loginButton = root.findViewById(R.id.buttonLogin);
        username = root.findViewById(R.id.editTextUsername);
        password = root.findViewById(R.id.editTextPassword);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .connectTimeout(10000, TimeUnit.MILLISECONDS)
                        .build();
                RequestBody formBody = new FormBody.Builder()
                        .add("username", username.getText().toString())
                        .add("password", password.getText().toString())
                        .build();
                final Request request = new Request.Builder()
                        .post(formBody)
                        .url(BASE_URL + "/user/login")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("Login", "登录失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("Login", response.body().string());
                        Headers headers = response.headers();
                        Log.d("Login", "header " + headers);
                        List<String> cookies = headers.values("Set-Cookie");
                        String s = cookies.get(0);
                        Log.d("Login", "onResponse-size: " + cookies);
                        LoginFragment.session = s.substring(0, s.indexOf(";"));
                        Log.i("Login", "session is  :" + LoginFragment.session);

                    }
                });
            }
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
