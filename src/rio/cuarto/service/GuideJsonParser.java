package rio.cuarto.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * the GuideJsonParser class provides data array (JSONArray) specific,
 * example getJSONArrayCategoryAdd () returns all categories to save
 */
public class GuideJsonParser {

	// key of the JSON objects the Rio Cuarto 2.0
	private final String CATEGORY_INSERT = "category_insert";
	private final String CATEGORY_DELETE = "category_delete";
	private final String CATEGORY_UPDATE = "category_update";
	private final String SUB_CATEGORY_INSERT = "subcategory_insert";
	private final String SUB_CATEGORY_DELETE = "subcategory_delete";
	private final String ADVERTISEMENT_INSERT = "advertisement_insert";
	private final String ADVERTISEMENT_DELETE = "advertisement_delete";
	private final String ADVERTISEMENT_UPDATE = "advertisement_update";
	private final String HAVE_INSERT = "have_insert";
	private final String HAVE_DELETE = "have_delete";

	private JSONObject guideJson;

	/**
	 * the constructor method
	 * 
	 * @param byteArray
	 *            = is a array to byte, contain the data obtained the web
	 *            service
	 */
	public GuideJsonParser(byte[] byteArray) {
		try {
			guideJson = new JSONObject(new String(byteArray));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// get JSONArray of the Category

	public JSONArray getCategoryAdd() {
		return getJsonArray(CATEGORY_INSERT);
	}

	public JSONArray getCategoryDelete() {
		return getJsonArray(CATEGORY_DELETE);
	}

	public JSONArray getCategoryUpdate() {
		return getJsonArray(CATEGORY_UPDATE);
	}

	// get JSONArray of the SubCategory

	public JSONArray getSubCategoryAdd() {
		return getJsonArray(SUB_CATEGORY_INSERT);
	}

	public JSONArray getSubCategoryDelete() {
		return getJsonArray(SUB_CATEGORY_DELETE);
	}

	// get JSONArry of the Advertisement

	public JSONArray getAdvertisementAdd() {
		return getJsonArray(ADVERTISEMENT_INSERT);
	}

	public JSONArray getAdvertisementDelete() {
		return getJsonArray(ADVERTISEMENT_DELETE);
	}

	public JSONArray getAdvertisementUpdate() {
		return getJsonArray(ADVERTISEMENT_UPDATE);
	}

	// get JSONArray of the Have

	public JSONArray getHaveAdd() {
		return getJsonArray(HAVE_INSERT);
	}

	public JSONArray getHaveDelete() {
		return getJsonArray(HAVE_DELETE);
	}

	/*
	 * @param string = is a specified string to determine what kind of JSONArray
	 * will return, such a category,advertisement or subcategory
	 * 
	 * @return JSONArray
	 */
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