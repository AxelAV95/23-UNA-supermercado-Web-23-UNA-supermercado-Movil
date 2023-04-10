package una.ac.cr.supermercadoapp.view.adapters;



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
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.view.activities.FormularioTipoUsuarioActivity;

public class TipoUsuarioAdapter extends RecyclerSwipeAdapter<TipoUsuarioAdapter.ViewHolderTipoUsuario> {

    private ArrayList<TipoUsuario> listaTipoUsuarios;
    private Context mContext;
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public TipoUsuarioAdapter(ArrayList<TipoUsuario> dataset, Context context) {
        listaTipoUsuarios = dataset;
        mContext = context;
    }

    public void setListaTipoUsuarios(ArrayList<TipoUsuario> dataset) {
        listaTipoUsuarios = dataset;
    }

    @Override
    public ViewHolderTipoUsuario onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolderTipoUsuario(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderTipoUsuario viewHolder, @SuppressLint("RecyclerView") int position) {
        String item = listaTipoUsuarios.get(position).getDescripcion().toString();
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
//                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                FancyAlertDialog.Builder
                        .with(viewHolder.itemView.getContext())
                        .setTitle("Detalles")
                        .setBackgroundColor(Color.parseColor("#262943"))
                        .setMessage("\nID: " + listaTipoUsuarios.get(position).getId() + "\nDescripción: " + listaTipoUsuarios.get(position).getDescripcion().toString() + "\n\n\n")
                        .setAnimation(Animation.POP)
                        .setIcon(R.drawable.usuarioicon, View.VISIBLE)
                        .setPositiveBtnBackground(Color.parseColor("#ffffff"))
                        .setNegativeBtnBackground(Color.parseColor("#ffffff"))
                        .setNegativeBtnText("")
                        .isCancellable(true)
                        .setPositiveBtnText("Ok")
                        .build()
                        .show();


                        //.onPositiveClicked(dialog -> Toast.makeText(viewHolder.itemView.getContext(), "Rate", Toast.LENGTH_SHORT).show())
                        //.onNegativeClicked(dialog -> Toast.makeText(viewHolder.itemView.getContext(), "Cancel", Toast.LENGTH_SHORT).show())

                //Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FormularioTipoUsuarioActivity.class);
                intent.putExtra("metodo","actualizar");
                intent.putExtra("tipoUsuario",listaTipoUsuarios.get(position));
                v.getContext().startActivity(intent);
            }
        });
        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                listaTipoUsuarios.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listaTipoUsuarios.size());
                mItemManger.closeAllItems();
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.textViewData.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.textViewData.setText(item);
        mItemManger.bindView(viewHolder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return listaTipoUsuarios.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolderTipoUsuario extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        LottieAnimationView buttonDelete, btnUpdate;
        public ViewHolderTipoUsuario(@NonNull View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);

            textViewData = (TextView) itemView.findViewById(R.id.text_data);
            buttonDelete =  itemView.findViewById(R.id.delete);
            btnUpdate = itemView.findViewById(R.id.update);

            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
                    Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });*/
        }

        public void setDatos(String dato, int position){
            swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            swipeLayout.addSwipeListener(new SimpleSwipeListener() {
                @Override
                public void onOpen(SwipeLayout layout) {
                //    YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
                }
            });
            swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
                @Override
                public void onDoubleClick(SwipeLayout layout, boolean surface) {
                    Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemManger.removeShownLayouts(swipeLayout);
                    listaTipoUsuarios.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, listaTipoUsuarios.size());
                    mItemManger.closeAllItems();
                    Toast.makeText(view.getContext(), "Deleted " + textViewData.getText().toString() + "!", Toast.LENGTH_SHORT).show();
                }
            });
            textViewPos.setText((position + 1) + ".");
            textViewData.setText(dato);
            mItemManger.bindView(itemView, position);
        }
    }
}
