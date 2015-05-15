package net.samongi.SamongiLib.CommandHandling;


public enum ArgumentType
{
	STRING,  // Can be anything
	NUMBER, // Int
	BOOLEAN; // True or False
	
	/**Returns the argument type as defined by the input string.
	 * 
	 * @param string
	 * @return STRING, NUMBER, or BOOLEAN argument types depending.
	 */
	static public ArgumentType getArgumentType(String string)
	{
		try
    {
	    java.lang.Double.parseDouble(string);
	    return ArgumentType.NUMBER;
    }catch (NumberFormatException e){} // Do Nothing
		if(string.toLowerCase().equals("true")) return ArgumentType.BOOLEAN;
		if(string.toLowerCase().equals("false")) return ArgumentType.BOOLEAN;
		return ArgumentType.STRING;
	}
}

