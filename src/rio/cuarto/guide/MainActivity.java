package rio.cuarto.guide;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
//		IReadDataOfGuideDataBase db = GuideDataBase.getInstance(this);
//		SQLiteCursor cursor = (CategoryCursor) db.getCategory();
//		
//		for (int i = 0; i < cursor.getCount(); i++) {
//			cursor.moveToPosition(i);
//			Log.i("Cursor Category",  " id : " + ((CategoryCursor) cursor).getColId()+" , name:" + ((CategoryCursor) cursor).getColName());
//		}
//		
//		cursor = db.getAdvertisement(1);
//		for (int i = 0; i < cursor.getCount(); i++) {
//			cursor.moveToPosition(i);
//			Log.i("Cursor Advertisement",  " id : " + ((AdvertisementCursor) cursor).getColId()+" , title:" + ((AdvertisementCursor) cursor).getColTitle());
//		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
