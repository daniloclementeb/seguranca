package br.com.fiap.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MenuAdapter
  extends BaseAdapter
{
  Context cont;
  private List<ItemListView> itens;
  private LayoutInflater mInflater;
  
  public MenuAdapter(Context paramContext, List<ItemListView> paramList)
  {
    this.itens = paramList;
    this.cont = paramContext;
    this.mInflater = LayoutInflater.from(paramContext);
  }
  
  public int getCount()
  {
    return this.itens.size();
  }
  
  public ItemListView getItem(int paramInt)
  {
    return (ItemListView)this.itens.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130903089, null);
      paramViewGroup = new ItemSuporte(null);
      paramViewGroup.txtTitle = ((TextView)paramView.findViewById(2131427466));
      paramViewGroup.imgIcon = ((ImageView)paramView.findViewById(2131427465));
      paramView.setTag(paramViewGroup);
    }
    for (;;)
    {
      ItemListView localItemListView = (ItemListView)this.itens.get(paramInt);
      paramViewGroup.txtTitle.setText(localItemListView.getTexto());
      paramViewGroup.imgIcon.setImageResource(localItemListView.getIconeRid());
      return paramView;
      paramViewGroup = (ItemSuporte)paramView.getTag();
    }
  }
  
  private class ItemSuporte
  {
    ImageView imgIcon;
    TextView txtTitle;
    
    private ItemSuporte() {}
  }
}


/* Location:              C:\Users\HP\Desktop\Nova pasta\dex2jar-2.0\fiap-dex2jar.jar!\br\com\fiap\layouts\MenuAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */