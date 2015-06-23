package net.samongi.SamongiLib.Menu.Listener;

import net.samongi.SamongiLib.Menu.InventoryMenu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener
{
  @EventHandler
  public void onInventoryClick(InventoryClickEvent event)
  {
    Inventory inventory = event.getClickedInventory();
    if(inventory == null) return;
    if(!InventoryMenu.isMenu(inventory)) return;
    Player player = (Player)event.getWhoClicked();
    InventoryMenu menu = InventoryMenu.getMenu(player);
    menu.onInventoryClickEvent(event);
    event.setCancelled(true);
  }
  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event)
  {
    Inventory inventory = event.getInventory();
    if(inventory == null) return;
    if(!InventoryMenu.isMenu(inventory)) return;
    Player player = (Player)event.getPlayer();
    InventoryMenu.removeMenu(player);
    
  }
}
