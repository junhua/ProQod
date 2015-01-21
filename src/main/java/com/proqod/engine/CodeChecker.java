package com.proqod.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.DefaultLogger;
import com.puppycrawl.tools.checkstyle.Main;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.XMLLogger;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import com.puppycrawl.tools.checkstyle.api.Utils;
import com.sun.tools.internal.ws.wsdl.parser.W3CAddressingExtensionHandler;

public class CodeChecker {
    public String output = "NON E";
    
    /** the options to the command line */
    private static final Options OPTS = new Options();
    static {
        OPTS.addOption("c", true, "The check configuration file to use.");
        OPTS.addOption("o", true, "Sets the output file. Defaults to stdout");
        OPTS.addOption("p", true, "Loads the properties file");
        OPTS.addOption(
            "f",
            true,
            "Sets the output format. (plain|xml). Defaults to plain");
        OPTS.addOption("v", false, "Print product version and exit");
    }

    public CodeChecker () {
    }

    public CodeChecker (String code) {
        this.output = PerformChecking(code);
    }
    
    public String PerformChecking(String code){
    	
//    	
////    	// Checkstyle Integration
////    	
////    	System.out.println("[DEBUG] arguments: ");
//    	String [] aArgs = new String[3];
//    	aArgs[0] = "-c";
//    	aArgs[1] = "/sun_checks.xml";
//    	aArgs[2] = "/Users/sunardi/Desktop/HW5_BonusQ.java";
////    	
//    	// parse the parameters
//        final CommandLineParser clp = new PosixParser();
//        CommandLine line = null;
//        try {
//            line = clp.parse(OPTS, aArgs);
//        }
//        catch (final ParseException e) {
//            e.printStackTrace();
//            usage();
//        }
//        assert line != null;
//
//       
    	String tempString=null;
        BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("/sun_checks.xml"));
 
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				tempString = tempString + sCurrentLine;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
//        
        // setup the properties
//        final Properties props =
//            line.hasOption("p")
//                ? loadProperties(new File(line.getOptionValue("p")))
//                : System.getProperties();
        
                

//        final String errorResult = loadConfig(line, props);
            
                
//        final Configuration config = loadConfig(line, props);
        
//
//        // setup the output stream
//        OutputStream out = null;
//        boolean closeOut = false;
//        if (line.hasOption("o")) {
//            final String fname = line.getOptionValue("o");
//            try {
//                out = new FileOutputStream(fname);
//                closeOut = true;
//            }
//            catch (final FileNotFoundException e) {
//                System.out.println("Could not find file: '" + fname + "'");
//                System.exit(1);
//            }
//        }
//        else {
//            out = System.out;
//            closeOut = false;
//        }
//        
//        final AuditListener listener = createListener(line, out, closeOut);
//        final List<File> files = getFilesToProcess_String_To_File(code);
//        
//    	
    	

