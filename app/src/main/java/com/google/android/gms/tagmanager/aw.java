package com.google.android.gms.tagmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class aw
  implements bm
{
  private HttpURLConnection apv;
  
  private InputStream a(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    int i = paramHttpURLConnection.getResponseCode();
    if (i == 200) {
      return paramHttpURLConnection.getInputStream();
    }
    paramHttpURLConnection = "Bad response: " + i;
    if (i == 404) {
      throw new FileNotFoundException(paramHttpURLConnection);
    }
    throw new IOException(paramHttpURLConnection);
  }
  
  private void b(HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection != null) {
      paramHttpURLConnection.disconnect();
    }
  }
  
  public InputStream cD(String paramString)
    throws IOException
  {
    this.apv = cE(paramString);
    return a(this.apv);
  }
  
  HttpURLConnection cE(String paramString)
    throws IOException
  {
    paramString = (HttpURLConnection)new URL(paramString).openConnection();
    paramString.setReadTimeout(20000);
    paramString.setConnectTimeout(20000);
    return paramString;
  }
  
  public void close()
  {
    b(this.apv);
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\tagmanager\aw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */