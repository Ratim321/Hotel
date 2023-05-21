package com.example.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotel.MySingleton;
import com.example.hotel.R;
import com.example.hotel.databinding.FirstfragmentadapterBinding;
import com.example.hotel.model.Authentication;
import com.example.hotel.model.Booking;
import com.example.hotel.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FristFragmentadapter extends RecyclerView.Adapter<FristFragmentadapter.ViewHolder> {
     private  ArrayList<Booking> list;
     Context context;
    private static final String URL_SEARCH = "https://shaanecommerce.000webhostapp.com/deletebooking.php";

    public FristFragmentadapter(ArrayList<Booking> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public FristFragmentadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.firstfragmentadapter,parent,false);
        ViewHolder v=new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull FristFragmentadapter.ViewHolder holder, int position) {
              Booking booking=list.get(position);
              holder.hotelname.setText(booking.getHotename());
        holder.price.setText(booking.getPrice());
        holder.date.setText(booking.getDate());
        holder.roomtype.setText(booking.getRoomtype());
           holder.imageButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   delete(booking.getBookingnumber());

               }
           });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
          TextView hotelname,price,date,roomtype;
        ImageButton imageButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelname=itemView.findViewById(R.id.hotel_name_textview);
            price=itemView.findViewById(R.id.price_textview);
            date=itemView.findViewById(R.id.date_textview);
            roomtype=itemView.findViewById(R.id.room_type_textview);
            imageButton=itemView.findViewById(R.id.cancel_button);


        }
    }
    private void  delete(String bookingnumber) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEARCH,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context.getApplicationContext(), response, Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> params = new HashMap<>();
                params.put("bookingnumber", bookingnumber);




                return params;
            }

        };
        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);

    }
}
