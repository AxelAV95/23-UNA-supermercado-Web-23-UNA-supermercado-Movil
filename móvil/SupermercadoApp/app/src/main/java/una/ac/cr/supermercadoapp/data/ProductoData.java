package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ProductoData {

    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public ProductoData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
