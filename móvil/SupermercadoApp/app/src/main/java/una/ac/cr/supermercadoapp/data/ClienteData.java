package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import una.ac.cr.supermercadoapp.domain.Cliente;
import una.ac.cr.supermercadoapp.utils.DBUtils;

public class ClienteData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public ClienteData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }

    public long insertarCliente(Cliente cliente){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("clientenombre",cliente.getNombre());
        valores.put("clienteapellidos",cliente.getApellidos());
        valores.put("clientecedula",cliente.getCedula());
        valores.put("clientedireccion",cliente.getDireccion());
        valores.put("clientetelefono",cliente.getTelefono());
        valores.put("clientecorreo",cliente.getCorreo());
        valores.put("clientefechaafilacion",cliente.getFechaAfiliacion());
        valores.put("clientetipomembresia",cliente.getTipoMembresia());

        return db.insert(DBUtils.CLIENTE,null,valores);
    }


    public int actualizarCliente(Cliente cliente){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("clientenombre",cliente.getNombre());
        valores.put("clienteapellidos",cliente.getApellidos());
        valores.put("clientecedula",cliente.getCedula());
        valores.put("clientedireccion",cliente.getDireccion());
        valores.put("clientetelefono",cliente.getTelefono());
        valores.put("clientecorreo",cliente.getCorreo());
        valores.put("clientefechaafilacion",cliente.getFechaAfiliacion());
        valores.put("clientetipomembresia",cliente.getTipoMembresia());

        return db.update(DBUtils.CLIENTE, valores, "clienteid = ?",new String[]{String.valueOf(cliente.getId())});
    }

    public int eliminarCliente(Cliente cliente){
        db = helper.getWritableDatabase();
        return db.delete(DBUtils.CLIENTE,"clienteid = ?",new String[]{String.valueOf(cliente.getId())});
    }

    public Cursor obtenerClientes(){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(DBUtils.CONSULTAR_CLIENTES,null);
        return cursor;
    }


}
