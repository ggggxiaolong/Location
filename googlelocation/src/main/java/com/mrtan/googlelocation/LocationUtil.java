package com.mrtan.googlelocation;

import android.Manifest;
import android.content.Context;
import android.location.Location;

import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public final class LocationUtil {
    private static Location location;
    private static FusedLocationProviderClient client;
    public static OnSuccessListener<Location> listener;

    private LocationUtil() {
    }

    public static void init(Context context) {
        client = LocationServices.getFusedLocationProviderClient(context);
        if (PermissionChecker.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        ) {
            start();
        }
    }

    public static void start() {
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location l) {
                location = l;
                if (listener != null){
                    listener.onSuccess(l);
                }
            }
        });
    }

    @Nullable
    public static Location location() {
        return location;
    }
}
