package br.com.fiap.beans;

import java.io.Serializable;

public class TurmaBean
  implements Serializable
{
  String Erro;
  String ano;
  String curso;
  String cursoID;
  String nomeTurma;
  String tipoTurma;
  
  public String getAno()
  {
    return this.ano;
  }
  
  public String getCurso()
  {
    return this.curso;
  }
  
  public String getCursoID()
  {
    return this.cursoID;
  }
  
  public String getErro()
  {
    return this.Erro;
  }
  
  public String getNomeTurma()
  {
    return this.nomeTurma;
  }
  
  public String getTipoTurma()
  {
    return this.tipoTurma;
  }
  
  public void setAno(String paramString)
  {
    this.ano = paramString;
  }
  
  public void setCurso(String paramString)
  {
    this.curso = paramString;
  }
  
  public void setCursoID(String paramString)
  {
    this.cursoID = paramString;
  }
  
  public void setErro(String paramString)
  {
    this.Erro = paramString;
  }
  
  public void setNomeTurma(String paramString)
  {
    this.nomeTurma = paramString;
  }
  
  public void setTipoTurma(String paramString)
  {
    this.tipoTurma = paramString;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\beans\TurmaBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */