package vocapp.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;

import vocapp.activities.R;
import vocapp.database.DBHelper;
import vocapp.database.WordTable;

import static vocapp.activities.R.id.wordListView;

public class DisplayWordListActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word_list);


        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                WordTable.TABLE_NAME,                     // The table to query
                new String[] {WordTable.WORD},   // The columns to return
                null,                   // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        List<String> wordList = new ArrayList<String>();
        while(cursor.moveToNext())
        {
            wordList.add( cursor.getString(0) );
        }
        cursor.close();

        //String[] wordArray= new String[wordList.size()];
        //wordArray = wordList.toArray(wordArray);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, wordList);

        ListView listView = findViewById(wordListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
          new AdapterView.OnItemClickListener()
          {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id)
              {
                  String WORD = String.valueOf(parent.getItemAtPosition(position));

                  Intent intent = new Intent(DisplayWordListActivity.this, DisplaySearchResultActivity.class);
                  intent.putExtra("WORD_TO_SEARCH", WORD.toUpperCase());
                  startActivity(intent);
              }
          }
        );

    }
}
