package net.samongi.SamongiLib.Text;

import net.samongi.SamongiLib.SamongiLib;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class TextUtils {

    /**
     * Wraps the text based on words and characters.
     *
     * @param text The text to wrap.
     * @param characters The limit at which wrapping should occur.
     * @return A list of the wrapped text (if it needs to be wrapped)
     */
    public static List<String> wrapText(String text, int characters, int wrappedTab)
    {
        List<String> wrappedText = new ArrayList<>();
        if(text.length() < characters) {
            wrappedText.add(text);
            return wrappedText;
        }

        // We first need to find a good place to split it
        int splitPoint = characters - 1;
        char queryCharacter = text.charAt(splitPoint);
        for(; queryCharacter != ' ' && splitPoint >= 0; splitPoint--) {
            queryCharacter = text.charAt(splitPoint);
        }

        // If we didn't find a blank spot to wrap, then we need to wrap wherever the heck we want to.
        if(queryCharacter != ' ') {
            wrappedText.add(text.substring(0, characters + 1));
            String remainder = text.substring(characters + 1);
            List<String> wrappedSubStrings = wrapText(remainder, characters, wrappedTab);
            wrappedText.add(wrappedSubStrings.get(0));
            if(wrappedSubStrings.size() > 1) {
                wrappedText.addAll(wrappedSubStrings.subList(1, wrappedSubStrings.size() - 1));
            }
            return wrappedText;
        }

        String headText = text.substring(0, splitPoint + 1);
        wrappedText.add(headText);
        String tailText = new String(new char[wrappedTab]).replace('\0', ' ') + text.substring(splitPoint+ 1);

        // We need to bring forward the chat color for the tail text in the case it was relying
        // on the headText;
        String formatting = ChatColor.getLastColors(headText);
        if(formatting != null)
        {
            tailText = formatting + tailText;
        }

        List<String> wrappedSubStrings = wrapText(tailText, characters, wrappedTab);
        wrappedText.add(wrappedSubStrings.get(0));
        if(wrappedSubStrings.size() > 1) {
            wrappedText.addAll(wrappedSubStrings.subList(1, wrappedSubStrings.size() - 1));
        }
        return wrappedText;
    }
}
