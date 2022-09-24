/*
	Deck class
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE )
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		// YOU DO THIS => init deck to be exactly numCards long
		deck = new int[numCards];
		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
		for(int i=0; i<=numCards-1; i++)
		{
			deck[i]=i;
		}
	}

	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		int [] modified = new int [deck.length];
    //int count=0;
		int[]firsthalf = Arrays.copyOfRange(deck, 0, deck.length/2);
		int[]secondhalf = Arrays.copyOfRange(deck, deck.length/2, deck.length);


		for(int i=1, x =0; i<deck.length;i+=2, x++){
			modified[i] = firsthalf[x];
		}
		for(int i=0,  x=0;i<deck.length;i+=2, x++ ){
			modified[i] = secondhalf[x];
		}
		for(int i =0;i<deck.length;i++){
			deck[i]= modified[i];
		}

	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int [] modified = new int [deck.length];
    //int count=0;
		int[]firsthalf = Arrays.copyOfRange(deck, 0, deck.length/2);
		int[]secondhalf = Arrays.copyOfRange(deck, deck.length/2, deck.length);


		for(int i=0,  x =0; i<deck.length;i+=2, x++){
			modified[i] = firsthalf[x];
		}
		for(int i=1, x  = 0; i<deck.length;i+=2, x++){
			modified[i] = secondhalf[x];
		}
		for(int i =0;i<deck.length;i++){
			deck[i]= modified[i];
		}

	}

	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		for(int i=0; i<deck.length; i++)
		{
			if(deck[i]==i)
			{

			}
			else
			return false;
		}
		return true;
	//	return false; JUST HERE TO COMPILE
	}
}	// END DECK CLASS
