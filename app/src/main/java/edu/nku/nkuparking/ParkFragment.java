package edu.nku.nkuparking;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;



public class ParkFragment extends Fragment {


    public FragmentManager fm = getFragmentManager();
    private static View view;
    /**
     * Note that this may be null if the Google Play services APK is not
     * available.
     */

    private static GoogleMap mMap;
    private static Double latitude, longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        view = (LinearLayout) inflater.inflate(R.layout.fragment_park, container, false);
        // Passing harcoded values for latitude & longitude. Please change as per your need. This is just used to drop a Marker on the Map


        setUpMapIfNeeded(); // For setting up the MapFragment

        final Button button = (Button)view.findViewById(R.id.reserve);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new listFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("map").commit();
            }
        });
        return view;
    }

    /***** Sets up the map if it is possible to do so *****/
    public static void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) MainActivity.fragmentManager
                    .findFragmentById(R.id.location_map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the
     * camera.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap}
     * is not null.
     */
    private static void setUpMap() {
        class getLot extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(LoginFragment.this,"...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                // loading.dismiss();
                String test1 = "11";
                ArrayList<lotItem> lots = new ArrayList<lotItem>();
                lots = new Gson().fromJson(s, new TypeToken<List<lotItem>>(){}.getType());
                setup(lots);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_LOTS);
                String temp = s;
                return s;
            }
        }
        getLot lu = new getLot();
        lu.execute();



    }
    public static void setup(ArrayList<lotItem> lots)
    {
        for (int i = 0; i < lots.size(); i++) {
            LatLng temp = new LatLng(Double.parseDouble(lots.get(i).getLatitude()),Double.parseDouble(lots.get(i).getLongitude()));

            mMap.addMarker(new MarkerOptions().position(temp).title(lots.get(i).getLotName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));

        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                39.036464,
                -84.466432), 15.0f));
        // For showing a move to my loction button
        mMap.setMyLocationEnabled(true);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (mMap != null)
            setUpMap();

        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) MainActivity.fragmentManager
                    .findFragmentById(R.id.location_map)).getMap(); // getMap is deprecated
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    /**** The mapfragment's id must be removed from the FragmentManager
     **** or else if the same it is passed on the next time then
     **** app will crash ****/
    @Override
    public void onDestroyView() {
        if (mMap != null) {

                MapFragment f = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.location_map);
                if (f.isResumed()){
                    getFragmentManager().beginTransaction().remove(f).commit();
                }

                super.onDestroy();
            }

            mMap = null;
        }
    }
