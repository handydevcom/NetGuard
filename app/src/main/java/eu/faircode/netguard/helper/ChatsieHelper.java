package eu.faircode.netguard.helper;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class ChatsieHelper {
    private static ArrayList<String> chatsieApps = null;

    public static boolean checkAppPackage(String packageName, ContentResolver contentResolver) {
        if (chatsieApps == null) {
            chatsieApps = new ArrayList<>();
            Cursor cursor = contentResolver.query(
                    Uri.parse("content://com.cando.chatsie.mvvmp.main.provider.apps/APPS"),
                    null,
                    null,
                    null,
                    null
            );
            while (cursor.moveToNext()) {
                String packageAppName = cursor.getString(cursor.getColumnIndex("PACKAGE_ROW"));
                chatsieApps.add(packageAppName);
            }
            cursor.close();
        }
        Log.d("LogVPN","chatsie apps size " + chatsieApps.size());

        return chatsieApps.contains(packageName);
    }

}
