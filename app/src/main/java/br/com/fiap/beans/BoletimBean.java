package br.com.fiap.beans;

import java.util.List;

public class BoletimBean
{
  String exibir2sem;
  String mensagem;
  String nomecol;
  List<NotaBean> notas;
  
  public String getExibir2sem()
  {
    return this.exibir2sem;
  }
  
  public String getMensagem()
  {
    return this.mensagem;
  }
  
  public String getNomecol()
  {
    return this.nomecol;
  }
  
  public List<NotaBean> getNotas()
  {
    return this.notas;
  }
  
  public void setExibir2sem(String paramString)
  {
    this.exibir2sem = paramString;
  }
  
  public void setMensagem(String paramString)
  {
    this.mensagem = paramString;
  }
  
  public void setNomecol(String paramString)
  {
    this.nomecol = paramString;
  }
  
  public void setNotas(List<NotaBean> paramList)
  {
    this.notas = paramList;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\beans\BoletimBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */