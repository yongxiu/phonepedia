package cn.edu.nju.software.db;

import java.util.Date;

import cn.edu.nju.software.utils.Time;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	
	public static final String KEY_ROWID = "_id";

	public static final String KEY_NAME = "name";

	public static final String KEY_DATE = "date";

	private static final String TAG = "DBAdapter";

	private static final String DATABASE_NAME = "pedia.db";

	private static final String DATABASE_TABLE = "pedia";

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE =

	"create table pedia (_id integer primary key autoincrement, "

	+ "name text not null, date datetime not null);";

	private final Context context;

	private DatabaseHelper DBHelper;

	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {

		this.context = ctx;

		DBHelper = new DatabaseHelper(context);

	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {

			super(context, DATABASE_NAME, null, DATABASE_VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(DATABASE_CREATE);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion,

				int newVersion) {

			Log.w(TAG, "Upgrading database from version " + oldVersion

			+ " to "

			+ newVersion + ", which will destroy all old data");

			db.execSQL("DROP TABLE IF EXISTS pedia");

			onCreate(db);

		}

	}

	public DBAdapter open() throws SQLException {

		db = DBHelper.getWritableDatabase();

		return this;

	}

	// ---关闭数据库---

	public void close() {

		DBHelper.close();

	}

	// ---向数据库插入一个标题---

	public long insertPedia(String name, Date date) {

		Cursor mCursor = null;
		try {
			mCursor = db.query(true, DATABASE_TABLE, new String[] {
					KEY_ROWID, KEY_NAME, KEY_DATE },
					KEY_NAME + "= '" + name + "'",
					null, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		if(mCursor.getCount() != 0)
			return 0;
			
		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_NAME, name);

		initialValues.put(KEY_DATE, Time.getDateStr(date));

		return db.insert(DATABASE_TABLE, null, initialValues);

	}

	// ---删除一个指定的标题---

	public boolean deletePedia(long rowId) {

		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;

	}

	// ---检索所有标题---

	public Cursor getAllPedias() {
		return db.query(DATABASE_TABLE, new String[] {

		KEY_ROWID,

		KEY_NAME,

		KEY_DATE },

		null,

		null,

		null,

		null, "date desc");

	}

	// ---检索一个指定的标题---

	public Cursor getPedia(long rowId) throws SQLException {

		Cursor mCursor =

		db.query(true, DATABASE_TABLE, new String[] {

		KEY_ROWID,

		KEY_NAME,

		KEY_DATE

		},

		KEY_ROWID + "=" + rowId,

		null,

		null,

		null,

		null,

		null);

		if (mCursor != null) {

			mCursor.moveToFirst();

		}

		return mCursor;

	}

	// ---更新一个标题---

	public boolean updatePedia(long rowId, String name, Date date) {

		ContentValues args = new ContentValues();

		args.put(KEY_NAME, name);

		args.put(KEY_DATE, Time.getDateStr(date));

		return db.update(DATABASE_TABLE, args,

		KEY_ROWID + "=" + rowId, null) > 0;

	}

}