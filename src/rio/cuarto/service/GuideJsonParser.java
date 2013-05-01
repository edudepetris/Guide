package rio.cuarto.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * la clase GuideJsonParser proporciona arreglo de datos (JSONArray) especificos
 * ejemplo : getJSONArrayCategoryAdd() retorna todas las categorias a guardar
 * 
 */
public class GuideJsonParser {

	// key of the JSON objects the Rio Cuarto 2.0
	private final String CATEGORY_INSERT = "category_insert";
	private final String CATEGORY_DELETE = "category_delete";
	private final String CATEGORY_UPDATE = "category_update";

	private JSONObject guideJson;

	/** the constructor method
	 * @param byteArray =  is a array to byte, contain the data obtained the  web service
	 */
	public GuideJsonParser(byte[] byteArray) {

		try {
			guideJson = new JSONObject(new String(byteArray));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// get JSONArray of the Category

	public JSONArray getJSONArrayCategoryAdd() {
		return getJsonArray(CATEGORY_INSERT);
	}

	public JSONArray getJSONArrayCategoryDelete() {
		return getJsonArray(CATEGORY_DELETE);
	}

	public JSONArray getJSONArrayCategoryUpdate() {
		return getJsonArray(CATEGORY_UPDATE);
	}

	// get JSONArray of the SubCategory

	public JSONArray getJSONArraySubCategoryAdd() {
		return null;
	}

	public JSONArray getJSONArraySubCategoryDelete() {
		return null;
	}

	// get JSONArry of the Advertisement

	public JSONArray getJSONArrayAdvertisementAdd() {
		return null;
	}

	public JSONArray getJSONArrayAdvertisementDelete() {
		return null;
	}

	public JSONArray getJSONArrayAdvertisementUpdate() {
		return null;
	}

	// get JSONArray of the Have

	public JSONArray getJSONArrayHaveAdd() {
		return null;
	}

	public JSONArray getJSONArrayHaveDelete() {
		return null;
	}

	// metodo usado por los getJsonArray
	// toma un string que es la category o advertisement o have o subcategory
	// que indica que
	// JSONArray va retornar
	private JSONArray getJsonArray(String string) {
		JSONArray jsonArray = null;
		try {
			jsonArray = guideJson.getJSONArray(string);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}