package cz.fel.cvut.helpdeskclient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

	// Declaring Local Variables for database name , and database path and
	// sqlite object and my context

	private static String DB_PATH = "";
	private static String DB_NAME = "helpdeskandroid.db"; // i had created
																// this in my
	// assets folder
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	private static MyDBHelper mDBConnection;
	static int version_val = 1; // version of my table
	HelpPrinter help = new HelpPrinter();

	// Constructor which sends db name to SQLiteOpenHelper
	public MyDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.myContext = context;
		DB_PATH = "/data/data/"
				+ context.getApplicationContext().getPackageName()
				+ "/databases/";

	}
	
	public MyDBHelper(Context context, String name, CursorFactory factory,
			int version, String dbname) {
		super(context, name, factory, version);
		this.myContext = context;
		DB_PATH = "/data/data/"
				+ context.getApplicationContext().getPackageName()
				+ "/databases/";
		DB_NAME = dbname; 

	}

	// Used to get the Object
	public static synchronized MyDBHelper getDBAdapterInstance(Context context) {
		if (mDBConnection == null) {
			mDBConnection = new MyDBHelper(context, DB_NAME, null, version_val);
		}
		return mDBConnection;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// TODO Auto-generated method stub
		/*try {
			copyDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// TODO Auto-generated method stub

	}

	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		
		
		if (dbExist) {
			// do nothing - database already exist
			Log.d("dbExist"," I am here and listen");
		} else {
			// By calling following method
			// 1) an empty database will be created into the default system path
			// of your application
			// 2) than we overwrite that database with our database.
			this.getReadableDatabase();
			this.close();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DB_NAME;
			Log.d("MyPath", myPath);
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException {
		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;
		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void openDataBase() throws SQLException {
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	public ArrayList<ArrayList<String>> selectRecordsFromDBList(String query, String[] selectionArgs) {
		ArrayList<ArrayList<String>> retList = new ArrayList<ArrayList<String>>();
		ArrayList<String> list = new ArrayList<String>();
		Cursor cursor = myDataBase.rawQuery(query, selectionArgs);
		if (cursor.moveToFirst()) {
			do {
				list = new ArrayList<String>();
				for (int i = 0; i < cursor.getColumnCount(); i++) {
					list.add(cursor.getString(i));
				}
				retList.add(list);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return retList;
	}

	public long insertRecordsInDB(String tableName, String nullColumnHack, ContentValues initialValues) {
		
		return myDataBase.insert(tableName, nullColumnHack, initialValues);
	}

	public int deleteRecordInDB(String tableName, String whereClause, String[] string) {
		
		return myDataBase.delete(tableName, whereClause, string);

	}
	
	public int updateRecordInDB(String table,ContentValues values,String whereClause,String[] whereArgs)
	{
		return myDataBase.update(table, values, whereClause, whereArgs);
	}
}