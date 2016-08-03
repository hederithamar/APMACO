package heder_ithamar.apmaco.operaciones;

import java.text.DecimalFormat;

/**
 * Created by Heder_ithamar on 27/08/2015.
 */
public class OperacionesConDesperdicio {

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

        if (totalcem>1000){
            resultado= tipo + " \n\n"
                    + " Volumen Inicial: " + df.format(volumen) + " \n"
                    + " Unidades:            " + df.format(elementos) + " \n"
                    + " Volumen Total:   " + df.format(elemVolumen) + " \n"
                    + " Tons. de Cemento:   " + df.format(totalcem/1000) + " \n"
                    + " Arena m^3:          " + df.format(totalAre) + " \n"
                    + " Grava m^3:          " + df.format(totalGrab) + " \n"
                    + " Agua m^3:           " + df.format(totalAgu);

        }else{
            resultado= tipo + " \n\n"
                    + " Volumen Inicial: " + df.format(volumen) + " \n"
                    + " Unidades:            " + df.format(elementos) + " \n"
                    + " Volumen Total:   " + df.format(elemVolumen) + " \n"
                    + " Kilos de Cemento:   " + df.format(totalcem) + " \n"
                    + " Arena m^3:          " + df.format(totalAre) + " \n"
                    + " Grava m^3:          " + df.format(totalGrab) + " \n"
                    + " Agua m^3:           " + df.format(totalAgu);

        }

        return (resultado);
    }

    public String Calcula100(double volumen, double elementos){
        //Valores constantes para 100
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.273; arena = 0.542; graba = 0.656; agua = 0.271;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula150( double volumen,double elementos){
        //Valores para 150
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.326; arena = 0.536; graba = 0.65; agua = 0.263;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula200( double volumen, double elementos){
        //Valores para 200
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.368; arena = 0.531; graba = 0.643; agua = 0.252;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula250(double volumen,double elementos){
        //Valores para 250
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.412; arena = 0.535; graba = 0.637; agua = 0.243;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo, cemento, arena, graba, agua, volumen, elementos);

        //regresa el valor final
        return (resultado);
    }

    public String Calcula300( double volumen, double elementos){
        //Valores para 300
        tipo="VOLUMEN DE CONCRETO";
        cemento = 0.442; arena = 0.54; graba = 0.654; agua = 0.245;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca100( double volumen, double elementos){
        //Valores constantes para 100
        tipo="CEMENTO TOLTECA";
        cemento = 231.91; arena = 0.59505; graba = 0.64083; agua = 0.24063;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca150(double volumen,double elementos){
        //Valores constantes para 150
        tipo="CEMENTO TOLTECA";
        cemento = 283.10; arena = 0.59505; graba = 0.64083; agua = 0.24063;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca200( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 336.56; arena = 0.53144; graba = 0.66430; agua = 0.23282;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaTolteca250( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 413.40; arena = 0.48957; graba = 0.65276; agua = 0.25413;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaTolteca300( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO TOLTECA";
        cemento = 520.14; arena = 0.47903; graba = 0.71865; agua = 0.23987;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco100( double volumen,double elementos){
        //Valores constantes para 100
        tipo="CEMENTO APASCO";
        cemento = 200.80; arena = 0.59449; graba = 0.63413; agua = 0.27780;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco150(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 253.07; arena = 0.54947; graba = 0.64937; agua = 0.29177;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaApasco200(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO APASCO";
        cemento = 300.50; arena = 0.47450; graba = 0.71175; agua = 0.27716;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco250( double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO APASCO";
        cemento = 348.70; arena = 0.48179; graba = 0.68827; agua = 0.28142;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaApasco300( double volumen,double elementos){
        //Valores constantes para 300
        tipo="CEMENTO APASCO";
        cemento = 520.14; arena = 0.51332; graba = 0.92398; agua = 0.23987;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma100(double volumen,double elementos){
        //Valores constantes para 100
        tipo="CEMENTO MOCTEZUMA";
        cemento = 222.50; arena = 0.57093; graba = 0.65876; agua = 0.25653;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaMoctezuma150( double volumen, double elementos){
        //Valores constantes para 150
        tipo="CEMENTO MOCTEZUMA";
        cemento = 260.98; arena = 0.56664; graba = 0.66966; agua = 0.24071;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma200( double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 305.97; arena = 0.54352; graba = 0.66430; agua = 0.24693;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma250(double volumen, double elementos){
        //Valores constantes para 200
        tipo="CEMENTO MOCTEZUMA";
        cemento = 355.97; arena = 0.49182; graba = 0.70260; agua = 0.24624;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaMoctezuma300( double volumen, double elementos){
        //Valores constantes para 300
        tipo="CEMENTO MOCTEZUMA";
        cemento = 471.22; arena = 0.46504; graba = 0.74407; agua = 0.27164;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    //Cemento cruz azul
    public String CalculaAzul100( double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 225.55; arena = 0.55649; graba = 0.66779; agua = 0.26004;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaAzul150(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 253.07; arena = 0.54947; graba = 0.67434; agua = 0.26259;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }
    public String CalculaAzul200(double volumen,double elementos){
        //Valores constantes para 200
        tipo="CEMENTO CRUZ AZUL";
        cemento = 317.51; arena = 0.53270; graba = 0.65804; agua = 0.25624;

        //calcula el resultado de la operacion
        resultado = calculaOperacion(tipo,cemento,arena,graba,agua,volumen,elementos);

        //regresa el valor final
        return (resultado);
    }

    public String CalculaAzul250( double volumen, double elementos){
        //Valores constantes para 250
        tipo="CEMENTO CRUZ AZUL";
        cemento = 371.44; arena = 0.51321; graba = 0.65984; agua = 0.25695;

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
