package com.example.projectlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectlab.Model.User;

import static com.example.projectlab.Storage.UserStorage.globaluserID;
import static com.example.projectlab.Storage.UserStorage.users;

public class MainActivity extends AppCompatActivity {

    EditText etUser, etPass;
    Button btnLogin;
    TextView tvReg;
    int flag=0;
    int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);

        btnLogin = findViewById(R.id.btnLogin);
        tvReg = findViewById(R.id.tvReg);

        User u = new User("Martin1","Martin1","1231231231","Male","12/12/1999","US001");
        users.add(u);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, pass;

                username = etUser.getText().toString();
                pass = etPass.getText().toString();

                if(username.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Username Required", Toast.LENGTH_SHORT).show();
                }
                else if (pass.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Password Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    //authen

                    for(int i=0; i < users.size(); i++){
                        Log.i("asd","flag");
                        if(users.get(i).getUsername().compareTo(username) == 0 && users.get(i).getPassword().compareTo(pass) == 0){
                            flag=1;
                            p = i;
                            break;
                        }else{
                            flag=0;
                        }
                    }

                    if(flag == 1){

                        Toast.makeText(MainActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,KosListForm.class);
                        globaluserID = users.get(p).getID();
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this,"Incorrect Username/Password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Registration.class);
                startActivity(i);
                finish();
            }
        });
    }


}
