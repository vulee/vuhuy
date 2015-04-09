package vn.dev.karaoke.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import vn.dev.karaoke.dto.Song;
import vn.dev.karaoke.dto.SongCol;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    /** Path of database */
    private static String DB_PATH = "/data/data/vn.dev.karaoke/databases/";

    /** Table song */
    private static final String TABLE_SONG = "TBLSONG";

    /** Database name */
    private static final String DB_NAME = "klist.db";

    /** Version of database */
    private static final int DATABASE_VERSION = 9;

    private SQLiteDatabase myDataBase; 

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
        }catch(SQLiteException e){
            //database does't exist yet.
        }

        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException{
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
            if(myDataBase != null)
                myDataBase.close();
            super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Getting all contacts
     * 
     * @author VULEE
     * @since 20150408
     * @return
     */
    public List<Song> getAllSongs() {
        List<Song> songList = new ArrayList<Song>();
        String selectQuery = "SELECT * FROM " + TABLE_SONG + " WHERE language LIKE 'vn' LIMIT 10;";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Song song = new Song();
                song.setId(cursor.getString(SongCol.SONG_NUM_COL));
                song.setVol(cursor.getString(SongCol.SONG_VOL_COL));
                song.setSongName(cursor.getString(SongCol.SONG_NAME_COL));
                song.setSongLyric(cursor.getString(SongCol.SONG_LYRIC_COL));
                song.setSongAuthor(cursor.getString(SongCol.SONG_AUTHOR_COL));
                songList.add(song);
            }while(cursor.moveToNext());
        }

        Log.d("All", songList.get(0).getSongName());
        return songList;
    }
}