package com.aooci.ff.console;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import com.aooci.ff.util.DataLoader;




public class FantasyForever {
	private static Logger log = Logger.getLogger(FantasyForever.class);

	public static final String CONFIG_FILE = "config.xml";
	public static final String ARG_TRAINING = "-train";
	public static final String ARG_APPLY = "-apply";
	public static final String ARG_TEST = "-test";

	
	public static void displayWelcome() {
		displayHelp();
	}
	
	public static void displayHelp() {
		displaySystemInfo();
		System.out.println( "Usage: java com.aooci.lotto.Console [OPTIONS]\n" +
				ARG_TRAINING + " <Min Error> <Date started Loading>               Training\n" +
				ARG_APPLY + " <year> <month> <day>          Apply\n" +
				ARG_TEST + " <Minimal hits> <Top numbers>           Apply Test\n"
                );
		System.out.println();
        System.out.println("=========================================================");
	}
	
	public static void displaySystemInfo(){
		
		System.out.println("Operation System:"+System.getProperty("os.name")
				+"("+System.getProperty("os.arch")
				+") Version:"+System.getProperty("os.version"));
		System.out.println("JRE Version:"+System.getProperty("java.version")
				+"("+System.getProperty("sun.arch.data.model")+"bit)");
	}
	
	public static void displayBye() {
		System.out.println();
		System.out.println("=========================================================");
		System.out.println("                       Good Luck!");
		System.out.println("=========================================================");
	}
	
	public static void main(String[] args) {
		displayHelp();

		try {
			XMLConfiguration config = new XMLConfiguration("job/euromillions/config.xml");
			System.out.println(config.getString("sourceResultFile"));
			System.out.println(config.getString("exported-binary-result-file[@enable]"));
			DataLoader.loadBinaryResults(config.getString("sourceResultFile"), config.getString("sourceResultFile[@separator]").charAt(0), 50, config.getInt("sourceResultFile[@firstLine]"), 1, 6);
			// do something with config
		} catch (ConfigurationException cex) {
			cex.printStackTrace();
			// something went wrong, e.g. the file was not found
		}

		displayBye();
	}

}
