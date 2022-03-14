package com.example.biom;
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.bluetooth.BluetoothAdapter;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity  {

    @Override

    protected void onCreate(Bundle savedInstanceState)   {


        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        setContentView(R.layout.activity_main);

        // storing ID of the button
        // in a variable
        Button button = (Button)findViewById(R.id.button);

        // operations to be performed
        // when user tap on the button
        if (button != null) {
            button.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it)  {
                    try {
                        fileStore();
                        // displaying a toast message
                        Toast.makeText((Context) MainActivity.this, R.string.message, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        //  Block of code to handle errors
                    }
                }
            }));

        }

    }
    public void fileStore() throws Exception {

        byte[] testput = "This is new test data".getBytes(StandardCharsets.UTF_8);
        test.FileUtils.write("/storage/emulated/0/Android/data/com.example.myapp/files/myfile.txt", testput);
        System.out.println(test.FileUtils.readAllBytes("/storage/emulated/0/Android/data/com.example.myapp/files/myfile.txt").toString());


        String dir = "/storage/emulated/0/Android/data/com.example.myapp/files/myfile2.txt";
        test.FileUtils.write(dir, "dette er min fil".getBytes(StandardCharsets.UTF_8));


    }

}

