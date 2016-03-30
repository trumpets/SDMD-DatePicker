package gr.academic.city.sdmd.datepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_pick_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });
    }

    private void pickDate() {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Button btnPickDate = (Button) findViewById(R.id.btn_pick_date);

                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear); // really important to know is that java's calendar has 0-based months (0 = January, 5 = June etc)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                Date date = c.getTime();
                long dateInMillis = c.getTimeInMillis();

                btnPickDate.setText(DATE_FORMATTER.format(date));
            }
        }, year, month, day);

        dialog.show();
    }
}
