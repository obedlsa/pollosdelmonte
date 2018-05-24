package com.example.obed.pollosdelmonte;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;
/** Clase ExpLVAdapter.

 @author Obed Alvarado
 @version 18/05/2018
 */
public class ExpLVAdapter extends BaseExpandableListAdapter{
    private ArrayList<String> listCategorias;
    private Map<String,ArrayList<String>> mapChild;
    private Context context;
    /**
     * Constructor, metodo que se ejecuta al comienzo del activity.
     * @param listCategorias: para guardar las categorias.
     * @param mapChild: guarda todos los arraylist de los elementos.
     * @param context: representa el contexto de la app.
     *

     */
    public ExpLVAdapter(ArrayList<String> listCategorias, Map<String, ArrayList<String>> mapChild, Context context) {
        this.listCategorias = listCategorias;
        this.mapChild = mapChild;
        this.context = context;
    }
    /**
     * getGroupcount, obtener el grupo.

     *

     */
    @Override
    public int getGroupCount() {

        return listCategorias.size();
    }
    /**
     * getChildrenCount, obtiene el hijo del elemento
     * @param i posicion del menu

     *

     */
    @Override
    public int getChildrenCount(int i) {
        return mapChild.get(listCategorias.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listCategorias.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mapChild.get(listCategorias.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String titulocategoria= (String) getGroup(i);
        view = LayoutInflater.from(context).inflate(R.layout.lsgroup,null
        );
        TextView tvGroup= (TextView) view.findViewById(R.id.tvgroup);
        tvGroup.setText(titulocategoria);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String item= (String) getChild(i,i1);
        view =LayoutInflater.from(context).inflate(R.layout.lschild,null);
        TextView tvChild = (TextView) view.findViewById(R.id.tvchild);
        tvChild.setText(item);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
