package rio.cuarto.utilities;

public class Constants {

	// name of file that stores the version of database (sharedPreferences)
	public static final String VERSION_DB_FILE = "rio.cuarto.service.VERSION_DB_FILE";
	
	public static final String VERSION_DB = "rio.cuarto.service.VERSION_DB";
	
	public static final int  DEFAULT_VERSION_DB = 1;
	
	// Defines a custom Intent action for intentFilter
	public static final String BROADCAST_ACTION = "rio.cuarto.service.BROADCAST";

	public static final int STATE_DEAFULT_VALUE = 100;

	// starting with the stored data
	public static final int STATE_BEGIN_TRANSACTION = 0;

	// fail the transaction while store data
	public static final int STATE_FAIL_TRANSACTION = 1;

	// data commit well
	public static final int STATE_END_TRANSACTION = 2;

	// errors at connection with the server
	public static final int HEADER_HTTPP_CODE_NOT_OK = 3;

	// defines the key for the status extra in an intent
	public static final String EXTENDED_DATA_STATUS = "rio.cuarto.service.STATUS";

}
