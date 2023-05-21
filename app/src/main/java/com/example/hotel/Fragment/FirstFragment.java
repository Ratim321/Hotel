package com.example.hotel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotel.MySingleton;
import com.example.hotel.R;
import com.example.hotel.SearchActivity;
import com.example.hotel.adapter.FristFragmentadapter;
import com.example.hotel.adapter.ProductAdapter;
import com.example.hotel.databinding.ActivitySearchBinding;
import com.example.hotel.databinding.FragmentFirstBinding;
import com.example.hotel.model.Authentication;
import com.example.hotel.model.Booking;
import com.example.hotel.model.Fragmentadapter;
import com.example.hotel.model.Hotel;
import com.example.hotel.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirstFragment extends Fragment {
    private static final String URL_SEARCH = "https://shaanecommerce.000webhostapp.com/getbooking.php";
    FristFragmentadapter fristFragmentadapter;
    ImageButton imageButton;
    ArrayList<Booking> list= new ArrayList<>();
    public FirstFragment() {
        // Required empty public constructor
    }
   FragmentFirstBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding=FragmentFirstBinding.inflate(inflater, container, false);
        getProducts();
       fristFragmentadapter = new FristFragmentadapter(list ,getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.firstfragmentrecyleview.setAdapter( fristFragmentadapter );
        binding.firstfragmentrecyleview.setLayoutManager(layoutManager);


        return binding.getRoot();
    }
    private void  getProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEARCH,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
//                            Toast.makeText(getContext(),response, Toast.LENGTH_SHORT).show();

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);


                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject jsonResponse = array.getJSONObject(i);

                                String hotelname=jsonResponse.getString("hotelname");
                                String roomtype=jsonResponse.getString("roomtype");
                                String price=jsonResponse.getString("price");
                                String date=jsonResponse.getString("date");
                                String bookingnumber=jsonResponse.getString("bookingnumber");


                                Booking p1=new Booking();

                                 p1.setHotename(hotelname);
                                 p1.setRoomtype(roomtype);
                                 p1.setPrice(price);
                                p1.setDate(date);
                                p1.setBookingnumber(bookingnumber);


                               list.add(p1);
                            }

                           fristFragmentadapter.notifyDataSetChanged();




                            //creating adapter object and setting it to recyclerview

                        } catch (JSONException e) {
                            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Authentication a=new Authentication(getContext());
                User u=new User();
                u= a.getCurrentUser();

                Map<String, String> params = new HashMap<>();
                params.put("userid", u.getId());




                return params;
            }

        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}
