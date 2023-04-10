package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ProveedorData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public ProveedorData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
