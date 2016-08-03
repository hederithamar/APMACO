package heder_ithamar.apmaco.operaciones;

import java.text.DecimalFormat;

/**
 * Created by HederIthamar on 13/05/2016.
 */
public class OperacionCarretillas {
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
                + " Carretillas de Cemento:   " + df.format(totalcem) + " \n"
                + " Carretillas de Arena:          " + df.format(totalAre) + " \n"
                + " Carretillas de Grava:          " + df.format(totalGrab) + " \n"
                + " Carretillas de Agua:           " + df.format(totalAgu);

        return (resultado);
    }

    public String Calcula100(double volumen, double elementos){
        //Valores constantes para 100
        tipo="VOLUMEN DE CONCRETO";
        cemento = 2.73; arena = 7.13; graba = 8.63; agua = 3.57;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula150( double volumen,double elementos){
        //Valores para 150
        tipo="VOLUMEN DE CONCRETO";
        cemento = 3.26; arena = 7.05; graba = 8.55; agua = 3.46;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula200( double volumen, double elementos){
        //Valores para 200
        tipo="VOLUMEN DE CONCRETO";
        cemento = 3.68; arena = 6.99; graba = 8.46; agua = 3.32;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula250(double volumen,double elementos){
        //Valores para 250
        tipo="VOLUMEN DE CONCRETO";
        cemento = 4.12; arena = 7.04; graba = 8.38; agua = 3.20;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo, cemento, arena, graba, agua, volumen, elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula300( double volumen, double elementos){
        //Valores para 300
        tipo="VOLUMEN DE CONCRETO";
        cemento = 4.42; arena = 7.11; graba = 8.61; agua = 4.54;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca100( double volumen, double elementos){
        //Valores constantes para 100
        tipo="CEMENTO TOLTECA";
        cemento = 2.32; arena = 7.83; graba = 8.43; agua = 3.17;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca150(double volumen,double elementos){
        //Valores constantes para 150
        tipo="CEMENTO TOLTECA";
        cemento = 2.83; arena = 7.35; graba = 8.46; agua = 3.44;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca200( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 3.37; arena = 6.99; graba = 8.74; agua = 3.06;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca250( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 4.13; arena = 6.44; graba = 8.59; agua = 3.34;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaTolteca300( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 5.2; arena = 6.30; graba = 3.46; agua = 3.16;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco100( double volumen,double elementos){
        //Valores constantes para 100
        tipo="CEMENTO APASCO";
        cemento = 2.01; arena = 7.82; graba = 8.34; agua = 3.66;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco150(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 2.53; arena = 7.23; graba = 8.54; agua = 3.84;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaApasco200(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 3.01; arena = 6.24; graba = 9.37; agua = 3.65;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco250( double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO APASCO";
        cemento = 3.49; arena = 6.34; graba = 9.06; agua = 3.70;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco300( double volumen,double elementos){
        //Valores constantes para 300
        tipo="CEMENTO APASCO";
        cemento = 5.20; arena = 6.75; graba = 12.16; agua = 3.16;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma100(double volumen,double elementos){
        //Valores constantes para 100
        tipo="CEMENTO MOCTEZUMA";
        cemento = 2.23; arena = 7.51; graba = 8.67; agua = 3.38;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma150( double volumen, double elementos){
        //Valores constantes para 150
        tipo="CEMENTO MOCTEZUMA";
        cemento = 2.61; arena = 7.46; graba = 8.81; agua = 3.17;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma200( double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 3.06; arena = 7.15; graba = 8.74; agua = 3.25;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma250(double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 3.56; arena = 6.47; graba = 9.24; agua = 3.24;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma300( double volumen, double elementos){
        //Valores constantes para 300
        tipo="CEMENTO MOCTEZUMA";
        cemento = 4.71; arena = 6.12; graba = 9.79; agua = 3.57;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    //Cemento cruz azul
    public String CalculaAzul100( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 2.26; arena = 7.32; graba = 8.79; agua = 3.42;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaAzul150(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 2.53; arena = 7.23; graba = 8.87; agua = 3.46;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaAzul200(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 3.18; arena = 7.01; graba = 8.66; agua = 3.37;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaAzul250( double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO CRUZ AZUL";
        cemento = 4.71; arena = 6.12; graba = 8.68; agua = 3.38;

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


