package heder_ithamar.apmaco.ui;

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
import heder_ithamar.apmaco.uiContenidoMinimo.AppTrapecioCM;
import heder_ithamar.apmaco.uiContenidoMinimo.ResultadoCM;

public class Resultados extends AppCompatActivity {

    private DataBaseManager manager;
    private Cursor cursor, cursordesc, cursoringles, cursoringlesdesc;
    private ListView lista, listadescuento, listaingles, listainglesdescuento;
    private TextView resultado, resultado2, resultadodesc, resultadoingles,resultadoInter, resultadoIng, tv_ID,tv_Nombre;
    SimpleCursorAdapter adapter,adapterdesc, adapteringles, adapteringlesdesc;
    String aviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
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
                        AlertDialog.Builder adb = new AlertDialog.Builder(Resultados.this);

                        adb.setTitle(R.string.msj_contenidominimo2);
                        adb.setMessage(getString(R.string.msj_calculacmresul));
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

        inicializarTabs();

        resultadoInter = (TextView)findViewById(R.id.textView_ResultadoInter);
        resultadoIng = (TextView)findViewById(R.id.textView_ResultadoIngles);

        resultado = (TextView)findViewById(R.id.textView_Totales);
        resultadodesc = (TextView)findViewById(R.id.textView_normdesc);

        resultadoingles = (TextView)findViewById(R.id.textViewTotalIngles);
        resultado2 = (TextView)findViewById(R.id.textView_Ingles);


        //Crear la base de datos
        manager = new DataBaseManager(this);
        lista = (ListView) findViewById(R.id.listView);
        listadescuento = (ListView) findViewById(R.id.listViewDesc);

        listaingles = (ListView)findViewById(R.id.listViewIngles);
        listainglesdescuento = (ListView)findViewById(R.id.listViewInglesDesc);


        //ejemplos
        //manager.insertar("edificio","cimiento","tolteca","100",5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,13.0);

        resultadoInter.setText(manager.sumaInternacional());
        resultadoIng.setText(manager.sumaIngles());

        resultado.setText(manager.sumaCemento());
        resultadodesc.setText(manager.sumaCemento3());

        resultadoingles.setText(manager.sumaCemento2());
        resultado2.setText(manager.sumaCemento4());

        String[] from = new String[]{manager.CN_ID,manager.CN_TIPOCONSTRUCCION,manager.CN_ESTRUCTURA,manager.CN_FORMA,manager.CN_lADOA,manager.CN_lADOB,manager.CN_lADOH,manager.CN_AlADO,manager.CN_BlADO,manager.CN_TIPOCEMENTO,manager.CN_RESISTENCIA,manager.CN_VOLUMEN,manager.CN_ELEMENTO,manager.CN_VOLUMENELEMENTO,manager.CN_CEMENTO,manager.CN_ARENA,manager.CN_GRABA,manager.CN_AGUA,manager.CN_DESCRIPCION};
        int[] to = new int[] {R.id.txtID,R.id.txtTipoConstruccion,R.id.txtTipoEstrucctura,R.id.txtForma,R.id.txtLadoa,R.id.txtLadob,R.id.txtLadoh,R.id.txtAlado,R.id.txtBlado,R.id.txtTipoCemento,R.id.txtResistencia,R.id.txtVolumen,R.id.txtUnidades,R.id.txtVolumenUnidades,R.id.txtCemento,R.id.txtArena,R.id.txtGrava,R.id.txtAgua,R.id.txtDescripcion};

        cursor = manager.cargarCursorContent();
        cursordesc = manager.cargarCursorContent3();

        cursoringles = manager.cargarCursorContentIngles();
        cursoringlesdesc = manager.cargarCursorContent4();


        adapter = new SimpleCursorAdapter(this,R.layout.resultado_item,cursor,from,to,0);
        adapterdesc = new SimpleCursorAdapter(this,R.layout.resultado_item,cursordesc,from,to,0);

        adapteringles = new SimpleCursorAdapter(this,R.layout.resultado_ingles_item,cursoringles,from,to,0);
        adapteringlesdesc = new SimpleCursorAdapter(this,R.layout.resultado_ingles_item,cursoringlesdesc,from,to,0);

