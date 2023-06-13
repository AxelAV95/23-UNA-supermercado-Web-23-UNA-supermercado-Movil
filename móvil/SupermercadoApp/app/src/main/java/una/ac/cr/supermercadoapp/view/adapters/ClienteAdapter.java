package una.ac.cr.supermercadoapp.view.adapters;

import android.annotation.SuppressLint;
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
import una.ac.cr.supermercadoapp.domain.Cliente;
import una.ac.cr.supermercadoapp.view.activities.FormularioClienteActivity;
import una.ac.cr.supermercadoapp.view.activities.FormularioTipoUsuarioActivity;

public class ClienteAdapter extends RecyclerSwipeAdapter<ClienteAdapter.ViewHolderCliente> {


    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ArrayList<Cliente> listaClientes;

    private Context mContext;
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ClienteAdapter(ActivityResultLauncher<Intent> activityResultLauncher, ArrayList<Cliente> dataset, Context context) {
        this.activityResultLauncher = activityResultLauncher;
        listaClientes = dataset;
        mContext = context;
    }

    public void setListaClientes(ArrayList<Cliente> dataset) {
        listaClientes = dataset;
    }

    @Override
    public ViewHolderCliente onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolderCliente(view,mListener);

    }

    @Override
    public void onBindViewHolder(ViewHolderCliente viewHolder, @SuppressLint("RecyclerView") int position) {
        String item = listaClientes.get(position).getNombre().toString();
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                FancyAlertDialog.Builder
                        .with(viewHolder.itemView.getContext())
                        .setTitle("Detalles")
                        .setBackgroundColor(Color.parseColor("#262943"))
                        .setMessage("\nID: " + listaClientes.get(position).getId() + "\nNombre: " + listaClientes.get(position).getNombre().toString() +
                                 "\nApellidos: " + listaClientes.get(position).getApellidos().toString() +
                                  "\nCédula: " + listaClientes.get(position).getCedula()+
                                  "\nDirección: " + listaClientes.get(position).getDireccion().toString() +
                                "\nTeléfono: " + listaClientes.get(position).getTelefono() +
                                "\nCorreo: " + listaClientes.get(position).getCorreo().toString() +
                                "\nfecha Afiliación: " + listaClientes.get(position).getFechaAfiliacion().toString() +
                                "\nTipo membresía: " + listaClientes.get(position).getTipoMembresia() +"\n\n\n")
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
                Intent intent = new Intent(v.getContext(), FormularioClienteActivity.class);
                intent.putExtra("metodo","actualizar");
                intent.putExtra("cliente",listaClientes.get(position));
                //  v.getContext().startActivity(intent);
                activityResultLauncher.launch(intent);
            }
        });


        viewHolder.textViewData.setText(item);
        mItemManger.bindView(viewHolder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        LottieAnimationView buttonDelete, btnUpdate;

        public ViewHolderCliente(@NonNull View itemView,  OnItemClickListener listener) {
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
