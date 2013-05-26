/**
 * 
 */
package rio.cuarto.service;

import org.json.JSONArray;

import rio.cuarto.database.GuideDataBase;
import rio.cuarto.database.IWriteOnGuide;
import rio.cuarto.guide.R;
import rio.cuarto.utilities.Constants;
import rio.cuarto.utilities.ServiceUtilities;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.util.Log;

/**
 * @author edu
 * 
 */
public class GuideUpdateService extends IntentService {

	private BroadcastNotifier mBroadcaster = new BroadcastNotifier(this);

	public GuideUpdateService() {
		super("GuideUpdateService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {

		// the version of me database in phone.
		int version = arg0.getIntExtra(Constants.VERSION_DB, 1);

		// connection to server and get the data
		byte[] byteArray = ServiceUtilities.getDataIS(version, mBroadcaster);

		GuideJsonParser jParser = new GuideJsonParser(byteArray);
		IWriteOnGuide db = GuideDataBase.getInstance(this);
		CommitData commitData = new CommitData(db);
		JSONArray jArray;

		// start transaction
		db.beginTransaction();
		mBroadcaster
				.broadcastIntentWithState(Constants.STATE_BEGIN_TRANSACTION);

		try {
			// all the operations on db : delete, update y insert

			// ----------category--------------------
			jArray = jParser.getCategoryAdd();
			commitData.categoryAdd(jArray);

			jArray = jParser.getCategoryUpdate();
			commitData.categoryUpdate(jArray);

			jArray = jParser.getCategoryDelete();
			commitData.categoryDelete(jArray);

			// -----------subCategorty----------------
			jArray = jParser.getSubCategoryAdd();
			commitData.subCategoryAdd(jArray);

			jArray = jParser.getSubCategoryDelete();
			commitData.subCategoryDelete(jArray);

			// ----------advertisement ---------------
			jArray = jParser.getAdvertisementAdd();
			commitData.advertisementAdd(jArray);

			jArray = jParser.getAdvertisementUpdate();
			commitData.advertisementUpdate(jArray);

			jArray = jParser.getAdvertisementDelete();
			commitData.advertisementDelete(jArray);

			// -----------have------------------------
			jArray = jParser.getHaveAdd();
			commitData.AdvHaveCategoryAdd(jArray);

			jArray = jParser.getHaveDelete();
			commitData.AdvHaveCategoryDelete(jArray);

			db.commit();
			// set to version of database
			saveVersionDB(5);
			mBroadcaster
					.broadcastIntentWithState(Constants.STATE_END_TRANSACTION);
		} catch (SQLException e) {
			Log.i("IWriteOnGUide", "contained exception");
			mBroadcaster
					.broadcastIntentWithState(Constants.STATE_FAIL_TRANSACTION);
		} finally {
			db.endTransaction();
		}
	}

	/*
	 * @param version of database that get of service and stored in phone 
	 */
	private void saveVersionDB(int version) {
		SharedPreferences sP = getApplicationContext().getSharedPreferences(
				Constants.VERSION_DB_FILE, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sP.edit();
		editor.putInt(Constants.VERSION_DB, version);
		editor.commit();
	}

}
