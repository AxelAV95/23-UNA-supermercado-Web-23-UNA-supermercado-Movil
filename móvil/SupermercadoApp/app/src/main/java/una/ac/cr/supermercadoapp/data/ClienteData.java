package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ClienteData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public ClienteData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
