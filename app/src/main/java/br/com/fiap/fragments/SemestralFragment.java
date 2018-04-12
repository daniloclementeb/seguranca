package br.com.fiap.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.beans.AvaliacaoBean;
import br.com.fiap.services.AvaliacaoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"ValidFragment"})
public class SemestralFragment
  extends Fragment
{
  List<AvaliacaoBean> ab;
  TextView data;
  Boolean exame = Boolean.valueOf(false);
  LayoutInflater factory;
  TextView materia;
  TextView mensagem;
  View myView;
  ProgressDialog p;
  String pchave;
  String prm;
  Boolean ps = Boolean.valueOf(false);
  SharedPreferences sb;
  Boolean sub = Boolean.valueOf(false);
  int tela;
  TextView tipo;
  AvaliacaoService webservice;
  
  public SemestralFragment(int paramInt)
  {
    this.tela = paramInt;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.factory = LayoutInflater.from(getActivity());
    this.myView = this.factory.inflate(2130903067, null);
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.p = ProgressDialog.show(getActivity(), null, String.valueOf(getResources().getText(2131099724)), true);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    new Thread()
    {
      public void run()
      {
        try
        {
          SemestralFragment.this.webservice = new AvaliacaoService();
          SemestralFragment.this.ab = SemestralFragment.this.webservice.avaliacoes(SemestralFragment.this.prm, SemestralFragment.this.pchave);
          SemestralFragment.this.p.dismiss();
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
        Object localObject1;
        for (;;)
        {
          try
          {
            paramAnonymousDialogInterface = SemestralFragment.this.ab.iterator();
            if (!paramAnonymousDialogInterface.hasNext())
            {
              if (SemestralFragment.this.ab.size() != 0) {
                break;
              }
              paramAnonymousDialogInterface = (LinearLayout)SemestralFragment.this.myView.findViewById(2131427417);
              localObject1 = SemestralFragment.this.factory.inflate(2130903117, null);
              SemestralFragment.this.mensagem = ((TextView)((View)localObject1).findViewById(2131427570));
              SemestralFragment.this.mensagem.setText(SemestralFragment.this.getResources().getText(2131099735));
              ((View)localObject1).setPadding(0, 200, 0, 0);
              paramAnonymousDialogInterface.addView((View)localObject1);
              return;
            }
            localObject1 = (AvaliacaoBean)paramAnonymousDialogInterface.next();
            if ((((AvaliacaoBean)localObject1).getNome().equalsIgnoreCase(String.valueOf(SemestralFragment.this.getResources().getText(2131099738)))) || (((AvaliacaoBean)localObject1).getNome().equalsIgnoreCase(String.valueOf(SemestralFragment.this.getResources().getText(2131099739)))))
            {
              SemestralFragment.this.ps = Boolean.valueOf(true);
              continue;
            }
            if (((AvaliacaoBean)localObject1).getNome().equalsIgnoreCase(String.valueOf(SemestralFragment.this.getResources().getText(2131099740)))) {
              break label295;
            }
          }
          catch (Exception paramAnonymousDialogInterface)
          {
            Toast.makeText(SemestralFragment.this.getActivity(), String.valueOf(SemestralFragment.this.getResources().getText(2131099733)), 1).show();
            SemestralFragment.this.getActivity().finish();
            return;
          }
          if (((AvaliacaoBean)localObject1).getNome().equalsIgnoreCase(String.valueOf(SemestralFragment.this.getResources().getText(2131099741)))) {
            label295:
            SemestralFragment.this.sub = Boolean.valueOf(true);
          } else if (((AvaliacaoBean)localObject1).getNome().equalsIgnoreCase("EXAME")) {
            SemestralFragment.this.exame = Boolean.valueOf(true);
          }
        }
        if ((!SemestralFragment.this.ps.booleanValue()) && (SemestralFragment.this.tela == 1))
        {
          paramAnonymousDialogInterface = (LinearLayout)SemestralFragment.this.myView.findViewById(2131427417);
          localObject1 = SemestralFragment.this.factory.inflate(2130903117, null);
          SemestralFragment.this.mensagem = ((TextView)((View)localObject1).findViewById(2131427570));
          SemestralFragment.this.mensagem.setText(SemestralFragment.this.getResources().getText(2131099735));
          ((View)localObject1).setPadding(0, 200, 0, 0);
          paramAnonymousDialogInterface.addView((View)localObject1);
        }
        if ((!SemestralFragment.this.sub.booleanValue()) && (SemestralFragment.this.tela == 2))
        {
          paramAnonymousDialogInterface = (LinearLayout)SemestralFragment.this.myView.findViewById(2131427417);
          localObject1 = SemestralFragment.this.factory.inflate(2130903117, null);
          SemestralFragment.this.mensagem = ((TextView)((View)localObject1).findViewById(2131427570));
          SemestralFragment.this.mensagem.setText(SemestralFragment.this.getResources().getText(2131099735));
          ((View)localObject1).setPadding(0, 200, 0, 0);
          paramAnonymousDialogInterface.addView((View)localObject1);
        }
        int i;
        LinearLayout localLinearLayout;
        View localView;
        AvaliacaoBean localAvaliacaoBean;
        if ((!SemestralFragment.this.exame.booleanValue()) && (SemestralFragment.this.tela == 3))
        {
          paramAnonymousDialogInterface = (LinearLayout)SemestralFragment.this.myView.findViewById(2131427417);
          localObject1 = SemestralFragment.this.factory.inflate(2130903117, null);
          SemestralFragment.this.mensagem = ((TextView)((View)localObject1).findViewById(2131427570));
          SemestralFragment.this.mensagem.setText(SemestralFragment.this.getResources().getText(2131099735));
          ((View)localObject1).setPadding(0, 200, 0, 0);
          paramAnonymousDialogInterface.addView((View)localObject1);
          break label2197;
          if (i < SemestralFragment.this.ab.size())
          {
            localLinearLayout = (LinearLayout)SemestralFragment.this.myView.findViewById(2131427417);
            int j;
            if ((SemestralFragment.this.tela == 1) && (SemestralFragment.this.ps.booleanValue()))
            {
              if ((!((AvaliacaoBean)SemestralFragment.this.ab.get(i)).getNome().equalsIgnoreCase(String.valueOf(SemestralFragment.this.getResources().getText(2131099738)))) && (!((AvaliacaoBean)SemestralFragment.this.ab.get(i)).getNome().equalsIgnoreCase(String.valueOf(SemestralFragment.this.getResources().getText(2131099739))))) {
                break label2205;
              }
              localView = SemestralFragment.this.factory.inflate(2130903109, null);
              SemestralFragment.this.materia = ((TextView)localView.findViewById(2131427505));
              SemestralFragment.this.data = ((TextView)localView.findViewById(2131427510));
              SemestralFragment.this.tipo = ((TextView)localView.findViewById(2131427513));
              new AvaliacaoBean();
              localAvaliacaoBean = (AvaliacaoBean)SemestralFragment.this.ab.get(i);
              localObject1 = new SimpleDateFormat("dd/MM/yyyy");
              paramAnonymousDialogInterface = null;
              try
              {
                localObject1 = ((SimpleDateFormat)localObject1).parse(localAvaliacaoBean.getData());
                paramAnonymousDialogInterface = (DialogInterface)localObject1;
              }
              catch (ParseException localParseException1)
              {
                for (;;)
                {
                  localParseException1.printStackTrace();
                  continue;
                  paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099725));
                  continue;
                  paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099726));
                  continue;
                  paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099727, "UTF-8"));
                  continue;
                  paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099728));
                  continue;
                  paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099729));
                  continue;
                  paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099730));
                  continue;
                  paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099731));
                }
              }
              localObject1 = new GregorianCalendar();
              ((GregorianCalendar)localObject1).setTime(paramAnonymousDialogInterface);
              j = ((GregorianCalendar)localObject1).get(7);
              paramAnonymousDialogInterface = "";
            }
            switch (j)
            {
            case 1: 
              SemestralFragment.this.materia.setText(localAvaliacaoBean.getDiscilplina());
              SemestralFragment.this.data.setText(localAvaliacaoBean.getData() + " - " + paramAnonymousDialogInterface);
              SemestralFragment.this.tipo.setText(localAvaliacaoBean.getNome());
              localLinearLayout.addView(localView);
              break;
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
              label1000:
              if ((SemestralFragment.this.tela == 2) && (SemestralFragment.this.sub.booleanValue()))
              {
                if ((!((AvaliacaoBean)SemestralFragment.this.ab.get(i)).getNome().equalsIgnoreCase(String.valueOf(SemestralFragment.this.getResources().getText(2131099740)))) && (!((AvaliacaoBean)SemestralFragment.this.ab.get(i)).getNome().equalsIgnoreCase(String.valueOf(SemestralFragment.this.getResources().getText(2131099741))))) {
                  break label2205;
                }
                localView = SemestralFragment.this.factory.inflate(2130903109, null);
                SemestralFragment.this.materia = ((TextView)localView.findViewById(2131427505));
                SemestralFragment.this.data = ((TextView)localView.findViewById(2131427510));
                SemestralFragment.this.tipo = ((TextView)localView.findViewById(2131427513));
                new AvaliacaoBean();
                localAvaliacaoBean = (AvaliacaoBean)SemestralFragment.this.ab.get(i);
                Object localObject2 = new SimpleDateFormat("dd/MM/yyyy");
                paramAnonymousDialogInterface = null;
                try
                {
                  localObject2 = ((SimpleDateFormat)localObject2).parse(localAvaliacaoBean.getData());
                  paramAnonymousDialogInterface = (DialogInterface)localObject2;
                }
                catch (ParseException localParseException2)
                {
                  for (;;)
                  {
                    localParseException2.printStackTrace();
                    continue;
                    paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099725));
                    continue;
                    paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099726));
                    continue;
                    paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099727));
                    continue;
                    paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099728));
                    continue;
                    paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099729));
                    continue;
                    paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099730));
                    continue;
                    paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099731));
                  }
                }
                localObject2 = new GregorianCalendar();
                ((GregorianCalendar)localObject2).setTime(paramAnonymousDialogInterface);
                j = ((GregorianCalendar)localObject2).get(7);
                paramAnonymousDialogInterface = "";
              }
              switch (j)
              {
              case 1: 
                SemestralFragment.this.materia.setText(localAvaliacaoBean.getDiscilplina());
                SemestralFragment.this.data.setText(localAvaliacaoBean.getData() + " - " + paramAnonymousDialogInterface);
                SemestralFragment.this.tipo.setText(localAvaliacaoBean.getNome());
                localLinearLayout.addView(localView);
                break;
              case 2: 
              case 3: 
              case 4: 
              case 5: 
              case 6: 
              case 7: 
                label1516:
                if ((SemestralFragment.this.tela == 3) && (SemestralFragment.this.exame.booleanValue()) && (((AvaliacaoBean)SemestralFragment.this.ab.get(i)).getNome().equalsIgnoreCase("Exame")))
                {
                  localView = SemestralFragment.this.factory.inflate(2130903109, null);
                  SemestralFragment.this.materia = ((TextView)localView.findViewById(2131427505));
                  SemestralFragment.this.data = ((TextView)localView.findViewById(2131427510));
                  SemestralFragment.this.tipo = ((TextView)localView.findViewById(2131427513));
                  new AvaliacaoBean();
                  localAvaliacaoBean = (AvaliacaoBean)SemestralFragment.this.ab.get(i);
                  Object localObject3 = new SimpleDateFormat("dd/MM/yyyy");
                  paramAnonymousDialogInterface = null;
                  try
                  {
                    localObject3 = ((SimpleDateFormat)localObject3).parse(localAvaliacaoBean.getData());
                    paramAnonymousDialogInterface = (DialogInterface)localObject3;
                  }
                  catch (ParseException localParseException3)
                  {
                    for (;;)
                    {
                      localParseException3.printStackTrace();
                      continue;
                      paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099725));
                      continue;
                      paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099726));
                      continue;
                      paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099727));
                      continue;
                      paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099728));
                      continue;
                      paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099729));
                      continue;
                      paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099730));
                      continue;
                      paramAnonymousDialogInterface = String.valueOf(SemestralFragment.this.getResources().getText(2131099731));
                    }
                  }
                  localObject3 = new GregorianCalendar();
                  ((GregorianCalendar)localObject3).setTime(paramAnonymousDialogInterface);
                  j = ((GregorianCalendar)localObject3).get(7);
                  paramAnonymousDialogInterface = "";
                  switch (j)
                  {
                  }
                }
                break;
              }
              break;
            }
          }
        }
        for (;;)
        {
          SemestralFragment.this.materia.setText(localAvaliacaoBean.getDiscilplina());
          SemestralFragment.this.data.setText(localAvaliacaoBean.getData() + " - " + paramAnonymousDialogInterface);
          SemestralFragment.this.tipo.setText(localAvaliacaoBean.getNome());
          localLinearLayout.addView(localView);
          break label2205;
          return;
          label2197:
          i = 0;
          break;
          break label1000;
          label2205:
          i += 1;
          break;
          break label1516;
        }
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\SemestralFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */