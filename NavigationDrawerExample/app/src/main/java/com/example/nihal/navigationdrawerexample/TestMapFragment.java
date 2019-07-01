package com.example.nihal.navigationdrawerexample;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TestMapFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;

    private GoogleMap mMap;
    LatLng latLng;
    Handler handler;
    Runnable runnable;

    List<TrackObject> trackObjectList= new ArrayList<>();

    public static int i =0;

    public TestMapFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_maps, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        makeNetworkRequest();

    }

    public void makeNetworkRequest(){

        ApiBuilder.getInstance().getLocation().enqueue(new Callback<List<TrackObject>>() {
            @Override
            public void onResponse(Call<List<TrackObject>> call, Response<List<TrackObject>> response) {
                trackObjectList = response.body();


                handler = new Handler();
                runnable= new Runnable() {
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
                };
                handler.postDelayed(runnable,1000);

            }

            @Override
            public void onFailure(Call<List<TrackObject>> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getLocalizedMessage());
            }
        });


    }

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

/*
    @Override
    public void onDestroyView() {

        super.onDestroyView();



        android.app.Fragment fragment = getActivity().getFragmentManager()
                .findFragmentById(R.id.map);
        if (null != fragment) {
            android.app.FragmentTransaction ft = getActivity()
                    .getFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }
        handler.removeCallbacks(runnable);
    }*/


    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
