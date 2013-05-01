/**
 * 
 */
package rio.cuarto.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * @author edu
 * 
 */
public class GuideUpdateService extends IntentService {

	public GuideUpdateService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
	}

	// ********ejemplo de iteracion para guardar datos en la base de datos

	// byte[] byteArray = Utils.getInputStream(params[0]);

	// GuideJsonParser guideJsonParser = new GuideJsonParser(byteArray);

	// IWriterOnGuide dbWriter = GuideDataBase.getInstance(this);
	// CommitData commitData = new CommitData(dbWriter);
	// JsonArray jsonArray;
	// dbWriter.beginTransaction();
	// try{
	// // all the operations on db : delete, update y insert
	// jsonArray = guideJsonParser.getCategoryAdd();
	// commitData.categoryAdd(jsonArray);
	// ..
	// ...
	// db.commit();
	// } catch (SQLException e) {
	// Log.i("IWriteOnGUide", "contained exception");
	// } finally {
	// db.endTransaction();
	// }
	
}
