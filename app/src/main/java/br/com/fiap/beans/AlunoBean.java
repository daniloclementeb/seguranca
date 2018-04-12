package br.com.fiap.beans;

import java.io.Serializable;
import java.util.List;

public class AlunoBean
  implements Serializable
{
  String chave;
  String email;
  String erro;
  String nome;
  String rm;
  String tipoAluno;
  List<TurmaBean> turma;
  
  public String getChave()
  {
    return this.chave;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getErro()
  {
    return this.erro;
  }
  
  public String getNome()
  {
    return this.nome;
  }
  
  public String getRm()
  {
    return this.rm;
  }
  
  public String getTipoAluno()
  {
    return this.tipoAluno;
  }
  
  public List<TurmaBean> getTurma()
  {
    return this.turma;
  }
  
  public void setChave(String paramString)
  {
    this.chave = paramString;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setErro(String paramString)
  {
    this.erro = paramString;
  }
  
  public void setNome(String paramString)
  {
    this.nome = paramString;
  }
  
  public void setRm(String paramString)
  {
    this.rm = paramString;
  }
  
  public void setTipoAluno(String paramString)
  {
    this.tipoAluno = paramString;
  }
  
  public void setTurma(List<TurmaBean> paramList)
  {
    this.turma = paramList;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\beans\AlunoBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */