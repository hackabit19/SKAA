package com.example.android.smartparking;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.android.smartparking.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int quantity1 =0;
    Member member;
    Button but;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        but=findViewById(R.id.proc);
        final DatabaseReference reff;
        member=new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text=findViewById(R.id.name_field);
                String value=text.getText().toString();
                EditText text1=findViewById(R.id.phone_number);
                String value1=text1.getText().toString();
                EditText text2=findViewById(R.id.email_id);
                String value2=text2.getText().toString();
                final TextView textView = findViewById(R.id.index);
                final TextView textView1 = findViewById(R.id.index1);


                final RadioGroup radioGroup = findViewById(R.id.radio);
                final RadioGroup radioGroup1 = findViewById(R.id.radio1);
                final RadioGroup radioGroup2 = findViewById(R.id.ampm);
                int checkedRadio = radioGroup.getCheckedRadioButtonId();
                RadioButton checkedRadioButton = findViewById(checkedRadio);
                String checkedBox = checkedRadioButton.getText().toString();
                int checkedRadio1 = radioGroup1.getCheckedRadioButtonId();
                RadioButton checkedRadioButton1 = findViewById(checkedRadio1);
                String checkedBox1 = checkedRadioButton1.getText().toString();
                int checkedRadio2 = radioGroup2.getCheckedRadioButtonId();
                RadioButton checkedRadioButton2 = findViewById(checkedRadio2);
                String checkedBox2 = checkedRadioButton2.getText().toString();

                String hh = Integer.toString(quantity1);
                String mm = Integer.toString(quantity);

                String car=value;
                String ph=value1;
                String em=value2;
                String vty=checkedBox1;
                String loca=checkedBox;
                member.setCarno(car);
                member.setPhno(ph);
                member.setEmail(em);
                member.setVt(vty);
                member.setLoc(loca);
                member.setHour(hh);
                member.setMinute(mm);
                member.setUnit(checkedBox2);
                reff.push().setValue(member);
                Toast.makeText(MainActivity.this,"Your Booking Will be Confirmed Soon",Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /*int j= calculatePrice(quantity);*/
        String priceMessage="";
        EditText text=findViewById(R.id.name_field);
        String value=text.getText().toString();
        EditText text2=findViewById(R.id.email_id);
        String value2=text2.getText().toString();
        EditText text1=findViewById(R.id.phone_number);
        String value1=text1.getText().toString();
        final TextView textView = findViewById(R.id.index);
        final TextView textView1 = findViewById(R.id.index1);


        final RadioGroup radioGroup = findViewById(R.id.radio);
        final RadioGroup radioGroup1 = findViewById(R.id.radio1);
        final RadioGroup radioGroup2 = findViewById(R.id.ampm);
        int checkedRadio = radioGroup.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = findViewById(checkedRadio);
        String checkedBox = checkedRadioButton.getText().toString();
        int checkedRadio1 = radioGroup1.getCheckedRadioButtonId();
        RadioButton checkedRadioButton1 = findViewById(checkedRadio1);
        String checkedBox1 = checkedRadioButton1.getText().toString();
        int checkedRadio2 = radioGroup2.getCheckedRadioButtonId();
        RadioButton checkedRadioButton2 = findViewById(checkedRadio2);
        String checkedBox2 = checkedRadioButton2.getText().toString();

        if (quantity <=9)
            priceMessage="Vehicle No: "+value+"\nEmail id: "+value2+"\nPhone Number: "+value1+"\nVehicle Type: "+checkedBox1+"\nTime of Arrival: "+ quantity1+ " : 0"+ quantity+" "+checkedBox2+"\n"+checkedBox ;
        else
            priceMessage="Vehicle No: "+value+"\nEmail id: "+value2+"\nPhone Number: "+value1+"\nVehicle Type: "+checkedBox1+"\nTime of Arrival: "+ quantity1+ " :"+ quantity+" "+checkedBox2+"\n"+checkedBox ;
        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:bauxtszw@mailparser.io")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Park It Right: Request "+value);
        intent.putExtra(Intent.EXTRA_TEXT, "Team Park It Right,\n\n"+priceMessage+"\n");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void increment1(View view) {
        if(quantity1>=12)
        {return;}
        quantity1++;

        display1(quantity1);
       /* displayPrice(calculatePrice(quantity1))*/;
    }
    public void decrement1(View view) {
        if(quantity1<=0)
        {return;}
        quantity1--;
        display1(quantity1);
       /* displayPrice(calculatePrice(quantity1))*/;
    }
    public void increment(View view) {

        if(quantity>=45)
        {return;}

        quantity=quantity+15;
        display(quantity);
       /* displayPrice(calculatePrice(quantity))*/;
    }
    public void decrement(View view) {
        if(quantity<=0)
        {return;}
        quantity=quantity-15;
        display(quantity);
       /* displayPrice(calculatePrice(quantity))*/;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.minute_text_view);
        quantityTextView.setText("" + number);

    }
    private void display1(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.hour_text_view1);
        quantityTextView.setText("" + number);

    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView SummaryTextView = (TextView) findViewById(R.id.ordersummary_text_view);
        SummaryTextView.setText(message);
    }

    /**
     * Method to calculate price
     */
    /*private int calculatePrice(int quantity1) {
        int price= (quantity1*5);
        displayPrice(price);
        return price;
         }*/


    }
