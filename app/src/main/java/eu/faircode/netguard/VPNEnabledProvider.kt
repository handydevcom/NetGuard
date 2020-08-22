package eu.faircode.netguard

import android.content.ContentProvider
import android.content.ContentValues
import android.content.SharedPreferences
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.preference.PreferenceManager

class VPNEnabledProvider : ContentProvider() {

    private var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, VPN_ENABLED, VPN_ENABLED_CODE)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        var matrixCursor: MatrixCursor? = null
        when (uriMatcher.match(uri)) {
            VPN_ENABLED_CODE -> {
                val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                val value = prefs.getBoolean("enabled", false)
                matrixCursor = MatrixCursor(arrayOf(VPN_ENABLED_ROW)).apply {
                    newRow().add(VPN_ENABLED_ROW, if (value) 1 else 0)
                }
            }
        }
        return matrixCursor
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    companion object {
        private const val AUTHORITY = "eu.faircode.netguard"
        private const val VPN_ENABLED = "VPN_ENABLED"

        private const val VPN_ENABLED_CODE = 1

        private const val VPN_ENABLED_ROW = "VPN_ENABLED_ROW"

        val VPN_ENABLED_CONTENT_URI = Uri.parse("content://$AUTHORITY/$VPN_ENABLED")

    }

}
