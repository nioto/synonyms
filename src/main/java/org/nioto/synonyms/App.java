package org.nioto.synonyms;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    throws Exception {
    	
    	String word ;
    	word = "abaisser";
    	System.out.println( Thesaurus.getSynonyms(word));
    	word = "zythum";
    	System.out.println( Thesaurus.getSynonyms(word));
    	
    	word = "bohémien";
    	System.out.println( Thesaurus.getSynonyms(word));
    	
    }
}
