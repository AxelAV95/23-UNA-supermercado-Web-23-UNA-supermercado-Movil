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
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.view.activities.FormularioProductoActivity;

public class ProductoAdapter extends RecyclerSwipeAdapter<ProductoAdapter.ViewHolderProducto> {

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ArrayList<Producto> listaProductos;
    private Context mContext;
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ProductoAdapter(ActivityResultLauncher<Intent> activityResultLauncher, ArrayList<Producto> dataset, Context context) {
        this.activityResultLauncher = activityResultLauncher;
        this.listaProductos = dataset;
        this.mContext = context;
    }

    public void setListaProductos(ArrayList<Producto> dataset) {
        this.listaProductos = dataset;
    }

    @Override
    public ViewHolderProducto onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolderProducto(view,mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolderProducto viewHolder,@SuppressLint("RecyclerView") int position) {
        String item = listaProductos.get(position).getNombre().toString();
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                FancyAlertDialog.Builder
                        .with(viewHolder.itemView.getContext())
                        .setTitle("Detalles")
                        .setBackgroundColor(Color.parseColor("#262943"))
                        .setMessage( "ID: "+listaProductos.get(position).getId()+"\n"+"Nombre: "+listaProductos.get(position).getNombre()
                                +"\nPrecio: "+listaProductos.get(position).getPrecio()+"\n"+"Estado: "+listaProductos.get(position).getEstado()
                                +"\nFecha de ingreso: "+listaProductos.get(position).getFechaIngreso()+"\n"+"Stock: "+listaProductos.get(position).getStock()+"\n"
                                +"\nCategoria: "+listaProductos.get(position).getCategoriaNombre()
                                +"\n"+"Proveedor: "+listaProductos.get(position).getProveedorNombre()
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
                Intent intent = new Intent(v.getContext(), FormularioProductoActivity.class);
                intent.putExtra("metodo","actualizar");
                intent.putExtra("producto",listaProductos.get(position));
                // v.getContext().startActivity(intent);
                activityResultLauncher.launch(intent);
            }
        });

        viewHolder.textViewData.setText(item);
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        LottieAnimationView buttonDelete, btnUpdate;
        public ViewHolderProducto(@NonNull View itemView, ProductoAdapter.OnItemClickListener listener){
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
