/**
 * 
 */
package rio.cuarto.database;

import java.io.IOException;

import rio.cuarto.utilities.DbUtilities;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author edu
 * 
 */
public class GuideDataBase extends SQLiteOpenHelper implements IWriteOnGuide {

	/** The version of the database that this class understands. */
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
			String imagem, String address, String phone, String geoPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#updateAdvertisement(long)
	 */
	@Override
	public boolean updateAdvertisement(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#deleteAdvertisement(long)
	 */
	@Override
	public boolean deleteAdvertisement(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#addCategory(long,
	 * java.lang.String)
	 */
	@Override
	public boolean addCategory(long id, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#updateCategory(long,
	 * java.lang.String)
	 */
	@Override
	public boolean updateCategory(long id, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#deleteCategory(long)
	 */
	@Override
	public boolean deleteCategory(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#addSubCategory(long, long)
	 */
	@Override
	public boolean addSubCategory(long catId, long subcatId) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#deleteSubCategory(long, long)
	 */
	@Override
	public boolean deleteSubCategory(long catId, long subcatId) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#addAdvHaveCategory(long, long)
	 */
	@Override
	public boolean addAdvHaveCategory(long advId, long catId) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rio.cuarto.database.IWriteOnGuide#deleteAdvHaveCategory(long, long)
	 */
	@Override
	public boolean deleteAdvHaveCategory(long advId, long catId) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
