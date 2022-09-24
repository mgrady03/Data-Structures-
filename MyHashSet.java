import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface
{	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;  // MAY CHOSE ANOTHER NUMBER

	public MyHashSet( int numBuckets )
	{	size=0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format("IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n", numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE );
	}

	public boolean add( String key )
	{
		// your code here to add the key to the table and ++ your size variable
		if(contains(key))
		return false;

    int hashInt=hashOf(key);
    Node newNode=new Node(key, bucketArray[hashInt]);

	   bucketArray[hashInt]=newNode;

		++size; // you just added a key to one of the lists
		if ( size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
			upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
			return true;
	}
	public boolean contains( String key )
	{
		int hashInt=hashOf(key);

    Node head=bucketArray[hashInt];

			while(head!=null)
			{
				if(head.data.equals(key))
				return true;
				else
				head=head.next;
			}

			return false;

	}

	public boolean remove(String key)
	{
 int hashInt=hashOf(key);

 if(!contains(key))
 {
 return false;
 }
 else if(bucketArray[hashInt].next==null)
		{
		bucketArray[hashInt]=null;
		return true;
	  }
  else
	{
		Node head=bucketArray[hashInt];
		if(head.data.equals(key))
		{
			bucketArray[hashInt]=head.next;
			return true;
		}

		while(head.next.next!=null && !head.next.data.equals(key))
		{
			head=head.next;
		}
		head.next=head.next.next;
		return true;
	}

	}

	public boolean isEmpty()
	{
    return (this.size()==0);
	}

	public int size()
	{
   return size;
	}

	public void clear()
	{
		/*for(int i=0; i<bucketArray.length; i++)
		{
			bucketArray[i]=null;
		}*/
		size=0;
		bucketArray=null;
	}

	private void upSize_ReHash_AllKeys()
	{	System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
						   size, bucketArray.length, bucketArray.length*2  );
		Node[] biggerArray = new Node[ bucketArray.length * 2 ];
		this.numBuckets = biggerArray.length;

	/*	FOR EACH LIST IN THE ARRAY
			FOR EACH NODE IN THAT LIST
				HASH THAT KEY INTO THE BIGGER TABLE
				BE SURE TO USE THE BIGGER .LENGTH AS THE MODULUS*/
for(int i=0; i<bucketArray.length; i++)
{
	for(Node head=bucketArray[i]; head!=null; head=head.next)
	{
		int hashInt=hashOf(head.data);
		Node implant=new Node(head.data, biggerArray[hashInt]);
		biggerArray[hashInt]=implant;
	}
}

		bucketArray = biggerArray;
	} // END OF UPSIZE & REHASH

	private int hashOf( String key) //  number returned -MUST- MUST BE IN [0..numBuckets-1]
	{
		int total=1;

		for(int i=0; i<key.length(); i++)
		{
			total=19*total+key.charAt(i);
		}

		return Math.abs(total%numBuckets);

		//return Math.abs(key.hashCode()) % numBuckets; // GET RID OF THIS: REPLACE WITH YOUR ALGORITHM
	 }

} //END MyHashSet CLASS

class Node
{	String data;
	Node next;
	public Node ( String data, Node next )
	{ 	this.data = data;
		this.next = next;
	}
}
