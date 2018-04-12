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
import android.widget.TextView;
import br.com.fiap.beans.CalendarioGraduacaoBean;
import br.com.fiap.beans.CalendarioPosGraduacaoBean;
import br.com.fiap.beans.ListasBean;
import br.com.fiap.beans.TurmaBean;
import br.com.fiap.fiapmobile.AulasGraduacao;
import br.com.fiap.fiapmobile.AulasPos;
import br.com.fiap.layouts.ListasAdapter;
import br.com.fiap.services.AulaService;
import br.com.fiap.services.TurmaService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AulasFragment
  extends Fragment
{
  String[] ano;
  TextView aula1;
  TextView aula2;
  TextView aviso;
  List<CalendarioGraduacaoBean> cg;
  List<CalendarioPosGraduacaoBean> cgp;
  TextView diasemana;
  LayoutInflater factory;
  TextView horario1;
  TextView horario2;
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
  TextView prof1;
  TextView prof2;
  String ptipo;
  TextView sala1;
  TextView sala2;
  SharedPreferences sb;
  TextView status;
  String[] turma;
  TurmaService twebservice;
  AulaService webservice;
  
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
            localList = AulasFragment.this.twebservice.turma(AulasFragment.this.prm, AulasFragment.this.pchave);
            AulasFragment.this.listaInfos = new ArrayList();
            AulasFragment.this.turma = new String[localList.size()];
            AulasFragment.this.ano = new String[localList.size()];
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
          AulasFragment.this.p.dismiss();
          return;
          if (((TurmaBean)localList.get(i)).getAno().equalsIgnoreCase(String.valueOf(Calendar.getInstance().get(1))))
          {
            localListasBean = new ListasBean();
            localListasBean.setLinha1(((TurmaBean)localList.get(i)).getNomeTurma());
            localListasBean.setLinha2(((TurmaBean)localList.get(i)).getCurso());
            AulasFragment.this.listaInfos.add(localListasBean);
            AulasFragment.this.turma[i] = ((TurmaBean)localList.get(i)).getNomeTurma();
            AulasFragment.this.ano[i] = ((TurmaBean)localList.get(i)).getAno();
          }
          i += 1;
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (AulasFragment.this.listaInfos.size() == 1)
        {
          paramAnonymousDialogInterface = new Intent(AulasFragment.this.getActivity(), AulasGraduacao.class);
          paramAnonymousDialogInterface.putExtra("turma", AulasFragment.this.turma[0]);
          paramAnonymousDialogInterface.putExtra("ano", AulasFragment.this.ano[0]);
          AulasFragment.this.startActivity(paramAnonymousDialogInterface);
        }
        paramAnonymousDialogInterface = new ListasAdapter(AulasFragment.this.getActivity(), AulasFragment.this.listaInfos);
        AulasFragment.this.lista.setAdapter(paramAnonymousDialogInterface);
        AulasFragment.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = new Intent(AulasFragment.this.getActivity(), AulasGraduacao.class);
            paramAnonymous2AdapterView.putExtra("turma", AulasFragment.this.turma[paramAnonymous2Int]);
            paramAnonymous2AdapterView.putExtra("ano", AulasFragment.this.ano[paramAnonymous2Int]);
            AulasFragment.this.startActivity(paramAnonymous2AdapterView);
          }
        });
      }
    });
    return this.myView;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.twebservice = new TurmaService();
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
            localList = AulasFragment.this.twebservice.turma(AulasFragment.this.prm, AulasFragment.this.pchave);
            AulasFragment.this.listaInfos = new ArrayList();
            AulasFragment.this.turma = new String[localList.size()];
            AulasFragment.this.ano = new String[localList.size()];
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
          AulasFragment.this.p.dismiss();
          return;
          localListasBean = new ListasBean();
          localListasBean.setLinha1(((TurmaBean)localList.get(i)).getNomeTurma());
          localListasBean.setLinha2(((TurmaBean)localList.get(i)).getCurso());
          AulasFragment.this.listaInfos.add(localListasBean);
          AulasFragment.this.turma[i] = ((TurmaBean)localList.get(i)).getNomeTurma();
          AulasFragment.this.ano[i] = ((TurmaBean)localList.get(i)).getAno();
          i += 1;
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (AulasFragment.this.turma.length == 1)
        {
          paramAnonymousDialogInterface = new Intent(AulasFragment.this.getActivity(), AulasPos.class);
          paramAnonymousDialogInterface.putExtra("turma", AulasFragment.this.turma[0]);
          paramAnonymousDialogInterface.putExtra("ano", AulasFragment.this.ano[0]);
          AulasFragment.this.startActivity(paramAnonymousDialogInterface);
        }
        paramAnonymousDialogInterface = new ListasAdapter(AulasFragment.this.getActivity(), AulasFragment.this.listaInfos);
        AulasFragment.this.lista.setAdapter(paramAnonymousDialogInterface);
        AulasFragment.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = new Intent(AulasFragment.this.getActivity(), AulasPos.class);
            paramAnonymous2AdapterView.putExtra("turma", AulasFragment.this.turma[paramAnonymous2Int]);
            paramAnonymous2AdapterView.putExtra("ano", AulasFragment.this.ano[paramAnonymous2Int]);
            AulasFragment.this.startActivity(paramAnonymous2AdapterView);
          }
        });
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\AulasFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */