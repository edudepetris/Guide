/**
 * Operaciones de escritura: borrar, actualizar y insertar son propuesta en esta interface 
 */
package rio.cuarto.database;


public interface IWriteOnGuide {

	/* advertisement */
	public boolean addAdvertisement(long id, String title, String description, String image, String address, String phone, String geoPosition);
	public boolean updateAdvertisement(long id, String title, String description, String image, String address, String phone, String geoPosition);
	public boolean deleteAdvertisement(long id);
	
	/* category */
	public boolean addCategory(long id, String name);
	public boolean updateCategory(long id, String name);
	public boolean deleteCategory(long id);

	/* sub category*/
	public boolean addSubCategory(long catId, long subcatId);
	public boolean deleteSubCategory(long catId, long subcatId);
	
	/* advertisement have category */
	public boolean addAdvHaveCategory(long advId, long catId);
	public boolean deleteAdvHaveCategory(long advId, long catId);
	
	
}
