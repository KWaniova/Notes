package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    public List<Question> list;

    public void MainActivity(){
        list = new ArrayList<>();
    }
    public void addItem(Question i){
        if(i.equals(null)) System.out.println("null");
        else
            list.add(i);
    }
    public List getList(){
        return list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.add_note){
            Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
            startActivity(intent);

            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);

        if(set==null){
            notes.add("Example note");
        }else{
            notes = new ArrayList<>(set);
        }

        System.out.println("Main");

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //System.out.println("OnCilick");
                Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
                intent.putExtra("noteId",i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                final int itemToDelete = i;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("notes size: " + notes.size());
                                notes.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

                                HashSet<String> set = new HashSet<>(MainActivity.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                                System.out.println("notes size: " + notes.size());
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });

    }
}



/*private void setSingleEvent(GridLayout gridLayout) {
        //Loop all child item of grid layout
        for(int i=0;i<gridLayout.getChildCount();i++){
            //all children is CardView so I cast object to CardView
            CardView cardView = (CardView)gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(QuizMenuActivity.this,"Clicked at index "+ finalI,Toast.LENGTH_SHORT).show();
                    if(finalI == 0){
                        intent = new Intent(getApplicationContext(),QuestionsViewActivity.class);
                        startActivity(intent);
                    }else if(finalI == 1){


                    }else if(finalI == 2){
                        intent = new Intent(getApplicationContext(), GameStartActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }*/
