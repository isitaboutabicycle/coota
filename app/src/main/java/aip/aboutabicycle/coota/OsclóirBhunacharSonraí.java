package aip.aboutabicycle.coota;
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class OsclóirBhunacharSonraí extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "Foclooir.db";
    private static final int DATABASE_VERSION = 1;

    public OsclóirBhunacharSonraí(Context comhthéacs) {
        super(comhthéacs, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
