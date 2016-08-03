package heder_ithamar.apmaco.uiContenidoMinimo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import heder_ithamar.apmaco.R;
import heder_ithamar.apmaco.datos.DataBaseManager;

import heder_ithamar.apmaco.R;

public class ResultadoCM extends AppCompatActivity {
    private DataBaseManager manager;
    private Cursor cursor,cursoringles;
    private ListView lista,listaingles;
    private TextView resultado,resultado2,tv_ID,tv_Nombre;
    SimpleCursorAdapter adapter, adapteringles;
    String elimina,aviso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_cm);
        //inicia
        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String name = i.getStringExtra(getString(R.string.btn_resu));
        int idDrawable = R.drawable.resultadofinal;

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle(name); // Cambiar título

        loadImageParallax(idDrawable);// Cargar Imagen

        aviso = String.format(getString(R.string.string_aviso), getString(R.string.btn_resu));
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

        inicializarTabs();

        resultado = (TextView)findViewById(R.id.textView_Totales);
        resultado2 = (TextView)findViewById(R.id.textView_Ingles);

        //Crear la base de datos
        manager = new DataBaseManager(this);
        lista = (ListView) findViewById(R.id.listView);
        listaingles = (ListView) findViewById(R.id.listViewIngles);

        //ejemplos
        //manager.insertar("edificio","cimiento","tolteca","100",5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,13.0);

        resultado.setText(manager.sumaCemento5());
        //resultado2.setText(manager.sumaCemento2());

        String[] desde = new String[]{manager.CN_ID,manager.CN_TIPOCONSTRUCCION,manager.CN_ESTRUCTURA,manager.CN_FORMA,manager.CN_lADOA,manager.CN_lADOB,manager.CN_lADOH,manager.CN_AlADO,manager.CN_BlADO,manager.CN_TIPOCEMENTO,manager.CN_RESISTENCIA,manager.CN_VOLUMEN,manager.CN_ELEMENTO,manager.CN_VOLUMENELEMENTO,manager.CN_CEMENTO,manager.CN_ARENA,manager.CN_GRABA,manager.CN_AGUA,manager.CN_DESCRIPCION,manager.CN_GRAVAARENA,manager.CN_DENSIDADG,manager.CN_DENSIDADA,manager.CN_DENSIDADC,manager.CN_MAXAIRE,manager.CN_AIRE};
        int[] para = new int[] {R.id.txtID,R.id.txtTipoConstruccion,R.id.txtTipoEstrucctura,R.id.txtForma,R.id.txtLadoa,R.id.txtLadob,R.id.txtLadoh,R.id.txtAlado,R.id.txtBlado,R.id.txtTipoCemento,R.id.txtResistencia,R.id.txtVolumen,R.id.txtUnidades,R.id.txtVolumenUnidades,R.id.txtCemento,R.id.txtArena,R.id.txtGrava,R.id.txtAgua,R.id.txtDescripcion,R.id.txtGravaArena,R.id.txtDensidadG,R.id.txtDensidadA,R.id.txtDensidadC,R.id.txtMaxAire,R.id.txtAire};

        cursor = manager.cargarCursorContent5();
        cursoringles = manager.cargarCursorContentIngles();

        adapter = new SimpleCursorAdapter(this,R.layout.resultadocm_item,cursor,desde,para,0);
        //adapteringles = new SimpleCursorAdapter(this,R.layout.resultado_ingles_item,cursoringles,from,to,0);

        lista.setAdapter(adapter);
        //listaingles.setAdapter(adapteringles);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "aun no me programan", Toast.LENGTH_SHORT).show();

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_ID = (TextView) view.findViewById(R.id.txtID);
                tv_Nombre = (TextView) view.findViewById(R.id.txtTipoEstrucctura);
                PopupMenu popup = new PopupMenu(ResultadoCM.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_resultados, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_delete_item:

                                final String aux_miembroId = tv_ID.getText().toString();
                                String aux_miembroNombre = tv_Nombre.getText().toString();
                                AlertDialog.Builder adb = new AlertDialog.Builder(ResultadoCM.this);

                                adb.setTitle(R.string.msj_eliminar);
                                adb.setMessage(getString(R.string.msj_eliminaritem) + " " + aux_miembroNombre);
                                adb.setNegativeButton(R.string.msj_cancelar, null);
                                adb.setPositiveButton(R.string.msj_ok, new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        manager.eliminar5(aux_miembroId);
                                        Toast.makeText(getApplicationContext(), R.string.alert2, Toast.LENGTH_SHORT).show();
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                });
                                adb.show();
                                //Toast.makeText(Resultados.this,"soy id: "+ aux_miembroId + " tipo: "+aux_miembroNombre, Toast.LENGTH_LONG).show();

                                break;
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });

        /*listaingles.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_ID = (TextView) view.findViewById(R.id.txtID);
                tv_Nombre = (TextView) view.findViewById(R.id.txtTipoEstrucctura);
                PopupMenu popup = new PopupMenu(ResultadoCM.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_resultados, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_delete_item:

                                final String aux_miembroId = tv_ID.getText().toString();
                                String aux_miembroNombre = tv_Nombre.getText().toString();
                                AlertDialog.Builder adb = new AlertDialog.Builder(ResultadoCM.this);

                                adb.setTitle(R.string.msj_eliminar);
                                adb.setMessage(getString(R.string.msj_eliminaritem)+" " + aux_miembroNombre);
                                adb.setNegativeButton(R.string.msj_cancelar, null);
                                adb.setPositiveButton(R.string.msj_ok, new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        manager.eliminar2(aux_miembroId);
                                        Toast.makeText(getApplicationContext(), R.string.alert2, Toast.LENGTH_SHORT).show();
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                });
                                adb.show();
                                //Toast.makeText(Resultados.this,"soy id: "+ aux_miembroId + " tipo: "+aux_miembroNombre, Toast.LENGTH_LONG).show();

                                break;
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });*/


    }
    private void inicializarTabs() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost2);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab4");
        spec.setContent(R.id.tab4);
        spec.setIndicator(this.getString(R.string.txt_sistemainter));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab5");
        spec.setContent(R.id.tab5);
        spec.setIndicator(this.getString(R.string.txt_sistemaingle));
        tabHost.addTab(spec);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_delete_item:

                AlertDialog.Builder adb = new AlertDialog.Builder(ResultadoCM.this);

                adb.setTitle(R.string.msj_eliminar);
                adb.setMessage(R.string.msj_eliminabase);
                adb.setNegativeButton(R.string.msj_cancelar, null);
                adb.setPositiveButton(R.string.msj_ok, new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        manager.eliminadatos();
                        Intent intent2 = getIntent();
                        showSnackBar(getString(R.string.msj_eliminarok));
                        finish();
                        startActivity(intent2);
                    }
                });
                adb.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
