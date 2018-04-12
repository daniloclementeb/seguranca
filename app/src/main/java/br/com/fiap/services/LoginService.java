package br.com.fiap.services;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import br.com.fiap.beans.AlunoBean;
import br.com.fiap.beans.LoginBean;
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

public class LoginService
{
  public void desativaDevice(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      SoapObject localSoapObject = new SoapObject("http://tempuri.org/", "DesativaInformacoesDevice");
      localSoapObject.addProperty("inRM", paramString1);
      localSoapObject.addProperty("stDeviceKey", paramString2);
      localSoapObject.addProperty("stChaveAluno", paramString3);
      localSoapObject.addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localSoapObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/DesativaInformacoesDevice", paramString1);
      Log.i("Desativa Device", ((SoapPrimitive)paramString1.getResponse()).toString());
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
  
  public void gravaInfos(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      SoapObject localSoapObject = new SoapObject("http://tempuri.org/", "GravaInformacaoDevice");
      localSoapObject.addProperty("inRM", paramString1);
      localSoapObject.addProperty("stDeviceKey", paramString2);
      localSoapObject.addProperty("stDeviceName", String.valueOf(Build.MANUFACTURER + " " + Build.PRODUCT));
      localSoapObject.addProperty("stOSVersion", String.valueOf(Build.VERSION.SDK_INT));
      localSoapObject.addProperty("stTipo", "ANDROID");
      localSoapObject.addProperty("stChaveAluno", paramString3);
      localSoapObject.addProperty("inVersao", String.valueOf(1));
      paramString1 = new SoapSerializationEnvelope(110);
      paramString1.dotNet = true;
      paramString1.setOutputSoapObject(localSoapObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/GravaInformacaoDevice", paramString1);
      Log.i("Grava Device", ((SoapPrimitive)paramString1.getResponse()).toString());
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
  
  public AlunoBean logar(LoginBean paramLoginBean, Context paramContext)
  {
    AlunoBean localAlunoBean = new AlunoBean();
    try
    {
      Object localObject = new SoapObject("http://tempuri.org/", "Login");
      ((SoapObject)localObject).addProperty("inRM", paramLoginBean.getInRM());
      ((SoapObject)localObject).addProperty("stSenha", paramLoginBean.getStSenha());
      ((SoapObject)localObject).addProperty("stChave", paramLoginBean.getStChave());
      ((SoapObject)localObject).addProperty("inVersao", String.valueOf(1));
      paramLoginBean = new SoapSerializationEnvelope(110);
      paramLoginBean.dotNet = true;
      paramLoginBean.setOutputSoapObject(localObject);
      new HttpTransportSE("http://www2.fiap.com.br/smaiw/app.asmx").call("http://tempuri.org/Login", paramLoginBean);
      paramLoginBean = new JSONObject(((SoapPrimitive)paramLoginBean.getResponse()).toString());
      ArrayList localArrayList;
      int i;
      if (!paramLoginBean.getString("Turmas").equalsIgnoreCase("null"))
      {
        localObject = new JSONArray(paramLoginBean.getString("Turmas"));
        localArrayList = new ArrayList();
        i = 0;
      }
      for (;;)
      {
        if (i >= ((JSONArray)localObject).length())
        {
          localAlunoBean.setTurma(localArrayList);
          localAlunoBean.setRm(paramLoginBean.getString("RM"));
          localAlunoBean.setNome(paramLoginBean.getString("NomeAluno"));
          localAlunoBean.setEmail(paramLoginBean.getString("Email"));
          localAlunoBean.setChave(paramLoginBean.getString("ChaveAluno"));
          localAlunoBean.setTipoAluno(paramLoginBean.getString("TipoAluno"));
          if (!paramLoginBean.getString("MensagemErro").equalsIgnoreCase("null")) {
            break;
          }
          localAlunoBean.setErro(null);
          return localAlunoBean;
        }
        JSONObject localJSONObject = new JSONObject(((JSONArray)localObject).getString(i));
        TurmaBean localTurmaBean = new TurmaBean();
        localTurmaBean.setNomeTurma(localJSONObject.getString("NomeTurma"));
        localTurmaBean.setCurso(localJSONObject.getString("Curso"));
        localTurmaBean.setCursoID(localJSONObject.getString("CursoID"));
        localTurmaBean.setAno(localJSONObject.getString("Ano"));
        localTurmaBean.setTipoTurma(localJSONObject.getString("TipoTurma"));
        localArrayList.add(localTurmaBean);
        i += 1;
      }
      localAlunoBean.setErro(paramLoginBean.getString("MensagemErro"));
      return localAlunoBean;
    }
    catch (IOException paramLoginBean)
    {
      localAlunoBean.setErro(paramContext.getResources().getString(2131099701));
      paramLoginBean.printStackTrace();
      return localAlunoBean;
    }
    catch (XmlPullParserException paramLoginBean)
    {
      localAlunoBean.setErro(paramContext.getResources().getString(2131099701));
      paramLoginBean.printStackTrace();
      return localAlunoBean;
    }
    catch (JSONException paramLoginBean)
    {
      localAlunoBean.setErro(paramContext.getResources().getString(2131099701));
      paramLoginBean.printStackTrace();
    }
    return localAlunoBean;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\services\LoginService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */