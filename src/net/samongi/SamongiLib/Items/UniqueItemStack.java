package net.samongi.SamongiLib.Items;

import java.util.Random;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

public class UniqueItemStack
{
  private final ItemStack item;
  private final UUID uuid;
  
  public UniqueItemStack(ItemStack item)
  {
    this.item = item;
    Random rand = new Random();
    this.uuid = new UUID(rand.nextLong(), rand.nextLong());
  }
  
  public ItemStack getItem(){return this.item;}
  
  public UUID getUUID(){return this.uuid;}
  
  public Object clone(){return new UniqueItemStack(this.item);}
  
  public String toString(){return this.item.toString();}
  
  public boolean equals(Object other)
  {
    if(!(other instanceof UniqueItemStack)) return false;
    return ((UniqueItemStack)other).getUUID().equals(this.getUUID());
  }
  
  public int hashCode(){return uuid.hashCode();}
}
