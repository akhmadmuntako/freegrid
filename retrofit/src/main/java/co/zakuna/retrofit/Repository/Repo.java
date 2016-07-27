package co.zakuna.retrofit.Repository;

import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 14/07/2016.
 */
public class Repo {
    @SerializedName("current_user_url")
    private String CurrentUser;
    @SerializedName("user")
    private String user;

    public String getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(String currentUser) {
        CurrentUser = currentUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Repo(String user){
        this.user = user;
    }
}
