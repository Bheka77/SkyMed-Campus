package com.example.ma3;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button hivButton = findViewById(R.id.hiv);
        Button influenzaButton = findViewById(R.id.influenza);
        Button pinkeyeButton = findViewById(R.id.pinkeye);
        Button tbButton = findViewById(R.id.tb);
        Button familyPlanningButton = findViewById(R.id.pneumonia);
        Button backhome = findViewById(R.id.backhome);

        hivButton.setOnClickListener(this);
        influenzaButton.setOnClickListener(this);
        pinkeyeButton.setOnClickListener(this);
        tbButton.setOnClickListener(this);
        familyPlanningButton.setOnClickListener(this);
        backhome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.hiv) {
            intent = new Intent(MainActivity2.this, HIVActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.influenza) {
            intent = new Intent(MainActivity2.this, InfluenzaActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.pinkeye) {
            intent = new Intent(MainActivity2.this, PinkeyeActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tb) {
            intent = new Intent(MainActivity2.this, TBActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.pneumonia) {
            intent = new Intent(MainActivity2.this, PneumoniaActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.backhome) {
            intent = new Intent(MainActivity2.this, UserHome.class);
            startActivity(intent);
        }
    }
}




/*import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String[] sicknesses = {
            "Flu - Symptoms: fever, cough, sore throat; Treatment: rest, fluids, medication",
            "Common Cold - Symptoms: runny nose, sneezing, sore throat; Treatment: rest, fluids, medication",
            "Stomach Flu - Symptoms: nausea, vomiting, diarrhea; Treatment: rest, fluids, bland diet"
            // Add more sicknesses as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView sicknessListView = findViewById(R.id.sicknessListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sicknesses);
        sicknessListView.setAdapter(adapter);
    }
}*/

