package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TipoUsuarioData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public TipoUsuarioData(Context context){
        helper = new SupermercadoDBHelper(context);
    }

    //Métodos
}
