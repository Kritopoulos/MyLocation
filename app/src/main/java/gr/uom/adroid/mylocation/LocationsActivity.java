package gr.uom.adroid.mylocation;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LocationsActivity extends AppCompatActivity {

    TextView dataTXT;
    private  String locations;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        dataTXT = findViewById(R.id.dataTXT);
        Log.d("KAPPA","All good till here");
        Intent intent = getIntent();
        String bdInfo = intent.getStringExtra("intentDB");
        dataTXT.setText(bdInfo);

    }


}

