package br.com.fiap.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.fiap.beans.TrabalhoPosBean;
import br.com.fiap.beans.TrabalhosPos;
import br.com.fiap.services.TrabalhosPosService;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"ValidFragment"})
public class TrabalhosPosFragment
  extends Fragment
{
  Dialog custom;
  TextView disciplina;
  TextView dtentregue;
  TextView dtlimite;
  LayoutInflater factory;
  View myView;
  TextView nota;
  ProgressDialog p;
  String pchave;
  String pcurso;
  String prm;
  TextView professor;
  String ptipo;
  SharedPreferences sb;
  TrabalhosPos tb;
  int tipo;
  TextView titulo;
  TrabalhosPosService ts;
  String turma;
  
  public TrabalhosPosFragment(int paramInt, String paramString)
  {
    this.tipo = paramInt;
    this.turma = paramString;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.factory = LayoutInflater.from(getActivity());
    this.myView = this.factory.inflate(2130903119, null);
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.pcurso = this.sb.getString("pcurso", "");
    this.ts = new TrabalhosPosService();
    this.tb = new TrabalhosPos();
    this.p = ProgressDialog.show(getActivity(), null, getResources().getString(2131099724), true);
    new Thread()
    {
      public void run()
      {
        try
        {
          TrabalhosPosFragment.this.tb = TrabalhosPosFragment.this.ts.trabalhos(TrabalhosPosFragment.this.prm, TrabalhosPosFragment.this.pchave, TrabalhosPosFragment.this.turma);
          TrabalhosPosFragment.this.p.dismiss();
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
        paramAnonymousDialogInterface = (LinearLayout)TrabalhosPosFragment.this.myView.findViewById(2131427573);
        if (TrabalhosPosFragment.this.tipo == 1)
        {
          localIterator = TrabalhosPosFragment.this.tb.getPendentes().iterator();
          if (localIterator.hasNext()) {}
        }
        TrabalhoPosBean localTrabalhoPosBean;
        View localView;
        label288:
        label519:
        do
        {
          return;
          localTrabalhoPosBean = (TrabalhoPosBean)localIterator.next();
          localView = TrabalhosPosFragment.this.factory.inflate(2130903116, null);
          TrabalhosPosFragment.this.titulo = ((TextView)localView.findViewById(2131427554));
          TrabalhosPosFragment.this.dtlimite = ((TextView)localView.findViewById(2131427557));
          TrabalhosPosFragment.this.disciplina = ((TextView)localView.findViewById(2131427566));
          TrabalhosPosFragment.this.professor = ((TextView)localView.findViewById(2131427563));
          TrabalhosPosFragment.this.titulo.setText(Html.fromHtml(localTrabalhoPosBean.getTituloTrabalho()).toString());
          if (localTrabalhoPosBean.getDataAlterada() > 0) {
            TrabalhosPosFragment.this.dtlimite.setText(localTrabalhoPosBean.getDataEntrega() + " [A]");
          }
          for (;;)
          {
            TrabalhosPosFragment.this.disciplina.setText(localTrabalhoPosBean.getNomeDisciplina());
            TrabalhosPosFragment.this.professor.setText(localTrabalhoPosBean.getNomeProfessor());
            paramAnonymousDialogInterface.addView(localView);
            break;
            TrabalhosPosFragment.this.dtlimite.setText(localTrabalhoPosBean.getDataEntrega());
          }
          if (TrabalhosPosFragment.this.tipo == 2)
          {
            localIterator = TrabalhosPosFragment.this.tb.getEntregues().iterator();
            if (localIterator.hasNext())
            {
              localTrabalhoPosBean = (TrabalhoPosBean)localIterator.next();
              localView = TrabalhosPosFragment.this.factory.inflate(2130903115, null);
              TrabalhosPosFragment.this.titulo = ((TextView)localView.findViewById(2131427554));
              TrabalhosPosFragment.this.dtlimite = ((TextView)localView.findViewById(2131427557));
              TrabalhosPosFragment.this.dtentregue = ((TextView)localView.findViewById(2131427560));
              TrabalhosPosFragment.this.disciplina = ((TextView)localView.findViewById(2131427566));
              TrabalhosPosFragment.this.professor = ((TextView)localView.findViewById(2131427563));
              TrabalhosPosFragment.this.titulo.setText(Html.fromHtml(localTrabalhoPosBean.getTituloTrabalho()).toString());
              if (localTrabalhoPosBean.getDataAlterada() <= 0) {
                break label519;
              }
              TrabalhosPosFragment.this.dtlimite.setText(localTrabalhoPosBean.getDataEntrega() + " [A]");
            }
            for (;;)
            {
              TrabalhosPosFragment.this.disciplina.setText(localTrabalhoPosBean.getNomeDisciplina());
              TrabalhosPosFragment.this.professor.setText(localTrabalhoPosBean.getNomeProfessor());
              TrabalhosPosFragment.this.dtentregue.setText(localTrabalhoPosBean.getDataHoraEntregou());
              paramAnonymousDialogInterface.addView(localView);
              break label288;
              break;
              TrabalhosPosFragment.this.dtlimite.setText(localTrabalhoPosBean.getDataEntrega());
            }
          }
        } while (TrabalhosPosFragment.this.tipo != 3);
        Iterator localIterator = TrabalhosPosFragment.this.tb.getCorrigidos().iterator();
        label563:
        if (localIterator.hasNext())
        {
          localTrabalhoPosBean = (TrabalhoPosBean)localIterator.next();
          localView = TrabalhosPosFragment.this.factory.inflate(2130903114, null);
          TrabalhosPosFragment.this.titulo = ((TextView)localView.findViewById(2131427554));
          TrabalhosPosFragment.this.dtlimite = ((TextView)localView.findViewById(2131427557));
          TrabalhosPosFragment.this.dtentregue = ((TextView)localView.findViewById(2131427560));
          TrabalhosPosFragment.this.disciplina = ((TextView)localView.findViewById(2131427566));
          TrabalhosPosFragment.this.professor = ((TextView)localView.findViewById(2131427563));
          TrabalhosPosFragment.this.nota = ((TextView)localView.findViewById(2131427569));
          TrabalhosPosFragment.this.titulo.setText(Html.fromHtml(localTrabalhoPosBean.getTituloTrabalho()).toString());
          if (localTrabalhoPosBean.getDataAlterada() <= 0) {
            break label850;
          }
          TrabalhosPosFragment.this.dtlimite.setText(localTrabalhoPosBean.getDataEntrega() + " [A]");
          label760:
          TrabalhosPosFragment.this.disciplina.setText(localTrabalhoPosBean.getNomeDisciplina());
          TrabalhosPosFragment.this.professor.setText(localTrabalhoPosBean.getNomeProfessor());
          TrabalhosPosFragment.this.dtentregue.setText(localTrabalhoPosBean.getDataHoraEntregou());
          if (localTrabalhoPosBean.getNotaRevisada() <= 0) {
            break label867;
          }
          TrabalhosPosFragment.this.nota.setText(localTrabalhoPosBean.getNotaTrabalho() + " [M]");
        }
        for (;;)
        {
          paramAnonymousDialogInterface.addView(localView);
          break label563;
          break;
          label850:
          TrabalhosPosFragment.this.dtlimite.setText(localTrabalhoPosBean.getDataEntrega());
          break label760;
          label867:
          TrabalhosPosFragment.this.nota.setText(localTrabalhoPosBean.getNotaTrabalho());
        }
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\TrabalhosPosFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */