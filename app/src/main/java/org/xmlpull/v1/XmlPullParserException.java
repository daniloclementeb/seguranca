package org.xmlpull.v1;

import java.io.PrintStream;

public class XmlPullParserException
  extends Exception
{
  protected int column = -1;
  protected Throwable detail;
  protected int row = -1;
  
  public XmlPullParserException(String paramString)
  {
    super(paramString);
  }
  
  public XmlPullParserException(String paramString, XmlPullParser paramXmlPullParser, Throwable paramThrowable) {}
  
  public int getColumnNumber()
  {
    return this.column;
  }
  
  public Throwable getDetail()
  {
    return this.detail;
  }
  
  public int getLineNumber()
  {
    return this.row;
  }
  
  public void printStackTrace()
  {
    if (this.detail == null)
    {
      super.printStackTrace();
      return;
    }
    synchronized (System.err)
    {
      System.err.println(super.getMessage() + "; nested exception is:");
      this.detail.printStackTrace();
      return;
    }
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\org\xmlpull\v1\XmlPullParserException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */