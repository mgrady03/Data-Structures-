/*
	S20 cs445 Instructor Tim Hoffman
	YOUR NAME: Mary Grady
	YOUR PITT ID: MTG48
	(i.e. "JFK63", not your pSoft#)
*/


import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader("state2presidents.txt") );

		BufferedReader infile2 = new BufferedReader( new FileReader("allPresidents.txt") );

    BufferedReader infile3 = new BufferedReader( new FileReader("allStates.txt") );


		// YOUR CODE HERE TO DECLARE MAPS OR SETS TO HOLD THE DATA OF THESE THREE INPUT FILES
    TreeMap<String, TreeSet<String>> state2presidents= new TreeMap<String, TreeSet<String>>();
    TreeSet<String> allPresidents= new TreeSet<String>();
		TreeSet<String> allStates= new TreeSet<String>();


		// ################# STEP #1 worth 50 % ###################

		// YOUR CODE HERE TO READ infile1 INTO A Map

		while(infile1.ready())
		{
			ArrayList<String> prez= new ArrayList<String>(Arrays.asList(infile1.readLine().split(" ")));//split the presidents from the states
			String state= prez.get(0);//add states to list
			prez.remove(0);//remove states
			state2presidents.add(state, new TreeSet<String>(prez));//concant states and prez in alphabetic order
		}
		infile1.close();

		System.out.println( "The following states had these presidents born in them:\n");  // DO NOT REMOVE OR MODIFY


		for(String state: state2presidents.keySet())//keySet() returns key elements in hash set
		{
			System.out.print(state+" ");
			for(String prez2: state2presidents.get(state))
			{
				System.out.print(prez2+" ");
			}
			System.out.println();
		}//prints out states and the presdents in alphabetical order

		// ################# STEP #2 worth 20% ###################

		System.out.println( "\nList of presidents and the state each was born in:\n");  // DO NOT REMOVE OR MODIFY

		// YOUR CODE HERE TO PRINT THE INVERSION OF THE MAP worth 15%

		while(infile2.ready())
		{
			allPresidents.add(infile2.readLine());
		}//reads infile2 and adds it to treeSet
		infile2.close();

		TreeSet<String> nonexist= new TreeSet<String>();//prez that dont have a state associated

		for(String prez3: allPresidents)
		{
			String state2=location(prez3, state2presidents);
			if(state2==null)
			{
				nonexist.add(prez3);
			}
			else
			{
				System.out.println(prez3+" "+state2);//prints out the state and prez born in it alphabetially
			}
		}




		// ################# STEP #3 worth 20% ###################

		System.out.println( "\nThese presidents were born before the states were formed:\n");  // DO NOT REMOVE OR MODIFY

		for(String prez4:nonexist)//above where state of prez is null nonexistant
		{
			System.out.println(prez4);
		}
		// YOUR CODE HERE TO PRINT THE NAME(S) Of ANY PRESIDENT(s)
		//  WHO WERE BORN BEFORE THE STATES WERE FORMED = 10%






		// ################# STEP #4 worth 10% ###################

		System.out.println( "\nThese states had no presidents were born in them:\n");

    while(infile3.ready())
		{
			allStates.add(infile3.readLine());
		}//reads infile3 adds it to treeSet
		infile3.close();

		for(String state3:allStates)
		{
			if(state2presidents.get(state3)==null)//if prez is null in the states from the file then it gets added to treeSet
			{
				System.out.println(state3);//prints states
			}
		}
		// YOUR CODE HERE TO PRINT THE NAME(S) OF ANY STATE(s) WHICH HAD NO PRESIDENT BORN IN THEM 5%




	} // END MAIN
	//              - - - - - - - - - - -  H E L P E R    M E T H O D S     D O W N    H E R E  - - - - - - - - - -

    static String location(String prez5, TreeMap<String, TreeSet<String>>state2presidents)
		{
			for(String state:state2presidents.keySet())
			{
				if(state2presidents.get(state).contains(prez5))
				{
					return state;
				}

			}
			return null;
		}







}	// END POTUS CLASS
