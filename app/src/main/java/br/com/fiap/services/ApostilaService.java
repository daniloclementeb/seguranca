package br.com.fiap.services;

import br.com.fiap.beans.ApostilaBean;
import br.com.fiap.beans.DisciplinaBean;
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

public class ApostilaService
{
  public List<ApostilaBean> apostilas(String paramString1, String paramString2, String paramString3)
  {
    localArrayList = new ArrayList();
    try
    {
      SoapObject localSoapObject = new SoapObject("http://tempuri.org/", "Apostila");
      localSoapObject.addProperty("inRM", paramString1);
      localSoapObject.addProperty("inCodigoRelacao", paramString3);
      localSoapObject.addProperty("stChaveAluno", paramString2);
      localSoapObject.addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localSoapObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/Apostila", paramString1);
      paramString1 = new JSONArray(new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString()).getString("ListaApostila"));
      int i = 0;
      for (;;)
      {
        if (i >= paramString1.length()) {
          return localArrayList;
        }
        paramString2 = new JSONObject(paramString1.getString(i));
        paramString3 = new ApostilaBean();
        paramString3.setApostilaid(paramString2.getInt("ApostilaID"));
        paramString3.setTitulo(paramString2.getString("Titulo"));
        paramString3.setUrl(paramString2.getString("DownloadURL"));
        paramString3.setTamanho(paramString2.getString("TamanhoMB"));
        paramString3.setMedida(paramString2.getString("MedidaTamanho"));
        paramString3.setExtencao(paramString2.getString("Extensao"));
        paramString3.setData(paramString2.getString("DataUpload"));
        localArrayList.add(paramString3);
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
  
  public List<DisciplinaBean> disciplinas(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    localArrayList = new ArrayList();
    try
    {
      SoapObject localSoapObject = new SoapObject("http://tempuri.org/", "DisciplinasTurma");
      localSoapObject.addProperty("inRM", paramString1);
      localSoapObject.addProperty("inAno", paramString3);
      localSoapObject.addProperty("stTurma", paramString4);
      localSoapObject.addProperty("stChaveAluno", paramString2);
      localSoapObject.addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localSoapObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/DisciplinasTurma", paramString1);
      paramString1 = new JSONArray(new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString()).getString("ListaRelacao"));
      int i = 0;
      for (;;)
      {
        if (i >= paramString1.length()) {
          return localArrayList;
        }
        paramString2 = new JSONObject(paramString1.getString(i));
        paramString3 = new DisciplinaBean();
        paramString3.setCodrelacao(paramString2.getInt("CodigoRelacao"));
        paramString3.setNomedisciplina(paramString2.getString("NomeDisciplina"));
        paramString3.setNomeprofessor(paramString2.getString("NomeProfessor"));
        localArrayList.add(paramString3);
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
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\services\ApostilaService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */