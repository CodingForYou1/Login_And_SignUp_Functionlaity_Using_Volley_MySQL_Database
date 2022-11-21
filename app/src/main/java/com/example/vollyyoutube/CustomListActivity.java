package com.example.vollyyoutube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vollyyoutube.databinding.ActivityCustomListBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    ActivityCustomListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String url = "https://reqres.in/api/users";
        ArrayList<UserModel> userArrayList = new ArrayList<>();

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject singleObject = jsonArray.getJSONObject(i);
                        int id = singleObject.getInt("id");
                        String fName = singleObject.getString("first_name");
                        String lName = singleObject.getString("last_name");
                        String email = singleObject.getString("email");
                        String image = singleObject.getString("avatar");

                        UserModel userModel = new UserModel(id,email,fName,lName,image);
                        userArrayList.add(userModel);
                    }

                    UserAdapter userAdapter = new UserAdapter(userArrayList , CustomListActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CustomListActivity.this);
                    binding.recyclerView.setLayoutManager(linearLayoutManager);
                    binding.recyclerView.setAdapter(userAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CustomListActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}