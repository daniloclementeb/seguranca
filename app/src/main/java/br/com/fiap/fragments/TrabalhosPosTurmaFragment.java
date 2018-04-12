package br.com.fiap.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
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
import br.com.fiap.fiapmobile.TrabalhosPosPager;
import br.com.fiap.layouts.ListasAdapter;
import br.com.fiap.services.BoletimService;
import br.com.fiap.services.TurmaService;
import java.util.ArrayList;
import java.util.List;

public class TrabalhosPosTurmaFragment
  extends Fragment
{
  String[] ano;
  BoletimService bwebservice;
  LayoutInflater factory;
  ListView lista;
  List<ListasBean> listaInfos;
  View myView;
  ProgressDialog p;
  String pchave;
  String pnometurma;
  String prm;
  SharedPreferences sb;
  String[] turma;
  TurmaService twebservice;
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.twebservice = new TurmaService();
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.pnometurma = this.sb.getString("pnometurma", "");
    this.myView = posgraduacao();
    return this.myView;
  }
  
  public View posgraduacao()
  {
    this.factory = LayoutInflater.from(getActivity());
    this.myView = this.factory.inflate(2130903073, null);
    this.lista = ((ListView)this.myView.findViewById(2131427423));
    this.p = ProgressDialog.show(getActivity(), null, "Carregando Dados...", true);
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            localList = TrabalhosPosTurmaFragment.this.twebservice.turma(TrabalhosPosTurmaFragment.this.prm, TrabalhosPosTurmaFragment.this.pchave);
            TrabalhosPosTurmaFragment.this.listaInfos = new ArrayList();
            TrabalhosPosTurmaFragment.this.turma = new String[localList.size()];
            i = 0;
            int j = localList.size();
            if (i < j) {
              continue;
            }
          }
          catch (Exception localException)
          {
            List localList;
            int i;
            ListasBean localListasBean;
            continue;
          }
          TrabalhosPosTurmaFragment.this.p.dismiss();
          return;
          localListasBean = new ListasBean();
          localListasBean.setLinha1(((TurmaBean)localList.get(i)).getNomeTurma());
          localListasBean.setLinha2(((TurmaBean)localList.get(i)).getCurso());
          TrabalhosPosTurmaFragment.this.listaInfos.add(localListasBean);
          TrabalhosPosTurmaFragment.this.turma[i] = ((TurmaBean)localList.get(i)).getNomeTurma();
          TrabalhosPosTurmaFragment.this.ano[i] = ((TurmaBean)localList.get(i)).getAno();
          i += 1;
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (TrabalhosPosTurmaFragment.this.turma.length == 1)
        {
          paramAnonymousDialogInterface = new Intent(TrabalhosPosTurmaFragment.this.getActivity(), TrabalhosPosPager.class);
          paramAnonymousDialogInterface.putExtra("turma", TrabalhosPosTurmaFragment.this.turma[0]);
          TrabalhosPosTurmaFragment.this.startActivity(paramAnonymousDialogInterface);
        }
        paramAnonymousDialogInterface = new ListasAdapter(TrabalhosPosTurmaFragment.this.getActivity(), TrabalhosPosTurmaFragment.this.listaInfos);
        TrabalhosPosTurmaFragment.this.lista.setAdapter(paramAnonymousDialogInterface);
        TrabalhosPosTurmaFragment.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = new Intent(TrabalhosPosTurmaFragment.this.getActivity(), TrabalhosPosPager.class);
            paramAnonymous2AdapterView.putExtra("turma", TrabalhosPosTurmaFragment.this.turma[paramAnonymous2Int]);
            TrabalhosPosTurmaFragment.this.startActivity(paramAnonymous2AdapterView);
          }
        });
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\TrabalhosPosTurmaFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */