package heder_ithamar.apmaco.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import heder_ithamar.apmaco.R;

/**
 * Fragmento para la sección de "Inicio"
 */
public class FragmentoInicio extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;

    public FragmentoInicio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        adaptador = new AdaptadorInicio();

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();


                Log.i("DemoRecView", "Pulsado el elemento " + reciclador.getChildPosition(v));

                switch (reciclador.getChildPosition(v)){
                    case 0 :
                        Fragment fredificios = new FragmentoCategorias();
                        ft.replace(R.id.contenedor_principal, fredificios);
                        ft.commit();
                        break;
                    case 1 :
                        Fragment frcarretera = new FragmentoCampos();
                        ft.replace(R.id.contenedor_principal, frcarretera);
                        ft.commit();
                        break;
                    case 2:
                        Fragment frpuentes = new FragmentoCategorias();
                        ft.replace(R.id.contenedor_principal, frpuentes);
                        ft.commit();
                        break;
                    default:
                        Toast.makeText(getActivity(), "Elija una opción", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });

        reciclador.setAdapter(adaptador);
        return view;
    }

}
