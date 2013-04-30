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

	public CommitData(IWriteOnGuide iwriteOnGuide) {
	}

	// --------------- category ---------------------------

	// guarda en la base de datos categorias nuevas , en objeto Json viene con
	// todas las categorias a guardar
	public void categoryAdd(JSONArray guideJsonArray) {

	}

	public void categoryDelete(JSONArray guideJsonArray) {
	}

	public void categoryUpdate(JSONArray guideJsonArray) {
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

}