package br.com.fiap.services;

import android.util.Log;
import br.com.fiap.beans.AulaBean;
import br.com.fiap.beans.CalendarioGraduacaoBean;
import br.com.fiap.beans.CalendarioPosGraduacaoBean;
import br.com.fiap.beans.HorarioBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class AulaService
{
  public List<CalendarioGraduacaoBean> horarios(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Log.i("chave", paramString4);
    localArrayList1 = new ArrayList();
    try
    {
      Object localObject1 = new SoapObject("http://tempuri.org/", "CalendarioAulaGraduacao");
      ((SoapObject)localObject1).addProperty("inRM", paramString1);
      ((SoapObject)localObject1).addProperty("inAno", paramString3);
      ((SoapObject)localObject1).addProperty("stTurma", paramString2);
      ((SoapObject)localObject1).addProperty("stChaveAluno", paramString4);
      ((SoapObject)localObject1).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject1);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/CalendarioAulaGraduacao", paramString1);
      paramString1 = ((SoapPrimitive)paramString1.getResponse()).toString();
      Log.i("Aulas", paramString1);
      paramString1 = new JSONArray(new JSONObject(paramString1).getString("ListaDiaSemana"));
      int i = 0;
      int j;
      for (;;)
      {
        if (i >= paramString1.length()) {
          return localArrayList1;
        }
        paramString3 = new JSONObject(paramString1.getString(i));
        paramString2 = new CalendarioGraduacaoBean();
        paramString2.setDiasemana(Integer.parseInt(paramString3.getString("DiaSemana")));
        paramString3 = new JSONArray(paramString3.getString("ListaHorarios"));
        paramString4 = new ArrayList();
        j = 0;
        if (j < paramString3.length()) {
          break;
        }
        paramString2.setHorarios(paramString4);
        localArrayList1.add(paramString2);
        i += 1;
      }
      Object localObject2 = new JSONObject(paramString3.getString(j));
      localObject1 = new HorarioBean();
      ((HorarioBean)localObject1).setHorario(Integer.parseInt(((JSONObject)localObject2).getString("Horario")));
      localObject2 = new JSONArray(((JSONObject)localObject2).getString("ListaAulas"));
      ArrayList localArrayList2 = new ArrayList();
      int k = 0;
      for (;;)
      {
        if (k >= ((JSONArray)localObject2).length())
        {
          ((HorarioBean)localObject1).setAula(localArrayList2);
          paramString4.add(localObject1);
          j += 1;
          break;
        }
        JSONObject localJSONObject = new JSONObject(((JSONArray)localObject2).getString(k));
        AulaBean localAulaBean = new AulaBean();
        localAulaBean.setAulaid(Integer.parseInt(localJSONObject.getString("AulaID")));
        localAulaBean.setCodrelacao(Integer.parseInt(localJSONObject.getString("CodigoRelacao")));
        localAulaBean.setPeriodo(localJSONObject.getString("Periodo"));
        localAulaBean.setSala(localJSONObject.getString("Sala"));
        localAulaBean.setPredio(localJSONObject.getString("Predio"));
        localAulaBean.setNomedisciplina(localJSONObject.getString("NomeDisciplina"));
        localAulaBean.setNomeprof(localJSONObject.getString("NomeProfessor"));
        localAulaBean.setHorainicio(localJSONObject.getString("HorarioInicio"));
        localAulaBean.setHorafim(localJSONObject.getString("HorarioTermino"));
        localArrayList2.add(localAulaBean);
        k += 1;
      }
      return localArrayList1;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      return localArrayList1;
    }
    catch (XmlPullParserException paramString1)
    {
      paramString1.printStackTrace();
      return localArrayList1;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public List<CalendarioPosGraduacaoBean> horariosPos(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Log.i("chave", paramString4);
    paramString3 = new ArrayList();
    try
    {
      Object localObject = new GregorianCalendar();
      int i = ((Calendar)localObject).get(2);
      int j = ((Calendar)localObject).get(1);
      localObject = new SoapObject("http://tempuri.org/", "CalendarioAulaPosGraduacao");
      ((SoapObject)localObject).addProperty("inRM", paramString1);
      ((SoapObject)localObject).addProperty("inAno", Integer.valueOf(j));
      ((SoapObject)localObject).addProperty("stTurma", paramString2);
      ((SoapObject)localObject).addProperty("stChaveAluno", paramString4);
      ((SoapObject)localObject).addProperty("stTipo", "C");
      ((SoapObject)localObject).addProperty("inMes", Integer.valueOf(i + 1));
      ((SoapObject)localObject).addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/CalendarioAulaPosGraduacao", paramString1);
      paramString1 = new JSONArray(new JSONObject(((SoapPrimitive)paramString1.getResponse()).toString()).getString("ListaAula"));
      i = 0;
      for (;;)
      {
        if (i >= paramString1.length()) {
          return paramString3;
        }
        paramString2 = new JSONObject(paramString1.getString(i));
        paramString4 = new CalendarioPosGraduacaoBean();
        paramString4.setCodrelacao(paramString2.getInt("CodigoRelacao"));
        paramString4.setHorainicio(paramString2.getString("HorarioInicio"));
        paramString4.setHorafim(paramString2.getString("HorarioTermino"));
        paramString4.setDataaula(paramString2.getString("DataAula"));
        paramString4.setPeriodo(paramString2.getString("Periodo"));
        paramString4.setHorario(paramString2.getString("Horario"));
        paramString4.setSala(paramString2.getString("Sala"));
        paramString4.setPredio(paramString2.getString("Predio"));
        paramString4.setNomedisciplina(paramString2.getString("NomeDisciplina"));
        paramString4.setProfessor(paramString2.getString("NomeProfessor"));
        paramString4.setCancelada(paramString2.getBoolean("Cancelada"));
        paramString4.setRemarcada(paramString2.getBoolean("Remarcado"));
        paramString4.setMes(paramString2.getInt("Mes"));
        paramString4.setAno(paramString2.getInt("Ano"));
        paramString3.add(paramString4);
        i += 1;
      }
      return paramString3;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      return paramString3;
    }
    catch (XmlPullParserException paramString1)
    {
      paramString1.printStackTrace();
      return paramString3;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\services\AulaService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */