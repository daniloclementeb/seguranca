package br.com.fiap.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.beans.BoletimBean;
import br.com.fiap.beans.NotaBean;
import br.com.fiap.layouts.FaltasAdapter;
import br.com.fiap.layouts.NacsAdapter;
import br.com.fiap.layouts.ToastManager;
import br.com.fiap.services.BoletimService;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"ValidFragment"})
public class BoletimSemestreFragment
  extends Fragment
{
  String ano;
  BoletimBean b;
  Dialog custom;
  TextView disciplina;
  TextView exa;
  LayoutInflater factory;
  TextView faltas;
  LinearLayout[] llfa;
  LinearLayout[] llnac;
  TextView media;
  TextView mf;
  TextView mp;
  View myView;
  NotaBean n;
  List<NotaBean> notas;
  TextView ntam;
  TextView ntnac;
  TextView ntps;
  ProgressDialog p;
  String pano;
  String pchave;
  String pcurso;
  String pnome;
  String pnometurma;
  TextView pr;
  String prm;
  String ptipo;
  SharedPreferences sb;
  int semestre;
  TextView status;
  String turma;
  BoletimService webservice;
  
  public BoletimSemestreFragment(int paramInt, String paramString1, String paramString2)
  {
    this.semestre = paramInt;
    this.ano = paramString1;
    this.turma = paramString2;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.factory = LayoutInflater.from(getActivity());
    this.myView = this.factory.inflate(2130903075, null);
    this.webservice = new BoletimService();
    paramLayoutInflater = getActivity();
    getActivity();
    this.sb = paramLayoutInflater.getSharedPreferences("user", 0);
    this.p = ProgressDialog.show(getActivity(), null, getResources().getString(2131099724), true);
    this.ptipo = this.sb.getString("ptipo", "");
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.pnome = this.sb.getString("pnome", "");
    this.pcurso = this.sb.getString("pcurso", "");
    new Thread()
    {
      public void run()
      {
        try
        {
          BoletimSemestreFragment.this.b = BoletimSemestreFragment.this.webservice.boletim(BoletimSemestreFragment.this.prm, BoletimSemestreFragment.this.pchave, BoletimSemestreFragment.this.ano, BoletimSemestreFragment.this.turma);
          BoletimSemestreFragment.this.notas = new ArrayList();
          BoletimSemestreFragment.this.notas = BoletimSemestreFragment.this.b.getNotas();
          BoletimSemestreFragment.this.p.dismiss();
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
        for (;;)
        {
          int i;
          Object localObject;
          try
          {
            BoletimSemestreFragment.this.llfa = new LinearLayout[BoletimSemestreFragment.this.notas.size()];
            BoletimSemestreFragment.this.llnac = new LinearLayout[BoletimSemestreFragment.this.notas.size()];
            i = 0;
            if (i >= BoletimSemestreFragment.this.notas.size()) {
              return;
            }
            if (BoletimSemestreFragment.this.semestre != 1) {
              break label954;
            }
            BoletimSemestreFragment.this.n = ((NotaBean)BoletimSemestreFragment.this.notas.get(i));
            paramAnonymousDialogInterface = (LinearLayout)BoletimSemestreFragment.this.myView.findViewById(2131427426);
            localObject = BoletimSemestreFragment.this.factory.inflate(2130903110, null);
            BoletimSemestreFragment.this.disciplina = ((TextView)((View)localObject).findViewById(2131427516));
            BoletimSemestreFragment.this.ntnac = ((TextView)((View)localObject).findViewById(2131427520));
            BoletimSemestreFragment.this.ntam = ((TextView)((View)localObject).findViewById(2131427523));
            BoletimSemestreFragment.this.ntps = ((TextView)((View)localObject).findViewById(2131427526));
            BoletimSemestreFragment.this.faltas = ((TextView)((View)localObject).findViewById(2131427529));
            BoletimSemestreFragment.this.media = ((TextView)((View)localObject).findViewById(2131427532));
            BoletimSemestreFragment.this.llfa[i] = ((LinearLayout)((View)localObject).findViewById(2131427527));
            BoletimSemestreFragment.this.llnac[i] = ((LinearLayout)((View)localObject).findViewById(2131427518));
            BoletimSemestreFragment.this.llfa[i].setTag(BoletimSemestreFragment.this.n);
            BoletimSemestreFragment.this.llnac[i].setTag(BoletimSemestreFragment.this.n);
            BoletimSemestreFragment.this.llfa[i].setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                paramAnonymous2View = ((NotaBean)paramAnonymous2View.getTag()).getFaltas1();
                if (paramAnonymous2View.size() == 0)
                {
                  ToastManager.show(BoletimSemestreFragment.this.getActivity(), "Nenhuma falta nessa disciplina");
                  return;
                }
                BoletimSemestreFragment.this.custom = new Dialog(BoletimSemestreFragment.this.getActivity(), 2131034213);
                BoletimSemestreFragment.this.custom.requestWindowFeature(1);
                Drawable localDrawable = BoletimSemestreFragment.this.getResources().getDrawable(2130837626);
                BoletimSemestreFragment.this.custom.getWindow().setBackgroundDrawable(localDrawable);
                BoletimSemestreFragment.this.custom.setContentView(2130903079);
                ((ListView)BoletimSemestreFragment.this.custom.findViewById(2131427430)).setAdapter(new FaltasAdapter(BoletimSemestreFragment.this.getActivity(), paramAnonymous2View));
                BoletimSemestreFragment.this.custom.setTitle("Faltas");
                BoletimSemestreFragment.this.custom.show();
              }
            });
            BoletimSemestreFragment.this.llnac[i].setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                paramAnonymous2View = ((NotaBean)paramAnonymous2View.getTag()).getListanac1();
                if (paramAnonymous2View.size() == 0)
                {
                  ToastManager.show(BoletimSemestreFragment.this.getActivity(), "Nenhuma nota de NAC lançada");
                  return;
                }
                BoletimSemestreFragment.this.custom = new Dialog(BoletimSemestreFragment.this.getActivity(), 2131034213);
                BoletimSemestreFragment.this.custom.requestWindowFeature(1);
                Drawable localDrawable = BoletimSemestreFragment.this.getResources().getDrawable(2130837626);
                BoletimSemestreFragment.this.custom.getWindow().setBackgroundDrawable(localDrawable);
                BoletimSemestreFragment.this.custom.setContentView(2130903094);
                ((ListView)BoletimSemestreFragment.this.custom.findViewById(2131427477)).setAdapter(new NacsAdapter(BoletimSemestreFragment.this.getActivity(), paramAnonymous2View));
                BoletimSemestreFragment.this.custom.show();
              }
            });
            BoletimSemestreFragment.this.disciplina.setText(BoletimSemestreFragment.this.n.getDisciplina());
            if (BoletimSemestreFragment.this.n.getNac1().equalsIgnoreCase("null"))
            {
              BoletimSemestreFragment.this.ntnac.setText("-");
              if (!BoletimSemestreFragment.this.n.getAm1().equalsIgnoreCase("null")) {
                break label643;
              }
              BoletimSemestreFragment.this.ntam.setText("-");
              if (!BoletimSemestreFragment.this.n.getPs1().equalsIgnoreCase("null")) {
                break label739;
              }
              BoletimSemestreFragment.this.ntps.setText("-");
              if (!BoletimSemestreFragment.this.n.getFalta1().equalsIgnoreCase("null")) {
                break label835;
              }
              BoletimSemestreFragment.this.faltas.setText("-");
              if (!BoletimSemestreFragment.this.n.getMd1().equalsIgnoreCase("null")) {
                break label858;
              }
              BoletimSemestreFragment.this.media.setText("-");
              paramAnonymousDialogInterface.addView((View)localObject);
              break label2306;
            }
            if (Double.parseDouble(BoletimSemestreFragment.this.n.getNac1()) < 6.0D)
            {
              BoletimSemestreFragment.this.ntnac.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
              BoletimSemestreFragment.this.ntnac.setText(String.valueOf(Double.valueOf(BoletimSemestreFragment.this.n.getNac1())));
              continue;
            }
            BoletimSemestreFragment.this.ntnac.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
          }
          catch (Exception paramAnonymousDialogInterface)
          {
            Toast.makeText(BoletimSemestreFragment.this.getActivity(), "Não foi possível carregar os dados!", 1).show();
            BoletimSemestreFragment.this.getActivity().finish();
            paramAnonymousDialogInterface.printStackTrace();
            return;
          }
          continue;
          label643:
          if (Double.parseDouble(BoletimSemestreFragment.this.n.getAm1()) < 6.0D) {
            BoletimSemestreFragment.this.ntam.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
          }
          for (;;)
          {
            BoletimSemestreFragment.this.ntam.setText(String.valueOf(Double.valueOf(BoletimSemestreFragment.this.n.getAm1())));
            break;
            BoletimSemestreFragment.this.ntam.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
          }
          label739:
          if (Double.parseDouble(BoletimSemestreFragment.this.n.getPs1()) < 6.0D) {
            BoletimSemestreFragment.this.ntps.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
          }
          for (;;)
          {
            BoletimSemestreFragment.this.ntps.setText(String.valueOf(Double.valueOf(BoletimSemestreFragment.this.n.getPs1())));
            break;
            BoletimSemestreFragment.this.ntps.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
          }
          label835:
          BoletimSemestreFragment.this.faltas.setText(BoletimSemestreFragment.this.n.getFalta1());
          continue;
          label858:
          if (Double.parseDouble(BoletimSemestreFragment.this.n.getMd1()) < 6.0D) {
            BoletimSemestreFragment.this.media.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
          }
          for (;;)
          {
            BoletimSemestreFragment.this.media.setText(String.valueOf(Double.valueOf(BoletimSemestreFragment.this.n.getMd1())));
            break;
            BoletimSemestreFragment.this.media.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
          }
          label954:
          View localView;
          if (BoletimSemestreFragment.this.semestre == 2)
          {
            paramAnonymousDialogInterface = (NotaBean)BoletimSemestreFragment.this.notas.get(i);
            localObject = (LinearLayout)BoletimSemestreFragment.this.myView.findViewById(2131427426);
            localView = BoletimSemestreFragment.this.factory.inflate(2130903110, null);
            BoletimSemestreFragment.this.disciplina = ((TextView)localView.findViewById(2131427516));
            BoletimSemestreFragment.this.ntnac = ((TextView)localView.findViewById(2131427520));
            BoletimSemestreFragment.this.ntam = ((TextView)localView.findViewById(2131427523));
            BoletimSemestreFragment.this.ntps = ((TextView)localView.findViewById(2131427526));
            BoletimSemestreFragment.this.faltas = ((TextView)localView.findViewById(2131427529));
            BoletimSemestreFragment.this.media = ((TextView)localView.findViewById(2131427532));
            BoletimSemestreFragment.this.llfa[i] = ((LinearLayout)localView.findViewById(2131427527));
            BoletimSemestreFragment.this.llnac[i] = ((LinearLayout)localView.findViewById(2131427518));
            BoletimSemestreFragment.this.llfa[i].setTag(paramAnonymousDialogInterface);
            BoletimSemestreFragment.this.llnac[i].setTag(paramAnonymousDialogInterface);
            BoletimSemestreFragment.this.llfa[i].setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                paramAnonymous2View = ((NotaBean)paramAnonymous2View.getTag()).getFaltas2();
                if (paramAnonymous2View.size() == 0)
                {
                  ToastManager.show(BoletimSemestreFragment.this.getActivity(), "Nenhuma falta nessa disciplina");
                  return;
                }
                BoletimSemestreFragment.this.custom = new Dialog(BoletimSemestreFragment.this.getActivity(), 2131034213);
                BoletimSemestreFragment.this.custom.requestWindowFeature(1);
                Drawable localDrawable = BoletimSemestreFragment.this.getResources().getDrawable(2130837626);
                BoletimSemestreFragment.this.custom.getWindow().setBackgroundDrawable(localDrawable);
                BoletimSemestreFragment.this.custom.setContentView(2130903079);
                ((ListView)BoletimSemestreFragment.this.custom.findViewById(2131427430)).setAdapter(new FaltasAdapter(BoletimSemestreFragment.this.getActivity(), paramAnonymous2View));
                BoletimSemestreFragment.this.custom.setTitle("Faltas");
                BoletimSemestreFragment.this.custom.show();
              }
            });
            BoletimSemestreFragment.this.llnac[i].setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                paramAnonymous2View = ((NotaBean)paramAnonymous2View.getTag()).getListanac2();
                if (paramAnonymous2View.size() == 0)
                {
                  ToastManager.show(BoletimSemestreFragment.this.getActivity(), "Nenhuma nota de NAC lançada");
                  return;
                }
                BoletimSemestreFragment.this.custom = new Dialog(BoletimSemestreFragment.this.getActivity(), 2131034213);
                BoletimSemestreFragment.this.custom.requestWindowFeature(1);
                Drawable localDrawable = BoletimSemestreFragment.this.getResources().getDrawable(2130837626);
                BoletimSemestreFragment.this.custom.getWindow().setBackgroundDrawable(localDrawable);
                BoletimSemestreFragment.this.custom.setContentView(2130903094);
                ((ListView)BoletimSemestreFragment.this.custom.findViewById(2131427477)).setAdapter(new NacsAdapter(BoletimSemestreFragment.this.getActivity(), paramAnonymous2View));
                BoletimSemestreFragment.this.custom.show();
              }
            });
            BoletimSemestreFragment.this.disciplina.setText(paramAnonymousDialogInterface.getDisciplina());
            if (paramAnonymousDialogInterface.getNac2().equalsIgnoreCase("null"))
            {
              BoletimSemestreFragment.this.ntnac.setText("-");
              if (!paramAnonymousDialogInterface.getAm2().equalsIgnoreCase("null")) {
                break label1446;
              }
              BoletimSemestreFragment.this.ntam.setText("-");
              if (!paramAnonymousDialogInterface.getPs2().equalsIgnoreCase("null")) {
                break label1530;
              }
              BoletimSemestreFragment.this.ntps.setText("-");
              if (!paramAnonymousDialogInterface.getFalta2().equalsIgnoreCase("null")) {
                break label1614;
              }
              BoletimSemestreFragment.this.faltas.setText("-");
            }
            for (;;)
            {
              if (!paramAnonymousDialogInterface.getMd2().equalsIgnoreCase("null")) {
                break label1631;
              }
              BoletimSemestreFragment.this.media.setText("-");
              ((LinearLayout)localObject).addView(localView);
              break label2306;
              if (Double.parseDouble(paramAnonymousDialogInterface.getNac2()) < 6.0D) {
                BoletimSemestreFragment.this.ntnac.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
              }
              for (;;)
              {
                BoletimSemestreFragment.this.ntnac.setText(String.valueOf(Double.valueOf(paramAnonymousDialogInterface.getNac2())));
                break;
                BoletimSemestreFragment.this.ntnac.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
              }
              label1446:
              if (Double.parseDouble(paramAnonymousDialogInterface.getAm2()) < 6.0D) {
                BoletimSemestreFragment.this.ntam.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
              }
              for (;;)
              {
                BoletimSemestreFragment.this.ntam.setText(String.valueOf(Double.valueOf(paramAnonymousDialogInterface.getAm2())));
                break;
                BoletimSemestreFragment.this.ntam.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
              }
              label1530:
              if (Double.parseDouble(paramAnonymousDialogInterface.getPs2()) < 6.0D) {
                BoletimSemestreFragment.this.ntps.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
              }
              for (;;)
              {
                BoletimSemestreFragment.this.ntps.setText(String.valueOf(Double.valueOf(paramAnonymousDialogInterface.getPs2())));
                break;
                BoletimSemestreFragment.this.ntps.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
              }
              label1614:
              BoletimSemestreFragment.this.faltas.setText(paramAnonymousDialogInterface.getFalta2());
            }
            label1631:
            if (Double.parseDouble(paramAnonymousDialogInterface.getMd2()) < 6.0D) {
              BoletimSemestreFragment.this.media.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
            }
            for (;;)
            {
              BoletimSemestreFragment.this.media.setText(String.valueOf(Double.valueOf(paramAnonymousDialogInterface.getMd2())));
              break;
              BoletimSemestreFragment.this.media.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
            }
          }
          if (BoletimSemestreFragment.this.semestre == 3)
          {
            paramAnonymousDialogInterface = (NotaBean)BoletimSemestreFragment.this.notas.get(i);
            localObject = (LinearLayout)BoletimSemestreFragment.this.myView.findViewById(2131427426);
            localView = BoletimSemestreFragment.this.factory.inflate(2130903113, null);
            BoletimSemestreFragment.this.disciplina = ((TextView)localView.findViewById(2131427516));
            BoletimSemestreFragment.this.pr = ((TextView)localView.findViewById(2131427536));
            BoletimSemestreFragment.this.mp = ((TextView)localView.findViewById(2131427539));
            BoletimSemestreFragment.this.exa = ((TextView)localView.findViewById(2131427542));
            BoletimSemestreFragment.this.mf = ((TextView)localView.findViewById(2131427545));
            BoletimSemestreFragment.this.status = ((TextView)localView.findViewById(2131427548));
            BoletimSemestreFragment.this.disciplina.setText(paramAnonymousDialogInterface.getDisciplina());
            if (paramAnonymousDialogInterface.getFrenquencia().equalsIgnoreCase("null"))
            {
              BoletimSemestreFragment.this.pr.setText("-");
              if (!paramAnonymousDialogInterface.getMp().equalsIgnoreCase("null")) {
                break label2037;
              }
              BoletimSemestreFragment.this.mp.setText("-");
              if (!paramAnonymousDialogInterface.getExa().equalsIgnoreCase("null")) {
                break label2121;
              }
              BoletimSemestreFragment.this.exa.setText("-");
            }
            for (;;)
            {
              if (!paramAnonymousDialogInterface.getMf().equalsIgnoreCase("null")) {
                break label2144;
              }
              BoletimSemestreFragment.this.mf.setText("-");
              if (!paramAnonymousDialogInterface.getSituacao().equalsIgnoreCase("null")) {
                break label2228;
              }
              BoletimSemestreFragment.this.status.setText("-");
              ((LinearLayout)localObject).addView(localView);
              break label2306;
              BoletimSemestreFragment.this.pr.setText(paramAnonymousDialogInterface.getFrenquencia());
              break;
              label2037:
              if (Double.parseDouble(paramAnonymousDialogInterface.getMp()) < 6.0D) {
                BoletimSemestreFragment.this.mp.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
              }
              for (;;)
              {
                BoletimSemestreFragment.this.mp.setText(String.valueOf(Double.valueOf(paramAnonymousDialogInterface.getMp())));
                break;
                BoletimSemestreFragment.this.mp.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
              }
              label2121:
              BoletimSemestreFragment.this.exa.setText(String.valueOf(Double.valueOf(paramAnonymousDialogInterface.getExa())));
            }
            label2144:
            if (Double.parseDouble(paramAnonymousDialogInterface.getMf()) < 6.0D) {
              BoletimSemestreFragment.this.mf.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
            }
            for (;;)
            {
              BoletimSemestreFragment.this.mf.setText(String.valueOf(Double.valueOf(paramAnonymousDialogInterface.getMf())));
              break;
              BoletimSemestreFragment.this.mf.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165233));
            }
            label2228:
            if (paramAnonymousDialogInterface.getSituacao().equalsIgnoreCase("Aprovado")) {
              BoletimSemestreFragment.this.status.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165232));
            }
            for (;;)
            {
              BoletimSemestreFragment.this.status.setText(paramAnonymousDialogInterface.getSituacao());
              break;
              BoletimSemestreFragment.this.status.setTextColor(BoletimSemestreFragment.this.getResources().getColor(2131165230));
            }
          }
          label2306:
          i += 1;
        }
      }
    });
    return this.myView;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fragments\BoletimSemestreFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */