package com.example.coffeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    // creating array list of coffee types
    ArrayList<coffee> list = new ArrayList<>();
    ArrayList<String> coffeeName =  new ArrayList<>();

    // creating needed instance of components
    TextView total, qty;
    Spinner spinner;
    RadioButton rbSmall, rbMedium, rbLarge;
    CheckBox cbCream, cbSuger, cbMilk;
    SeekBar sb;
    Button order;
    public static double originalPrice=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // calling fill data function so that array will be filled
        fillData();
        // creating connection with design
        total = findViewById(R.id.total);
        spinner = findViewById(R.id.spinner);
        rbSmall = findViewById(R.id.rbSmall);
        rbMedium = findViewById(R.id.rbMedium);
        rbLarge = findViewById(R.id.rbLarge);
        cbCream=findViewById(R.id.checkBox);
        cbSuger=findViewById(R.id.checkBox2);
        cbMilk=findViewById(R.id.checkBox3);
        sb=findViewById(R.id.seekBar);
        order=findViewById(R.id.button);
        qty=findViewById(R.id.tvQty);

        // creating event for each raioButton
        rbSmall.setOnClickListener(this);
        rbMedium.setOnClickListener(this);
        rbLarge.setOnClickListener(this);

        //creating event for the chechboxes
        cbCream.setOnCheckedChangeListener(new ChkBoxEvent());
        cbMilk.setOnCheckedChangeListener(new ChkBoxEvent());

        order.setOnClickListener(this);

        //creating the seekBar event
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                qty.setText(String.valueOf(i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        // filling the spinner
        ArrayAdapter i = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,coffeeName);
        spinner.setAdapter(i);
        spinner.setOnItemSelectedListener(this);


        // seting total to first value of the dropDown Menu
        total.setText(Double.toString(list.get(0).getCoffeePrice())); // changing double to String type
    }

    public void fillData(){
        list.add(new coffee("Tea", 1.5));
        list.add(new coffee("Simple Coffee", 1.7));
        list.add(new coffee("Hot Chocolate",1.3));
        list.add(new coffee("cappacinno", 2.2));
        list.add(new coffee("Espresso",0.9));
        for (int i = 0;i < list.size(); i++){
            coffeeName.add(list.get(i).getCoffeeName());  // filling the arraylist with the name of coffee types
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rbSmall.setChecked(true);
        total.setText(Double.toString(list.get(position).getCoffeePrice()));// changing double to String type
        originalPrice=list.get(position).getCoffeePrice();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        double tot = originalPrice;


        switch (v.getId()){
            case R.id.rbSmall:
                tot = originalPrice ;
                break;
            case R.id.rbMedium:
                tot = 1.5 * originalPrice ;
                break;
            case R.id.rbLarge:
                tot = 2.0 * originalPrice ;
            case R.id.button:
                double amount = Double.parseDouble(total.getText().toString());
                int quantity = Integer.parseInt(qty.getText().toString());
                amount *=quantity;
                tot = amount * 1.13;


        }
        total.setText(String.format("%.2f",tot));


    }
    private class ChkBoxEvent implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            double tot=Double.parseDouble(total.getText().toString());
            if (compoundButton.getId()==R.id.checkBox)
                if(cbCream.isChecked())
                    tot+=0.5;
                else
                    tot-=0.5;
            if (compoundButton.getId()==R.id.checkBox3)
                if(cbMilk.isChecked())
                    tot+=0.25;
                else
                    tot-=0.25;
            total.setText(String.format("%.2f",tot));



        }
    }

}

