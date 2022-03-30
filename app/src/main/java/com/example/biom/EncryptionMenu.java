package com.example.biom;

import static com.example.biom.MainActivity.notifyUser;

import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EncryptionMenu extends Fragment {


    AppNotifications appNotifications = new AppNotifications();
    public EncryptionMenu() {
        // require a empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first,
                container, false);
        appNotifications.notification();

        appNotifications.authenticationCallback = new BiometricPrompt.AuthenticationCallback() {


            @Override
            public void onAuthenticationError(
                    int errorCode, CharSequence errString) {

                super.onAuthenticationError(errorCode, errString);

                 MainActivity.notifyUser("Authentication Error : " + errString);
            }
            @Override
            public  void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {

                try {
                    System.out.println("why");
                    super.onAuthenticationSucceeded(result);

                    notifyUser("Authentication Succeeded");

                    String dir = MainActivity.thisActivity.getExternalFilesDir(null).getAbsolutePath()+"/";

                    System.out.println(dir+"this is dior dior");
                    AndroidEncrypt androidEncrypt = new AndroidEncrypt();

                     androidEncrypt.SymmetricKeyGenerator(dir);

                } catch (Exception e) {

                }
            }
        };


            Button button = (Button) view.findViewById(R.id.encryptButton);
        button.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
             @Override
            public   void onClick(View v) {
                System.out.println("lolz");
                // This creates a dialog of biometric
                // auth and it requires title , subtitle
                // , and description In our case there
                // is a cancel button by clicking it, it
                // will cancel the process of
                // fingerprint authentication
                BiometricPrompt biometricPrompt = new BiometricPrompt
                        .Builder(getActivity().getApplicationContext())
                        .setTitle("Title of Prompt")
                        .setSubtitle("Subtitle")
                        .setDescription("Uses FP")
                        .setNegativeButton("Cancel", getActivity().getMainExecutor(), new DialogInterface.OnClickListener() {
                            @Override
                            public void
                            onClick(DialogInterface dialogInterface, int i)
                            {
                               MainActivity.notifyUser("Authentication Cancelled");
                            }
                        }).build();

                // starter   authenticationCallback i
                // mainExecutor

                biometricPrompt.authenticate(
                        appNotifications.getCancellationSignal(),
                        getActivity().getMainExecutor(),
                        appNotifications.authenticationCallback);
            }
        }));

    return view;}
}




