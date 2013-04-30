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

	// IwriteOnGuide iwriteOnGuide = new ImplementWriteOnGuide();

	// CommitData commitData = new CommitData(iwriteOnGuide);
	//
	// JSONArray categoryArrayAdd =
	// guideJsonParser.getJSONArrayCategoryInsert();
	// commitData.addCategoryInsert(categoryArray);

	// JSONArray categoryArrayDelete =
	// guideJsonParser.getJSONArrayCategoryDelete();
	// commitData.addCategoryInsert(categoryArrayDelete);

}
