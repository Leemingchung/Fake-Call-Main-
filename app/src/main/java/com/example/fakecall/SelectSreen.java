package com.example.fakecall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.fakecall.Adapter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectSreen extends AppCompatActivity {
    List<Model> list = new ArrayList<>() ;
    GridAdapter gridAdapter ;
    GridView gridView ;
    static int  idlayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call3);
        gridView = findViewById(R.id.gridview);
        addLiss();
        gridAdapter = new GridAdapter(this,list );
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idlayout= i ;
            }
        });
    }
    public void addLiss()
    {
        list.add(new Model(R.drawable.add_ , "opppo")) ;
        list.add(new Model(R.drawable.bg , "samsung")) ;
        list.add(new Model(R.drawable.background , "iphone")) ;
        list.add(new Model(R.drawable.gallery_btn_2, "vivo")) ;
    }
}