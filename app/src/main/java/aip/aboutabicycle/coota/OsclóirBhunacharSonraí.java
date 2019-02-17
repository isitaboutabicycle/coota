package aip.aboutabicycle.coota;
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper; //https://github.com/jgilfelt/android-sqlite-asset-helper

public class OsclóirBhunacharSonraí extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "Foclooir.db";
    private static final int DATABASE_VERSION = 1;

    public OsclóirBhunacharSonraí(Context comhthéacs) {
        super(comhthéacs, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
