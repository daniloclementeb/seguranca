package br.com.fiap.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.fiap.beans.ListasBean;
import java.util.List;

public class ListasAdapter
  extends BaseAdapter
{
  private ViewHolder holder;
  private List<ListasBean> lista;
  private LayoutInflater mInflater;
  
  public ListasAdapter(Context paramContext, List<ListasBean> paramList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.lista = paramList;
  }
  
  public int getCount()
  {
    return this.lista.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.lista.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130903087, null);
      this.holder = new ViewHolder();
      this.holder.linha1 = ((TextView)paramView.findViewById(2131755008));
      this.holder.linha2 = ((TextView)paramView.findViewById(2131755009));
      paramView.setTag(this.holder);
    }
    for (;;)
    {
      paramViewGroup = (ListasBean)this.lista.get(paramInt);
      this.holder.linha1.setText(paramViewGroup.getLinha1());
      this.holder.linha2.setText(paramViewGroup.getLinha2());
      return paramView;
      this.holder = ((ViewHolder)paramView.getTag());
    }
  }
  
  static class ViewHolder
  {
    private TextView linha1;
    private TextView linha2;
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\ListasAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */