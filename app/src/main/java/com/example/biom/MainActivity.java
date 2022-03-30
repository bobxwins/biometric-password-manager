package com.example.biom ;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    EncryptionMenu encryptionMenu = new EncryptionMenu();
    HomeMenu homeMenu = new HomeMenu();
    DecryptionMenu decryptionMenu = new DecryptionMenu();
    private static CancellationSignal cancellationSignal = null;
    private BiometricPrompt.AuthenticationCallback authenticationCallback;
    static Activity thisActivity = null;

    BottomNavigationView bottomNavigationView;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.encrypt:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, encryptionMenu).commit();
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeMenu).commit();
                return true;

            case R.id.decrypt:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, decryptionMenu).commit();
                return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)

    @Override
    protected void
    onCreate(@Nullable  Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        thisActivity = this;

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.encrypt);


        authenticationCallback = new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(
                    int errorCode, CharSequence errString) {

                super.onAuthenticationError(errorCode, errString);
                notifyUser("Authentication Error : " + errString);
            }


            @Override
            public  void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {

                try {
                    super.onAuthenticationSucceeded(result);

                    notifyUser("Authentication Succeeded");

                    String dir = MainActivity.this.getExternalFilesDir(null).getAbsolutePath();

                    AndroidEncrypt androidEncrypt = new AndroidEncrypt();

                    //  androidEncrypt.SymmetricKeyGenerator(dir+"/");

                    AndroidDecrypt androidDecrypt = new AndroidDecrypt();
                    androidDecrypt.DecryptFile(dir + "/");

                } catch (Exception e) {

                }
            }
        };
        checkBiometricSupport();


    }


    // kadles når autentificering stoppes af brugeren
    public static CancellationSignal getCancellationSignal()
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
    public static void notifyUser(String message)
    {
        Toast.makeText(thisActivity, message, Toast.LENGTH_SHORT).show();
    }


}
