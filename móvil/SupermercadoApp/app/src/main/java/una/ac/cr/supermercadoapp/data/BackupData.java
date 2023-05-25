package una.ac.cr.supermercadoapp.data;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;

public class BackupData {

    public BackupData() {
    }

    public void respaldarJson(String jsonData, Context context, String nombreArchivo) {

        try {
            FileOutputStream fos = context.openFileOutput(nombreArchivo, Context.MODE_APPEND);
            fos.write(jsonData.getBytes());
            fos.close();
            Log.d("Registro guardado", "Registro agregado al JSON");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error", "Un error ocurrió al agregar al archivo JSON");
        }
    }
}
