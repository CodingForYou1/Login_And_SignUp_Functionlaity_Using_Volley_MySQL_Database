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
import com.example.vollyyoutube.databinding.ActivityInssertDataBinding;

import java.util.HashMap;
import java.util.Map;

public class InssertDataActivity extends AppCompatActivity {

    ActivityInssertDataBinding binding;

    // you have to use your IP address
    String url = "http://192.161.44.11/AndroidApi/volley/insertdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInssertDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InssertDataActivity.this , LoginActivity.class));
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etUsername.getText().toString().isEmpty()){
                    binding.etUsername.setError("Please enter username");
                }else if (binding.etEmail.getText().toString().isEmpty()){
                    binding.etEmail.setError("please enter email");
                }else if (binding.etPassword.getText().toString().isEmpty()){
                    binding.etPassword.setError("please enter password");
                }else {
                    inserData(binding);
                }
            }
        });

    }

    private void inserData(ActivityInssertDataBinding binding) {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                startActivity(new Intent(InssertDataActivity.this , MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(InssertDataActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String , String> map = new HashMap<>();

                map.put("username" , binding.etUsername.getText().toString());
                map.put("email" , binding.etEmail.getText().toString());
                map.put("password" , binding.etPassword.getText().toString());

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}