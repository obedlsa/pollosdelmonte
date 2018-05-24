package com.example.obed.pollosdelmonte;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** Clase Fragmentvisitas.

 @author Obed Alvarado
 @version 18/05/2018
 */
public class FragmentVisitas extends Fragment {
    private AppBarLayout appBar;
    private TabLayout   tabs;
    private ViewPager viewPager;
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
       View view= inflater.inflate(R.layout.fragment_fragment_visitas, container, false);
       View contenedor = (View) container.getParent();
       appBar= (AppBarLayout) contenedor.findViewById(R.id.appbar);
       tabs=new TabLayout(getActivity());
       tabs.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
       appBar.addView(tabs);
       viewPager=(ViewPager)view.findViewById(R.id.pager);
       ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getFragmentManager());
       viewPager.setAdapter(pagerAdapter);
       tabs.setupWithViewPager(viewPager);

       return view;

    }
    /**
     * on destroyview, metodo que se ejecuta al cambiar de fragment.

     *

     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabs);
    }
    /** Class ViewPagerAdapter

     @author Obed Alvarado
     @version 18/05/2018
     */
    public class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        /**
         Constructor
         @param fragmentManager: manejador del fragment

         *

         */
        public ViewPagerAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);

        }
        String [] titulostab={"Diarias","Mensuales","Anuales"};
        /**
         * getitem, obtiene el numero de titulos

         *

         */
        @Override
        public Fragment getItem(int position) {
            switch(position)
            {
                case 0: return new Fragmentvisitasdias();
                case 1: return new Fragmentvisitasmeses();
                case 2: return new Fragmentvisitasanos();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulostab[position];
        }
    }
}
