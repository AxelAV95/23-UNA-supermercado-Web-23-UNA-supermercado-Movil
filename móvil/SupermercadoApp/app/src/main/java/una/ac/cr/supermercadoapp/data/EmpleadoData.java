package una.ac.cr.supermercadoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.utils.DBUtils;

public class EmpleadoData {
    private SupermercadoDBHelper helper;
    private SQLiteDatabase db;

    public EmpleadoData(Context context) {
        helper = new SupermercadoDBHelper(context);
    }


    public long insertarEmpleado(Empleado empleado){
        db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("cedula",empleado.getCedula());
        valores.put("nombre",empleado.getNombre());
        valores.put("apellidos",empleado.getApellidos());
        valores.put("telefono",empleado.getTelefono());
        valores.put("direccion",empleado.getDireccion());
        valores.put("fechaIngreso",empleado.getFechaIngreso());
        valores.put("fechaSalida",empleado.getFechaSalida());
        valores.put("estado",empleado.getEstado());
        valores.put("tipoEmpleadoId",empleado.getTipo());
        return db.insert(DBUtils.EMPLEADO,null,valores);
    }



}
