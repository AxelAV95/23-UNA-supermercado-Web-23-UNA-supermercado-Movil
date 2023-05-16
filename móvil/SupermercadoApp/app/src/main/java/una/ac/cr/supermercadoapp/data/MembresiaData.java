package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import una.ac.cr.supermercadoapp.domain.Membresia;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.utils.DBUtils;

public class MembresiaData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public MembresiaData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }

    public long insertarMembresia(Membresia membresia){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("membresiadescripcion",membresia.getDescripcion());
        return db.insert(DBUtils.MEMBRESIA,null,valores);
    }


    public int actualizarMembresia(Membresia membresia){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("membresiaid",membresia.getId());
        valores.put("membresiadescripcion",membresia.getDescripcion());
        return db.update(DBUtils.MEMBRESIA, valores, "membresiaid = ?",new String[]{String.valueOf(membresia.getId())});
    }

    public int eliminarMembresia(Membresia membresia){
        db = helper.getWritableDatabase();
        return db.delete(DBUtils.MEMBRESIA,"membresiaid = ?",new String[]{String.valueOf(membresia.getId())});
    }

    public Cursor obtenerMembresias(){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(DBUtils.CONSULTAR_MEMBRESIAS,null);
        return cursor;
    }


}
