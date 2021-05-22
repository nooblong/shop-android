package github.nooblong.shop.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CartViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("历史订单");
    }

    public LiveData<String> getText() {
        return mText;
    }
}