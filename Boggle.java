import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle
{
	static String[][] board;
	static long startTime,endTime; // for timing
	static final long MILLISEC_PER_SEC = 1000;
	static TreeSet<String> dictionary;
	static TreeSet<String> hits;

	// define your dictionary set and and your hits set UP HERE as TreeSets

	public static void main( String args[] ) throws Exception
	{
		startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );
		dictionary=new TreeSet<String>();
		hits= new TreeSet<String>();

		BufferedReader scanDictionary=new BufferedReader(new FileReader(args[0]));

		while(scanDictionary.ready())
		{

			String word=scanDictionary.readLine();
			if(word.length()>2)
			dictionary.add(word);
		}
		scanDictionary.close();
		// INITIALIZE DICT AND HITS HERE

		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  ); // FOR EACH [R][C] THE WORD STARTS EMPTY


		// EVENTUALLY YOU ADD HERE
		// PRINT OUT YOUR SORTED HITS CONTAINER ONE WORD PER LINE

		for(String result: hits)
		{
			System.out.println(result);
		}

		endTime =  System.currentTimeMillis(); // for timing
		//System.out.println("GENERATION COMPLETED: runtime=" + (endTime-startTime)/MILLISEC_PER_SEC );

	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{
		word += board[r][c];

		if(dictionary.contains(word))
		{
		hits.add(word);
	  }

		//System.out.println( word ); // EVENTUALLY REPLACE WITH IF FOUND IN DICT ADD TO HITS CONTAINER

		// THIS IS THE FORM OF EACH OF YOUR N,NE,E,SE,S,SW,W,NW BLOCKS

		// IM GIVING you THE NORTH VERSION - YOU MUST WRITE 7 MORE BELOW IT
		// DO NOT ELSE THEM OFF GIVE EVERY BLOCK AN INDEPENDENT IF TEST
		// YOU WANT TO RESUME YOUR CLOCKWISE SWEEP OF NEIGHBORS

		// NORTH IS [r-1][c]

		if ( r-1 >= 0 && board[r-1][c] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r-1, c, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// NE IS [r-1][c+1]  YOU WILL NEED TO TEST BOTH r-1 AND c+1 FOR OUT OF BOUNDS
    if(r-1>=0 && c+1<=board[r].length-1 && board[r-1][c+1]!=null)
		{
			String unMarked= board[r][c];
			board[r][c]=null;
			dfs(r-1,c+1,word);
			board[r][c]=unMarked;
		}
		// E IS [r][c+1]
   if(c+1<=board[r].length-1 && board[r][c+1]!=null)
	 {
		 String unMarked=board[r][c];
		 board[r][c]=null;
		 dfs(r,c+1,word);
		 board[r][c]=unMarked;
	 }
		// SE IS ...
   if(r+1<=board.length-1 && c+1<=board[r].length-1 && board[r+1][c+1]!=null)
	 {
		 String unMarked=board[r][c];
		 board[r][c]=null;
		 dfs(r+1,c+1,word);
		 board[r][c]=unMarked;
	 }
		// S IS ...
  if(r+1<=board.length-1 && board[r+1][c]!=null)
	{
		String unMarked=board[r][c];
		board[r][c]=null;
		dfs(r+1,c,word);
		board[r][c]=unMarked;
	}
		// SW IS ...
   if(r+1<=board.length-1 && c-1>=0 && board[r+1][c-1]!=null)
	 {
		 String unMarked=board[r][c];
		 board[r][c]=null;
		 dfs(r+1,c-1,word);
		 board[r][c]=unMarked;
	 }
		// W IS ...
   if(c-1>=0 &&board[r][c-1]!=null)
	 {
		 String unMarked=board[r][c];
		 board[r][c]=null;
		 dfs(r,c-1,word);
		 board[r][c]=unMarked;
	 }
		// NW IS ...
   if(r-1>=0 && c-1>=0 && board[r-1][c-1]!=null)
	 {
		 String unMarked=board[r][c];
		 board[r][c]=null;
		 dfs(r-1,c-1,word);
		 board[r][c]=unMarked;
	 }

	} // END DFS ----------------------------------------------------------------------------

	//=======================================================================================
	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD

} // END BOGGLE CLASS
