package heder_ithamar.apmaco.uiContenidoMinimo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
import heder_ithamar.apmaco.operaciones.OpContenidoMinimoConDesp;
import heder_ithamar.apmaco.ui.Resultados;
import heder_ithamar.apmaco.util.TextChangedListener;

public class AppPiramideCM extends AppCompatActivity {

    private PieChart pieChart;

    private String[] GravaArena = {"60-40","50-50","40-60"};
    private String[] Resistencias = {"100","150","200","250","300","350","400"};
    private String[] MaxAire = {"10","13","20","25","38","51","76","152"};

    private Switch mySwitch, mySwitchSist, mySwitchNormal;
    private Spinner sp_resistencia,spn_gravaarena,spn_Max;

    private EditText txtLargo, txtAncho, txtAlto, txtElementos,txtDescripcion,cmpDensidadG,cmpWga,cmpDensidadC,cmpDensidadA;
    private TextInputLayout input_cmpLargo,input_cmpAncho, input_cmpAlto, input_cmpElementos,input_cmpDensidadG,input_cmpWga,input_cmpDensidadC,input_cmpDensidadA;
    private Button btnCalcular, btnGuardarVolumen;
    private TextView resultado, switchStatus,
            switchStatusSist, statusRes, switchstatusNormal;
    private DataBaseManager manager;
    private ArrayAdapter<String> adaptador0,adaptador,adaptador1;
    //Variables
    private Double volumen, elem, densidadG,densidadA,densidadC,wga,ladoa,ladob,ladoh,gravaArena,resistence,maxiaire,
            v_elemVolumen, v_totalCem, v_totalAre, v_totalGrab, v_totalAgu, v_totalAir;
    private String  estructura, forma, descrip, selec, txt_ladoa, txt_ladob, txt_ladoh, nulo, alerta1,
            aviso,selecGravaArena,selecMaxAire,txt_Wga,txt_DensidadG,txt_DensidadC,txt_DensidadA,tipocemento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_piramide_cm);
        //Inicia los componentes
        inicializarComponentesIU();
        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String name = i.getStringExtra(getString(R.string.title_activity_app_piramide_cm));
        int idDrawable = R.drawable.piramide;

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle(name); // Cambiar título

        loadImageParallax(idDrawable);// Cargar Imagen

        aviso = String.format(getString(R.string.string_aviso), getString(R.string.title_activity_app_piramide_cm));
        // Setear escucha al FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchStatus.setText(R.string.txt_condesp);
                } else {
                    switchStatus.setText(R.string.txt_sindesp);
                }

            }
        });

        //Elejir el sistema en el cual se va a trabajar
        mySwitchSist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //si se trabaja con Sistema Internacional
                if (isChecked) {
                    switchStatusSist.setText(R.string.txt_sistemainter);
                    statusRes.setText(R.string.txt_resist);
                }
                //Si se trabaja con sistema ingles
                else {
                    switchStatusSist.setText(R.string.txt_sistemaingle);
                    statusRes.setText(R.string.txt_resist_ing);
                }
            }
        });

        //Llama a metodos
        //RegistraAdaptadores();
        CargaRessistencia();
    }

    private void inicializarComponentesIU() {
        pieChart = (PieChart) findViewById(R.id.pieChart);
        //Setup Toolbar
        sp_resistencia = (Spinner) findViewById(R.id.spn_Res);
        spn_gravaarena = (Spinner) findViewById(R.id.spn_gravaarena);
        spn_Max = (Spinner) findViewById(R.id.spn_Max);

        mySwitch = (Switch) findViewById(R.id.mySwitch);
        switchStatus = (TextView) findViewById(R.id.switchStatus);
        mySwitchSist = (Switch) findViewById(R.id.mySwitch_Sist);
        switchStatusSist = (TextView) findViewById(R.id.switchStatusSist);
        statusRes = (TextView) findViewById(R.id.status_res);
        switchstatusNormal = (TextView) findViewById(R.id.switchStatusNomal);
        mySwitchNormal = (Switch) findViewById(R.id.mySwitchNomal);

        resultado = (TextView)findViewById(R.id.txtView_Resultado);

        input_cmpWga = (TextInputLayout) findViewById(R.id.input_cmpWga);
        input_cmpDensidadA = (TextInputLayout) findViewById(R.id.input_cmpDensidadA);
        input_cmpDensidadC = (TextInputLayout) findViewById(R.id.input_cmpDensidadC);
        input_cmpDensidadG = (TextInputLayout) findViewById(R.id.input_cmpDensidadG);

        input_cmpLargo = (TextInputLayout) findViewById(R.id.input_cmpLargo);
        input_cmpAncho = (TextInputLayout) findViewById(R.id.input_cmpAncho);
        input_cmpAlto = (TextInputLayout) findViewById(R.id.input_cmpAlto);
        input_cmpElementos = (TextInputLayout) findViewById(R.id.input_cmpElementos);

        cmpWga = (EditText)findViewById(R.id.cmpWga);
        cmpDensidadA = (EditText)findViewById(R.id.cmpDensidadA);
        cmpDensidadC = (EditText)findViewById(R.id.cmpDensidadC);
        cmpDensidadG = (EditText)findViewById(R.id.cmpDensidadG);

        txtLargo = (EditText)findViewById(R.id.cmpLargo);
        txtAncho = (EditText)findViewById(R.id.cmpAncho);
        txtAlto = (EditText)findViewById(R.id.cmpAlto);
        txtElementos = (EditText)findViewById(R.id.cmpElementos);
        txtDescripcion = (EditText)findViewById(R.id.cmpDescripcion);

        btnGuardarVolumen = (Button) findViewById(R.id.btnVolumen);
        //Activar el boton  calcular
        txtAlto.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence seq, int i, int i2, int i3) {
                btnCalcular = (Button) findViewById(R.id.btnCalcular);
                btnCalcular.setEnabled(!seq.toString().trim().isEmpty());

            }
        });
    }

    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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


    public void CargaRessistencia(){
        adaptador0 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,GravaArena);
        spn_gravaarena.setAdapter(adaptador0);

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Resistencias);
        sp_resistencia.setAdapter(adaptador);

        adaptador1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,MaxAire);
        spn_Max.setAdapter(adaptador1);


    }

    public void imprimeVolumen(String res){
        resultado.setVisibility(View.VISIBLE);
        resultado.setText(res);
        btnGuardarVolumen.setEnabled(true);
        btnGuardarVolumen.setVisibility(View.VISIBLE);
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
                return true;
            case R.id.action_result:
                startActivity(new Intent(this, Resultados.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view) {
        if (!validarWga()) {
            return;
        }
        if (!validarDensidadG()) {
            return;
        }

        if (!validarDensidadA()) {
            return;
        }

        if (!validarDensidadC()) {
            return;
        }

        if (!validarcmpLargo()) {
            return;
        }

        if (!validarcmpAncho()) {
            return;
        }

        if (!validarcmpAlto()) {
            return;
        }

        if (!validarcmpElementos()) {
            return;
        }

        selecGravaArena = spn_gravaarena.getSelectedItem().toString();
        selec = sp_resistencia.getSelectedItem().toString();
        selecMaxAire = spn_Max.getSelectedItem().toString();

        txt_Wga = cmpWga.getText().toString();
        txt_DensidadA = cmpDensidadA.getText().toString();
        txt_DensidadC= cmpDensidadC.getText().toString();
        txt_DensidadG = cmpDensidadG.getText().toString();
        txt_ladoa = txtLargo.getText().toString();
        txt_ladob = txtAncho.getText().toString();
        txt_ladoh = txtAlto.getText().toString();


        wga = Double.parseDouble(txt_Wga);
        densidadA = Double.parseDouble(txt_DensidadA);
        densidadC = Double.parseDouble(txt_DensidadC);
        densidadG = Double.parseDouble(txt_DensidadG);
        ladoa = Double.parseDouble(txt_ladoa);
        ladob = Double.parseDouble(txt_ladob);
        ladoh = Double.parseDouble(txt_ladoh);

        switch (selecGravaArena){
            case "60-40":
                gravaArena = 0.6;
                break;
            case "50-50":
                gravaArena = 0.5;
                break;
            case "40-60":
                gravaArena = 0.4;
                break;
            default:
                Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                break;
        }

        switch (selec){
            case "100":
                resistence = 0.9035;
                break;
            case "150":
                resistence = 0.7696;
                break;
            case "200":
                resistence = 0.6762;
                break;
            case "250":
                resistence = 0.5948;
                break;
            case "300":
                resistence = 0.5255;
                break;
            case "350":
                resistence = 0.45804;
                break;
            case "400":
                resistence = 0.40804;
                break;
            default:
                Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                break;
        }
        switch (selecMaxAire){
            case "10":
                maxiaire = 0.03;
                break;
            case "13":
                maxiaire = 0.025;
                break;
            case "20":
                maxiaire = 0.02;
                break;
            case "25":
                maxiaire = 0.015;
                break;
            case "38":
                maxiaire = 0.01;
                break;
            case "51":
                maxiaire = 0.005;
                break;
            case "76":
                maxiaire = 0.003;
                break;
            case "152":
                maxiaire = 0.002;
                break;
            default:
                Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                break;
        }

        volumen = calculaVolumen(ladoa, ladob, ladob);

        elem = Double.parseDouble(txtElementos.getText().toString());
        descrip = txtDescripcion.getText().toString();
        tipocemento = this.getString(R.string.msj_contenidominimo);
        estructura = this.getString(R.string.btn_pirami);
        alerta1 = this.getString(R.string.alert1);


        // si es Calculo con sistema Internacional
        if(mySwitchSist.isChecked()){
            Toast.makeText(getApplicationContext(), R.string.alert0, Toast.LENGTH_SHORT).show();
            //Calculo con desperdicio
            if(mySwitch.isChecked()){
                //Aquitodo el codigo con desperdicio
                forma = this.getString(R.string.txt_condesp);

                //Crear el objeto
                OpContenidoMinimoConDesp op = new OpContenidoMinimoConDesp();

                imprimeVolumen(op.CalculaContenidoMin(maxiaire,wga,gravaArena,densidadA,densidadG,
                        resistence, densidadC, volumen, elem));
                valoresVolumen(op.getVolumenElementos(), op.getCemento(), op.getArena(),
                        op.getGrava(), op.getAgua(), op.getAire());

                generarGrafica(op.getCemento(), op.getArena(),
                        op.getGrava(), op.getAgua(), op.getAire());

            }
            //Calculo sin desperdicio
            else {
                //Valores para calcular sin desperdicio
                forma = this.getString(R.string.txt_sindesp);

                //Crear el objeto
                OpContenidoMinimoConDesp op = new OpContenidoMinimoConDesp();

                imprimeVolumen(op.CalculaContenidoMinSinDes(maxiaire, wga, gravaArena, densidadA,
                        densidadG, resistence, densidadC, volumen, elem));
                valoresVolumen(op.getVolumenElementos(), op.getCemento(), op.getArena(),
                        op.getGrava(), op.getAgua(), op.getAire());

                generarGrafica(op.getCemento(), op.getArena(),
                        op.getGrava(), op.getAgua(),op.getAire());

            }
        }
        // si es caso con sistema Ingles
        else {
            Toast.makeText(getApplicationContext(), R.string.alert0, Toast.LENGTH_SHORT).show();
            //Calculo con desperdicio
            if(mySwitch.isChecked()){
                //Aquitodo el codigo con desperdicio
                forma = this.getString(R.string.txt_condesp);


            }
            //Calculo sin desperdicio
            else {
                //Valores para calcular sin desperdicio
                forma = this.getString(R.string.txt_sindesp);
            }
        }
        //Boton para guardar valores de Volumen
        btnGuardarVolumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mySwitchNormal.isChecked()){
                    if(mySwitchSist.isChecked()){
                        manager.insertar5("", estructura, forma, txt_ladoa, txt_ladob, txt_ladoh, nulo, nulo,
                                tipocemento, resistence.toString(), volumen, elem, v_elemVolumen,
                                v_totalCem, v_totalAre, v_totalGrab, v_totalAgu, descrip,
                                gravaArena, densidadG, densidadA, densidadC, maxiaire, v_totalAir);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarVolumen.setEnabled(false);
                    }else{
                        manager.insertar5("", estructura, forma, txt_ladoa, txt_ladob, txt_ladoh, nulo, nulo,
                                tipocemento, resistence.toString(), volumen, elem, v_elemVolumen,
                                v_totalCem, v_totalAre, v_totalGrab, v_totalAgu, descrip,
                                gravaArena, densidadG, densidadA, densidadC, maxiaire, v_totalAir);
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarVolumen.setEnabled(false);
                    }
                }else{
                    if(mySwitchSist.isChecked()){
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarVolumen.setEnabled(false);
                    }else{
                        Toast.makeText(getApplicationContext(), alerta1, Toast.LENGTH_SHORT).show();
                        btnGuardarVolumen.setEnabled(false);
                    }
                }
            }
        });

        btnCalcular.setEnabled(false);
        limpiarCampos();
    }

    private void valoresVolumen(double elemVolumen, double totalCem, double totalAre, double totalGrab, double totalAgua, double totalaire) {
        v_elemVolumen = elemVolumen;
        v_totalCem = totalCem;
        v_totalAre = totalAre;
        v_totalGrab = totalGrab;
        v_totalAgu = totalAgua;
        v_totalAir = totalaire;
    }

    private Double calculaVolumen(Double base, Double altura, Double elevacion) {
        return (elevacion * base * altura / 6);
    }

    private void generarGrafica(double cemento, double grava, double arena, double agua, double aire) {
        float a = (float) cemento;
        float b = (float) grava;
        float c = (float) arena;
        float d = (float) agua;
        float e = (float) aire;
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
        valsY.add(new Entry(e,4));

 		/*creamos una lista para los valores X*/
        ArrayList<String> valsX = new ArrayList<String>();
        valsX.add(getString(R.string.desc_cemento));
        valsX.add(getString(R.string.desc_grava));
        valsX.add(getString(R.string.desc_arena));
        valsX.add(getString(R.string.desc_agua));
        valsX.add(getString(R.string.desc_aire));

 		/*creamos una lista de colores*/
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.md_cemento));
        colors.add(getResources().getColor(R.color.md_grava));
        colors.add(getResources().getColor(R.color.md_arena));
        colors.add(getResources().getColor(R.color.md_agua));
        colors.add(getResources().getColor(R.color.md_aire));

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
        txtLargo.getText().clear();
        txtAncho.getText().clear();
        txtAlto.getText().clear();
        txtElementos.setText("1");
        txtDescripcion.getText().clear();

        //oculta el teclado
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        pieChart.setFocusable(true);
        pieChart.setFocusableInTouchMode(true);///add this line
        pieChart.requestFocus();
        //txtLargo.requestFocus();
    }

    public boolean validarWga(){
        if (cmpWga.getText().toString().trim().isEmpty()) {
            input_cmpWga.setError(getString(R.string.txt_wga));
            requestFocus(cmpWga);
            return false;
        } else {
            input_cmpWga.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validarDensidadG(){
        if (cmpDensidadG.getText().toString().trim().isEmpty()) {
            input_cmpDensidadG.setError(getString(R.string.txt_densidadg));
            requestFocus(cmpDensidadG);
            return false;
        } else {
            input_cmpDensidadG.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validarDensidadA(){
        if (cmpDensidadA.getText().toString().trim().isEmpty()) {
            input_cmpDensidadA.setError(getString(R.string.txt_densidada));
            requestFocus(cmpDensidadA);
            return false;
        } else {
            input_cmpDensidadA.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validarDensidadC(){
        if (cmpDensidadC.getText().toString().trim().isEmpty()) {
            input_cmpDensidadC.setError(getString(R.string.txt_densidadc));
            requestFocus(cmpDensidadC);
            return false;
        } else {
            input_cmpDensidadC.setErrorEnabled(false);
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

    public boolean validarcmpAncho(){
        if (txtAncho.getText().toString().trim().isEmpty()) {
            input_cmpAncho.setError(getString(R.string.cmp_ancho));
            requestFocus(txtAncho);
            return false;
        } else {
            input_cmpAncho.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validarcmpAlto(){
        if (txtAlto.getText().toString().trim().isEmpty()) {
            input_cmpAlto.setError(getString(R.string.cmp_altura));
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
