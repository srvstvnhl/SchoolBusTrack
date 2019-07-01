/*
package com.example.nihal.navigationdrawerexample;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.nihal.navigationdrawerexample.TrackAppFiles.ApiBuilder;
import com.example.nihal.navigationdrawerexample.TrackAppFiles.TrackObject;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng latLng;


    List<TrackObject> trackObjectList= new ArrayList<>();

    public static int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //SystemClock.sleep(2000);
        //trackObjectList = new ArrayList<>();
        makeNetworkRequest();
    }
    public void makeNetworkRequest(){

        ApiBuilder.getInstance().getLocation().enqueue(new Callback<List<TrackObject>>() {
            @Override
            public void onResponse(Call<List<TrackObject>> call, Response<List<TrackObject>> response) {
                trackObjectList = response.body();


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        latLng = new LatLng(Double.parseDouble(trackObjectList.get(i).getLatitude())
                                , Double.parseDouble(trackObjectList.get(i).getLongitude()));
                        mMap.addMarker(new MarkerOptions().position(latLng)
                                .title("Here")
                                .icon(BitmapDescriptorFactory.fromBitmap(getSmallerSize(R.drawable.green_dot_th))));
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f));


                        i++;

                        if(i <trackObjectList.size()){
                            handler.postDelayed(this,1000);
                        }else{
                            Log.e("TAG", "run: nothing to be fetched");
                        }

                    }
                },1000);
            }

            @Override
            public void onFailure(Call<List<TrackObject>> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    */
/*public void waitSec(){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()<start+1000);
    }*//*


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        latLng = new LatLng(0,0);
        //displayCurrentLocation();
    }

    private Bitmap getSmallerSize(int res) {
        int height = 30;
        int width =30;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(res);

        Bitmap bitmap = bitmapDrawable.getBitmap();
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }


}
*/
