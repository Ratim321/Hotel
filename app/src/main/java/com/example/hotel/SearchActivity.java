package com.example.hotel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.hotel.adapter.ProductAdapter;
import com.example.hotel.databinding.ActivitySearchBinding;
import com.example.hotel.model.Authentication;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {


    ActivitySearchBinding binding;
    ProductAdapter productAdapter;
    ArrayList<Hotel> products= new ArrayList<>();
    ArrayList<Hotel> filterproduct= new ArrayList<>();
    TextView searchavailable;
    private static final String URL_SEARCH = "https://shaanecommerce.000webhostapp.com/getlocation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        searchavailable=findViewById(R.id.notavailable);

        String query = getIntent().getStringExtra("location");
        String fromdate = getIntent().getStringExtra("fromdate");
        String todate = getIntent().getStringExtra("todate");
        Toast.makeText(SearchActivity.this,fromdate+" "+todate,Toast.LENGTH_LONG).show();
        String adult = getIntent().getStringExtra("adult");
        String children = getIntent().getStringExtra("children");

        getProducts(query,adult,children,fromdate,todate);
//        getSupportActionBar().setTitle(query);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        productAdapter = new ProductAdapter(this, filterproduct);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
        if(filterproduct.size()==0){
            searchavailable.setVisibility(View.GONE);

        }else{
            searchavailable.setVisibility(View.VISIBLE);
        }

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void  getProducts(String query, String adult, String children,String fromdate,String todate) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEARCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            if(array.length()==0){
                              binding.notfound.setVisibility(View.VISIBLE);
                            }
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                Hotel p1=new Hotel();
                                p1.setHotelid(product.getString("hotelid"));
                                p1.setHotelname(product.getString("hotelname"));
                                p1.setLocation(product.getString("location"));
                                p1.setRoomid(product.getString("roomid"));
                                p1.setRoomtype(product.getString("roomtype"));
                                p1.setSize(product.getString("size"));
                                p1.setAddress(product.getString("address"));
                                p1.setPrice(product.getString("price"));
                                p1.setPhoneno(product.getString("phoneno"));
                                p1.setEmail(product.getString("email"));
                                p1.setRating(product.getString("rating"));
                                p1.setFromdate(fromdate);
                                p1.setTodate(todate);



                                products.add(p1);
                            }
                            productAdapter.notifyDataSetChanged();
                            //  Toast.makeText(UserProfile.this,orders.toString(), Toast.LENGTH_SHORT).show();

//                            Toast.makeText(SearchActivity.this,String.valueOf(products.size()), Toast.LENGTH_SHORT).show();

                            for(Hotel item:products) {

                                if (item.getHotelname().toLowerCase().contains(query.toLowerCase())) {

                                    filterproduct.add(item);
                                }
                            }



                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            Toast.makeText(SearchActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(SearchActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Authentication a=new Authentication(SearchActivity.this);
                User u=new User();
                u= a.getCurrentUser();

                Map<String, String> params = new HashMap<>();
                params.put("name", query);
                params.put("adult", adult);
                params.put("children", children);
                params.put("fromdate",fromdate );
                params.put("todate", todate);



                return params;
            }

        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}