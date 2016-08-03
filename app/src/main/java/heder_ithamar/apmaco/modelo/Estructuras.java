package heder_ithamar.apmaco.modelo;

import java.util.ArrayList;
import java.util.List;

import heder_ithamar.apmaco.R;

/**
 * Created by Heder_ithamar on 27/09/2015.
 */
public class Estructuras {

    private int precio;
    private int nombre;
    private int idDrawable;

    public Estructuras(int precio, int nombre, int idDrawable) {
        this.precio = precio;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public static final List<Estructuras> INICIO = new ArrayList<Estructuras>();
    public static final List<Estructuras> EDIFICIOS = new ArrayList<>();
    public static final List<Estructuras> PUENTES = new ArrayList<>();
    public static final List<Estructuras> CARRETERAS = new ArrayList<>();

    public static final List<Estructuras> EDUVOLUMEN = new ArrayList<>();
    public static final List<Estructuras> EDUTABLA = new ArrayList<>();
    public static final List<Estructuras> EDUDISENO = new ArrayList<>();


    static {
        //INICIO
        INICIO.add(new Estructuras(R.string.msj_educativo , R.string.descedificio, R.drawable.edificios));
        INICIO.add(new Estructuras(R.string.msj_laboral, R.string.descpuente, R.drawable.puente));
        //EDIFICIOS
        EDIFICIOS.add(new Estructuras(R.string.title_activity_app_volumen, R.string.msj_mvolumen, R.drawable.cafe));
        EDIFICIOS.add(new Estructuras(R.string.btn_parale, R.string.msj_mvolumen, R.drawable.paralelepipedo));
        EDIFICIOS.add(new Estructuras(R.string.btn_cilind, R.string.msj_mvolumen, R.drawable.cilindro));
        EDIFICIOS.add(new Estructuras(R.string.btn_pirami, R.string.msj_mvolumen, R.drawable.piramide));
        EDIFICIOS.add(new Estructuras(R.string.btn_elipti, R.string.msj_mvolumen, R.drawable.eliptica));
        EDIFICIOS.add(new Estructuras(R.string.btn_triang, R.string.msj_mvolumen, R.drawable.triangular));
        EDIFICIOS.add(new Estructuras(R.string.btn_trapec, R.string.msj_mvolumen, R.drawable.trapecio));
        EDIFICIOS.add(new Estructuras(R.string.btn_rectru, R.string.msj_mvolumen, R.drawable.rectruncada));
        EDIFICIOS.add(new Estructuras(R.string.btn_tritru, R.string.msj_mvolumen, R.drawable.triatruncada));
        EDIFICIOS.add(new Estructuras(R.string.btn_conotr, R.string.msj_mvolumen, R.drawable.conotruncado));
        EDIFICIOS.add(new Estructuras(R.string.btn_esfera, R.string.msj_mvolumen, R.drawable.esfera));
        EDIFICIOS.add(new Estructuras(R.string.btn_semici, R.string.msj_mvolumen, R.drawable.semicirculo));
        EDIFICIOS.add(new Estructuras(R.string.msj_resultado, R.string.msj_mvolumen, R.drawable.resultadofinal));
        //PUENTES
        PUENTES.add(new Estructuras(R.string.title_activity_app_volumen, R.string.msj_tabla, R.drawable.cafe));
        PUENTES.add(new Estructuras(R.string.btn_parale, R.string.msj_tabla, R.drawable.paralelepipedo));
        PUENTES.add(new Estructuras(R.string.btn_cilind, R.string.msj_tabla, R.drawable.cilindro));
        PUENTES.add(new Estructuras(R.string.btn_pirami, R.string.msj_tabla, R.drawable.piramide));
        PUENTES.add(new Estructuras(R.string.btn_elipti, R.string.msj_tabla, R.drawable.eliptica));
        PUENTES.add(new Estructuras(R.string.btn_triang, R.string.msj_tabla, R.drawable.triangular));
        PUENTES.add(new Estructuras(R.string.btn_trapec, R.string.msj_tabla, R.drawable.trapecio));
        PUENTES.add(new Estructuras(R.string.btn_rectru, R.string.msj_tabla, R.drawable.rectruncada));
        PUENTES.add(new Estructuras(R.string.btn_tritru, R.string.msj_tabla, R.drawable.triatruncada));
        PUENTES.add(new Estructuras(R.string.btn_conotr, R.string.msj_tabla, R.drawable.conotruncado));
        PUENTES.add(new Estructuras(R.string.btn_esfera, R.string.msj_tabla, R.drawable.esfera));
        PUENTES.add(new Estructuras(R.string.btn_semici, R.string.msj_tabla, R.drawable.semicirculo));
        PUENTES.add(new Estructuras(R.string.msj_resultado, R.string.msj_tabla, R.drawable.resultadofinal));
        //CARRETERAS
        CARRETERAS.add(new Estructuras(R.string.title_activity_app_volumen, R.string.msj_contenidominimo, R.drawable.cafe));
        CARRETERAS.add(new Estructuras(R.string.btn_triang, R.string.msj_contenidominimo, R.drawable.triangular));
        CARRETERAS.add(new Estructuras(R.string.btn_trapec, R.string.msj_contenidominimo, R.drawable.trapecio));
        CARRETERAS.add(new Estructuras(R.string.btn_rectru, R.string.msj_contenidominimo, R.drawable.rectruncada));
        CARRETERAS.add(new Estructuras(R.string.btn_tritru, R.string.msj_contenidominimo, R.drawable.triatruncada));
        CARRETERAS.add(new Estructuras(R.string.btn_parale, R.string.msj_contenidominimo, R.drawable.paralelepipedo));
        CARRETERAS.add(new Estructuras(R.string.btn_cilind, R.string.msj_contenidominimo, R.drawable.cilindro));
        CARRETERAS.add(new Estructuras(R.string.btn_pirami, R.string.msj_contenidominimo, R.drawable.piramide));
        CARRETERAS.add(new Estructuras(R.string.btn_elipti, R.string.msj_contenidominimo, R.drawable.eliptica));
        CARRETERAS.add(new Estructuras(R.string.btn_conotr, R.string.msj_contenidominimo, R.drawable.conotruncado));
        CARRETERAS.add(new Estructuras(R.string.btn_esfera, R.string.msj_contenidominimo, R.drawable.esfera));
        CARRETERAS.add(new Estructuras(R.string.btn_semici, R.string.msj_contenidominimo, R.drawable.semicirculo));
        CARRETERAS.add(new Estructuras(R.string.msj_resultado, R.string.msj_contenidominimo, R.drawable.resultadofinal));

        //CAMPO EDUCACIONAL
        //VOLUMEN
        EDUVOLUMEN.add(new Estructuras(R.string.title_activity_app_volumen, R.string.msj_mvolumen, R.drawable.cafe));
        EDUVOLUMEN.add(new Estructuras(R.string.btn_parale, R.string.msj_mvolumen, R.drawable.paralelepipedo));
        EDUVOLUMEN.add(new Estructuras(R.string.btn_cilind, R.string.msj_mvolumen, R.drawable.cilindro));
        EDUVOLUMEN.add(new Estructuras(R.string.btn_triang, R.string.msj_mvolumen, R.drawable.triangular));
        EDUVOLUMEN.add(new Estructuras(R.string.btn_trapec, R.string.msj_mvolumen, R.drawable.trapecio));
        EDUVOLUMEN.add(new Estructuras(R.string.btn_elipti, R.string.msj_mvolumen, R.drawable.eliptica));
        EDUVOLUMEN.add(new Estructuras(R.string.msj_resultado, R.string.msj_mvolumen, R.drawable.resultadofinal));
        //TABLA
        EDUTABLA.add(new Estructuras(R.string.title_activity_app_volumen, R.string.msj_tabla, R.drawable.cafe));
        EDUTABLA.add(new Estructuras(R.string.btn_parale, R.string.msj_tabla, R.drawable.paralelepipedo));
        EDUTABLA.add(new Estructuras(R.string.btn_cilind, R.string.msj_tabla, R.drawable.cilindro));
        EDUTABLA.add(new Estructuras(R.string.btn_triang, R.string.msj_tabla, R.drawable.triangular));
        EDUTABLA.add(new Estructuras(R.string.btn_trapec, R.string.msj_tabla, R.drawable.trapecio));
        EDUTABLA.add(new Estructuras(R.string.btn_elipti, R.string.msj_tabla, R.drawable.eliptica));
        EDUTABLA.add(new Estructuras(R.string.msj_resultado, R.string.msj_tabla, R.drawable.resultadofinal));
        //DISEÑO
        EDUDISENO.add(new Estructuras(R.string.title_activity_app_volumen, R.string.msj_contenidominimo, R.drawable.cafe));
        EDUDISENO.add(new Estructuras(R.string.btn_parale, R.string.msj_contenidominimo, R.drawable.paralelepipedo));
        EDUDISENO.add(new Estructuras(R.string.btn_cilind, R.string.msj_contenidominimo, R.drawable.cilindro));
        EDUDISENO.add(new Estructuras(R.string.btn_triang, R.string.msj_contenidominimo, R.drawable.triangular));
        EDUDISENO.add(new Estructuras(R.string.btn_trapec, R.string.msj_contenidominimo, R.drawable.trapecio));
        EDUDISENO.add(new Estructuras(R.string.btn_elipti, R.string.msj_contenidominimo, R.drawable.eliptica));
        EDUDISENO.add(new Estructuras(R.string.msj_resultado, R.string.msj_contenidominimo, R.drawable.resultadofinal));
    }

    public int getPrecio() {
        return precio;
    }

    public int getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
}
