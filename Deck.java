/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE )
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
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

	public String toBitString( int n )
	{
		int num=(int)((Math.log(n)/Math.log(2))+1);
    int [] newnum= new int [num];

		for(int i=0; i<newnum.length; i++){
		newnum[i]=0;
	}

	  int count = 0;
		while(n>0){
			newnum[count++] = n%2;
			n=n/2;
		}

   String binary="";

   /*for(int i=0; i<newnum.length; i++){
	 		binary+=newnum[i];
		}*/

		for(int i=newnum.length-1; i>=0; i--)
		{
			binary+=newnum[i];
		}

		return binary;
	}

}	// END DECK CLASS
