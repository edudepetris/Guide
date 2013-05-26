package rio.cuarto.guide;

import rio.cuarto.service.GuideUpdateService;
import rio.cuarto.utilities.Constants;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// The filter's action is BROADCAST_ACTION
		IntentFilter statusIntentFilter = new IntentFilter(
				Constants.BROADCAST_ACTION);

		// Sets the filter's category to DEFAULT
		statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);

		// Instantiates a new DownloadStateReceiver
		UpdateBroadcastReceiver receiver;
		receiver = new UpdateBroadcastReceiver();

		// Registers the DownloadStateReceiver and its intent filters
		LocalBroadcastManager.getInstance(this).registerReceiver(receiver,
				statusIntentFilter);

		// write my database version
		SharedPreferences sharedPref = this.getSharedPreferences(
				Constants.VERSION_DB_FILE, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(Constants.VERSION_DB, Constants.DEFAULT_VERSION_DB);
		editor.commit();
	}

	public void update(View view) {
		Intent intent = new Intent(getApplicationContext(),
				GuideUpdateService.class);

		int myVersionDatabase;

		// shared preference file that belongs to the activity, you don't need
		// to supply a name.
		SharedPreferences sharedPref = this.getSharedPreferences(
				Constants.VERSION_DB_FILE, Context.MODE_PRIVATE);

		// read my database version
		myVersionDatabase = sharedPref.getInt(Constants.VERSION_DB,
				Constants.DEFAULT_VERSION_DB);
		intent.putExtra(Constants.VERSION_DB, myVersionDatabase);
		this.startService(intent);
	}

	public class UpdateBroadcastReceiver extends BroadcastReceiver {

		/**
		 * 
		 * This method is called by the system when a broadcast Intent is
		 * matched by this class' intent filters
		 * 
		 * @param context
		 *            An Android context
		 * @param intent
		 *            The incoming broadcast Intent
		 */
		@Override
		public void onReceive(Context context, Intent intent) {

			/*
			 * Gets the status from the Intent's extended data, and chooses the
			 * appropriate action
			 */
			int status = intent.getIntExtra(Constants.EXTENDED_DATA_STATUS,
					Constants.STATE_DEAFULT_VALUE);
			switch (status) {

			case Constants.STATE_BEGIN_TRANSACTION: {
				Log.d("MAIN", "State: empezando a guardar");
			}
				break;

			case Constants.STATE_END_TRANSACTION: {
				Log.d("MAIN", "State: teminando de guardar");
			}
				break;

			case Constants.STATE_FAIL_TRANSACTION: {
				Log.d("MAIN", "State: fallo el  guardar");
			}
				break;
			}

		}

	}

}
