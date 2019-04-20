package vocapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import vocapp.activities.R;
import vocapp.database.DBHelper;
import vocapp.database.WordTable;

public class DisplaySearchResultActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search_result);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String wordToSearch = intent.getStringExtra("WORD_TO_SEARCH").toUpperCase();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                WordTable.TABLE_NAME,                     // The table to query
                new String[] {WordTable.DEFINITION, WordTable.SYNONYM, WordTable.ANTONYM},   // The columns to return
                WordTable.WORD + " = ?",                   // The columns for the WHERE clause
                new String[] {wordToSearch},               // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        String searchResult = "";
        while(cursor.moveToNext())
        {
            searchResult = "Definition: " + cursor.getString(0) + "\n\n" +
                    "Synonym: " + cursor.getString(1) + "\n\n" +
                    "Antonym: " + cursor.getString(2);
        }
        cursor.close();

        // Capture the layout's TextView and set the string as its text
        TextView upperTextView = (TextView) findViewById(R.id.keyword);
        upperTextView.setText(wordToSearch);

        TextView lowerTextView = (TextView) findViewById(R.id.resultBox);
        lowerTextView.setText(searchResult);
    }
}
