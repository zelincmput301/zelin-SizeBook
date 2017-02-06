package com.example.zelin_sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {
    private static final String FILENAME = "file.sav";

    ListView displaySizeList;

    TextView counter;

    private ArrayList textList;


    public static ArrayList sizeList;
    private ArrayAdapter<Size> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displaySizeList = (ListView) findViewById(R.id.list);

        displaySizeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position,long id){
                Intent intent = new Intent(MainActivity.this, AddEntry.class);
                intent.putExtra("item_position",position);
                startActivity(intent);
                finish();
            }
        });

        displaySizeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id){
                textList.remove(position);
                sizeList.remove(position);
                adapter.notifyDataSetChanged();
                counter = (TextView) findViewById(R.id.count);
                counter.setText("number of entries : "+sizeList.size());
                try {
                    FileOutputStream fos = openFileOutput(FILENAME,
                            Context.MODE_PRIVATE);
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

                    Gson gson = new Gson();

                    gson.toJson(MainActivity.sizeList, out);

                    out.flush();


                    fos.close();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                return true;
            }

        });
    }

    public void addEntry(View view) {
        Intent intent = new Intent(this, AddEntry.class);
        startActivity(intent);
        finish();
    }



    private void LoadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            sizeList = gson.fromJson(in, new TypeToken<ArrayList<Size>>(){}.getType());

            fis.close();

        } catch (FileNotFoundException e) {
//            sizeList = new ArrayList<Size>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        sizeList = new ArrayList<Size>();
        LoadFromFile();

        textList = new ArrayList<String>();



        for(int i =0; i<sizeList.size(); i ++){
            Size s = (Size) sizeList.get(i);
            textList.add(s.SizetoString());
        }
        counter = (TextView) findViewById(R.id.count);
        counter.setText("number of entries : "+sizeList.size());

        adapter = new ArrayAdapter<Size>(this,
                R.layout.list_item, textList);

        displaySizeList.setAdapter(adapter);
    }
}
