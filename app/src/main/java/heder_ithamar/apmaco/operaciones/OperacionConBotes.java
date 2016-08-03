package heder_ithamar.apmaco.operaciones;

import java.text.DecimalFormat;

/**
 * Created by HederIthamar on 13/05/2016.
 */
public class OperacionConBotes {
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
                + " Botes de Cemento:   " + df.format(totalcem) + " \n"
                + " Botes de Arena:          " + df.format(totalAre) + " \n"
                + " Botes de Grava:          " + df.format(totalGrab) + " \n"
                + " Botes de Agua:           " + df.format(totalAgu);

        return (resultado);
    }

    public String Calcula100(double volumen, double elementos){
        //Valores constantes para 100
        tipo="VOLUMEN DE CONCRETO";
        cemento = 10.92; arena = 28.53; graba = 34.53; agua = 14.26;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula150( double volumen,double elementos){
        //Valores para 150
        tipo="VOLUMEN DE CONCRETO";
        cemento = 13.04; arena = 28.21; graba = 34.21; agua = 13.84;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula200( double volumen, double elementos){
        //Valores para 200
        tipo="VOLUMEN DE CONCRETO";
        cemento = 14.72; arena = 27.95; graba = 33.84; agua = 13.26;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula250(double volumen,double elementos){
        //Valores para 250
        tipo="VOLUMEN DE CONCRETO";
        cemento = 16.48; arena = 28.16; graba = 33.53; agua = 12.79;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo, cemento, arena, graba, agua, volumen, elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula300( double volumen, double elementos){
        //Valores para 300
        tipo="VOLUMEN DE CONCRETO";
        cemento = 17.68; arena = 28.42; graba = 34.42; agua = 18.16;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca100( double volumen, double elementos){
        //Valores constantes para 100
        tipo="CEMENTO TOLTECA";
        cemento = 9.28; arena = 31.32; graba = 33.73; agua = 13.74;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca150(double volumen,double elementos){
        //Valores constantes para 150
        tipo="CEMENTO TOLTECA";
        cemento = 11.32; arena = 29.41; graba = 33.82; agua = 13.74;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca200( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 13.46; arena = 27.97; graba = 34.96; agua = 12.25;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca250( double volumen,double elementos){
        //Valores constantes para 250
        tipo="CEMENTO TOLTECA";
        cemento = 16.54; arena = 25.77; graba = 34.36; agua = 13.38;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaTolteca300( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 20.81; arena = 25.21; graba = 37.82; agua = 12.62;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco100( double volumen,double elementos){
        //Valores constantes para 100
        tipo="CEMENTO APASCO";
        cemento = 8.03; arena = 31.29; graba = 33.38; agua = 14.62;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco150(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 10.12; arena = 28.92; graba = 34.18; agua = 15.36;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaApasco200(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 12.02; arena = 24.97; graba = 37.46; agua = 036.22;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco250( double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO APASCO";
        cemento = 13.95; arena = 25.36; graba = 36.22; agua = 14.81;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco300( double volumen,double elementos){
        //Valores constantes para 300
        tipo="CEMENTO APASCO";
        cemento = 20.81; arena = 27.02; graba = 48.63; agua = 12.62;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma100(double volumen,double elementos){
        //Valores constantes para 100
        tipo="CEMENTO MOCTEZUMA";
        cemento = 8.90; arena = 30.05; graba = 34.67; agua = 13.50;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma150( double volumen, double elementos){
        //Valores constantes para 150
        tipo="CEMENTO MOCTEZUMA";
        cemento = 10.44; arena = 29.82; graba = 35.25; agua = 12.67;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma200( double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 12.24; arena = 28.61; graba = 34.96; agua = 13.00;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma250(double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO MOCTEZUMA";
        cemento = 12.24; arena = 28.61; graba = 36.98; agua = 12.96;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma300( double volumen, double elementos){
        //Valores constantes para 300
        tipo="CEMENTO MOCTEZUMA";
        cemento = 18.85; arena = 24.48; graba = 39.16; agua = 14.30;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    //Cemento cruz azul
    public String CalculaAzul100( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 9.02; arena = 29.29; graba = 35.15; agua = 13.69;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaAzul150(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 10.12; arena = 28.92; graba = 35.49; agua = 13.82;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaAzul200(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 12.70; arena = 28.04; graba = 34.63; agua = 13.49;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaAzul250( double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO CRUZ AZUL";
        cemento = 14.86; arena = 27.01; graba = 34.73; agua = 13.52;

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

