package br.com.fiap.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class FiapUtils
{
  private static final String APP_VERSION = "appVersion";
  public static final String REG_ID = "regId";
  public static final String REG_STATUS = "regStatus";
  String SENDER_ID = "520937860769";
  private String TAG = "FIAP_GCM";
  GoogleCloudMessaging gcm;
  AtomicInteger msgId = new AtomicInteger();
  SharedPreferences prefs;
  String regId;
  
  private static int getAppVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.d("RegisterActivity", "I never expected this! Going down, going down!" + paramContext);
      throw new RuntimeException(paramContext);
    }
  }
  
  private String getRegistrationId(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(FiapUtils.class.getSimpleName(), 0);
    String str = localSharedPreferences.getString("regId", "");
    if (str.isEmpty())
    {
      Log.i(this.TAG, "Registration not found.");
      str = "";
    }
    while (localSharedPreferences.getInt("appVersion", Integer.MIN_VALUE) == getAppVersion(paramContext)) {
      return str;
    }
    Log.i(this.TAG, "App version changed.");
    return "";
  }
  
  private void registerInBackground(final Context paramContext)
  {
    new AsyncTask()
    {
      protected Object doInBackground(Object... paramAnonymousVarArgs)
      {
        try
        {
          if (FiapUtils.this.gcm == null) {
            FiapUtils.this.gcm = GoogleCloudMessaging.getInstance(paramContext);
          }
          FiapUtils.this.regId = FiapUtils.this.gcm.register(new String[] { FiapUtils.this.SENDER_ID });
          Log.d("RegisterActivity", "registerInBackground - regId: " + FiapUtils.this.regId);
          paramAnonymousVarArgs = "Device registered, registration ID=" + FiapUtils.this.regId;
          FiapUtils.this.storeRegistrationId(paramContext, FiapUtils.this.regId);
        }
        catch (IOException paramAnonymousVarArgs)
        {
          for (;;)
          {
            paramAnonymousVarArgs = "Error :" + paramAnonymousVarArgs.getMessage();
            Log.d("RegisterActivity", "Error: " + paramAnonymousVarArgs);
          }
        }
        Log.d("RegisterActivity", "AsyncTask completed: " + paramAnonymousVarArgs);
        return paramAnonymousVarArgs;
      }
      
      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
      }
    }.execute(new Object[] { null, null, null });
  }
  
  private void storeRegistrationId(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(FiapUtils.class.getSimpleName(), 0);
    int i = getAppVersion(paramContext);
    Log.i(this.TAG, "Saving regId on app version " + i);
    paramContext = localSharedPreferences.edit();
    paramContext.putString("regId", paramString);
    paramContext.putBoolean("regStatus", true);
    paramContext.putInt("appVersion", i);
    paramContext.commit();
  }
  
  public static boolean verificaConexao(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (paramContext.getActiveNetworkInfo() != null) && (paramContext.getActiveNetworkInfo().isAvailable()) && (paramContext.getActiveNetworkInfo().isConnected());
  }
  
  public String registerClient(Context paramContext)
  {
    this.gcm = GoogleCloudMessaging.getInstance(paramContext);
    this.regId = getRegistrationId(paramContext);
    if (TextUtils.isEmpty(this.regId))
    {
      registerInBackground(paramContext);
      Log.d("RegisterActivity", "registerGCM - successfully registered with GCM server - regId: " + this.regId);
    }
    return this.regId;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\tools\FiapUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */