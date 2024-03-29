package una.ac.cr.supermercadoapp.view.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.data.CategoriaData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.activities.CategoriaActivity;
import una.ac.cr.supermercadoapp.view.activities.FormularioCategoriaActivity;
import una.ac.cr.supermercadoapp.view.activities.FormularioTipoUsuarioActivity;
import una.ac.cr.supermercadoapp.view.activities.MainActivity;
import una.ac.cr.supermercadoapp.view.activities.MenuEmpleadoActivity;
import una.ac.cr.supermercadoapp.view.interfaces.CategoriaICallback;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;


import java.util.ArrayList;


import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.Categoria;

public class CategoriaAdapter extends RecyclerSwipeAdapter<CategoriaAdapter.ViewHolderCategoria> {


    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ArrayList<Categoria> listaCategorias;

    private Context mContext;
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CategoriaAdapter(ActivityResultLauncher<Intent> activityResultLauncher,ArrayList<Categoria> dataset, Context context) {
        this.activityResultLauncher = activityResultLauncher;
        listaCategorias = dataset;
        mContext = context;
    }

    public void setListaCategorias(ArrayList<Categoria> dataset) {
        listaCategorias = dataset;
    }

    @Override
    public ViewHolderCategoria onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolderCategoria(view,mListener);

    }

    @Override
    public void onBindViewHolder(ViewHolderCategoria viewHolder, @SuppressLint("RecyclerView") int position) {
        String item = listaCategorias.get(position).getNombre().toString();
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                FancyAlertDialog.Builder
                        .with(viewHolder.itemView.getContext())
                        .setTitle("Detalles")
                        .setBackgroundColor(Color.parseColor("#262943"))
                        .setMessage("\nID: " + listaCategorias.get(position).getId() + "\nNombre: " + listaCategorias.get(position).getNombre().toString() + "\n\n\n")
                        .setAnimation(Animation.POP)
                        .setIcon(R.drawable.categoriaicon, View.VISIBLE)
                        .setPositiveBtnBackground(Color.parseColor("#ffffff"))
                        .setNegativeBtnBackground(Color.parseColor("#ffffff"))
                        .setNegativeBtnText("")
                        .isCancellable(true)
                        .setPositiveBtnText("Ok")
                        .build()
                        .show();
            }
        });



        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FormularioCategoriaActivity.class);
                intent.putExtra("metodo","actualizar");
                intent.putExtra("categoria",listaCategorias.get(position));
                //  v.getContext().startActivity(intent);
                activityResultLauncher.launch(intent);
            }
        });


        viewHolder.textViewData.setText(item);
        mItemManger.bindView(viewHolder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolderCategoria extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        LottieAnimationView buttonDelete, btnUpdate;

        public ViewHolderCategoria(@NonNull View itemView,  OnItemClickListener listener) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);

            textViewData = (TextView) itemView.findViewById(R.id.text_data);
            buttonDelete =  itemView.findViewById(R.id.delete);
            btnUpdate = itemView.findViewById(R.id.update);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }


    }
}
