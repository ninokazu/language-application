package org.android.soldesk.intro_slide.word;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.android.soldesk.intro_slide.R;
import org.android.soldesk.intro_slide.vo.Word;

import java.util.ArrayList;

/**
 * Created by shin on 2017-05-12.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private ArrayList<Word> items;
    private Context context;
    public WordAdapter(Context context, int textViewResourceId, ArrayList<Word> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.context=context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.korrow, null);
        }
        Word w = items.get(position);
        if (w != null) {
            ImageView tt = (ImageView) v.findViewById(R.id.wordImage);
            TextView bt = (TextView) v.findViewById(R.id.wordName);
            if (tt != null){
                tt.setImageResource(w.getFname());
            }
            if(bt != null){
                bt.setText(w.getName());
            }
        }
        return v;
    }
}

