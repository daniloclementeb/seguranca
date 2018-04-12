package br.com.fiap.services;

import android.util.Log;
import br.com.fiap.beans.AvisoBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class AvisoService
{
  public List<AvisoBean> aviso(String paramString1, String paramString2)
  {
    localArrayList = new ArrayList();
    try
    {
      Object localObject = new SoapObject("http://tempuri.org/", "AvisoAluno");
      ((SoapObject)localObject).addProperty("inRM", paramString1);
      ((SoapObject)localObject).addProperty("stChaveAluno", paramString2);
      ((SoapObject)localObject).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/AvisoAluno", paramString1);
      paramString1 = new JSONArray(new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString()).getString("ListaAviso"));
      int i = 0;
      for (;;)
      {
        if (i >= paramString1.length()) {
          return localArrayList;
        }
        paramString2 = new JSONObject(paramString1.getString(i));
        localObject = new AvisoBean();
        ((AvisoBean)localObject).setIdaviso(paramString2.getInt("IdAviso"));
        ((AvisoBean)localObject).setTitulo(paramString2.getString("TituloAviso"));
        ((AvisoBean)localObject).setTexto(paramString2.getString("TextoAviso"));
        ((AvisoBean)localObject).setData(paramString2.getString("DataAviso"));
        ((AvisoBean)localObject).setStatus(paramString2.getInt("Status"));
        localArrayList.add(localObject);
        i += 1;
      }
      return localArrayList;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      return localArrayList;
    }
    catch (XmlPullParserException paramString1)
    {
      paramString1.printStackTrace();
      return localArrayList;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public void marcaraviso(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      SoapObject localSoapObject = new SoapObject("http://tempuri.org/", "MarcarAvisoLido");
      localSoapObject.addProperty("inRM", paramString1);
      localSoapObject.addProperty("stChaveAluno", paramString3);
      localSoapObject.addProperty("IdAviso", paramString2);
      localSoapObject.addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localSoapObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/MarcarAvisoLido", paramString1);
      Log.i("resultado", ((SoapPrimitive)paramString1.getResponse()).toString());
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    catch (XmlPullParserException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\services\AvisoService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */