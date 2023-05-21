package com.example.hotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Dashboard;
import com.example.hotel.ProductDetailActivity;
import com.example.hotel.R;
import com.example.hotel.databinding.ItemProductBinding;
import com.example.hotel.model.Hotel;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    ArrayList<Hotel> products;

    public ProductAdapter(Context context, ArrayList<Hotel> products) {
        this.context = context;
        this.products = products;
    }

//    public ProductAdapter(SearchActivity context, ArrayList<Product> products) {
//    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Hotel product = products.get(position);

        holder.binding.label.setText(product.getFromdate()+" "+"to"+product.getTodate());
        holder.binding.locationName.setText(product.getRoomid());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("hotelid", product.getHotelid());
                intent.putExtra("hotelname", product.getHotelname());
                intent.putExtra("location", product.getLocation());
                intent.putExtra("roomid", product.getRoomid());
                intent.putExtra("roomtype", product.getRoomtype());
                intent.putExtra("size", product.getSize());
                intent.putExtra("address", product.getAddress());
                intent.putExtra("price", product.getPrice());
                intent.putExtra("phoneno", product.getPhoneno());
                intent.putExtra("email", product.getEmail() );
                intent.putExtra("rating", product.getRating());
                intent.putExtra("fromdate", product.getFromdate());
                intent.putExtra("todate", product.getTodate());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ItemProductBinding binding;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemProductBinding.bind(itemView);
        }
    }
}
