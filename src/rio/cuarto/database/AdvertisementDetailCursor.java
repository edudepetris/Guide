/**
 * 
 */
package rio.cuarto.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/**
 * Provides self-contained query-specific cursor for Advertisement. The query
 * and all Accessor methods are in the class.
 */
public class AdvertisementDetailCursor extends SQLiteCursor {
	/** The query for this cursor */
	public static final String QUERY = "SELECT "
			+ "id_adv as _id,title,description,address,phone,image,geo_position "
			+ "FROM advertisement " + "WHERE id_adv = ";

	/** Cursor constructor */
	@SuppressWarnings("deprecation")
	AdvertisementDetailCursor(SQLiteDatabase db, SQLiteCursorDriver driver,
			String editTable, SQLiteQuery query) {
		super(db, driver, editTable, query);
	}

	/** Private factory class necessary for rawQueryWithFactory() call */
	public static class Factory implements SQLiteDatabase.CursorFactory {
		@Override
		public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver,
				String editTable, SQLiteQuery query) {
			return new AdvertisementDetailCursor(db, driver, editTable, query);
		}
	}

	/* Accessor functions -- one per database column */
	public long getColId() {
		return getLong(getColumnIndexOrThrow("_id"));
	}

	public String getColTitle() {
		return getString(getColumnIndexOrThrow("title"));
	}

	public String getColAddress() {
		return getString(getColumnIndexOrThrow("address"));
	}

	public String getColDescription() {
		return getString(getColumnIndexOrThrow("description"));
	}

	public String getColPhone() {
		return getString(getColumnIndexOrThrow("phone"));
	}

	public String getColImage() {
		return getString(getColumnIndexOrThrow("image"));
	}

	public String getColGeoPosition() {
		return getString(getColumnIndexOrThrow("geo_position"));
	}

}
