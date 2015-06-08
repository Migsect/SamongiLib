package net.samongi.SamongiLib.Utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class StringUtil
{
	/**Replaces all "&X" formatting prefixes with the correct bukkit formatting
	 *    
	 * @param String -> String to be encoded.
	 * @return String -> Encoded String.
	 */
	static public String formatString(String string)
	{
		String newString = string;
		
		newString = newString.replace("&0", "" + ChatColor.BLACK);
		newString = newString.replace("&1", "" + ChatColor.DARK_BLUE);
		newString = newString.replace("&2", "" + ChatColor.DARK_GREEN);
		newString = newString.replace("&3", "" + ChatColor.DARK_AQUA);
		newString = newString.replace("&4", "" + ChatColor.DARK_RED);
		newString = newString.replace("&5", "" + ChatColor.DARK_PURPLE);
		newString = newString.replace("&6", "" + ChatColor.GOLD);
		newString = newString.replace("&7", "" + ChatColor.GRAY);
		newString = newString.replace("&8", "" + ChatColor.DARK_GRAY);
		newString = newString.replace("&9", "" + ChatColor.BLUE);
		newString = newString.replace("&a", "" + ChatColor.GREEN);
		newString = newString.replace("&b", "" + ChatColor.AQUA);
		newString = newString.replace("&c", "" + ChatColor.RED);
		newString = newString.replace("&d", "" + ChatColor.LIGHT_PURPLE);
		newString = newString.replace("&e", "" + ChatColor.YELLOW);
		newString = newString.replace("&f", "" + ChatColor.WHITE);
		
		newString = newString.replace("&k", "" + ChatColor.MAGIC);
		newString = newString.replace("&l", "" + ChatColor.BOLD);
		newString = newString.replace("&m", "" + ChatColor.STRIKETHROUGH);
		newString = newString.replace("&n", "" + ChatColor.UNDERLINE);
		newString = newString.replace("&o", "" + ChatColor.ITALIC);
		newString = newString.replace("&p ", "" + ChatColor.RESET);
		
		return newString;
	}
	
	/**Replaces all "&X" formatting prefixes with the correct bukkit formatting.
	 * This is done for each element in each rom
	 *    
	 * @param List of Strings -> Strings to be encoded.
	 * @return List of Strings -> Encoded Strings.
	 */
	static public List<String> formatString(List<String> strings)
	{
		List<String> new_strings = new ArrayList<String>();
		for(String s : strings)
		{
			new_strings.add(StringUtil.formatString(s));
		}
		return new_strings;
	}
	
	/**Returns the int value of the input numeral.
	 *    
	 * @param numeral The string in the format of a roman numeral
	 * @return The value of the numeral as an int.  Will return 0 if containing illegal digit.
	 */
	public static int numeralToInt(String numeral)
	{
		int sum = 0;
		// looping through the string.
		int max = 0;
		for(int c = numeral.length(); c >= 0; c--)
		{
			int cur_num = numeralVal(numeral.charAt(c));
			if(cur_num == 0) return 0; // If we find an illegal character.
			if(cur_num > max)
			{
				max = cur_num;
				sum += cur_num;
			}
			else if(cur_num < max)
			{
				sum -= cur_num;
			}
			else
			{
				sum += cur_num;
			}
		}
		return sum;
	}
	private static int numeralVal(char numeral)
	{
		switch(numeral)
		{
		  case 'i':
			case 'I':
				return 1;
			case 'v':
			case 'V':
				return 5;
			case 'x':
			case 'X':
				return 10;
			case 'l':
			case 'L':
				return 50;
			case 'c':
			case 'C':
				return 100;
			case 'd':
			case 'D':
				return 500;
			case 'm':
			case 'M':
				return 1000;
		}
		return 0;
	}
	
	/**Converts an int into a roman numeral.
	 * @param number Number to be converted into roman numerals
	 * @return String representation of the roman numeral.
	 */
	public static String intToNumeral(int number)
	{
		String numeral = "";
		while(number > 0)
		{
			if(number >= 1000)
			{
				number -= 1000;
				numeral = numeral + "M";
			}
			else if(number >= 900)
			{
				number -= 900;
				numeral = numeral + "CM";
			}
			else if(number >= 500)
			{
				number -= 500;
				numeral = numeral + "D";
			}
			else if(number >= 400)
			{
				number -= 400;
				numeral = numeral + "DC";
			}
			else if(number >= 100)
			{
				number -= 100;
				numeral = numeral + "C";
			}
			else if(number >= 90)
			{
				number -= 90;
				numeral = numeral + "XC";
			}
			else if(number >= 50)
			{
				number -= 50;
				numeral = numeral + "L";
			}
			else if(number >= 40)
			{
				number -= 40;
				numeral = numeral + "XL";
			}
			else if(number >= 10)
			{
				number -= 10;
				numeral = numeral + "X";
			}
			else if(number >= 9)
			{
				number -= 9;
				numeral = numeral + "IX";
			}
			else if(number >= 5)
			{
				number -= 5;
				numeral = numeral + "V";
			}
			else if(number >= 4)
			{
				number -= 4;
				numeral = numeral + "IV";
			}
			else if(number >= 1)
			{
				number -= 1;
				numeral = numeral + "I";
			}
			if(number < 0) return "ERROR";
		}
		return numeral;
	}
	
	/**creates a string of spaces.
	 *  
	 * @param n number of spaces
	 * @return A string of spaces
	 */
	public static String spaces(int n)
	{
		String str = "";
		for(int i = 0; i < n; i++)
		{
			str += " ";
		}
		return str;
	}
	
	/**Will upcase the first letter of each inner string as determined by the delimiter.
	 * 
	 * @param string The string to capitalize
	 * @param delimiter The delimtier between strings.
	 * @return A capitalized string.
	 */
	public static String capitalize(String string, String delimiter)
	{
		String[] broken_up = string.toLowerCase().split(delimiter);
		String return_string = "";
		for(int i = 0; i < broken_up.length; i++)
		{
			broken_up[i] = broken_up[i].substring(0,1).toUpperCase() + broken_up[i].substring(1);
			return_string = return_string + broken_up[i];
			if(i != broken_up.length-1) return_string = return_string + " ";
		}
		return return_string;
	}
	/**Extracts any numbers from the string using a " " delimiter
	 * 
	 * @param str The string
	 * @return A list of all double found.
	 */
	public static List<Double> extractNumbers(String str){return extractNumbers(str, " ");}
	
	/**Extaracts any numbers from the string using a defined delimiter.
	 * 
	 * @param str The string
	 * @param delim The delimiter
	 * @return The list of all doubles found.
	 */
	public static List<Double> extractNumbers(String str, String delim)
	{
		List<Double> ret_doub = new ArrayList<Double>();
		for(String s : str.split(delim))
		{
			Double doub = null;
			try
			{
				doub = Double.parseDouble(s);
			}
			catch (NumberFormatException e) {}
			if(doub != null) ret_doub.add(doub); 
		}
		return ret_doub;
	}
}
