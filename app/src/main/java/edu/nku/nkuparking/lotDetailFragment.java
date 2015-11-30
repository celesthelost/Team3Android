package edu.nku.nkuparking;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class lotDetailFragment extends Fragment {
    private lotItem lot;
    private static String User;
    private String selectedLot;
    private static View v;
    private static int chosenspot=0;
     static ImageView image1=null;
     static ImageView image2=null;
     static ImageView image3=null;
     static ImageView image4=null;
     static ImageView image5=null;
     static ImageView image6=null;
     static ImageView image7=null;
     static ImageView image8=null;
     static ImageView image9=null;
     static ImageView image10=null;


    public lotDetailFragment() {/*Required empty public constructor*/}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lot = (lotItem) getArguments().getParcelable("lotDetails");
              selectedLot = lot.getLotName();
        }
        MainActivity x =(MainActivity)getActivity();
        User =x.getUser();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        v = inflater.inflate(R.layout.detail_fragment, container, false);
        final Button button = (Button)v.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                class Update extends AsyncTask<Void,Void,String>{

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();

                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);


                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String,String> params = new HashMap<>();
                        params.put("SpotNumber",Integer.toString(chosenspot));
                        params.put("SpotLot",selectedLot);

                        params.put("User",User);

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(Config.URL_ADD, params);
                        return res;
                    }
                }
                if(chosenspot!=0) {
                    Update ae = new Update();
                    ae.execute();
                }
                Toast.makeText(getActivity(), "Reservation Successful", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();


            }
        });

        class getSpot extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                // loading.dismiss();
                String test1 = "11";
                ArrayList<spotItem> spots = new ArrayList<spotItem>();
                spots = new Gson().fromJson(s, new TypeToken<List<spotItem>>() {
                }.getType());
                setup(selectedLot, spots);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_SPOTS);
                return s;
            }
        }
        getSpot lu = new getSpot();
        lu.execute();

        return v;
    }

    public static void setup(final String selectedLot, final ArrayList<spotItem> spots) {
        class getSpot extends AsyncTask<Void, Void, String> {
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
                ArrayList<historyItem> history = new ArrayList<historyItem>();
                history = new Gson().fromJson(s, new TypeToken<List<historyItem>>() {
                }.getType());
                setup2(selectedLot, spots, history);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_HISTORY);
                return s;
            }
        }
        getSpot lu = new getSpot();
        lu.execute();
    }

    public static  void setup2(final String selectedLot, final ArrayList<spotItem> spots, ArrayList<historyItem> history) {
        for (int i = 0; i < spots.size(); i++) {
            if (spots.get(i).getSpotLot().equals(selectedLot)) {
                setupimage(spots.get(i));
            }
        }
        }



    public static void setupimage(spotItem spot)
    {
        if (spot.getSpotNumber().equals("1"))
        {
            image1=(ImageView) v.findViewById(R.id.imageView1);
            image1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (image1.getTag().equals(R.drawable.square) && chosenspot == 0) {
                        image1.setImageResource(R.drawable.check);
                        chosenspot = 1;
                    }

                }

            });
            chooseimage(image1, spot);

        }
        else if (spot.getSpotNumber().equals("2"))
        {
            image2=(ImageView) v.findViewById(R.id.imageView2);
            chooseimage(image2, spot);
            image2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (image2.getTag().equals(R.drawable.square) && chosenspot == 0) {
                        image2.setImageResource(R.drawable.check);
                        chosenspot = 2;
                    }

                }

            });
        }
        else if (spot.getSpotNumber().equals("3"))
        {
            image3=(ImageView) v.findViewById(R.id.imageView3);
            image3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(image3.getTag().equals(R.drawable.square) && chosenspot==0) {
                        image3.setImageResource(R.drawable.check);
                        chosenspot=3;
                    }

                }

            });


            chooseimage(image3, spot);
        }
        else if (spot.getSpotNumber().equals("4"))
        {
            image4=(ImageView) v.findViewById(R.id.imageView4);
            image4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(image4.getTag().equals(R.drawable.square) && chosenspot==0) {
                        image4.setImageResource(R.drawable.check);
                        chosenspot=4;
                    }

                }

            });

            chooseimage(image4, spot);

        }
        else if (spot.getSpotNumber().equals("5"))
        {
            image5=(ImageView) v.findViewById(R.id.imageView5);
            image5.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(image5.getTag().equals(R.drawable.square) && chosenspot==0) {
                        image5.setImageResource(R.drawable.check);
                        chosenspot=5;
                    }

                }

            });
            chooseimage(image5, spot);

        }
        else if (spot.getSpotNumber().equals("6"))
        {
            image6=(ImageView) v.findViewById(R.id.imageView6);
            image6.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(image6.getTag().equals(R.drawable.square) && chosenspot==0) {
                        image6.setImageResource(R.drawable.check);
                        chosenspot=6;
                    }

                }

            });

            chooseimage(image6, spot);

        }
        else if (spot.getSpotNumber().equals("7"))
        {
            image7=(ImageView) v.findViewById(R.id.imageView7);
            image7.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(image7.getTag().equals(R.drawable.square) && chosenspot==0) {
                        image7.setImageResource(R.drawable.check);
                        chosenspot=7;
                    }

                }

            });

            chooseimage(image7, spot);

        }
        else if (spot.getSpotNumber().equals("8"))
        {
            image8=(ImageView) v.findViewById(R.id.imageView8);
            image8.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(image8.getTag().equals(R.drawable.square) && chosenspot==0) {
                        image8.setImageResource(R.drawable.check);
                        chosenspot=8;
                    }

                }

            });

            chooseimage(image8, spot);

        }
        else if (spot.getSpotNumber().equals("9"))
        {
            image9=(ImageView) v.findViewById(R.id.imageView9);
            image9.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(image9.getTag().equals(R.drawable.square) && chosenspot==0) {
                        image9.setImageResource(R.drawable.check);
                        chosenspot=9;
                    }

                }

            });

            chooseimage(image9, spot);

        }
        else if (spot.getSpotNumber().equals("10"))
        {
            image10=(ImageView) v.findViewById(R.id.imageView10);
            image10.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(image10.getTag().equals(R.drawable.square) && chosenspot==0) {
                        image10.setImageResource(R.drawable.check);
                        chosenspot=10;
                    }

                }

            });
            chooseimage(image10, spot);

        }
    }
    public static void chooseimage(ImageView image, spotItem spot)
    {
        if(spot.getSpotStatus().equals("unavaiable"))
        {
            image.setImageResource(R.drawable.x);
            image.setTag(R.drawable.x);
        }
        else if(spot.getSpotStatus().equals("closed"))
        {
            image.setImageResource(R.drawable.x);
            image.setTag(R.drawable.x);
        }
        else if(spot.getSpotStatus().equals("reserved"))
        {
            if(spot.getReservedBy().equals(User))
            {
                image.setImageResource(R.drawable.rr);
                image.setTag(R.drawable.rr);
            }
            else {
                image.setImageResource(R.drawable.x);
                image.setTag(R.drawable.x);
            }
        }
        else
        {
            image.setImageResource(R.drawable.square);
            image.setTag(R.drawable.square);
        }
    }
    public void onActivityCreated( Bundle state )
    {
        super.onActivityCreated( state ) ;
    }

    @Override
    public void onStart( )
    {
        super.onStart() ;
    }

    @Override
    public void onResume( )
    {
        chosenspot=0;
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
        super.onSaveInstanceState(state) ;
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




}




