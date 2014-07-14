/**
 * 
 */
package org.nioto.synonyms;

import java.io.Closeable;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author nioto
 *
 */
public final class IOUtils {

	/**
	 * Return the first line of the file, since it's the encoding of the content
	 * @param path
	 * @return
	 */
	public static String getEncoding(String path){
		Scanner sc = null;
		String enc = null;
		InputStream in = ClassLoader.getSystemResourceAsStream(path);
		if( in != null) {
			sc = new Scanner(in);
			enc = sc.nextLine();
		}
		closeQuietly( sc );
		closeQuietly( in );
		return enc;
	}
	/**
	 * Close a {@see Closeable} with throwing an exception
	 * @param c
	 */
	public static void closeQuietly( Closeable c) {
		if (c == null)
			return;
		try{
			c.close();
		} catch ( Throwable t) {
			// do nothing
		}
	}
}