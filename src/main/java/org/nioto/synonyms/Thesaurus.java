package org.nioto.synonyms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nioto
 *
 */
public class Thesaurus {

	private final static String PATH="thes_fr.dat";
	
	private final static Thesaurus INSTANCE = new Thesaurus();

	private Map<String, List<String>> map ;
	
	private Thesaurus() {
		try {
			this.map = read();
		} catch (ThesaurusException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	private Map<String, List<String>> read()
	throws ThesaurusException {
		String encoding = IOUtils.getEncoding( PATH );
		if( encoding == null ) {
			throw new ThesaurusException("No encoding found !!");
		}
		InputStream io =null;
		Reader reader = null;
		BufferedReader br =null;
		Map<String, List<String>> map = new HashMap<String, List<String>> ();
		try {
			io = ClassLoader.getSystemResourceAsStream(PATH);
			reader = new InputStreamReader(io, encoding);
			br = new BufferedReader(reader); 
			// skip first line as already read as encoding
			br.readLine();
			String line;
			while ( (line = br.readLine())  != null ) {
				String word;
				int lines;
				String[] arr = line.split("\\|");
				word = arr[0];
				lines = Integer.parseInt(arr[1]);
				List<String> list = new ArrayList<String>();
				for (int i = 0; i< lines; i++){
					line = br.readLine();
					arr = line.split("\\|");
					for (int j=1; j<arr.length; j++) {
						list.add(arr[j]);
					}
				}
				map.put(word, Collections.unmodifiableList(list));
			}
		} catch (UnsupportedEncodingException uee) {
			throw new ThesaurusException( uee );
		} catch (IOException ioe) {
			throw new ThesaurusException( ioe );
		} finally {
			IOUtils.closeQuietly( io );
			IOUtils.closeQuietly( reader );
			IOUtils.closeQuietly( br );
		}
		return Collections.unmodifiableMap(map);
	}

	public static List<String> getSynonyms(String word){
		return INSTANCE.map.get(word);
	}
}