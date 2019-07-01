package com.example.nihal.navigationdrawerexample.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nihal.navigationdrawerexample.Constants;
import com.example.nihal.navigationdrawerexample.MainActivity;
import com.example.nihal.navigationdrawerexample.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_email)
    EditText login_input_email;

    @BindView(R.id.input_password)
    EditText login_input_password;

    @BindView(R.id.btn_login)
    Button login_btn;


    String username;
    String password;
    //TextView textResponse;
    LoginResponse userImageResponse;
    Bundle b= new Bundle();;
 SharedPreferences sharedpref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ButterKnife.bind(this);

        sharedpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // Check if UserResponse is Already Logged In
        if(SaveLoginStatus.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, " Welcome to the Login ", Toast.LENGTH_SHORT).show();
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = login_input_email.getText().toString();
                password = login_input_password.getText().toString();


                /*SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Username", username);
                editor.putString("Password", password);
                editor.apply();*/

                if (validateLogin(username, password)) {

                    loginUser(username, password);
                }
            }
        });

    }

    private boolean validateLogin(String username, String password) {

        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void loginUser(final String username, final String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginInterface loginInterface = retrofit.create(LoginInterface.class);

        final Call<LoginResponse> loginResponseCall = loginInterface.getUserLogin(username,password);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){

                    userImageResponse = response.body();
                    Log.e("TAG", "onResponse: "+userImageResponse );


                    Log.e("TAG", "onResponse: "+userImageResponse.getResponseResult().getUsername() );

                    //Picasso.get().load(userImageResponse.getResponseResult().getUserImage()).into(circularImage);

                    //SaveLoginStatus.putPref("userinfo",userImageResponse.getResponseResult().getUsername(),getApplicationContext());

                    b.putString("myname",userImageResponse.getResponseResult().getUsername());

                    Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_SHORT).show();

                    // Set Logged In statue to 'true'
                    SaveLoginStatus.setLoggedIn(getApplicationContext(), true);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    //intent.putExtra(userImageResponse.getResponseResult().getUsername(),userImageResponse.getResponseResult().getPassword());
                    //intent.putExtra("token",userImageResponse.getResponseResult().getUsername());
                    intent.putExtras(b);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else{
                    Log.e("TAG", "onResponse: empty response" );
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: this method called  "+ t.getLocalizedMessage() );
            }
        });
    }
}