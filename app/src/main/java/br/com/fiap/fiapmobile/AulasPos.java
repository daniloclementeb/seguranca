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
import android.widget.TextView;
import br.com.fiap.beans.CalendarioPosGraduacaoBean;
import br.com.fiap.services.AulaService;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockActivity;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class AulasPos
  extends SherlockActivity
{
  String ano;
  TextView aula1;
  TextView aula2;
  TextView aviso;
  List<CalendarioPosGraduacaoBean> cgp;
  TextView diasemana;
  LayoutInflater factory;
  TextView horario1;
  TextView horario2;
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
  TextView title;
  String turma;
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
    this.webservice = new AulaService();
    this.p = ProgressDialog.show(this, null, "Carregando Dados...", true);
    paramBundle = getIntent();
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.pnome = this.sb.getString("pnome", "");
    this.pcurso = this.sb.getString("pcurso", "");
    this.pano = this.sb.getString("pano", "");
    this.turma = paramBundle.getStringExtra("turma");
    this.ano = paramBundle.getStringExtra("ano");
    new Thread()
    {
      public void run()
      {
        try
        {
          AulasPos.this.cgp = AulasPos.this.webservice.horariosPos(AulasPos.this.prm, AulasPos.this.turma, AulasPos.this.ano, AulasPos.this.pchave);
          AulasPos.this.p.dismiss();
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
        int i = 0;
        if (i >= AulasPos.this.cgp.size()) {
          return;
        }
        CalendarioPosGraduacaoBean localCalendarioPosGraduacaoBean = (CalendarioPosGraduacaoBean)AulasPos.this.cgp.get(i);
        if (localCalendarioPosGraduacaoBean.getCodrelacao() == 0)
        {
          localLinearLayout = (LinearLayout)AulasPos.this.findViewById(2131427414);
          localView = AulasPos.this.factory.inflate(2130903102, null);
          AulasPos.this.diasemana = ((TextView)localView.findViewById(2131427458));
          AulasPos.this.aviso = ((TextView)localView.findViewById(2131427460));
          localObject = new SimpleDateFormat("dd/MM/yyyy");
          paramAnonymousDialogInterface = null;
        }
        try
        {
          localObject = ((SimpleDateFormat)localObject).parse(localCalendarioPosGraduacaoBean.getDataaula());
          paramAnonymousDialogInterface = (DialogInterface)localObject;
        }
        catch (Exception localException2)
        {
          for (;;) {}
        }
        Object localObject = new GregorianCalendar();
        ((GregorianCalendar)localObject).setTime(paramAnonymousDialogInterface);
        switch (((GregorianCalendar)localObject).get(7))
        {
        }
        for (;;)
        {
          AulasPos.this.aviso.setText(localCalendarioPosGraduacaoBean.getNomedisciplina());
          localLinearLayout.addView(localView);
          i += 1;
          break;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Domingo");
          continue;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Segunda");
          continue;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Terça");
          continue;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Quarta");
          continue;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Quinta");
          continue;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Sexta");
          continue;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Sábado");
        }
        LinearLayout localLinearLayout = (LinearLayout)AulasPos.this.findViewById(2131427414);
        View localView = AulasPos.this.factory.inflate(2130903105, null);
        AulasPos.this.diasemana = ((TextView)localView.findViewById(2131427458));
        AulasPos.this.aula1 = ((TextView)localView.findViewById(2131427491));
        AulasPos.this.prof1 = ((TextView)localView.findViewById(2131427492));
        AulasPos.this.status = ((TextView)localView.findViewById(2131427500));
        AulasPos.this.horario1 = ((TextView)localView.findViewById(2131427493));
        AulasPos.this.sala1 = ((TextView)localView.findViewById(2131427494));
        AulasPos.this.horario1.setVisibility(8);
        if (localCalendarioPosGraduacaoBean != null)
        {
          localObject = new SimpleDateFormat("dd/MM/yyyy");
          paramAnonymousDialogInterface = null;
        }
        try
        {
          localObject = ((SimpleDateFormat)localObject).parse(localCalendarioPosGraduacaoBean.getDataaula());
          paramAnonymousDialogInterface = (DialogInterface)localObject;
        }
        catch (Exception localException1)
        {
          for (;;) {}
        }
        localObject = new GregorianCalendar();
        ((GregorianCalendar)localObject).setTime(paramAnonymousDialogInterface);
        switch (((GregorianCalendar)localObject).get(7))
        {
        default: 
          label708:
          AulasPos.this.aula1.setText(localCalendarioPosGraduacaoBean.getNomedisciplina());
          AulasPos.this.prof1.setText(localCalendarioPosGraduacaoBean.getProfessor());
          if (localCalendarioPosGraduacaoBean.getCancelada())
          {
            AulasPos.this.status.setText("Cancelada");
            AulasPos.this.horario1.setVisibility(8);
            AulasPos.this.sala1.setVisibility(8);
            label782:
            if ((!localCalendarioPosGraduacaoBean.getHorainicio().equalsIgnoreCase("null")) && (!localCalendarioPosGraduacaoBean.getHorafim().equalsIgnoreCase("null"))) {
              break label1195;
            }
            AulasPos.this.horario1.setText("-");
            label820:
            if ((!localCalendarioPosGraduacaoBean.getPredio().equalsIgnoreCase("null")) || (localCalendarioPosGraduacaoBean.getSala().equalsIgnoreCase("null"))) {
              break label1239;
            }
            AulasPos.this.sala1.setText(localCalendarioPosGraduacaoBean.getSala());
          }
          break;
        }
        for (;;)
        {
          localLinearLayout.addView(localView);
          break;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Domingo");
          break label708;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Segunda");
          break label708;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Terça");
          break label708;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Quarta");
          break label708;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Quinta");
          break label708;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Sexta");
          break label708;
          AulasPos.this.diasemana.setText(localCalendarioPosGraduacaoBean.getDataaula() + " - Sábado");
          break label708;
          if (localCalendarioPosGraduacaoBean.getRemarcada())
          {
            AulasPos.this.status.setText("Reposição");
            AulasPos.this.status.setBackgroundColor(AulasPos.this.getResources().getColor(2131165233));
            AulasPos.this.horario1.setVisibility(8);
            break label782;
          }
          AulasPos.this.status.setVisibility(8);
          break label782;
          label1195:
          AulasPos.this.horario1.setText(localCalendarioPosGraduacaoBean.getHorainicio() + " - " + localCalendarioPosGraduacaoBean.getHorafim());
          break label820;
          label1239:
          if ((!localCalendarioPosGraduacaoBean.getPredio().equalsIgnoreCase("null")) && (!localCalendarioPosGraduacaoBean.getSala().equalsIgnoreCase("null"))) {
            AulasPos.this.sala1.setText(localCalendarioPosGraduacaoBean.getSala() + " - " + "Unidade " + localCalendarioPosGraduacaoBean.getPredio());
          }
        }
      }
    });
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\AulasPos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */