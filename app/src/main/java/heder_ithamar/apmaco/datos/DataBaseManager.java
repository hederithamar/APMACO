package heder_ithamar.apmaco.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Heder_ithamar on 03/05/2015.
 */
public class DataBaseManager {
    //VOLUMEN NORMAL SISTEMA INTERNACIONAL
    public static final String TABLE_NAME = "volnorsisint";
    //VOLUMEN NORMAL SISTEMA INGLES
    public static final String TABLE_NAME2 = "volnorsising";
    //VOLUMEN A DESCONTAR SISTEMA INTERNACIONAL
    public static final String TABLE_NAME3 = "voldessisint";
    //VOLUMEN A DESCONTAR SISTEMA INGLES
    public static final String TABLE_NAME4 = "voldessising";
    //CONTENIO MINIMO NORMAL SISTEMA INTERNACIONAL
    public static final String TABLE_NAME5 = "contnorsisint";
    //CONTENIDO MINIMO A DESCONTAR SISTEMA INTERNACIONAL
    public static final String TABLE_NAME6 = "contdessisint";
    //valores para las tablas
    public static final String CN_ID = "_id";
    public static final String CN_TIPOCONSTRUCCION = "tipocontruccion";
    public static final String CN_ESTRUCTURA = "estructura";
    public static final String CN_FORMA = "forma";
    public static final String CN_lADOA = "ladoa";
    public static final String CN_lADOB = "ladob";
    public static final String CN_lADOH = "ladoh";
    public static final String CN_AlADO = "alado";
    public static final String CN_BlADO = "blado";
    public static final String CN_TIPOCEMENTO = "tipocemento";
    public static final String CN_RESISTENCIA = "resistencia";
    public static final String CN_VOLUMEN = "volumen";
    public static final String CN_ELEMENTO = "elemento";
    public static final String CN_VOLUMENELEMENTO = "volumenelemento";
    public static final String CN_CEMENTO = "cemento";
    public static final String CN_ARENA = "arena";
    public static final String CN_GRABA = "graba";
    public static final String CN_AGUA = "agua";
    public static final String CN_DESCRIPCION = "descripcion";
    //Se agregan mas para contenido minimo
    public static final String CN_GRAVAARENA = "gravaarena";
    public static final String CN_DENSIDADG = "densidadg";
    public static final String CN_DENSIDADA = "densidada";
    public static final String CN_DENSIDADC = "densidadc";
    public static final String CN_MAXAIRE = "maxaire";
    public static final String CN_AIRE = "aire";

    private DbHelper helper;
    private SQLiteDatabase db;
    private String totalCemento="No hay Datos", totalArena="No hay Datos", totalGraba="No hay Datos",
            totalAgua="No hay Datos",totalAire="No hay Datos";

