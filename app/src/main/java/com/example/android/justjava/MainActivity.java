/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int quantity = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {



        CheckBox whippedCreamCheckBox = findViewById(R.id.whippedcream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText customerName = findViewById(R.id.customer_name);
        String name = customerName.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);











          String priceMessage =  createOrderSummary(name, price, hasWhippedCream, hasChocolate);

          if(quantity !=0) {

              Intent intent = new Intent(Intent.ACTION_SENDTO);
              intent.setData(Uri.parse("mailto:")); // only email apps should handle this

              intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
              intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
              if (intent.resolveActivity(getPackageManager()) != null) {
                  startActivity(intent);
              }
          }

          else {
              Context context = getApplicationContext();
              CharSequence text = "No Drinks Selected!";
              int duration = Toast.LENGTH_SHORT;

              Toast toast = Toast.makeText(context, text, duration);
              toast.show();
          }







    }






    // This method increments quantity by 1


    public void increment(View view) {

if(quantity<100)
        displayQuantity(quantity += 1);
    }

    // This method decrements quantity by 1 to zero

    public void decrement(View view) {
        if (quantity != 0)

            displayQuantity(quantity -= 1);

        else displayQuantity(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfDrinks) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfDrinks);
    }

    private String createOrderSummary(String customerName, int total,boolean toppingOne, boolean toppingTwo) {
String orderSummary;

        if (quantity !=0)
            return orderSummary = ("Name: " +customerName+"\nWhipped Cream: " +toppingOne +"\nChocolate: " +toppingTwo + "\nQuantity: " + quantity + "\nTotal: $" +total+ "\nThank you!");

        else
           return orderSummary = ("$" + quantity);



    }


    /**
     * Calculates the price of the order.
     *

     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int basePrice = 5;

        if (whippedCream)
        basePrice +=1;

        if (chocolate)
        basePrice +=2;
        return quantity * basePrice;


    }




}
