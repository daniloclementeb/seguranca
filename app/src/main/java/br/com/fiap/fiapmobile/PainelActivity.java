package br.com.fiap.fiapmobile;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.fiap.fragments.AvisoFragment;
import br.com.fiap.fragments.BoletimFragment;
import br.com.fiap.fragments.HomeFragment;
import br.com.fiap.fragments.MenuFragment;
import br.com.fiap.layouts.ToastManager;
import br.com.fiap.services.LoginService;
import br.com.fiap.tools.FiapUtils;
import com.actionbarsherlock.app.ActionBar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class PainelActivity
  extends BaseActivity
{
  CheckBox checkBox;
  Dialog custom;
  LoginService l;
  private Fragment mContent;
  String pchave;
  String prm;
  SharedPreferences sb;
  
  public PainelActivity()
  {
    super(2131099702);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    final Object localObject = getSharedPreferences(FiapUtils.class.getSimpleName(), 0);
    if (Build.VERSION.SDK_INT > 9) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    }
    if (!FiapUtils.verificaConexao(this))
    {
      ToastManager.show(this, getResources().getString(2131099742));
      finish();
      return;
    }
    ((Button)findViewById(2131427474)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PainelActivity.this.l = new LoginService();
        PainelActivity.this.sb = PainelActivity.this.getSharedPreferences("user", 0);
        PainelActivity.this.prm = PainelActivity.this.sb.getString("prm", "");
        PainelActivity.this.pchave = PainelActivity.this.sb.getString("pchave", "");
        PainelActivity.this.custom = new Dialog(PainelActivity.this, 2131034213);
        PainelActivity.this.custom.requestWindowFeature(1);
        paramAnonymousView = PainelActivity.this.getResources().getDrawable(2130837626);
        PainelActivity.this.custom.getWindow().setBackgroundDrawable(paramAnonymousView);
        PainelActivity.this.custom.setContentView(2130903078);
        PainelActivity.this.checkBox = ((CheckBox)PainelActivity.this.custom.findViewById(2131427429));
        if (localObject.getBoolean("regStatus", false)) {
          PainelActivity.this.checkBox.setChecked(true);
        }
        PainelActivity.this.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
          public void onCheckedChanged(CompoundButton paramAnonymous2CompoundButton, boolean paramAnonymous2Boolean)
          {
            if (this.val$prefs.getBoolean("regStatus", false))
            {
              paramAnonymous2CompoundButton = this.val$prefs.getString("regId", "");
              localEditor = this.val$prefs.edit();
              localEditor.putBoolean("regStatus", false);
              localEditor.commit();
              PainelActivity.this.l.desativaDevice(PainelActivity.this.prm, paramAnonymous2CompoundButton, PainelActivity.this.pchave);
              PainelActivity.this.checkBox.setChecked(false);
              return;
            }
            paramAnonymous2CompoundButton = this.val$prefs.getString("regId", "");
            SharedPreferences.Editor localEditor = this.val$prefs.edit();
            localEditor.putBoolean("regStatus", true);
            localEditor.commit();
            PainelActivity.this.checkBox.setChecked(true);
            PainelActivity.this.l.gravaInfos(PainelActivity.this.prm, paramAnonymous2CompoundButton, PainelActivity.this.pchave);
          }
        });
        PainelActivity.this.custom.show();
      }
    });
    localObject = getIntent().getStringExtra("push");
    if (localObject != null)
    {
      if (((String)localObject).equalsIgnoreCase("avisos")) {
        this.mContent = new AvisoFragment();
      }
    }
    else
    {
      if (paramBundle != null) {
        this.mContent = getSupportFragmentManager().getFragment(paramBundle, "mContent");
      }
      if (this.mContent == null) {
        this.mContent = new HomeFragment();
      }
      if (findViewById(2131427475) != null) {
        break label266;
      }
      setBehindContentView(2130903092);
      getSlidingMenu().setSlidingEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    for (;;)
    {
      setContentView(2130903097);
      getSupportFragmentManager().beginTransaction().replace(2131427489, this.mContent).commit();
      setBehindContentView(2130903092);
      getSupportFragmentManager().beginTransaction().replace(2131427475, new MenuFragment()).commit();
      return;
      if (!((String)localObject).equalsIgnoreCase("notas")) {
        break;
      }
      this.mContent = new BoletimFragment();
      break;
      label266:
      setBehindContentView(new View(this));
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    getSupportFragmentManager().putFragment(paramBundle, "mContent", this.mContent);
  }
  
  public void switchContent(Fragment paramFragment, String paramString)
  {
    this.mContent = paramFragment;
    getSupportFragmentManager().beginTransaction().replace(2131427489, paramFragment).commit();
    getSlidingMenu().showContent();
    if (paramString.equalsIgnoreCase("home"))
    {
      ((ImageView)findViewById(2131427473)).setImageDrawable(getResources().getDrawable(2130837690));
      ((TextView)findViewById(2131427472)).setText("");
      return;
    }
    ((TextView)findViewById(2131427472)).setText(paramString);
    ((ImageView)findViewById(2131427473)).setImageDrawable(null);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\PainelActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */