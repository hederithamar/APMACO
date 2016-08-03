package heder_ithamar.apmaco.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import heder_ithamar.apmaco.R;

public class DetailActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            crearFullScreenDialog();
        }
    }

    private void crearFullScreenDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FullScreenDialog newFragment = new FullScreenDialog();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment, "FullScreenFragment")
                .commit();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        FullScreenDialog fragment = (FullScreenDialog) getSupportFragmentManager().findFragmentByTag("FullScreenFragment");
        if (fragment != null) {
            fragment.setDateView(year, monthOfYear, dayOfMonth);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        FullScreenDialog fragment = (FullScreenDialog) getSupportFragmentManager().findFragmentByTag("FullScreenFragment");
        if (fragment != null) {
            fragment.setTimeView(hourOfDay, minute);
        }
    }
}

