/**
 * 
 * example of the common use.

	IWriteOnGuide db = GuideDataBase.getInstance(this);
	...
	..
	...
	db.beginTransaction();
	try{
		// todas las operaciones de escritura sobre la db : delete, update y insert
		...
		..
		...
		db.commit();
	} catch (SQLException e) {
		Log.i("IWriteOnGUide", "contained execption");
	} finally {
     		db.endTransaction();
	}
	
 *	
 *	example of use: In class that extends to Activity or FragmentActivity.  
 	IReadDataOfGuideDataBase db = new GuideDataBase(context);
    SqliteCursor cursor = (AdvertisementCursor) db.getAdvertisement(id_category);
  	...
  	..
  	for (int i = 0 ; i < cursor.getCounr() ; i++){
  		cursor.moveToPosition(i); 
 		doSomething..
  	}
 	...
 	..
	
 */
package rio.cuarto.database;

import java.io.IOException;

import rio.cuarto.utilities.DbUtilities;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author edu
 * 
 */
public class GuideDataBase extends SQLiteOpenHelper implements IWriteOnGuide,IReadDataOfGuideDataBase {

	private static final int DATABASE_VERSION = 1;

	private static GuideDataBase instance = null;

	/** Keep track of context so that we can load SQL from string resources */
	private Context mContext;

	/*
	 * keep db for transaction : this db is only used to delete, upadte and
	 * insert
	 */
	private SQLiteDatabase db;

	/* constructor singleton */
	private GuideDataBase(Context context) {
		super(context, DbUtilities.DB_NAME, null, DATABASE_VERSION);
		this.mContext = context;
		this.onCreate(null);
	}

