package com.example.colmos.sda_2018_assign2colmosullivan;
/*

 */

// Import all classes required for this class
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmailContents extends AppCompatActivity {

    // onCreate class for initialising window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_contents);
    }

    public void sendMessage(View view) {

        Intent sendMessageIntent = new Intent(this, MainActivity.class);

        // Saves the value in the To field
        EditText etEmailTo = findViewById(R.id.EmailTo);
        String sEmailTo = etEmailTo.getText().toString();
        sendMessageIntent.putExtra("EMAIL_TO", sEmailTo);
        // Log to show the email address entered
        //Log.i("EmailContentsJava", "To String contains: " + sEmailTo);

        int atSymbolLocation = sEmailTo.indexOf('@'); // Find if there is an @ symbol in the email address entered
        int finalStopSymbolLocation = sEmailTo.lastIndexOf('.'); // Find if there is an . symbol in the email address entered
        int emailLength = sEmailTo.length(); // Get the length of the email address

        // Logs to show the values for these integers
        //Log.i("EmailContentsJava", "@ symbol is located at: " + atSymbolLocation);
        //Log.i("EmailContentsJava", "Final . symbol is located at: " + finalStopSymbolLocation);
        //Log.i("EmailContentsJava", "Length of email is: " + emailLength);

        // Saves the value in the Subject field
        EditText etEmailSubject = findViewById(R.id.EmailSubject);
        String sSubjectEmail = etEmailSubject.getText().toString();
        sendMessageIntent.putExtra("EMAIL_SUBJECT", sSubjectEmail);
        // Log to show the subject entered
        //Log.i("EmailContentsJava", "Subject String contains: " + sSubjectEmail);

        // Saves the value in the Compose field
        EditText etEmailCompose = findViewById(R.id.EmailCompose);
        String sComposeEmail = etEmailCompose.getText().toString();
        sendMessageIntent.putExtra("EMAIL_COMPOSE", sComposeEmail);
        // Log to show the email content entered
        //Log.i("EmailContentsJava", "Compose String contains: " + sComposeEmail);

        // Initialisation of one toast to inform the user if all fields are not filled in or if an invalid email address is detected
        int duration = Toast.LENGTH_SHORT;
        CharSequence fillAllFields = getResources().getString(R.string.fill_all_fields);
        CharSequence notValidEmail = getResources().getString(R.string.invalid_email);
        Toast toastFillAllFields = Toast.makeText(this, fillAllFields, duration);
        Toast toastNotValidEmail = Toast.makeText(this, notValidEmail, duration);

        // Checks if any of the fields are blank and a toast to tell the user that all fields are not populated
        if (sEmailTo.equals("") || sSubjectEmail.equals("") || sComposeEmail.equals("")){
            toastFillAllFields.show();
            /*
             Checks to ensure the following:
             The address contains an @ symbol and it is not at the start
             There is at least one character between the @ and the last . symbols
             The . symbol is not the last character of the email address
             A toast stating that the email address is not valid is displayed if any of these are flagged
            */
        } else if ( (atSymbolLocation < 1) || ((atSymbolLocation + 1) >= finalStopSymbolLocation) || (finalStopSymbolLocation >= emailLength - 1)) {
            toastNotValidEmail.show();
            // Returns the values to the main activity if the email address is valid and all fields are populated
        } else {
            startActivity(sendMessageIntent);
        }
    }
}
