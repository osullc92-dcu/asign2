package com.example.colmos.sda_2018_assign2colmosullivan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EmailContents extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_contents);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText emailTo = findViewById(R.id.EmailTo);
        String toEmail = emailTo.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, toEmail);

        EditText emailSubject = findViewById(R.id.EmailSubject);
        String subjectEmail = emailSubject.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, subjectEmail);

        /*EditText emailCompose = findViewById(R.id.EmailCompose);
        String composeEmail = emailCompose.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, composeEmail);*/

        startActivity(intent);
    }
}
