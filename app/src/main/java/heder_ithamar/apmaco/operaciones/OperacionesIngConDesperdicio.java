package heder_ithamar.apmaco.operaciones;

import java.text.DecimalFormat;

/**
 * Created by Heder_ithamar on 28/08/2015.
 */
public class OperacionesIngConDesperdicio {

    private String resultado,tipo;
    private Double cemento, arena, graba, agua, totalCem, totalAre, totalGrab, totalAgu, elemVolumen;

    private String calculaOperacion(String tipo, Double cemento, Double arena, Double graba, Double agua, Double volumen, Double elementos) {
        //Valores para calcular el total
        totalCem = elementos * (volumen * cemento);
        totalAre = elementos * (volumen * arena);
        totalGrab = elementos * (volumen * graba);
        totalAgu = elementos * (volumen * agua);

        elemVolumen = elementos * volumen;

        //Imprimir resultado
        resultado = Imprime( volumen, elementos, elemVolumen, totalCem, totalAre, totalGrab, totalAgu, tipo);

        return (resultado);
    }

    String Imprime(double volumen,double elementos,double elemVolumen, double totalcem, double totalAre, double totalGrab, double totalAgu, String tipo){

        DecimalFormat df = new DecimalFormat("#.###");

        resultado= tipo + " \n\n"
                + " Volumen Inicial: " + df.format(volumen) + " \n"
                + " Unidades:            " + df.format(elementos) + " \n"
                + " Volumen Total:   " + df.format(elemVolumen) + " \n"
                + " Cemento klb:     " + df.format(totalcem) + " \n"
                + " Arena f^3:           " + df.format(totalAre) + " \n"
                + " Grava f^3:           " + df.format(totalGrab) + " \n"
                + " Agua f^3:            " + df.format(totalAgu);

        return (resultado);
    }

    public String Calcula100(double volumen, double elementos){
        //Valores constantes para 100
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.273 * 0.06242795645; arena = 0.542; graba = 0.656; agua = 0.271;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula150( double volumen,double elementos){
        //Valores para 150
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.326 * 0.06242795645; arena = 0.536; graba = 0.65; agua = 0.263;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula200( double volumen, double elementos){
        //Valores para 200
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.368 * 0.06242795645; arena = 0.531; graba = 0.643; agua = 0.252;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula250(double volumen,double elementos){
        //Valores para 250
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.412 * 0.06242795645; arena = 0.535; graba = 0.637; agua = 0.243;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula300( double volumen, double elementos){
        //Valores para 300
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.442 * 0.06242795645; arena = 0.54; graba = 0.654; agua = 0.245;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca100( double volumen, double elementos){
        //Valores constantes para 100
        tipo="CEMENTO TOLTECA";
        cemento = 0.2575 * 0.06242795645; arena = 0.66073; graba = 0.72255; agua = 0.26719;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca150(double volumen,double elementos){
        //Valores constantes para 150
        tipo="CEMENTO TOLTECA";
        cemento = 0.309 * 0.06242795645; arena = 0.6099; graba = 0.70139; agua = 0.285;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca200( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 0.3605 * 0.06242795645; arena = 0.56924; graba = 0.71155; agua = 0.24938;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca250( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 0.412 * 0.06242795645; arena = 0.48792; graba = 0.65056; agua = 0.25327;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaTolteca300( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 0.4635 * 0.06242795645; arena = 0.42687; graba = 0.6404; agua = 0.21375;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco100( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 0.2575 * 0.06242795645; arena = 0.76238; graba = 0.8132; agua = 0.35625;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco150(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 0.309 * 0.06242795645; arena = 0.67089; graba = 0.79287; agua = 0.36625;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaApasco200(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 0.3605 * 0.06242795645; arena = 0.56924; graba = 0.85386; agua = 0.3325;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco250( double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO APASCO";
        cemento = 0.412 * 0.06242795645; arena = 0.56924; graba = 0.8132; agua = 0.3325;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco300( double volumen,double elementos){
        //Valores constantes para 300
        tipo="CEMENTO APASCO";
        cemento = 0.4635 * 0.06242795645; arena = 0.45743; graba = 0.82337; agua = 0.21375;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma100(double volumen,double elementos){
        //Valores constantes para 100
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.2575 * 0.06242795645; arena = 0.66073; graba = 0.76238; agua = 0.29688;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma150( double volumen, double elementos){
        //Valores constantes para 150
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.309 * 0.06242795645; arena = 0.67089; graba = 0.79287; agua = 0.285;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma200( double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.3605 * 0.06242795645; arena = 0.6404; graba = 0.78271; agua = 0.29094;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma250(double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.412 * 0.06242795645; arena = 0.56924; graba = 0.8132; agua = 0.285;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma300( double volumen, double elementos){
        //Valores constantes para 300
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.4635 * 0.06242795645; arena = 0.45743; graba = 0.73188; agua = 0.26719;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    //<editor-fold desc="METODOS GETTER">
    public double getTotalCem(){
        return this.totalCem;
    }
    public double getTotalAre(){
        return this.totalAre;
    }
    public double getTotalGrab(){
        return this.totalGrab;
    }
    public double getTotalAgua(){
        return this.totalAgu;
    }
    public double getElemVolumen(){
        return this.elemVolumen;
    }
    //</editor-fold>

}
