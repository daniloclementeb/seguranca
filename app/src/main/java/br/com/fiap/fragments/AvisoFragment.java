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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fiap.beans.AvisoBean;
import br.com.fiap.fiapmobile.AvisoItem;
import br.com.fiap.layouts.AvisoAdapter;
import br.com.fiap.services.AvisoService;
import java.util.Iterator;
import java.util.List;

public class AvisoFragment
  extends Fragment
{
  AvisoAdapter adapterAviso;
  List<AvisoBean> avisos;
  View child;
  LayoutInflater factory;
  ListView lista;
  TextView mensagem;
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
  AvisoService webservice;
  
  public void atualizar(int paramInt)
  {
    Object localObject = new AvisoBean();
    int j = 0;
    int i = 0;
    Iterator localIterator = this.avisos.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        this.adapterAviso.replace(j, (AvisoBean)localObject);
        return;
      }
      AvisoBean localAvisoBean = (AvisoBean)localIterator.next();
      if (localAvisoBean.getIdaviso() == paramInt)
      {
        j = i;
        localObject = localAvisoBean;
        ((AvisoBean)localObject).setStatus(1);
      }
      i += 1;
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.factory = LayoutInflater.from(getActivity());
    this.myView = this.factory.inflate(2130903084, null);
    this.p = ProgressDialog.show(getActivity(), null, "Carregando Dados...", true);
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.webservice = new AvisoService();
    new Thread()
    {
      public void run()
      {
        try
        {
          AvisoFragment.this.avisos = AvisoFragment.this.webservice.aviso(AvisoFragment.this.prm, AvisoFragment.this.pchave);
          AvisoFragment.this.p.dismiss();
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
        if (AvisoFragment.this.avisos.size() == 0)
        {
          paramAnonymousDialogInterface = (LinearLayout)AvisoFragment.this.myView.findViewById(2131427461);
          AvisoFragment.this.child = AvisoFragment.this.factory.inflate(2130903117, null);
          AvisoFragment.this.mensagem = ((TextView)AvisoFragment.this.child.findViewById(2131427570));
          AvisoFragment.this.mensagem.setText("Aluno não tem nenhum aviso!");
          paramAnonymousDialogInterface.addView(AvisoFragment.this.child);
          return;
        }
        paramAnonymousDialogInterface = (LinearLayout)AvisoFragment.this.myView.findViewById(2131427461);
        paramAnonymousDialogInterface.setGravity(48);
        AvisoFragment.this.child = AvisoFragment.this.factory.inflate(2130903069, null);
        AvisoFragment.this.lista = ((ListView)AvisoFragment.this.child.findViewById(2131427419));
        AvisoFragment.this.adapterAviso = new AvisoAdapter(AvisoFragment.this.getActivity(), AvisoFragment.this.avisos);
        AvisoFragment.this.lista.setAdapter(AvisoFragment.this.adapterAviso);
        AvisoFragment.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
          {
            paramAnonymous2AdapterView = (AvisoBean)AvisoFragment.this.lista.getItemAtPosition(paramAnonymous2Int);
            paramAnonymous2View = new Intent(AvisoFragment.this.getActivity(), AvisoItem.class);
            paramAnonymous2View.putExtra("texto", paramAnonymous2AdapterView.getTexto());
            paramAnonymous2View.putExtra("titulo", paramAnonymous2AdapterView.getTitulo());
            paramAnonymous2View.putExtra("data", paramAnonymous2AdapterView.getData());
            paramAnonymous2View.putExtra("id", paramAnonymous2AdapterView.getIdaviso());
            paramAnonymous2View.putExtra("lido", paramAnonymous2AdapterView.getStatus());
            if (paramAnonymous2AdapterView.getStatus() == 0) {
              AvisoFragment.this.atualizar(paramAnonymous2AdapterView.getIdaviso());
            }
            AvisoFragment.this.startActivity(paramAnonymous2View);
          }
        });
        paramAnonymousDialogInterface.addView(AvisoFragment.this.child);
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\AvisoFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */