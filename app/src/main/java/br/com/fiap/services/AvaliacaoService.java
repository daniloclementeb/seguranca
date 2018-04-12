package br.com.fiap.services;

import br.com.fiap.beans.AvaliacaoBean;
import br.com.fiap.beans.NacBean;
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

public class AvaliacaoService
{
  public List<AvaliacaoBean> avaliacoes(String paramString1, String paramString2)
  {
    localArrayList = new ArrayList();
    try
    {
      Object localObject = new SoapObject("http://tempuri.org/", "Avaliacoes");
      ((SoapObject)localObject).addProperty("inRM", paramString1);
      ((SoapObject)localObject).addProperty("stChaveAluno", paramString2);
      ((SoapObject)localObject).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/Avaliacoes", paramString1);
      paramString1 = new JSONArray(new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString()).getString("Avaliacao"));
      int i = 0;
      if (i >= paramString1.length()) {
        return localArrayList;
      }
      paramString2 = new JSONObject(paramString1.getString(i));
      localObject = new JSONArray(paramString2.getString("ListaAvaliacao"));
      int j = 0;
      for (;;)
      {
        if (j >= ((JSONArray)localObject).length())
        {
          i += 1;
          break;
        }
        AvaliacaoBean localAvaliacaoBean = new AvaliacaoBean();
        JSONObject localJSONObject = new JSONObject(((JSONArray)localObject).getString(j));
        localAvaliacaoBean.setNome(paramString2.getString("NomeAvaliacao"));
        localAvaliacaoBean.setId(localJSONObject.getInt("Identificacao"));
        localAvaliacaoBean.setCodrelacao(localJSONObject.getInt("CodigoRelacao"));
        localAvaliacaoBean.setTurma(localJSONObject.getString("Turma"));
        localAvaliacaoBean.setProfessor(localJSONObject.getString("Professor"));
        localAvaliacaoBean.setDiscilplina(localJSONObject.getString("Disciplina"));
        localAvaliacaoBean.setData(localJSONObject.getString("Data"));
        localAvaliacaoBean.setHorario(localJSONObject.getInt("Horario"));
        localArrayList.add(localAvaliacaoBean);
        j += 1;
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
  
  public List<NacBean> nacs(String paramString1, String paramString2)
  {
    localArrayList = new ArrayList();
    try
    {
      Object localObject = new SoapObject("http://tempuri.org/", "Avaliacoes");
      ((SoapObject)localObject).addProperty("inRM", paramString1);
      ((SoapObject)localObject).addProperty("stChaveAluno", paramString2);
      ((SoapObject)localObject).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/Avaliacoes", paramString1);
      paramString1 = new JSONArray(new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString()).getString("ListaNac"));
      int i = 0;
      for (;;)
      {
        if (i >= paramString1.length()) {
          return localArrayList;
        }
        paramString2 = new JSONObject(paramString1.getString(i));
        localObject = new NacBean();
        ((NacBean)localObject).setId(paramString2.getInt("Identificacao"));
        ((NacBean)localObject).setCodrelacao(paramString2.getInt("CodigoRelacao"));
        ((NacBean)localObject).setTurma(paramString2.getString("Turma"));
        ((NacBean)localObject).setProfessor(paramString2.getString("Professor"));
        ((NacBean)localObject).setDisciplina(paramString2.getString("Disciplina"));
        ((NacBean)localObject).setData(paramString2.getString("Data"));
        ((NacBean)localObject).setTema(paramString2.getString("Tema"));
        ((NacBean)localObject).setTipo("NAC");
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
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\services\AvaliacaoService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */