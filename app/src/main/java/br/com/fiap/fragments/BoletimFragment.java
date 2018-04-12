package br.com.fiap.fragments;

import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fiap.beans.BoletimPosBean;
import br.com.fiap.beans.ListasBean;
import br.com.fiap.beans.TurmaBean;
import br.com.fiap.fiapmobile.BoletimPager;
import br.com.fiap.fiapmobile.BoletimPos;
import br.com.fiap.layouts.ListasAdapter;
import br.com.fiap.services.BoletimService;
import br.com.fiap.services.TurmaService;
import java.util.ArrayList;
import java.util.List;

public class BoletimFragment
  extends Fragment
{
  String[] ano;
  TextView ar;
  Double artigo;
  BoletimService bwebservice;
  Dialog custom;
  TextView disciplina;
  TextView exa;
  LayoutInflater factory;
  ListView lista;
  List<ListasBean> listaInfos;
  List<BoletimPosBean> listanotas;
  LinearLayout[] llfa;
  Double media;
  TextView mf;
  TextView mp;
  View myView;
  ProgressDialog p;
  String pano;
  String pchave;
  String pcurso;
  String pnome;
  String pnometurma;
  TextView pr;
  String prm;
  String ptipo;
  TextView rodape;
  SharedPreferences sb;
  TextView status;
  String[] turma;
  TurmaService twebservice;
  
  public View graduacao()
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
            localList = BoletimFragment.this.twebservice.turma(BoletimFragment.this.prm, BoletimFragment.this.pchave);
            BoletimFragment.this.listaInfos = new ArrayList();
            BoletimFragment.this.turma = new String[localList.size()];
            BoletimFragment.this.ano = new String[localList.size()];
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
          BoletimFragment.this.p.dismiss();
          return;
          localListasBean = new ListasBean();
          localListasBean.setLinha1(((TurmaBean)localList.get(i)).getNomeTurma() + " - " + ((TurmaBean)localList.get(i)).getAno());
          localListasBean.setLinha2(((TurmaBean)localList.get(i)).getCurso());
          BoletimFragment.this.listaInfos.add(localListasBean);
          BoletimFragment.this.turma[i] = ((TurmaBean)localList.get(i)).getNomeTurma();
          BoletimFragment.this.ano[i] = ((TurmaBean)localList.get(i)).getAno();
          i += 1;
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (BoletimFragment.this.turma.length == 1)
        {
          paramAnonymousDialogInterface = new Intent(BoletimFragment.this.getActivity(), BoletimPager.class);
          paramAnonymousDialogInterface.putExtra("turma", BoletimFragment.this.turma[0]);
          paramAnonymousDialogInterface.putExtra("ano", BoletimFragment.this.ano[0]);
          BoletimFragment.this.startActivity(paramAnonymousDialogInterface);
        }
        paramAnonymousDialogInterface = new ListasAdapter(BoletimFragment.this.getActivity(), BoletimFragment.this.listaInfos);
        BoletimFragment.this.lista.setAdapter(paramAnonymousDialogInterface);
        BoletimFragment.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = new Intent(BoletimFragment.this.getActivity(), BoletimPager.class);
            paramAnonymous2AdapterView.putExtra("turma", BoletimFragment.this.turma[paramAnonymous2Int]);
            paramAnonymous2AdapterView.putExtra("ano", BoletimFragment.this.ano[paramAnonymous2Int]);
            BoletimFragment.this.startActivity(paramAnonymous2AdapterView);
          }
        });
      }
    });
    return this.myView;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.twebservice = new TurmaService();
    this.bwebservice = new BoletimService();
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.pnometurma = this.sb.getString("pnometurma", "");
    this.ptipo = this.sb.getString("ptipo", "");
    if (this.ptipo.equalsIgnoreCase("G")) {
      this.myView = graduacao();
    }
    for (;;)
    {
      return this.myView;
      if (this.ptipo.equalsIgnoreCase("P")) {
        this.myView = posgraduacao();
      }
    }
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
            localList = BoletimFragment.this.twebservice.turma(BoletimFragment.this.prm, BoletimFragment.this.pchave);
            BoletimFragment.this.listaInfos = new ArrayList();
            BoletimFragment.this.turma = new String[localList.size()];
            BoletimFragment.this.ano = new String[localList.size()];
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
          BoletimFragment.this.p.dismiss();
          return;
          localListasBean = new ListasBean();
          localListasBean.setLinha1(((TurmaBean)localList.get(i)).getNomeTurma());
          localListasBean.setLinha2(((TurmaBean)localList.get(i)).getCurso());
          BoletimFragment.this.listaInfos.add(localListasBean);
          BoletimFragment.this.turma[i] = ((TurmaBean)localList.get(i)).getNomeTurma();
          BoletimFragment.this.ano[i] = ((TurmaBean)localList.get(i)).getAno();
          i += 1;
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        paramAnonymousDialogInterface = new Intent(BoletimFragment.this.getActivity(), BoletimPos.class);
        paramAnonymousDialogInterface.putExtra("turma", BoletimFragment.this.turma[0]);
        paramAnonymousDialogInterface.putExtra("ano", BoletimFragment.this.ano[0]);
        BoletimFragment.this.startActivity(paramAnonymousDialogInterface);
        paramAnonymousDialogInterface = new ListasAdapter(BoletimFragment.this.getActivity(), BoletimFragment.this.listaInfos);
        BoletimFragment.this.lista.setAdapter(paramAnonymousDialogInterface);
        BoletimFragment.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = new Intent(BoletimFragment.this.getActivity(), BoletimPos.class);
            paramAnonymous2AdapterView.putExtra("turma", BoletimFragment.this.turma[paramAnonymous2Int]);
            paramAnonymous2AdapterView.putExtra("ano", BoletimFragment.this.ano[paramAnonymous2Int]);
            BoletimFragment.this.startActivity(paramAnonymous2AdapterView);
          }
        });
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\BoletimFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */