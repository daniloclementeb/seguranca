package br.com.fiap.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.fiap.beans.AvaliacaoBean;
import br.com.fiap.beans.NacBean;
import br.com.fiap.layouts.AvaliacoesAdapter;
import br.com.fiap.layouts.ToastManager;
import br.com.fiap.services.AvaliacaoService;
import java.util.List;

public class AvaliacaoesFragment
  extends Fragment
{
  List<AvaliacaoBean> ab;
  LayoutInflater factory;
  List<NacBean> ln;
  AvaliacoesAdapter mAdapter;
  ViewPager mViewPager;
  TextView mensagem;
  View myView;
  ProgressDialog p;
  String pchave;
  String prm;
  SharedPreferences sb;
  int tela;
  AvaliacaoService webservice;
  
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
    new Thread()
    {
      public void run()
      {
        try
        {
          AvaliacaoesFragment.this.webservice = new AvaliacaoService();
          AvaliacaoesFragment.this.ln = AvaliacaoesFragment.this.webservice.nacs(AvaliacaoesFragment.this.prm, AvaliacaoesFragment.this.pchave);
          AvaliacaoesFragment.this.ab = AvaliacaoesFragment.this.webservice.avaliacoes(AvaliacaoesFragment.this.prm, AvaliacaoesFragment.this.pchave);
          AvaliacaoesFragment.this.p.dismiss();
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            Log.i("Erro:", "Webservice de avaliação");
          }
        }
      }
    }.start();
    this.p.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        try
        {
          if (((AvaliacaoesFragment.this.ln.size() == 0) || (AvaliacaoesFragment.this.ln == null)) && ((AvaliacaoesFragment.this.ab.size() == 0) || (AvaliacaoesFragment.this.ab == null)))
          {
            paramAnonymousDialogInterface = (LinearLayout)AvaliacaoesFragment.this.myView.findViewById(2131427461);
            localView = AvaliacaoesFragment.this.factory.inflate(2130903117, null);
            AvaliacaoesFragment.this.mensagem = ((TextView)localView.findViewById(2131427570));
            AvaliacaoesFragment.this.mensagem.setText("Não há nenhum calendário cadastrado");
            paramAnonymousDialogInterface.addView(localView);
            return;
          }
          paramAnonymousDialogInterface = (LinearLayout)AvaliacaoesFragment.this.myView.findViewById(2131427461);
          paramAnonymousDialogInterface.setGravity(48);
          View localView = AvaliacaoesFragment.this.factory.inflate(2130903066, null);
          AvaliacaoesFragment.this.mAdapter = new AvaliacoesAdapter(AvaliacaoesFragment.this.getActivity().getSupportFragmentManager());
          AvaliacaoesFragment.this.mViewPager = ((ViewPager)localView.findViewById(2131427415));
          AvaliacaoesFragment.this.mViewPager.setAdapter(AvaliacaoesFragment.this.mAdapter);
          paramAnonymousDialogInterface.addView(localView);
          return;
        }
        catch (Exception paramAnonymousDialogInterface)
        {
          ToastManager.show(AvaliacaoesFragment.this.getActivity(), "Não foi possível carregar os dados! Verifique sua conexão!");
          AvaliacaoesFragment.this.getActivity().finish();
        }
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\AvaliacaoesFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */