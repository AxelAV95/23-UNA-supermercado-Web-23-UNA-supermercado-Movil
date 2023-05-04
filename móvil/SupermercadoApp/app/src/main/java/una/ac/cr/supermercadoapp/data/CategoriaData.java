package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.utils.DBUtils;

public class CategoriaData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public CategoriaData(Context context) {helper = new SupermercadoDBHelper(context);}

        public long insertarCategoria(Categoria ca){
            db = helper.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("categorianombre",ca.getNombre());
            valores.put("categoriasync",ca.getEstadosync());
            return db.insert(DBUtils.CATEGORIA,null,valores);
        }



        public int actualizarCategoria(Categoria ca){
            db = helper.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("categorianombre",ca.getNombre());
            valores.put("categoriasync",ca.getEstadosync());
            return db.update(DBUtils.CATEGORIA, valores, "categoriaid = ?",new String[]{String.valueOf(ca.getId())});
        }

        public int eliminarCategoria(Categoria ca){
            db = helper.getWritableDatabase();
            return db.delete(DBUtils.CATEGORIA,"categoriaid = ?",new String[]{String.valueOf(ca.getId())});
        }

        public boolean actualizarEstado(int estado){
            db = helper.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("categoriasync",estado);

            long resultado = db.update(DBUtils.CATEGORIA,valores, "categoriasync = -1",  null);
            //-1: error != éxito
            if(resultado != -1){ //validar
                return true; //todo en orden
            }else{
                return false; //si hay errores
            }
        }

        public Cursor obtenerCategorias(){
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(DBUtils.CONSULTAR_CATEGORIAS+"where categoriasync = -1", null);
            return cursor;
        }

}
