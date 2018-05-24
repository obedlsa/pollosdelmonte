package com.example.obed.pollosdelmonte;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import objetos.Comentario;

/* Clase Fragmentvisitasdias.
        3  *
        4  * @author Obed Alvarado
       5  * @version 18/05/2018
*/
public class Fragmentvisitasdias extends Fragment {
    private ExpandableListView expLV;
    private ExpLVAdapter adapter;
    private ArrayList<String> listCategorias;
    private Map<String,ArrayList<String>> mapChild;
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
        View view=inflater.inflate(R.layout.fragment_fragment_visitasdias, container, false);
        expLV=(ExpandableListView) view.findViewById(R.id.expLV);
        listCategorias =new ArrayList<>();
        mapChild=new HashMap<>();
        cargarDatos();

        return view;

    }
    /**
     * cargardatos, obtiene los datos de la base de datos y los carga en una lista.


     */
    private void cargarDatos()
    {
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef = fdb.getReference("Visitas/Dias");
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


                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    ArrayList<String> prueba=new ArrayList<>();
                    String clave =snapshot.getKey();
                    String valor= snapshot.getValue().toString();

                    listCategorias.add(clave);

                    prueba.add("            Visitas:"+valor);
                    mapChild.put(listCategorias.get(i),prueba);
                    i++;

                    Log.e("firebase" ,"cadena: "+valor );
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

        adapter =new ExpLVAdapter(listCategorias,mapChild,getActivity().getApplicationContext());
        expLV.setAdapter(adapter);

    }

}
