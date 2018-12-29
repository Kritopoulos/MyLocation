package gr.uom.adroid.mylocation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLConnection;
import java.security.PublicKey;

public class MainPageActivity extends AppCompatActivity {

    private static TextView lattxt,lngtxt,iptxt;
    IPhandler ip;
    URLconection urlGetCords;
    private static String ipconection,incomeLNG,incomeLAT,dataFromDB;

    //database
    LocationsBD dbLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        lattxt = findViewById(R.id.latTXT);
        lngtxt = findViewById(R.id.lngTXT);
        iptxt = findViewById(R.id.ipTXT);

        dbLocation = new LocationsBD(this);
        ip = new IPhandler();
        ip.execute();

    }

    public void myLocationsBTN(View v){
        Log.d("KAPPA", "BUTTON PRESSED");
        Cursor res = dbLocation.getAllData();
        if(res.getColumnCount() == 0 ){
            dataFromDB ="Something happend. Could not load files.";
            Log.d("KAPPA", "ERROR");
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID : " + res.getString(0));
                buffer.append(" ||NAME : " + res.getString(1));
                buffer.append(" ||LAT : " + res.getString(2));
                buffer.append(" ||LNG : " + res.getString(3) + "\n");
            }
            dataFromDB = buffer.toString();
            Log.d("KAPPA", "data from buffer:  " +"\n"+ buffer.toString());
        }
        Intent intent = new Intent(MainPageActivity.this,LocationsActivity.class);
        intent.putExtra("intentDB",dataFromDB);
        startActivity(intent);

    }

    public void favoriteLocationsBTN(View v){
        Intent LocationsIntent = new Intent(MainPageActivity.this, FavoriteLocationsActivity.class);
        startActivity(LocationsIntent);
    }

    public void getLocationBTN(View v){
        urlGetCords = new URLconection(ipconection);
        urlGetCords.execute();
    }

    public void SaveLocationBTN(View v){
        //NEED TO GIVE A NAME TO LOCATION.
        boolean isInserted = dbLocation.insertData("PLACE NAME",incomeLAT.toString(),incomeLNG.toString());

        if (isInserted == true) {
            Toast.makeText(MainPageActivity.this, "Succesfully saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainPageActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void setIP(String astring){
        ipconection = astring;
        iptxt.setText("IP: " + astring);
    }

    public  void setLAT(String astring){
        incomeLAT = astring;
        lattxt.setText("Latitude: " +astring);
    }
    public  void setLNG(String astring){
        incomeLNG = astring;
        lngtxt.setText("Longitude: "+astring);
    }
}

