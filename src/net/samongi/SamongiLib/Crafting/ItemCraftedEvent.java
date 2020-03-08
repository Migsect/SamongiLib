package net.samongi.SamongiLib.Crafting;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.CraftItemEvent;

public class ItemCraftedEvent extends Event {

    // ---------------------------------------------------------------------------------------------------------------//
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @Override public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
    // ---------------------------------------------------------------------------------------------------------------//
    private final CraftItemEvent m_baseEvent;
    private final int m_amountCrafted;

    // ---------------------------------------------------------------------------------------------------------------//
    public ItemCraftedEvent(CraftItemEvent baseEvent, int amountCrafted) {
        m_baseEvent = baseEvent;
        m_amountCrafted = amountCrafted;
    }

    // ---------------------------------------------------------------------------------------------------------------//
    public int getAmountCrafted() {
        return m_amountCrafted;
    }

    public CraftItemEvent getBaseEvent() {
        return m_baseEvent;
    }

    // ---------------------------------------------------------------------------------------------------------------//
}
