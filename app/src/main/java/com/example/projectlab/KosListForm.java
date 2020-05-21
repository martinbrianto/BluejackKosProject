package com.example.projectlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.projectlab.Adapter.BookingFormAdapter;
import com.example.projectlab.Adapter.ListKosAdapter;
import com.example.projectlab.Model.Kos;
import com.example.projectlab.Storage.KosStorage;

import java.util.Calendar;
import java.util.List;

import static com.example.projectlab.Storage.KosStorage.ListKos;
import static com.example.projectlab.Storage.UserStorage.globaluserID;

public class KosListForm extends AppCompatActivity implements View.OnClickListener, ListKosAdapter.OnKosListerner
{

    ListKosAdapter listKosAdapter;
    RecyclerView rvKos;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_book){
            Intent j = new Intent(KosListForm.this, BookingForm.class);
            startActivity(j);

        }else if(item.getItemId() == R.id.item_logout){
        Intent i = new Intent(KosListForm.this,MainActivity.class);
        startActivity(i);
        finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kos_list_form);



    rvKos = findViewById(R.id.rv_koslist);

        addDummyData();
        showList();

    }



    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    void showList(){
        Log.d("qqq", "showListKOS: JALAN");
        listKosAdapter = new ListKosAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvKos.setLayoutManager(layoutManager);
        rvKos.setAdapter(listKosAdapter);

    }

    private void addDummyData(){
        //write Logic process input from user here

        if(ListKos.size()==0){


        ListKos.add(new Kos("1",R.drawable.kos1,"Kos Maharaja",1450000,"AC, WiFi, Bathroom","The best boarding","-6.2000809","106.7833355"));
        ListKos.add(new Kos("2",R.drawable.kos2,"Kos Haji Indra",1900000,"AC, WiFi","The cheapest boarding","-6.2261741","106.9078293"));
        ListKos.add(new Kos("3",R.drawable.kos3,"Kos Orange",1500000,"Billiard, AC","Comfortable","-6.22213741","123.2321223"));
        ListKos.add(new Kos("4",R.drawable.kos4,"Kos D'lofts",4000000,"Swimming pool, Security","Luxury boarding","-21.222213741","23.23321223"));
        ListKos.add(new Kos("5",R.drawable.kos5,"Kos HolyWings",3200000,"Free flow beer","Party everyday","-69.237123","420.420000"));
        ListKos.add(new Kos("6",R.drawable.kos6,"Kos Super Bandeng",2420000,"Gym, near KFC","Strategic","-2312.2213741","00.1110"));
        }
    }


    @Override
    public void onKosClick(int position) {


        Kos k = ListKos.get(position);
        //String UserID = UserID;

        Bundle packet = getIntent().getExtras();

        Log.d("aaa", "onKosClick: "+position);
        Log.d("abc",ListKos.get(0).getName());
        Log.d("IDD", "IDKL: "+globaluserID);

        Intent intent = new Intent(this, KosDetailForm.class);
        intent.putExtra("Kos",k);
        startActivity(intent);
    }
}
