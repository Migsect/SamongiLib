package net.samongi.SamongiLib.CommandHandling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.samongi.SamongiLib.SamongiLib;
import net.samongi.SamongiLib.Utilities.ArrayUtilities;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand
{
	protected String command_path;
	protected List<String> usage = new ArrayList<>();
	protected String permission = "";
	protected List<SenderType> allowed_senders = new ArrayList<>();
	protected List<ArgumentType[]> allowed_arguments = new ArrayList<>();
	
	public BaseCommand(String command_path)
	{
		this.command_path = command_path;
	}
	
	/**Executes the command.
	 * 
	 * @param sender The one who sent the command
	 * @param args An array of relevant arguments stripped of prior command path arguments
	 * @return True if the command suceeded. If returned false, a ErrorType.Fail will be passed back through the class.
	 */
	abstract public boolean run(CommandSender sender, String[] args);
	/**Handles the error caused, generally sends a message back to the sender.  This is meant to be handled on the client's end.
	 * 
	 * @param sender The sender of the command that caused an error.
	 * @param type The type of error that was caused.
	 */
	public void handleError(CommandSender sender, ErrorType type)
	{
		if(type.equals(ErrorType.WrongSender))
		{
			sender.sendMessage(ChatColor.RED + "There was an error in your command: " + ChatColor.YELLOW + "You cannot use this command as your SenderType.");
		}
		if(type.equals(ErrorType.NoPermission))
		{
			sender.sendMessage(ChatColor.RED + "There was an error in your command: " + ChatColor.YELLOW + "You do not have permission to use this command.");
		}
		if(type.equals(ErrorType.WrongArguments))
		{
			sender.sendMessage(ChatColor.RED + "There was an error in your command: " + ChatColor.YELLOW + "You entered the wrong number or type of arguments.");
		}
		if(type.equals(ErrorType.Failure))
		{
			sender.sendMessage(ChatColor.RED + "There was an error in your command: " + ChatColor.YELLOW + "/" + command_path);
		}
	}
	
	/**Sets a series of messages that will be sent to a player when they incorrectly use the command.
	 * 
	 * @param usage A list of strings to be sent to the player.
	 */
	final protected void setUsage(List<String> usage){this.usage = usage;}
  /**Gets a list of Strings that explains the usage of the command
   * 
   * @return A list of strings that detail the command's usage.
   */
  final public List<String> getUsage(){return this.usage;}
	
	/**The permission required by a player to use this command.
	 * 
	 * @param permission The permission node.
	 */
	final protected void setPermission(String permission){this.permission = permission;}
	/**The permission required by a player to use this command.
	 * 
	 * @return The permission a player requires for this command.
	 */
	final public String getPermission(){return this.permission;}
	/**Checks if the commandsender is a player.  If they are, it then checks to see if they have permission.
	 * 
	 * @param sender The sender of the command.
	 * @return boolean if they do not have permission.
	 */
	final public boolean hasPermission(CommandSender sender)
	{
		if(!(sender instanceof Player)) return true;
		Player player = (Player) sender;
		if(this.permission.length() == 0) return true;
		return player.hasPermission(this.getPermission());
	}
	
	/**The allowed senderTypes of this command.
	 * 
	 * @param allowed_senders A list of all allowed sender types.
	 */
	final protected void setAllowedSenders(List<SenderType> allowed_senders){this.allowed_senders = allowed_senders;}
	
	/**Checks to see if this command allows that type of sender.
	 * 
	 * @param sender The sender to be checked
	 * @return true if the Sender is can execute this command.
	 */
	final protected boolean isAllowedSender(CommandSender sender)
	{
		return allowed_senders.contains(SenderType.getSenderType(sender));
	}
	
	/**The series of argument types that this command takes.
	 * 
	 * @param allowed_argumemnts the argument types.
	 */
	final protected void setAllowedArguments(List<ArgumentType[]> allowed_argumemnts){this.allowed_arguments = allowed_argumemnts;}
	
	/**Checks the see if the list of arguments is allowed.  Only minimum amounts of arguments are checked.
	 *   If a command has more arguments then required, it is assumed they are ignored.
	 * 
	 * @param arguments The list of argument types.
	 * @return True if it is allowed
	 */
	final protected boolean isCorrectArguments(ArgumentType[] arguments)
	{
	  // This is what we want to return.
		boolean found_match = false;
		for(ArgumentType[] args : allowed_arguments)
		{
		  if(SamongiLib.debugger) SamongiLib.logger.info("    BaseCommand: Comparing '" + ArrayUtilities.arrayToString(args) + "' and '" + ArrayUtilities.arrayToString(arguments) + "'");
		  // if(Arrays.equals(args, arguments)) return true;
		  if(Arrays.equals(args, arguments)) return true;
		  if(arguments.length == 0) continue;
		  if(arguments.length < args.length) continue; // we are looping through args.length.  Already nullifies case.
		  // Looping through the argument we got and comparing it to the arguments.
		  for(int i = 0 ; i < args.length ; i++)
		  {
		    if(SamongiLib.debugger) SamongiLib.logger.info("    BaseCommand: isCorrectArguments comparing: '" + args[i].toString() + "' and '" + arguments[i].toString() + "'");
		    if(args[i] != arguments[i] && args[i] != ArgumentType.STRING)
		    {
		      found_match = false;
		      if(SamongiLib.debugger) SamongiLib.logger.info("    BaseCommand: No match! Breaking from check...");
		      break;
		    }
		    found_match = true;
		    if(SamongiLib.debugger) SamongiLib.logger.info("    BaseCommand: Match! Going on to check next element.");
		  }
		  // if there is a match, we escape.
		  if(found_match) return true;
		}
		return found_match;
	}
	
	/**Returns the command path
	 * 
	 * @return the command path.  Command paths are delimited by '.'
	 */
	final public String getCommandPath(){return command_path;}

}
