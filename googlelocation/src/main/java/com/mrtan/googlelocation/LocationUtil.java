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
    private static OnSuccessListener<Location> onSuccessListener = new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location l) {
            location = l;
        }
    };
    private LocationUtil() {
    }
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
        client = LocationServices.getFusedLocationProviderClient(context);
        start();
    }

    public static void start() {
        if (PermissionChecker.PERMISSION_GRANTED ==
                PermissionChecker.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
        ) {
            location = null;
            client.getLastLocation().addOnSuccessListener(onSuccessListener);
        }
    }

    @Nullable
    public static Location location() {
        return location;
    }
}
