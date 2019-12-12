package com.example.ahabdelhak.userapp.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.ahabdelhak.userapp.Model.Tracking;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class FunWritten {

    void UploadDataToServer(double lat,double lng) {
        DatabaseReference locations = FirebaseDatabase.getInstance().getReference("Locations");
        locations.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(new Tracking(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                        FirebaseAuth.getInstance().getCurrentUser().getUid(),
                        String.valueOf(lat),
                        String.valueOf(lng)));
    }

}
