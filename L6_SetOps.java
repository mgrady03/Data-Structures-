import java.io.*;
import java.util.*;

public class L6_SetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		TreeSet<String> set1 = loadSet( infile1 );
		TreeSet<String> set2 = loadSet( infile2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		TreeSet<String> union = union( set1, set2 );
		printSet( "\nunion: ", union );

		TreeSet<String> intersection = intersection( set1, set2 );
		printSet( "\nintersection: ",intersection );

		TreeSet<String> difference = difference( set1, set2 );
		printSet( "\ndifference: ",difference );

		TreeSet<String> xor = xor( set1, set2 );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// only define 1 TreeSet no arrays
	static TreeSet<String> loadSet( BufferedReader infile ) throws Exception
	{
		TreeSet<String> newSet=new TreeSet<String>();
		
		while(infile.ready())
		{
		 newSet.add(infile.readLine());
		}
		infile.close();

		return newSet;
	}
	// NO arrays allowed: USE ENHANCED FOR LOOP
	static void printSet( String caption, TreeSet<String> set )
	{
		String result=caption;
		for(String s: set)
		{
			result+=s+" ";
		}
		System.out.println(result);
	}


	/* ###############################################################
		For each of the following set operations you must return a TreeSet<String> that represents
		the union / intersection / difference / XOR of the two incoming sets
		NO LOOPs ALLOWED.  NO arrays allowed to be defined.
		Only a single TreeSet<String> may be defined and returned
		** except for the case of the xor you may define multiple TreeSets
	*/

	static TreeSet<String> union( TreeSet<String> set1, TreeSet<String> set2 )
	{
    TreeSet<String> union=new TreeSet<String>(set1);

		union.addAll(set2);

		return union;
	}

	static TreeSet<String> intersection( TreeSet<String> set1, TreeSet<String> set2 )
	{
		TreeSet<String> inter=new TreeSet<String>(set1);

		inter.retainAll(set2);

		return inter;

	}

	static TreeSet<String> difference( TreeSet<String> set1, TreeSet<String> set2 )
	{
		TreeSet<String> diff=new TreeSet<String>(set1);

		diff.removeAll(intersection(set1,set2));

		return diff;
	}

	static TreeSet<String> xor(TreeSet<String> set1, TreeSet<String> set2 )
	{
		TreeSet<String> xor= new TreeSet<String>(union(set1,set2));


   xor.removeAll(intersection(set1,set2));

	 return xor;

	}

} // END CLASS
