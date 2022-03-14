package com.example.biom ;
import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import test.FileUtils;

public class MainActivity extends AppCompatActivity {


    private CancellationSignal cancellationSignal = null;


    private BiometricPrompt.AuthenticationCallback authenticationCallback;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void
    onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authenticationCallback = new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(
                    int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser("Authentication Error : " + errString);
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                fileStore();
                notifyUser("Authentication Succeeded");

            }
        };

        checkBiometricSupport();
        Button button = (Button)findViewById(R.id.button);
        // biometrisk dialog skabes når button trykkes
        button.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view)
            {
                // This creates a dialog of biometric
                // auth and it requires title , subtitle
                // , and description In our case there
                // is a cancel button by clicking it, it
                // will cancel the process of
                // fingerprint authentication
                BiometricPrompt biometricPrompt = new BiometricPrompt
                        .Builder(getApplicationContext())
                        .setTitle("Title of Prompt")
                        .setSubtitle("Subtitle")
                        .setDescription("Uses FP")
                        .setNegativeButton("Cancel", getMainExecutor(), new DialogInterface.OnClickListener() {
                            @Override
                            public void
                            onClick(DialogInterface dialogInterface, int i)
                            {
                                notifyUser("Authentication Cancelled");
                            }
                        }).build();



                // starter   authenticationCallback i
                // mainExecutor
                biometricPrompt.authenticate(
                        getCancellationSignal(),
                        getMainExecutor(),
                        authenticationCallback);
            }
        }));
    }

    // kadles når autentificering stoppes af brugeren
    private CancellationSignal getCancellationSignal()
    {
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(
                new CancellationSignal.OnCancelListener() {
                    @Override public void onCancel()
                    {
                        notifyUser("Authentication was Cancelled by the user");
                    }
                });
        return cancellationSignal;
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private Boolean checkBiometricSupport()
    {
        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
        if (!keyguardManager.isDeviceSecure()) {
            notifyUser("Fingerprint Autentificering er ikke slået til i indstillinger");
            return false;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC)!= PackageManager.PERMISSION_GRANTED) {
            notifyUser("Fingerprint Autentificering tilladelse er ikke slået til");
            return false;
        }
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            return true;
        }
        else
            return true;
    }

    // this is a toast method which is responsible for
    // showing toast it takes a string as parameter
    private void notifyUser(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void fileStore()  {
        String dir = this.getExternalFilesDir(null).getAbsolutePath();

        byte[] testput = "User is verified".getBytes(StandardCharsets.UTF_8);

        System.out.println(dir);
        test.FileUtils.write(dir+"/myfile.txt", testput);
        System.out.println(test.FileUtils.readAllBytes(dir+"/myfile.txt").toString());

        // en bruger opretter sin symmetriske nøgle. nøglen og alt andet gemmes på telefonen, og ikke brugerens computer.
        // for at kunne dekryptere indholdet skal brugeren autentifiere sig via appen. så krypterings nøglen puttes i en vault som bruger kun har adgand til ved autentificering
        // gem kryptofilerne på telefonen
    }
}
