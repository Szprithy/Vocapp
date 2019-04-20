package vocapp.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import vocapp.database.DBHelper;
import vocapp.database.WordTable;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the GO button */
    public void searchForWord(View view)
    {
        EditText editText = (EditText) findViewById(R.id.wordBox);
        String wordToSearch = editText.getText().toString();

        Intent intent = new Intent(this, DisplaySearchResultActivity.class);
        intent.putExtra("WORD_TO_SEARCH", wordToSearch.toUpperCase());
        startActivity(intent);
    }

    public void showWordList(View view)
    {
        Intent intent = new Intent(this, DisplayWordListActivity.class);
        startActivity(intent);
    }

}
