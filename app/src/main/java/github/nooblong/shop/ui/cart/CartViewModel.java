package github.nooblong.shop.ui.cart;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import github.nooblong.shop.entity.OrderDetail;
import github.nooblong.shop.ui.login.LoginFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CartViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<List<OrderDetail>> orderDetailsLiveData = new MutableLiveData<>();

    private List<Integer> orderNumList;

    public CartViewModel(Application application) {
        super(application);
        orderNumList = new ArrayList<>();
        mText = new MutableLiveData<>();
        mText.setValue("历史订单");

        if (LoginFragment.session == null) {
            mText.setValue("请先登录");
        } else {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .build();
            final Request request = new Request.Builder()
                    .get()
                    .addHeader("cookie", LoginFragment.session)
                    .url(LoginFragment.BASE_URL + "/api/order/num")
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mText.setValue("获取失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {

                        List<OrderDetail> orderDetailList = new ArrayList<>();

                        Gson gson = new Gson();
                        String json = response.body().string();
                        Log.d("resp", json);
                        Integer[] tmp = gson.fromJson(json, Integer[].class);
                        orderNumList = Arrays.asList(tmp);
                        Log.d("orderList", orderNumList.toString());

                        if (orderNumList.size() == 0){
                            mText.setValue("没有订单");
                        } else {
                            for (int i : orderNumList) {
                                final Request request2 = new Request.Builder()
                                        .get()
                                        .addHeader("cookie", LoginFragment.session)
                                        .url(LoginFragment.BASE_URL + "/api/order/list/" + i)
                                        .build();
                                Call call2 = client.newCall(request2);
                                call2.enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String json = response.body().string();
                                        Log.d("order" + i, json);
                                        OrderDetail orderDetail = new Gson().fromJson(json,OrderDetail.class);
                                        orderDetailList.add(orderDetail);
//                                        Log.d("orderDetailList" , orderDetailList.toString());
                                        getOrderDetailsLiveData().postValue(orderDetailList);
                                    }
                                });
                            }
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<OrderDetail>> getOrderDetailsLiveData() {
        return orderDetailsLiveData;
    }
}