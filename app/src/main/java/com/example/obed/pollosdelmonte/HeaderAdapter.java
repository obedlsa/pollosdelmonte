package com.example.obed.pollosdelmonte;


import android.app.Application;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
/* Clase HeaderAdapter
        3  *
        4  * @author Obed Alvarado
       5  * @version 18/05/2018
*/
public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.ViewHolder> {
    private ArrayList<Promocion> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public EditText mtv;
        public ImageButton eliminar;
        public ImageButton modificar;
        public ViewHolder(View view) {
            super(view);
            mtv = (EditText) view.findViewById(R.id.etPromocion);
            eliminar=(ImageButton) view.findViewById(R.id.ibEliminar);
            modificar=(ImageButton) view.findViewById(R.id.ibModificar);
        }
    }

    /**
     * Constructor.
     * @param myDataset: Lista de todas las promociones.

     */
    public HeaderAdapter(ArrayList<Promocion> myDataset) {
        mDataset = myDataset;
    }

    /**
     * Crearvistas
     */
    @Override
    public HeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header, parent, false);


        return new ViewHolder(v);
    }

    /**
     * onBindViewHolder, para pasar los construir la vista

     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mtv.setText(mDataset.get(position).getPromocion());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase fdb = FirebaseDatabase.getInstance();
                //apuntamos al nodo que queremos leer
                DatabaseReference myRef = fdb.getReference("Promociones/"+mDataset.get(position).getIdPromocion());
                myRef.removeValue();
                Toast.makeText(view.getContext(), "Eliminado", Toast.LENGTH_SHORT).show();
            }
        });
        holder.modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase fdb = FirebaseDatabase.getInstance();
                //apuntamos al nodo que queremos leer
                DatabaseReference myRef = fdb.getReference("Promociones/"+mDataset.get(position).getIdPromocion());
                myRef.child("promocion").setValue(holder.mtv.getText().toString());
                Toast.makeText(view.getContext(), "Modificado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * getItemCount, obtiene el numero de promociones.
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}