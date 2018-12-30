package gr.uom.adroid.mylocation;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;


public class LocationsActivity extends AppCompatActivity implements Serializable {

    ListView dataList;
    String listID,Lat,Lng;
    ArrayList <String> locationsArray;
    LocationsBD dbLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        listViewer();

    }

    public void deleteDATA(View v){
        int check = dbLocation.deleteData(listID);
        if(check > 0 ){
            Toast.makeText(LocationsActivity.this,"Succesfully deleted",Toast.LENGTH_SHORT).show();
            listViewer();
        }
        else{
            Toast.makeText(LocationsActivity.this,"IDIOT IDIOT IDIOT",Toast.LENGTH_SHORT).show();
        }


    }

    public void openDATA(View v){
        Intent i = new Intent (Intent.ACTION_VIEW);
        String geo = "geo:"+Lat+Lng;
        Log.d("KAPPA", "GEOLOCATION"+geo);
        i.setData(Uri.parse(geo));
        startActivity(i);

    }

    public static void restartActivity(Activity act){
        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();

    }

    public void listViewer(){
        locationsArray = new ArrayList<>();
        dbLocation = new LocationsBD(this);
        Cursor res = dbLocation.getAllData();
        if(res.getColumnCount() == 0 ){
            locationsArray.add("Something happend. Could not load files.");
        }
        else {
            while (res.moveToNext()) {
                locationsArray.add("ID: " +res.getString(0) +" NAME: " +res.getString(1) +
                        "\n||LAT: " + res.getString(2) +", LNG: " + res.getString(3) +"\n");
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.activity_datas,R.id.dataTXT,locationsArray);
        dataList = findViewById(R.id.dataLIST);
        dataList.setAdapter(adapter);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = dataList.getItemAtPosition(position);
                String astring=(String)o;
                String listSTR[] = astring.split(" ");
                listID = listSTR[1];
                Lat = listSTR[4];
                Lng = listSTR[6];
                Log.d("KAPPA", "\nID: "+listID+"\n" +"Lat: "+Lat + "\nLng: "+ Lng);

            }
        });

    }

}

