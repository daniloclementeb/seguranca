package br.com.fiap.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import br.com.fiap.beans.AulaBean;
import br.com.fiap.beans.CalendarioGraduacaoBean;
import br.com.fiap.beans.CalendarioPosGraduacaoBean;
import br.com.fiap.beans.HorarioBean;
import br.com.fiap.fiapmobile.LoginActivity;
import br.com.fiap.fiapmobile.PainelActivity;
import br.com.fiap.services.HomeService;
import br.com.fiap.tools.FiapUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class WidgetProvider
  extends AppWidgetProvider
{
  public static final String WIDGETTAG = "Widget";
  Integer appWidgetId;
  AppWidgetManager appWidgetManager;
  List<CalendarioGraduacaoBean> calendarioGrad;
  List<CalendarioPosGraduacaoBean> calendarioPosGrad;
  Context context;
  String diadasemana;
  String pano;
  String pchave;
  String pnometurma;
  String prm;
  String ptipo;
  SharedPreferences sb;
  RemoteViews views;
  HomeService webservice;
  
  public void onUpdate(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    super.onUpdate(paramContext, paramAppWidgetManager, paramArrayOfInt);
    this.appWidgetManager = paramAppWidgetManager;
    this.context = paramContext;
    this.sb = paramContext.getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.ptipo = this.sb.getString("ptipo", "");
    this.pnometurma = this.sb.getString("pnometurma", "");
    this.pano = this.sb.getString("pano", "");
    Log.i("TIPO", this.ptipo);
    Log.i("Widget", "onUpdate");
    int j = paramArrayOfInt.length;
    int i = 0;
    if (i >= j) {
      return;
    }
    this.appWidgetId = Integer.valueOf(paramArrayOfInt[i]);
    if ((this.prm.equalsIgnoreCase("")) || (this.pchave.equalsIgnoreCase("")))
    {
      this.views = new RemoteViews(paramContext.getPackageName(), 2130903125);
      Object localObject = new Intent(paramContext, LoginActivity.class);
      ((Intent)localObject).putExtra("appWidgetId", this.appWidgetId);
      localObject = PendingIntent.getActivity(paramContext, 1, (Intent)localObject, 0);
      this.views.setOnClickPendingIntent(2131427577, (PendingIntent)localObject);
      paramAppWidgetManager.updateAppWidget(this.appWidgetId.intValue(), this.views);
    }
    for (;;)
    {
      i += 1;
      break;
      if (FiapUtils.verificaConexao(paramContext))
      {
        new CalendarioTask(null).execute(new Integer[] { this.appWidgetId });
      }
      else
      {
        this.views = new RemoteViews(paramContext.getPackageName(), 2130903125);
        this.views.setTextViewText(2131427612, paramContext.getResources().getString(2131099742));
        paramAppWidgetManager.updateAppWidget(this.appWidgetId.intValue(), this.views);
      }
    }
  }
  
  private class CalendarioTask
    extends AsyncTask<Integer, Void, Integer>
  {
    private CalendarioTask() {}
    
    protected Integer doInBackground(Integer... paramVarArgs)
    {
      WidgetProvider.this.webservice = new HomeService();
      if (WidgetProvider.this.ptipo.equalsIgnoreCase("G")) {
        WidgetProvider.this.calendarioGrad = WidgetProvider.this.webservice.horarios(WidgetProvider.this.prm, WidgetProvider.this.pnometurma, WidgetProvider.this.pano, WidgetProvider.this.pchave);
      }
      for (;;)
      {
        return paramVarArgs[0];
        if (WidgetProvider.this.ptipo.equalsIgnoreCase("P")) {
          WidgetProvider.this.calendarioPosGrad = WidgetProvider.this.webservice.horariosPos(WidgetProvider.this.prm, WidgetProvider.this.pnometurma, WidgetProvider.this.pano, WidgetProvider.this.pchave);
        }
      }
    }
    
    protected void onPostExecute(Integer paramInteger)
    {
      int j = Calendar.getInstance().get(7);
      Object localObject1;
      int i;
      label93:
      Object localObject2;
      switch (j)
      {
      default: 
        if (WidgetProvider.this.ptipo.equalsIgnoreCase("G"))
        {
          localObject1 = new CalendarioGraduacaoBean();
          if (WidgetProvider.this.calendarioGrad.size() > 0)
          {
            i = 0;
            if (i >= WidgetProvider.this.calendarioGrad.size())
            {
              new ArrayList();
              localObject1 = ((CalendarioGraduacaoBean)localObject1).getHorarios();
              if (localObject1 != null)
              {
                if (((List)localObject1).size() != 2) {
                  break label870;
                }
                WidgetProvider.this.views = new RemoteViews(WidgetProvider.this.context.getPackageName(), 2130903123);
                localObject2 = (AulaBean)((HorarioBean)((List)localObject1).get(0)).getAula().get(0);
                localObject1 = (AulaBean)((HorarioBean)((List)localObject1).get(1)).getAula().get(0);
                WidgetProvider.this.views.setTextViewText(2131427590, WidgetProvider.this.diadasemana);
                WidgetProvider.this.views.setTextViewText(2131427592, ((AulaBean)localObject2).getNomedisciplina());
                WidgetProvider.this.views.setTextViewText(2131427597, ((AulaBean)localObject1).getNomedisciplina());
                WidgetProvider.this.views.setTextViewText(2131427593, ((AulaBean)localObject2).getNomeprof());
                WidgetProvider.this.views.setTextViewText(2131427598, ((AulaBean)localObject1).getNomeprof());
                WidgetProvider.this.views.setTextViewText(2131427594, ((AulaBean)localObject2).getHorainicio() + " - " + ((AulaBean)localObject2).getHorafim());
                WidgetProvider.this.views.setTextViewText(2131427599, ((AulaBean)localObject1).getHorainicio() + " - " + ((AulaBean)localObject1).getHorafim());
                if ((!((AulaBean)localObject2).getPredio().equalsIgnoreCase("null")) || (!((AulaBean)localObject2).getSala().equalsIgnoreCase("null"))) {
                  WidgetProvider.this.views.setTextViewText(2131427595, ((AulaBean)localObject2).getSala() + " - " + "Unidade " + ((AulaBean)localObject2).getPredio());
                }
                if ((!((AulaBean)localObject1).getPredio().equalsIgnoreCase("null")) || (!((AulaBean)localObject1).getSala().equalsIgnoreCase("null"))) {
                  WidgetProvider.this.views.setTextViewText(2131427600, ((AulaBean)localObject1).getSala() + " - " + "Unidade " + ((AulaBean)localObject1).getPredio());
                }
              }
            }
          }
        }
        break;
      }
      for (;;)
      {
        localObject1 = new Intent(WidgetProvider.this.context, PainelActivity.class);
        ((Intent)localObject1).putExtra("appWidgetId", WidgetProvider.this.appWidgetId);
        localObject1 = PendingIntent.getActivity(WidgetProvider.this.context, 1, (Intent)localObject1, 0);
        WidgetProvider.this.views.setOnClickPendingIntent(2131427577, (PendingIntent)localObject1);
        WidgetProvider.this.appWidgetManager.updateAppWidget(paramInteger.intValue(), WidgetProvider.this.views);
        return;
        WidgetProvider.this.diadasemana = String.valueOf(WidgetProvider.this.context.getResources().getText(2131099725));
        break;
        WidgetProvider.this.diadasemana = String.valueOf(WidgetProvider.this.context.getResources().getText(2131099726));
        break;
        WidgetProvider.this.diadasemana = String.valueOf(WidgetProvider.this.context.getResources().getText(2131099727, "UTF-8"));
        break;
        WidgetProvider.this.diadasemana = String.valueOf(WidgetProvider.this.context.getResources().getText(2131099728));
        break;
        WidgetProvider.this.diadasemana = String.valueOf(WidgetProvider.this.context.getResources().getText(2131099729));
        break;
        WidgetProvider.this.diadasemana = String.valueOf(WidgetProvider.this.context.getResources().getText(2131099730));
        break;
        WidgetProvider.this.diadasemana = String.valueOf(WidgetProvider.this.context.getResources().getText(2131099731));
        break;
        if (((CalendarioGraduacaoBean)WidgetProvider.this.calendarioGrad.get(i)).getDiasemana() == j) {
          localObject1 = (CalendarioGraduacaoBean)WidgetProvider.this.calendarioGrad.get(i);
        }
        i += 1;
        break label93;
        label870:
        if (((List)localObject1).size() == 1)
        {
          WidgetProvider.this.views = new RemoteViews(WidgetProvider.this.context.getPackageName(), 2130903122);
          localObject2 = (AulaBean)((HorarioBean)((List)localObject1).get(0)).getAula().get(0);
          localObject1 = (AulaBean)((HorarioBean)((List)localObject1).get(1)).getAula().get(0);
          WidgetProvider.this.views.setTextViewText(2131427583, WidgetProvider.this.diadasemana);
          WidgetProvider.this.views.setTextViewText(2131427585, ((AulaBean)localObject2).getNomedisciplina());
          WidgetProvider.this.views.setTextViewText(2131427586, ((AulaBean)localObject2).getNomeprof());
          WidgetProvider.this.views.setTextViewText(2131427587, ((AulaBean)localObject2).getHorainicio() + " - " + ((AulaBean)localObject2).getHorafim());
          if ((!((AulaBean)localObject2).getPredio().equalsIgnoreCase("null")) || (!((AulaBean)localObject2).getSala().equalsIgnoreCase("null"))) {
            WidgetProvider.this.views.setTextViewText(2131427588, ((AulaBean)localObject2).getSala() + " - " + "Unidade " + ((AulaBean)localObject2).getPredio());
          }
        }
        else
        {
          WidgetProvider.this.views = new RemoteViews(WidgetProvider.this.context.getPackageName(), 2130903121);
          WidgetProvider.this.views.setTextViewText(2131427579, WidgetProvider.this.diadasemana);
          WidgetProvider.this.views.setTextViewText(2131427581, WidgetProvider.this.context.getResources().getString(2131099732));
          continue;
          WidgetProvider.this.views = new RemoteViews(WidgetProvider.this.context.getPackageName(), 2130903121);
          WidgetProvider.this.views.setTextViewText(2131427579, WidgetProvider.this.diadasemana);
          WidgetProvider.this.views.setTextViewText(2131427581, WidgetProvider.this.context.getResources().getString(2131099732));
          continue;
          if (WidgetProvider.this.ptipo.equalsIgnoreCase("P"))
          {
            Object localObject4 = null;
            i = 0;
            label1314:
            if (i >= WidgetProvider.this.calendarioPosGrad.size())
            {
              localObject1 = localObject4;
              label1334:
              localObject2 = localObject1;
              if (localObject1 == null)
              {
                localObject2 = new CalendarioPosGraduacaoBean();
                ((CalendarioPosGraduacaoBean)localObject2).setCodrelacao(0);
                ((CalendarioPosGraduacaoBean)localObject2).setNomedisciplina(String.valueOf(WidgetProvider.this.context.getResources().getText(2131099734)));
                new Date();
                ((CalendarioPosGraduacaoBean)localObject2).setDataaula(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
              }
              if (((CalendarioPosGraduacaoBean)localObject2).getCodrelacao() != 0) {
                break label2178;
              }
              WidgetProvider.this.views = new RemoteViews(WidgetProvider.this.context.getPackageName(), 2130903121);
              localObject3 = new SimpleDateFormat("dd/MM/yyyy");
              localObject1 = null;
            }
          }
        }
        try
        {
          localObject3 = ((SimpleDateFormat)localObject3).parse(((CalendarioPosGraduacaoBean)localObject2).getDataaula());
          localObject1 = localObject3;
        }
        catch (Exception localException2)
        {
          for (;;) {}
        }
        Object localObject3 = new GregorianCalendar();
        ((GregorianCalendar)localObject3).setTime((Date)localObject1);
        switch (((GregorianCalendar)localObject3).get(7))
        {
        }
        for (;;)
        {
          WidgetProvider.this.views.setTextViewText(2131427581, ((CalendarioPosGraduacaoBean)localObject2).getNomedisciplina());
          break;
          localObject2 = ((CalendarioPosGraduacaoBean)WidgetProvider.this.calendarioPosGrad.get(i)).getDataaula();
          Object localObject5 = new SimpleDateFormat("dd/MM/yyyy");
          localObject3 = new Date();
          localObject1 = new Date();
          try
          {
            localObject2 = ((SimpleDateFormat)localObject5).parse((String)localObject2);
            localObject1 = localObject2;
            localObject5 = ((SimpleDateFormat)localObject5).parse(((SimpleDateFormat)localObject5).format((Date)localObject3));
            localObject3 = localObject5;
            localObject1 = localObject2;
            if (((Date)localObject3).equals(localObject1))
            {
              localObject1 = (CalendarioPosGraduacaoBean)WidgetProvider.this.calendarioPosGrad.get(i);
              Log.i("before", String.valueOf(i));
            }
          }
          catch (ParseException localParseException)
          {
            for (;;)
            {
              localParseException.printStackTrace();
            }
            if (((Date)localObject3).before((Date)localObject1))
            {
              localObject1 = (CalendarioPosGraduacaoBean)WidgetProvider.this.calendarioPosGrad.get(i);
              Log.i("equals", String.valueOf(i));
              break label1334;
            }
            i += 1;
          }
          break label1314;
          WidgetProvider.this.views.setTextViewText(2131427579, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099725)));
          continue;
          WidgetProvider.this.views.setTextViewText(2131427579, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099726)));
          continue;
          WidgetProvider.this.views.setTextViewText(2131427579, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099727)));
          continue;
          WidgetProvider.this.views.setTextViewText(2131427579, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099728)));
          continue;
          WidgetProvider.this.views.setTextViewText(2131427579, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099729)));
          continue;
          WidgetProvider.this.views.setTextViewText(2131427579, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099730)));
          continue;
          WidgetProvider.this.views.setTextViewText(2131427579, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099731)));
        }
        label2178:
        WidgetProvider.this.views = new RemoteViews(WidgetProvider.this.context.getPackageName(), 2130903124);
        WidgetProvider.this.views.setViewVisibility(2131427607, 8);
        if (localParseException != null)
        {
          localObject3 = new SimpleDateFormat("dd/MM/yyyy");
          localObject1 = null;
        }
        try
        {
          localObject3 = ((SimpleDateFormat)localObject3).parse(localParseException.getDataaula());
          localObject1 = localObject3;
        }
        catch (Exception localException1)
        {
          for (;;) {}
        }
        localObject3 = new GregorianCalendar();
        ((GregorianCalendar)localObject3).setTime((Date)localObject1);
        switch (((GregorianCalendar)localObject3).get(7))
        {
        default: 
          label2320:
          WidgetProvider.this.views.setTextViewText(2131427604, localParseException.getNomedisciplina());
          WidgetProvider.this.views.setTextViewText(2131427605, localParseException.getProfessor());
          if (localParseException.getCancelada())
          {
            WidgetProvider.this.views.setTextViewText(2131427606, WidgetProvider.this.context.getResources().getText(2131099736));
            WidgetProvider.this.views.setViewVisibility(2131427607, 8);
            WidgetProvider.this.views.setViewVisibility(2131427608, 8);
            label2423:
            if ((!localParseException.getHorainicio().equalsIgnoreCase("null")) && (!localParseException.getHorafim().equalsIgnoreCase("null"))) {
              break label3039;
            }
            WidgetProvider.this.views.setTextViewText(2131427607, "-");
          }
          break;
        }
        for (;;)
        {
          if ((!localParseException.getPredio().equalsIgnoreCase("null")) || (localParseException.getSala().equalsIgnoreCase("null"))) {
            break label3086;
          }
          WidgetProvider.this.views.setTextViewText(2131427608, localParseException.getSala());
          break;
          WidgetProvider.this.views.setTextViewText(2131427602, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099725)));
          break label2320;
          WidgetProvider.this.views.setTextViewText(2131427602, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099726)));
          break label2320;
          WidgetProvider.this.views.setTextViewText(2131427602, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099727)));
          break label2320;
          WidgetProvider.this.views.setTextViewText(2131427602, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099728)));
          break label2320;
          WidgetProvider.this.views.setTextViewText(2131427602, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099729)));
          break label2320;
          WidgetProvider.this.views.setTextViewText(2131427602, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099730)));
          break label2320;
          WidgetProvider.this.views.setTextViewText(2131427602, localParseException.getDataaula() + " - " + String.valueOf(WidgetProvider.this.context.getResources().getText(2131099731)));
          break label2320;
          if (localParseException.getRemarcada())
          {
            WidgetProvider.this.views.setTextViewText(2131427606, WidgetProvider.this.context.getResources().getText(2131099737));
            WidgetProvider.this.views.setInt(2131427606, "setBackgroundColor", WidgetProvider.this.context.getResources().getColor(2131165233));
            WidgetProvider.this.views.setViewVisibility(2131427608, 8);
            break label2423;
          }
          WidgetProvider.this.views.setViewVisibility(2131427606, 8);
          break label2423;
          label3039:
          WidgetProvider.this.views.setTextViewText(2131427607, localParseException.getHorainicio() + " - " + localParseException.getHorafim());
        }
        label3086:
        if ((!localParseException.getPredio().equalsIgnoreCase("null")) && (!localParseException.getSala().equalsIgnoreCase("null"))) {
          WidgetProvider.this.views.setTextViewText(2131427608, localParseException.getSala() + " - " + "Unidade " + localParseException.getPredio());
        }
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\widget\WidgetProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */