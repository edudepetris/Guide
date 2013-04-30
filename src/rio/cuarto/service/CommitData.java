package rio.cuarto.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rio.cuarto.database.IWriteOnGuide;

/**
 * CommitData es la clase encargada de guardar los datos en la base de datos
 * usando la interface IWeiteOnGuide ,cada metodo toma un JSONArray que trae los
 * datos especificos para cada metodo
 * 
 * 
 */
public class CommitData {
	// key of the Category object Json
	private final String CATEGORY_ID = "id_category";
	private final String CATEGORY_NAME = "name";
	// key of de CategoryDelete object json
	private final String CATEGORY_DELETE_ID = "id_category_delete";

	// key for switch mothod the IWriteOnGuide
	private final int CATEGORY_ADD = 0;
	private final int CATEGORY_DELETE = 1;
	private final int CATEGORY_UPDATE = 2;

	private IWriteOnGuide iwriteOnGuide;

	public CommitData(IWriteOnGuide iwriteOnGuide) {
		this.iwriteOnGuide = iwriteOnGuide;
	}

	// --------------- category ---------------------------

	// guarda en la base de datos categorias nuevas , en objeto Json viene con
	// todas las categorias a guardar
	public void categoryAdd(JSONArray guideJsonArray) {
		String[] attr = { CATEGORY_ID, CATEGORY_NAME };
		saveDataBase(guideJsonArray, attr, CATEGORY_ADD);
	}

	public void categoryDelete(JSONArray guideJsonArray) {
		String[] attr = { CATEGORY_DELETE_ID };
		saveDataBase(guideJsonArray, attr, CATEGORY_DELETE);
	}

	public void categoryUpdate(JSONArray guideJsonArray) {
		String[] attr = { CATEGORY_ID, CATEGORY_NAME };
		saveDataBase(guideJsonArray, attr, CATEGORY_UPDATE);
	}

	// ---------------------subcategory--------------------------------
	public void subCategoryAdd(JSONArray guideJsonArray) {
	}

	public void subCategoryDelete(JSONArray guideJsonArray) {
	}

	// ----------------advertisement--------------------------------
	public void advertisementAdd(JSONArray guideJsonArray) {
	}

	public void advertisementUpdate(JSONArray guideJsonArray) {
	}

	public void advertisementDelete(JSONArray guideJsonArray) {
	}

	// ---------------------HAVE-----------------------------------------

	public void AdvHaveCategoryAdd(JSONArray guideJsonArray) {
	}

	public void AdvHaveCategoryDelete(JSONArray guideJsonArray) {
	}

	// ---------------------------------------------------------------
	private boolean saveDataBase(JSONArray guideJsonArray, String[] date,
			int keyMethod) {
		boolean bool = false;
		try {
			for (int i = 0; i < guideJsonArray.length(); i++) {

				JSONObject objJson = guideJsonArray.getJSONObject(i);
				saveDataBaseObjectJson(keyMethod, date, objJson);
				bool = true;
			}
		} catch (JSONException e) {
			bool = false;
		}
		return bool;
	}

	private void saveDataBaseObjectJson(int keyMethod, String[] date,
			JSONObject objJson) throws JSONException {
		switch (keyMethod) {
		case CATEGORY_ADD:
			iwriteOnGuide.addCategory(objJson.getInt(date[0]),
					objJson.getString(date[1]));
			break;
		case CATEGORY_UPDATE:
			iwriteOnGuide.updateCategory(objJson.getInt(date[0]),
					objJson.getString(date[1]));
			break;
		case CATEGORY_DELETE:
			iwriteOnGuide.deleteCategory(objJson.getInt(date[0]));
			break;
		}
	}
}