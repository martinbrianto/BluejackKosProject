package com.example.projectlab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectlab.Adapter.ListKosAdapter;
import com.example.projectlab.Model.Book;
import com.example.projectlab.Model.Kos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.projectlab.Storage.BookStorage.ListBook;
import static com.example.projectlab.Storage.UserStorage.globaluserID;

public class KosDetailForm extends AppCompatActivity {

    ImageView pict;
    TextView tvNama, tvHarga, tvFacility, tvDesc, tvLongitude, tvLatitude;
    Button book;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    int jumlahBook;
    String BookID = null;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kos_detail_form);

        Intent intent = getIntent();
        final Kos kos = intent.getParcelableExtra("Kos");

        String ID = kos.getID();
        int picture = kos.getPic();
        int price = kos.getPrice();
        String name = kos.getName();
        String facility = kos.getFacility();
        String desc = kos.getDesc();
        String longitude = kos.getLongitude();
        String latitude = kos.getLatitude();


        tvNama = findViewById(R.id.tvnama);
        pict = findViewById(R.id.iv_kos);
        tvHarga = findViewById(R.id.tvharga);
        tvFacility = findViewById(R.id.tvfacility);
        tvDesc = findViewById(R.id.tvdesc);
        tvLongitude = findViewById(R.id.tvlatitude);
        tvLatitude = findViewById(R.id.tvlatitude);
        book = findViewById(R.id.btnBook);

        tvNama.setText(name);
        tvHarga.setText("Rp "+price+" /Bulan");
        tvFacility.setText(facility);
        tvDesc.setText(desc);
        tvLongitude.setText("Longitude"+longitude);
        tvLatitude.setText("Latitude: "+latitude);
        pict.setImageResource(picture);

    book.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            final long todayDate = System.currentTimeMillis() - 1000;
            Log.d("qqq", "onDateSet: "+dateFormat.format(cal.getTime()));
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(KosDetailForm.this, mDateSetListener
                    ,year,month,day);
            dialog.getDatePicker().setMinDate(todayDate);
            dialog.show();
        }


    });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Calendar cal = Calendar.getInstance();
                Date todayDate = new Date();
                Date inputDate = new Date();
                String ID;

                month = month+1;
                String date = month + "/" + day + "/" + year;


                try {
                    inputDate = dateFormat.parse(date);
                    todayDate = dateFormat.parse(dateFormat.format(cal.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.d("qwe", "todayDate: "+dateFormat.format(todayDate));
                Log.d("qwe", "inputDate: "+dateFormat.format(inputDate));
                Log.d("IDD", "ID: "+ globaluserID);

                if(inputDate.before(todayDate)){
                    Toast.makeText(KosDetailForm.this,"Error: Input date must not before today's date", Toast.LENGTH_LONG).show();
                }

                jumlahBook = ListBook.size()+1;
                if(jumlahBook<10){
                    BookID = "BK00"+jumlahBook;
                }else if(jumlahBook<100){
                    BookID = "BK0"+jumlahBook;
                }else if(jumlahBook>=100){
                    BookID = "BK"+jumlahBook;
                }else if(jumlahBook==1000){
                    Toast.makeText(KosDetailForm.this,"Cannot book any more kos", Toast.LENGTH_LONG).show();
                }
                if(ListBook.size()==0){
                    ListBook.add(new Book(BookID, globaluserID, date, kos));
                    Toast.makeText(KosDetailForm.this, "Book Success", Toast.LENGTH_LONG).show();
                    Log.d("IDD", "BOOK SUCCESS: "+ BookID+"KOSID"+kos.getID());
                }else{
                    for(int j = 0 ; j < ListBook.size(); j++) {
                        if (globaluserID.equals(ListBook.get(j).getUserID()) && kos.getID().equals(ListBook.get(j).getKosdata().getID())) {
                            flag=1;
                            break;
                        } else {
                            flag=0;
                        }
                    }

                    if(flag == 1){
                        Toast.makeText(KosDetailForm.this, BookID+"Error: you cannot book same kos", Toast.LENGTH_LONG).show();
                    }else if(flag==0){
                        ListBook.add(new Book(BookID, globaluserID, date, kos));
                        Toast.makeText(KosDetailForm.this, "Book Success", Toast.LENGTH_LONG).show();
                        Log.d("IDD", "BOOK SUCCESS: "+ BookID+"KOSID"+kos.getID());

                    }



                }




            }
        };



    }
}
