package org.ksoap2.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.kxml2.io.KXmlParser;
import org.kxml2.io.KXmlSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public class SoapServlet
  extends HttpServlet
{
  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(120);
  Hashtable instanceMap = new Hashtable();
  
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    Object localObject;
    for (;;)
    {
      try
      {
        localObject = getInstance(paramHttpServletRequest);
        KXmlParser localKXmlParser = new KXmlParser();
        localKXmlParser.setInput(paramHttpServletRequest.getInputStream(), paramHttpServletRequest.getCharacterEncoding());
        localKXmlParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        this.envelope.parse(localKXmlParser);
        paramHttpServletRequest = invoke(localObject, (SoapObject)this.envelope.bodyIn);
        System.out.println("result: " + paramHttpServletRequest);
        this.envelope.bodyOut = paramHttpServletRequest;
        paramHttpServletResponse.setContentType("text/xml; charset=utf-8");
        paramHttpServletResponse.setHeader("Connection", "close");
        paramHttpServletRequest = new StringWriter();
        localObject = new KXmlSerializer();
        ((XmlSerializer)localObject).setOutput(paramHttpServletRequest);
      }
      catch (SoapFault paramHttpServletRequest)
      {
        paramHttpServletRequest.printStackTrace();
        this.envelope.bodyOut = paramHttpServletRequest;
        paramHttpServletResponse.setStatus(500);
        paramHttpServletResponse.setContentType("text/xml; charset=utf-8");
        paramHttpServletResponse.setHeader("Connection", "close");
        paramHttpServletRequest = new StringWriter();
        localObject = new KXmlSerializer();
        ((XmlSerializer)localObject).setOutput(paramHttpServletRequest);
        try
        {
          this.envelope.write((XmlSerializer)localObject);
          ((XmlSerializer)localObject).flush();
          System.out.println("result xml: " + paramHttpServletRequest);
          localObject = paramHttpServletResponse.getWriter();
          ((Writer)localObject).write(paramHttpServletRequest.toString());
          ((Writer)localObject).close();
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          continue;
        }
      }
      catch (Throwable paramHttpServletRequest)
      {
        paramHttpServletRequest.printStackTrace();
        localObject = new SoapFault();
        ((SoapFault)localObject).faultcode = "Server";
        ((SoapFault)localObject).faultstring = paramHttpServletRequest.getMessage();
        this.envelope.bodyOut = localObject;
        paramHttpServletResponse.setStatus(500);
        paramHttpServletResponse.setContentType("text/xml; charset=utf-8");
        paramHttpServletResponse.setHeader("Connection", "close");
        paramHttpServletRequest = new StringWriter();
        localObject = new KXmlSerializer();
        ((XmlSerializer)localObject).setOutput(paramHttpServletRequest);
        try
        {
          this.envelope.write((XmlSerializer)localObject);
          ((XmlSerializer)localObject).flush();
          System.out.println("result xml: " + paramHttpServletRequest);
          localObject = paramHttpServletResponse.getWriter();
          ((Writer)localObject).write(paramHttpServletRequest.toString());
          ((Writer)localObject).close();
        }
        catch (Exception localException3)
        {
          localException3.printStackTrace();
          continue;
        }
      }
      finally
      {
        paramHttpServletResponse.setContentType("text/xml; charset=utf-8");
        paramHttpServletResponse.setHeader("Connection", "close");
        localObject = new StringWriter();
        localKXmlSerializer = new KXmlSerializer();
        localKXmlSerializer.setOutput((Writer)localObject);
      }
      try
      {
        this.envelope.write((XmlSerializer)localObject);
        ((XmlSerializer)localObject).flush();
        System.out.println("result xml: " + paramHttpServletRequest);
        localObject = paramHttpServletResponse.getWriter();
        ((Writer)localObject).write(paramHttpServletRequest.toString());
        ((Writer)localObject).close();
        paramHttpServletResponse.flushBuffer();
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
    }
    try
    {
      KXmlSerializer localKXmlSerializer;
      this.envelope.write(localKXmlSerializer);
      localKXmlSerializer.flush();
      System.out.println("result xml: " + localObject);
      paramHttpServletResponse = paramHttpServletResponse.getWriter();
      paramHttpServletResponse.write(((StringWriter)localObject).toString());
      paramHttpServletResponse.close();
      throw paramHttpServletRequest;
    }
    catch (Exception localException4)
    {
      for (;;)
      {
        localException4.printStackTrace();
      }
    }
  }
  
  public SoapSerializationEnvelope getEnvelope()
  {
    return this.envelope;
  }
  
  protected Object getInstance(HttpServletRequest paramHttpServletRequest)
  {
    if (paramHttpServletRequest.getPathInfo() == null) {}
    do
    {
      return this;
      paramHttpServletRequest = this.instanceMap.get(paramHttpServletRequest.getPathInfo());
    } while (paramHttpServletRequest == null);
    return paramHttpServletRequest;
  }
  
  SoapObject invoke(Object paramObject, SoapObject paramSoapObject)
    throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    String str = paramSoapObject.getName();
    Class[] arrayOfClass = new Class[paramSoapObject.getPropertyCount()];
    Object[] arrayOfObject = new Object[paramSoapObject.getPropertyCount()];
    PropertyInfo localPropertyInfo = new PropertyInfo();
    Hashtable localHashtable = new Hashtable();
    int i = 0;
    while (i < arrayOfClass.length)
    {
      paramSoapObject.getPropertyInfo(i, localHashtable, localPropertyInfo);
      arrayOfClass[i] = ((Class)localPropertyInfo.type);
      arrayOfObject[i] = paramSoapObject.getProperty(i);
      i += 1;
    }
    paramObject = paramObject.getClass().getMethod(str, arrayOfClass).invoke(paramObject, arrayOfObject);
    System.out.println("result:" + paramObject);
    paramSoapObject = new SoapObject(paramSoapObject.getNamespace(), str + "Response");
    if (paramObject != null) {
      paramSoapObject.addProperty("return", paramObject);
    }
    return paramSoapObject;
  }
  
  public void publishClass(Class paramClass, String paramString)
  {
    Method[] arrayOfMethod = paramClass.getMethods();
    int i = 0;
    while (i < arrayOfMethod.length)
    {
      if (Modifier.isPublic(arrayOfMethod[i].getModifiers()))
      {
        Class[] arrayOfClass = arrayOfMethod[i].getParameterTypes();
        PropertyInfo[] arrayOfPropertyInfo = new PropertyInfo[arrayOfClass.length];
        int j = 0;
        while (j < arrayOfClass.length)
        {
          arrayOfPropertyInfo[j] = new PropertyInfo();
          arrayOfPropertyInfo[j].type = arrayOfClass[j];
          j += 1;
        }
        publishMethod(paramClass, paramString, arrayOfMethod[i].getName(), arrayOfPropertyInfo);
      }
      i += 1;
    }
  }
  
  public void publishInstance(String paramString, Object paramObject)
  {
    this.instanceMap.put(paramString, paramObject);
  }
  
  public void publishMethod(Class paramClass, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    int j = 0;
    Object localObject = paramClass.getMethods();
    int i = 0;
    while (i < localObject.length)
    {
      if ((localObject[i].getName().equals(paramString2)) && (localObject[i].getParameterTypes().length == paramArrayOfString.length))
      {
        localObject = localObject[i].getParameterTypes();
        PropertyInfo[] arrayOfPropertyInfo = new PropertyInfo[localObject.length];
        i = j;
        while (i < localObject.length)
        {
          arrayOfPropertyInfo[i] = new PropertyInfo();
          arrayOfPropertyInfo[i].name = paramArrayOfString[i];
          arrayOfPropertyInfo[i].type = localObject[i];
          i += 1;
        }
        publishMethod(paramClass, paramString1, paramString2, arrayOfPropertyInfo);
        return;
      }
      i += 1;
    }
    throw new RuntimeException("Method not found!");
  }
  
  public void publishMethod(Class paramClass, String paramString1, String paramString2, PropertyInfo[] paramArrayOfPropertyInfo)
  {
    paramClass = new SoapObject(paramString1, paramString2);
    int i = 0;
    while (i < paramArrayOfPropertyInfo.length)
    {
      paramClass.addProperty(paramArrayOfPropertyInfo[i], null);
      i += 1;
    }
    this.envelope.addTemplate(paramClass);
  }
  
  public void setEnvelope(SoapSerializationEnvelope paramSoapSerializationEnvelope)
  {
    this.envelope = paramSoapSerializationEnvelope;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\org\ksoap2\servlet\SoapServlet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */