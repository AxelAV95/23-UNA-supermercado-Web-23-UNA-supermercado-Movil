package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import una.ac.cr.supermercadoapp.utils.DBUtils;

public class SupermercadoDBHelper extends SQLiteOpenHelper {
    public SupermercadoDBHelper(@Nullable Context context) {
        super(context, DBUtils.BASE_DE_DATOS, null, DBUtils.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBUtils.CREAR_TABLA_CATEGORIA);
        db.execSQL(DBUtils.CREAR_TABLA_CLIENTE);
        db.execSQL(DBUtils.CREAR_TABLA_DESCUENTO);
        db.execSQL(DBUtils.CREAR_TABLA_EMPLEADO);
        db.execSQL(DBUtils.CREAR_TABLA_PRODUCTO);
        db.execSQL(DBUtils.CREAR_TABLA_MEMBRESIA);
        db.execSQL(DBUtils.CREAR_TABLA_PROVEEDOR);
        db.execSQL(DBUtils.CREAR_TABLA_SUPERMERCADO);
        db.execSQL(DBUtils.CREAR_TABLA_TIPO_EMPLEADO);
        db.execSQL(DBUtils.CREAR_TABLA_TIPO_USUARIO);
        db.execSQL(DBUtils.CREAR_TABLA_USUARIO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
