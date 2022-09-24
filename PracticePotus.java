import java.util.*;
import java.io.*;

public class PotusSolution
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();

		BufferedReader infile2 = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();

		BufferedReader infile3 = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();

		while (infile1.ready())
		{
			ArrayList<String> presidents = new ArrayList<String>( Arrays.asList( infile1.readLine().split(" ") ) );
			String state = presidents.get(0); presidents.remove(0); // the first element was state - not president
			state2Presidents.put( state, new TreeSet<String>(presidents) );
		}

		// MAP IS BUILT. DUMP IT 60%  OR  PRINT IT FORMATTED 70%

		System.out.println( "The following states had these presidents born in them:\n");

		System.out.println( state2Presidents );  // the 50% toString dump

		for ( String state : state2Presidents.keySet() )
		{
			System.out.print( state + " ");
			for ( String pres : state2Presidents.get( state ) )
				System.out.print( pres + " ");
			System.out.println();
		}

		// PRINT THE INVERSION OF THE MAP worth 15%

		// load  allPrez file into TreeSet
		System.out.println( "\nList of presidents and the state each was born in:\n");

		while (infile2.ready())
				allPresidents.add( infile2.readLine() );

		TreeSet<String> nonNativePotus = new TreeSet<String>();

		// for each prez, look him up in the first map. If found print his state
		// if not FOUND add him to the list of non native potus'
		for ( String thisPres : allPresidents )
		{
			String hisState = findStateOf( thisPres, state2Presidents );
			if ( hisState == null )
				nonNativePotus.add( thisPres );
			else
				System.out.println( thisPres + " " + hisState );
		}

		// PRINT THE NAME(S) Of ANY PRESIDENT(s) WHO WAS  BORN BEFORE THE STATES WERE FORMED 10%

		System.out.println( "\nThese presidents were born before the states were formed:\n");

		for ( String pres : nonNativePotus )
				System.out.println( pres );

		// PRINT THE NAME(S) OF ANY STATE(s) WHO HAD NO PRESIDENT BORN THERE 5%

		System.out.println( "\nThese states had no presidents were born in them:\n");

		while (infile3.ready())
				allStates.add( infile3.readLine() );

		for ( String thisState : allStates ) // ...are you in good hands?
		{
			if ( state2Presidents.get( thisState) == null )
				System.out.println( thisState );
		}



	} // END MAIN

	//              - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -

	private static String findStateOf( String thisPres, TreeMap<String,TreeSet<String>> state2Presidents )
	{
		for ( String state : state2Presidents.keySet() )
				if ( state2Presidents.get(state).contains(thisPres) )
				return state;
		return null;
	}

}	// END VALIDATE CLASS
