package heder_ithamar.apmaco.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import heder_ithamar.apmaco.R;
import heder_ithamar.apmaco.modelo.Estructuras;
import heder_ithamar.apmaco.uiContenidoMinimo.AppCilindroCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppConoTruncadoCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppElipticaCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppEsferaCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppParalelepipedoCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppPiramideCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppPiramideRectTruncadaCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppPiramideTrianTruncaCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppRectSemiCirculoCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppTrapecioCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppTriangularCM;
import heder_ithamar.apmaco.uiContenidoMinimo.ResultadoCM;
import heder_ithamar.apmaco.uiLabTabla.AppLabCilindroTab;
import heder_ithamar.apmaco.uiLabTabla.AppLabElipticaTab;
import heder_ithamar.apmaco.uiLabTabla.AppLabParalelepipedoTab;
import heder_ithamar.apmaco.uiLabTabla.AppLabTrapecioTab;
import heder_ithamar.apmaco.uiLabTabla.AppLabTriangularTab;
import heder_ithamar.apmaco.uiLabTabla.AppLabVolumenTab;
import heder_ithamar.apmaco.uiLabVolumen.AppLabCilindro;
import heder_ithamar.apmaco.uiLabVolumen.AppLabEliptica;
import heder_ithamar.apmaco.uiLabVolumen.AppLabParalelepipedo;
import heder_ithamar.apmaco.uiLabVolumen.AppLabTrapecio;
import heder_ithamar.apmaco.uiLabVolumen.AppLabTriangular;
import heder_ithamar.apmaco.uiLabVolumen.AppLabVolumen;
import heder_ithamar.apmaco.uiProporcion.AppCilindroTab;
import heder_ithamar.apmaco.uiProporcion.AppConoTruncadoTab;
import heder_ithamar.apmaco.uiProporcion.AppElipticaTab;
import heder_ithamar.apmaco.uiProporcion.AppEsferaProp;
import heder_ithamar.apmaco.uiProporcion.AppParalelepipedoTab;
import heder_ithamar.apmaco.uiProporcion.AppPiramideRectTruncadaTab;
import heder_ithamar.apmaco.uiProporcion.AppPiramideTab;
import heder_ithamar.apmaco.uiProporcion.AppPiramideTrianTruncaTab;
import heder_ithamar.apmaco.uiProporcion.AppRectSemiCirculoTab;
import heder_ithamar.apmaco.uiProporcion.AppTrapecioTab;
import heder_ithamar.apmaco.uiProporcion.AppTriangularTab;
import heder_ithamar.apmaco.uiVolumen.AppVolumen;
import heder_ithamar.apmaco.uiVolumen.AppVolumenTab;

/**
 * Created by HederIthamar on 09/05/2016.
 */
public class FragmentoCampo extends Fragment {

    private static final String INDICE_SECCION
            = "heder_ithamar.apmaco.FragmentoCamposTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorCategorias adaptador;

    public static FragmentoCampo nuevaInstancia(int indiceSeccion) {
        FragmentoCampo fragment = new FragmentoCampo();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grup_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(layoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                adaptador = new AdaptadorCategorias(Estructuras.EDUVOLUMEN);
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (reciclador.getChildPosition(v)){
                            case 0 :
                                Intent appVolumen = new Intent(getActivity(), AppLabVolumen.class);
                                startActivity(appVolumen);
                                break;
                            case 1 :
                                Intent appParalelepipedo = new Intent(getActivity(), AppLabParalelepipedo.class);
                                startActivity(appParalelepipedo);
                                break;
                            case 2:
                                Intent appCilindro = new Intent(getActivity(),AppLabCilindro.class);
                                startActivity(appCilindro);
                                break;
                            case 3:
                                Intent appTriangular = new Intent(getActivity(), AppLabTriangular.class);
                                startActivity(appTriangular);
                                break;
                            case 4:
                                Intent appTrapecio = new Intent(getActivity(),AppLabTrapecio.class);
                                startActivity(appTrapecio);
                                break;
                            case 5:
                                Intent appEliptica = new Intent(getActivity(),AppLabEliptica.class);
                                startActivity(appEliptica);
                                break;
                            case 6:
                                Intent resultado = new Intent(getActivity(),Resultados.class);
                                startActivity(resultado);
                                break;
                            default:
                                Toast.makeText(getActivity(), "Nada", Toast.LENGTH_SHORT).show();
                                break;

                        }

                    }
                });
                break;
            case 1:
                adaptador = new AdaptadorCategorias(Estructuras.EDUTABLA);
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (reciclador.getChildPosition(v)){
                            case 0 :
                                Intent appVolumen = new Intent(getActivity(),AppLabVolumenTab.class);
                                startActivity(appVolumen);
                                break;
                            case 1 :
                                Intent appParalelepipedo = new Intent(getActivity(),AppLabParalelepipedoTab.class);
                                startActivity(appParalelepipedo);
                                break;
                            case 2:
                                Intent appCilindro = new Intent(getActivity(), AppLabCilindroTab.class);
                                startActivity(appCilindro);
                                break;
                            case 3:
                                Intent appTriangular = new Intent(getActivity(),AppLabTriangularTab.class);
                                startActivity(appTriangular);
                                break;
                            case 4:
                                Intent appTrapecio = new Intent(getActivity(),AppLabTrapecioTab.class);
                                startActivity(appTrapecio);
                                break;
                            case 5:
                                Intent appEliptica = new Intent(getActivity(),AppLabElipticaTab.class);
                                startActivity(appEliptica);
                                break;
                            case 6:
                                Intent resultado = new Intent(getActivity(),Resultados.class);
                                startActivity(resultado);
                                break;
                            default:
                                Toast.makeText(getActivity(), "Nada", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                });
                break;
            case 2:
                adaptador = new AdaptadorCategorias(Estructuras.EDUDISENO);
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (reciclador.getChildPosition(v)){
                            case 0:
                                Intent appVolumen2 = new Intent(getActivity(),AppVolumen.class);
                                startActivity(appVolumen2);
                                break;
                            case 1:
                                Intent appParalelepipedo = new Intent(getActivity(),AppParalelepipedoCM.class);
                                startActivity(appParalelepipedo);
                                break;
                            case 2:
                                Intent appCilindro = new Intent(getActivity(),AppCilindroCM.class);
                                startActivity(appCilindro);
                                break;
                            case 3:
                                Intent appTriangular = new Intent(getActivity(),AppTriangularCM.class);
                                startActivity(appTriangular);
                                break;
                            case 4:
                                Intent appTrapecio = new Intent(getActivity(),AppTrapecioCM.class);
                                startActivity(appTrapecio);
                                break;
                            case 5:
                                Intent appEliptica = new Intent(getActivity(),AppElipticaCM.class);
                                startActivity(appEliptica);
                                break;
                            case 6:
                                Intent resultadocm = new Intent(getActivity(),ResultadoCM.class);
                                startActivity(resultadocm);
                                break;
                            default:
                                Toast.makeText(getActivity(), "Nada", Toast.LENGTH_SHORT).show();
                                break;

                        }

                    }
                });
                break;
        }
        reciclador.setAdapter(adaptador);
        return view;
    }

}
