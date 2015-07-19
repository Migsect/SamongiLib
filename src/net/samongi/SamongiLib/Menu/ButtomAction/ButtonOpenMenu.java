package net.samongi.SamongiLib.Menu.ButtomAction;

import org.bukkit.inventory.ItemStack;

import net.samongi.SamongiLib.Menu.InventoryMenu;

/**This represents an Inventory Menu button that, when pressed, will
 * open a menu for the clicker.
 */
public class ButtonOpenMenu implements ButtonAction
{
  private final ItemStack display;
  private final InventoryMenu menu;
  public ButtonOpenMenu(InventoryMenu menu, ItemStack display)
  {
    this.display = display.clone();
    this.menu = menu;
  }
  
  public void register(InventoryMenu menu, int slot)
  {
    menu.setItem(slot, display);
    menu.addClickAction(slot, this);
  }
  @Override
  public void onButtonPress()
  {
    menu.openMenu();
  }
  
}
