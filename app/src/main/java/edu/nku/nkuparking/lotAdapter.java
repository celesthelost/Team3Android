package edu.nku.nkuparking;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.Collections;
import java.util.List;

public class lotAdapter extends ArrayAdapter
{
    private final Activity context;
    private final List<lotItem> lots;

    public lotAdapter(Activity context, int resource, List<lotItem> lots)
    {
        super(context, resource, lots);
        this.context = context;
        this.lots = lots;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder vh;

        if (convertView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.lot_item, null);

            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }
        else
        {
            vh = (ViewHolder) convertView.getTag();
        }

        lotItem lot = this.lots.get(position);
        vh.lotName.setText(lot.getLotName());

        return convertView;
    }

    private class ViewHolder
    {
        TextView lotName;


        public ViewHolder(View v)
        {
            lotName = (TextView) v.findViewById(R.id.lot_name);

        }
    }



    public List<lotItem> getLots() {
        return lots;
    }

}
