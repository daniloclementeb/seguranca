package br.com.fiap.layouts;

public class ItemListView
{
  private int iconeRid;
  private String texto;
  
  public ItemListView()
  {
    this("", -1);
  }
  
  public ItemListView(String paramString, int paramInt)
  {
    this.texto = paramString;
    this.iconeRid = paramInt;
  }
  
  public int getIconeRid()
  {
    return this.iconeRid;
  }
  
  public String getTexto()
  {
    return this.texto;
  }
  
  public void setIconeRid(int paramInt)
  {
    this.iconeRid = paramInt;
  }
  
  public void setTexto(String paramString)
  {
    this.texto = paramString;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\ItemListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */