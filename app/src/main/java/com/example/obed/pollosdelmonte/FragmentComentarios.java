package com.example.obed.pollosdelmonte;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import objetos.Comentario;

/** Clase FragmentComentarios.

 @author Obed Alvarado
 @version 18/05/2018
 */
public class FragmentComentarios extends Fragment {
    private ExpandableListView expLV;
    private ExpLVAdapter adapter;
    private ArrayList<String> listCategorias;
    private Map<String,ArrayList<String>> mapChild;
    private ImageButton imRecargar;
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
        View view=inflater.inflate(R.layout.fragment_fragment_comentarios, container, false);
        expLV=(ExpandableListView) view.findViewById(R.id.expLV);
        listCategorias =new ArrayList<>();
        mapChild=new HashMap<>();
    cargarDatos();
    imRecargar=(ImageButton)view.findViewById(R.id.imRecargar);
    imRecargar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            adapter.notifyDataSetChanged();
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
        DatabaseReference myRef = fdb.getReference("Comentarios");
        myRef.addValueEventListener(new ValueEventListener() {
            /**
             * on DataChange, metodo que detecta si se produce un cambio en la base de datos.
             * @param dataSnapshot guarda todos los datos
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mapChild.clear();
                listCategorias.removeAll(listCategorias);
                int i=0;
                String hola=dataSnapshot.toString();
                for (DataSnapshot snapshot:
                     dataSnapshot.getChildren()) {
                    ArrayList<String> prueba=new ArrayList<>();
                    Comentario comentario= snapshot.getValue(Comentario.class);
                    listCategorias.add(comentario.getNombre()+" "+comentario.getFecha());
                    Log.e("firebase" ,"cadena: "+hola );
                    prueba.add("comentario:"+comentario.getComentario());
                    prueba.add("email:"+comentario.getEmail());
                    prueba.add("telefono:"+comentario.getTelefono());
                    mapChild.put(listCategorias.get(i),prueba);
                    i++;
                }
                adapter.notifyDataSetChanged();
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
/*
        ArrayList<String> listaaves=new ArrayList<>();
        ArrayList<String> listamamiferos=new ArrayList<>();
        ArrayList<String> listareptiles=new ArrayList<>();
        ArrayList<String> listapeces=new ArrayList<>();
        listCategorias.add("Aves");
        listCategorias.add("Mamiferos");
        listCategorias.add("Reptiles");
        listCategorias.add("Peces");

        listaaves.add("Loro");
        listaaves.add("Aguila");
        listaaves.add("Pajaros");

        listamamiferos.add("Loro");
        listamamiferos.add("Aguila");
        listamamiferos.add("Pajaros");

        listareptiles.add("Loro");
        listareptiles.add("Aguila");
        listareptiles.add("Pajaros");

        listapeces.add("Loro");
        listapeces.add("Aguila");
        listapeces.add("Pajaros");


    mapChild.put(listCategorias.get(0),listaaves);
    mapChild.put(listCategorias.get(1),listamamiferos);
    mapChild.put(listCategorias.get(2),listareptiles);
    mapChild.put(listCategorias.get(3),listapeces);
*/
    adapter =new ExpLVAdapter(listCategorias,mapChild,getActivity().getApplicationContext());
    expLV.setAdapter(adapter);

    }

}
