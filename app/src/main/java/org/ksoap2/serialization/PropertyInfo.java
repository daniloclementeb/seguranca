package org.ksoap2.serialization;

import java.util.Vector;

public class PropertyInfo
{
  public static final Class BOOLEAN_CLASS;
  public static final Class INTEGER_CLASS;
  public static final Class LONG_CLASS;
  public static final int MULTI_REF = 2;
  public static final Class OBJECT_CLASS = new Object().getClass();
  public static final PropertyInfo OBJECT_TYPE = new PropertyInfo();
  public static final int REF_ONLY = 4;
  public static final Class STRING_CLASS = "".getClass();
  public static final int TRANSIENT = 1;
  public static final Class VECTOR_CLASS;
  public PropertyInfo elementType;
  public int flags;
  public boolean multiRef;
  public String name;
  public String namespace;
  public Object type = OBJECT_CLASS;
  
  static
  {
    INTEGER_CLASS = new Integer(0).getClass();
    LONG_CLASS = new Long(0L).getClass();
    BOOLEAN_CLASS = new Boolean(true).getClass();
    VECTOR_CLASS = new Vector().getClass();
  }
  
  public void clear()
  {
    this.type = OBJECT_CLASS;
    this.flags = 0;
    this.name = null;
    this.namespace = null;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\org\ksoap2\serialization\PropertyInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */