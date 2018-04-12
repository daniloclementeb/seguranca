package br.com.fiap.services;

import br.com.fiap.beans.TrabalhoPosBean;
import br.com.fiap.beans.TrabalhosPos;
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

public class TrabalhosPosService
{
  public TrabalhosPos trabalhos(String paramString1, String paramString2, String paramString3)
  {
    localTrabalhosPos = new TrabalhosPos();
    try
    {
      Object localObject1 = new SoapObject("http://tempuri.org/", "TrabalhosPos");
      ((SoapObject)localObject1).addProperty("inRM", paramString1);
      ((SoapObject)localObject1).addProperty("inTurma", paramString3);
      ((SoapObject)localObject1).addProperty("stChaveAluno", paramString2);
      ((SoapObject)localObject1).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject1);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/TrabalhosPos", paramString1);
      paramString1 = new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString());
      Object localObject2 = new JSONArray(paramString1.getString("ListaTrabalhosPosPendentes"));
      localObject1 = new JSONArray(paramString1.getString("ListaTrabalhosPosEntregues"));
      paramString2 = new JSONArray(paramString1.getString("ListaTrabalhosPosCorrigidos"));
      paramString3 = new ArrayList();
      int i = 0;
      if (i >= ((JSONArray)localObject2).length())
      {
        localObject2 = new ArrayList();
        i = 0;
        label192:
        if (i < ((JSONArray)localObject1).length()) {
          break label383;
        }
        localObject1 = new ArrayList();
        i = 0;
      }
      for (;;)
      {
        if (i >= paramString2.length())
        {
          localTrabalhosPos.setPendentes(paramString3);
          localTrabalhosPos.setEntregues((List)localObject2);
          localTrabalhosPos.setCorrigidos((List)localObject1);
          localTrabalhosPos.setErro(paramString1.getInt("Erro"));
          localTrabalhosPos.setMsgErro(paramString1.getString("MensagemErro"));
          return localTrabalhosPos;
          localJSONObject = new JSONObject(((JSONArray)localObject2).getString(i));
          localTrabalhoPosBean = new TrabalhoPosBean();
          localTrabalhoPosBean.setCodigoTrabalho(localJSONObject.getInt("CodigoTrabalho"));
          localTrabalhoPosBean.setDataAlterada(localJSONObject.getInt("DataAlterada"));
          localTrabalhoPosBean.setDataEntrega(localJSONObject.getString("DataEntrega"));
          localTrabalhoPosBean.setNomeDisciplina(localJSONObject.getString("NomeDisciplina"));
          localTrabalhoPosBean.setNomeProfessor(localJSONObject.getString("NomeProfessor"));
          localTrabalhoPosBean.setTituloTrabalho(localJSONObject.getString("TituloTrabalho"));
          paramString3.add(localTrabalhoPosBean);
          i += 1;
          break;
          label383:
          localJSONObject = new JSONObject(((JSONArray)localObject1).getString(i));
          localTrabalhoPosBean = new TrabalhoPosBean();
          localTrabalhoPosBean.setCodigoTrabalho(localJSONObject.getInt("CodigoTrabalho"));
          localTrabalhoPosBean.setDataAlterada(localJSONObject.getInt("DataAlterada"));
          localTrabalhoPosBean.setDataEntrega(localJSONObject.getString("DataEntrega"));
          localTrabalhoPosBean.setDataHoraEntregou(localJSONObject.getString("DataHoraEntregou"));
          localTrabalhoPosBean.setNomeDisciplina(localJSONObject.getString("NomeDisciplina"));
          localTrabalhoPosBean.setNomeProfessor(localJSONObject.getString("NomeProfessor"));
          localTrabalhoPosBean.setTituloTrabalho(localJSONObject.getString("TituloTrabalho"));
          ((List)localObject2).add(localTrabalhoPosBean);
          i += 1;
          break label192;
        }
        JSONObject localJSONObject = new JSONObject(paramString2.getString(i));
        TrabalhoPosBean localTrabalhoPosBean = new TrabalhoPosBean();
        localTrabalhoPosBean.setCodigoTrabalho(localJSONObject.getInt("CodigoTrabalho"));
        localTrabalhoPosBean.setDataAlterada(localJSONObject.getInt("DataAlterada"));
        localTrabalhoPosBean.setDataEntrega(localJSONObject.getString("DataEntrega"));
        localTrabalhoPosBean.setDataHoraEntregou(localJSONObject.getString("DataHoraEntregou"));
        localTrabalhoPosBean.setNomeDisciplina(localJSONObject.getString("NomeDisciplina"));
        localTrabalhoPosBean.setNomeProfessor(localJSONObject.getString("NomeProfessor"));
        localTrabalhoPosBean.setNotaRevisada(localJSONObject.getInt("NotaRevisada"));
        localTrabalhoPosBean.setNotaTrabalho(localJSONObject.getString("NotaTrabalho"));
        localTrabalhoPosBean.setTituloTrabalho(localJSONObject.getString("TituloTrabalho"));
        ((List)localObject1).add(localTrabalhoPosBean);
        i += 1;
      }
      return localTrabalhosPos;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      return localTrabalhosPos;
    }
    catch (XmlPullParserException paramString1)
    {
      paramString1.printStackTrace();
      return localTrabalhosPos;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\services\TrabalhosPosService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */