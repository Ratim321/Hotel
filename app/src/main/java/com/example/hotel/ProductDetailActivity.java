package com.example.hotel;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotel.databinding.ActivityProductDetailBinding;
import com.example.hotel.model.Authentication;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.User;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {


    ActivityProductDetailBinding binding;
    Hotel currentProduct;
 String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String hotelid = getIntent().getStringExtra("hotelid");
        String hotelname = getIntent().getStringExtra("hotelname");
        String location = getIntent().getStringExtra("location");
        String roomid = getIntent().getStringExtra("roomid");
        String roomtype = getIntent().getStringExtra("roomtype");
        String size = getIntent().getStringExtra("size");
        String address = getIntent().getStringExtra("address");
        String price = getIntent().getStringExtra("price");
        String phoneno = getIntent().getStringExtra("phoneno");
        String email = getIntent().getStringExtra("email");
        String rating = getIntent().getStringExtra("rating");
        String fromdate = getIntent().getStringExtra("fromdate");
        String todate = getIntent().getStringExtra("todate");

     Toast.makeText(ProductDetailActivity.this,"From date"+fromdate,Toast.LENGTH_LONG).show();
//        Glide.with(this)
//                .load(image)
//                .into(binding.productImage);

        binding.rating.setText("Rating is "+rating+" "+" ");
        binding.ProductPrice.setText("Price is "+price+" "+"dollar");



        //getProductDetails(id);
//
//        getSupportActionBar().setTitle(roomtype);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popup(hotelid, hotelname, location, roomid, roomtype, size, address, price, phoneno, email, rating,fromdate,todate);

            }
        });





    }

    private void insertData(String hotelid, String hotelname, String location, String roomid, String roomtype,
                            String size, String address, String price, String phoneno, String email, String rating,String fromdate,String todate,String input) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://shaanecommerce.000webhostapp.com/bookuser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ProductDetailActivity.this, "Booked", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ProductDetailActivity.this,Dashboard.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductDetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Authentication a=new Authentication(ProductDetailActivity.this);
//                User u=new User();
//                u= a.getCurrentUser();

                // Create a SimpleDateFormat object with the SQL date format
                SimpleDateFormat sdf = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }

// Get the current date
                Date currentDate = new Date(System.currentTimeMillis());
                String formattedDate="0000-00-00";
// Format the date using the SimpleDateFormat object
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                     formattedDate = sdf.format(currentDate);
                }


                User u=new User();
                u=a.getCurrentUser();
                Map<String, String> params = new HashMap<>();
                params.put("userid", u.getId());
                params.put("hotelid", hotelid);
//                params.put("hotelname", hotelname);

//                params.put("location", location);

                params.put("fromdate",fromdate);
                params.put("todate",todate);
//                params.put("todate", location);

                params.put("roomid", roomid);
                params.put("foodstatus", input);
//                params.put("roomtype", roomtype);
//                params.put("size", size);
//                params.put("address", address);
//                params.put("price", price);
//                params.put("phoneno", phoneno);
//                params.put("email", email);
//                params.put("rating", rating);



                return params;
            }

        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    void popup(String hotelid, String hotelname, String location, String roomid, String roomtype, String size, String address, String price, String phoneno, String email, String rating, String fromdate, String todate){
        final AlertDialog.Builder alert = new AlertDialog.Builder(ProductDetailActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.popupwindowsupdate,null);
        final EditText txt_inputText = (EditText)mView.findViewById(R.id.txt_input1);
        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel1);
        Button btn_okay = (Button)mView.findViewById(R.id.btn_okay1);
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

                insertData(hotelid, hotelname, location, roomid, roomtype, size, address, price, phoneno, email, rating,fromdate,todate,txt_inputText.getText().toString());

                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }



}