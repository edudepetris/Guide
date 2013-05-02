package rio.cuarto.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/**
 * Provides self-contained query-specific cursor for Category. The query
 * and all Accessor methods are in the class.
 */
public class CategoryCursor extends SQLiteCursor {
	/** The query for this cursor */
	public static final String QUERY = "SELECT "
			+ "id_category as _id,name "
			+ "FROM category "
			+ "WHERE id_category NOT IN "
			+ " (SELECT id_sub_category FROM sub_category) " +
			"ORDER BY name";

	/** Cursor constructor */
	@SuppressWarnings("deprecation")
	CategoryCursor(SQLiteDatabase db, SQLiteCursorDriver driver,
			String editTable, SQLiteQuery query) {
		super(db, driver, editTable, query);
	}

	/** Private factory class necessary for rawQueryWithFactory() call */
	public static class Factory implements SQLiteDatabase.CursorFactory {
		@Override
		public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver,
				String editTable, SQLiteQuery query) {
			return new CategoryCursor(db, driver, editTable, query);
		}
	}

	/* Accessor functions -- one per database column */
	public long getColId() {
		return getLong(getColumnIndexOrThrow("_id"));
	}

	public String getColName() {
		return getString(getColumnIndexOrThrow("name"));
	}

	

}