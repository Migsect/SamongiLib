package net.samongi.SamongiLib.CommandHandling;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

public enum SenderType
{
	PLAYER, 
	CONSOLE, 
	BLOCK, 
	REMOTE;
	
	/**Returns a SenderType enumeration to ease in comparison.
	 * 
	 * @param sender The sender to check.
	 * @return The sendertype of the command.
	 */
	public static SenderType getSenderType(CommandSender sender)
	{
		if(sender instanceof Player) return SenderType.PLAYER;
		if(sender instanceof BlockCommandSender) return SenderType.BLOCK;
		if(sender instanceof ConsoleCommandSender) return SenderType.CONSOLE;
		if(sender instanceof RemoteConsoleCommandSender) return SenderType.REMOTE;
		return null;
	}
	
	/**Casts the sender type to be a player.
	 * 
	 * @param sender The sender to be converted
	 * @return The CommandSender casted to Player
	 */
	public static Player convertSenderToPlayer(CommandSender sender){return (Player) sender;}
	/**
	 * 
	 * @param sender The sender to be converted
	 * @return The CommandSender casted to BlockCommandSender
	 */
	public static BlockCommandSender convertSenderToBlock(CommandSender sender){return (BlockCommandSender) sender;}
	/**
	 * 
	 * @param sender The sender to be converted
	 * @return The CommandSender casted to ConsoleCommandSender
	 */
	public static ConsoleCommandSender convertSenderToConsole(CommandSender sender){return (ConsoleCommandSender) sender;}
	/**
	 * 
	 * @param sender The sender to be converted
	 * @return The CommandSender casted to RemoteConsoleCommandSender
	 */
	public static RemoteConsoleCommandSender convertSenderToRemote(CommandSender sender){return (RemoteConsoleCommandSender) sender;}
}
