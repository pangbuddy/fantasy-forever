package com.aooci.ff.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import com.aooci.ff.juge.AbstractJuge;
import com.aooci.ff.juge.HabitJuge;
import com.aooci.ff.util.DataLoader;




public class FFConsole {
	private static Logger log = Logger.getLogger(FFConsole.class);

	
	public static void displayWelcome() {
		displayHelp();
	}
	
	public static void displayHelp() {
		displaySystemInfo();
		System.out.println( "Usage: java com.aooci.lotto.Console [OPTIONS]\n"
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
			Map<String, boolean[]> results = DataLoader.loadBinaryResults(config.getString("sourceResultFile"), config.getString("sourceResultFile[@separator]").charAt(0), 50, config.getInt("sourceResultFile[@firstLine]"), 1, 6);
			// do something with config
			boolean[][] resultData = new boolean[results.size()][50];
			
			int counter = 0;
			for(Entry<String, boolean[]> e:results.entrySet()){
					resultData[counter++] = e.getValue();
					System.out.println(e.getKey());
			}
			
			Map<Integer, AtomicInteger> scores = new HashMap<Integer, AtomicInteger>();
			for(int i=0;i<50;i++){
				scores.put(i+1, new AtomicInteger(0));
			}
			
			AbstractJuge juge = new HabitJuge();
			juge.examine(scores, resultData);
			
			for(int i=0;i<50;i++){
				System.out.println((i+1)+" - "+scores.get(i+1).intValue());
			}
			
		} catch (ConfigurationException cex) {
			cex.printStackTrace();
			// something went wrong, e.g. the file was not found
		}

		displayBye();
	}

}
