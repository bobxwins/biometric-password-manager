package com.example.biom;

import static com.example.biom.MainActivity.notifyUser;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class AppNotifications extends Fragment {

    public static BiometricPrompt.AuthenticationCallback authenticationCallback;
    public static CancellationSignal cancellationSignal = null;

    public AppNotifications() {

    }


    public static CancellationSignal getCancellationSignal() {
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(
                new CancellationSignal.OnCancelListener() {
                    @Override
                    public void onCancel() {
                        notifyUser("Authentication was Cancelled by the user");
                    }
                });
        return cancellationSignal;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void notification() {
/*
        */

        authenticationCallback = new BiometricPrompt.AuthenticationCallback() {


            @RequiresApi(Build.VERSION_CODES.M)
            private Boolean checkBiometricSupport() {

                KeyguardManager keyguardManager = (KeyguardManager) getActivity().getSystemService(Context.KEYGUARD_SERVICE);
                if (!keyguardManager.isDeviceSecure()) {
                    notifyUser("Fingerprint Autentificering er ikke slået til i indstillinger");
                    return false;
                }
                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
                    notifyUser("Fingerprint Autentificering tilladelse er ikke slået til");
                    return false;
                }
                if (getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
                    return true;
                } else
                    return true;
            }

        };}}



