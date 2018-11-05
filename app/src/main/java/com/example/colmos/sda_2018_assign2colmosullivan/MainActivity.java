package com.example.colmos.sda_2018_assign2colmosullivan;
/*

 */

// Import all classes required for this class
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Boolean emailContentsSet = false; // Global variable (within this class) used to only enable to Send Button once the information has beet retrieved from the explicit intent
    public String toAddress = null; // Global variable (within this class) used to store the To Email Address from the explicit intent
    public String subjectTitle = null; // Global variable (within this class) used to store the Email Subject from the explicit intent
    public String messageContent = null; // Global variable (within this class) used to store the Email Subject from the explicit intent

    // onCreate class for initialising window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intent to copy email information from explicit intent
        Intent intent = getIntent();
        toAddress = intent.getStringExtra("EMAIL_TO"); // Takes value from email field in explicit intent
        subjectTitle = intent.getStringExtra("EMAIL_SUBJECT"); // Takes value from subject field in explicit intent
        messageContent = intent.getStringExtra("EMAIL_COMPOSE"); // Takes value from compose field in explicit intent
        //Log messages to show the information was received successfully
        //Log.i("EmailIntent", "EmailContents.java to email returned as " + toAddress);
        //Log.i("EmailIntent", "EmailContents.java subject email returned as " + subjectTitle);
        //Log.i("EmailIntent", "EmailContents.java compose email returned as " + messageContent);

        // Capture the layout's TextView and set the string as its text
        if (toAddress != null && subjectTitle != null && messageContent != null){ // Ensures the email content is only displayed once the explicit intent is completed
            emailContentsSet = true; // Used to enable the send button
            TextView textView = findViewById(R.id.ActivityContentsTextView);
            String emailTextViewContents = "To: " + toAddress + "\n" + "Subject: " + subjectTitle + "\n" + "Content: " + messageContent;
            textView.setText(emailTextViewContents); // Prints the email information to the MainActivity window
            //Log.i("EmailIntent", "emailContentsSet is: " + emailContentsSet);
        }
    }

    // Intent to open the phones camera app to capture a picture
    // https://developer.android.com/guide/components/intents-common#java
    public void capturePhoto(View view) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        //if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        //}
    }

    // Intent to open the phones gallery to view captured images
    // https://stackoverflow.com/questions/6016000/how-to-open-phones-gallery-through-code
    // https://stackoverflow.com/questions/16928727/open-gallery-app-from-android-intent/23821227
    public void openGallery(View view) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // Intent to open a second activity allowing the used to enter details for sending an email
    // https://developer.android.com/training/basics/firstapp/starting-activity#java
    public void callAnActivity(View view) {
        Intent intent = new Intent(this, EmailContents.class);
        startActivity(intent);
    }

    // Intent to allow the user to send the email as composed in the explicit intent
    // https://developer.android.com/guide/components/intents-common#java
    /*
       Issues were encountered here when trying to add values to the EXTRA_EMAIL variable
       This was due to me attempting to add the email address as a single string rather than a value in an array
       I found the solution at the following webpage: https://stackoverflow.com/questions/9097080/intent-extra-email-not-populating-the-to-field
    */
    public void sendEmail(View view) {
        if (emailContentsSet) { // If statement to ensure the method only becomes functional once the fields have been populated
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {toAddress}); // The email address entered
            intent.putExtra(Intent.EXTRA_SUBJECT, subjectTitle); // The subject of the email entered
            intent.putExtra(Intent.EXTRA_TEXT, messageContent); // The content of the email entered
            startActivity(intent);
        }
    }
}