        lista.setAdapter(adapter);
        listadescuento.setAdapter(adapterdesc);
        listaingles.setAdapter(adapteringles);
        listainglesdescuento.setAdapter(adapteringlesdesc);


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
                PopupMenu popup = new PopupMenu(Resultados.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_resultados, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_delete_item:

                                final String aux_miembroId = tv_ID.getText().toString();
                                String aux_miembroNombre = tv_Nombre.getText().toString();
                                AlertDialog.Builder adb = new AlertDialog.Builder(Resultados.this);

                                adb.setTitle(R.string.msj_eliminar);
                                adb.setMessage(getString(R.string.msj_eliminaritem) + " " + aux_miembroNombre);
                                adb.setNegativeButton(R.string.msj_cancelar, null);
                                adb.setPositiveButton(R.string.msj_ok, new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        manager.eliminar(aux_miembroId);
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

        listadescuento.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_ID = (TextView) view.findViewById(R.id.txtID);
                tv_Nombre = (TextView) view.findViewById(R.id.txtTipoEstrucctura);
                PopupMenu popup = new PopupMenu(Resultados.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_resultados, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_delete_item:

                                final String aux_miembroId = tv_ID.getText().toString();
                                String aux_miembroNombre = tv_Nombre.getText().toString();
                                AlertDialog.Builder adb = new AlertDialog.Builder(Resultados.this);

                                adb.setTitle(R.string.msj_eliminar);
                                adb.setMessage(getString(R.string.msj_eliminaritem) + " " + aux_miembroNombre);
                                adb.setNegativeButton(R.string.msj_cancelar, null);
                                adb.setPositiveButton(R.string.msj_ok, new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        manager.eliminar3(aux_miembroId);
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
        listaingles.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_ID = (TextView) view.findViewById(R.id.txtID);
                tv_Nombre = (TextView) view.findViewById(R.id.txtTipoEstrucctura);
                PopupMenu popup = new PopupMenu(Resultados.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_resultados, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_delete_item:

                                final String aux_miembroId = tv_ID.getText().toString();
                                String aux_miembroNombre = tv_Nombre.getText().toString();
                                AlertDialog.Builder adb = new AlertDialog.Builder(Resultados.this);

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
        });

        listainglesdescuento.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_ID = (TextView) view.findViewById(R.id.txtID);
                tv_Nombre = (TextView) view.findViewById(R.id.txtTipoEstrucctura);
                PopupMenu popup = new PopupMenu(Resultados.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_resultados, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_delete_item:

                                final String aux_miembroId = tv_ID.getText().toString();
                                String aux_miembroNombre = tv_Nombre.getText().toString();
                                AlertDialog.Builder adb = new AlertDialog.Builder(Resultados.this);

                                adb.setTitle(R.string.msj_eliminar);
                                adb.setMessage(getString(R.string.msj_eliminaritem)+" " + aux_miembroNombre);
                                adb.setNegativeButton(R.string.msj_cancelar, null);
                                adb.setPositiveButton(R.string.msj_ok, new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        manager.eliminar4(aux_miembroId);
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


    }
    private void iniciaCM(){
        //Crear un nuevo intent
        Intent intent = new Intent(this,ResultadoCM.class);
        //Iniciar actividad
        startActivity(intent);
    }
    private void inicializarTabs() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost2);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator(this.getString(R.string.txt_normal));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator(this.getString(R.string.txt_descuento));
        tabHost.addTab(spec);

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost);
        tabHost1.setup();

        TabHost.TabSpec spec1 = tabHost1.newTabSpec("tabresul");
        spec1.setContent(R.id.tabresul);
        spec1.setIndicator(this.getString(R.string.title_activity_resultados));
        tabHost1.addTab(spec1);

        spec1 = tabHost1.newTabSpec("tabIntern");
        spec1.setContent(R.id.tabIntern);
        spec1.setIndicator(this.getString(R.string.txt_sistemainter));
        tabHost1.addTab(spec1);

        spec1 = tabHost1.newTabSpec("tabIngles");
        spec1.setContent(R.id.tabIngles);
        spec1.setIndicator(this.getString(R.string.txt_sistemaingle));
        tabHost1.addTab(spec1);


        TabHost tabHost2 = (TabHost) findViewById(R.id.tabHost3);
        tabHost2.setup();

        TabHost.TabSpec spec2 = tabHost2.newTabSpec("tab3");
        spec2.setContent(R.id.tab3);
        spec2.setIndicator(this.getString(R.string.txt_normal));
        tabHost2.addTab(spec2);

        spec2 = tabHost2.newTabSpec("tab4");
        spec2.setContent(R.id.tab4);
        spec2.setIndicator(this.getString(R.string.txt_descuento));
        tabHost2.addTab(spec2);

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

                AlertDialog.Builder adb = new AlertDialog.Builder(Resultados.this);

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
