package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.utils.DBUtils;

public class TipoUsuarioData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public TipoUsuarioData(Context context){
        helper = new SupermercadoDBHelper(context);
    }

    //Métodos
    public long insertarTipoUsuario(TipoUsuario tu){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("tipodescripcion",tu.getDescripcion());
        valores.put("tiposync",tu.getEstadosync());
        return db.insert(DBUtils.TIPO_USUARIO,null,valores);
    }

    public int actualizarTipoUsuario(TipoUsuario tu){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("tipodescripcion",tu.getDescripcion());
        valores.put("tiposync", tu.getEstadosync());
        return db.update(DBUtils.TIPO_USUARIO, valores, "tipoid = ?",new String[]{String.valueOf(tu.getId())});
    }

    public int eliminarTipoUsuario(TipoUsuario tu){
        db = helper.getWritableDatabase();
        return db.delete(DBUtils.TIPO_USUARIO,"tipoid = ?",new String[]{String.valueOf(tu.getId())});
    }

    public boolean actualizarEstado(int estado){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("tiposync",estado);

        long resultado = db.update(DBUtils.TIPO_USUARIO,valores, "tiposync = -1",  null);
        //-1: error != éxito
        if(resultado != -1){ //validar
            return true; //todo en orden
        }else{
            return false; //si hay errores
        }
    }

    public Cursor obtenerTipoUsuarios(){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(DBUtils.CONSULTAR_TIPOUSUARIO+"where tiposync = -1", null);
        return cursor;
    }

}
