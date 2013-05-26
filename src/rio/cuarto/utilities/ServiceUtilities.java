package rio.cuarto.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import rio.cuarto.service.BroadcastNotifier;

public class ServiceUtilities {

	// Internet connection

	/**
	 * @param version
	 *            of database phone
	 * @return
	 */
	public static byte[] getDataIS(int version, BroadcastNotifier notifier) {
		String MY_URL = "http://damp-reef-9737.herokuapp.com/update/";
		String url = MY_URL + version;
		byte[] data = null;
		InputStream stream = null;
		ByteArrayOutputStream bArrayOutputStream = null;
		HttpURLConnection connection = null;
		try {
			URL linkurl = new URL(url);
			connection = (HttpURLConnection) linkurl.openConnection();
			int responseCode = connection.getResponseCode();
			if (!(responseCode == HttpURLConnection.HTTP_OK))
				notifier.broadcastIntentWithState(Constants.HEADER_HTTPP_CODE_NOT_OK);
			else {
				stream = connection.getInputStream();
				bArrayOutputStream = new ByteArrayOutputStream();
				int j = 0;
				try {
					while ((j = stream.read()) != -1) {
						bArrayOutputStream.write(j);
					}
					stream.close();
					data = bArrayOutputStream.toByteArray();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return data;
	}
	// Wifi status

}
