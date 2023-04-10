package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MembresiaData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public MembresiaData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
