package br.com.fiap.fiapmobile;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.actionbarsherlock.app.SherlockActivity;

public class LeitorPdf
  extends SherlockActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = new Intent().getStringExtra("url");
    WebView localWebView = new WebView(this);
    localWebView.getSettings().setJavaScriptEnabled(true);
    localWebView.getSettings().setPluginsEnabled(true);
    localWebView.loadUrl(paramBundle);
    setContentView(localWebView);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\fiapmobile\LeitorPdf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */