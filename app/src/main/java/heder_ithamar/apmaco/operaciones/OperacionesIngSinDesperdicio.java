package heder_ithamar.apmaco.operaciones;

import java.text.DecimalFormat;

/**
 * Created by Heder_ithamar on 28/08/2015.
 */
public class OperacionesIngSinDesperdicio {

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
        cemento = 0.26481 * 0.06242795645; arena = 0.50406; graba = 0.61008; agua = 0.20325;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula150( double volumen,double elementos){
        //Valores para 150
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.31622 * 0.06242795645; arena = 0.49848; graba = 0.6045; agua = 0.19725;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula200( double volumen, double elementos){
        //Valores para 200
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.35696 * 0.06242795645; arena = 0.49383; graba = 0.59799; agua = 0.189;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula250(double volumen,double elementos){
        //Valores para 250
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.39964 * 0.06242795645; arena = 0.49755; graba = 0.59241; agua = 0.18225;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula300( double volumen, double elementos){
        //Valores para 300
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.42874 * 0.06242795645; arena = 0.5022; graba = 0.60822; agua = 0.25875;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca100( double volumen, double elementos){
        //Valores constantes para 100
        tipo="CEMENTO TOLTECA";
        cemento = 0.25 * 0.06242795645; arena = 0.6175; graba = 0.665; agua = 0.21375;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca150(double volumen,double elementos){
        //Valores constantes para 150
        tipo="CEMENTO TOLTECA";
        cemento = 0.3 * 0.06242795645; arena = 0.57; graba = 0.6555; agua = 0.228;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca200( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 0.35 * 0.06242795645; arena = 0.532; graba = 0.665; agua = 0.1995;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca250( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 0.4 * 0.06242795645; arena = 0.456; graba = 0.608; agua = 0.20262;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaTolteca300( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 0.45 * 0.06242795645; arena = 0.39894; graba = 0.5985; agua = 0.171;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco100( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 0.25 * 0.06242795645; arena = 0.7125; graba = 0.76; agua = 0.285;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco150(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 0.3 * 0.06242795645; arena = 0.627; graba = 0.742; agua = 0.285;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaApasco200(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 0.35 * 0.06242795645; arena = 0.532; graba = 0.798; agua = 0.266;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco250( double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO APASCO";
        cemento = 0.4 * 0.06242795645; arena = 0.532; graba = 0.76; agua = 0.266;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco300( double volumen,double elementos){
        //Valores constantes para 300
        tipo="CEMENTO APASCO";
        cemento = 0.45 * 0.06242795645; arena = 0.4275; graba = 0.7695; agua = 0.171;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma100(double volumen,double elementos){
        //Valores constantes para 100
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.25 * 0.06242795645; arena = 0.6175; graba = 0.7125; agua = 0.2375;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma150( double volumen, double elementos){
        //Valores constantes para 150
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.3 * 0.06242795645; arena = 0.627; graba = 0.741; agua = 0.228;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma200( double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.35 * 0.06242795645; arena = 0.5985; graba = 0.7315; agua = 0.23275;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma250(double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.4 * 0.06242795645; arena = 0.532; graba = 0.76; agua = 0.228;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma300( double volumen, double elementos){
        //Valores constantes para 300
        tipo="CEMENTO MOCTEZUMA";
        cemento = 0.45 * 0.06242795645; arena = 0.4275; graba = 0.684; agua = 0.21375;

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
