/**
 * 
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
public class GuideDataBase extends SQLiteOpenHelper implements IWriteOnGuide {

	private static final int DATABASE_VERSION = 1;

	/** Keep track of context so that we can load SQL from string resources */
	private Context mContext;

	/* constructor */
	public GuideDataBase(Context context) {
		super(context, DbUtilities.DB_NAME, null, DATABASE_VERSION);
		this.mContext = context;
		this.onCreate(null);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#addAdvertisement(long,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addAdvertisement(long id, String title, String description,
			String image, String address, String phone, String geoPosition) {
		String sql = "INSERT INTO advertisement (id_adv , title , address , description , phone , image , geo_position)"
				+ " VALUES (?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[] { id, title, address, description,
				phone, image, geoPosition };
		try {
			getWritableDatabase().execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error writing new advertisement", e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#updateAdvertisement()
	 */
	@Override
	public boolean updateAdvertisement(long id, String title,
			String description, String image, String address, String phone,
			String geoPosition) {
		String sql = "UPDATE advertisement "
				+ "SET title = ? , address = ? , description = ?  , phone = ? , image = ? , geo_position = ? "
				+ "WHERE id_adv = ? ";
		Object[] bindArgs = new Object[] { title, address, description, phone,
				image, geoPosition, id };
		try {
			getWritableDatabase().execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error update advertisement", e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#deleteAdvertisement(long)
	 */
	@Override
	public boolean deleteAdvertisement(long id) {
		String sql = String.format("DELETE FROM advertisement "
				+ "WHERE id_adv = '%d' ", id);
		try {
			getWritableDatabase().execSQL(sql);
		} catch (SQLException e) {
			Log.e("Error deleteing advertisement", e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#addCategory(long,
	 * java.lang.String)
	 */
	@Override
	public boolean addCategory(long id, String name) {
		String sql = "INSERT INTO category (id_category, name)"
				+ " VALUES (?,?)";
		Object[] bindArgs = new Object[] { id, name };
		try {
			getWritableDatabase().execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error writing new category", e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#updateCategory(long,
	 * java.lang.String)
	 */
	@Override
	public boolean updateCategory(long id, String name) {
		String sql = "UPDATE category " + "SET name = ? "
				+ " WHERE id_category = ? ";
		Object[] bindArgs = new Object[] { name, id };
		try {
			getWritableDatabase().execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error update category", e.toString());
			return false;
		}
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#deleteCategory(long)
	 */
	@Override
	public boolean deleteCategory(long id) {
		String sql = String.format("DELETE FROM category "
				+ "WHERE id_category = '%d' ", id);
		try {
			getWritableDatabase().execSQL(sql);
		} catch (SQLException e) {
			Log.e("Error deleteing category", e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#addSubCategory(long, long)
	 */
	@Override
	public boolean addSubCategory(long catId, long subcatId) {
		String sql = "INSERT INTO sub_category (id_category,id_sub_category)"
				+ " VALUES (?,?)";
		Object[] bindArgs = new Object[] { catId, subcatId };
		try {
			getWritableDatabase().execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error writing new subCategory", e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#deleteSubCategory(long, long)
	 */
	@Override
	public boolean deleteSubCategory(long catId, long subcatId) {
		String sql = String.format("DELETE FROM sub_category "
				+ "WHERE id_category = '%d' and id_sub_category = '%d' ",
				catId, subcatId);
		try {
			getWritableDatabase().execSQL(sql);
		} catch (SQLException e) {
			Log.e("Error deleteing subCategory", e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#addAdvHaveCategory(long, long)
	 */
	@Override
	public boolean addAdvHaveCategory(long advId, long catId) {
		String sql = "INSERT INTO adv_have_category (id_adv_have , id_category_have)"
				+ " VALUES (?,?)";
		Object[] bindArgs = new Object[] { advId, catId };
		try {
			getWritableDatabase().execSQL(sql, bindArgs);
		} catch (SQLException e) {
			Log.e("Error writing new advertisementHaveCategory", e.toString());
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#deleteAdvHaveCategory(long, long)
	 */
	@Override
	public boolean deleteAdvHaveCategory(long advId, long catId) {
		String sql = String.format("DELETE FROM adv_have_category "
				+ "WHERE id_adv_have = '%d' and id_category_have = '%d' ",
				advId, catId);
		try {
			getWritableDatabase().execSQL(sql);
		} catch (SQLException e) {
			Log.e("Error deleteing subCategory", e.toString());
			return false;
		}
		return true;
	}
}
