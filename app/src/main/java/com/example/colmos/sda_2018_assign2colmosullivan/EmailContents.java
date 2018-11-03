package com.example.colmos.sda_2018_assign2colmosullivan;

// Import all required classes
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EmailContents extends AppCompatActivity {

    // onCreate class for initialising window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_contents);
    }

    // sendMessage class to return the message contents back to the initial window
    public void sendMessage(View view) {
        Intent sendMessageIntent = new Intent(this, MainActivity.class);

        // Saves the value in the To field
        EditText etEmailTo = findViewById(R.id.EmailTo);
        String sEmailTo = etEmailTo.getText().toString();
        sendMessageIntent.putExtra("EMAIL_TO", sEmailTo);
        //Log.i("EmailContentsJava", "To String contains: " + sEmailTo);

        // Saves the value in the Subject field
        EditText etEmailSubject = findViewById(R.id.EmailSubject);
        String sSubjectEmail = etEmailSubject.getText().toString();
        sendMessageIntent.putExtra("EMAIL_SUBJECT", sSubjectEmail);
        //Log.i("EmailContentsJava", "Subject String contains: " + sSubjectEmail);

        // Saves the value in the Compose field
        EditText etEmailCompose = findViewById(R.id.EmailCompose);
        String sComposeEmail = etEmailCompose.getText().toString();
        sendMessageIntent.putExtra("EMAIL_COMPOSE", sComposeEmail);
        //Log.i("EmailContentsJava", "Compose String contains: " + sComposeEmail);

        //if (etEmailTo != null && etEmailSubject != null && etEmailCompose != null){
            startActivity(sendMessageIntent);
        //}
    }
}
