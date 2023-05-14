package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.utils.DBUtils;

public class TipoEmpleadoData {

    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public TipoEmpleadoData(Context context) { helper = new SupermercadoDBHelper(context);}


    public Cursor obtenerTiposEmpleados(){
        db = helper.getReadableDatabase();
        Cursor cursor  = db.rawQuery(DBUtils.CONSULTAR_TIPOEMPLEADO, null);
        return cursor;
    }

    public long insertarTipoEmpleado(TipoEmpleado tipoEmpleado){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("tipodescripcion",tipoEmpleado.getTipoEmpleadoDescripcion());
        return db.insert(DBUtils.TIPO_EMPLEADO,null,valores);
    }


    public int eliminarTipoEmpleado(TipoEmpleado tipoEmpleado){
        db = helper.getWritableDatabase();
        return db.delete(DBUtils.TIPO_EMPLEADO,"tipoid = ?",new String[]{String.valueOf(tipoEmpleado.getId())});
    }

    public int actualizarTipoEmpleado(TipoEmpleado tipoEmpleado){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("tipoid",tipoEmpleado.getId());
        valores.put("tipodescripcion",tipoEmpleado.getTipoEmpleadoDescripcion());
        return db.update(DBUtils.TIPO_EMPLEADO, valores, "tipoid = ?",new String[]{String.valueOf(tipoEmpleado.getId())});
    }



}
