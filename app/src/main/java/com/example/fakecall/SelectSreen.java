package com.example.fakecall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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
                Toast.makeText(SelectSreen.this, " màn hình thứ" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addLiss()
    {
        list.add(new Model(R.drawable.screenshot3 , "opppo")) ;
        list.add(new Model(R.drawable.bg , "samsung")) ;
        list.add(new Model(R.drawable.screenshot2 , "iphone")) ;
        list.add(new Model(R.drawable.screenshot1, "vivo")) ;
    }
}