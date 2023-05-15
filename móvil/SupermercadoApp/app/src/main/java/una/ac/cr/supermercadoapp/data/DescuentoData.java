package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.utils.DBUtils;

public class DescuentoData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public DescuentoData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }

    public Cursor obtenerDescuentos(){
        db = helper.getReadableDatabase();
        Cursor cursor  = db.rawQuery(DBUtils.CONSULTAR_DESCUENTOS, null);
        return cursor;
    }

    public long insertarDescuento(Descuento descuento){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("descuentotarifa",descuento.getTarifa());
        valores.put("descuentomembresiaid",descuento.getMembresiaid());
        return db.insert(DBUtils.DESCUENTO,null,valores);
    }


    public int eliminarDescuento(Descuento descuento){
        db = helper.getWritableDatabase();
        return db.delete(DBUtils.DESCUENTO,"descuentoid = ?",new String[]{String.valueOf(descuento.getId())});
    }

    public int actualizarDescuento(Descuento descuento){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("descuentoid",descuento.getId());
        valores.put("descuentotarifa",descuento.getTarifa());
        valores.put("descuentomembresiaid",descuento.getMembresiaid());
        return db.update(DBUtils.DESCUENTO, valores, "descuentoid = ?",new String[]{String.valueOf(descuento.getId())});
    }

}
