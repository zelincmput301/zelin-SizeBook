package com.example.zelin_sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddEntry extends Activity {
    int id = -1;

    EditText nameText;
    EditText date_YText;
    EditText date_MText;
    EditText date_DText;
    EditText neckText;
    EditText bustText;
    EditText chestText;
    EditText waistText;
    EditText hipText;
    EditText inseamText;
    EditText commentText;


    private static final String FILENAME = "file.sav";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        nameText = (EditText) findViewById(R.id.entry_name);
        date_YText = (EditText) findViewById(R.id.entry_date_year);
        date_MText = (EditText) findViewById(R.id.entry_date_month);
        date_DText = (EditText) findViewById(R.id.entry_date_day);
        neckText = (EditText) findViewById(R.id.entry_neck);
        bustText = (EditText) findViewById(R.id.entry_bust);
        chestText = (EditText) findViewById(R.id.entry_chest);
        waistText = (EditText) findViewById(R.id.entry_waist);
        hipText = (EditText) findViewById(R.id.entry_hip);
        inseamText = (EditText) findViewById(R.id.entry_inseam);
        commentText = (EditText) findViewById(R.id.entry_comment);

        Intent current = getIntent();
        id = current.getIntExtra("item_position",-1);
        if (id != -1){
            Size mod = (Size) MainActivity.sizeList.get(id);
            nameText.setText(mod.name);
            date_YText.setText(mod.date_Y);
            date_MText.setText(mod.date_Y);
            date_DText.setText(mod.date_Y);
            neckText.setText(mod.neck);
            bustText.setText(mod.bust);
            chestText.setText(mod.chest);
            waistText.setText(mod.waist);
            hipText.setText(mod.hip);
            inseamText.setText(mod.inseam);
            commentText.setText(mod.comment);
        }


    }
    public void SaveInFile(View v) {


        if (nameText.getText().toString().isEmpty()){}
        else {


            Size entry = new Size(
                    nameText.getText().toString(),
                    date_YText.getText().toString(),
                    date_MText.getText().toString(),
                    date_DText.getText().toString(),
                    neckText.getText().toString(),
                    bustText.getText().toString(),
                    chestText.getText().toString(),
                    waistText.getText().toString(),
                    hipText.getText().toString(),
                    inseamText.getText().toString(),
                    commentText.getText().toString()
            );
            try {
                FileOutputStream fos = openFileOutput(FILENAME,
                        Context.MODE_PRIVATE);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

                Gson gson = new Gson();
                if(id == -1){
                    MainActivity.sizeList.add(entry);}
                else{
                    MainActivity.sizeList.set(id,entry);
                }

                gson.toJson(MainActivity.sizeList, out);

                out.flush();


                fos.close();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

}
