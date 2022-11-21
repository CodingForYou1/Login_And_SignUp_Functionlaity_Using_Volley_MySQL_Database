package com.example.vollyyoutube.volleyCrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vollyyoutube.MainActivity;
import com.example.vollyyoutube.R;
import com.example.vollyyoutube.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    // you have to use your IP address
    String url = "http://192.161.44.11/AndroidApi/volley/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("LoginFile" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("isLoggedIn" , "").equals("true")){
            startActivity(new Intent(LoginActivity.this , MainActivity.class));
            finishAffinity();
        }

        binding.txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this , InssertDataActivity.class));
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etEmail.getText().toString().isEmpty()) {
                    binding.etEmail.setError("please enter email");
                } else if (binding.etPassword.getText().toString().isEmpty()) {
                    binding.etPassword.setError("please enter password");
                } else {
                    LoginUser(binding);
                }
            }
        });


    }

    private void LoginUser(ActivityLoginBinding binding) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                editor.putString("isLoggedIn", "true");
                editor.commit();
                startActivity(new Intent(LoginActivity.this , MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String , String> map = new HashMap<>();

//                map.put("username" , binding.etUsername.getText().toString());
                map.put("email" , binding.etEmail.getText().toString());
                map.put("password" , binding.etPassword.getText().toString());

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}