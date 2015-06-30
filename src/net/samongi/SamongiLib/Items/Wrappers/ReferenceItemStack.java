package net.samongi.SamongiLib.Items.Wrappers;

import org.bukkit.inventory.ItemStack;

public class ReferenceItemStack
{
  private final ItemStack item;
  
  public ReferenceItemStack(ItemStack item)
  {
    this.item = item;
  }
  
  public ItemStack getItem(){return this.item;}
  
  public Object clone(){return new ReferenceItemStack(this.item);}
  
  public String toString(){return this.item.toString();}
  
  public boolean equals(Object other)
  {
    if(!(other instanceof UniqueItemStack)) return false;
    return ((UniqueItemStack)other).getItem() == this.getItem();
  }
  
  public int hashCode(){return System.identityHashCode(this.item);}
  
}