    //Constructor
    public DataBaseManager(Context context) {
        //base de datos
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    //Tabla para guardar elementos del sistema Internacional
    public static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_TIPOCONSTRUCCION + " text not null,"
            + CN_ESTRUCTURA + " text,"
            + CN_FORMA + " text,"
            + CN_lADOA + " text,"
            + CN_lADOB + " text,"
            + CN_lADOH + " text,"
            + CN_AlADO + " text,"
            + CN_BlADO + " text,"
            + CN_TIPOCEMENTO + " text,"
            + CN_RESISTENCIA + " text,"
            + CN_VOLUMEN + " real,"
            + CN_ELEMENTO + " real,"
            + CN_VOLUMENELEMENTO + " real,"
            + CN_CEMENTO + " real,"
            + CN_ARENA + " real,"
            + CN_GRABA + " real,"
            + CN_AGUA + " real,"
            + CN_DESCRIPCION + " text);";

    //Tabla para guardar elementos del sistema Ingles
    public static final String CREATE_TABLE2 = "create table "+TABLE_NAME2+" ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_TIPOCONSTRUCCION + " text not null,"
            + CN_ESTRUCTURA + " text,"
            + CN_FORMA + " text,"
            + CN_lADOA + " text,"
            + CN_lADOB + " text,"
            + CN_lADOH + " text,"
            + CN_AlADO + " text,"
            + CN_BlADO + " text,"
            + CN_TIPOCEMENTO + " text,"
            + CN_RESISTENCIA + " text,"
            + CN_VOLUMEN + " real,"
            + CN_ELEMENTO + " real,"
            + CN_VOLUMENELEMENTO + " real,"
            + CN_CEMENTO + " real,"
            + CN_ARENA + " real,"
            + CN_GRABA + " real,"
            + CN_AGUA + " real,"
            + CN_DESCRIPCION + " text);";

    //Tabla para guardar elementos a descontar del sistema Internacional
    public static final String CREATE_TABLE3 = "create table "+TABLE_NAME3+" ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_TIPOCONSTRUCCION + " text not null,"
            + CN_ESTRUCTURA + " text,"
            + CN_FORMA + " text,"
            + CN_lADOA + " text,"
            + CN_lADOB + " text,"
            + CN_lADOH + " text,"
            + CN_AlADO + " text,"
            + CN_BlADO + " text,"
            + CN_TIPOCEMENTO + " text,"
            + CN_RESISTENCIA + " text,"
            + CN_VOLUMEN + " real,"
            + CN_ELEMENTO + " real,"
            + CN_VOLUMENELEMENTO + " real,"
            + CN_CEMENTO + " real,"
            + CN_ARENA + " real,"
            + CN_GRABA + " real,"
            + CN_AGUA + " real,"
            + CN_DESCRIPCION + " text);";

    //Tabla para guardar elementos a descontar del sistema Ingles
    public static final String CREATE_TABLE4 = "create table "+TABLE_NAME4+" ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_TIPOCONSTRUCCION + " text not null,"
            + CN_ESTRUCTURA + " text,"
            + CN_FORMA + " text,"
            + CN_lADOA + " text,"
            + CN_lADOB + " text,"
            + CN_lADOH + " text,"
            + CN_AlADO + " text,"
            + CN_BlADO + " text,"
            + CN_TIPOCEMENTO + " text,"
            + CN_RESISTENCIA + " text,"
            + CN_VOLUMEN + " real,"
            + CN_ELEMENTO + " real,"
            + CN_VOLUMENELEMENTO + " real,"
            + CN_CEMENTO + " real,"
            + CN_ARENA + " real,"
            + CN_GRABA + " real,"
            + CN_AGUA + " real,"
            + CN_DESCRIPCION + " text);";

    //Tabla para guardar elementos del sistema Internacional contenido minimo
    public static final String CREATE_TABLE5 = "create table "+TABLE_NAME5+" ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_TIPOCONSTRUCCION + " text not null,"
            + CN_ESTRUCTURA + " text,"
            + CN_FORMA + " text,"
            + CN_lADOA + " text,"
            + CN_lADOB + " text,"
            + CN_lADOH + " text,"
            + CN_AlADO + " text,"
            + CN_BlADO + " text,"
            + CN_TIPOCEMENTO + " text,"
            + CN_RESISTENCIA + " text,"
            + CN_VOLUMEN + " real,"
            + CN_ELEMENTO + " real,"
            + CN_VOLUMENELEMENTO + " real,"
            + CN_CEMENTO + " real,"
            + CN_ARENA + " real,"
            + CN_GRABA + " real,"
            + CN_AGUA + " real,"
            + CN_DESCRIPCION + " text,"
            + CN_GRAVAARENA + " real,"
            + CN_DENSIDADG + " real,"
            + CN_DENSIDADA + " real,"
            + CN_DENSIDADC + " real,"
            + CN_MAXAIRE + " real,"
            + CN_AIRE + " real);";

    //Generar las columnas para las primeras 4 tablas
    private ContentValues generarContentValues(String tipoconstruccion, String estructura,
                                               String forma, String ladoa , String ladob,
                                               String ladoh, String alado, String blado,
                                               String tipocemento, String resistencia,
                                               Double volumen, Double elemento,
                                               Double volumenelemento, Double cemento,
                                               Double arena, Double graba, Double agua,
                                               String descripcion){
        ContentValues valores = new ContentValues();
        valores.put(CN_TIPOCONSTRUCCION, tipoconstruccion);
        valores.put(CN_ESTRUCTURA, estructura);
        valores.put(CN_FORMA, forma);
        valores.put(CN_lADOA, ladoa);
        valores.put(CN_lADOB, ladob);
        valores.put(CN_lADOH, ladoh);
        valores.put(CN_AlADO, alado);
        valores.put(CN_BlADO, blado);
        valores.put(CN_TIPOCEMENTO, tipocemento);
        valores.put(CN_RESISTENCIA, resistencia);
        valores.put(CN_VOLUMEN, volumen);
        valores.put(CN_ELEMENTO, elemento);
        valores.put(CN_VOLUMENELEMENTO, volumenelemento);
        valores.put(CN_CEMENTO, cemento);
        valores.put(CN_ARENA, arena);
        valores.put(CN_GRABA, graba);
        valores.put(CN_AGUA, agua);
        valores.put(CN_DESCRIPCION, descripcion);

        return  valores;
    }
    //Generar tablas para contenidos minimo
    private ContentValues generarContentValues2(String tipoconstruccion, String estructura,
                                                String forma, String ladoa , String ladob,
                                                String ladoh, String alado, String blado,
                                                String tipocemento, String resistencia,
                                                Double volumen, Double elemento,
                                                Double volumenelemento, Double cemento,
                                                Double arena, Double graba, Double agua,
                                                String descripcion,Double gravaarena,
                                                Double densidadg, Double densidada,
                                                Double densidadc, Double maxaire, Double aire){
        ContentValues valores = new ContentValues();
        valores.put(CN_TIPOCONSTRUCCION, tipoconstruccion);
        valores.put(CN_ESTRUCTURA, estructura);
        valores.put(CN_FORMA, forma);
        valores.put(CN_lADOA, ladoa);
        valores.put(CN_lADOB, ladob);
        valores.put(CN_lADOH, ladoh);
        valores.put(CN_AlADO, alado);
        valores.put(CN_BlADO, blado);
        valores.put(CN_TIPOCEMENTO, tipocemento);
        valores.put(CN_RESISTENCIA, resistencia);
        valores.put(CN_VOLUMEN, volumen);
        valores.put(CN_ELEMENTO, elemento);
        valores.put(CN_VOLUMENELEMENTO, volumenelemento);
        valores.put(CN_CEMENTO, cemento);
        valores.put(CN_ARENA, arena);
        valores.put(CN_GRABA, graba);
        valores.put(CN_AGUA, agua);
        valores.put(CN_DESCRIPCION, descripcion);
        valores.put(CN_GRAVAARENA, gravaarena);
        valores.put(CN_DENSIDADG, densidadg);
        valores.put(CN_DENSIDADA, densidada);
        valores.put(CN_DENSIDADC, densidadc);
        valores.put(CN_MAXAIRE, maxaire);
        valores.put(CN_AIRE, aire);

        return  valores;
    }

    //metodo para insertar datos en la tabla Sistema Internacional "calculo"
    public void insertar(String tipoconstruccion, String estructura,String forma, String ladoa , String ladob, String ladoh, String alado, String blado, String tipocemento, String resistencia, Double volumen, Double elemento, Double volumenelemento, Double cemento, Double arena, Double graba, Double agua, String descripcion){

        db.insert(TABLE_NAME,null,generarContentValues(tipoconstruccion, estructura, forma, ladoa, ladob, ladoh, alado, blado, tipocemento, resistencia, volumen, elemento, volumenelemento, cemento, arena, graba, agua, descripcion));
    }

    //metodo para insetar datos en la tabla Sistema Ingles "ingles"
    public void insertar2(String tipoconstruccion, String estructura,String forma, String ladoa , String ladob, String ladoh, String alado, String blado, String tipocemento, String resistencia, Double volumen, Double elemento, Double volumenelemento, Double cemento, Double arena, Double graba, Double agua, String descripcion){

        db.insert(TABLE_NAME2,null,generarContentValues(tipoconstruccion, estructura, forma, ladoa, ladob, ladoh, alado, blado, tipocemento, resistencia, volumen, elemento, volumenelemento, cemento, arena, graba, agua, descripcion));
    }

    //metodo para insertar datos para descontar en la tabla Sistema Internacional "calculo"
    public void insertar3(String tipoconstruccion, String estructura,String forma, String ladoa , String ladob, String ladoh, String alado, String blado, String tipocemento, String resistencia, Double volumen, Double elemento, Double volumenelemento, Double cemento, Double arena, Double graba, Double agua, String descripcion){

        db.insert(TABLE_NAME3,null,generarContentValues(tipoconstruccion, estructura, forma, ladoa, ladob, ladoh, alado, blado, tipocemento, resistencia, volumen, elemento, volumenelemento, cemento, arena, graba, agua, descripcion));
    }

    //metodo para insetar datos para descontar en la tabla Sistema Ingles "ingles"
    public void insertar4(String tipoconstruccion, String estructura,String forma, String ladoa , String ladob, String ladoh, String alado, String blado, String tipocemento, String resistencia, Double volumen, Double elemento, Double volumenelemento, Double cemento, Double arena, Double graba, Double agua, String descripcion){

        db.insert(TABLE_NAME4,null,generarContentValues(tipoconstruccion, estructura, forma, ladoa, ladob, ladoh, alado, blado, tipocemento, resistencia, volumen, elemento, volumenelemento, cemento, arena, graba, agua, descripcion));
    }

    //metodo para insertar datos en la tabla Sistema Internacional "calculo" contenido minimo
    public void insertar5(String tipoconstruccion, String estructura,String forma, String ladoa , String ladob, String ladoh, String alado, String blado, String tipocemento, String resistencia, Double volumen, Double elemento, Double volumenelemento, Double cemento, Double arena, Double graba, Double agua, String descripcion, Double arenagrava, Double densidadg, Double densidada, Double densidadc, Double maxaire, Double aire){

        db.insert(TABLE_NAME5,null,generarContentValues2(tipoconstruccion, estructura, forma, ladoa, ladob, ladoh, alado, blado, tipocemento, resistencia, volumen, elemento, volumenelemento, cemento, arena, graba, agua, descripcion, arenagrava, densidadg, densidada, densidadc, maxaire, aire));
    }

    public void insertarexe(String tipoconstruccion, String estructura,String forma, String tipocemento, String resistencia, Double volumen, Double cemento, Double arena, Double graba, Double agua){
        //insert into calculo; insert into calculo(telefono) values (null)

        db.execSQL("insert into " + TABLE_NAME + " values (null,'" + tipoconstruccion + "','" + estructura + "','" + forma + "','" + tipocemento + "'," + resistencia + "," + volumen + "," + cemento + "," + arena + "," + graba + "," + agua + ")");
    }
    //obtiene un resultado de table name 1
    public Cursor getCementoCal(){
        return db.rawQuery("SELECT SUM(cemento) from "+TABLE_NAME, null);
    }

    public void eliminadatos(){
        db.execSQL("delete from " + TABLE_NAME + ";");
        db.execSQL("delete from " + TABLE_NAME2 + ";");
        db.execSQL("delete from " + TABLE_NAME3 + ";");
        db.execSQL("delete from " + TABLE_NAME4 + ";");

    }

    public String sumaInternacional(){
        Cursor cemento = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME, null);
        Cursor arena = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME, null);
        Cursor graba = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME, null);
        Cursor agua = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME, null);

        Cursor cemento1 = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME3, null);
        Cursor arena1 = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME3, null);
        Cursor graba1 = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME3, null);
        Cursor agua1 = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME3, null);
        float num1,num2,a;

        if (cemento.moveToFirst() && cemento1.moveToFirst()) {

            num1 = cemento.getFloat(0);
            num2 = cemento1.getFloat(0);
            //a = num1-num2;
            totalCemento = " "+(num1 - num2);
        }
        if (arena.moveToFirst() && arena1.moveToFirst()){
            num1 = arena.getFloat(0);
            num2 = arena1.getFloat(0);
            totalArena = " "+(num1 - num2);
        }
        if (graba.moveToFirst() && graba1.moveToFirst()){
            num1 = graba.getFloat(0);
            num2 = graba1.getFloat(0);
            totalGraba = " "+(num1 - num2);
        }
        if (agua.moveToFirst() && agua1.moveToFirst()){
            num1 = agua.getFloat(0);
            num2 = agua1.getFloat(0);
            totalAgua = " "+(num1 - num2);
        }

        String resultado =" Total Cemento Tons: " + totalCemento + " \n"
                + " Total Arena m^3:        " + " " + totalArena + " \n"
                + " Total Grava m^3:        " + " " + totalGraba + " \n"
                + " Total Agua m^3:         " + " " + totalAgua;
        return resultado;
    }

    public String sumaIngles(){
        Cursor cemento = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME2, null);
        Cursor arena = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME2, null);
        Cursor graba = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME2, null);
        Cursor agua = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME2, null);

        Cursor cemento1 = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME4, null);
        Cursor arena1 = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME4, null);
        Cursor graba1 = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME4, null);
        Cursor agua1 = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME4, null);
        float num1,num2,a;

        if (cemento.moveToFirst() && cemento1.moveToFirst()) {

            num1 = cemento.getFloat(0);
            num2 = cemento1.getFloat(0);
            //a = num1-num2;
            totalCemento = " "+(num1 - num2);
        }
        if (arena.moveToFirst() && arena1.moveToFirst()){
            num1 = arena.getFloat(0);
            num2 = arena1.getFloat(0);
            totalArena = " "+(num1 - num2);
        }
        if (graba.moveToFirst() && graba1.moveToFirst()){
            num1 = graba.getFloat(0);
            num2 = graba1.getFloat(0);
            totalGraba = " "+(num1 - num2);
        }
        if (agua.moveToFirst() && agua1.moveToFirst()){
            num1 = agua.getFloat(0);
            num2 = agua1.getFloat(0);
            totalAgua = " "+(num1 - num2);
        }


        String resultado =" Total Cemento klb:  " + totalCemento + " \n"
                + " Total Arena ft^3:      " + " " + totalArena + " \n"
                + " Total Grava ft^3:      " + " " + totalGraba + " \n"
                + " Total Agua ft^3:       " + " " + totalAgua;
        return resultado;
    }

    public String sumaCemento(){
        Cursor cemento = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME, null);
        Cursor arena = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME, null);
        Cursor graba = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME, null);
        Cursor agua = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME, null);

        if (cemento.moveToFirst()) {
            totalCemento = cemento.getString(0);
        }
        if (arena.moveToFirst()){
            totalArena = arena.getString(0);
        }
        if (graba.moveToFirst()){
            totalGraba = graba.getString(0);
        }
        if (agua.moveToFirst()){
            totalAgua = agua.getString(0);
        }

        String resultado =" Total de Cemento Tons: " + totalCemento + " \n"
                + " Total de Arena m^3:        " + " " + totalArena + " \n"
                + " Total de Grava m^3:        " + " " + totalGraba + " \n"
                + " Total de Agua m^3:         " + " " + totalAgua;
        return resultado;
    }

    public String sumaCemento2(){
        Cursor cemento = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME2, null);
        Cursor arena = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME2, null);
        Cursor graba = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME2, null);
        Cursor agua = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME2, null);

        if (cemento.moveToFirst()) {
            totalCemento = cemento.getString(0);
        }
        if (arena.moveToFirst()){
            totalArena = arena.getString(0);
        }
        if (graba.moveToFirst()){
            totalGraba = graba.getString(0);
        }
        if (agua.moveToFirst()){
            totalAgua = agua.getString(0);
        }

        String resultado =" Total de Cemento klb: " + totalCemento + " \n"
                + " Total de Arena ft^3:      " + " " + totalArena + " \n"
                + " Total de Grava ft^3:      " + " " + totalGraba + " \n"
                + " Total de Agua ft^3:       " + " " + totalAgua;
        return resultado;
    }

    public String sumaCemento3(){
        Cursor cemento = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME3, null);
        Cursor arena = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME3, null);
        Cursor graba = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME3, null);
        Cursor agua = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME3, null);

        if (cemento.moveToFirst()) {
            totalCemento = cemento.getString(0);
        }
        if (arena.moveToFirst()){
            totalArena = arena.getString(0);
        }
        if (graba.moveToFirst()){
            totalGraba = graba.getString(0);
        }
        if (agua.moveToFirst()){
            totalAgua = agua.getString(0);
        }

        String resultado =" Total de Cemento Tons: " + totalCemento + " \n"
                + " Total de Arena m^3:        " + " " + totalArena + " \n"
                + " Total de Grava m^3:        " + " " + totalGraba + " \n"
                + " Total de Agua m^3:         " + " " + totalAgua;
        return resultado;
    }

    public String sumaCemento4(){
        Cursor cemento = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME4, null);
        Cursor arena = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME4, null);
        Cursor graba = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME4, null);
        Cursor agua = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME4, null);

        if (cemento.moveToFirst()) {
            totalCemento = cemento.getString(0);
        }
        if (arena.moveToFirst()){
            totalArena = arena.getString(0);
        }
        if (graba.moveToFirst()){
            totalGraba = graba.getString(0);
        }
        if (agua.moveToFirst()){
            totalAgua = agua.getString(0);
        }

        String resultado =" Total de Cemento klb: " + totalCemento + " \n"
                + " Total de Arena ft^3:      " + " " + totalArena + " \n"
                + " Total de Grava ft^3:      " + " " + totalGraba + " \n"
                + " Total de Agua ft^3:       " + " " + totalAgua;
        return resultado;
    }

    public String sumaCemento5(){
        Cursor cemento = db.rawQuery("SELECT SUM(cemento) from " + TABLE_NAME5, null);
        Cursor arena = db.rawQuery("SELECT SUM(arena) from " + TABLE_NAME5, null);
        Cursor graba = db.rawQuery("SELECT SUM(graba) from " + TABLE_NAME5, null);
        Cursor agua = db.rawQuery("SELECT SUM(agua) from " + TABLE_NAME5, null);
        Cursor aire = db.rawQuery("SELECT SUM(aire) from " + TABLE_NAME5, null);

        if (cemento.moveToFirst()) {
            totalCemento = cemento.getString(0);
        }
        if (arena.moveToFirst()){
            totalArena = arena.getString(0);
        }
        if (graba.moveToFirst()){
            totalGraba = graba.getString(0);
        }
        if (agua.moveToFirst()){
            totalAgua = agua.getString(0);
        }
        if (aire.moveToFirst()){
            totalAire = aire.getString(0);
        }

        String resultado =" Total de Cemento klb: " + totalCemento + " \n"
                + " Total de Arena ft^3:      " + " " + totalArena + " \n"
                + " Total de Grava ft^3:      " + " " + totalGraba + " \n"
                + " Total de Agua ft^3:       " + " " + totalAgua + " \n"
                + " Total de Aire ft^3:       " + " " + totalAire;
        return resultado;
    }

    //Elimina un elemento de tabla calculo
    public void eliminar(String tipo){
        //bd.delete (Tabla, Clausulas Where, Argumentos Where)
        db.delete(TABLE_NAME, CN_ID +"=?",new String[]{tipo});

    }

    //Elimina un elemento de la tabla ingles
    public void eliminar2(String tipo){
        //bd.delete (Tabla, Clausulas Where, Argumentos Where)
        db.delete(TABLE_NAME2, CN_ID +"=?",new String[]{tipo});

    }

    //Elimina un elemento para descontar de tabla calculo
    public void eliminar3(String tipo){
        //bd.delete (Tabla, Clausulas Where, Argumentos Where)
        db.delete(TABLE_NAME3, CN_ID +"=?",new String[]{tipo});

    }

    //Elimina un elemento para descontar de la tabla ingles
    public void eliminar4(String tipo){
        //bd.delete (Tabla, Clausulas Where, Argumentos Where)
        db.delete(TABLE_NAME4, CN_ID +"=?",new String[]{tipo});

    }

    //Elimina un elemento para descontar de la tabla ingles
    public void eliminar5(String tipo){
        //bd.delete (Tabla, Clausulas Where, Argumentos Where)
        db.delete(TABLE_NAME5, CN_ID +"=?",new String[]{tipo});

    }

    public void eliminarMultiple(String tipo1,String tipo2){
        db.delete(TABLE_NAME, CN_TIPOCONSTRUCCION +"IN (?,?)",new String[]{tipo1,tipo2});
    }

    //Carga los datos de la tabla calculo
    public Cursor cargarCursorContent(){
        String[] columnas = new String[]{CN_ID,CN_TIPOCONSTRUCCION,CN_ESTRUCTURA,CN_FORMA,CN_lADOA,CN_lADOB,CN_lADOH,CN_AlADO,CN_BlADO,CN_TIPOCEMENTO,CN_RESISTENCIA,CN_VOLUMEN,CN_ELEMENTO,CN_VOLUMENELEMENTO,CN_CEMENTO,CN_ARENA,CN_GRABA,CN_AGUA,CN_DESCRIPCION};
        // query (String table, String[] columns, String selecion,String [] selectionArgs, String groupBy, Strings having, String orderBy)
        return db.query(TABLE_NAME,columnas,null,null,null,null,"_id DESC");
    }

    public Cursor cargarCursorContentIngles(){
        String[] columnas = new String[]{CN_ID,CN_TIPOCONSTRUCCION,CN_ESTRUCTURA,CN_FORMA,CN_lADOA,CN_lADOB,CN_lADOH,CN_AlADO,CN_BlADO,CN_TIPOCEMENTO,CN_RESISTENCIA,CN_VOLUMEN,CN_ELEMENTO,CN_VOLUMENELEMENTO,CN_CEMENTO,CN_ARENA,CN_GRABA,CN_AGUA,CN_DESCRIPCION};
        // query (String table, String[] columns, String selecion,String [] selectionArgs, String groupBy, Strings having, String orderBy)
        return db.query(TABLE_NAME2,columnas,null,null,null,null,"_id DESC");
    }
    //Carga los datos de la tabla calculo
    public Cursor cargarCursorContent3(){
        String[] columnas = new String[]{CN_ID,CN_TIPOCONSTRUCCION,CN_ESTRUCTURA,CN_FORMA,CN_lADOA,CN_lADOB,CN_lADOH,CN_AlADO,CN_BlADO,CN_TIPOCEMENTO,CN_RESISTENCIA,CN_VOLUMEN,CN_ELEMENTO,CN_VOLUMENELEMENTO,CN_CEMENTO,CN_ARENA,CN_GRABA,CN_AGUA,CN_DESCRIPCION};
        // query (String table, String[] columns, String selecion,String [] selectionArgs, String groupBy, Strings having, String orderBy)
        return db.query(TABLE_NAME3,columnas,null,null,null,null,"_id DESC");
    }

    public Cursor cargarCursorContent4(){
        String[] columnas = new String[]{CN_ID,CN_TIPOCONSTRUCCION,CN_ESTRUCTURA,CN_FORMA,CN_lADOA,CN_lADOB,CN_lADOH,CN_AlADO,CN_BlADO,CN_TIPOCEMENTO,CN_RESISTENCIA,CN_VOLUMEN,CN_ELEMENTO,CN_VOLUMENELEMENTO,CN_CEMENTO,CN_ARENA,CN_GRABA,CN_AGUA,CN_DESCRIPCION};
        // query (String table, String[] columns, String selecion,String [] selectionArgs, String groupBy, Strings having, String orderBy)
        return db.query(TABLE_NAME4,columnas,null,null,null,null,"_id DESC");
    }
    public Cursor cargarCursorContent5(){
        String[] columnas = new String[]{CN_ID,CN_TIPOCONSTRUCCION,CN_ESTRUCTURA,CN_FORMA,CN_lADOA,CN_lADOB,CN_lADOH,CN_AlADO,CN_BlADO,CN_TIPOCEMENTO,CN_RESISTENCIA,CN_VOLUMEN,CN_ELEMENTO,CN_VOLUMENELEMENTO,CN_CEMENTO,CN_ARENA,CN_GRABA,CN_AGUA,CN_DESCRIPCION,CN_GRAVAARENA,CN_DENSIDADG,CN_DENSIDADA,CN_DENSIDADC,CN_MAXAIRE,CN_AIRE};
        // query (String table, String[] columns, String selecion,String [] selectionArgs, String groupBy, Strings having, String orderBy)
        return db.query(TABLE_NAME5,columnas,null,null,null,null,"_id DESC");
    }
}