    	return "[debug1] " + code + " " + tempString + " "+  "!";
    }

    public String getResult() {
        return output;
    }
    
    
    private static void testPrintFiles(List<File>files )
    {
    	for (final File f : files)
    	{
    		System.out.println("\nFile - " + f.getName());
    		BufferedReader br = null;
    		 
    		try {
     
    			String sCurrentLine;
     
    			br = new BufferedReader(new FileReader(f.getAbsolutePath()));
     
    			while ((sCurrentLine = br.readLine()) != null) {
    				System.out.println(sCurrentLine);
    			}
     
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				if (br != null)br.close();
    			} catch (IOException ex) {
    				ex.printStackTrace();
    			}
    		}
    	}
    }
    
    /**
     * Creates List of files - java.io.File objects to process
     * 
     */
    private static List<File> getFilesToProcess_String_To_File(String code)
    {
    	final List<File> files = Lists.newLinkedList();
//    	File file = new File("source.java");
    	File file = new File("test.java");
        
        try{
    		String data = " This content will append to the end of the file";
 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        
	        bufferWritter.write(code);
	        bufferWritter.close();
 
	        System.out.println("Done");
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
        
        files.add(file);
        
        return files;
    }
    
    /**
     * Creates the Checker object.
     *
     * @param aConfig the configuration to use
     * @param aNosy the sticky beak to track what happens
     * @return a nice new fresh Checker
     */
    private static Checker createChecker(Configuration aConfig,
                                         AuditListener aNosy)
    {
        Checker c = null;
        try {
            c = new Checker();

            final ClassLoader moduleClassLoader =
                Checker.class.getClassLoader();
            c.setModuleClassLoader(moduleClassLoader);
            c.configure(aConfig);
            c.addListener(aNosy);
        }
        catch (final Exception e) {
            System.out.println("Unable to create Checker: "
                               + e.getMessage());
            e.printStackTrace(System.out);
            System.exit(1);
        }
        return c;
    }

    /**
     * Determines the files to process.
     *
     * @param aLine the command line options specifying what files to process
     * @return list of files to process
     */
    private static List<File> getFilesToProcess(CommandLine aLine)
    {
        final List<File> files = Lists.newLinkedList();
        final String[] remainingArgs = aLine.getArgs();
        
        // [debugging]
        System.out.println("Size is : " + aLine.getArgs().length);
        for ( int i=0; i< aLine.getArgs().length; i++)
        {
        	System.out.println(aLine.getArgs()[i]);
        }
        
        for (String element : remainingArgs) {
            traverse(new File(element), files);
        }

        if (files.isEmpty()) {
            System.out.println("Must specify files to process");
            usage();
        }
        return files;
    }

    /**
     * Create the audit listener
     *
     * @param aLine command line options supplied
     * @param aOut the stream to log to
     * @param aCloseOut whether the stream should be closed
     * @return a fresh new <code>AuditListener</code>
     */
    private static AuditListener createListener(CommandLine aLine,
                                                OutputStream aOut,
                                                boolean aCloseOut)
    {
        final String format =
            aLine.hasOption("f") ? aLine.getOptionValue("f") : "plain";

        AuditListener listener = null;
        if ("xml".equals(format)) {
            listener = new XMLLogger(aOut, aCloseOut);
        }
        else if ("plain".equals(format)) {
            listener = new DefaultLogger(aOut, aCloseOut);
        }
        else {
            System.out.println("Invalid format: (" + format
                               + "). Must be 'plain' or 'xml'.");
            usage();
        }
        return listener;
    }

    /**
     * Loads the configuration file. Will exit if unable to load.
     *
     * @param aLine specifies the location of the configuration
     * @param aProps the properties to resolve with the configuration
     * @return a fresh new configuration
     */
//    private static Configuration loadConfig(CommandLine aLine,
//                                            Properties aProps)
//    {
//        try {
//            return ConfigurationLoader.loadConfiguration(
//                    aLine.getOptionValue("c"), new PropertiesExpander(aProps));
//        }
//        catch (final CheckstyleException e) {
//            System.out.println("Error loading configuration file");
//            e.printStackTrace(System.out);
//            System.exit(1);
//            return null; // can never get here
//        }
//    }
    private static String loadConfig(CommandLine aLine,
            Properties aProps)
	{
		try {
			
			Configuration anew =  ConfigurationLoader.loadConfiguration(
					aLine.getOptionValue("c"), new PropertiesExpander(aProps));
			return "Sucess";
			
		}
//			catch (final CheckstyleException e) {
		catch (final Exception e) {
//				System.out.println("Error loading configuration file");
//				e.printStackTrace(System.out);
//				System.exit(1);
				return e.getMessage(); // can never get here
	}
}
    
    /** Prints the usage information. **/
    private static void usage()
    {
        final HelpFormatter hf = new HelpFormatter();
        hf.printHelp(
            "java "
                + Main.class.getName()
                + " [options] -c <config.xml> file...",
            OPTS);
        System.exit(1);
    }

    /**
     * Traverses a specified node looking for files to check. Found
     * files are added to a specified list. Subdirectories are also
     * traversed.
     *
     * @param aNode the node to process
     * @param aFiles list to add found files to
     */
    private static void traverse(File aNode, List<File> aFiles)
    {
        if (aNode.canRead()) {
            if (aNode.isDirectory()) {
                final File[] nodes = aNode.listFiles();
                for (File element : nodes) {
                    traverse(element, aFiles);
                }
            }
            else if (aNode.isFile()) {
                aFiles.add(aNode);
            }
        }
    }

    /**
     * Loads properties from a File.
     * @param aFile the properties file
     * @return the properties in aFile
     */
    private static Properties loadProperties(File aFile)
    {
        final Properties properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(aFile);
            properties.load(fis);
        }
        catch (final IOException ex) {
            System.out.println("Unable to load properties from file: "
                + aFile.getAbsolutePath());
            ex.printStackTrace(System.out);
            System.exit(1);
        }
        finally {
            Utils.closeQuietly(fis);
        }

        return properties;
    }
}
