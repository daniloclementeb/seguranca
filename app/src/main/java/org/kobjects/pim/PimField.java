package org.kobjects.pim;

import java.util.Enumeration;
import java.util.Hashtable;

public class PimField
{
  String name;
  Hashtable properties;
  Object value;
  
  public PimField(String paramString)
  {
    this.name = paramString;
  }
  
  public PimField(PimField paramPimField)
  {
    this(paramPimField.name);
    Object localObject;
    if ((paramPimField.value instanceof String[]))
    {
      localObject = new String[((String[])paramPimField.value).length];
      System.arraycopy((String[])paramPimField.value, 0, localObject, 0, localObject.length);
    }
    for (this.value = localObject; paramPimField.properties != null; this.value = paramPimField.value)
    {
      this.properties = new Hashtable();
      localObject = paramPimField.properties.keys();
      while (((Enumeration)localObject).hasMoreElements())
      {
        String str = (String)((Enumeration)localObject).nextElement();
        this.properties.put(str, paramPimField.properties.get(str));
      }
    }
  }
  
  public boolean getAttribute(String paramString)
  {
    String str = getProperty("type");
    if (str == null) {}
    while (str.indexOf(paramString) == -1) {
      return false;
    }
    return true;
  }
  
  public String getProperty(String paramString)
  {
    if (this.properties == null) {
      return null;
    }
    return (String)this.properties.get(paramString);
  }
  
  public Object getValue()
  {
    return this.value;
  }
  
  public Enumeration propertyNames()
  {
    return this.properties.keys();
  }
  
  public void setAttribute(String paramString, boolean paramBoolean)
  {
    if (getAttribute(paramString) == paramBoolean) {
      return;
    }
    String str2 = getProperty("type");
    String str1;
    if (paramBoolean)
    {
      str1 = paramString;
      if (str2 != null)
      {
        if (str2.length() != 0) {
          break label50;
        }
        str1 = paramString;
      }
    }
    for (;;)
    {
      setProperty("type", str1);
      return;
      label50:
      str1 = str2 + paramString;
      continue;
      int j = str2.indexOf(paramString);
      int i = j;
      if (j > 0) {
        i = j - 1;
      }
      if (i != -1) {
        str1 = str2.substring(0, i) + str2.substring(i + paramString.length() + 1);
      } else {
        str1 = str2;
      }
    }
  }
  
  public void setProperty(String paramString1, String paramString2)
  {
    if (this.properties == null)
    {
      if (paramString2 == null) {
        return;
      }
      this.properties = new Hashtable();
    }
    if (paramString2 == null)
    {
      this.properties.remove(paramString1);
      return;
    }
    this.properties.put(paramString1, paramString2);
  }
  
  public void setValue(Object paramObject)
  {
    this.value = paramObject;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer().append(this.name);
    if (this.properties != null) {}
    for (String str = ";" + this.properties;; str = "") {
      return str + ":" + this.value;
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\org\kobjects\pim\PimField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */