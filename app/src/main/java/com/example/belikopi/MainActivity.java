package com.example.belikopi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void increment(View view){//perintah tombol tambah
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama:"+name);

        CheckBox whippedcreamChekBox= (CheckBox) findViewById(R.id.WhippedCream_checkbox);
        boolean haswhippedcream=whippedcreamChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedcream:"+haswhippedcream);

        CheckBox chocolateChekBox= (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean haschocolate=chocolateChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedcream:"+haschocolate);

        CheckBox wafflesChekBox= (CheckBox) findViewById(R.id.Waffles_checkbox);
        boolean haswaffles=wafflesChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has waffles:"+haswaffles);

        int price=calculateprice(haswhippedcream,haschocolate,haswaffles);//memanggil method jumlah harga
        String pricemessage=createOrderSummary(price,name,haswhippedcream,haschocolate,haswaffles);

        displayMessage(pricemessage);
    }
    private int calculateprice(boolean addwhipedcream,boolean addchocolate,boolean addwaffles){//jumlah pesanan * harga
        int harga=5000;

        if(addwhipedcream){
            harga=harga+1000;//harga tambahan toping
        }

        if(addchocolate){
            harga=harga+2000;
        }

        if(addwaffles){
            harga=harga+3000;
        }
        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean addChocolate, boolean addWhippedCream, boolean addWaffles) {//hasil pemesanan
        String pricemessage=" Name : "+name;
        pricemessage+="\n Add Cream         : " +addWhippedCream;
        pricemessage+="\n Add Chocolatos : " +addChocolate;
        pricemessage+="\n Add Waffle     : " +addWaffles;
        pricemessage+="\n Total Order     : " +quantity;
        pricemessage+="\n Grand Total    : Rp " +price;
        pricemessage+="\n Have a Nice Day !";
        return  pricemessage;
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}