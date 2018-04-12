package br.com.fiap.services;

import br.com.fiap.beans.TurmaBean;
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

public class TurmaService
{
  public List<TurmaBean> turma(String paramString1, String paramString2)
  {
    localArrayList = new ArrayList();
    try
    {
      Object localObject = new SoapObject("http://tempuri.org/", "TurmaAluno");
      ((SoapObject)localObject).addProperty("inRM", paramString1);
      ((SoapObject)localObject).addProperty("stChaveAluno", paramString2);
      ((SoapObject)localObject).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/TurmaAluno", paramString1);
      paramString1 = new JSONArray(new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString()).getString("ListaTurmas"));
      int i = 0;
      for (;;)
      {
        if (i >= paramString1.length()) {
          return localArrayList;
        }
        paramString2 = new JSONObject(paramString1.getString(i));
        localObject = new TurmaBean();
        ((TurmaBean)localObject).setNomeTurma(paramString2.getString("NomeTurma"));
        ((TurmaBean)localObject).setCurso(paramString2.getString("Curso"));
        ((TurmaBean)localObject).setAno(paramString2.getString("Ano"));
        ((TurmaBean)localObject).setCursoID(paramString2.getString("CursoID"));
        ((TurmaBean)localObject).setTipoTurma(paramString2.getString("TipoTurma"));
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


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\services\TurmaService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */