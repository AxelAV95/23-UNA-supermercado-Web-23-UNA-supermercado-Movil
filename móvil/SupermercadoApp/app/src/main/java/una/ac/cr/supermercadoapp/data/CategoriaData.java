package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CategoriaData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public CategoriaData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
