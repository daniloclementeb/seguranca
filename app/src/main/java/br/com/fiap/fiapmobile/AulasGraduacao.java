package br.com.fiap.fiapmobile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fiap.beans.AulaBean;
import br.com.fiap.beans.CalendarioGraduacaoBean;
import br.com.fiap.beans.HorarioBean;
import br.com.fiap.beans.ListasBean;
import br.com.fiap.services.AulaService;
import br.com.fiap.services.TurmaService;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockActivity;
import java.util.ArrayList;
import java.util.List;

public class AulasGraduacao
  extends SherlockActivity
{
  String ano;
  TextView aula1;
  TextView aula2;
  TextView aviso;
  List<CalendarioGraduacaoBean> cg;
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
  TextView sala1;
  TextView sala2;
  SharedPreferences sb;
  TextView status;
  TextView title;
  String turma;
  TurmaService twebservice;
  AulaService webservice;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903065);
    paramBundle = getLayoutInflater().inflate(2130903072, null);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setCustomView(paramBundle, new ActionBar.LayoutParams(-1, -1));
    localActionBar.setDisplayOptions(16);
    localActionBar.setDisplayUseLogoEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(false);
    localActionBar.setDisplayShowHomeEnabled(false);
    localActionBar.setDisplayHomeAsUpEnabled(false);
    this.title = ((TextView)findViewById(2131689472));
    this.title.setText("Aulas");
    this.sb = getSharedPreferences("user", 0);
    this.factory = LayoutInflater.from(this);
    this.p = ProgressDialog.show(this, null, String.valueOf(getResources().getText(2131099724)), true);
    paramBundle = getIntent();
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.pnome = this.sb.getString("pnome", "");
    this.pcurso = this.sb.getString("pcurso", "");
    this.pano = this.sb.getString("pano", "");
    this.turma = paramBundle.getStringExtra("turma");
    this.ano = paramBundle.getStringExtra("ano");
    this.webservice = new AulaService();
    new Thread()
    {
      public void run()
      {
        try
        {
          AulasGraduacao.this.cg = AulasGraduacao.this.webservice.horarios(AulasGraduacao.this.prm, AulasGraduacao.this.turma, AulasGraduacao.this.ano, AulasGraduacao.this.pchave);
          AulasGraduacao.this.p.dismiss();
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
        int i;
        if (AulasGraduacao.this.cg.size() == 0)
        {
          i = 2;
          if (i <= 7) {}
        }
        do
        {
          return;
          paramAnonymousDialogInterface = (LinearLayout)AulasGraduacao.this.findViewById(2131427414);
          localObject1 = AulasGraduacao.this.factory.inflate(2130903102, null);
          AulasGraduacao.this.diasemana = ((TextView)((View)localObject1).findViewById(2131427458));
          switch (i)
          {
          }
          for (;;)
          {
            AulasGraduacao.this.aviso = ((TextView)((View)localObject1).findViewById(2131427460));
            AulasGraduacao.this.aviso.setText(AulasGraduacao.this.getResources().getString(2131099732, new Object[] { "UTF-8" }));
            paramAnonymousDialogInterface.addView((View)localObject1);
            i += 1;
            break;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099725, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099726, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099727, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099728, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099729, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099730, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099731, new Object[] { "UTF-8" }));
          }
          i = 0;
        } while (i >= AulasGraduacao.this.cg.size());
        new CalendarioGraduacaoBean();
        paramAnonymousDialogInterface = (CalendarioGraduacaoBean)AulasGraduacao.this.cg.get(i);
        int j = paramAnonymousDialogInterface.getDiasemana();
        new ArrayList();
        paramAnonymousDialogInterface = paramAnonymousDialogInterface.getHorarios();
        if (paramAnonymousDialogInterface.size() == 2)
        {
          localObject2 = LayoutInflater.from(AulasGraduacao.this);
          localObject1 = (LinearLayout)AulasGraduacao.this.findViewById(2131427414);
          localObject2 = ((LayoutInflater)localObject2).inflate(2130903101, null);
          AulasGraduacao.this.diasemana = ((TextView)((View)localObject2).findViewById(2131427458));
          AulasGraduacao.this.aula1 = ((TextView)((View)localObject2).findViewById(2131427491));
          AulasGraduacao.this.aula2 = ((TextView)((View)localObject2).findViewById(2131427496));
          AulasGraduacao.this.prof1 = ((TextView)((View)localObject2).findViewById(2131427492));
          AulasGraduacao.this.prof2 = ((TextView)((View)localObject2).findViewById(2131427497));
          AulasGraduacao.this.horario1 = ((TextView)((View)localObject2).findViewById(2131427493));
          AulasGraduacao.this.horario2 = ((TextView)((View)localObject2).findViewById(2131427498));
          AulasGraduacao.this.sala1 = ((TextView)((View)localObject2).findViewById(2131427494));
          AulasGraduacao.this.sala2 = ((TextView)((View)localObject2).findViewById(2131427499));
          localAulaBean = (AulaBean)((HorarioBean)paramAnonymousDialogInterface.get(0)).getAula().get(0);
          paramAnonymousDialogInterface = (AulaBean)((HorarioBean)paramAnonymousDialogInterface.get(1)).getAula().get(0);
          AulasGraduacao.this.aula1.setText(localAulaBean.getNomedisciplina());
          AulasGraduacao.this.aula2.setText(paramAnonymousDialogInterface.getNomedisciplina());
          AulasGraduacao.this.prof1.setText(localAulaBean.getNomeprof());
          AulasGraduacao.this.prof2.setText(paramAnonymousDialogInterface.getNomeprof());
          AulasGraduacao.this.horario1.setText(localAulaBean.getHorainicio() + " - " + localAulaBean.getHorafim());
          AulasGraduacao.this.horario2.setText(paramAnonymousDialogInterface.getHorainicio() + " - " + paramAnonymousDialogInterface.getHorafim());
          if ((!localAulaBean.getPredio().equalsIgnoreCase("null")) || (!localAulaBean.getSala().equalsIgnoreCase("null"))) {
            AulasGraduacao.this.sala1.setText(localAulaBean.getSala() + " - " + "Unidade " + localAulaBean.getPredio());
          }
          if ((!paramAnonymousDialogInterface.getPredio().equalsIgnoreCase("null")) || (!paramAnonymousDialogInterface.getSala().equalsIgnoreCase("null"))) {
            AulasGraduacao.this.sala2.setText(paramAnonymousDialogInterface.getSala() + " - " + "Unidade " + paramAnonymousDialogInterface.getPredio());
          }
          switch (j)
          {
          default: 
            ((LinearLayout)localObject1).addView((View)localObject2);
          }
        }
        while (paramAnonymousDialogInterface.size() != 1) {
          for (;;)
          {
            AulaBean localAulaBean;
            i += 1;
            break;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099725, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099726, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099727, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099728, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099729, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099730, new Object[] { "UTF-8" }));
            continue;
            AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099731, new Object[] { "UTF-8" }));
          }
        }
        Object localObject1 = (LinearLayout)AulasGraduacao.this.findViewById(2131427414);
        Object localObject2 = AulasGraduacao.this.factory.inflate(2130903106, null);
        AulasGraduacao.this.diasemana = ((TextView)((View)localObject2).findViewById(2131427458));
        AulasGraduacao.this.aula1 = ((TextView)((View)localObject2).findViewById(2131427501));
        AulasGraduacao.this.prof1 = ((TextView)((View)localObject2).findViewById(2131427502));
        AulasGraduacao.this.horario1 = ((TextView)((View)localObject2).findViewById(2131427503));
        AulasGraduacao.this.sala1 = ((TextView)((View)localObject2).findViewById(2131427504));
        paramAnonymousDialogInterface = (AulaBean)((HorarioBean)paramAnonymousDialogInterface.get(0)).getAula().get(0);
        AulasGraduacao.this.aula1.setText(paramAnonymousDialogInterface.getNomedisciplina());
        AulasGraduacao.this.prof1.setText(paramAnonymousDialogInterface.getNomeprof());
        AulasGraduacao.this.horario1.setText(paramAnonymousDialogInterface.getHorainicio() + " - " + paramAnonymousDialogInterface.getHorafim());
        if ((!paramAnonymousDialogInterface.getPredio().equalsIgnoreCase("null")) || (!paramAnonymousDialogInterface.getSala().equalsIgnoreCase("null"))) {
          AulasGraduacao.this.sala1.setText(paramAnonymousDialogInterface.getSala() + " - " + "Unidade " + paramAnonymousDialogInterface.getPredio());
        }
        switch (j)
        {
        }
        for (;;)
        {
          ((LinearLayout)localObject1).addView((View)localObject2);
          break;
          AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099725, new Object[] { "UTF-8" }));
          continue;
          AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099726, new Object[] { "UTF-8" }));
          continue;
          AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099727, new Object[] { "UTF-8" }));
          continue;
          AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099728, new Object[] { "UTF-8" }));
          continue;
          AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099729, new Object[] { "UTF-8" }));
          continue;
          AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099730, new Object[] { "UTF-8" }));
          continue;
          AulasGraduacao.this.diasemana.setText(AulasGraduacao.this.getResources().getString(2131099731, new Object[] { "UTF-8" }));
        }
      }
    });
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\AulasGraduacao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */