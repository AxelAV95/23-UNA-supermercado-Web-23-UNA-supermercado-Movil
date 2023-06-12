package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;


public class DescuentoData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public DescuentoData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }

}
