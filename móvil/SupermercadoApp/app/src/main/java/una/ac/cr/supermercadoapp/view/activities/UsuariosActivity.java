package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import una.ac.cr.supermercadoapp.R;

public class UsuariosActivity extends AppCompatActivity {

    private LottieAnimationView tipoUsuarios,iconUsuarios;
    private PowerMenu powerMenu;

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

            if(item.title.equals("Tipos de usuario")){
             //   powerMenu.setSelectedPosition(position); // change selected item
                powerMenu.dismiss();
                Intent intent = new Intent(UsuariosActivity.this, TipoUsuarioActivity.class);
                startActivity(intent);

            }else if(item.title.equals("Menú principal")){
                powerMenu.dismiss();
                Intent intent = new Intent(UsuariosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            //Toast.makeText(getBaseContext(), item.title, Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        if(cedula == null){
            Intent intent = new Intent(UsuariosActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }

        powerMenu = new PowerMenu.Builder(this)

                .addItem(new PowerMenuItem("Tipos de usuario", false)) // add an item.
                .addItem(new PowerMenuItem("Menú principal", false)) // add an item.
                .setAnimation(MenuAnimation.SHOWUP_BOTTOM_RIGHT) // Animation start point (TOP | LEFT).
                .setMenuRadius(10f) // sets the corner radius.
                .setMenuShadow(10f) // sets the shadow.
                .setTextColor(ContextCompat.getColor(this, R.color.dark_gray))
                .setTextGravity(Gravity.CENTER)
                .setTextTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD))
                .setSelectedTextColor(Color.WHITE)
                .setMenuColor(Color.WHITE)
                .setSelectedMenuColor(ContextCompat.getColor(getApplicationContext(), R.color.black))
                .setOnMenuItemClickListener(onMenuItemClickListener)
                .build();

        tipoUsuarios = findViewById(R.id.icon_ver_tipos_usuario);
        int primaryColor = ContextCompat.getColor(this, R.color.primary);
        tipoUsuarios.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(primaryColor)));



        iconUsuarios = findViewById(R.id.icon_usuarios);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
        iconUsuarios .addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));


        tipoUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (powerMenu.isShowing()) {
                    powerMenu.dismiss();
                    return;
                }
                powerMenu.showAsDropDown(view);
            }
        });
    }
}