package net.samongi.SamongiLib.CommandHandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.samongi.SamongiLib.SamongiLib;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler implements CommandExecutor
{

	JavaPlugin plugin;
	
	Map<String, BaseCommand> commands = new HashMap<>();
	Set<String> registered_heads = new HashSet<>();
	ArgumentSeperator seperator = new ArgumentSeperator();
	
	public CommandHandler(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}

	@Override
  public boolean onCommand(CommandSender sender, Command cmd, String label,
      String[] args)
  {
	  BaseCommand command = this.getCommand(cmd, args);
	  String[] true_args = this.getTrueArguments(cmd, args);
	  boolean errored = checkForError(command, sender, true_args);
	  if(errored) command.run(sender, true_args);
	  return true;
  }
	
	/**Gets all the commands registered.
	 * 
	 * @return All the commands.
	 */
	public List<BaseCommand> getCommands()
	{
	  List<BaseCommand> commands = new ArrayList<>();
	  commands.addAll(this.commands.values());
	  return commands;
	}
	
	/**Registers the command with the handler
	 * 
	 * @param command The BaseCommand extended command
	 */
	public void registerCommand(BaseCommand command)
	{
		// The command path will be segmented by a space.
		String head = command.getCommandPath().split(" ")[0];
		if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: On register command head is '" + head + "'");
		
		// registering the command context if it hasn't already.
		if(!registered_heads.contains(head))
		{
			plugin.getCommand(head).setExecutor(this);
			registered_heads.add(head);
		}
		// Adding the command to the map
		commands.put(command.getCommandPath(), command);
		// register the command's path with the seperator:
		seperator.registerCommandPath(command.getCommandPath());
	}
	
	/**Gets the base-command based off the passed in command and raw arguments
	 * 
	 * @param cmd The command
	 * @param args Raw arguments
	 * @return The BaseCommand found if any.
	 */
	private BaseCommand getCommand(Command cmd, String[] args)
	{
	  if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: On getting command: cmd='" + cmd.getLabel() + "'");
		String header = cmd.getLabel();
		String command = header;
		// This appends all the arguments to the command.
		for(String s : args)
		{
			command = command + " " + s;
		}
		String[] split_command = seperator.splitCommand(command);
		if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: On getting command: split[0]='" + split_command[0] + "', split[1]='" + split_command[1] + "'");
		return commands.get(split_command[0]);
	}
	/**Returns the true arguments of the command, excluding the subcommands form the args list
	 * 
	 * @param cmd The command
	 * @param args The arguments
	 * @return The true args
	 */
	private String[] getTrueArguments(Command cmd, String[] args)
	{
		String header = cmd.getLabel();
		String command = header;
		// This appends all the arguments to the command.
		for(String s : args)
		{
			command = command + " " + s;
		}
		String[] split_command = seperator.splitCommand(command);
		if(split_command[1].length() == 0) return new String[0]; // We're returning an empty array if we didn't get much of any arguments
		return split_command[1].split(" "); 
	}
	
	/**Checks for any errors with the sent command.  Returns false if any area fails.
	 * 
	 * @param cmd The command to look at.
	 * @param sender The command sender (could be player, console, command-block, etc)
	 * @param args The list of true-argments (stripped of sub-commands)
	 * @return false if anything fails.
	 */
	private boolean checkForError(BaseCommand cmd, CommandSender sender, String[] args)
	{
		boolean status = true;
		// generating the argument type list.
		ArgumentType[] arguments = new ArgumentType[args.length];
		for(int i = 0; i < args.length; i++)
		{
			arguments[i] = ArgumentType.getArgumentType(args[i]);
		}
		// Converting arguments to an ArgumentTypeArray
		if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: Checking for Errors on command '" + cmd.getCommandPath() + "'");
		if(SamongiLib.debugger)
		{
		  String arg_string = "";
		  for(ArgumentType t : arguments) arg_string += " " + t.toString();
		  arg_string.trim();
		  SamongiLib.logger.info("  CommandHandler: Checking for Argument Errors: '" + arg_string + "'");
		}
		if(SamongiLib.debugger) 
		if(!cmd.isCorrectArguments(arguments))
		{
			cmd.handleError(sender, ErrorType.WrongArguments);
			status = false;
			if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: Checking for Argument Errors - failed");
		}
		if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: Checking for Sender Errors: '" + sender.getName() + "'");
		if(!cmd.isAllowedSender(sender))
		{
			cmd.handleError(sender, ErrorType.WrongSender);
			status = false;
			if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: Checking for Sender Errors - failed");
		}
		if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: Checking for Permission Errors: '" + cmd.getPermission() + "'");
		if(!cmd.hasPermission(sender))
		{
			cmd.handleError(sender, ErrorType.NoPermission);
			status = false;
			if(SamongiLib.debugger) SamongiLib.logger.info("  CommandHandler: Checking for Permission Errors - failed");
		}
		return status;
	}

}
