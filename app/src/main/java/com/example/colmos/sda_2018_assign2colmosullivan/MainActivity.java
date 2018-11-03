package com.example.colmos.sda_2018_assign2colmosullivan;
/*
    Created By: Colm O'Sullivan
    Last Edited: 03/11/2018

*/

// Import all required classes
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

        Intent intent = getIntent();
        toAddress = intent.getStringExtra("EMAIL_TO");
        subjectTitle = intent.getStringExtra("EMAIL_SUBJECT");
        messageContent = intent.getStringExtra("EMAIL_COMPOSE");
        //Log.i("EmailIntent", "EmailContents.java to email returned as " + toAddress);
        //Log.i("EmailIntent", "EmailContents.java subject email returned as " + subjectTitle);
        //Log.i("EmailIntent", "EmailContents.java compose email returned as " + messageContent);

        // Capture the layout's TextView and set the string as its text
        if (toAddress != null && subjectTitle != null && messageContent != null){
            emailContentsSet = true;
            TextView textView = findViewById(R.id.ActivityContentsTextView);
            String emailTextViewContents = "To: " + toAddress + "\n" + "Subject: " + subjectTitle + "\n" + "Content: " + messageContent;
            textView.setText(emailTextViewContents);
            Log.i("EmailIntent", "emailContentsSet is: " + emailContentsSet);
        }


    }

    /** Called when the user taps the Send button */
    public void capturePhoto(View view) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        //if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        //}
    }

    public void openGallery(View view) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void callAnActivity(View view) {
        Intent intent = new Intent(this, EmailContents.class);
        startActivity(intent);
    }

    public void sendEmail(View view) {
        if (emailContentsSet) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            //intent.setType("*/*");
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {toAddress});
            intent.putExtra(Intent.EXTRA_SUBJECT, subjectTitle);
            intent.putExtra(Intent.EXTRA_TEXT, messageContent);
            //if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            //}
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        //Log.i(TAG, "The activity is visible and about to be started.");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.i(TAG, "The activity is visible and about to be restarted.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.i(TAG, "The activity is and has focus (it is now \"resumed\")");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.i(TAG, "Another activity is taking focus (this activity is about to be \"paused\")");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.i(TAG, "The activity is no longer visible (it is now \"stopped\")");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.i(TAG, "The activity is about to be destroyed.");
    }
}
