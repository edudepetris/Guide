/**
 * 
 */
package rio.cuarto.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

import rio.cuarto.database.GuideDataBase;
import rio.cuarto.database.IWriteOnGuide;
import rio.cuarto.utilities.Constants;
import android.app.IntentService;
import android.content.Intent;
import android.database.SQLException;
import android.util.Log;

/**
 * @author edu
 * 
 */
public class GuideUpdateService extends IntentService {

	private final String MY_URL = "http://damp-reef-9737.herokuapp.com/update/1";
    private BroadcastNotifier mBroadcaster = new BroadcastNotifier(this);

	
	public GuideUpdateService() {
		super("GuideUpdateService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {

		// ********ejemplo de iteracion para guardar datos en la base de datos
		byte[] byteArray = getInputStream();

		GuideJsonParser jParser = new GuideJsonParser(byteArray);
		IWriteOnGuide db = GuideDataBase.getInstance(this);
		CommitData commitData = new CommitData(db);
		JSONArray jArray;

		// start transaction
		db.beginTransaction();
        mBroadcaster.broadcastIntentWithState(Constants.STATE_BEGIN_TRANSACTION);
		
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
			mBroadcaster.broadcastIntentWithState(Constants.STATE_END_TRANSACTION);
		} catch (SQLException e) {
			Log.i("IWriteOnGUide", "contained exception");
	        mBroadcaster.broadcastIntentWithState(Constants.STATE_FAIL_TRANSACTION);
		} finally {
			db.endTransaction();
		}
	}

	// provisorio
	public byte[] getInputStream() {
		String url = MY_URL;
		byte[] data = null;
		InputStream linkinStream = null;
		ByteArrayOutputStream bArrayOutputStream = null;
		HttpURLConnection linkConnection = null;
		try {
			URL linkurl = new URL(url);
			linkConnection = (HttpURLConnection) linkurl.openConnection();
			int responseCode = linkConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				linkinStream = linkConnection.getInputStream();
				bArrayOutputStream = new ByteArrayOutputStream();
				int j = 0;
				try {
					while ((j = linkinStream.read()) != -1) {
						bArrayOutputStream.write(j);
					}
					linkinStream.close();
					data = bArrayOutputStream.toByteArray();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (linkConnection != null) {
				linkConnection.disconnect();
			}
		}
		return data;
	}
}
