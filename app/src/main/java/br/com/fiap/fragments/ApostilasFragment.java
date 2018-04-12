package br.com.fiap.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.fiap.beans.ListasBean;
import br.com.fiap.beans.TurmaBean;
import br.com.fiap.fiapmobile.ListaDisciplinasActivity;
import br.com.fiap.layouts.ListasAdapter;
import br.com.fiap.services.ApostilaService;
import br.com.fiap.services.TurmaService;
import java.util.ArrayList;
import java.util.List;

public class ApostilasFragment
  extends Fragment
{
  String[] ano;
  ListView lista;
  List<ListasBean> listaInfos;
  View myView;
  ProgressDialog p;
  String pano;
  String pchave;
  String pcurso;
  String pnome;
  String pnometurma;
  String prm;
  String ptipo;
  SharedPreferences sb;
  String[] turma;
  TurmaService twebservice;
  ApostilaService webservice;
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.myView = LayoutInflater.from(getActivity()).inflate(2130903063, null);
    this.lista = ((ListView)this.myView.findViewById(2131427409));
    this.webservice = new ApostilaService();
    this.twebservice = new TurmaService();
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.p = ProgressDialog.show(getActivity(), null, String.valueOf(getResources().getText(2131099724)), true);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.ptipo = this.sb.getString("ptipo", "");
    new Thread()
    {
      public void run()
      {
        try
        {
          localList = ApostilasFragment.this.twebservice.turma(ApostilasFragment.this.prm, ApostilasFragment.this.pchave);
          ApostilasFragment.this.listaInfos = new ArrayList();
          ApostilasFragment.this.turma = new String[localList.size()];
          ApostilasFragment.this.ano = new String[localList.size()];
          i = 0;
          int j = localList.size();
          if (i < j) {
            break label96;
          }
        }
        catch (Exception localException)
        {
          List localList;
          int i;
          label96:
          ListasBean localListasBean;
          for (;;) {}
        }
        ApostilasFragment.this.p.dismiss();
        return;
        localListasBean = new ListasBean();
        if (ApostilasFragment.this.ptipo.equalsIgnoreCase("G")) {
          localListasBean.setLinha1(((TurmaBean)localList.get(i)).getNomeTurma() + " - " + ((TurmaBean)localList.get(i)).getAno());
        }
        for (;;)
        {
          localListasBean.setLinha2(((TurmaBean)localList.get(i)).getCurso());
          ApostilasFragment.this.listaInfos.add(localListasBean);
          ApostilasFragment.this.turma[i] = ((TurmaBean)localList.get(i)).getNomeTurma();
          ApostilasFragment.this.ano[i] = ((TurmaBean)localList.get(i)).getAno();
          i += 1;
          break;
          localListasBean.setLinha1(((TurmaBean)localList.get(i)).getNomeTurma());
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (ApostilasFragment.this.turma.length == 1)
        {
          paramAnonymousDialogInterface = new Intent(ApostilasFragment.this.getActivity(), ListaDisciplinasActivity.class);
          paramAnonymousDialogInterface.putExtra("turma", ApostilasFragment.this.turma[0]);
          paramAnonymousDialogInterface.putExtra("ano", ApostilasFragment.this.ano[0]);
          ApostilasFragment.this.startActivity(paramAnonymousDialogInterface);
        }
        paramAnonymousDialogInterface = new ListasAdapter(ApostilasFragment.this.getActivity(), ApostilasFragment.this.listaInfos);
        ApostilasFragment.this.lista.setAdapter(paramAnonymousDialogInterface);
        ApostilasFragment.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = new Intent(ApostilasFragment.this.getActivity(), ListaDisciplinasActivity.class);
            paramAnonymous2AdapterView.putExtra("turma", ApostilasFragment.this.turma[paramAnonymous2Int]);
            paramAnonymous2AdapterView.putExtra("ano", ApostilasFragment.this.ano[paramAnonymous2Int]);
            ApostilasFragment.this.startActivity(paramAnonymous2AdapterView);
          }
        });
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\ApostilasFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */