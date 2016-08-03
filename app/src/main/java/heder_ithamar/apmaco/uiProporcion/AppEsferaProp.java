package heder_ithamar.apmaco.uiProporcion;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
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
import heder_ithamar.apmaco.ui.Resultados;
import heder_ithamar.apmaco.uiContenidoMinimo.AppEsferaCM;
import heder_ithamar.apmaco.util.TextChangedListener;

public class AppEsferaProp extends AppCompatActivity {
    private PieChart pieChart;
    private ImageView img_tolteca, img_apasco, img_moctezuma, img_azul;
    private String[] Resistencias = {"100","150","200","250","300"};
    private Switch mySwitch, mySwitchSist, mySwitchNormal;
    private Spinner sp_resistencia,sp_tipocemento;

    private EditText txtAlto, txtElementos,txtDescripcion;
    private TextInputLayout input_cmpAlto, input_cmpElementos;

    private Button btnCalcular, btnGuardarTolteca,btnGuardarMoctezu,btnGuardarApasco, btnGuardarAzul;
    private TextView resultado, resultadoTolteca, resultadoApasco, resultadoMoctezuma, resultadoAzul,
            switchStatus, switchStatusSist, statusRes,switchstatusNormal;
    private DataBaseManager manager;
    private ArrayAdapter<String> adaptador;
    //Variables
    private Double elem, a_totalCem, a_totalAre, a_totalGrab, a_totalAgu, a_elemVolumen,
            t_elemVolumen, t_totalCem, t_totalAre, t_totalGrab, t_totalAgu,
            m_elemVolumen, m_totalCem, m_totalAre, m_totalGrab, m_totalAgu,
            c_elemVolumen, c_totalCem, c_totalAre, c_totalGrab, c_totalAgu,
            volumen, diametro;
    private String tipo, estructura, forma, descrip, selec, ladoh, nulo, alerta1, cemTolteca,
            cemApasco, cemMoctezuma, cemAzul, aviso, center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_esfera_prop);

        //Inicia los componentes
        inicializarComponentesIU();

        //inicia
        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String name = i.getStringExtra(getString(R.string.title_activity_app_esfera_prop));
        int idDrawable = R.drawable.esfera;

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle(name); // Cambiar título

        loadImageParallax(idDrawable);// Cargar Imagen

        aviso = String.format(getString(R.string.string_aviso), getString(R.string.title_activity_app_esfera_prop));

        // Setear escucha al FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(AppEsferaProp.this);

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

        //attach a listener to check for changes in state elije el tipo de desperdicio
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
        Intent intent = new Intent(this,AppEsferaCM.class);
        //Iniciar actividad
        startActivity(intent);
    }

    /**
     * Se carga una imagen para el detalle
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
        switchStatusSist = (TextView) findViewById(R.id.switchStatusSist);
        mySwitchSist = (Switch) findViewById(R.id.mySwitch_Sist);
        switchstatusNormal = (TextView) findViewById(R.id.switchStatusNomal);
        mySwitchNormal = (Switch) findViewById(R.id.mySwitchNomal);
        img_apasco=(ImageView)findViewById(R.id.imageView_Apasco);
        img_tolteca=(ImageView)findViewById(R.id.imageView_Tolteca);
        img_moctezuma=(ImageView)findViewById(R.id.imageView_Moctezuma);
        img_azul=(ImageView)findViewById(R.id.imageView_Azul);

        resultado = (TextView)findViewById(R.id.txtView_Resultado);
        resultadoTolteca = (TextView)findViewById(R.id.txtView_Tolteca);
        resultadoApasco = (TextView)findViewById(R.id.txtView_Apasco);
        resultadoMoctezuma = (TextView)findViewById(R.id.textView_Moctezuma);
        resultadoAzul = (TextView)findViewById(R.id.textView_Azul);
        input_cmpAlto = (TextInputLayout) findViewById(R.id.input_cmpAlto);
        input_cmpElementos = (TextInputLayout) findViewById(R.id.input_cmpElementos);

        txtAlto = (EditText)findViewById(R.id.cmpAlto);
        txtElementos = (EditText)findViewById(R.id.cmpElementos);
        txtDescripcion = (EditText)findViewById(R.id.cmpDescripcion);

        btnGuardarTolteca =(Button) findViewById(R.id.btnGuardarTolteca);
        btnGuardarMoctezu = (Button)findViewById(R.id.btnGuardarMoctez);
        btnGuardarApasco = (Button) findViewById(R.id.btnGuardarApasco);
        btnGuardarAzul = (Button) findViewById(R.id.btnGuardarAzul);


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
                //Toast.makeText(getApplicationContext(), "Aun no ha llenado nada...", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void CargaRessistencia(){
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Resistencias);
        sp_resistencia.setAdapter(adaptador);
    }

    public void imprimeTolteca(String res){
        img_tolteca.setVisibility(View.VISIBLE);
        resultadoTolteca.setVisibility(View.VISIBLE);
        resultadoTolteca.setText(res);
        btnGuardarTolteca.setEnabled(true);
        btnGuardarTolteca.setVisibility(View.VISIBLE);
    }

    public void imprimeApasco(String res){
        img_apasco.setVisibility(View.VISIBLE);
        resultadoApasco.setVisibility(View.VISIBLE);
        resultadoApasco.setText(res);
        btnGuardarApasco.setEnabled(true);
        btnGuardarApasco.setVisibility(View.VISIBLE);
    }

    public void imprimeMoctezuma(String res){
        img_moctezuma.setVisibility(View.VISIBLE);
        resultadoMoctezuma.setVisibility(View.VISIBLE);
        resultadoMoctezuma.setText(res);
        btnGuardarMoctezu.setEnabled(true);
        btnGuardarMoctezu.setVisibility(View.VISIBLE);
    }

    public void imprimeAzul(String res){
        img_azul.setVisibility(View.VISIBLE);
        resultadoAzul.setVisibility(View.VISIBLE);
        resultadoAzul.setText(res);
        btnGuardarAzul.setEnabled(true);
        btnGuardarAzul.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_esfera, menu);
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
        if (!validarcmpAlto()) {
            return;
        }

        if (!validarcmpElementos()) {
            return;
        }

        selec = sp_resistencia.getSelectedItem().toString();

        ladoh = txtAlto.getText().toString();
        diametro = Double.parseDouble(ladoh);

        volumen = calculaVolumen(diametro);

        elem = Double.parseDouble(txtElementos.getText().toString());

        descrip = txtDescripcion.getText().toString();

        alerta1 = this.getString(R.string.alert1);
        cemTolteca = this.getString(R.string.txt_cementoTolteca);
        cemApasco = this.getString(R.string.txt_cementoApasco);
        cemMoctezuma = this.getString(R.string.txt_cementoMoctezuma);
        cemAzul = this.getString(R.string.txt_cementoAzul);
        tipo = this.getString(R.string.btn_edif);
        estructura = this.getString(R.string.btn_esfera);
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
                FragmentManager fragmentManager = getFragmentManager();
                switch (selec){
                    //Si el usuario elije la resistencia de 100
                    case "100":
                        //Tolteca 100
                        imprimeTolteca(op.CalculaTolteca100( volumen, elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 100
                        imprimeApasco(op.CalculaApasco100(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 100
                        imprimeMoctezuma(op.CalculaMoctezuma100(volumen, elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Azul 100
                        imprimeAzul(op.CalculaAzul100(volumen, elem));
                        valoresAzul(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                    //Si el usuario elije la resistencia de 150
                    case "150":

                        //Tolteca 150
                        imprimeTolteca(op.CalculaTolteca150( volumen, elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 150
                        imprimeApasco(op.CalculaApasco150(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 150
                        imprimeMoctezuma(op.CalculaMoctezuma150(volumen, elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Azul 150
                        imprimeAzul(op.CalculaAzul150(volumen, elem));
                        valoresAzul(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 200
                    case "200":

                        //Tolteca 200
                        imprimeTolteca(op.CalculaTolteca200(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 200
                        imprimeApasco(op.CalculaApasco200(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 200
                        imprimeMoctezuma(op.CalculaMoctezuma200(volumen, elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Azul 200
                        imprimeAzul(op.CalculaAzul200(volumen, elem));
                        valoresAzul(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        break;

                    //Si el usuario elije la resistencia de 250
                    case "250":
                        //Tolteca 250
                        imprimeTolteca(op.CalculaTolteca250(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 250
                        imprimeApasco(op.CalculaApasco250(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 250
                        imprimeMoctezuma(op.CalculaMoctezuma250(volumen, elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Azul 250
                        imprimeAzul(op.CalculaAzul250(volumen, elem));
                        valoresAzul(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 300
                    case "300":
                        //Tolteca 300
                        imprimeTolteca(op.CalculaTolteca300(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 300
                        imprimeApasco(op.CalculaApasco300(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 300
                        imprimeMoctezuma(op.CalculaMoctezuma300(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
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
                        //Tolteaca 100
                        imprimeTolteca(op.CalculaTolteca100(volumen, elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 100
                        imprimeApasco(op.CalculaApasco100(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 100
                        imprimeMoctezuma(op.CalculaMoctezuma100(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 150
                    case "150":
                        //Tolteca 150
                        imprimeTolteca(op.CalculaTolteca150( volumen, elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 150
                        imprimeApasco(op.CalculaApasco150(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 150
                        imprimeMoctezuma(op.CalculaMoctezuma150(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 200
                    case "200":
                        //Tolteca 200
                        imprimeTolteca(op.CalculaTolteca200(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 200
                        imprimeApasco(op.CalculaApasco200(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 200
                        imprimeMoctezuma(op.CalculaMoctezuma200(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 250
                    case "250":
                        //Tolteca 250
                        imprimeTolteca(op.CalculaTolteca250(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 250
                        imprimeApasco(op.CalculaApasco250(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 250
                        imprimeMoctezuma(op.CalculaMoctezuma250(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 300
                    case "300":
                        //Tolteca 300
                        imprimeTolteca(op.CalculaTolteca300(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 300
                        imprimeApasco(op.CalculaApasco300(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 300
                        imprimeMoctezuma(op.CalculaMoctezuma300(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
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
                        //Tolteaca 100
                        imprimeTolteca(op.CalculaTolteca100(volumen, elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 100
                        imprimeApasco(op.CalculaApasco100(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 100
                        imprimeMoctezuma(op.CalculaMoctezuma100(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 150
                    case "150":
                        //Tolteca 150
                        imprimeTolteca(op.CalculaTolteca150( volumen, elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 150
                        imprimeApasco(op.CalculaApasco150(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 150
                        imprimeMoctezuma(op.CalculaMoctezuma150(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 200
                    case "200":
                        //Tolteca 200
                        imprimeTolteca(op.CalculaTolteca200(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 200
                        imprimeApasco(op.CalculaApasco200(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 200
                        imprimeMoctezuma(op.CalculaMoctezuma200(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 250
                    case "250":
                        //Tolteca 250
                        imprimeTolteca(op.CalculaTolteca250(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 250
                        imprimeApasco(op.CalculaApasco250(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 250
                        imprimeMoctezuma(op.CalculaMoctezuma250(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 300
                    case "300":
                        //Tolteca 300
                        imprimeTolteca(op.CalculaTolteca300(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 300
                        imprimeApasco(op.CalculaApasco300(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 300
                        imprimeMoctezuma(op.CalculaMoctezuma300(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
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
                        //Tolteaca 100
                        imprimeTolteca(op.CalculaTolteca100(volumen, elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 100
                        imprimeApasco(op.CalculaApasco100(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 100
                        imprimeMoctezuma(op.CalculaMoctezuma100(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 150
                    case "150":
                        //Tolteca 150
                        imprimeTolteca(op.CalculaTolteca150( volumen, elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 150
                        imprimeApasco(op.CalculaApasco150(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 150
                        imprimeMoctezuma(op.CalculaMoctezuma150(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 200
                    case "200":
                        //Tolteca 200
                        imprimeTolteca(op.CalculaTolteca200(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 200
                        imprimeApasco(op.CalculaApasco200(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 200
                        imprimeMoctezuma(op.CalculaMoctezuma200(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 250
                    case "250":
                        //Tolteca 250
                        imprimeTolteca(op.CalculaTolteca250(volumen,elem));
                        valoresTolteca(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 250
                        imprimeApasco(op.CalculaApasco250(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 250
                        imprimeMoctezuma(op.CalculaMoctezuma250(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    //Si el usuario elije la resistencia de 300
                    case "300":
                        //Tolteca 300
                        imprimeTolteca(op.CalculaTolteca300(volumen,elem));
                        valoresTolteca(op.getElemVolumen(),op.getTotalCem(),op.getTotalAre(),
                                op.getTotalGrab(),op.getTotalAgua());

                        //Genera una grafica
                        generarGrafica(op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Apasco 300
                        imprimeApasco(op.CalculaApasco300(volumen,elem));
                        valoresApasco(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());

                        //Moctezuma 300
                        imprimeMoctezuma(op.CalculaMoctezuma300(volumen,elem));
                        valoresMoctezuma(op.getElemVolumen(), op.getTotalCem(), op.getTotalAre(),
                                op.getTotalGrab(), op.getTotalAgua());
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }

        //Boton para guardar valores de Tolteca
        btnGuardarTolteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mySwitchNormal.isChecked()){
                    if(mySwitchSist.isChecked()){
                        manager.insertar(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemTolteca,selec,
                                volumen,elem,t_elemVolumen,t_totalCem,t_totalAre,t_totalGrab,t_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarTolteca.setEnabled(false);
                    }else{
                        manager.insertar2(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemTolteca,selec,
                                volumen,elem,t_elemVolumen,t_totalCem,t_totalAre,t_totalGrab,t_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarTolteca.setEnabled(false);
                    }
                }else{
                    if(mySwitchSist.isChecked()){
                        manager.insertar3(tipo, estructura, forma, nulo, nulo, nulo, ladoh, nulo, cemTolteca, selec,
                                volumen, elem, t_elemVolumen, t_totalCem, t_totalAre, t_totalGrab, t_totalAgu, descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarTolteca.setEnabled(false);
                    }else{
                        manager.insertar4(tipo, estructura, forma, nulo, nulo, nulo, ladoh, nulo, cemTolteca, selec,
                                volumen, elem, t_elemVolumen, t_totalCem, t_totalAre, t_totalGrab, t_totalAgu, descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarTolteca.setEnabled(false);
                    }
                }

            }
        });

        //Boton para guardar valores para Apasco
        btnGuardarApasco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mySwitchNormal.isChecked()){
                    if(mySwitchSist.isChecked()){
                        manager.insertar(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemApasco,selec,
                                volumen,elem,a_elemVolumen,a_totalCem,a_totalAre,a_totalGrab,a_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarApasco.setEnabled(false);
                    }else{
                        manager.insertar2(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemApasco,selec,
                                volumen,elem,a_elemVolumen,a_totalCem,a_totalAre,a_totalGrab,a_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarApasco.setEnabled(false);
                    }
                }else{
                    if(mySwitchSist.isChecked()){
                        manager.insertar3(tipo, estructura, forma, nulo, nulo, nulo, ladoh, nulo, cemApasco, selec,
                                volumen, elem, a_elemVolumen, a_totalCem, a_totalAre, a_totalGrab, a_totalAgu, descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarApasco.setEnabled(false);
                    }else{
                        manager.insertar4(tipo, estructura, forma, nulo, nulo, nulo, ladoh, nulo, cemApasco, selec,
                                volumen, elem, a_elemVolumen, a_totalCem, a_totalAre, a_totalGrab, a_totalAgu, descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarApasco.setEnabled(false);
                    }
                }
            }
        });

        //Boton para guardar valores de Moctezuma
        btnGuardarMoctezu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mySwitchNormal.isChecked()){
                    if(mySwitchSist.isChecked()){
                        manager.insertar(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemMoctezuma,selec,
                                volumen,elem,m_elemVolumen,m_totalCem,m_totalAre,m_totalGrab,m_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarMoctezu.setEnabled(false);
                    }else{
                        manager.insertar2(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemMoctezuma,selec,
                                volumen,elem,m_elemVolumen,m_totalCem,m_totalAre,m_totalGrab,m_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarMoctezu.setEnabled(false);
                    }
                }else{

                    if(mySwitchSist.isChecked()){
                        manager.insertar3(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemMoctezuma,selec,
                                volumen,elem,m_elemVolumen,m_totalCem,m_totalAre,m_totalGrab,m_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarMoctezu.setEnabled(false);
                    }else{
                        manager.insertar4(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemMoctezuma,selec,
                                volumen,elem,m_elemVolumen,m_totalCem,m_totalAre,m_totalGrab,m_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarMoctezu.setEnabled(false);
                    }
                }
            }
        });

        //Boton para guardar valores para Apasco
        btnGuardarAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mySwitchNormal.isChecked()){
                    if(mySwitchSist.isChecked()){
                        manager.insertar(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemAzul,selec,
                                volumen,elem,c_elemVolumen,c_totalCem,c_totalAre,c_totalGrab,c_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarAzul.setEnabled(false);
                    }else{
                        manager.insertar2(tipo,estructura,forma,nulo,nulo,nulo,ladoh,nulo,cemAzul,selec,
                                volumen,elem,c_elemVolumen,c_totalCem,c_totalAre,c_totalGrab,c_totalAgu,descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarAzul.setEnabled(false);
                    }
                }else{
                    if(mySwitchSist.isChecked()){
                        manager.insertar3(tipo, estructura, forma, nulo, nulo, nulo, ladoh, nulo, cemAzul, selec,
                                volumen, elem, c_elemVolumen, c_totalCem, c_totalAre, c_totalGrab, c_totalAgu, descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarAzul.setEnabled(false);
                    }else{
                        manager.insertar4(tipo, estructura, forma, nulo, nulo, nulo, ladoh, nulo, cemAzul, selec,
                                volumen, elem, c_elemVolumen, c_totalCem, c_totalAre, c_totalGrab, c_totalAgu, descrip);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarAzul.setEnabled(false);
                    }
                }
            }
        });

        btnCalcular.setEnabled(false);
        limpiarCampos();
    }

    private void valoresMoctezuma(double elemVolumen, double totalCem, double totalAre, double totalGrab, double totalAgua) {
        m_elemVolumen = elemVolumen;
        m_totalCem = totalCem;
        m_totalAre = totalAre;
        m_totalGrab = totalGrab;
        m_totalAgu = totalAgua;
    }

    private void valoresApasco(double elemVolumen, double totalCem, double totalAre, double totalGrab, double totalAgua) {
        //Guarda valores Apasco
        a_elemVolumen = elemVolumen;
        a_totalCem = totalCem;
        a_totalAre = totalAre;
        a_totalGrab = totalGrab;
        a_totalAgu = totalAgua;
    }

    private void valoresTolteca(double elemVolumen, double totalCem, double totalAre, double totalGrab, double totalAgua) {
        //Guarda los valores de Tolteca
        t_elemVolumen = elemVolumen;
        t_totalCem = totalCem;
        t_totalAre = totalAre;
        t_totalGrab = totalGrab;
        t_totalAgu = totalAgua;
    }

    private void valoresAzul(double elemVolumen, double totalCem, double totalAre, double totalGrab, double totalAgua) {
        //Guarda valores Apasco
        c_elemVolumen = elemVolumen;
        c_totalCem = totalCem;
        c_totalAre = totalAre;
        c_totalGrab = totalGrab;
        c_totalAgu = totalAgua;
    }

    //Calcula volumen
    double calculaVolumen(double radio){
        return (3.1415 * radio * radio * radio / 6);
    }

    private void generarGrafica(double cemento, double arena, double grava, double agua) {
        float a = (float) cemento;
        float b = (float) arena;
        float c = (float) grava;
        float d = (float) agua;
        center = "Cemento:\n "+a;
        pieChart.setCenterText(center);
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
        //valsY.add(new Entry(a,0));
        valsY.add(new Entry(b,1));
        valsY.add(new Entry(c,2));
        valsY.add(new Entry(d,3));

 		/*creamos una lista para los valores X*/
        ArrayList<String> valsX = new ArrayList<String>();
        //valsX.add(getString(R.string.desc_cemento));
        valsX.add(getString(R.string.desc_arena));
        valsX.add(getString(R.string.desc_grava));
        valsX.add(getString(R.string.desc_agua));

 		/*creamos una lista de colores*/
        ArrayList<Integer> colors = new ArrayList<Integer>();
        //colors.add(getResources().getColor(R.color.md_cemento));
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

    public boolean validarcmpAlto(){
        if (txtAlto.getText().toString().trim().isEmpty()) {
            input_cmpAlto.setError(getString(R.string.cmp_diametro));
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