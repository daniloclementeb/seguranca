package org.ksoap2.serialization;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class SoapSerializationEnvelope
  extends SoapEnvelope
{
  private static final String ANY_TYPE_LABEL = "anyType";
  private static final String ARRAY_MAPPING_NAME = "Array";
  private static final String ARRAY_TYPE_LABEL = "arrayType";
  static final Marshal DEFAULT_MARSHAL = new DM();
  private static final String HREF_LABEL = "href";
  private static final String ID_LABEL = "id";
  private static final String ITEM_LABEL = "item";
  private static final String NIL_LABEL = "nil";
  private static final String NULL_LABEL = "null";
  protected static final int QNAME_MARSHAL = 3;
  protected static final int QNAME_NAMESPACE = 0;
  protected static final int QNAME_TYPE = 1;
  private static final String ROOT_LABEL = "root";
  private static final String TYPE_LABEL = "type";
  protected Hashtable classToQName = new Hashtable();
  public boolean dotNet;
  Hashtable idMap = new Hashtable();
  public boolean implicitTypes;
  Vector multiRef;
  public Hashtable properties = new Hashtable();
  protected Hashtable qNameToClass = new Hashtable();
  
  public SoapSerializationEnvelope(int paramInt)
  {
    super(paramInt);
    addMapping(this.enc, "Array", PropertyInfo.VECTOR_CLASS);
    DEFAULT_MARSHAL.register(this);
  }
  
  private int getIndex(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null) {}
    while (paramString.length() - paramInt1 < 3) {
      return paramInt2;
    }
    return Integer.parseInt(paramString.substring(paramInt1 + 1, paramString.length() - 1));
  }
  
  private void writeElement(XmlSerializer paramXmlSerializer, Object paramObject1, PropertyInfo paramPropertyInfo, Object paramObject2)
    throws IOException
  {
    if (paramObject2 != null)
    {
      ((Marshal)paramObject2).writeInstance(paramXmlSerializer, paramObject1);
      return;
    }
    if ((paramObject1 instanceof KvmSerializable))
    {
      writeObjectBody(paramXmlSerializer, (KvmSerializable)paramObject1);
      return;
    }
    if ((paramObject1 instanceof Vector))
    {
      writeVectorBody(paramXmlSerializer, (Vector)paramObject1, paramPropertyInfo.elementType);
      return;
    }
    throw new RuntimeException("Cannot serialize: " + paramObject1);
  }
  
  public void addMapping(String paramString1, String paramString2, Class paramClass)
  {
    addMapping(paramString1, paramString2, paramClass, null);
  }
  
  public void addMapping(String paramString1, String paramString2, Class paramClass, Marshal paramMarshal)
  {
    Hashtable localHashtable = this.qNameToClass;
    SoapPrimitive localSoapPrimitive = new SoapPrimitive(paramString1, paramString2, null);
    if (paramMarshal == null) {}
    for (Object localObject = paramClass;; localObject = paramMarshal)
    {
      localHashtable.put(localSoapPrimitive, localObject);
      this.classToQName.put(paramClass.getName(), new Object[] { paramString1, paramString2, null, paramMarshal });
      return;
    }
  }
  
  public void addTemplate(SoapObject paramSoapObject)
  {
    this.qNameToClass.put(new SoapPrimitive(paramSoapObject.namespace, paramSoapObject.name, null), paramSoapObject);
  }
  
  public Object[] getInfo(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null) {
      if (((paramObject2 instanceof SoapObject)) || ((paramObject2 instanceof SoapPrimitive))) {
        paramObject1 = paramObject2;
      }
    }
    for (;;)
    {
      if ((paramObject1 instanceof SoapObject))
      {
        paramObject1 = (SoapObject)paramObject1;
        paramObject1 = new Object[] { ((SoapObject)paramObject1).getNamespace(), ((SoapObject)paramObject1).getName(), null, null };
      }
      do
      {
        return (Object[])paramObject1;
        paramObject1 = paramObject2.getClass();
        break;
        if ((paramObject1 instanceof SoapPrimitive))
        {
          paramObject1 = (SoapPrimitive)paramObject1;
          return new Object[] { ((SoapPrimitive)paramObject1).getNamespace(), ((SoapPrimitive)paramObject1).getName(), null, DEFAULT_MARSHAL };
        }
        if ((!(paramObject1 instanceof Class)) || (paramObject1 == PropertyInfo.OBJECT_CLASS)) {
          break label151;
        }
        paramObject2 = (Object[])this.classToQName.get(((Class)paramObject1).getName());
        paramObject1 = paramObject2;
      } while (paramObject2 != null);
      label151:
      return new Object[] { this.xsd, "anyType", null, null };
    }
  }
  
  public Object getResponse()
    throws SoapFault
  {
    if ((this.bodyIn instanceof SoapFault)) {
      throw ((SoapFault)this.bodyIn);
    }
    KvmSerializable localKvmSerializable = (KvmSerializable)this.bodyIn;
    if (localKvmSerializable.getPropertyCount() == 0) {
      return null;
    }
    return localKvmSerializable.getProperty(0);
  }
  
  public Object getResult()
  {
    KvmSerializable localKvmSerializable = (KvmSerializable)this.bodyIn;
    if (localKvmSerializable.getPropertyCount() == 0) {
      return null;
    }
    return localKvmSerializable.getProperty(0);
  }
  
  public void parseBody(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    this.bodyIn = null;
    paramXmlPullParser.nextTag();
    Object localObject1;
    if ((paramXmlPullParser.getEventType() == 2) && (paramXmlPullParser.getNamespace().equals(this.env)) && (paramXmlPullParser.getName().equals("Fault")))
    {
      localObject1 = new SoapFault();
      ((SoapFault)localObject1).parse(paramXmlPullParser);
      this.bodyIn = localObject1;
    }
    for (;;)
    {
      return;
      while (paramXmlPullParser.getEventType() == 2)
      {
        localObject1 = paramXmlPullParser.getAttributeValue(this.enc, "root");
        Object localObject2 = read(paramXmlPullParser, null, -1, paramXmlPullParser.getNamespace(), paramXmlPullParser.getName(), PropertyInfo.OBJECT_TYPE);
        if (("1".equals(localObject1)) || (this.bodyIn == null)) {
          this.bodyIn = localObject2;
        }
        paramXmlPullParser.nextTag();
      }
    }
  }
  
  public Object read(XmlPullParser paramXmlPullParser, Object paramObject, int paramInt, String paramString1, String paramString2, PropertyInfo paramPropertyInfo)
    throws IOException, XmlPullParserException
  {
    String str3 = paramXmlPullParser.getName();
    String str1 = paramXmlPullParser.getAttributeValue(null, "href");
    if (str1 != null)
    {
      if (paramObject == null) {
        throw new RuntimeException("href at root level?!?");
      }
      paramPropertyInfo = str1.substring(1);
      paramString2 = this.idMap.get(paramPropertyInfo);
      if (paramString2 != null)
      {
        paramString1 = paramString2;
        if (!(paramString2 instanceof FwdRef)) {}
      }
      else
      {
        paramString1 = new FwdRef();
        paramString1.next = ((FwdRef)paramString2);
        paramString1.obj = paramObject;
        paramString1.index = paramInt;
        this.idMap.put(paramPropertyInfo, paramString1);
        paramString1 = null;
      }
      paramXmlPullParser.nextTag();
      paramXmlPullParser.require(3, null, str3);
      paramXmlPullParser.require(3, null, str3);
      return paramString1;
    }
    str1 = paramXmlPullParser.getAttributeValue(this.xsi, "nil");
    String str4 = paramXmlPullParser.getAttributeValue(null, "id");
    paramObject = str1;
    if (str1 == null) {
      paramObject = paramXmlPullParser.getAttributeValue(this.xsi, "null");
    }
    if ((paramObject != null) && (SoapEnvelope.stringToBoolean((String)paramObject)))
    {
      paramXmlPullParser.nextTag();
      paramXmlPullParser.require(3, null, str3);
      paramObject = null;
      if (str4 != null)
      {
        paramString1 = this.idMap.get(str4);
        if (!(paramString1 instanceof FwdRef)) {
          break label516;
        }
        paramString1 = (FwdRef)paramString1;
      }
    }
    for (;;)
    {
      if ((paramString1.obj instanceof KvmSerializable))
      {
        ((KvmSerializable)paramString1.obj).setProperty(paramString1.index, paramObject);
        label288:
        paramString1 = paramString1.next;
        if (paramString1 != null) {}
      }
      else
      {
        label359:
        label516:
        do
        {
          this.idMap.put(str4, paramObject);
          paramString1 = (String)paramObject;
          break;
          paramObject = paramXmlPullParser.getAttributeValue(this.xsi, "type");
          String str2;
          if (paramObject != null)
          {
            paramInt = ((String)paramObject).indexOf(':');
            str2 = ((String)paramObject).substring(paramInt + 1);
            if (paramInt == -1)
            {
              paramObject = "";
              str1 = paramXmlPullParser.getNamespace((String)paramObject);
            }
          }
          for (;;)
          {
            paramString1 = readInstance(paramXmlPullParser, str1, str2, paramPropertyInfo);
            paramObject = paramString1;
            if (paramString1 != null) {
              break;
            }
            paramObject = readUnknown(paramXmlPullParser, str1, str2);
            break;
            paramObject = ((String)paramObject).substring(0, paramInt);
            break label359;
            str1 = paramString1;
            str2 = paramString2;
            if (paramString2 == null)
            {
              str1 = paramString1;
              str2 = paramString2;
              if (paramString1 == null) {
                if (paramXmlPullParser.getAttributeValue(this.enc, "arrayType") != null)
                {
                  str1 = this.enc;
                  str2 = "Array";
                }
                else
                {
                  paramObject = getInfo(paramPropertyInfo.type, null);
                  str1 = (String)paramObject[0];
                  str2 = (String)paramObject[1];
                }
              }
            }
          }
          ((Vector)paramString1.obj).setElementAt(paramObject, paramString1.index);
          break label288;
        } while (paramString1 == null);
        throw new RuntimeException("double ID");
      }
    }
  }
  
  public Object readInstance(XmlPullParser paramXmlPullParser, String paramString1, String paramString2, PropertyInfo paramPropertyInfo)
    throws IOException, XmlPullParserException
  {
    Object localObject = this.qNameToClass.get(new SoapPrimitive(paramString1, paramString2, null));
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof Marshal)) {
      return ((Marshal)localObject).readInstance(paramXmlPullParser, paramString1, paramString2, paramPropertyInfo);
    }
    if ((localObject instanceof SoapObject)) {
      paramString1 = ((SoapObject)localObject).newInstance();
    }
    while ((paramString1 instanceof KvmSerializable))
    {
      readSerializable(paramXmlPullParser, (KvmSerializable)paramString1);
      return paramString1;
      try
      {
        paramString1 = ((Class)localObject).newInstance();
      }
      catch (Exception paramXmlPullParser)
      {
        throw new RuntimeException(paramXmlPullParser.toString());
      }
    }
    if ((paramString1 instanceof Vector))
    {
      readVector(paramXmlPullParser, (Vector)paramString1, paramPropertyInfo.elementType);
      return paramString1;
    }
    throw new RuntimeException("no deserializer for " + paramString1.getClass());
  }
  
  protected void readSerializable(XmlPullParser paramXmlPullParser, KvmSerializable paramKvmSerializable)
    throws IOException, XmlPullParserException
  {
    int i = -1;
    int k = paramKvmSerializable.getPropertyCount();
    PropertyInfo localPropertyInfo = new PropertyInfo();
    String str;
    int j;
    if (paramXmlPullParser.nextTag() != 3)
    {
      str = paramXmlPullParser.getName();
      j = k;
    }
    for (;;)
    {
      if (j == 0) {
        throw new RuntimeException("Unknown Property: " + str);
      }
      int m = i + 1;
      i = m;
      if (m >= k) {
        i = 0;
      }
      paramKvmSerializable.getPropertyInfo(i, this.properties, localPropertyInfo);
      if (((localPropertyInfo.namespace == null) && (str.equals(localPropertyInfo.name))) || ((localPropertyInfo.name == null) && (i == 0)) || ((str.equals(localPropertyInfo.name)) && (paramXmlPullParser.getNamespace().equals(localPropertyInfo.namespace))))
      {
        paramKvmSerializable.setProperty(i, read(paramXmlPullParser, paramKvmSerializable, i, null, null, localPropertyInfo));
        break;
        paramXmlPullParser.require(3, null, null);
        return;
      }
      j -= 1;
    }
  }
  
  protected Object readUnknown(XmlPullParser paramXmlPullParser, String paramString1, String paramString2)
    throws IOException, XmlPullParserException
  {
    String str2 = paramXmlPullParser.getName();
    String str3 = paramXmlPullParser.getNamespace();
    paramXmlPullParser.next();
    String str1;
    Object localObject;
    if (paramXmlPullParser.getEventType() == 4)
    {
      str1 = paramXmlPullParser.getText();
      localObject = new SoapPrimitive(paramString1, paramString2, str1);
      paramXmlPullParser.next();
    }
    for (;;)
    {
      if (paramXmlPullParser.getEventType() == 2)
      {
        if ((str1 != null) && (str1.trim().length() != 0))
        {
          throw new RuntimeException("Malformed input: Mixed content");
          if (paramXmlPullParser.getEventType() == 3)
          {
            localObject = new SoapObject(paramString1, paramString2);
            str1 = null;
          }
        }
        else
        {
          paramString2 = new SoapObject(paramString1, paramString2);
          for (;;)
          {
            paramString1 = paramString2;
            if (paramXmlPullParser.getEventType() == 3) {
              break;
            }
            paramString2.addProperty(paramXmlPullParser.getName(), read(paramXmlPullParser, paramString2, paramString2.getPropertyCount(), null, null, PropertyInfo.OBJECT_TYPE));
            paramXmlPullParser.nextTag();
          }
        }
      }
      else
      {
        paramString1 = (String)localObject;
        paramXmlPullParser.require(3, str3, str2);
        return paramString1;
      }
      str1 = null;
      localObject = null;
    }
  }
  
  protected void readVector(XmlPullParser paramXmlPullParser, Vector paramVector, PropertyInfo paramPropertyInfo)
    throws IOException, XmlPullParserException
  {
    int i = paramVector.size();
    String str3 = paramXmlPullParser.getAttributeValue(this.enc, "arrayType");
    int j;
    String str2;
    String str1;
    if (str3 != null)
    {
      i = str3.indexOf(':');
      j = str3.indexOf("[", i);
      str2 = str3.substring(i + 1, j);
      if (i == -1)
      {
        str1 = "";
        str1 = paramXmlPullParser.getNamespace(str1);
        i = getIndex(str3, j, -1);
        if (i == -1) {
          break label275;
        }
        paramVector.setSize(i);
        j = 0;
      }
    }
    for (;;)
    {
      if (paramPropertyInfo == null) {
        paramPropertyInfo = PropertyInfo.OBJECT_TYPE;
      }
      for (;;)
      {
        paramXmlPullParser.nextTag();
        int k = getIndex(paramXmlPullParser.getAttributeValue(this.enc, "offset"), 0, 0);
        for (;;)
        {
          if (paramXmlPullParser.getEventType() != 3)
          {
            int m = getIndex(paramXmlPullParser.getAttributeValue(this.enc, "position"), 0, k);
            k = i;
            if (j != 0)
            {
              k = i;
              if (m >= i)
              {
                k = m + 1;
                paramVector.setSize(k);
              }
            }
            paramVector.setElementAt(read(paramXmlPullParser, paramVector, m, str1, str2, paramPropertyInfo), m);
            m += 1;
            paramXmlPullParser.nextTag();
            i = k;
            k = m;
            continue;
            str1 = str3.substring(0, i);
            break;
          }
        }
        paramXmlPullParser.require(3, null, null);
        return;
      }
      label275:
      j = 1;
      continue;
      j = 1;
      str2 = null;
      str1 = null;
    }
  }
  
  public void writeBody(XmlSerializer paramXmlSerializer)
    throws IOException
  {
    this.multiRef = new Vector();
    this.multiRef.addElement(this.bodyOut);
    Object[] arrayOfObject = getInfo(null, this.bodyOut);
    if (this.dotNet)
    {
      str = "";
      paramXmlSerializer.startTag(str, (String)arrayOfObject[1]);
      if (this.dotNet) {
        paramXmlSerializer.attribute(null, "xmlns", (String)arrayOfObject[0]);
      }
      if (arrayOfObject[2] != null) {
        break label167;
      }
      str = "o0";
      label91:
      paramXmlSerializer.attribute(null, "id", str);
      paramXmlSerializer.attribute(this.enc, "root", "1");
      writeElement(paramXmlSerializer, this.bodyOut, null, arrayOfObject[3]);
      if (!this.dotNet) {
        break label177;
      }
    }
    label167:
    label177:
    for (String str = "";; str = (String)arrayOfObject[0])
    {
      paramXmlSerializer.endTag(str, (String)arrayOfObject[1]);
      return;
      str = (String)arrayOfObject[0];
      break;
      str = (String)arrayOfObject[2];
      break label91;
    }
  }
  
  public void writeObjectBody(XmlSerializer paramXmlSerializer, KvmSerializable paramKvmSerializable)
    throws IOException
  {
    PropertyInfo localPropertyInfo = new PropertyInfo();
    int j = paramKvmSerializable.getPropertyCount();
    int i = 0;
    while (i < j)
    {
      paramKvmSerializable.getPropertyInfo(i, this.properties, localPropertyInfo);
      if ((localPropertyInfo.flags & 0x1) == 0)
      {
        paramXmlSerializer.startTag(localPropertyInfo.namespace, localPropertyInfo.name);
        writeProperty(paramXmlSerializer, paramKvmSerializable.getProperty(i), localPropertyInfo);
        paramXmlSerializer.endTag(localPropertyInfo.namespace, localPropertyInfo.name);
      }
      i += 1;
    }
  }
  
  protected void writeProperty(XmlSerializer paramXmlSerializer, Object paramObject, PropertyInfo paramPropertyInfo)
    throws IOException
  {
    if (paramObject == null)
    {
      paramPropertyInfo = this.xsi;
      if (this.version >= 120) {}
      for (paramObject = "nil";; paramObject = "null")
      {
        paramXmlSerializer.attribute(paramPropertyInfo, (String)paramObject, "true");
        return;
      }
    }
    Object[] arrayOfObject = getInfo(null, paramObject);
    if ((paramPropertyInfo.multiRef) || (arrayOfObject[2] != null))
    {
      int j = this.multiRef.indexOf(paramObject);
      int i = j;
      if (j == -1)
      {
        i = this.multiRef.size();
        this.multiRef.addElement(paramObject);
      }
      if (arrayOfObject[2] == null) {}
      for (paramObject = "#o" + i;; paramObject = "#" + arrayOfObject[2])
      {
        paramXmlSerializer.attribute(null, "href", (String)paramObject);
        return;
      }
    }
    if ((!this.implicitTypes) || (paramObject.getClass() != paramPropertyInfo.type))
    {
      String str = paramXmlSerializer.getPrefix((String)arrayOfObject[0], true);
      paramXmlSerializer.attribute(this.xsi, "type", str + ":" + arrayOfObject[1]);
    }
    writeElement(paramXmlSerializer, paramObject, paramPropertyInfo, arrayOfObject[3]);
  }
  
  protected void writeVectorBody(XmlSerializer paramXmlSerializer, Vector paramVector, PropertyInfo paramPropertyInfo)
    throws IOException
  {
    PropertyInfo localPropertyInfo = paramPropertyInfo;
    if (paramPropertyInfo == null) {
      localPropertyInfo = PropertyInfo.OBJECT_TYPE;
    }
    int m = paramVector.size();
    paramPropertyInfo = getInfo(localPropertyInfo.type, null);
    paramXmlSerializer.attribute(this.enc, "arrayType", paramXmlSerializer.getPrefix((String)paramPropertyInfo[0], false) + ":" + paramPropertyInfo[1] + "[" + m + "]");
    int k = 0;
    int i = 0;
    if (k < m)
    {
      if (paramVector.elementAt(k) == null) {}
      int j;
      for (i = 1;; i = j)
      {
        k += 1;
        break;
        paramXmlSerializer.startTag(null, "item");
        j = i;
        if (i != 0)
        {
          paramXmlSerializer.attribute(this.enc, "position", "[" + k + "]");
          j = 0;
        }
        writeProperty(paramXmlSerializer, paramVector.elementAt(k), localPropertyInfo);
        paramXmlSerializer.endTag(null, "item");
      }
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\org\ksoap2\serialization\SoapSerializationEnvelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */