package com.example.topquiz.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.topquiz.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class HistoricTableAdapter extends BaseAdapter {

    private Activity context;
    HashMap<String, List<Integer>> map;
    private int element;

    private String key;
    private int singleValue;


    public HistoricTableAdapter(Activity context, HashMap<String, List<Integer>> map) {
        super();
        this.context = context;
        this.map = map;
    }


    @Override
    public int getCount() {
        if (map.size() < 5) {
            return map.size();
        } else {
            return 5;
        }

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.activity_historic_list_view, parent, false);
            holder = new ViewHolder();

            holder.playerNameTxt = (TextView) view.findViewById(R.id.activity_historic_list_view_name_player);
            holder.scorePlayerTxt = (TextView) view.findViewById(R.id.activity_historic_list_view_score_player);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            key = entry.getKey();
            holder.playerNameTxt.setText(key);
            for (int singleValue : entry.getValue()) {
                holder.scorePlayerTxt.setText(String.valueOf(singleValue));
            }
        }


        return view;
    }

    static class ViewHolder {
        private TextView playerNameTxt;
        private TextView scorePlayerTxt;
    }

}
