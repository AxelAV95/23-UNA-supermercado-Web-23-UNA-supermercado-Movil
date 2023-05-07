package una.ac.cr.supermercadoapp.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.domain.Usuario;
import una.ac.cr.supermercadoapp.view.activities.FormularioTipoUsuarioActivity;
import una.ac.cr.supermercadoapp.view.activities.FormularioUsuarioActivity;

public class UsuarioAdapter extends RecyclerSwipeAdapter<UsuarioAdapter.ViewHolderUsuario> {

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ArrayList<Usuario> listaUsuarios;
    private Context mContext;
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private TipoUsuarioAdapter.OnItemClickListener mListener;
    public void setOnItemClickListener(TipoUsuarioAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public UsuarioAdapter(ActivityResultLauncher<Intent> activityResultLauncher, ArrayList<Usuario> dataset, Context context) {
        this.activityResultLauncher = activityResultLauncher;
        this.listaUsuarios = dataset;
        this.mContext = context;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public UsuarioAdapter.ViewHolderUsuario onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new UsuarioAdapter.ViewHolderUsuario(view,mListener);
    }

    @Override
    public void onBindViewHolder(UsuarioAdapter.ViewHolderUsuario viewHolder, int position) {
        final Usuario user = listaUsuarios.get(position);
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        String datos = "ID: "+user.getId()+"\n"+"Nombre completo: "+user.getEmpleado().getNombre()+" "+user.getEmpleado().getApellidos()+"\n"+"Tipo: "+user.getTipoUsuario().getDescripcion();
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                FancyAlertDialog.Builder
                        .with(viewHolder.itemView.getContext())
                        .setTitle("Detalles")
                        .setBackgroundColor(Color.parseColor("#262943"))
                        .setMessage("\n"+datos + "\n\n\n")
                        .setAnimation(Animation.POP)
                        .setIcon(R.drawable.usuarioicon, View.VISIBLE)
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
                Intent intent = new Intent(v.getContext(), FormularioUsuarioActivity.class);
                intent.putExtra("metodo","actualizar");
                intent.putExtra("usuario",listaUsuarios.get(position));
               // v.getContext().startActivity(intent);
                activityResultLauncher.launch(intent);
            }
        });

        viewHolder.textViewData.setText(user.getEmpleado().getNombre()+" "+user.getEmpleado().getApellidos());
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolderUsuario extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        LottieAnimationView buttonDelete, btnUpdate;
        public ViewHolderUsuario(@NonNull View itemView, TipoUsuarioAdapter.OnItemClickListener listener){
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
