//import Logger;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.lang.reflect.Array;

public class DataManager {
	
	private volatile static DataManager INSTANCE;
	private DataManager() {
		InitMap();
	}
	
	private HashMap<String, String> hm;
	private void InitMap() {
		File file = new File("data.txt");
		if (!file.exists()) {
			// to do : Write error messager to specified file, include : date, class , position etc
			System.out.println("ERROR ! file not found");
		}
		
		hm = new HashMap<String, String>();
		hm.clear();
		
		try {
			BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String word = null;
			while ((word=bufr.readLine())!=null) {
				String sentence = bufr.readLine();
				hm.put(word, sentence);
			}
		} catch (FileNotFoundException e1) {
			// to do : write to specified file
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}
	
	public static DataManager GetInstance() {
		if (INSTANCE == null) {
			synchronized(DataManager.class) {
				if (INSTANCE == null) {
					INSTANCE = new DataManager();
				}
			}
		}
		return INSTANCE;
	}
	
	public String GetWordRandomly() {
		String key = null;
		if (hm.isEmpty()) {
			return null;
		}
		
		Random generator = new Random();
		String[] keys = hm.keySet().toArray(new String[0]);
		return keys[generator.nextInt(keys.length)];
	}
	
	public boolean CheckAnswer(String key, String userAns) {
		if (!hm.containsKey(key)) {
			return false;
		}
		
		if (!userAns.equals(hm.get(key))) {
			return false;
		}
		return true;
	}
}
