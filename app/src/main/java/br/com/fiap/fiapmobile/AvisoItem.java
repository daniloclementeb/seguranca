package br.com.fiap.fiapmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.TextView;
import br.com.fiap.services.AvisoService;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockActivity;

public class AvisoItem
  extends SherlockActivity
{
  WebView conteudo;
  String data;
  TextView date;
  int idaviso;
  int lido;
  String pchave;
  String prm;
  SharedPreferences sb;
  String texto;
  TextView title;
  String titulo;
  AvisoService webservice;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903068);
    paramBundle = getIntent();
    this.idaviso = paramBundle.getIntExtra("id", 0);
    this.titulo = paramBundle.getStringExtra("titulo");
    this.data = paramBundle.getStringExtra("data");
    this.texto = paramBundle.getStringExtra("texto");
    this.lido = paramBundle.getIntExtra("lido", 0);
    this.webservice = new AvisoService();
    Log.i("lido", String.valueOf(this.lido));
    Log.i("lido", String.valueOf(this.idaviso));
    this.sb = getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.conteudo = ((WebView)findViewById(2131427418));
    paramBundle = getLayoutInflater().inflate(2130903071, null);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setCustomView(paramBundle, new ActionBar.LayoutParams(-1, -1));
    localActionBar.setDisplayOptions(16);
    localActionBar.setDisplayUseLogoEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(false);
    localActionBar.setDisplayShowHomeEnabled(false);
    localActionBar.setDisplayHomeAsUpEnabled(false);
    this.title = ((TextView)findViewById(2131623936));
    this.title.setText(this.titulo);
    this.date = ((TextView)findViewById(2131623937));
    this.date.setText(this.data);
    this.conteudo.loadDataWithBaseURL("x-data://base", this.texto, "text/html", "UTF-8", null);
    if (this.lido == 0) {
      this.webservice.marcaraviso(this.prm, String.valueOf(this.idaviso), this.pchave);
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\AvisoItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */