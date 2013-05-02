package rio.cuarto.database;

public interface IReadDataOfGuideDataBase {

	/**
	 * @param category
	 *            id of the category
	 * @return AdvertisementCursor for all Advertisement that belongs a category
	 */
	public AdvertisementCursor getAdvertisement(long category);

	/**
	 * All category that does not have subcategory.
	 * 
	 * @return CategoryCursor
	 */
	public CategoryCursor getCategory();

}
