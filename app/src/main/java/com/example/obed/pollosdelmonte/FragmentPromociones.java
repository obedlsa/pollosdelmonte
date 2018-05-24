package com.example.obed.pollosdelmonte;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/** Clase FragmentPromociones.

 @author Obed Alvarado
 @version 18/05/2018
 */

public class FragmentPromociones extends Fragment {
    private EditText etPromocion;
    private ImageButton imAgregar;
    private RecyclerView recyclerView;
    private HeaderAdapter headerAdapter;
    private ArrayList<Promocion> promociones;
    /**
     * on createview, metodo que se ejecuta al comienzo del activity.
     * @param savedInstanceState: parametro que guarda la instancia.
     * @param inflater: parametro que guarda el elemento para meter items.
     * @param container: contenedor del fragment.
     *

     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_fragment_promociones, container, false);
        etPromocion=(EditText) view.findViewById(R.id.etPromocion);
        imAgregar=(ImageButton)view.findViewById(R.id.imAgregar);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyckerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        promociones=new ArrayList<Promocion>();
        cargarDatos();
        headerAdapter =new HeaderAdapter(promociones);
        recyclerView.setAdapter(headerAdapter);
        imAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String promocion=etPromocion.getText().toString();
                if(!promocion.equalsIgnoreCase(""))
                {
                    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
                    //apuntamos al nodo que queremos leer
                    DatabaseReference myRef = fdb.getReference("Promociones");
                    myRef.push().child("promocion").setValue(promocion,"Estoy escribiendo datos", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                Toast.makeText(getActivity().getApplicationContext(), "Hubo un problema",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "Agregado con exito",Toast.LENGTH_SHORT).show();
                                etPromocion.setText("");
                            }
                        }
                    });


                }else
                    Toast.makeText(getActivity().getApplicationContext(), "Introduce una promocion valida",Toast.LENGTH_SHORT).show();

            }
        });
        return view;

    }
    /**
     * cargardatos, obtiene los datos de la base de datos y los carga en una lista.


     */
    private void cargarDatos()
    {
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef = fdb.getReference("Promociones");
        myRef.addValueEventListener(new ValueEventListener() {
            /**
             * on DataChange, metodo que detecta si se produce un cambio en la base de datos.
             * @param dataSnapshot guarda todos los datos
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                promociones.removeAll(promociones);
                int i=0;


                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    String clave =snapshot.getKey();
                    String valor= snapshot.child("promocion").getValue().toString();
                    Promocion prueba=new Promocion(clave,valor);
                    Log.e("claves", "la clave es: "+clave);
                    promociones.add(prueba);
                }
                headerAdapter.notifyDataSetChanged();
            }
            /**
             * on Cancelled, si se cancela la conexion con la base de datos
             * @param databaseError guarda los errores
             */
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "onCancelled: "+databaseError);
            }
        });

    }

}
