package com.example.fakecall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fakecall.DAO.ModelCharacter;

import java.util.ArrayList;

public class CharAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    ArrayList<ModelCharacter> categroyArrayList;
    Context context;
    CovertIMG covertIMG ;
    public class Holder {
        ImageView img;
        TextView tv;
        TextView tv2;
    }

    public CharAdapter(Context mainActivity, ArrayList<ModelCharacter> categroyArrayList) {
        this.categroyArrayList = categroyArrayList;
        this.context = mainActivity;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return this.categroyArrayList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        View rowView = null;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.character_row, null);
            holder = new Holder();
            holder.tv = (TextView) rowView.findViewById(R.id.text_row);
            holder.tv2 = (TextView) rowView.findViewById(R.id.text_row2);
            holder.img = (ImageView) rowView.findViewById(R.id.img_row);
            rowView.setTag(holder);
        } else {
            holder = (Holder) rowView.getTag();
        }
        holder.tv.setText((categroyArrayList.get(position)).getName());
        holder.tv2.setText((categroyArrayList.get(position)).getSdt());
        holder.img.setImageBitmap(CovertIMG.getImage(categroyArrayList.get(position).getHinhanh()));

        return rowView;
    }
}
