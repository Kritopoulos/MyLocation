package gr.uom.adroid.mylocation;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager = CallbackManager.Factory.create();
    LoginButton loginButton;
    Button continuebtn;

    int PERMISSION_ALL = 97;
    String[] PERMISSIONS = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(!hasPermissions(MainActivity.this, PERMISSIONS ) ){
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, PERMISSION_ALL);
        }
        //FACEBOOK LOG IN
        loginButton=findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "Successfully loged in!",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Log in failed!",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(MainActivity.this, "An error was occured!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //SIGA MHN BALW KI ALLA SXOLIA
        continuebtn = findViewById(R.id.ContinueBTN);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(hasPermissions(MainActivity.this, PERMISSIONS ) ){
                    Intent newIntent = new Intent(MainActivity.this, MainPageActivity.class);
                    startActivity(newIntent);
                }
                else{
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Permission needed")
                            .setMessage("You need to add all the permissions to move further")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override

                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, PERMISSION_ALL);
                                }
                            })
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
            }
        });
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
