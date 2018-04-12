package br.com.fiap.beans;

import java.util.List;

public class TrabalhosPos
{
  List<TrabalhoPosBean> corrigidos;
  List<TrabalhoPosBean> entregues;
  int erro;
  String msgErro;
  List<TrabalhoPosBean> pendentes;
  
  public List<TrabalhoPosBean> getCorrigidos()
  {
    return this.corrigidos;
  }
  
  public List<TrabalhoPosBean> getEntregues()
  {
    return this.entregues;
  }
  
  public int getErro()
  {
    return this.erro;
  }
  
  public String getMsgErro()
  {
    return this.msgErro;
  }
  
  public List<TrabalhoPosBean> getPendentes()
  {
    return this.pendentes;
  }
  
  public void setCorrigidos(List<TrabalhoPosBean> paramList)
  {
    this.corrigidos = paramList;
  }
  
  public void setEntregues(List<TrabalhoPosBean> paramList)
  {
    this.entregues = paramList;
  }
  
  public void setErro(int paramInt)
  {
    this.erro = paramInt;
  }
  
  public void setMsgErro(String paramString)
  {
    this.msgErro = paramString;
  }
  
  public void setPendentes(List<TrabalhoPosBean> paramList)
  {
    this.pendentes = paramList;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\beans\TrabalhosPos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */