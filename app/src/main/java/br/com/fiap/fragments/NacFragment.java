package br.com.fiap.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.beans.NacBean;
import br.com.fiap.services.AvaliacaoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class NacFragment
  extends Fragment
{
  TextView data;
  LayoutInflater factory;
  List<NacBean> ln;
  TextView materia;
  TextView mensagem;
  View myView;
  ProgressDialog p;
  String pchave;
  String prm;
  SharedPreferences sb;
  TextView tema;
  TextView tipo;
  AvaliacaoService webservice;
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.factory = LayoutInflater.from(getActivity());
    this.myView = this.factory.inflate(2130903093, null);
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.p = ProgressDialog.show(getActivity(), null, "Carregando Dados...", true);
    new Thread()
    {
      public void run()
      {
        try
        {
          NacFragment.this.webservice = new AvaliacaoService();
          NacFragment.this.ln = NacFragment.this.webservice.nacs(NacFragment.this.prm, NacFragment.this.pchave);
          NacFragment.this.p.dismiss();
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
        try
        {
          if (NacFragment.this.ln.size() != 0) {
            break label479;
          }
          paramAnonymousDialogInterface = (LinearLayout)NacFragment.this.myView.findViewById(2131427476);
          localObject = NacFragment.this.factory.inflate(2130903117, null);
          NacFragment.this.mensagem = ((TextView)((View)localObject).findViewById(2131427570));
          NacFragment.this.mensagem.setText("Não há nenhum calendário cadastrado");
          ((View)localObject).setPadding(0, 200, 0, 0);
          paramAnonymousDialogInterface.addView((View)localObject);
          return;
        }
        catch (Exception paramAnonymousDialogInterface)
        {
          Object localObject;
          LinearLayout localLinearLayout;
          View localView;
          NacBean localNacBean;
          Toast.makeText(NacFragment.this.getActivity(), "Não foi possível carregar os dados! Verifique sua conexão!", 1).show();
          NacFragment.this.getActivity().finish();
          return;
        }
        if (i < NacFragment.this.ln.size())
        {
          localLinearLayout = (LinearLayout)NacFragment.this.myView.findViewById(2131427476);
          localView = NacFragment.this.factory.inflate(2130903108, null);
          NacFragment.this.materia = ((TextView)localView.findViewById(2131427505));
          NacFragment.this.tema = ((TextView)localView.findViewById(2131427507));
          NacFragment.this.data = ((TextView)localView.findViewById(2131427510));
          NacFragment.this.tipo = ((TextView)localView.findViewById(2131427513));
          new NacBean();
          localNacBean = (NacBean)NacFragment.this.ln.get(i);
          localObject = new SimpleDateFormat("dd/MM/yyyy");
          paramAnonymousDialogInterface = null;
          try
          {
            localObject = ((SimpleDateFormat)localObject).parse(localNacBean.getData());
            paramAnonymousDialogInterface = (DialogInterface)localObject;
          }
          catch (ParseException localParseException)
          {
            for (;;)
            {
              int j;
              localParseException.printStackTrace();
            }
          }
          localObject = new GregorianCalendar();
          ((GregorianCalendar)localObject).setTime(paramAnonymousDialogInterface);
          j = ((GregorianCalendar)localObject).get(7);
          paramAnonymousDialogInterface = "";
          switch (j)
          {
          }
        }
        for (;;)
        {
          NacFragment.this.materia.setText(localNacBean.getDisciplina());
          NacFragment.this.tema.setText(localNacBean.getTema());
          NacFragment.this.data.setText(localNacBean.getData() + " - " + paramAnonymousDialogInterface);
          NacFragment.this.tipo.setText(localNacBean.getTipo());
          localLinearLayout.addView(localView);
          i += 1;
          break;
          paramAnonymousDialogInterface = "Domingo";
          continue;
          paramAnonymousDialogInterface = "Sábado";
          continue;
          return;
          label479:
          i = 0;
          break;
          continue;
          paramAnonymousDialogInterface = "Segunda";
          continue;
          paramAnonymousDialogInterface = "Terça";
          continue;
          paramAnonymousDialogInterface = "Quarta";
          continue;
          paramAnonymousDialogInterface = "Quinta";
          continue;
          paramAnonymousDialogInterface = "Sexta";
        }
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\NacFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */