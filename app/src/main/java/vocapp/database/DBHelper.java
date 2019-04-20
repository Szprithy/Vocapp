package vocapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;



public class DBHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Vocapp_DB";

    Context thisContext = null;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        thisContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(WordTable.TABLE_CREATE_COMMAND);

        try
        {
            //BufferedReader file = new BufferedReader(new FileReader("wordlist.txt"));
            String line;
            ContentValues values;

            AssetManager assetManager = thisContext.getAssets();
            InputStream inStream = assetManager.open("wordlist.txt");
            BufferedReader file = new BufferedReader(new InputStreamReader(inStream));

            while (true)
            {
                if((line= file.readLine()) == null) break;

                values = new ContentValues();

                values.put(WordTable.WORD, line);
                values.put(WordTable.DEFINITION, file.readLine());
                values.put(WordTable.SYNONYM, file.readLine());
                values.put(WordTable.ANTONYM, file.readLine());

                db.insert(WordTable.TABLE_NAME, null, values);

            }

            file.close();
        }
        catch (Exception e)
        {
            //TODO:
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVerssion)
    {
        // who cares
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVerssion)
    {
        // who cares
    }
}
