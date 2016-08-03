package heder_ithamar.apmaco.operaciones;

import java.text.DecimalFormat;

/**
 * Created by HederIthamar on 29/10/2015.
 */
public class OpContenidoMinimoConDesp {
    private Double volumenElementos, u_cemento, u_grava, u_arena, u_agua, u_aire, cemento, grava, arena, agua, aire, bd_cemento,bd_grava,bd_arena,bd_agua,bd_aire;
    private String resultado;

    public  String CalculaContenidoMinSinDes(double maxiaire, double wga, double gravaArena, double densidadA, double densidadG, double resistence, double densidadC , double volumen,double elementos){

        bd_aire = maxiaire;
        bd_arena = wga * (1 - gravaArena) / (densidadA * 1000);
        bd_grava = wga * gravaArena / (densidadG * 1000);
        bd_cemento = (1 - (bd_arena + bd_grava)- bd_aire) / (1 + resistence * densidadC) * densidadC;
        bd_agua = 1 - bd_cemento / densidadC  - bd_arena - bd_grava - bd_aire;
        u_cemento = (volumen * bd_cemento );
        u_arena = (volumen * bd_arena);
        u_grava = (volumen * bd_grava);
        u_agua = (volumen * bd_agua);
        u_aire = volumen * bd_aire ;
        volumenElementos = volumen * elementos;
        cemento = u_cemento * elementos;
        arena = u_arena * elementos;
        grava = u_grava * elementos;
        agua = u_agua * elementos;
        aire = u_aire * elementos;

        DecimalFormat df = new DecimalFormat("#.###");

        resultado= " Volumen Unid:  " + df.format(volumen) + " \n"
                + " Elementos   :      " + df.format(elementos) + " \n"
                + " Vol. por Elem.:   " + df.format(volumenElementos) + " \n"
                + " Cemento Tons:   " + df.format(cemento) + " \n"
                + " Arena m^3:         " + df.format(arena) + " \n"
                + " Grava m^3:         " + df.format(grava) + " \n"
                + " Agua m^3:          " + df.format(agua) + " \n"
                + " Aire:                   " + df.format(aire);

        return (resultado);

    }
    public  String CalculaContenidoMin(double maxiaire, double wga, double gravaArena, double densidadA, double densidadG, double resistence, double densidadC , double volumen,double elementos){

        bd_aire = maxiaire;
        bd_arena = wga * (1- gravaArena) / (densidadA * 1000);
        bd_grava = wga * gravaArena / (densidadG * 1000);
        bd_cemento = (1 - (bd_arena + bd_grava)- bd_aire) / (1 + resistence * densidadC) * densidadC;
        bd_agua = 1 - bd_cemento / densidadC  - bd_arena - bd_grava - bd_aire;

        u_cemento = (volumen * bd_cemento )* 1.03;
        u_arena = (volumen * bd_arena) * 1.07;
        u_grava = (volumen * bd_grava) *1.07;
        u_agua = (volumen * bd_agua) *1.25;
        u_aire = volumen * bd_aire ;
        volumenElementos = volumen * elementos;
        cemento = u_cemento * elementos;
        arena = u_arena * elementos;
        grava = u_grava * elementos;
        agua = u_agua * elementos;
        aire = u_aire * elementos;


        DecimalFormat df = new DecimalFormat("#.###");

        resultado= " Volumen Unid:  " + df.format(volumen) + " \n"
                + " Elementos   :      " + df.format(elementos) + " \n"
                + " Vol. por Elem.:   " + df.format(volumenElementos) + " \n"
                + " Cemento Tons:   " + df.format(cemento) + " \n"
                + " Arena m^3:         " + df.format(arena) + " \n"
                + " Grava m^3:         " + df.format(grava) + " \n"
                + " Agua m^3:          " + df.format(agua) + " \n"
                + " Aire:                   " + df.format(aire);

        return (resultado);

    }

    //<editor-fold desc="Metodos getter">
    public Double getCemento() {
        return cemento;
    }

    public Double getGrava() {
        return grava;
    }

    public Double getArena() {
        return arena;
    }

    public Double getAgua() {
        return agua;
    }

    public Double getAire() {
        return aire;
    }

    public Double getVolumenElementos() {
        return volumenElementos;
    }
    //</editor-fold>
}
