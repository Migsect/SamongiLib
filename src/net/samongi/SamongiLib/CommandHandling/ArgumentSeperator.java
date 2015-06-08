package net.samongi.SamongiLib.CommandHandling;

import java.util.HashMap;

import net.samongi.SamongiLib.SamongiLib;

/**With the input of a commandpath, this seperates the command path from the arguments.
 * 
 * @author Migsect
 *
 */
public class ArgumentSeperator
{
	StringBranch root = new StringBranch("");
	
	public String[] splitCommand(String command)
	{
	  SamongiLib.debugLog("  ArgumentSeperator: On splitting, command is '" + command + "'");
	  // We split the command based on it's spaces.  This is the entire command.
		String[] split_command = command.split(" ");
		String cmd = ""; // The command (complex-> header+subcommands
		String args = ""; // The arguments to the command
		StringBranch current_branch = this.root;
		boolean arg_time = false;
		// This is basically determining when to stop and grab commands.
		for(String s : split_command)
		{
			// If we got a branch, that means its a command.
			if(current_branch.hasBranch(s) && arg_time == false)
			{
			  SamongiLib.debugLog("  ArgumentSeperator: On splitting, found command branch fro '" + s + "'");
				cmd = cmd + " " + s;
				SamongiLib.debugLog("  ArgumentSeperator: On splitting, command is '" + cmd + "'");
        current_branch = current_branch.getBranch(s);
        continue;
			}
			// If the arg_time flag is false, set it to true and the rest will be appending to the args.
			else if(arg_time == false)
			{
			  SamongiLib.debugLog("  ArgumentSeperator: On splitting, did not find branch for '" + s + "'");
				arg_time = true;
			}
			// Start appending to args.
			args = args + " " + s;
			SamongiLib.debugLog("  ArgumentSeperator: On splitting, args is '" + args + "'");
		}
		// making the return string.
		String[] return_array = {cmd.trim(), args.trim()};
		SamongiLib.debugLog("  ArgumentSepeator: Finished Split: [ " + return_array[0] +" , "+ return_array[1] + " ]");
		
		return return_array;
		
	}
	
	/**Registers the commandpath with the argument seperator.
	 * 
	 * @param command_path The command path to be registered
	 */
	public void registerCommandPath(String command_path)
	{
	  SamongiLib.debugLog("  ArgumentSeperator: On register command path  is '" + command_path + "'");
		// Splits the command into each element.
		String[] split_command_path = command_path.split(" ");
		// Sets the current branch to the super branch.
		StringBranch current_branch = this.root;
		// Starts add each element of the array as a new branch to each other.
		//   With multiple registerations this will create a tree.
		for(String s : split_command_path)
		{
		  SamongiLib.debugLog("  ArgumentSeperator: Path loop, branch is: '" + s + "' from '" + command_path + "'");
		  // Making or Getting the next branch below.
		  if(!current_branch.hasBranch(s)) 
		  {
		    SamongiLib.debugLog("    Creating a new branch for '" + s + "'");
		    current_branch = new StringBranch(s, current_branch);
		  }
		  else 
		  {
		    SamongiLib.debugLog("    Getting a the branch for '" + s + "'");
		    current_branch = current_branch.getBranch(s);
		  }
		  
		}
	}
	
	private class StringBranch
	{
		String name;
		StringBranch parent;
		HashMap<String, StringBranch> branches = new HashMap<>();
		
		public StringBranch(String name)
		{
			this.name = name;
			this.parent = null;
		}
		public StringBranch(String name, StringBranch parent)
		{
			this.name = name;
			this.parent = parent;
			parent.branches.put(getName(), this);
		}
		
		/**Checks to see if the branch has a parent branch.
		 * 
		 * @return HasParent : if the branch does have a parent branch this will return true
		 */
		@SuppressWarnings("unused")
    public boolean hasParent(){return parent != null;}
		
		/**Returns the branch's parent branch.
		 * 
		 * @return Branch's parent
		 */
		@SuppressWarnings("unused")
    public StringBranch getParent(){return parent;}
		
		/**Returns the name of the Branch
		 * 
		 * @return the name of the branch
		 */
    public String getName(){return name;}
		
		/**Returns true if the branch contains the branch going by the included name
		 * 
		 * @param branch_name
		 * @return True if contains branch, false if it does not.
		 */
		public boolean hasBranch(String branch_name){return branches.containsKey(branch_name);}
		
		/**Returns the branch specified
		 * 
		 * @param branch_name The branch to get.
		 * @return The branch specified if it exists, otherwise it returns null.
		 */
		public StringBranch getBranch(String branch_name){return branches.get(branch_name);}
	}
}
