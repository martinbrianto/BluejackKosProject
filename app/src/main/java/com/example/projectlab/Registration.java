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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.projectlab.Model.User;
import com.example.projectlab.Storage.UserStorage;

import java.util.Calendar;

import static com.example.projectlab.Storage.UserStorage.users;

public class Registration extends AppCompatActivity {

    EditText etUser, etPass, etPhone, etConPass, etDOB;
    Button btnReg;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    RadioGroup rg_gender;
    CheckBox agree;
    RadioButton rb_gender;
    int jumlahData=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etDOB = findViewById(R.id.etDOB);
        etUser = findViewById(R.id.etUser);
        etPhone = findViewById(R.id.etPhone);
        etPass = findViewById(R.id.etPass);
        etConPass = findViewById(R.id.etConPass);
        etDOB = findViewById(R.id.etDOB);
        rg_gender = findViewById(R.id.rg_gender);
        agree = findViewById(R.id.cbAgree);
        btnReg = findViewById(R.id.btnReg);



        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Registration.this, mDateSetListener
                ,year,month,day);
                dialog.show();
            }
        });
    mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month = month+1;
        String date = month + "/" + day + "/" + year;
        etDOB.setText(date);}
        };

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, pass, conpass, DOB, phone, gender, ID;
                String patternPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$";
                String patternPhone = "^([0-9])";
                String patternUsername = "^(?=.[0-9])(?=.[a-zA-Z])";

                username = etUser.getText().toString();
                pass = etPass.getText().toString();
                conpass = etConPass.getText().toString();
                DOB = etDOB.getText().toString();
                phone = etPhone.getText().toString();
                ID="";

                if(username.isEmpty()){
                    Toast.makeText(Registration.this,"Username cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(username.length() < 3 || username.length() > 25)
                {
                    Toast.makeText(Registration.this,"Username must consist more than 3 character and less than 25 characters", Toast.LENGTH_LONG).show();
                }
                else if (username.matches(patternUsername))
                {
                    Toast.makeText(Registration.this,"Username must consist at least 1 alphabet and 1 numeric", Toast.LENGTH_LONG).show();
                }
                else if(pass.isEmpty()){
                    Toast.makeText(Registration.this,"Password cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (pass.length()<6)
                {
                    Toast.makeText(Registration.this,"Password must consists more than 6 characters", Toast.LENGTH_LONG).show();
                }else if (pass.matches(patternPassword))
                {
                    Toast.makeText(Registration.this,"Password must consist at least 1 UP 1 LW 1 Number", Toast.LENGTH_LONG).show();
                }
                else if(conpass.isEmpty()){
                    Toast.makeText(Registration.this,"Confirm your password", Toast.LENGTH_SHORT).show();
                }
                else if (!conpass.equals(conpass))
                {
                    Toast.makeText(Registration.this,"Password does not match", Toast.LENGTH_SHORT).show();
                }
                else if(phone.isEmpty()){
                    Toast.makeText(Registration.this,"Phone cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (phone.matches(patternPhone))
                {
                    Toast.makeText(Registration.this,"Invalid phone number", Toast.LENGTH_LONG).show();
                }
                else if (phone.length() < 10 || phone.length() > 12)
                {
                    Toast.makeText(Registration.this,"Invalid phone number length", Toast.LENGTH_LONG).show();
                }
                else if (DOB.isEmpty())
                {
                    Toast.makeText(Registration.this,"Date of Birth must be filled", Toast.LENGTH_LONG).show();
                }
                else if (!agree.isChecked())
                {
                    Toast.makeText(Registration.this,"You must agree to terms and condition", Toast.LENGTH_LONG).show();
                }else if (rg_gender.getCheckedRadioButtonId() == -1){
                    Toast.makeText(Registration.this,"Please choose your gender", Toast.LENGTH_LONG).show();
                }
                else{
                    //authen
                    int radioID = rg_gender.getCheckedRadioButtonId();
                    rb_gender = findViewById(radioID);
                    gender = rb_gender.getText().toString();

                    jumlahData=users.size()+1;
                    if(jumlahData<10){
                        ID = "US"+"00"+jumlahData;
                    }else if(jumlahData<100){
                        ID = "US"+"0"+jumlahData;
                    }else if(jumlahData>=100){
                        ID = "US"+jumlahData;
                    }else if(jumlahData==999){
                        Toast.makeText(Registration.this,"Can't add more account", Toast.LENGTH_LONG).show();
                    }

                    User u  = new User(username,pass,phone,gender,DOB,ID);
                    users.add(u);

                    Log.i(ID, u.getUsername());

                    Toast.makeText(Registration.this,"Register Success", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Registration.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }


            }

        });



    }


}
