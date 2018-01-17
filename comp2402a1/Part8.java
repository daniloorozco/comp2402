package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Part8 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		// Your code goes here - see Part0 for an example
		Map<String, Integer> obj = new LinkedHashMap<String, Integer>();
			for (String line = r.readLine(); line != null; line = r.readLine()){
				if ((obj.containsKey(line))){
					obj.put(line, obj.get(line) + 1);
				}
				else{
					obj.put(line, 1);
				}
			}
			List<Map.Entry<String,Integer>> freq = new ArrayList<Map.Entry<String,Integer>>(obj.entrySet());
			Collections.sort(freq, new Comparator<Map.Entry<String,Integer>>() {
				@Override
				public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
					int res = e1.getValue().compareTo(e2.getValue());
					if(res == 0){
						return e1.getKey().compareTo(e2.getKey());
					}
					return res * -1;
				}
		});
		List<String> list = new ArrayList<String>();
		for(Map.Entry<String, Integer> e: freq){
    	list.add(e.getKey());
			//w.println(e);
		}
		for (String i : list){
			w.println(i);
		}
}

	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 10e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
