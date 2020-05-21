package com.example.projectlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectlab.Adapter.BookingFormAdapter;
import com.example.projectlab.Adapter.ListKosAdapter;
import com.example.projectlab.Storage.BookStorage;

import java.util.List;

import static com.example.projectlab.Storage.BookStorage.ListBook;
import static com.example.projectlab.Storage.BookStorage.currentbook;
import static com.example.projectlab.Storage.UserStorage.globaluserID;

public class BookingForm extends AppCompatActivity implements View.OnClickListener, BookingFormAdapter.OnBookListener{

    private BookingFormAdapter bookFormAdapter;
    private RecyclerView rvBookList;
    private TextView tvEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        tvEmpty = findViewById(R.id.tvEmpty);
        rvBookList = findViewById(R.id.rv_booklist);

        currentbook.clear();
        for(int i=0; i<ListBook.size(); i++){
            if(ListBook.get(i).getUserID().equals(globaluserID)){
                currentbook.add(ListBook.get(i));
            }
        }

        checkDataSize();
        showList();
    }

    void showList(){
        bookFormAdapter = new BookingFormAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        rvBookList.setLayoutManager(layoutManager);
        rvBookList.setAdapter(bookFormAdapter);
    }

    private void checkDataSize(){
        if(BookStorage.ListBook.size() == 0){
            tvEmpty.setText("No booking transaction");
            Toast.makeText(BookingForm.this,"You don't have any booking yet", Toast.LENGTH_LONG).show();
        }
        else{
            tvEmpty.setText("");
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBookClick(int position) {
        //menu logic
        registerForContextMenu(rvBookList);
        rvBookList.showContextMenu();
    }
}
