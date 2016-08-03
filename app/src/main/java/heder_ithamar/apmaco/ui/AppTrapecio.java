package heder_ithamar.apmaco.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import heder_ithamar.apmaco.R;
import heder_ithamar.apmaco.datos.DataBaseManager;
import heder_ithamar.apmaco.operaciones.OperacionesConDesperdicio;
import heder_ithamar.apmaco.operaciones.OperacionesIngConDesperdicio;
import heder_ithamar.apmaco.operaciones.OperacionesIngSinDesperdicio;
import heder_ithamar.apmaco.operaciones.OperacionesSinDesperdicio;
import heder_ithamar.apmaco.uiContenidoMinimo.AppParalelepipedoCM;
import heder_ithamar.apmaco.uiContenidoMinimo.AppTrapecioCM;
import heder_ithamar.apmaco.util.TextChangedListener;

/**
 * Actividad para la calcular la forma Trapecio.
 */

public class AppTrapecio extends AppCompatActivity {
    private PieChart pieChart;
    private String[] Resistencias = {"100","150","200","250","300"};
    private Switch mySwitch, mySwitchSist, mySwitchNormal;
    private Spinner sp_resistencia;
    private EditText txtLargo, txtAncho, txtAlto, txtElementos,txtDescripcion,txtMayor;
    private TextInputLayout input_cmpLargo,input_cmpAncho, input_cmpAlto, input_cmpElementos, input_cmpMayor;
    private Button btnCalcular, btnGuardarVolumen;
    private TextView resultado,switchStatus, switchStatusSist,statusRes, switchstatusNormal;
    private DataBaseManager manager;
    private ArrayAdapter<String> adaptador;
    //Variables
    private Double basemayor, basemenor, longitud, altura, volumen, elem,
            v_elemVolumen, v_totalCem, v_totalAre, v_totalGrab, v_totalAgu;
    private String res, tipo,estructura, forma, descrip, selec, ladoa, ladob, ladoh, Blado, nulo,
            alerta1, aviso, cemVolumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_trapecio);
        //Inicia los componentes
        inicializarComponentesIU();

        //inicia
        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String name = i.getStringExtra(getString(R.string.title_activity_app_trapecio));
        int idDrawable = R.drawable.trapecio;

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle(name); // Cambiar título

        loadImageParallax(idDrawable);// Cargar Imagen

        aviso = String.format(getString(R.string.string_aviso), getString(R.string.title_activity_app_trapecio));

        // Setear escucha al FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(AppTrapecio.this);

                        adb.setTitle(R.string.msj_contenidominimo);
                        adb.setMessage(getString(R.string.msj_calculacm));
                        adb.setNegativeButton(R.string.msj_cancelar, null);
                        adb.setPositiveButton(R.string.msj_ok, new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                iniciaCM();
                            }
                        });
                        adb.show();

                        showSnackBar(aviso);
                    }
                }
        );
        //Crea la base de datos
        manager = new DataBaseManager(this);

        //set the switch to ON
        mySwitch.setChecked(true);
        mySwitchSist.setChecked(true);
        mySwitchNormal.setChecked(true);

        //Elije donde va a almacenar en el cual se va a trabajar
        mySwitchNormal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //si se trabaja con Sistema Internacional
                if (isChecked) {
                    switchstatusNormal.setText(R.string.txtvolumen);
                }
                //Si se trabaja con sistema ingles
                else {
                    switchstatusNormal.setText(R.string.txtvolumendesc);
                }
            }
        });

        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    switchStatus.setText(R.string.txt_condesp);
                }else{
                    switchStatus.setText(R.string.txt_sindesp);
                }

            }
        });

        //Elejir el sistema en el cual se va a trabajar
        mySwitchSist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                //si se trabaja con Sistema Internacional
                if(isChecked){
                    switchStatusSist.setText(R.string.txt_sistemainter);
                    statusRes.setText(R.string.txt_resist);
                }
                //Si se trabaja con sistema ingles
                else{
                    switchStatusSist.setText(R.string.txt_sistemaingle);
                    statusRes.setText(R.string.txt_resist_ing);
                }
            }
        });

        //Llama a metodos
        RegistraAdaptadores();
        CargaRessistencia();
    }

    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    private void iniciaCM(){
        //Crear un nuevo intent
        Intent intent = new Intent(this,AppTrapecioCM.class);
        //Iniciar actividad
        startActivity(intent);
    }


    /**
     * Se carga una imagen aleatoria para el detalle
     */
    private void loadImageParallax(int id) {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        // Usando Glide para la carga asíncrona
        Glide.with(this)
                .load(id)
                .centerCrop()
                .into(image);
    }

    /**
     * Proyecta una {@link Snackbar} con el string usado
     *
     * @param msg Mensaje
     */
    private void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG)
                .show();
    }

    private void inicializarComponentesIU() {
        pieChart = (PieChart) findViewById(R.id.pieChart);
        statusRes = (TextView) findViewById(R.id.status_res);
        sp_resistencia = (Spinner) findViewById(R.id.spn_Res);
        mySwitch = (Switch) findViewById(R.id.mySwitch);
        switchStatus = (TextView) findViewById(R.id.switchStatus);
        mySwitchSist = (Switch) findViewById(R.id.mySwitch_Sist);
        switchStatusSist = (TextView) findViewById(R.id.switchStatusSist);
        switchstatusNormal = (TextView) findViewById(R.id.switchStatusNomal);
        mySwitchNormal = (Switch) findViewById(R.id.mySwitchNomal);
        resultado = (TextView)findViewById(R.id.txtView_Resultado);

        input_cmpLargo = (TextInputLayout) findViewById(R.id.input_cmpLargo);
        input_cmpAncho = (TextInputLayout) findViewById(R.id.input_cmpAncho);
        input_cmpAlto = (TextInputLayout) findViewById(R.id.input_cmpAlto);
        input_cmpMayor = (TextInputLayout) findViewById(R.id.input_cmpMayor);
        input_cmpElementos = (TextInputLayout) findViewById(R.id.input_cmpElementos);

        txtLargo = (EditText)findViewById(R.id.cmpLargo);
        txtAncho = (EditText)findViewById(R.id.cmpAncho);
        txtAlto = (EditText)findViewById(R.id.cmpAlto);
        txtMayor = (EditText) findViewById(R.id.cmpMayor);
        txtElementos = (EditText)findViewById(R.id.cmpElementos);
        txtDescripcion = (EditText)findViewById(R.id.cmpDescripcion);
        btnGuardarVolumen = (Button) findViewById(R.id.btnVolumen);

        //Activar el boton  calcular
        txtAlto.addTextChangedListener(new TextChangedListener(){
            @Override
            public void onTextChanged(CharSequence seq, int i, int i2, int i3) {
                btnCalcular = (Button) findViewById(R.id.btnCalcular);
                btnCalcular.setEnabled(!seq.toString().trim().isEmpty());

            }
        });
    }

    public void RegistraAdaptadores(){
        sp_resistencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // inicia componentes
                //Toast.makeText(getApplicationContext(),Resistencias[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Aun no ha llenado nada...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CargaRessistencia(){
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Resistencias);
        sp_resistencia.setAdapter(adaptador);
    }

    public void imprimeVolumen(String res){
        resultado.setVisibility(View.VISIBLE);
        resultado.setText(res);
        btnGuardarVolumen.setEnabled(true);
        btnGuardarVolumen.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_trapecio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                showSnackBar("Se abren los ajustes");
                //inicia actividada contenido minimo
                iniciaCM();
                return true;
            case R.id.action_result:
                startActivity(new Intent(this, Resultados.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view) {
        if (!validarcmpMayor()) {
            return;
        }

        if (!validarcmpAncho()) {
            return;
        }

        if (!validarcmpLargo()) {
            return;
        }

        if (!validarcmpAlto()) {
            return;
        }

        if (!validarcmpElementos()) {
            return;
        }

        selec = sp_resistencia.getSelectedItem().toString();

        ladoa = txtLargo.getText().toString();
        ladob = txtAncho.getText().toString();
        ladoh = txtAlto.getText().toString();
        Blado = txtMayor.getText().toString();

        basemayor = Double.parseDouble(Blado);
        basemenor = Double.parseDouble(ladob);
        longitud = Double.parseDouble(ladoa);
        altura = Double.parseDouble(ladoh);

        volumen = calculaVolumen(basemayor, basemenor, longitud, altura);

        elem = Double.parseDouble(txtElementos.getText().toString());
        descrip = txtDescripcion.getText().toString();

        alerta1 = this.getString(R.string.alert1);
        cemVolumen = this.getString(R.string.txt_cementoVolumen);
        tipo = this.getString(R.string.btn_edif);
        estructura = this.getString(R.string.btn_trapec);
        nulo = " - ";

        // si es Calculo con sistema Internacional
        if(mySwitchSist.isChecked()){
            Toast.makeText(getApplicationContext(), R.string.alert0, Toast.LENGTH_SHORT).show();
            //Calculo con desperdicio
            if(mySwitch.isChecked()){
                //Aquitodo el codigo con desperdicio
                forma = this.getString(R.string.txt_condesp);

                //Crear el objeto
                OperacionesConDesperdicio op = new OperacionesConDesperdicio();

                switch (selec){
                    //Si el usuario elije la resistencia de 100
                    case "100":
                        //General 100
                        imprimeVolumen(op.Calcula100(volumen, elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 150
                    case "150":
                        //General 150
                        imprimeVolumen(op.Calcula150(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 200
                    case "200":
                        //General
                        imprimeVolumen(op.Calcula200(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 250
                    case "250":
                        //General 250
                        imprimeVolumen(op.Calcula250(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 300
                    case "300":
                        //valores 300
                        imprimeVolumen(op.Calcula300(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    default:
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            //Calculo sin desperdicio
            else {
                //Valores para calcular sin desperdicio
                forma = this.getString(R.string.txt_sindesp);

                //Crear el objeto para sin desperdicio
                OperacionesSinDesperdicio op = new OperacionesSinDesperdicio();

                switch (selec){
                    //Si el usuario elije la resistencia de 100
                    case "100":
                        //General 100
                        imprimeVolumen(op.Calcula100(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 150
                    case "150":
                        //General 150
                        imprimeVolumen(op.Calcula150(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 200
                    case "200":
                        //General
                        imprimeVolumen(op.Calcula200(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 250
                    case "250":
                        //General 250
                        imprimeVolumen(op.Calcula250(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 300
                    case "300":
                        //valores 300
                        imprimeVolumen(op.Calcula300(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    default:
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
        // si es caso con sistema Ingles
        else {
            Toast.makeText(getApplicationContext(), R.string.alert0, Toast.LENGTH_SHORT).show();
            //Calculo con desperdicio
            if(mySwitch.isChecked()){
                //Aquitodo el codigo con desperdicio
                forma = this.getString(R.string.txt_condesp);

                //Crear el objeto
                OperacionesIngConDesperdicio op = new OperacionesIngConDesperdicio();

                switch (selec){
                    //Si el usuario elije la resistencia de 100
                    case "100":
                        //General 100
                        imprimeVolumen(op.Calcula100(volumen, elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 150
                    case "150":
                        //General 150
                        imprimeVolumen(op.Calcula150(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 200
                    case "200":
                        //General
                        imprimeVolumen(op.Calcula200(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 250
                    case "250":
                        //General 250
                        imprimeVolumen(op.Calcula250(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 300
                    case "300":
                        //valores 300
                        imprimeVolumen(op.Calcula300(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    default:
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            //Calculo sin desperdicio
            else {
                //Valores para calcular sin desperdicio
                forma = this.getString(R.string.txt_sindesp);

                //Crear el objeto para sin desperdicio
                OperacionesIngSinDesperdicio op = new OperacionesIngSinDesperdicio();

                switch (selec){
                    //Si el usuario elije la resistencia de 100
                    case "100":
                        //General 100
                        imprimeVolumen(op.Calcula100(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 150
                    case "150":
                        //General 150
                        imprimeVolumen(op.Calcula150(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 200
                    case "200":
                        //General
                        imprimeVolumen(op.Calcula200(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 250
                    case "250":
                        //General 250
                        imprimeVolumen(op.Calcula250(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 300
                    case "300":
                        //valores 300
                        imprimeVolumen(op.Calcula300(volumen,elem));
                        valoresVolumen(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    default:
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
        //Boton para guardar valores de Volumen
        btnGuardarVolumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mySwitchNormal.isChecked()){
                    if(mySwitchSist.isChecked()){
                        manager.insertar(tipo,estructura,forma,ladoa,ladob,ladoh,nulo,Blado,cemVolumen,
                                selec,volumen,elem,v_elemVolumen,v_totalCem,v_totalAre,v_totalGrab,v_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarVolumen.setEnabled(false);
                    }else{
                        manager.insertar2(tipo,estructura,forma,ladoa,ladob,ladoh,nulo,Blado,cemVolumen,
                                selec,volumen,elem,v_elemVolumen,v_totalCem,v_totalAre,v_totalGrab,v_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarVolumen.setEnabled(false);
                    }
                }else{
                    if(mySwitchSist.isChecked()){
                        manager.insertar3(tipo,estructura,forma,ladoa,ladob,ladoh,nulo,Blado,cemVolumen,
                                selec,volumen,elem,v_elemVolumen,v_totalCem,v_totalAre,v_totalGrab,v_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarVolumen.setEnabled(false);
                    }else{
                        manager.insertar4(tipo,estructura,forma,ladoa,ladob,ladoh,nulo,Blado,cemVolumen,
                                selec,volumen,elem,v_elemVolumen,v_totalCem,v_totalAre,v_totalGrab,v_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarVolumen.setEnabled(false);
                    }
                }

            }
        });

        btnCalcular.setEnabled(false);
        limpiarCampos();
    }

    private void valoresVolumen(double elemVolumen, double totalCem, double totalAre, double totalGrab, double totalAgua) {
        v_elemVolumen = elemVolumen;
        v_totalCem = totalCem;
        v_totalAre = totalAre;
        v_totalGrab = totalGrab;
        v_totalAgu = totalAgua;
    }

    private Double calculaVolumen(Double basemayor, Double basemenor, Double longitud, Double altura) {
        return (altura * (basemayor + basemenor) * longitud / 2);
    }

    private void generarGrafica(double cemento, double arena, double grava, double agua) {
        float a = (float) cemento;
        float b = (float) arena;
        float c = (float) grava;
        float d = (float) agua;
        //grafica
       /*definimos algunos atributos*/
        pieChart.setHoleRadius(35f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationEnabled(true);
        pieChart.animateXY(5000, 5000);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        //pieChart.setUsePercentValues(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setDescription("");
        pieChart.setTransparentCircleRadius(45f);


		/*creamos una lista para los valores Y*/
        ArrayList<Entry> valsY = new ArrayList<Entry>();
        valsY.add(new Entry(a,0));
        valsY.add(new Entry(b,1));
        valsY.add(new Entry(c,2));
        valsY.add(new Entry(d,3));

 		/*creamos una lista para los valores X*/
        ArrayList<String> valsX = new ArrayList<String>();
        valsX.add(getString(R.string.desc_cemento));
        valsX.add(getString(R.string.desc_arena));
        valsX.add(getString(R.string.desc_grava));
        valsX.add(getString(R.string.desc_agua));

 		/*creamos una lista de colores*/
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.md_cemento));
        colors.add(getResources().getColor(R.color.md_arena));
        colors.add(getResources().getColor(R.color.md_grava));
        colors.add(getResources().getColor(R.color.md_agua));

 		/*seteamos los valores de Y y los colores*/
        PieDataSet set1 = new PieDataSet(valsY, "");
        set1.setSliceSpace(3f);
        set1.setSelectionShift(15f);
        set1.setValueTextSize(13f);
        set1.setColors(colors);

		/*seteamos los valores de X*/
        PieData data = new PieData(valsX, set1);
        data.setValueTextSize(15f);
        data.setValueTextColor(R.color.cardview_light_background);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }


    //Metodo para limpiar campos
    private void limpiarCampos() {
        txtMayor.getText().clear();
        txtLargo.getText().clear();
        txtAncho.getText().clear();
        txtAlto.getText().clear();
        txtElementos.setText("1");
        txtDescripcion.getText().clear();

        //Oculta el teclado
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        pieChart.setFocusable(true);
        pieChart.setFocusableInTouchMode(true);///add this line
        pieChart.requestFocus();
        //txtLargo.requestFocus();
    }
    public boolean validarcmpMayor(){
        if (txtMayor.getText().toString().trim().isEmpty()) {
            input_cmpMayor.setError(getString(R.string.cmp_basemayor));
            requestFocus(txtMayor);
            return false;
        } else {
            input_cmpMayor.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validarcmpAncho(){
        if (txtAncho.getText().toString().trim().isEmpty()) {
            input_cmpAncho.setError(getString(R.string.cmp_basemenor));
            requestFocus(txtAncho);
            return false;
        } else {
            input_cmpAncho.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validarcmpLargo(){
        if (txtLargo.getText().toString().trim().isEmpty()) {
            input_cmpLargo.setError(getString(R.string.cmp_longitu));
            requestFocus(txtLargo);
            return false;
        } else {
            input_cmpLargo.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validarcmpAlto(){
        if (txtAlto.getText().toString().trim().isEmpty()) {
            input_cmpAlto.setError(getString(R.string.cmp_elevacion));
            requestFocus(txtAlto);
            return false;
        } else {
            input_cmpAlto.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validarcmpElementos(){
        if (txtElementos.getText().toString().trim().isEmpty()) {
            input_cmpElementos.setError(getString(R.string.cmp_elementos));
            requestFocus(txtElementos);
            return false;
        } else {
            input_cmpElementos.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}