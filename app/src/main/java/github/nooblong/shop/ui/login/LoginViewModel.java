package github.nooblong.shop.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<String> username;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        username = new MutableLiveData<>();
        username.setValue("username");
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public void setUsername(String s){
        this.username.setValue(s);
    }
}
