package com.example.hotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel.databinding.ActivityMainBinding;
import com.example.hotel.databinding.ActivityUserDashboardBinding;
import com.example.hotel.model.Authentication;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserDashboardActivity extends AppCompatActivity {
ActivityUserDashboardBinding binding;
    String input;
    private static final String URL_SEARCH = "https://shaanecommerce.000webhostapp.com/getstatus.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
          binding.userNameDisplay101.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  popup();
              }
          });
        Authentication a=new Authentication(UserDashboardActivity.this);
        User u=new User();
        u=a.getCurrentUser();
        getvalue(u.getId());
    }

    private void  getvalue(String value) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEARCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);


                            for (int i = 0; i < array.length(); i++) {     //getting product object from json array
                                JSONObject product = array.getJSONObject(0);

                                binding.bookedStatus101.setText(product.getString("bookedstatus"));
                                binding.foodService101.setText(product.getString("foodservice"));
                                binding.userNameDisplay101.setText(product.getString("username"));

                            }


                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            Toast.makeText(UserDashboardActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserDashboardActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Authentication a=new Authentication(UserDashboardActivity.this);
                User u=new User();
                u= a.getCurrentUser();

                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(value));




                return params;
            }

        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    void userupdate(String name){
// Instantiate the RequestQueue.
        binding.userNameDisplay101.setText(name);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://shaanecommerce.000webhostapp.com/updateuser.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UserDashboardActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserDashboardActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Authentication a=new Authentication(UserDashboardActivity.this);
                User u=new User();
                u=a.getCurrentUser();
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(u.getId()));
                params.put("username", name);

                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void popup(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(UserDashboardActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.popupwindow,null);
        final EditText txt_inputText = (EditText)mView.findViewById(R.id.txt_input);
        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
        Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userupdate(txt_inputText.getText().toString());
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}