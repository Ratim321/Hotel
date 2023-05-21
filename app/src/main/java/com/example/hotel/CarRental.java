package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hotel.model.Authentication;
import com.example.hotel.model.User;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CarRental extends AppCompatActivity {

    // on below line we are creating variables.
    private Button pickDateBtn;
    private TextView selectedDateTV;
    String date;
    String time;
    private  Button bookcar;
    private  TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rental);
        // on below line we are initializing our variables.
        pickDateBtn = findViewById(R.id.idBtnPickDate);
        selectedDateTV = findViewById(R.id.idTVSelectedDate);
        bookcar=findViewById(R.id.bookcar);
        textView=findViewById(R.id.bookingdone);
        // on below line we are adding click listener for our pick date button
        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        CarRental.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.

                                // Create a TimePickerDialog to allow user to select a time
                                TimePickerDialog timePickerDialog = new TimePickerDialog(CarRental.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        // Get the selected time
                                        String selectedTime = hourOfDay + ":" + minute;
                                        if (hourOfDay >= 12) {
                                            selectedTime = (hourOfDay == 12 ? 12 : hourOfDay - 12) + ":" + minute + " PM";
                                        } else {
                                            selectedTime = (hourOfDay == 0 ? 12 : hourOfDay) + ":" + minute + " AM";
                                        }
                                       time=selectedTime;

                                        selectedDateTV.setText("Selected date is "+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year+" "+" time is "+selectedTime);
                                        date=String.valueOf(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, 0, 0, false);


                                // Show the TimePickerDialog
                                timePickerDialog.show();
                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

         bookcar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 insertData(date,time);
             }
         });



    }

    private void insertData(String date,String time) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://shaanecommerce.000webhostapp.com/bookcar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CarRental.this, response, Toast.LENGTH_SHORT).show();
                        textView.setVisibility(View.VISIBLE);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CarRental.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Authentication a=new Authentication(CarRental.this);


                User u=new User();
                u=a.getCurrentUser();
                Map<String, String> params = new HashMap<>();
                params.put("userid", u.getId());
                params.put("date", date);
                params.put("time", time);


                return params;
            }

        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}