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
import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.view.activities.FormularioDescuentoActivity;
import una.ac.cr.supermercadoapp.view.activities.FormularioTipoEmpleadoActivity;

public class DescuentoAdapter extends RecyclerSwipeAdapter<DescuentoAdapter.ViewHolderDescuento> {
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ArrayList<Descuento> listaDescuentos;

    private Context mContext;
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DescuentoAdapter(ActivityResultLauncher<Intent> activityResultLauncher,ArrayList<Descuento> dataset, Context context) {
        this.activityResultLauncher = activityResultLauncher;
        listaDescuentos = dataset;
        mContext = context;
    }

    public void setListaDescuentos(ArrayList<Descuento> dataset) {
        listaDescuentos = dataset;
    }

    @Override
    public ViewHolderDescuento onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolderDescuento(view,mListener);

    }


    @Override
    public void onBindViewHolder(ViewHolderDescuento viewHolder, @SuppressLint("RecyclerView") int position) {
        Float item = listaDescuentos.get(position).getTarifa();
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                FancyAlertDialog.Builder
                        .with(viewHolder.itemView.getContext())
                        .setTitle("Detalles")
                        .setBackgroundColor(Color.parseColor("#262943"))
                        .setMessage("\nID: " + listaDescuentos.get(position).getId() + "\nTarifa: " + listaDescuentos.get(position).getTarifa() + "\n\n\n")
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
                Intent intent = new Intent(v.getContext(), FormularioDescuentoActivity.class);
                intent.putExtra("metodo","actualizar");
                intent.putExtra("descuento",listaDescuentos.get(position));
                //System.out.println(listaDescuentos.get(position).getTarifa());
                //  v.getContext().startActivity(intent);
                activityResultLauncher.launch(intent);
            }
        });


        viewHolder.textViewData.setText(""+item);
        mItemManger.bindView(viewHolder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return listaDescuentos.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolderDescuento extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        LottieAnimationView buttonDelete, btnUpdate;

        public ViewHolderDescuento(@NonNull View itemView,  DescuentoAdapter.OnItemClickListener listener) {
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