	public static GuideDataBase getInstance(Context context) {
		if (instance == null) {
			instance = new GuideDataBase(context);
		}
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			DbUtilities.createDatabaseIfNotExists(mContext);
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			DbUtilities.createDatabaseIfNotExists(mContext);
		} catch (IOException ioe) {
			throw new Error("Unable to update database");
		}
	}

	
	/***********************************************************
	 * Implementation of the interface IReadDataOfGuideDataBase*
	 ***********************************************************/

	/* 
	 * @see rio.cuarto.database.IReadDataOfGuideDataBase#getAdvertisement(long)
	 */
	public AdvertisementCursor getAdvertisement(long category) {
		String sql = AdvertisementCursor.QUERY + category + ") ORDER BY title";
		SQLiteDatabase d = getReadableDatabase();
		AdvertisementCursor c = (AdvertisementCursor) d.rawQueryWithFactory(
				new AdvertisementCursor.Factory(), sql,
				null, null);
		c.moveToFirst();
		return c;
	}

	/* 
	 * @see rio.cuarto.database.IReadDataOfGuideDataBase#getCategory()
	 */
	public CategoryCursor getCategory(){
		String sql = CategoryCursor.QUERY;
		SQLiteDatabase d = getReadableDatabase();
		CategoryCursor c = (CategoryCursor) d.rawQueryWithFactory(
				new CategoryCursor.Factory(), sql,
				null, null);
		c.moveToFirst();
		return c;
	}
	

	/*******************************************************
	 * Implementation of the interface IWriteOnGuide       *
	 *******************************************************/

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#addAdvertisement(long,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void addAdvertisement(long id, String title, String description,
			String image, String address, String phone, String geoPosition)
			throws SQLException {
		String sql = "INSERT INTO advertisement (id_adv , title , address , description , phone , image , geo_position)"
				+ " VALUES (?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[] { id, title, address, description,
				phone, image, geoPosition };
		try {
			db.execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error writing new advertisement", e.toString());
			throw new SQLException();
		}
	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#updateAdvertisement()
	 */
	@Override
	public void updateAdvertisement(long id, String title, String description,
			String image, String address, String phone, String geoPosition)
			throws SQLException {
		String sql = "UPDATE advertisement "
				+ "SET title = ? , address = ? , description = ?  , phone = ? , image = ? , geo_position = ? "
				+ "WHERE id_adv = ? ";
		Object[] bindArgs = new Object[] { title, address, description, phone,
				image, geoPosition, id };
		try {
			db.execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error update advertisement", e.toString());
			throw new SQLException();
		}

	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#deleteAdvertisement(long)
	 */
	@Override
	public void deleteAdvertisement(long id) throws SQLException {
		String sql = String.format("DELETE FROM advertisement "
				+ "WHERE id_adv = '%d' ", id);
		try {
			db.execSQL(sql);
		} catch (SQLException e) {
			Log.e("Error deleteing advertisement", e.toString());
			throw new SQLException();
		}

	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#addCategory(long,
	 * java.lang.String)
	 */
	@Override
	public void addCategory(long id, String name) throws SQLException {
		String sql = "INSERT INTO category (id_category, name)"
				+ " VALUES (?,?)";
		Object[] bindArgs = new Object[] { id, name };
		try {
			db.execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error writing new category", e.toString());
			throw new SQLException();
		}
	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#updateCategory(long,
	 * java.lang.String)
	 */
	@Override
	public void updateCategory(long id, String name) throws SQLException {
		String sql = "UPDATE category " + "SET name = ? "
				+ " WHERE id_category = ? ";
		Object[] bindArgs = new Object[] { name, id };
		try {
			db.execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error update category", e.toString());
			throw new SQLException();
		}

	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#deleteCategory(long)
	 */
	@Override
	public void deleteCategory(long id) throws SQLException {
		String sql = String.format("DELETE FROM category "
				+ "WHERE id_category = '%d' ", id);
		try {
			db.execSQL(sql);
		} catch (SQLException e) {
			Log.e("Error deleteing category", e.toString());
			throw new SQLException();
		}

	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#addSubCategory(long, long)
	 */
	@Override
	public void addSubCategory(long catId, long subcatId) throws SQLException {
		String sql = "INSERT INTO sub_category (id_category,id_sub_category)"
				+ " VALUES (?,?)";
		Object[] bindArgs = new Object[] { catId, subcatId };
		try {
			db.execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error writing new subCategory", e.toString());
			throw new SQLException();
		}

	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#deleteSubCategory(long, long)
	 */
	@Override
	public void deleteSubCategory(long catId, long subcatId)
			throws SQLException {
		String sql = String.format("DELETE FROM sub_category "
				+ "WHERE id_category = '%d' and id_sub_category = '%d' ",
				catId, subcatId);
		try {
			db.execSQL(sql);
		} catch (SQLException e) {
			Log.e("Error deleteing subCategory", e.toString());
			throw new SQLException();
		}

	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#addAdvHaveCategory(long, long)
	 */
	@Override
	public void addAdvHaveCategory(long advId, long catId) throws SQLException {
		String sql = "INSERT INTO adv_have_category (id_adv_have , id_category_have)"
				+ " VALUES (?,?)";
		Object[] bindArgs = new Object[] { advId, catId };
		try {
			db.execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error writing new advertisementHaveCategory", e.toString());
			throw new SQLException();
		}

	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#deleteAdvHaveCategory(long, long)
	 */
	@Override
	public void deleteAdvHaveCategory(long advId, long catId)
			throws SQLException {
		String sql = String.format("DELETE FROM adv_have_category "
				+ "WHERE id_adv_have = '%d' and id_category_have = '%d' ",
				advId, catId);
		try {
			db.execSQL(sql);
		} catch (SQLException e) {
			Log.e("Error deleteing subCategory", e.toString());
			throw new SQLException();
		}

	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#beginTransaction()
	 */
	@Override
	public void beginTransaction() {
		db = this.getWritableDatabase();
		db.beginTransaction();
	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#commit()
	 */
	@Override
	public void commit() {
		db.setTransactionSuccessful();
	}

	/*
	 * @see rio.cuarto.database.IWriteOnGuide#endTransaction()
	 */
	@Override
	public void endTransaction() {
		db.endTransaction();
		db = null;
	}
}
