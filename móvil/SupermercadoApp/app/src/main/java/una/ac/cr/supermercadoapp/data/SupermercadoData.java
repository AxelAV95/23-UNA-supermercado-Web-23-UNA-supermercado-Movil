package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SupermercadoData {

    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public SupermercadoData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
