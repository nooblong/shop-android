package github.nooblong.shop.ui.home;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import github.nooblong.shop.MainActivity;
import github.nooblong.shop.entity.Product;
import github.nooblong.shop.ui.login.LoginFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();
    private HomeFragment homeFragment;
    private static final String TAG = "Home";

    public void setHomeFragment(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public HomeViewModel(@NonNull Application application) {
        super(application);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        final Request request = new Request.Builder()
                .get()
                .url(LoginFragment.BASE_URL + "/api/goods")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(application.getApplicationContext(), "获取失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                List<Product> products = gson.fromJson(response.body().string(), new TypeToken<List<Product>>(){}.getType());
                Log.d(TAG, products.toString());
                getProducts().postValue(products);
            }
        });
    }

    public MutableLiveData<List<Product>> getProducts(){
        return productsLiveData;
    }

}