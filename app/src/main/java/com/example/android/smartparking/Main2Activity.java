package com.example.android.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    // define the global variable

    TextView question1;
    // Add button Move to Activity

    Button next_Activity_button;
    Button next_Activity_button1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(Main2Activity.this,"Firebase Connection Success",Toast.LENGTH_LONG).show();

        // by ID we can use each component which id is assign in xml file
        // use findViewById() to get the Button
        next_Activity_button = (Button)findViewById(R.id.first_activity_button);
        question1 = (TextView)findViewById(R.id.question1_id);
        next_Activity_button1 = (Button)findViewById(R.id.second_activity_button);

        // In question1 get the TextView use by findViewById()
        // In TextView set question Answer for message
        question1.setText("Q 1 - How to pass the data between activities in Android?\n"+ "\n"
                + "Ans- Intent");

        // Add_button add clicklistener
        next_Activity_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {

                // Intents are objects of the android.content.Intent type. Your code can send them
                // to the Android system defining the components you are targeting.
                // Intent to start an activity called SecondActivity with the following code:

                Intent intent = new Intent(Main2Activity.this, MainActivity.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });
        next_Activity_button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View x)
        {

            // Intents are objects of the android.content.Intent type. Your code can send them
            // to the Android system defining the components you are targeting.
            // Intent to start an activity called SecondActivity with the following code:

            Intent intent = new Intent(Main2Activity.this, Main3Activity.class);

            // start the activity connect to the specified class
            startActivity(intent);
        }
    });
    }
}
