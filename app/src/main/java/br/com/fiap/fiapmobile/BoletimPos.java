package br.com.fiap.fiapmobile;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fiap.beans.BoletimPosBean;
import br.com.fiap.beans.ListasBean;
import br.com.fiap.layouts.FaltasPosAdapter;
import br.com.fiap.layouts.ToastManager;
import br.com.fiap.services.BoletimService;
import br.com.fiap.services.TurmaService;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import java.util.List;

public class BoletimPos
  extends SherlockFragmentActivity
{
  String ano;
  TextView ar;
  Double artigo;
  BoletimService bwebservice;
  Dialog custom;
  TextView disciplina;
  TextView exa;
  LayoutInflater factory;
  ListView lista;
  List<ListasBean> listaInfos;
  List<BoletimPosBean> listanotas;
  LinearLayout[] llfa;
  Double media;
  TextView mf;
  TextView mp;
  View myView;
  ProgressDialog p;
  String pano;
  String pchave;
  String pcurso;
  String pnome;
  String pnometurma;
  TextView pr;
  String prm;
  String ptipo;
  TextView rodape;
  SharedPreferences sb;
  TextView status;
  TextView title;
  String turma;
  TurmaService twebservice;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903076);
    paramBundle = getLayoutInflater().inflate(2130903072, null);
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setCustomView(paramBundle, new ActionBar.LayoutParams(-1, -1));
    localActionBar.setDisplayOptions(16);
    localActionBar.setDisplayUseLogoEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(false);
    localActionBar.setDisplayShowHomeEnabled(false);
    localActionBar.setDisplayHomeAsUpEnabled(false);
    this.sb = getSharedPreferences("user", 0);
    paramBundle = getIntent();
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    this.turma = paramBundle.getStringExtra("turma");
    this.title = ((TextView)findViewById(2131689472));
    this.title.setText("Notas e Faltas");
    this.twebservice = new TurmaService();
    this.bwebservice = new BoletimService();
    this.factory = LayoutInflater.from(this);
    this.p = ProgressDialog.show(this, null, "Carregando Dados...", true);
    new Thread()
    {
      public void run()
      {
        try
        {
          BoletimPos.this.listanotas = BoletimPos.this.bwebservice.boletimpos(BoletimPos.this.prm, BoletimPos.this.pchave, BoletimPos.this.turma);
          BoletimPos.this.p.dismiss();
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
        BoletimPos.this.llfa = new LinearLayout[BoletimPos.this.listanotas.size()];
        int i = 0;
        if (i >= BoletimPos.this.listanotas.size()) {
          return;
        }
        BoletimPosBean localBoletimPosBean = (BoletimPosBean)BoletimPos.this.listanotas.get(i);
        LinearLayout localLinearLayout = (LinearLayout)BoletimPos.this.findViewById(2131427426);
        BoletimPos.this.rodape = ((TextView)BoletimPos.this.findViewById(2131427428));
        if (localBoletimPosBean.getUsaartigo() == 1)
        {
          paramAnonymousDialogInterface = BoletimPos.this.factory.inflate(2130903112, null);
          BoletimPos.this.rodape.setText(2131099719);
          label125:
          BoletimPos.this.disciplina = ((TextView)paramAnonymousDialogInterface.findViewById(2131427516));
          BoletimPos.this.pr = ((TextView)paramAnonymousDialogInterface.findViewById(2131427536));
          BoletimPos.this.mp = ((TextView)paramAnonymousDialogInterface.findViewById(2131427539));
          BoletimPos.this.exa = ((TextView)paramAnonymousDialogInterface.findViewById(2131427542));
          BoletimPos.this.mf = ((TextView)paramAnonymousDialogInterface.findViewById(2131427545));
          BoletimPos.this.status = ((TextView)paramAnonymousDialogInterface.findViewById(2131427548));
          BoletimPos.this.llfa[i] = ((LinearLayout)paramAnonymousDialogInterface.findViewById(2131427534));
          BoletimPos.this.llfa[i].setTag(localBoletimPosBean);
          BoletimPos.this.llfa[i].setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              paramAnonymous2View = ((BoletimPosBean)paramAnonymous2View.getTag()).getListafaltas();
              if (paramAnonymous2View.size() == 0)
              {
                ToastManager.show(BoletimPos.this, "Nenhuma falta nesse modulo");
                return;
              }
              BoletimPos.this.custom = new Dialog(BoletimPos.this, 2131034213);
              BoletimPos.this.custom.requestWindowFeature(1);
              Drawable localDrawable = BoletimPos.this.getResources().getDrawable(2130837626);
              BoletimPos.this.custom.getWindow().setBackgroundDrawable(localDrawable);
              BoletimPos.this.custom.setContentView(2130903079);
              ((ListView)BoletimPos.this.custom.findViewById(2131427430)).setAdapter(new FaltasPosAdapter(BoletimPos.this, paramAnonymous2View));
              BoletimPos.this.custom.setTitle("Faltas");
              BoletimPos.this.custom.show();
            }
          });
          if (localBoletimPosBean.getUsaartigo() == 1)
          {
            BoletimPos.this.ar = ((TextView)paramAnonymousDialogInterface.findViewById(2131427551));
            if (localBoletimPosBean.getNotaartigo().equalsIgnoreCase("null")) {
              break label585;
            }
            BoletimPos.this.ar.setText(String.valueOf(Double.parseDouble(localBoletimPosBean.getNotaartigo())));
          }
          label328:
          if (localBoletimPosBean.getMediafinal().equalsIgnoreCase("null")) {
            break label600;
          }
          BoletimPos.this.media = Double.valueOf(Double.parseDouble(localBoletimPosBean.getMediafinal()));
          label357:
          if (localBoletimPosBean.getCh() != 0) {
            break label616;
          }
          BoletimPos.this.disciplina.setText(localBoletimPosBean.getNomemodulo());
          label378:
          if (localBoletimPosBean.getFaltas().doubleValue() != 0.0D) {
            break label663;
          }
          BoletimPos.this.pr.setText("0");
          label402:
          if (!localBoletimPosBean.getNota().equalsIgnoreCase("null")) {
            break label683;
          }
          BoletimPos.this.mp.setText("-");
          if (!localBoletimPosBean.getNotasub().equalsIgnoreCase("null")) {
            break label770;
          }
          BoletimPos.this.exa.setText("-");
          label450:
          if (BoletimPos.this.media.doubleValue() >= 7.0D) {
            break label793;
          }
          BoletimPos.this.mf.setTextColor(BoletimPos.this.getResources().getColor(2131165230));
          label489:
          if (BoletimPos.this.media.doubleValue() >= 0.0D) {
            break label818;
          }
          BoletimPos.this.mf.setText("-");
        }
        for (;;)
        {
          if (BoletimPos.this.media.doubleValue() >= 0.0D) {
            break label841;
          }
          BoletimPos.this.status.setText("-");
          localLinearLayout.addView(paramAnonymousDialogInterface);
          i += 1;
          break;
          paramAnonymousDialogInterface = BoletimPos.this.factory.inflate(2130903111, null);
          BoletimPos.this.rodape.setText(2131099718);
          break label125;
          label585:
          BoletimPos.this.ar.setText("-");
          break label328;
          label600:
          BoletimPos.this.media = Double.valueOf(-1.0D);
          break label357;
          label616:
          BoletimPos.this.disciplina.setText(localBoletimPosBean.getNomemodulo() + " - " + localBoletimPosBean.getCh() + "h");
          break label378;
          label663:
          BoletimPos.this.pr.setText(String.valueOf(localBoletimPosBean.getFaltas()));
          break label402;
          label683:
          if (Double.valueOf(localBoletimPosBean.getNota()).doubleValue() < 7.0D) {
            BoletimPos.this.mp.setTextColor(BoletimPos.this.getResources().getColor(2131165230));
          }
          for (;;)
          {
            BoletimPos.this.mp.setText(String.valueOf(Double.parseDouble(localBoletimPosBean.getNota())));
            break;
            BoletimPos.this.mp.setTextColor(BoletimPos.this.getResources().getColor(2131165233));
          }
          label770:
          BoletimPos.this.exa.setText(String.valueOf(Double.parseDouble(localBoletimPosBean.getNotasub())));
          break label450;
          label793:
          BoletimPos.this.mf.setTextColor(BoletimPos.this.getResources().getColor(2131165233));
          break label489;
          label818:
          BoletimPos.this.mf.setText(String.valueOf(BoletimPos.this.media));
        }
        label841:
        if (BoletimPos.this.media.doubleValue() >= 7.0D) {
          BoletimPos.this.status.setTextColor(BoletimPos.this.getResources().getColor(2131165232));
        }
        for (;;)
        {
          BoletimPos.this.status.setText(localBoletimPosBean.getStatus());
          break;
          BoletimPos.this.status.setTextColor(BoletimPos.this.getResources().getColor(2131165230));
        }
      }
    });
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\BoletimPos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */