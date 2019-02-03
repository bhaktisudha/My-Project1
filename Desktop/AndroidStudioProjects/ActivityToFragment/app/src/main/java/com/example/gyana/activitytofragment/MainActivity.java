package com.example.gyana.activitytofragment;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    EditText firstName,LastName;
    Button send;
    public static FragmentManager fragmentManager;
    FrameLayout frameLayout ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText)findViewById(R.id.edt1);
        LastName = (EditText)findViewById(R.id.edt2);
        send = (Button)findViewById(R.id.btn);
        frameLayout = (FrameLayout)findViewById(R.id.container);
        if(findViewById(R.id.container)!= null){
            if(savedInstanceState != null){
                return;
            }
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("firstName", firstName.getText().toString());
                bundle.putString("lastname",LastName.getText().toString());
                BlankFragment myFrag = new BlankFragment();
                myFrag.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.container, myFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


    }
}
