package rio.cuarto.database;

public interface IReadDataOfGuideDataBase {

	/**
	 * @param category {@link Long}
	 *            id of the category
	 * @return {@link AdvertisementCursor} for all Advertisement that belongs a category
	 */
	public AdvertisementCursor getAdvertisements(long category);

	
	/**
	 * @param id {@link Long} key of Advertisement
	 * @return {@link AdvertisementDetailCursor} with an Advertisement
	 */
	public AdvertisementDetailCursor getAdvertisement (long id);
	
	/**
	 * All category that does not have subcategory.
	 * 
	 * @return {@link CategoryCursor}
	 */
	public CategoryCursor getCategories();

}
