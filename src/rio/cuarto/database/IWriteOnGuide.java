/**
 * Operaciones de escritura: borrar, actualizar y insertar son propuesta en esta interface 
 */
package rio.cuarto.database;

import android.database.SQLException;

public interface IWriteOnGuide {

	/* advertisement */
	public void addAdvertisement(long id, String title, String description,
			String image, String address, String phone, String geoPosition)
			throws SQLException;

	public void updateAdvertisement(long id, String title, String description,
			String image, String address, String phone, String geoPosition)
			throws SQLException;

	public void deleteAdvertisement(long id) throws SQLException;

	/* category */
	public void addCategory(long id, String name) throws SQLException;

	public void updateCategory(long id, String name) throws SQLException;

	public void deleteCategory(long id) throws SQLException;

	/* sub category */
	public void addSubCategory(long catId, long subcatId) throws SQLException;

	public void deleteSubCategory(long catId, long subcatId)
			throws SQLException;

	/* advertisement have category */
	public void addAdvHaveCategory(long advId, long catId) throws SQLException;

	public void deleteAdvHaveCategory(long advId, long catId)
			throws SQLException;

	/* database transaction */
	public void beginTransaction();

	public void commit();

	public void endTransaction();

}
