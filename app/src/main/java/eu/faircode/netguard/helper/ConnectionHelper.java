package eu.faircode.netguard.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionHelper {

	private static ConnectionHelper instance;

	public static ConnectionHelper getInstance() {
		if (instance == null) {
			instance = new ConnectionHelper();
		}
		return instance;
	}

	public Boolean isNetworkAvailable(Context context)  {

		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		return info != null && info.isConnectedOrConnecting();
	}

}
