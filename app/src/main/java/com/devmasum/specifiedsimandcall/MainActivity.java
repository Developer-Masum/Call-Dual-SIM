package com.devmasum.specifiedsimandcall;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.devmasum.specifiedcalldualsim.MasumService;


public class MainActivity extends AppCompatActivity {

    private EditText ussdCodeEditText;
    private Button sendButton;
    private TextView responseTextView;
    private MasumService masumService;
    private Spinner spinnerSim;
    private int sim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        ussdCodeEditText = findViewById(R.id.ussd_code);
        sendButton = findViewById(R.id.btn_send);
        responseTextView = findViewById(R.id.tv_response);
        spinnerSim = findViewById(R.id.spinner_sim);


        // Set up Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sim_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSim.setAdapter(adapter);


        // Handle Spinner item selection
        spinnerSim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // SIM 1 selected
                    sim = 0;
                } else if (position == 1) {
                    // SIM 2 selected
                  sim = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });



        // Initialize USSDService with the activity context
        masumService = new MasumService(this);

        // Set up button click listener
        sendButton.setOnClickListener(view -> {
            String ussdCode = ussdCodeEditText.getText().toString();
            if (ussdCode.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a USSD code", Toast.LENGTH_SHORT).show();
                return;
            }

            // Send the USSD code using the USSDService
            masumService.send(ussdCode,sim);

        });
    }
}
