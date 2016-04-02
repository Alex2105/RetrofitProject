package com.example.alex.retrofitproject;

import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Alex on 30.03.2016.
 */
public class Retrofit {
    private static final String ENDPOINT = "http://testpb.alscon-clients.com";
    private static PostInterfaceRegistration postInterfaceRegistration;
    private static PostInterfaceLogin postInterfaceLogin;
    static {
        init();
    }

    private static void init() {
        RestAdapter postAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        postInterfaceRegistration =postAdapter.create(PostInterfaceRegistration.class);
        postInterfaceLogin = postAdapter.create(PostInterfaceLogin.class);
    }

      public static void sendRegistrationData(Map<String, String> datas, Callback<RegistrationResponseFromServer> callback){
          postInterfaceRegistration.sendRegistrationData(datas, callback);
      }

    public static void sendLoginData(Map<String, String> loginDatas, Callback<Void> callback){
        postInterfaceLogin.sendLoginData(loginDatas, callback);
    }



    interface PostInterfaceRegistration {
        @FormUrlEncoded
        @POST("/registration.php")
         void sendRegistrationData(@FieldMap Map<String, String> datas, Callback<RegistrationResponseFromServer> callback);
    }

    interface PostInterfaceLogin{
        @FormUrlEncoded
        @POST("/login.php")
        void sendLoginData(@FieldMap Map<String, String> loginDatas, Callback<Void> callback);
    }
}
