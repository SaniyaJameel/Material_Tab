package info.androidhive.materialtabs.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import info.androidhive.materialtabs.R;

/**
 * Created by HalFM-1 on 11/24/2015.
 */
public class contactAdapter extends ArrayAdapter<String> {
Context c;
String[] name;
    String[] phone;


    public contactAdapter(Context context, int resource, String[] name,String [] phone ) {
        super(context, resource,  name );
        c=context;
        this.name=name;
        this.phone=phone;

    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contact_list, parent, false);
        TextView t=(TextView)rowView.findViewById(R.id.nametv);
        TextView t2=(TextView)rowView.findViewById(R.id.phonetv);
        t.setText(name[position]);
        t2.setText(phone[position]);
        return rowView;
    }
}
