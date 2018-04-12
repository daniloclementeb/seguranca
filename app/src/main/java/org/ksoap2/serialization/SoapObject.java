package org.ksoap2.serialization;

import java.util.Hashtable;
import java.util.Vector;

public class SoapObject
  implements KvmSerializable
{
  Vector data = new Vector();
  Vector info = new Vector();
  String name;
  String namespace;
  
  public SoapObject(String paramString1, String paramString2)
  {
    this.namespace = paramString1;
    this.name = paramString2;
  }
  
  public SoapObject addProperty(String paramString, Object paramObject)
  {
    PropertyInfo localPropertyInfo = new PropertyInfo();
    localPropertyInfo.name = paramString;
    if (paramObject == null) {}
    for (paramString = PropertyInfo.OBJECT_CLASS;; paramString = paramObject.getClass())
    {
      localPropertyInfo.type = paramString;
      return addProperty(localPropertyInfo, paramObject);
    }
  }
  
  public SoapObject addProperty(PropertyInfo paramPropertyInfo, Object paramObject)
  {
    this.info.addElement(paramPropertyInfo);
    this.data.addElement(paramObject);
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof SoapObject)) {
      return false;
    }
    paramObject = (SoapObject)paramObject;
    int j = this.data.size();
    if (j != ((SoapObject)paramObject).data.size()) {
      return false;
    }
    int i = 0;
    while (i < j) {
      try
      {
        boolean bool = this.data.elementAt(i).equals(((SoapObject)paramObject).getProperty(((PropertyInfo)this.info.elementAt(i)).name));
        if (!bool) {
          return false;
        }
        i += 1;
      }
      catch (Exception paramObject)
      {
        return false;
      }
    }
    return true;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getNamespace()
  {
    return this.namespace;
  }
  
  public Object getProperty(int paramInt)
  {
    return this.data.elementAt(paramInt);
  }
  
  public Object getProperty(String paramString)
  {
    int i = 0;
    while (i < this.data.size())
    {
      if (paramString.equals(((PropertyInfo)this.info.elementAt(i)).name)) {
        return this.data.elementAt(i);
      }
      i += 1;
    }
    throw new RuntimeException("illegal property: " + paramString);
  }
  
  public int getPropertyCount()
  {
    return this.data.size();
  }
  
  public void getPropertyInfo(int paramInt, Hashtable paramHashtable, PropertyInfo paramPropertyInfo)
  {
    paramHashtable = (PropertyInfo)this.info.elementAt(paramInt);
    paramPropertyInfo.name = paramHashtable.name;
    paramPropertyInfo.namespace = paramHashtable.namespace;
    paramPropertyInfo.flags = paramHashtable.flags;
    paramPropertyInfo.type = paramHashtable.type;
    paramPropertyInfo.elementType = paramHashtable.elementType;
  }
  
  public SoapObject newInstance()
  {
    SoapObject localSoapObject = new SoapObject(this.namespace, this.name);
    int i = 0;
    while (i < this.data.size())
    {
      localSoapObject.addProperty((PropertyInfo)this.info.elementAt(i), this.data.elementAt(i));
      i += 1;
    }
    return localSoapObject;
  }
  
  public void setProperty(int paramInt, Object paramObject)
  {
    this.data.setElementAt(paramObject, paramInt);
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("" + this.name + "{");
    int i = 0;
    while (i < getPropertyCount())
    {
      localStringBuffer.append("" + ((PropertyInfo)this.info.elementAt(i)).name + "=" + getProperty(i) + "; ");
      i += 1;
    }
    localStringBuffer.append("}");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\org\ksoap2\serialization\SoapObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */