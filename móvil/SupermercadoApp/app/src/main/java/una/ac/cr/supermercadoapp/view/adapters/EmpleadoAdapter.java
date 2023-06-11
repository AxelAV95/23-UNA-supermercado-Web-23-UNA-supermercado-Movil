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
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.view.activities.FormularioEmpleadoActivity;
import una.ac.cr.supermercadoapp.view.activities.FormularioProductoActivity;

public class EmpleadoAdapter extends RecyclerSwipeAdapter<EmpleadoAdapter.ViewHolderEmpleado> {
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ArrayList<Empleado> listaEmpleados;
    private Context mContext;
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private EmpleadoAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(EmpleadoAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public EmpleadoAdapter(ActivityResultLauncher<Intent> activityResultLauncher, ArrayList<Empleado> dataset, Context context) {
        this.activityResultLauncher = activityResultLauncher;
        this.listaEmpleados = dataset;
        this.mContext = context;
    }

    public void setListaEmpleados(ArrayList<Empleado> dataset) {
        this.listaEmpleados = dataset;
    }

    @Override
    public EmpleadoAdapter.ViewHolderEmpleado onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new EmpleadoAdapter.ViewHolderEmpleado(view,mListener);
    }

    @Override
    public void onBindViewHolder(EmpleadoAdapter.ViewHolderEmpleado viewHolder, @SuppressLint("RecyclerView") int position) {
        String item = listaEmpleados.get(position).getNombre().toString();
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                FancyAlertDialog.Builder
                        .with(viewHolder.itemView.getContext())
                        .setTitle("Detalles")
                        .setBackgroundColor(Color.parseColor("#262943"))
                        .setMessage( "ID: "+listaEmpleados.get(position).getId()+"\n"+"Cedula: "+listaEmpleados.get(position).getCedula()
                                +"Nombre: "+listaEmpleados.get(position).getNombre()+"\n"+"Apellidos: "+listaEmpleados.get(position).getApellidos()
                                +"Telefono: "+listaEmpleados.get(position).getTelefono()+"\n"+"Direccion: "+listaEmpleados.get(position).getDireccion()
                                +"Fecha de ingreso: "+listaEmpleados.get(position).getFechaIngreso()+"\n"+"Fecha salida: "+listaEmpleados.get(position).getFechaSalida()+"\n"
                                +"Estado: "+listaEmpleados.get(position).getEstado()+"\n"+"Tipo empleado: "+listaEmpleados.get(position).getTipoEmpleado().getTipoEmpleadoDescripcion()
                        )
                        .setAnimation(Animation.POP)
                        .setIcon(R.drawable.productoicon, View.VISIBLE)
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
                Intent intent = new Intent(v.getContext(), FormularioEmpleadoActivity.class);
                intent.putExtra("metodo","actualizar");
                intent.putExtra("empleado",listaEmpleados.get(position));
                // v.getContext().startActivity(intent);
                activityResultLauncher.launch(intent);
            }
        });

        viewHolder.textViewData.setText(item);
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolderEmpleado extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        LottieAnimationView buttonDelete, btnUpdate;
        public ViewHolderEmpleado(@NonNull View itemView, EmpleadoAdapter.OnItemClickListener listener){
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
