package net.samongi.SamongiLib.Configuration;

import net.samongi.SamongiLib.Logger.BetterLogger;

import javax.annotation.Nonnull;
import java.io.File;

public interface ConfigurationParsing {

    /**
     * parses the provided config file. Provides the file for logging purposes.
     *
     * @param file The file the config is retrieved from.
     * @param config The config resulting from the file.
     * @return False if the parsing failed.
     */
    public boolean parseConfigFile(@Nonnull File file, @Nonnull ConfigFile config);

    // ---------------------------------------------------------------------------------------------------------------//
    default public boolean parseFile(@Nonnull File file)
    {
        ConfigFile config = new ConfigFile(file);
        return parseConfigFile(file, config);
    }

    // ---------------------------------------------------------------------------------------------------------------//
    default public boolean parseDirectory(@Nonnull File directory)
    {
        if(!directory.isDirectory())
        {
            return false;
        }

        boolean noIssues = true;
        File[] files = directory.listFiles();
        for (File file : files) {
            if(!parseFile(file))
            {
                noIssues = false;
            }
        }
        return noIssues;
    }

    // ---------------------------------------------------------------------------------------------------------------//
    default public boolean parse(@Nonnull File file)
    {
        if(!file.exists())
        {
            return false;
        }

        if(file.isDirectory())
        {
            return parseDirectory(file);
        }
        if(file.isFile())
        {
            return parseFile(file);
        }

        return false;
    }

    // ---------------------------------------------------------------------------------------------------------------//
}
