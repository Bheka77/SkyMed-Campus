package com.example.ma3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SymtomActivity extends AppCompatActivity {
    private EditText symptomEditText;
    private Button checkButton, backButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptomchecker);

        symptomEditText = findViewById(R.id.symptom_edit_text);
        checkButton = findViewById(R.id.check_button);
        backButton = findViewById(R.id.backsymtom);
        resultTextView = findViewById(R.id.result_text_view);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symptom = symptomEditText.getText().toString();
                String result = checkSymptom(symptom);
                resultTextView.setText(result);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SymtomActivity.this, UserHome.class);
                startActivity(intent);
            }
        });
    }

    private String checkSymptom(String symptom) {
        if (symptom.equals("Headache")) {
            return "Influenza (Flue)";
        } else if (symptom.equals("Fever")) {
            return "Influenza (Flue)";
        } else if (symptom.equals("Swollen lymph nodes") || symptom.equals("Rash") || symptom.equals("Mouth ulcers") || symptom.equals("Chronic diarrhea") || symptom.equals("Weight loss")) {
            return "HIV";
        } else if (symptom.equals("Redness in eye") || symptom.equals("Increased tearing") || symptom.equals("Eye discharge") || symptom.equals("Itchy eyes") || symptom.equals("Burning eyes") || symptom.equals("Blurred vision") || symptom.equals("Sensitivity to Light")) {
            return "Pink Eye";
        } else if (symptom.equals("Cough with phlegm")|| symptom.equals("Cough with pus")|| symptom.equals("Difficulty in breathing") || symptom.equals("Nausea")|| symptom.equals("Vomiting")|| symptom.equals("Rapid breathing")|| symptom.equals("Chest pains")) {
            return "Pneumonia";
        } else if (symptom.equals("Chest pain")|| symptom.equals("Coughing blood") || symptom.equals("Coughing sputum")|| symptom.equals("Night sweats")|| symptom.equals("Weight")) {
            return "Tuberculosis";
        } else if (symptom.equals("Cough")|| symptom.equals("Chills")|| symptom.equals("Sore throat")|| symptom.equals("Running nose")|| symptom.equals("Stuffy nose")) {
            return "Influenza (Flue)";
        } else {
            return "Unknown Symptom";
        }
    }
}
