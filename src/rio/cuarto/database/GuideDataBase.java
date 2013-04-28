/**
 * 
 */
package rio.cuarto.database;

/**
 * @author edu
 *
 */
public class GuideDataBase implements IWriteOnGuide {

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#addAdvertisement(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addAdvertisement(long id, String title, String description,
			String imagem, String address, String phone, String geoPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#updateAdvertisement(long)
	 */
	@Override
	public boolean updateAdvertisement(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#deleteAdvertisement(long)
	 */
	@Override
	public boolean deleteAdvertisement(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#addCategory(long, java.lang.String)
	 */
	@Override
	public boolean addCategory(long id, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#updateCategory(long, java.lang.String)
	 */
	@Override
	public boolean updateCategory(long id, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#deleteCategory(long)
	 */
	@Override
	public boolean deleteCategory(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#addSubCategory(long, long)
	 */
	@Override
	public boolean addSubCategory(long catId, long subcatId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#deleteSubCategory(long, long)
	 */
	@Override
	public boolean deleteSubCategory(long catId, long subcatId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#addAdvHaveCategory(long, long)
	 */
	@Override
	public boolean addAdvHaveCategory(long advId, long catId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see rio.cuarto.database.IWriteOnGuide#deleteAdvHaveCategory(long, long)
	 */
	@Override
	public boolean deleteAdvHaveCategory(long advId, long catId) {
		// TODO Auto-generated method stub
		return false;
	}

}
