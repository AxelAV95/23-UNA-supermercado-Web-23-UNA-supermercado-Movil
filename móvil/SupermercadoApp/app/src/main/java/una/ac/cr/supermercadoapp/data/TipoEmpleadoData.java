package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TipoEmpleadoData {

    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public TipoEmpleadoData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
