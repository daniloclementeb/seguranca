package br.com.fiap.fiapmobile;

import android.app.Application;
import br.com.fiap.tools.FiapUtils;

public class MyApplication
  extends Application
{
  public void onCreate()
  {
    super.onCreate();
    new FiapUtils().registerClient(getApplicationContext());
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\MyApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */