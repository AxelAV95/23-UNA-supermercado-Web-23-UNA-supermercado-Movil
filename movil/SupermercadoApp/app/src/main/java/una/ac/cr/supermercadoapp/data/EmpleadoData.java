package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EmpleadoData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public EmpleadoData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
