package com.example.home.wakemethere;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MapSearch extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMsp;
    FloatingActionButton searchFab;
    EditText searchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);


        final EditText search = (EditText) findViewById(R.id.search_field);
        search.setBackground(getResources().getDrawable(R.drawable.capsule));

        MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment);
        fragment.getMapAsync(this);
        searchFab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        searchField = (EditText) findViewById(R.id.search_field);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intent resultIntent = new Intent(getApplicationContext(), SearchResultsDisplay.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MapSearch.this, findViewById(R.id.search_field), "sharedElement");
                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
                startActivity(resultIntent,activityOptionsCompat.toBundle());
//                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchField.setVisibility(View.INVISIBLE);
        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFab.setVisibility(View.INVISIBLE);
                searchField.setVisibility(View.VISIBLE);
                searchField.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                final int targtetHeight = searchField.getMeasuredWidth();

                searchField.getLayoutParams().width = 0;
                searchField.setVisibility(View.VISIBLE);

                Animation a = new Animation() {
                    @Override
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        searchField.getLayoutParams().width = interpolatedTime == 1
                                ? ViewGroup.LayoutParams.MATCH_PARENT
                                : (int) (targtetHeight * interpolatedTime);
                        searchField.requestLayout();
                    }

                    @Override
                    public boolean willChangeBounds() {
                        return true;
                    }
                };

                a.setDuration((int) (targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
                searchField.startAnimation(a);

                /**
                 *

                 */
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mGoogleMsp = googleMap;
            LocationManager manager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
//            return;
            }
            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Toast.makeText(getApplicationContext(), "" + latitude + "," + longitude, Toast.LENGTH_LONG).show();
            gotoLocZoom(latitude, longitude, 17);
//        gotoLocZoom(17.397460f, 78.540799f,15);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void gotoLocZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMsp.animateCamera(update);
    }
}
