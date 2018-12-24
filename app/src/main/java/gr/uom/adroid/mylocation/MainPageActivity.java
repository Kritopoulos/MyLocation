package gr.uom.adroid.mylocation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPageActivity extends AppCompatActivity {
    Button myLocationsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        myLocationsbtn = findViewById(R.id.myLocationsBTN);
        myLocationsbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent LocationsIntent = new Intent(MainPageActivity.this, LocationsActivity.class);
                startActivity(LocationsIntent);
            }
        });
    }
    public void favoriteLocationsBTN(View v){
        Intent LocationsIntent = new Intent(MainPageActivity.this, FavoriteLocationsActivity.class);
        startActivity(LocationsIntent);
    }


    public void getLocationBTN(View v){


    }

}
