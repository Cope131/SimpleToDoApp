package sg.edu.rp.c346.id19004731.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Spinner spinner;
    Button btnAdd, btnDelete, btnClear;
    ListView lvTask;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editTextTask);
        spinner = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.buttonAddTask);
        btnDelete = findViewById(R.id.buttonDeleteTask);
        btnClear = findViewById(R.id.buttonClearField);
        lvTask = findViewById(R.id.listViewTask);

        alTasks = new ArrayList<String>();

        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTask.setAdapter(aaTask);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        et.setHint(R.string.newTaskHint);
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        et.setHint(R.string.deleteTaskHint);
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = et.getText().toString().trim();
                if (!newTask.isEmpty()) {
                    alTasks.add(newTask);
                    aaTask.notifyDataSetChanged();
                    et.getText().clear();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTasks.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.emptyTask, Toast.LENGTH_SHORT).show();
                    return;
                }

                String posStr = et.getText().toString().trim();
                if (!posStr.isEmpty() && posStr.matches("[0-9]+")) {
                    int posInt = Integer.parseInt(posStr);
                    if (posInt < alTasks.size() && posInt >= 0) {
                        alTasks.remove(posInt);
                        aaTask.notifyDataSetChanged();
                        et.getText().clear();
                    } else {
                        Toast.makeText(MainActivity.this, R.string.wrongIndex, Toast.LENGTH_SHORT).show();
                    }
                }
                return;
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                aaTask.notifyDataSetChanged();
            }
        });

    }
}
