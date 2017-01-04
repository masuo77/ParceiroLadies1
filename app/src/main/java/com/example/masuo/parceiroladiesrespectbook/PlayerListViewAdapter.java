package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Masuo on 2016/12/11.
 */

public class PlayerListViewAdapter extends ArrayAdapter<PlayerListItem> {

    private LayoutInflater inflater;
    private int tvResId;
    private List<PlayerListItem> items;

    public PlayerListViewAdapter(Context context, int resource, List<PlayerListItem> items) {
        super(context, resource, items);
        this.tvResId = resource;
        this.items = items;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // ListViewからgetViewが呼ばれる
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null)
        {
            view = convertView;
        }else
        {
            view = inflater.inflate(tvResId, null);
        }

        PlayerListItem item = this.items.get(position);

        ImageView face = (ImageView)view.findViewById(R.id.list_image);
        face.setImageResource(item.getImageRes());

        TextView name = (TextView)view.findViewById(R.id.list_name);
        name.setText(item.getName());

        TextView pos = (TextView)view.findViewById(R.id.list_info);
        pos.setText(item.getPosition());

        return view;
    }
}




