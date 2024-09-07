package com.example.mynotes;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnCreateNotes;
    FloatingActionButton fabAdd;
    RecyclerView recyclerNotes;

    //database reference
    DatabaseHelper databaseHelper;
    LinearLayout llNoNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initVar();

        showNotes();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_note_lay);

                EditText edtTitle,edtContent;
                Button btnSave;

                edtTitle=findViewById(R.id.edtTitle);
                edtContent=findViewById(R.id.edtContent);
                btnSave=findViewById(R.id.btnSave);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title=edtTitle.getText().toString();
                        String content=edtContent.getText().toString();

                        if(!content.equals(" ")){

                            databaseHelper.noteDao().addNote(new Note(title,content));
                            showNotes();

                            dialog.dismiss();

                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter Something", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();

            }
        });

        btnCreateNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAdd.performClick();
            }
        });

    }

    private void showNotes() {
        ArrayList<Note> arrNotes=(ArrayList<Note>) databaseHelper.noteDao().getNotes();

        if(arrNotes.size()>0){
            recyclerNotes.setVisibility(View.VISIBLE);
            llNoNotes.setVisibility(View.GONE);

            recyclerNotes.setAdapter(new RecyclerNotesAdapter(this,arrNotes));
        }else{
            llNoNotes.setVisibility(View.VISIBLE);
            recyclerNotes.setVisibility(View.GONE);
        }

    }

    private void initVar() {
        btnCreateNotes=findViewById(R.id.btnCreateNotes);
        fabAdd=findViewById(R.id.fabAdd);
        recyclerNotes=findViewById(R.id.recyclerNotes);
        llNoNotes=findViewById(R.id.llNoNotes);

        recyclerNotes.setLayoutManager(new GridLayoutManager(this,2));

        databaseHelper=DatabaseHelper.getInstance(this);



    }
}