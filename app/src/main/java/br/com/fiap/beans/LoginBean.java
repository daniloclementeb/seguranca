package br.com.fiap.beans;

public class LoginBean
{
  private String inRM;
  private String inVersao;
  private String stChave = "DD114BA7-FCB6-4638-88DB-F7E0FF57F894";
  private String stSenha;
  
  public String getInRM()
  {
    return this.inRM;
  }
  
  public String getInVersao()
  {
    return this.inVersao;
  }
  
  public String getStChave()
  {
    return this.stChave;
  }
  
  public String getStSenha()
  {
    return this.stSenha;
  }
  
  public void setInRM(String paramString)
  {
    this.inRM = paramString;
  }
  
  public void setInVersao(String paramString)
  {
    this.inVersao = paramString;
  }
  
  public void setStSenha(String paramString)
  {
    this.stSenha = paramString;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\beans\LoginBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */