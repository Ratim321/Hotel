package com.example.hotel;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.util.Pair;

import com.example.hotel.Fragment.FirstFragment;
import com.example.hotel.Fragment.MainFragment;
import com.example.hotel.model.Authentication;
import com.example.hotel.model.Booking;
import com.example.hotel.model.User;
import com.fxn.BubbleTabBar;
import com.fxn.OnBubbleClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.sql.Date;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {


    private AppCompatButton mPickDateButton;
    private AppCompatButton Guestnumber;
    private AppCompatButton search;
    private EditText Location;
    private TextView mShowSelectedDateText;
    private BottomSheetDialog bottomSheetDialog;
    TextView test;
    String roomCount ;
    String adultCount ;
    String childrenCount;
    String fromdate,todate;

    ImageView userProfile, foodService, carRental;
    BubbleTabBar bottomNav;
    private String date="Null";

    private TextView roomCountTextView, adultCountTextView, childCountTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mPickDateButton = findViewById(R.id.pick_date_button);
        Guestnumber= findViewById(R.id.Guestnumber);
        Location=findViewById(R.id.Location);
        bottomNav=findViewById(R.id.bottomNav);
        search=findViewById(R.id.search);
        userProfile = findViewById(R.id.userProfile);
        foodService = findViewById(R.id.foodService);
        carRental = findViewById(R.id.carRental);
        test=findViewById(R.id.show_selected_date);
        mShowSelectedDateText = findViewById(R.id.show_selected_date);
        String name = getIntent().getStringExtra("name");
        Authentication a=new Authentication(Dashboard.this);
        User u=new User();
        u= a.getCurrentUser();
       // Toast.makeText(this, u.getId(), Toast.LENGTH_SHORT).show();
        carRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, CarRental.class);
                startActivity(intent);
            }
        });

         bottomNav.addBubbleListener(new OnBubbleClickListener() {
             @Override
             public void onBubbleClick(int i) {
                 switch (i) {

                     case 2131296512:
                         // Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
                         Intent intent3 = new Intent(Dashboard.this,CarRental.class);

                         startActivity(intent3);
                         break;
                     case 2131296895:
                         // Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(Dashboard.this, MainFragment.class);

                         startActivity(intent);
                         break;
                     case 2131296897:
                         //  Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
                         Intent intent1 = new Intent(Dashboard.this, UserDashboardActivity.class);

                         startActivity(intent1);
                         break;

                 }
             }
         });


        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, UserDashboardActivity.class);
                startActivity(intent);
            }
        });



        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, MainFragment.class);
                startActivity(intent);
            }
        });

        mPickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent=new Intent(Dashboard.this,SearchActivity.class);
                intent.putExtra("location",Location.getText().toString());
                intent.putExtra("date",date);
                intent.putExtra("adult",adultCount);
                intent.putExtra("children",childrenCount);
                intent.putExtra("fromdate",fromdate);
                intent.putExtra("todate",todate);

                startActivity(intent);

            }
        });


        if(name!=null){
            Location.setText(name);
        }


        Guestnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guestpicker();
            }
        });


    }

    private void datepicker() {
        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();

        // now define the properties of the
        // materialDateBuilder
        materialDateBuilder.setTitleText("SELECT A DATE");


        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();


        mPickDateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });


        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        Pair<Long, Long> datePair = (Pair<Long, Long>) selection;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            fromdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(datePair.first));
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            todate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(datePair.second));
                        }
                        mPickDateButton.setText("Selected Dates: " + fromdate + " to " + todate);

                    }
                });
    }




    private void Guestpicker() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottomsheet, null);

        Button roomDecrementButton = bottomSheetView.findViewById(R.id.roomDecrementButton);
        Button roomIncrementButton = bottomSheetView.findViewById(R.id.roomIncrementButton);
        Button adultDecrementButton = bottomSheetView.findViewById(R.id.adultDecrementButton);
        Button adultIncrementButton = bottomSheetView.findViewById(R.id.adultIncrementButton);
        Button childrenDecrementButton = bottomSheetView.findViewById(R.id.childrenDecrementButton);
        Button childrenIncrementButton = bottomSheetView.findViewById(R.id.childrenIncrementButton);
        Button save = bottomSheetView.findViewById(R.id.saveButton);
        final TextView roomCountTextView = bottomSheetView.findViewById(R.id.roomCountTextView);
        final TextView adultCountTextView = bottomSheetView.findViewById(R.id.adultCountTextView);
        final TextView childrenCountTextView = bottomSheetView.findViewById(R.id.childrenCountTextView);


        roomDecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementRoomCount(roomCountTextView);
            }
        });
        adultDecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementadultCount(adultCountTextView);
            }
        });


        roomIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementRoomCount(roomCountTextView);
            }
        });
        adultIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementadultCount(adultCountTextView);
            }
        });


        childrenIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementchildrenCount(childrenCountTextView);
            }
        });
        childrenDecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementchildrenCount(childrenCountTextView);
            }
        });








        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomCount = roomCountTextView.getText().toString();
                adultCount = adultCountTextView.getText().toString();
                childrenCount = childrenCountTextView.getText().toString();
                Guestnumber.setText("Room:"+roomCount+" "+"Adult:"+adultCount+" "+"Children:"+childrenCount);

                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog = new BottomSheetDialog(Dashboard.this);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void incrementRoomCount(TextView textView) {
        int roomCount = Integer.parseInt(textView.getText().toString()) + 1;
        textView.setText(String.valueOf(roomCount));
    }

    private void incrementchildrenCount(TextView textView) {
        int roomCount = Integer.parseInt(textView.getText().toString()) + 1;
        textView.setText(String.valueOf(roomCount));
    }

    private void incrementadultCount(TextView textView) {
        int roomCount = Integer.parseInt(textView.getText().toString()) + 1;
        textView.setText(String.valueOf(roomCount));
    }

    private void decrementRoomCount(TextView textView) {
        int roomCount = Integer.parseInt(textView.getText().toString());
        if (roomCount > 1) {
            roomCount--;
            textView.setText(String.valueOf(roomCount));
        }
    }

    private void decrementadultCount(TextView textView) {
        int roomCount = Integer.parseInt(textView.getText().toString());
        if (roomCount > 1) {
            roomCount--;
            textView.setText(String.valueOf(roomCount));
        }
    }

    private void decrementchildrenCount(TextView textView) {
        int roomCount = Integer.parseInt(textView.getText().toString());
        if (roomCount > 1) {
            roomCount--;
            textView.setText(String.valueOf(roomCount));
        }
    }
}