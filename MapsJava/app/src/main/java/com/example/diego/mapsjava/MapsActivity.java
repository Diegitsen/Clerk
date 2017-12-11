package com.example.diego.mapsjava;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int cont = 1;
    int c = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onSearch(View view)
    {
        String snippet1 = "Subsuelo: destruido";
        String snippet2 = "Zona peligrosa";
        String snippet3 = "Subsuelo: Imposible habitar";
        String snippet4 = "Susuelo: en Construccion";
        String snippet5 = "Subsuelo: No destruido";
        String snippet6 = "Tierra: Estado dañado";
        String snippet7 = "Subsuelo: Tuberias dañadas";
        String snippet8 = "Subsuelo: Inestable";
        String snippet9 = "Subsuelo: No apto";
                                                                                                                            String snippet10 = "Subsuelo: Semi destruido";
                                                                                                                            String realsnippet = "";
                                                                                                                            EditText location_tf = (EditText)findViewById(R.id.etName);
                                                                                                                            String location = location_tf.getText().toString();
                                                                                                                            List<Address> addressList = null;
                                                                                                                            if(location != null || !location.equals(""))
                                                                                                                            {
                                                                                                                                if(c == 1)
                                                                                                                                    realsnippet = snippet1;

                                                                                                                                if(c == 2)
                                                                                                                                    realsnippet = snippet2;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  if(c == 4)
                                                                                                                                    realsnippet = snippet4;

                                                                                                                                if(c == 5)
                                                                                                                                    realsnippet = snippet5;

            if(c == 6)
                realsnippet = snippet6;

            if(c == 7)
                realsnippet = snippet7;

            if(c == 8)
                realsnippet = snippet8;

            if(c == 9)
                realsnippet = snippet9;

            if(c == 10)
                realsnippet = snippet10;

            if(c>10)
                c = 1;
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location , 3);


            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude() , address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location)
            .draggable(true)
            .snippet(realsnippet));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            c++;
        }
    }

    public void onChangeType(View view)
    {
        if(cont % 2 != 0)
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        else
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        cont++;
    }

    public void onZoom(View view)
    {
        if(view.getId()==R.id.ibZoomIn)
            mMap.animateCamera(CameraUpdateFactory.zoomIn());

        if(view.getId()==R.id.ibZoomOut)
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
    }
}
