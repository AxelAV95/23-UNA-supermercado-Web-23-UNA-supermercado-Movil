package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import una.ac.cr.supermercadoapp.domain.Proveedor;
import una.ac.cr.supermercadoapp.utils.DBUtils;

public class ProveedorData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public ProveedorData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }

    public Cursor obtenerProveedores(){
        db = helper.getReadableDatabase();
        Cursor cursor  = db.rawQuery(DBUtils.CONSULTAR_PROVEEDOR, null);
        return cursor;
    }


    public long insertarProveedor(Proveedor proveedor){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("proveedornombre",proveedor.getNombre());
        valores.put("proveedordireccion",proveedor.getDireccion());
        valores.put("proveedortelefono",proveedor.getTelefono());
        valores.put("proveedorcorreo",proveedor.getCorreo());
        valores.put("proveedorlat",proveedor.getLatitud());
        valores.put("proveedorlong",proveedor.getLongitud());
        return db.insert(DBUtils.PROVEEDOR,null,valores);
    }

    public int eliminarProveedor(Proveedor proveedor){
        db = helper.getWritableDatabase();
        return db.delete(DBUtils.PROVEEDOR,"proveedorid = ?",new String[]{String.valueOf(proveedor.getId())});
    }

    public int actualizarProveedor(Proveedor proveedor){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("proveedorid",proveedor.getId());
        valores.put("proveedornombre",proveedor.getNombre());
        valores.put("proveedordireccion",proveedor.getDireccion());
        valores.put("proveedortelefono",proveedor.getTelefono());
        valores.put("proveedorcorreo",proveedor.getCorreo());
        valores.put("proveedorlat",proveedor.getLatitud());
        valores.put("proveedorlong",proveedor.getLongitud());
        return db.update(DBUtils.PROVEEDOR, valores, "proveedorid = ?",new String[]{String.valueOf(proveedor.getId())});
    }





}
