package edu.nku.nkuparking;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
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
import android.widget.Toast;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class LoginFragment extends Fragment
{
    private String user;
    private String pass;



    @Override
    public void onAttach( Activity activity )
    {
        super.onAttach( activity ) ;

    }

    @Override
    public void onCreate( Bundle state )
    {
        super.onCreate( state ) ;

    }
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle state )
    {
        System.out.println( "onCreateView fired" ) ;

        View v = inflater.inflate( R.layout.fragment_login, container, false ) ;
        Button login = (Button)v.findViewById(R.id.login);

        final EditText username = (EditText)v.findViewById(R.id.username_edittext);
        final EditText password = (EditText)v.findViewById(R.id.password_edittext);

        login.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        user=username.getText().toString();
                        pass=password.getText().toString();
                        LoginUser();
                    }
                });

        return v ;
    }

    private void LoginUser(){
        class loginUser extends AsyncTask<Void,Void,String>{
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
                login(s,user,pass);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_USER,user);
                String temp = s;
                return s;
            }
        }
        loginUser lu = new loginUser();
        lu.execute();
    }



    private void login(String json, String inputUser, String inputPass){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String password = c.getString(Config.TAG_PASSWORD);


            if(password.equals(pass))
            {
                MainActivity x =(MainActivity)getActivity();
                x.setUser(inputUser);
                login();
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void login()
    {

        Fragment fragment = new ParkFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        return;
    }


    public void onActivityCreated( Bundle state )
    {
        super.onActivityCreated( state ) ;
    }

    @Override
    public void onStart( )
    {
        super.onStart( ) ;
    }

    @Override
    public void onResume( )
    {
        super.onResume() ;
    }

    @Override
    public void onPause( )
    {
        super.onPause( ) ;
    }
    @Override
    public void onSaveInstanceState( Bundle state )
    {
        super.onSaveInstanceState( state ) ;
    }
    @Override
    public void onStop( )
    {
        super.onStop() ;
    }

    @Override
    public void onDestroyView( )
    {
        super.onDestroyView( ) ;
    }

    @Override
    public void onDestroy( )
    {
        super.onDestroy( ) ;
    }

    @Override
    public void onDetach( )
    {
        super.onDetach( ) ;
    }


}