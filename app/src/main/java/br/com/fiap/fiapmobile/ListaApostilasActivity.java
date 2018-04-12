package br.com.fiap.fiapmobile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fiap.beans.ApostilaBean;
import br.com.fiap.layouts.ApostilasAdapter;
import br.com.fiap.layouts.ToastManager;
import br.com.fiap.services.ApostilaService;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockActivity;
import java.util.List;

public class ListaApostilasActivity
  extends SherlockActivity
{
  String codrelacao;
  ListView lista;
  List<ApostilaBean> listaapstilas;
  ProgressDialog p;
  String pano;
  String pchave;
  String pcurso;
  String pnome;
  String pnometurma;
  String prm;
  String ptipo;
  SharedPreferences sb;
  TextView title;
  ApostilaService webservice;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903086);
    paramBundle = getLayoutInflater().inflate(2130903072, null);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setCustomView(paramBundle, new ActionBar.LayoutParams(-1, -1));
    localActionBar.setDisplayOptions(16);
    localActionBar.setDisplayUseLogoEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(false);
    localActionBar.setDisplayShowHomeEnabled(false);
    localActionBar.setDisplayHomeAsUpEnabled(false);
    this.title = ((TextView)findViewById(2131689472));
    this.title.setText("Arquivos");
    this.p = ProgressDialog.show(this, null, "Carregando Dados...", true);
    this.sb = getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.codrelacao = getIntent().getStringExtra("codrelacao");
    this.webservice = new ApostilaService();
    this.lista = ((ListView)findViewById(2131427463));
    new Thread()
    {
      public void run()
      {
        try
        {
          ListaApostilasActivity.this.listaapstilas = ListaApostilasActivity.this.webservice.apostilas(ListaApostilasActivity.this.prm, ListaApostilasActivity.this.pchave, ListaApostilasActivity.this.codrelacao);
          ListaApostilasActivity.this.p.dismiss();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (ListaApostilasActivity.this.listaapstilas.size() == 0)
        {
          ToastManager.show(ListaApostilasActivity.this, "Pasta vazia");
          ListaApostilasActivity.this.finish();
        }
        paramAnonymousDialogInterface = new ApostilasAdapter(ListaApostilasActivity.this, ListaApostilasActivity.this.listaapstilas);
        ListaApostilasActivity.this.lista.setAdapter(paramAnonymousDialogInterface);
        ListaApostilasActivity.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = ((ApostilaBean)ListaApostilasActivity.this.lista.getItemAtPosition(paramAnonymous2Int)).getUrl();
            paramAnonymous2View = new Intent("android.intent.action.VIEW");
            paramAnonymous2View.setData(Uri.parse(paramAnonymous2AdapterView));
            ListaApostilasActivity.this.startActivity(paramAnonymous2View);
          }
        });
      }
    });
  }
  
  public void voltar(View paramView)
  {
    finish();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\ListaApostilasActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */