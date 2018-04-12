package com.google.android.gms.analytics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class ag
  implements m
{
  private final HttpClient Bj;
  private URL Bk;
  private final Context mContext;
  private final String vW;
  private GoogleAnalytics yu;
  
  ag(HttpClient paramHttpClient, Context paramContext)
  {
    this(paramHttpClient, GoogleAnalytics.getInstance(paramContext), paramContext);
  }
  
  ag(HttpClient paramHttpClient, GoogleAnalytics paramGoogleAnalytics, Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.vW = a("GoogleAnalytics", "3.0", Build.VERSION.RELEASE, aj.a(Locale.getDefault()), Build.MODEL, Build.ID);
    this.Bj = paramHttpClient;
    this.yu = paramGoogleAnalytics;
  }
  
  /* Error */
  private void a(aa paramaa, URL paramURL, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 90	com/google/android/gms/analytics/aa:eL	()Ljava/lang/String;
    //   4: invokestatic 96	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   7: ifne +10 -> 17
    //   10: aload_0
    //   11: invokevirtual 100	com/google/android/gms/analytics/ag:eS	()Z
    //   14: ifne +4 -> 18
    //   17: return
    //   18: aload_2
    //   19: ifnonnull +231 -> 250
    //   22: aload_0
    //   23: getfield 102	com/google/android/gms/analytics/ag:Bk	Ljava/net/URL;
    //   26: ifnull +172 -> 198
    //   29: aload_0
    //   30: getfield 102	com/google/android/gms/analytics/ag:Bk	Ljava/net/URL;
    //   33: astore_2
    //   34: new 104	org/apache/http/HttpHost
    //   37: dup
    //   38: aload_2
    //   39: invokevirtual 109	java/net/URL:getHost	()Ljava/lang/String;
    //   42: aload_2
    //   43: invokevirtual 113	java/net/URL:getPort	()I
    //   46: aload_2
    //   47: invokevirtual 116	java/net/URL:getProtocol	()Ljava/lang/String;
    //   50: invokespecial 119	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   53: astore 5
    //   55: aload_0
    //   56: aload_1
    //   57: invokevirtual 90	com/google/android/gms/analytics/aa:eL	()Ljava/lang/String;
    //   60: aload_2
    //   61: invokevirtual 122	java/net/URL:getPath	()Ljava/lang/String;
    //   64: invokespecial 126	com/google/android/gms/analytics/ag:h	(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/http/HttpEntityEnclosingRequest;
    //   67: astore_1
    //   68: aload_1
    //   69: ifnull -52 -> 17
    //   72: aload_1
    //   73: ldc -128
    //   75: aload 5
    //   77: invokevirtual 131	org/apache/http/HttpHost:toHostString	()Ljava/lang/String;
    //   80: invokeinterface 137 3 0
    //   85: invokestatic 142	com/google/android/gms/analytics/z:eK	()Z
    //   88: ifeq +8 -> 96
    //   91: aload_0
    //   92: aload_1
    //   93: invokespecial 145	com/google/android/gms/analytics/ag:a	(Lorg/apache/http/HttpEntityEnclosingRequest;)V
    //   96: iload_3
    //   97: ifeq +10 -> 107
    //   100: aload_0
    //   101: getfield 39	com/google/android/gms/analytics/ag:mContext	Landroid/content/Context;
    //   104: invokestatic 151	com/google/android/gms/analytics/p:A	(Landroid/content/Context;)V
    //   107: aload_0
    //   108: getfield 75	com/google/android/gms/analytics/ag:Bj	Lorg/apache/http/client/HttpClient;
    //   111: aload 5
    //   113: aload_1
    //   114: invokeinterface 157 3 0
    //   119: astore_1
    //   120: aload_1
    //   121: invokeinterface 163 1 0
    //   126: invokeinterface 168 1 0
    //   131: istore 4
    //   133: aload_1
    //   134: invokeinterface 172 1 0
    //   139: astore_2
    //   140: aload_2
    //   141: ifnull +9 -> 150
    //   144: aload_2
    //   145: invokeinterface 177 1 0
    //   150: iload 4
    //   152: sipush 200
    //   155: if_icmpeq -138 -> 17
    //   158: new 179	java/lang/StringBuilder
    //   161: dup
    //   162: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   165: ldc -74
    //   167: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload_1
    //   171: invokeinterface 163 1 0
    //   176: invokeinterface 168 1 0
    //   181: invokevirtual 189	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   184: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   187: invokestatic 196	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   190: return
    //   191: astore_1
    //   192: ldc -58
    //   194: invokestatic 196	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   197: return
    //   198: new 106	java/net/URL
    //   201: dup
    //   202: ldc -56
    //   204: invokespecial 202	java/net/URL:<init>	(Ljava/lang/String;)V
    //   207: astore_2
    //   208: goto -174 -> 34
    //   211: astore_1
    //   212: return
    //   213: astore_1
    //   214: new 179	java/lang/StringBuilder
    //   217: dup
    //   218: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   221: ldc -52
    //   223: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: aload_1
    //   227: invokevirtual 208	java/lang/Object:getClass	()Ljava/lang/Class;
    //   230: invokevirtual 213	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   233: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: invokestatic 196	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   242: aload_1
    //   243: invokevirtual 216	java/io/IOException:getMessage	()Ljava/lang/String;
    //   246: invokestatic 196	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   249: return
    //   250: goto -216 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	253	0	this	ag
    //   0	253	1	paramaa	aa
    //   0	253	2	paramURL	URL
    //   0	253	3	paramBoolean	boolean
    //   131	25	4	i	int
    //   53	59	5	localHttpHost	HttpHost
    // Exception table:
    //   from	to	target	type
    //   55	68	191	org/apache/http/client/ClientProtocolException
    //   72	96	191	org/apache/http/client/ClientProtocolException
    //   100	107	191	org/apache/http/client/ClientProtocolException
    //   107	140	191	org/apache/http/client/ClientProtocolException
    //   144	150	191	org/apache/http/client/ClientProtocolException
    //   158	190	191	org/apache/http/client/ClientProtocolException
    //   22	34	211	java/net/MalformedURLException
    //   198	208	211	java/net/MalformedURLException
    //   55	68	213	java/io/IOException
    //   72	96	213	java/io/IOException
    //   100	107	213	java/io/IOException
    //   107	140	213	java/io/IOException
    //   144	150	213	java/io/IOException
    //   158	190	213	java/io/IOException
  }
  
  private void a(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = paramHttpEntityEnclosingRequest.getAllHeaders();
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localStringBuffer.append(localObject[i].toString()).append("\n");
      i += 1;
    }
    localStringBuffer.append(paramHttpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
    if (paramHttpEntityEnclosingRequest.getEntity() != null) {}
    try
    {
      paramHttpEntityEnclosingRequest = paramHttpEntityEnclosingRequest.getEntity().getContent();
      if (paramHttpEntityEnclosingRequest != null)
      {
        i = paramHttpEntityEnclosingRequest.available();
        if (i > 0)
        {
          localObject = new byte[i];
          paramHttpEntityEnclosingRequest.read((byte[])localObject);
          localStringBuffer.append("POST:\n");
          localStringBuffer.append(new String((byte[])localObject)).append("\n");
        }
      }
    }
    catch (IOException paramHttpEntityEnclosingRequest)
    {
      for (;;)
      {
        z.V("Error Writing hit to log...");
      }
    }
    z.V(localStringBuffer.toString());
  }
  
  private HttpEntityEnclosingRequest h(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
    {
      z.W("Empty hit, discarding.");
      return null;
    }
    String str = paramString2 + "?" + paramString1;
    if (str.length() < 2036) {
      paramString1 = new BasicHttpEntityEnclosingRequest("GET", str);
    }
    for (;;)
    {
      paramString1.addHeader("User-Agent", this.vW);
      return paramString1;
      paramString2 = new BasicHttpEntityEnclosingRequest("POST", paramString2);
      try
      {
        paramString2.setEntity(new StringEntity(paramString1));
        paramString1 = paramString2;
      }
      catch (UnsupportedEncodingException paramString1)
      {
        z.W("Encoding error, discarding hit");
      }
    }
    return null;
  }
  
  public int a(List<w> paramList, aa paramaa, boolean paramBoolean)
  {
    int m = 0;
    int n = Math.min(paramList.size(), 40);
    paramaa.e("_hr", paramList.size());
    int i = 0;
    Object localObject1 = null;
    boolean bool1 = true;
    int k = 0;
    if (k < n)
    {
      w localw = (w)paramList.get(k);
      URL localURL = a(localw);
      int j;
      if (localURL == null)
      {
        if (z.eK()) {
          z.W("No destination: discarding hit: " + localw.eF());
        }
        for (;;)
        {
          i += 1;
          j = m + 1;
          k += 1;
          m = j;
          break;
          z.W("No destination: discarding hit.");
        }
      }
      Object localObject2 = new HttpHost(localURL.getHost(), localURL.getPort(), localURL.getProtocol());
      Object localObject3 = localURL.getPath();
      if (TextUtils.isEmpty(localw.eF())) {}
      for (localObject1 = "";; localObject1 = x.a(localw, System.currentTimeMillis()))
      {
        localObject3 = h((String)localObject1, (String)localObject3);
        if (localObject3 != null) {
          break label235;
        }
        i += 1;
        j = m + 1;
        localObject1 = localURL;
        break;
      }
      label235:
      ((HttpEntityEnclosingRequest)localObject3).addHeader("Host", ((HttpHost)localObject2).toHostString());
      if (z.eK()) {
        a((HttpEntityEnclosingRequest)localObject3);
      }
      if (((String)localObject1).length() > 8192)
      {
        z.W("Hit too long (> 8192 bytes)--not sent");
        j = i + 1;
      }
      for (;;)
      {
        paramaa.e("_td", ((String)localObject1).getBytes().length);
        m += 1;
        localObject1 = localURL;
        i = j;
        j = m;
        break;
        if (this.yu.isDryRunEnabled())
        {
          z.U("Dry run enabled. Hit not actually sent.");
          j = i;
        }
        else
        {
          boolean bool2 = bool1;
          boolean bool3;
          if (bool1) {
            bool3 = bool1;
          }
          try
          {
            p.A(this.mContext);
            bool2 = false;
            bool3 = bool2;
            bool1 = bool2;
            localObject2 = this.Bj.execute((HttpHost)localObject2, (HttpRequest)localObject3);
            bool3 = bool2;
            bool1 = bool2;
            int i1 = ((HttpResponse)localObject2).getStatusLine().getStatusCode();
            bool3 = bool2;
            bool1 = bool2;
            localObject3 = ((HttpResponse)localObject2).getEntity();
            if (localObject3 != null)
            {
              bool3 = bool2;
              bool1 = bool2;
              ((HttpEntity)localObject3).consumeContent();
            }
            bool1 = bool2;
            j = i;
            if (i1 != 200)
            {
              bool3 = bool2;
              bool1 = bool2;
              z.W("Bad response: " + ((HttpResponse)localObject2).getStatusLine().getStatusCode());
              bool1 = bool2;
              j = i;
            }
          }
          catch (ClientProtocolException localClientProtocolException)
          {
            z.W("ClientProtocolException sending hit; discarding hit...");
            paramaa.e("_hd", i);
            bool1 = bool3;
            j = i;
          }
          catch (IOException paramList)
          {
            z.W("Exception sending hit: " + paramList.getClass().getSimpleName());
            z.W(paramList.getMessage());
            paramaa.e("_de", 1);
            paramaa.e("_hd", i);
            paramaa.e("_hs", m);
            a(paramaa, localURL, bool1);
            return m;
          }
        }
      }
    }
    paramaa.e("_hd", i);
    paramaa.e("_hs", m);
    if (paramBoolean) {
      a(paramaa, (URL)localObject1, bool1);
    }
    return m;
  }
  
  String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6 });
  }
  
  URL a(w paramw)
  {
    if (this.Bk != null) {
      return this.Bk;
    }
    for (paramw = paramw.eI();; paramw = "https://ssl.google-analytics.com/collect") {
      try
      {
        if ("http:".equals(paramw))
        {
          paramw = "http://www.google-analytics.com/collect";
          paramw = new URL(paramw);
          return paramw;
        }
      }
      catch (MalformedURLException paramw)
      {
        z.T("Error trying to parse the hardcoded host url. This really shouldn't happen.");
        return null;
      }
    }
  }
  
  public void af(String paramString)
  {
    try
    {
      this.Bk = new URL(paramString);
      return;
    }
    catch (MalformedURLException paramString)
    {
      this.Bk = null;
    }
  }
  
  public boolean dX()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      z.V("...no network connectivity");
      return false;
    }
    return true;
  }
  
  boolean eS()
  {
    return Math.random() * 100.0D <= 1.0D;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\com\google\android\gms\analytics\ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */