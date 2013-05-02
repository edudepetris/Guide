package rio.cuarto.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rio.cuarto.database.IWriteOnGuide;
import android.util.Log;

/**
 * the CommitData class is responsible for saving the data in the database   
 * using IWeiteOnGuide interface, each method takes a JSONArray which brings the
 * specific data for each method
 */
public class CommitData {
	private final String LOG = "COMMITE DATA";

	// the key to json objects to add or update category on the data base
	private final String CATEGORY_ID = "id_category";
	private final String CATEGORY_NAME = "name";
	// the key to json objects to delete category on the data base
	private final String CATEGORY_DELETE_ID = "id_category_delete";
	// the key to json objects to add or delete subcategory on the data base
	private final String SUB_CATEGORY_ADD_ID = "id_category";
	private final String SUB_CATEGORY_ADD_ID_SUB = "id_sub_category";
	private final String SUB_CATEGORY_DELETE_ID = "id_category_delete";
	private final String SUB_CATEGORY_DELETE_ID_SUB = "id_sub_category_delete";
	// the key to json objects to add or update advertisement on the data base
	private final String ADVERTISEMENT_ID = "id_adv";
	private final String ADVERTISEMENT_TITLE = "title";
	private final String ADVERTISEMENT_DESCRIPTION = "description";
	private final String ADVERTISEMENT_IMAGE = "image";
	private final String ADVERTISEMENT_ADDRESS = "address";
	private final String ADVERTISEMENT_PHONE = "phone";
	private final String ADVERTISEMENT_GEO_POSTION = "geo_position";
	// the key to json objects to delete advertisment on the data base
	private final String ADVERTISEMENT_DELETE_ID = "id_adv_delete";
	// the key to json objects to add have on the data base
	private final String HAVE_ADD_ID_ADVERTISEMENT = "id_adv_have";
	private final String HAVE_ADD_ID_CATEGORY = "id_category_have";
	// the key to json objects to delete have the data base
	private final String HAVE_DELETE_ID_ADVERTISEMENT = "id_adv_have_delete";
	private final String HAVE_DELETE_ID_CATEGORY = "id_category_have_delete";

	// the keys to switch to a method specific selection of IWriteOnGuide
	// inferface
	private final int CATEGORY_ADD = 0;
	private final int CATEGORY_DELETE = 1;
	private final int CATEGORY_UPDATE = 2;
	private final int SUB_CATEGORY_ADD = 3;
	private final int SUB_CATEGORY_DELETE = 4;
	private final int ADVERTISEMENT_ADD = 5;
	private final int ADVERTISEMENT_DELETE = 6;
	private final int ADVERTISEMENT_UPDATE = 7;
	private final int HAVE_ADD = 8;
	private final int HAVE_DELETE = 9;

	private IWriteOnGuide writeOnGuide;

	public CommitData(IWriteOnGuide writeOnGuide) {
		this.writeOnGuide = writeOnGuide;
	}

	// --------------- category ---------------------------

	// stored in the database new categorys,a Json object comes with all
	// categories to add
	public void categoryAdd(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { CATEGORY_ID, CATEGORY_NAME };
			saveDataBase(guideJsonArray, attr, CATEGORY_ADD);
		}
	}

	public void categoryDelete(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { CATEGORY_DELETE_ID };
			saveDataBase(guideJsonArray, attr, CATEGORY_DELETE);
		}
	}

	public void categoryUpdate(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { CATEGORY_ID, CATEGORY_NAME };
			saveDataBase(guideJsonArray, attr, CATEGORY_UPDATE);
		}
	}

	// ---------------------subcategory--------------------------------
	public void subCategoryAdd(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { SUB_CATEGORY_ADD_ID, SUB_CATEGORY_ADD_ID_SUB };
			saveDataBase(guideJsonArray, attr, SUB_CATEGORY_ADD);
		}
	}

	public void subCategoryDelete(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { SUB_CATEGORY_DELETE_ID,
					SUB_CATEGORY_DELETE_ID_SUB };
			saveDataBase(guideJsonArray, attr, SUB_CATEGORY_DELETE);
		}
	}

	// ----------------advertisement--------------------------------

	public void advertisementAdd(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { ADVERTISEMENT_ID, ADVERTISEMENT_TITLE,
					ADVERTISEMENT_DESCRIPTION, ADVERTISEMENT_IMAGE,
					ADVERTISEMENT_ADDRESS, ADVERTISEMENT_PHONE,
					ADVERTISEMENT_GEO_POSTION };
			saveDataBase(guideJsonArray, attr, ADVERTISEMENT_ADD);
		}
	}

	public void advertisementUpdate(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { ADVERTISEMENT_ID, ADVERTISEMENT_TITLE,
					ADVERTISEMENT_DESCRIPTION, ADVERTISEMENT_IMAGE,
					ADVERTISEMENT_ADDRESS, ADVERTISEMENT_PHONE,
					ADVERTISEMENT_GEO_POSTION };
			saveDataBase(guideJsonArray, attr, ADVERTISEMENT_UPDATE);
		}
	}

	public void advertisementDelete(JSONArray guideJsonArray) {
		Log.i(LOG, "advertisementDelete antes " + guideJsonArray.length());
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { ADVERTISEMENT_DELETE_ID };
			saveDataBase(guideJsonArray, attr, ADVERTISEMENT_DELETE);
		}
	}

	// ---------------------HAVE-----------------------------------------

	public void AdvHaveCategoryAdd(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { HAVE_ADD_ID_ADVERTISEMENT, HAVE_ADD_ID_CATEGORY };
			saveDataBase(guideJsonArray, attr, HAVE_ADD);
		}
	}

	public void AdvHaveCategoryDelete(JSONArray guideJsonArray) {
		if (!isEmptyJsonArray(guideJsonArray)) {
			String[] attr = { HAVE_DELETE_ID_ADVERTISEMENT,
					HAVE_DELETE_ID_CATEGORY };
			saveDataBase(guideJsonArray, attr, HAVE_DELETE);
		}
	}

	// ---------------------------------------------------------------

	private boolean isEmptyJsonArray(JSONArray guideJsonArray) {
		return guideJsonArray == null || guideJsonArray.length() <= 0;
	}

	/**
	 * stored in the information database of a json object array
	 */
	private void saveDataBase(JSONArray guideJsonArray, String[] data,
			int keyMethod) {
		try {
			for (int i = 0; i < guideJsonArray.length(); i++) {
				JSONObject objJson = guideJsonArray.getJSONObject(i);
				saveDataBaseObjectJson(keyMethod, data, objJson);
			}
		} catch (JSONException e) {
			Log.e("ERROR saveDataBAse", e.toString());
		}
	}

	/**
	 * stored in the information database of a json object
	 * 
	 * @param keyMethod
	 *            is a method that allows you to select the correct method to
	 *            store in the database
	 * 
	 * @param date
	 *            is an array containing the keys to extract the data from json
	 *            object
	 * 
	 * @param objJson
	 *            is a json object
	 * @throws JSONException
	 */
	private void saveDataBaseObjectJson(int keyMethod, String[] date,
			JSONObject objJson) throws JSONException {
		switch (keyMethod) {
		case CATEGORY_ADD:
			writeOnGuide.addCategory(objJson.getInt(date[0]),
					objJson.getString(date[1]));
			break;
		case CATEGORY_UPDATE:
			writeOnGuide.updateCategory(objJson.getInt(date[0]),
					objJson.getString(date[1]));
			break;
		case CATEGORY_DELETE:
			writeOnGuide.deleteCategory(objJson.getInt(date[0]));
			break;
		case SUB_CATEGORY_ADD:
			writeOnGuide.addSubCategory(objJson.getInt(date[0]),
					objJson.getInt(date[1]));
			break;
		case SUB_CATEGORY_DELETE:
			writeOnGuide.deleteSubCategory(objJson.getInt(date[0]),
					objJson.getInt(date[1]));
			break;

		case ADVERTISEMENT_ADD:
			writeOnGuide.addAdvertisement(objJson.getInt(date[0]),
					objJson.getString(date[1]), objJson.getString(date[2]),
					objJson.getString(date[3]), objJson.getString(date[4]),
					objJson.getString(date[5]), objJson.getString(date[6]));
			break;

		case ADVERTISEMENT_UPDATE:
			writeOnGuide.updateAdvertisement(objJson.getInt(date[0]),
					objJson.getString(date[1]), objJson.getString(date[2]),
					objJson.getString(date[3]), objJson.getString(date[4]),
					objJson.getString(date[5]), objJson.getString(date[6]));
			break;
		case ADVERTISEMENT_DELETE:
			writeOnGuide.deleteAdvertisement(objJson.getInt(date[0]));
			break;
		case HAVE_ADD:
			writeOnGuide.addAdvHaveCategory(objJson.getInt(date[0]),
					objJson.getInt(date[1]));
			break;
		case HAVE_DELETE:
			writeOnGuide.deleteAdvHaveCategory(objJson.getInt(date[0]),
					objJson.getInt(date[1]));
			break;
		}
	}
}