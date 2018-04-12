package br.com.fiap.services;

import android.util.Log;
import br.com.fiap.beans.BoletimBean;
import br.com.fiap.beans.BoletimPosBean;
import br.com.fiap.beans.FaltaBean;
import br.com.fiap.beans.FaltaPosBean;
import br.com.fiap.beans.ListaNacBean;
import br.com.fiap.beans.NotaBean;
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

public class BoletimService
{
  public BoletimBean boletim(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    localBoletimBean = new BoletimBean();
    try
    {
      Object localObject1 = new SoapObject("http://tempuri.org/", "Boletim");
      ((SoapObject)localObject1).addProperty("inRM", paramString1);
      ((SoapObject)localObject1).addProperty("inAno", paramString3);
      ((SoapObject)localObject1).addProperty("stTurma", paramString4);
      ((SoapObject)localObject1).addProperty("stChaveAluno", paramString2);
      ((SoapObject)localObject1).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject1);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/Boletim", paramString1);
      paramString1 = new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString());
      localBoletimBean.setMensagem(paramString1.getString("Mensagem"));
      localBoletimBean.setExibir2sem(paramString1.getString("Exibi2Semestre"));
      localBoletimBean.setNomecol(paramString1.getString("NomeColuna_AM_TI"));
      paramString1 = new JSONArray(paramString1.getString("ListaNotas"));
      paramString2 = new ArrayList();
      int i = 0;
      if (i >= paramString1.length())
      {
        localBoletimBean.setNotas(paramString2);
        return localBoletimBean;
      }
      paramString4 = new JSONObject(paramString1.getString(i));
      paramString3 = new NotaBean();
      paramString3.setCodrelacao(paramString4.getInt("CodigoRelacao"));
      paramString3.setDisciplina(paramString4.getString("Disciplina"));
      paramString3.setProfessor(paramString4.getString("NomeProfessor"));
      paramString3.setNac1(paramString4.getString("Nac1"));
      paramString3.setAm1(paramString4.getString("Am1"));
      paramString3.setPs1(paramString4.getString("Ps1"));
      paramString3.setFalta1(paramString4.getString("Falta1"));
      paramString3.setMd1(paramString4.getString("Md1"));
      paramString3.setNac2(paramString4.getString("Nac2"));
      paramString3.setAm2(paramString4.getString("Am2"));
      paramString3.setPs2(paramString4.getString("Ps2"));
      paramString3.setFalta2(paramString4.getString("Falta2"));
      paramString3.setMd2(paramString4.getString("Md2"));
      paramString3.setMp(paramString4.getString("Mp"));
      paramString3.setExa(paramString4.getString("Exa"));
      paramString3.setMf(paramString4.getString("Mf"));
      paramString3.setFaltas(paramString4.getString("TotalFaltas"));
      paramString3.setFrenquencia(paramString4.getString("Frequencia"));
      paramString3.setCh(paramString4.getString("Ch"));
      paramString3.setSituacao(paramString4.getString("Situacao"));
      Object localObject2 = new JSONArray(paramString4.getString("ListaNac1"));
      localObject1 = new ArrayList();
      int j = 0;
      label472:
      Object localObject3;
      if (j >= ((JSONArray)localObject2).length())
      {
        localObject2 = new JSONArray(paramString4.getString("ListaNac2"));
        localObject3 = new ArrayList();
        j = 0;
        label510:
        if (j < ((JSONArray)localObject2).length()) {
          break label759;
        }
        paramString3.setListanac1((List)localObject1);
        paramString3.setListanac2((List)localObject3);
        localObject2 = new JSONArray(paramString4.getString("ListaFalta1"));
        localObject1 = new ArrayList();
        j = 0;
        label560:
        if (j < ((JSONArray)localObject2).length()) {
          break label881;
        }
        paramString4 = new JSONArray(paramString4.getString("ListaFalta2"));
        localObject2 = new ArrayList();
        j = 0;
      }
      for (;;)
      {
        if (j >= paramString4.length())
        {
          paramString3.setFaltas1((List)localObject1);
          paramString3.setFaltas2((List)localObject2);
          paramString2.add(paramString3);
          i += 1;
          break;
          localObject3 = new JSONObject(((JSONArray)localObject2).getString(j));
          localObject4 = new ListaNacBean();
          ((ListaNacBean)localObject4).setDesc(((JSONObject)localObject3).getString("Descricao"));
          ((ListaNacBean)localObject4).setValor(((JSONObject)localObject3).getString("ValorNac"));
          ((ListaNacBean)localObject4).setNota(((JSONObject)localObject3).getString("Nota"));
          ((ListaNacBean)localObject4).setData(((JSONObject)localObject3).getString("Data"));
          ((ListaNacBean)localObject4).setDescarte(((JSONObject)localObject3).getString("Descartado"));
          ((ListaNacBean)localObject4).setValido(((JSONObject)localObject3).getString("Valido"));
          ((List)localObject1).add(localObject4);
          j += 1;
          break label472;
          label759:
          localObject4 = new JSONObject(((JSONArray)localObject2).getString(j));
          ListaNacBean localListaNacBean = new ListaNacBean();
          localListaNacBean.setDesc(((JSONObject)localObject4).getString("Descricao"));
          localListaNacBean.setValor(((JSONObject)localObject4).getString("ValorNac"));
          localListaNacBean.setNota(((JSONObject)localObject4).getString("Nota"));
          localListaNacBean.setData(((JSONObject)localObject4).getString("Data"));
          localListaNacBean.setDescarte(((JSONObject)localObject4).getString("Descartado"));
          localListaNacBean.setValido(((JSONObject)localObject4).getString("Valido"));
          ((List)localObject3).add(localListaNacBean);
          j += 1;
          break label510;
          label881:
          localObject3 = new JSONObject(((JSONArray)localObject2).getString(j));
          localObject4 = new FaltaBean();
          ((FaltaBean)localObject4).setData(((JSONObject)localObject3).getString("Data"));
          ((FaltaBean)localObject4).setQtd(((JSONObject)localObject3).getInt("Quantidade"));
          ((List)localObject1).add(localObject4);
          j += 1;
          break label560;
        }
        localObject3 = new JSONObject(paramString4.getString(j));
        Object localObject4 = new FaltaBean();
        ((FaltaBean)localObject4).setData(((JSONObject)localObject3).getString("Data"));
        ((FaltaBean)localObject4).setQtd(((JSONObject)localObject3).getInt("Quantidade"));
        ((List)localObject2).add(localObject4);
        j += 1;
      }
      return localBoletimBean;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      return localBoletimBean;
    }
    catch (XmlPullParserException paramString1)
    {
      paramString1.printStackTrace();
      return localBoletimBean;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public List<BoletimPosBean> boletimpos(String paramString1, String paramString2, String paramString3)
  {
    localArrayList = new ArrayList();
    Log.i("turma", paramString3);
    try
    {
      Object localObject1 = new SoapObject("http://tempuri.org/", "BoletimPos");
      ((SoapObject)localObject1).addProperty("inRM", paramString1);
      ((SoapObject)localObject1).addProperty("stChaveAluno", paramString2);
      ((SoapObject)localObject1).addProperty("stTurma", paramString3);
      ((SoapObject)localObject1).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject1);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/BoletimPos", paramString1);
      paramString1 = new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString());
      paramString2 = new JSONArray(paramString1.getString("ListaNotas"));
      int i = 0;
      if (i >= paramString2.length()) {
        return localArrayList;
      }
      Object localObject2 = new JSONObject(paramString2.getString(i));
      paramString3 = new BoletimPosBean();
      paramString3.setCodrelacao(((JSONObject)localObject2).getInt("CodigoRelacao"));
      paramString3.setNrmodulo(((JSONObject)localObject2).getInt("NumeroModulo"));
      paramString3.setCh(((JSONObject)localObject2).getInt("Ch"));
      paramString3.setUsaartigo(paramString1.getInt("UsaNotaArtigo"));
      paramString3.setNomemodulo(((JSONObject)localObject2).getString("NomeDisciplina"));
      paramString3.setNota(((JSONObject)localObject2).getString("Nota"));
      paramString3.setNotaartigo(((JSONObject)localObject2).getString("NotaArtigo"));
      paramString3.setNotasub(((JSONObject)localObject2).getString("NotaSub"));
      paramString3.setStatus(((JSONObject)localObject2).getString("Status"));
      paramString3.setFaltas(Double.valueOf(((JSONObject)localObject2).getDouble("Faltas")));
      paramString3.setMediafinal(((JSONObject)localObject2).getString("MediaFinal"));
      localObject1 = new ArrayList();
      localObject2 = new JSONArray(((JSONObject)localObject2).getString("ListaFalta"));
      int j = 0;
      for (;;)
      {
        if (j >= ((JSONArray)localObject2).length())
        {
          paramString3.setListafaltas((List)localObject1);
          localArrayList.add(paramString3);
          i += 1;
          break;
        }
        JSONObject localJSONObject = new JSONObject(((JSONArray)localObject2).getString(j));
        FaltaPosBean localFaltaPosBean = new FaltaPosBean();
        localFaltaPosBean.setData(localJSONObject.getString("Data"));
        localFaltaPosBean.setQtd(Double.valueOf(localJSONObject.getDouble("Quantidade")));
        ((List)localObject1).add(localFaltaPosBean);
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
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\services\BoletimService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */