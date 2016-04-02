package com.example.alex.retrofitproject;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Registration extends Activity implements View.OnClickListener {
    private EditText login;
    private EditText email;
    private EditText password;
    private Button buttonRegistration;
    private LinkedHashMap<String, String> data;
    private String regularExprensionsEmail;
    private boolean result;
    private String response;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        login = (EditText) findViewById(R.id.registration_login);
        email = (EditText) findViewById(R.id.registration_email);
        password = (EditText) findViewById(R.id.registration_password);
        buttonRegistration = (Button) findViewById(R.id.registration_button_registration);
        buttonRegistration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registration_button_registration:
                sendRegistrationInformationToServer();
                break;
        }
    }

    public void sendRegistrationInformationToServer() {


        data = new LinkedHashMap<>();

        if (login.getText().length() < 6) {
            Toast.makeText(getApplication(), "Логин, должен быть больше 6 символов", Toast.LENGTH_LONG).show();
        } else if (resultRegularExprensionsEmail() == false) {
            Toast.makeText(getApplication(), "Email, некоректный", Toast.LENGTH_LONG).show();
        } else if (password.getText().length() < 6) {
            Toast.makeText(getApplication(), "Пароль, должен быть больше 6 символов", Toast.LENGTH_LONG).show();
        } else {
            data.put("login", String.valueOf(login.getText()));
            data.put("email", String.valueOf(email.getText()));
            data.put("password", String.valueOf(password.getText()));

            Retrofit.sendRegistrationData(data, new Callback<RegistrationResponseFromServer>() {
                @Override
                public void success(RegistrationResponseFromServer registrationResponstseFromServer, Response response)  {
                    Toast.makeText(getApplication(), "Data sent", Toast.LENGTH_SHORT).show();

               //     Integer response2 = registrationResponstseFromServer.response;
                    ///registrationResponseFromServer = new RegistrationResponseFromServer();
                   //String ddd = response.getReason();
                   // login.setText(String.valueOf(ddd));
//                    JSONObject jsonObject = null;
//
//                    try {
//                        jsonObject = new JSONObject(String.valueOf(ddd));
//                         fff = jsonObject.getJSONObject("response");
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
////                    }
//                    if (ddd == 3){
//                        Toast.makeText(Registration.this,"Логин занят",Toast.LENGTH_LONG).show();
//                    }else if (ddd ==1){
//                        Toast.makeText(Registration.this,"Email занят", Toast.LENGTH_LONG).show();
//                    }else if (ddd==2){
//                        Toast.makeText(Registration.this,"ВСЕ ОК!!!!", Toast.LENGTH_LONG).show();
//                    }

//                        registrationResponseFromServer = new RegistrationResponseFromServer();
//                        registrationResponseFromServer.response = String.valueOf(response.toString());
//                        if (registrationResponseFromServer.response.equals("0")){
//                            Toast.makeText(Registration.this,"Логин занят",Toast.LENGTH_LONG).show();
//                        }else if (registrationResponseFromServer.response.equals("1")){
//                            Toast.makeText(Registration.this,"Email занят", Toast.LENGTH_LONG).show();
//                        }else if (registrationResponseFromServer.response.equals("2")){
//                            Toast.makeText(Registration.this,"ВСЕ ОК!!!!", Toast.LENGTH_LONG).show();
//                        }


//                    if (response == "0"){
//                        Toast.makeText(Registration.this,"Логин занят",Toast.LENGTH_LONG).show();
//                    }else if (response == "1"){
//                        Toast.makeText(Registration.this,"Email занят", Toast.LENGTH_LONG).show();
//                    }else if (response == "2"){
//                        Toast.makeText(Registration.this,"ВСЕ ОК!!!!", Toast.LENGTH_LONG).show();
//                    }
//
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplication(), "Data don't sent. Check internet connection", Toast.LENGTH_LONG).show();
                }
            });


        }
        // Gson gson = new GsonBuilder().create();
//        Gson gson = new GsonBuilder().create();
//        String responce1 = gson.fromJson(".response", String.class);
//        login.setText(String.valueOf(responce1));


//        System.out.println(responce1);
//        if (response == "0"){
//            Toast.makeText(Registration.this,"Такой login, уже существует",Toast.LENGTH_LONG).show();
//        }else if (response == "1"){
//            Toast.makeText(Registration.this,"Такой email, уже занят",Toast.LENGTH_LONG).show();
//        }else if (response == "2"){
//            Toast.makeText(Registration.this,"Пользователь успешно зарегестрировался",Toast.LENGTH_LONG).show();
//        }


    }

    private boolean resultRegularExprensionsEmail() {
        regularExprensionsEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\"" +
                ")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\" +
                "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?" +
                "|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\" +
                "x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pCheckEmail = Pattern.compile(regularExprensionsEmail);
        Matcher mCheckEmail = pCheckEmail.matcher(email.getText().toString());
        result = mCheckEmail.matches();
        return result;
    }


}

