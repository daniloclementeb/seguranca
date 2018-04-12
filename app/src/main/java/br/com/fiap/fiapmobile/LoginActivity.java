package br.com.fiap.fiapmobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import br.com.fiap.beans.AlunoBean;
import br.com.fiap.beans.LoginBean;
import br.com.fiap.beans.TurmaBean;
import br.com.fiap.layouts.ToastManager;
import br.com.fiap.services.LoginService;
import br.com.fiap.tools.FiapUtils;
import br.com.fiap.widget.WidgetProvider;
import java.util.List;

public class LoginActivity
  extends Activity
{
  String apid;
  LoginBean dadosLogin;
  LoginService l;
  ProgressDialog p;
  String pano;
  String pchave;
  String pcurso;
  String pnome;
  String pnometurma;
  String prm;
  String ptipo;
  AlunoBean retorno;
  EditText rm;
  SharedPreferences sb;
  EditText senha;
  
  public void logar(View paramView)
  {
    this.p = ProgressDialog.show(this, null, "Realizando Login...", true);
    this.dadosLogin.setInRM(this.rm.getText().toString());
    this.dadosLogin.setStSenha(this.senha.getText().toString());
    new Thread()
    {
      public void run()
      {
        try
        {
          LoginActivity.this.retorno = LoginActivity.this.l.logar(LoginActivity.this.dadosLogin, LoginActivity.this);
          LoginActivity.this.p.dismiss();
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
        if (LoginActivity.this.retorno.getErro() != null)
        {
          ToastManager.show(LoginActivity.this, LoginActivity.this.retorno.getErro());
          return;
        }
        LoginActivity.this.sb = LoginActivity.this.getSharedPreferences("user", 0);
        paramAnonymousDialogInterface = LoginActivity.this.sb.edit();
        new TurmaBean();
        TurmaBean localTurmaBean = (TurmaBean)LoginActivity.this.retorno.getTurma().get(0);
        paramAnonymousDialogInterface.putString("prm", LoginActivity.this.retorno.getRm());
        paramAnonymousDialogInterface.putString("pchave", LoginActivity.this.retorno.getChave());
        paramAnonymousDialogInterface.putString("pnome", LoginActivity.this.retorno.getNome());
        paramAnonymousDialogInterface.putString("pnometurma", localTurmaBean.getNomeTurma());
        paramAnonymousDialogInterface.putString("pcurso", localTurmaBean.getCurso());
        paramAnonymousDialogInterface.putString("pano", localTurmaBean.getAno());
        paramAnonymousDialogInterface.putString("ptipo", LoginActivity.this.retorno.getTipoAluno());
        paramAnonymousDialogInterface.commit();
        paramAnonymousDialogInterface = LoginActivity.this.getSharedPreferences(FiapUtils.class.getSimpleName(), 0).getString("regId", "");
        LoginActivity.this.l.gravaInfos(LoginActivity.this.retorno.getRm(), paramAnonymousDialogInterface, LoginActivity.this.retorno.getChave());
        paramAnonymousDialogInterface = new Intent(LoginActivity.this, PainelActivity.class);
        LoginActivity.this.startActivity(paramAnonymousDialogInterface);
        LoginActivity.this.finish();
        paramAnonymousDialogInterface = new Intent(LoginActivity.this, WidgetProvider.class);
        paramAnonymousDialogInterface.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        paramAnonymousDialogInterface.putExtra("appWidgetIds", AppWidgetManager.getInstance(LoginActivity.this.getApplication()).getAppWidgetIds(new ComponentName(LoginActivity.this.getApplication(), WidgetProvider.class)));
        LoginActivity.this.sendBroadcast(paramAnonymousDialogInterface);
      }
    });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    int k = 0;
    super.onCreate(paramBundle);
    setContentView(2130903090);
    if (Build.VERSION.SDK_INT > 9) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    }
    if (!verificaConexao())
    {
      ToastManager.show(this, getResources().getString(2131099742));
      finish();
    }
    label269:
    label314:
    label319:
    label323:
    for (;;)
    {
      return;
      this.l = new LoginService();
      this.dadosLogin = new LoginBean();
      this.rm = ((EditText)findViewById(2131427468));
      this.senha = ((EditText)findViewById(2131427469));
      this.sb = getSharedPreferences("user", 0);
      this.prm = this.sb.getString("prm", "");
      this.pchave = this.sb.getString("pchave", "");
      this.pnome = this.sb.getString("pnome", "");
      this.pnometurma = this.sb.getString("pnometurma", "");
      this.pcurso = this.sb.getString("pcurso", "");
      this.pano = this.sb.getString("pano", "");
      this.ptipo = this.sb.getString("ptipo", "");
      int i;
      int j;
      if (this.prm.equalsIgnoreCase(""))
      {
        i = 0;
        if (!this.pchave.equalsIgnoreCase("")) {
          break label314;
        }
        j = 0;
        if (!this.pnome.equalsIgnoreCase("")) {
          break label319;
        }
      }
      for (;;)
      {
        if ((i & j & k) == 0) {
          break label323;
        }
        startActivity(new Intent(this, PainelActivity.class));
        finish();
        return;
        i = 1;
        break;
        j = 1;
        break label269;
        k = 1;
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558407, paramMenu);
    return true;
  }
  
  public boolean verificaConexao()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)getSystemService("connectivity");
    return (localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().isAvailable()) && (localConnectivityManager.getActiveNetworkInfo().isConnected());
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\LoginActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */