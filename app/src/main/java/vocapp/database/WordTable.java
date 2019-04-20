package vocapp.database;

public class WordTable
{
    public static final String TABLE_NAME = "WordTable";
    public static final String WORD = "Word";
    public static final String DEFINITION = "Definition";
    public static final String SYNONYM = "Synonym";
    public static final String ANTONYM = "Antonym";

    public static final String TABLE_CREATE_COMMAND = "CREATE TABLE " +
            TABLE_NAME +
            "(" +
            WORD + " TEXT," +
            DEFINITION + " TEXT," +
            SYNONYM + " TEXT," +
            ANTONYM + " TEXT" +
            ")";
}
