package heder_ithamar.apmaco.util;

/**
 * Created by Heder_ithamar on 06/08/2015.
 */
public class Forma {
    private String lado_a,lado_b,lado_h,lado_c,lado_d,elementos;

    public Forma(String lado_a, String lado_b, String lado_h, String lado_c, String lado_d, String elementos) {
        this.lado_a = lado_a;
        this.lado_b = lado_b;
        this.lado_h = lado_h;
        this.lado_c = lado_c;
        this.lado_d = lado_d;
        this.elementos = elementos;
    }

    //<editor-fold desc="Metodos getter">
    public String getLado_a() {
        return lado_a;
    }

    public String getLado_b() {
        return lado_b;
    }

    public String getLado_h() {
        return lado_h;
    }

    public String getLado_c() {
        return lado_c;
    }

    public String getLado_d() {
        return lado_d;
    }

    public String getElementos(){return elementos;}
    //</editor-fold>

    //<editor-fold desc="Metodos Setter">
    public void setLado_a(String lado_a) {
        this.lado_a = lado_a;
    }

    public void setLado_b(String lado_b) {
        this.lado_b = lado_b;
    }

    public void setLado_h(String lado_h) {
        this.lado_h = lado_h;
    }

    public void setLado_c(String lado_c) {
        this.lado_c = lado_c;
    }

    public void setLado_d(String lado_d) {
        this.lado_d = lado_d;
    }

    public void setElementos(String elementos){this.elementos = elementos;}

    //</editor-fold>

}
