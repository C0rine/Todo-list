package corine.todo_list;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> todos = new ArrayList<String>();
    private EditText input;
    private ListAdapter theAdapter;
    private Bundle bundle = new Bundle();
    private ListView theListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input = (EditText) findViewById(R.id.editText);

        theAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.textview1,
                todos);

        theListView = (ListView) findViewById(R.id.listView);

        theListView.setAdapter(theAdapter);

        // remove item from the list
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                todos.remove(String.valueOf(parent.getItemAtPosition(position)));

                ((ArrayAdapter<String>)theAdapter).notifyDataSetChanged();

            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        bundle.putStringArrayList("list", todos);

    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);

        todos = bundle.getStringArrayList("list");
        Toast.makeText(this, todos.get(1), Toast.LENGTH_LONG).show();
        theAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.textview1,
                todos);
        theListView.setAdapter(theAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // add an item to the list
    public void addItem(View view) {

        todos.add(String.valueOf(input.getText()));
        input.setText("");
        ((ArrayAdapter<String>)theAdapter).notifyDataSetChanged();
    }
}

