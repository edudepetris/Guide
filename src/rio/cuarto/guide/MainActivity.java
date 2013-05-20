package rio.cuarto.guide;

import org.json.JSONArray;

import rio.cuarto.database.GuideDataBase;
import rio.cuarto.database.IWriteOnGuide;
import rio.cuarto.service.CommitData;
import rio.cuarto.service.GuideJsonParser;
import rio.cuarto.utilities.Utils;
import android.app.Activity;
import android.app.ProgressDialog;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final String url = "http://damp-reef-9737.herokuapp.com/update/1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// para poder poder porbar y lanzar la actualizacion solo se debe insertar
	// un boton (con onClick="update") en el Activiy main
	public void update(View view) {
		if (Utils.isNetworkAvailable(MainActivity.this)) {
			new MyTask().execute(url);
		} else {
			showToast("No Network Connection!!!");
		}
	}

	public void showToast(String msg) {
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// My AsyncTask start...

	class MyTask extends AsyncTask<String, Void, byte[]> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Loading...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected byte[] doInBackground(String... params) {
			return Utils.getInputStream(params[0]);
		}

		@Override
		protected void onPostExecute(byte[] byteArray) {
			super.onPostExecute(byteArray);

			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}
			// ver bien el inputstream xq no va el tostring
			if (null == byteArray || byteArray.toString().length() == 0) {
				showToast("No data found from web!!!");
				MainActivity.this.finish();
			} else {
				GuideJsonParser guideJsonParser = new GuideJsonParser(byteArray);
				IWriteOnGuide dbWriter = GuideDataBase
						.getInstance(getApplicationContext());
				CommitData commitData = new CommitData(dbWriter);

				JSONArray jsonArray;
				dbWriter.beginTransaction();
				try {
					// all the operations on db : delete, update y insert

					// ----------category------------------------------
					jsonArray = guideJsonParser.getCategoryAdd();
					commitData.categoryAdd(jsonArray);

					jsonArray = guideJsonParser.getCategoryUpdate();
					commitData.categoryUpdate(jsonArray);

					jsonArray = guideJsonParser.getCategoryDelete();
					commitData.categoryDelete(jsonArray);

					// -----------subCategorty-----------------
					jsonArray = guideJsonParser.getSubCategoryAdd();
					commitData.subCategoryAdd(jsonArray);

					jsonArray = guideJsonParser.getSubCategoryDelete();
					commitData.subCategoryDelete(jsonArray);

					// ----------advertisement ----------------------
					jsonArray = guideJsonParser.getAdvertisementAdd();
					commitData.advertisementAdd(jsonArray);

					jsonArray = guideJsonParser.getAdvertisementUpdate();
					commitData.advertisementUpdate(jsonArray);

					jsonArray = guideJsonParser.getAdvertisementDelete();
					commitData.advertisementDelete(jsonArray);

					// -----------have------------------------------

					jsonArray = guideJsonParser.getHaveAdd();
					commitData.AdvHaveCategoryAdd(jsonArray);

					jsonArray = guideJsonParser.getHaveDelete();
					commitData.AdvHaveCategoryDelete(jsonArray);

					dbWriter.commit();
				} catch (SQLException e) {
					Log.i("IWriteOnGUide", "contained exception");
				} finally {
					dbWriter.endTransaction();
				}
			}
		}
	}

}
