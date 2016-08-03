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
 * Fragmento que representa el contenido de cada pestaña dentro de la sección "Categorías"
 */
public class FragmentoCategoria extends Fragment {

    private static final String INDICE_SECCION
            = "heder_ithamar.apmaco.FragmentoCategoriasTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorCategorias adaptador;

    public static FragmentoCategoria nuevaInstancia(int indiceSeccion) {
        FragmentoCategoria fragment = new FragmentoCategoria();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(layoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                adaptador = new AdaptadorCategorias(Estructuras.EDIFICIOS);
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (reciclador.getChildPosition(v)){
                            case 0 :
                                Intent appVolumen = new Intent(getActivity(),AppVolumen.class);
                                startActivity(appVolumen);
                                break;
                            case 1 :
                                Intent appParalelepipedo = new Intent(getActivity(),AppParalelepipedo.class);
                                startActivity(appParalelepipedo);
                                break;
                            case 2:
                                Intent appCilindro = new Intent(getActivity(),AppCilindro.class);
                                startActivity(appCilindro);
                                break;
                            case 3:
                                Intent appPiramide = new Intent(getActivity(),AppPiramide.class);
                                startActivity(appPiramide);
                                break;
                            case 4:
                                Intent appEliptica = new Intent(getActivity(),AppEliptica.class);
                                startActivity(appEliptica);
                                break;
                            case 5:
                                Intent appTriangular = new Intent(getActivity(),AppTriangular.class);
                                startActivity(appTriangular);
                                break;
                            case 6:
                                Intent appTrapecio = new Intent(getActivity(),AppTrapecio.class);
                                startActivity(appTrapecio);
                                break;
                            case 7:
                                Intent appPiramideRecTrun = new Intent(getActivity(),AppPiramideRectTruncada.class);
                                startActivity(appPiramideRecTrun);
                                break;
                            case 8:
                                Intent appTriaTrunc = new Intent(getActivity(),AppPiramideTrianTrunca.class);
                                startActivity(appTriaTrunc);
                                break;
                            case 9:
                                Intent appConoTuncado = new Intent(getActivity(),AppConoTruncado.class);
                                startActivity(appConoTuncado);
                                break;
                            case 10:
                                Intent appEsfera = new Intent(getActivity(),AppEsfera.class);
                                startActivity(appEsfera);
                                break;
                            case 11:
                                Intent appSemiCirculo = new Intent(getActivity(),AppRectSemiCirculo.class);
                                startActivity(appSemiCirculo);
                                break;
                            case 12:
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
                adaptador = new AdaptadorCategorias(Estructuras.PUENTES);
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (reciclador.getChildPosition(v)){
                            case 0 :
                                Intent appVolumen = new Intent(getActivity(),AppVolumenTab.class);
                                startActivity(appVolumen);
                                break;
                            case 1 :
                                Intent appParalelepipedo = new Intent(getActivity(),AppParalelepipedoTab.class);
                                startActivity(appParalelepipedo);
                                break;
                            case 2:
                                Intent appCilindro = new Intent(getActivity(),AppCilindroTab.class);
                                startActivity(appCilindro);
                                break;
                            case 3:
                                Intent appPiramide = new Intent(getActivity(),AppPiramideTab.class);
                                startActivity(appPiramide);
                                break;
                            case 4:
                                Intent appEliptica = new Intent(getActivity(),AppElipticaTab.class);
                                startActivity(appEliptica);
                                break;
                            case 5:
                                Intent appTriangular = new Intent(getActivity(),AppTriangularTab.class);
                                startActivity(appTriangular);
                                break;
                            case 6:
                                Intent appTrapecio = new Intent(getActivity(),AppTrapecioTab.class);
                                startActivity(appTrapecio);
                                break;
                            case 7:
                                Intent appPiramideRecTrun = new Intent(getActivity(),AppPiramideRectTruncadaTab.class);
                                startActivity(appPiramideRecTrun);
                                break;
                            case 8:
                                Intent appTriaTrunc = new Intent(getActivity(),AppPiramideTrianTruncaTab.class);
                                startActivity(appTriaTrunc);
                                break;
                            case 9:
                                Intent appConoTuncado = new Intent(getActivity(),AppConoTruncadoTab.class);
                                startActivity(appConoTuncado);
                                break;
                            case 10:
                                Intent appEsfera = new Intent(getActivity(),AppEsferaProp.class);
                                startActivity(appEsfera);
                                break;
                            case 11:
                                Intent appSemiCirculo = new Intent(getActivity(),AppRectSemiCirculoTab.class);
                                startActivity(appSemiCirculo);
                                break;
                            case 12:
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
                adaptador = new AdaptadorCategorias(Estructuras.CARRETERAS);
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (reciclador.getChildPosition(v)){
                            case 0:
                                Intent appVolumen2 = new Intent(getActivity(),AppVolumen.class);
                                startActivity(appVolumen2);
                                break;
                            case 1:
                                Intent appTriangular = new Intent(getActivity(),AppTriangularCM.class);
                                startActivity(appTriangular);
                                break;
                            case 2:
                                Intent appTrapecio = new Intent(getActivity(),AppTrapecioCM.class);
                                startActivity(appTrapecio);
                                break;
                            case 3:
                                Intent appPiramideRecTrun = new Intent(getActivity(),AppPiramideRectTruncadaCM.class);
                                startActivity(appPiramideRecTrun);
                                break;
                            case 4:
                                Intent appTriaTrunc = new Intent(getActivity(),AppPiramideTrianTruncaCM.class);
                                startActivity(appTriaTrunc);
                                break;
                            case 5:
                                Intent appParalelepipedo = new Intent(getActivity(),AppParalelepipedoCM.class);
                                startActivity(appParalelepipedo);
                                break;
                            case 6:
                                Intent appCilindro = new Intent(getActivity(),AppCilindroCM.class);
                                startActivity(appCilindro);
                                break;
                            case 7:
                                Intent appPiramide = new Intent(getActivity(),AppPiramideCM.class);
                                startActivity(appPiramide);
                                break;
                            case 8:
                                Intent appEliptica = new Intent(getActivity(),AppElipticaCM.class);
                                startActivity(appEliptica);
                                break;
                            case 9:
                                Intent appConoTuncado = new Intent(getActivity(),AppConoTruncadoCM.class);
                                startActivity(appConoTuncado);
                                break;
                            case 10:
                                Intent appEsfera = new Intent(getActivity(),AppEsferaCM.class);
                                startActivity(appEsfera);
                                break;
                            case 11:
                                Intent appSemiCirculo = new Intent(getActivity(),AppRectSemiCirculoCM.class);
                                startActivity(appSemiCirculo);
                                break;
                            case 12:
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
