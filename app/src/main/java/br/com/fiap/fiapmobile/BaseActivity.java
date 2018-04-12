package br.com.fiap.fiapmobile;

import android.app.Dialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import br.com.fiap.services.LoginService;
import br.com.fiap.tools.FiapUtils;
import br.com.fiap.widget.WidgetProvider;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity
  extends SlidingFragmentActivity
{
  Dialog custom;
  LoginService l;
  protected ListFragment mFrag;
  private int mTitleRes;
  String pchave;
  String prm;
  SharedPreferences sb;
  private TextView title;
  
  public BaseActivity(int paramInt)
  {
    this.mTitleRes = paramInt;
  }
  
  public void abrirMenu(View paramView)
  {
    toggle();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(this.mTitleRes);
    this.l = new LoginService();
    this.sb = getSharedPreferences("user", 0);
    this.prm = this.sb.getString("prm", "");
    this.pchave = this.sb.getString("pchave", "");
    setBehindContentView(2130903092);
    paramBundle = getSlidingMenu();
    paramBundle.setMode(0);
    paramBundle.setTouchModeAbove(0);
    paramBundle.setShadowWidthRes(2131361828);
    paramBundle.setShadowDrawable(2130837707);
    paramBundle.setBehindOffsetRes(2131361826);
    paramBundle.setFadeDegree(0.35F);
    paramBundle = getSupportActionBar();
    paramBundle.setCustomView(getLayoutInflater().inflate(2130903091, null), new ActionBar.LayoutParams(-1, -1));
    paramBundle.setDisplayOptions(16);
    paramBundle.setDisplayUseLogoEnabled(false);
    paramBundle.setDisplayShowTitleEnabled(false);
    paramBundle.setDisplayShowHomeEnabled(false);
    paramBundle.setDisplayHomeAsUpEnabled(false);
    this.title = ((TextView)findViewById(2131427472));
    this.title.setText(this.mTitleRes);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void sairMenu(View paramView)
  {
    this.custom = new Dialog(this, 2131034213);
    this.custom.requestWindowFeature(1);
    paramView = getResources().getDrawable(2130837626);
    this.custom.getWindow().setBackgroundDrawable(paramView);
    this.custom.setContentView(2130903062);
    paramView = (TextView)this.custom.findViewById(2131427405);
    Object localObject = (TextView)this.custom.findViewById(2131427406);
    paramView.setText("Desconectar");
    ((TextView)localObject).setText("Deseja realmente sair?");
    paramView = (Button)this.custom.findViewById(2131427408);
    localObject = (Button)this.custom.findViewById(2131427407);
    paramView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BaseActivity.this.getSharedPreferences("user", 0).edit().clear().commit();
        paramAnonymousView = BaseActivity.this.getSharedPreferences(FiapUtils.class.getSimpleName(), 0).getString("regId", "");
        BaseActivity.this.l.desativaDevice(BaseActivity.this.prm, paramAnonymousView, BaseActivity.this.pchave);
        paramAnonymousView = new Intent(BaseActivity.this, LoginActivity.class);
        BaseActivity.this.startActivity(paramAnonymousView);
        paramAnonymousView = new Intent(BaseActivity.this, WidgetProvider.class);
        paramAnonymousView.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        paramAnonymousView.putExtra("appWidgetIds", AppWidgetManager.getInstance(BaseActivity.this.getApplication()).getAppWidgetIds(new ComponentName(BaseActivity.this.getApplication(), WidgetProvider.class)));
        BaseActivity.this.sendBroadcast(paramAnonymousView);
        BaseActivity.this.finish();
      }
    });
    ((Button)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BaseActivity.this.custom.dismiss();
      }
    });
    this.custom.show();
    toggle();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */