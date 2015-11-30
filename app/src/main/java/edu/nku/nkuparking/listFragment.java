package edu.nku.nkuparking;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.Parcelable;
        import android.app.Fragment;
        import android.app.FragmentManager;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.RadioButton;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

        import java.util.ArrayList;
        import java.util.List;


public class listFragment extends Fragment
{
    private ListView lotList;

    private lotAdapter adapter;

    public interface listFragmentHost
    {
        void getlots();
    }

    public listFragment(){/*Required empty public constructor*/}


    @Override
    public void onAttach( Activity activity )
    {
        super.onAttach(activity);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle state )
    {


        class getLot extends AsyncTask<Void,Void,String> {
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

        View v = inflater.inflate( R.layout.list_fragment, container, false);
        lotList = (ListView) v.findViewById(R.id.lot_list);



        return v;
    }
    public  void setup(ArrayList<lotItem> lots) {
        adapter = new lotAdapter(getActivity(), R.layout.lot_item, new ArrayList<lotItem>());
        for(int i=0; i<lots.size(); i++) {
            adapter.add(lots.get(i));
        }
        lotList.setAdapter(adapter);
        lotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lotDetailFragment lotDetailFragment = new lotDetailFragment();
                Bundle args = new Bundle();
                args.putParcelable("lotDetails", (lotItem) adapter.getItem(position));
                lotDetailFragment.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, lotDetailFragment).addToBackStack("list").commit();
            }
        });
        adapter.notifyDataSetChanged();

    }


    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return super.onOptionsItemSelected(item);
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
        super.onPause() ;
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
        super.onDestroyView() ;
    }

    @Override
    public void onDestroy( )
    {
        super.onDestroy() ;
    }




}