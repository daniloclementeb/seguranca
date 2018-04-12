package br.com.fiap.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.fiap.beans.ApostilaBean;
import java.util.List;

public class ApostilasAdapter
  extends BaseAdapter
{
  private List<ApostilaBean> apostilas;
  private ViewHolder holder;
  private LayoutInflater mInflater;
  
  public ApostilasAdapter(Context paramContext, List<ApostilaBean> paramList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.apostilas = paramList;
  }
  
  public int getCount()
  {
    return this.apostilas.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.apostilas.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130903064, null);
      this.holder = new ViewHolder();
      this.holder.tvTitulo = ((TextView)paramView.findViewById(2131427412));
      this.holder.tvDataTamanho = ((TextView)paramView.findViewById(2131427413));
      this.holder.icone = ((ImageView)paramView.findViewById(2131427411));
      paramView.setTag(this.holder);
    }
    for (;;)
    {
      paramViewGroup = (ApostilaBean)this.apostilas.get(paramInt);
      this.holder.tvTitulo.setText(paramViewGroup.getTitulo());
      this.holder.tvDataTamanho.setText(paramViewGroup.getData() + " - " + paramViewGroup.getTamanho() + paramViewGroup.getMedida());
      if (!paramViewGroup.getExtencao().equalsIgnoreCase("jpg")) {
        break;
      }
      this.holder.icone.setImageResource(2130837685);
      return paramView;
      this.holder = ((ViewHolder)paramView.getTag());
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("doc"))
    {
      this.holder.icone.setImageResource(2130837668);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("ppt"))
    {
      this.holder.icone.setImageResource(2130837701);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("zip"))
    {
      this.holder.icone.setImageResource(2130837727);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("png"))
    {
      this.holder.icone.setImageResource(2130837698);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("xls"))
    {
      this.holder.icone.setImageResource(2130837726);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("pdf"))
    {
      this.holder.icone.setImageResource(2130837696);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("rar"))
    {
      this.holder.icone.setImageResource(2130837704);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("mp3"))
    {
      this.holder.icone.setImageResource(2130837693);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("avi"))
    {
      this.holder.icone.setImageResource(2130837617);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("csv"))
    {
      this.holder.icone.setImageResource(2130837667);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("txt"))
    {
      this.holder.icone.setImageResource(2130837716);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("pptx"))
    {
      this.holder.icone.setImageResource(2130837701);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("xlsx"))
    {
      this.holder.icone.setImageResource(2130837726);
      return paramView;
    }
    if (paramViewGroup.getExtencao().equalsIgnoreCase("docx"))
    {
      this.holder.icone.setImageResource(2130837668);
      return paramView;
    }
    this.holder.icone.setImageResource(2130837716);
    return paramView;
  }
  
  static class ViewHolder
  {
    private ImageView icone;
    private TextView tvDataTamanho;
    private TextView tvTitulo;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\ApostilasAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */