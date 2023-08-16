package com.bitcodetech.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAlert;
    private Button btnDatePicker;
    private Button btnTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();

        btnAlert.setOnClickListener(new BtnAlertClickListener());

        btnDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(
                                        MainActivity.this,
                                        new DateListener(),
                                        1947,
                                        7,
                                        15
                                );
                        datePickerDialog.show();
                    }
                }
        );

        btnTimePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog =
                                new TimePickerDialog(
                                        MainActivity.this,
                                        new TimeListener(),
                                        22,
                                        45,
                                        false
                                );
                        timePickerDialog.show();
                    }
                }
        );
    }

    private class TimeListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
            btnTimePicker.setText(hours + " : " + minutes);
        }
    }

    private class DateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePickerDialog, int year, int month, int dayOfMonth) {
            btnDatePicker.setText( dayOfMonth + " - " + (month+1) + " - " + year);
        }
    }

    private class BtnAlertClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("BitCode Tech");
            builder.setMessage("Do you want to work hard?");

            DialogInterface.OnClickListener listener =
                    new AlertButtonsClickListener();

            builder.setPositiveButton("Yes", listener);
            builder.setNegativeButton("No", listener);
            builder.setNeutralButton("Hmm", listener);

            /*builder.setPositiveButton("Yes", new BtnYesClickListener());
            builder.setNegativeButton("No", new BtnNoClickListener());
            builder.setNeutralButton("Hmm", new BtnHmmClickListener());*/

            builder.setCancelable(true);

            builder.setOnCancelListener(
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            mt("Cancelled");
                        }
                    }
            );

            builder.setOnDismissListener(
                    new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            mt("Dismissed");
                        }
                    }
            );

            AlertDialog alertDialog = builder.create();
            //mt("AlertDialog: " + alertDialog.hashCode());
            alertDialog.show();

        }
    }

    private class AlertButtonsClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    mt("Yes");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    mt("No");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    mt("Hmm");
                    break;
            }
        }
    }

    private class BtnYesClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You said you will work hard! Welcome to BitCode!");
            mt("Yes: " + dialog.hashCode());
        }
    }

    private class BtnNoClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You said you will NOT work hard! Pay the fees and leave!");
        }
    }

    private class BtnHmmClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("??????");
        }
    }

    private void initViews() {
        btnAlert = findViewById(R.id.btnAlert);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnTimePicker = findViewById(R.id.btnTimePicker);
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        Log.e("tag", text);
    }
}



