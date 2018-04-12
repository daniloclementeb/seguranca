package br.com.fiap.fiapmobile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.beans.DisciplinaBean;
import br.com.fiap.beans.ListasBean;
import br.com.fiap.layouts.ListasAdapter;
import br.com.fiap.services.ApostilaService;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockActivity;
import java.util.ArrayList;
import java.util.List;

public class ListaDisciplinasActivity
  extends SherlockActivity
{
  String ano;
  String[] codrelacao;
  List<DisciplinaBean> disciplinas;
  ListView lista;
  List<ListasBean> listaInfos;
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
  String turma;
  ApostilaService webservice;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903088);
    paramBundle = getLayoutInflater().inflate(2130903072, null);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setCustomView(paramBundle, new ActionBar.LayoutParams(-1, -1));
    localActionBar.setDisplayOptions(16);
    localActionBar.setDisplayUseLogoEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(false);
    localActionBar.setDisplayShowHomeEnabled(false);
    localActionBar.setDisplayHomeAsUpEnabled(false);
    this.title = ((TextView)findViewById(2131689472));
    this.title.setText("Disciplinas");
    this.p = ProgressDialog.show(this, null, "Carregando Dados...", true);
    paramBundle = getIntent();
    this.turma = paramBundle.getStringExtra("turma");
    this.ano = paramBundle.getStringExtra("ano");
    this.sb = getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.webservice = new ApostilaService();
    this.lista = ((ListView)findViewById(2131427464));
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            ListaDisciplinasActivity.this.disciplinas = ListaDisciplinasActivity.this.webservice.disciplinas(ListaDisciplinasActivity.this.prm, ListaDisciplinasActivity.this.pchave, ListaDisciplinasActivity.this.ano, ListaDisciplinasActivity.this.turma);
            ListaDisciplinasActivity.this.codrelacao = new String[ListaDisciplinasActivity.this.disciplinas.size()];
            ListaDisciplinasActivity.this.listaInfos = new ArrayList();
            i = 0;
            int j = ListaDisciplinasActivity.this.disciplinas.size();
            if (i < j) {
              continue;
            }
          }
          catch (Exception localException)
          {
            int i;
            ListasBean localListasBean;
            continue;
          }
          ListaDisciplinasActivity.this.p.dismiss();
          return;
          localListasBean = new ListasBean();
          localListasBean.setLinha1(((DisciplinaBean)ListaDisciplinasActivity.this.disciplinas.get(i)).getNomedisciplina());
          localListasBean.setLinha2(((DisciplinaBean)ListaDisciplinasActivity.this.disciplinas.get(i)).getNomeprofessor());
          ListaDisciplinasActivity.this.listaInfos.add(localListasBean);
          ListaDisciplinasActivity.this.codrelacao[i] = String.valueOf(((DisciplinaBean)ListaDisciplinasActivity.this.disciplinas.get(i)).getCodrelacao());
          i += 1;
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (ListaDisciplinasActivity.this.disciplinas.size() == 0)
        {
          Toast.makeText(ListaDisciplinasActivity.this, "Não há disciplinas cadastradas", 1).show();
          ListaDisciplinasActivity.this.finish();
        }
        paramAnonymousDialogInterface = new ListasAdapter(ListaDisciplinasActivity.this, ListaDisciplinasActivity.this.listaInfos);
        ListaDisciplinasActivity.this.lista.setAdapter(paramAnonymousDialogInterface);
        ListaDisciplinasActivity.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = new Intent(ListaDisciplinasActivity.this, ListaApostilasActivity.class);
            paramAnonymous2AdapterView.putExtra("codrelacao", ListaDisciplinasActivity.this.codrelacao[paramAnonymous2Int]);
            ListaDisciplinasActivity.this.startActivity(paramAnonymous2AdapterView);
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


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\ListaDisciplinasActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */