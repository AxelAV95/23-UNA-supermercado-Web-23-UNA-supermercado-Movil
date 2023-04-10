package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioData {

    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public UsuarioData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }
}
