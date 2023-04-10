package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DescuentoData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public DescuentoData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
