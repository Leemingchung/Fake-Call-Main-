package com.example.fakecall.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.fakecall.Model;
import com.example.fakecall.R;
import com.example.fakecall.SelectSreen;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private  int selectposition = -1 ;
    Context context ;
    List<Model> list;
     Boolean check  = true;
     SelectSreen selectSreen ;
    int idlayout ;
    public GridAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        final Model model = list.get(i);
        Toast.makeText(context, ""+model, Toast.LENGTH_SHORT).show();
        if (view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item, null);
            viewHolder.textView = view.findViewById(R.id.nameItem);
            viewHolder.imageView = view.findViewById(R.id.imageItem);
            viewHolder.done = view.findViewById(R.id.done);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(model.getName());
        viewHolder.imageView.setImageResource(model.getImageView());
        return  view ;
    }
    public class ViewHolder{
        TextView textView;
        ImageView imageView;
        ImageView done ;
    }
}
