import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE

	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked")
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data );
		}
		infile.close();
	}



	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		CDLL_Node<T> newNode= new CDLL_Node(data,null,null);

		if(head==null)
		{
			newNode.next=newNode;
			newNode.prev=newNode;
			head=newNode;
		  return;
		}
		else
		{
			CDLL_Node<T> newNode2= new CDLL_Node(data,head.prev,head);
			head=newNode2;
			head.next.prev=head;
			head.prev.next=head;
			head=head.next;
		}
	}


	public int size()
	{
	  if(head==null)
		return 0;
		else
		{
			int count=1;
			CDLL_Node<T> curr=head.next;

		  while(!(curr.data.equals(head.data)))
			{
				count++;
       curr=curr.next;

			}

			return count;
		}
	}

	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{
		if(head.data.equals(key))
		return head;

		CDLL_Node<T> curr=head.next;

		while(!(curr.data.equals(head.data)))
		{
			if(curr.data.equals(key))
			return curr;

			curr=curr.next;

		}

		return null;
	}

	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String result=""+head.data;

		CDLL_Node<T> curr=head.next;

		while(!(curr.data.equals(head.data)))
		{
			result+="<=>"+curr.data;

		  curr=curr.next;

		}

		return result;

	}

	void removeNode( CDLL_Node<T> deadNode )
	{
		CDLL_Node<T> curr=head;

		while(!(deadNode.equals(curr)))
		{
			curr=curr.next;
		}

		curr.prev.next=curr.next;
		curr.next.prev=curr.prev;
	}

	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() <= 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;

		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.data;

			System.out.println( "stopping on "+curr.data+" to delete "+curr.data);

			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS
			// 1: you gotta move that curr off of the deadNode.
			//    if skipCount poitive do curr=curr.next  esle do  curr=curr.prev
			 if (skipCount > 0)
			 curr=curr.next;
			 else
			 curr=curr.prev;
			// 2: check to see if HEAD is pointing to the deadnode.
			//    If so make head=curr
			if(head.equals(deadNode))
			head=curr;

			// NOW DELETE THE DEADNODE
       removeNode(deadNode);
			// println("deleted. list now: + toString() ); // toString prints the
      System.out.println("deleted. list now: "+toString() );
			// if the list size has reached 1 return YOU ARE DONE.  RETURN RIGHT HERE
      if(size()==1)
			return;
			// ** println("resuming at curr.data, skipping curr.data + skipCount-1 nodes CLOCKWISE/COUNTERWISE after");
      if(skipCount>0)
			System.out.println("resuming at "+curr.data+", skipping "+curr.data+" + "+(Math.abs(skipCount)-1)+" nodes CLOCKWISE after");
			else
			System.out.println("resuming at "+curr.data+", skipping "+curr.data+" + "+(Math.abs(skipCount)-1)+" nodes COUNTERWISE after");
			// write loop that advances curr pointer skipCount times (be sure of CLOCKWISE or COUNTER)
       if(skipCount>0)
			 {
				 for(int i=0; i<skipCount; i++)
				 {
					 curr=curr.next;
				 }
			 }
			 else
			 {
				 for(int i=0; i<(skipCount*-1); i++)
				 {
					 curr=curr.prev;
				 }
			 }
			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
			// Scanner kbd = new Scanner( System.in ); String junk = kbd.nextLine();

		}
		while (size() > 1 );  // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}

} // END CDLL_LIST CLASS

class CDLL_Node<T>
{
  T data;
  CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

  CDLL_Node()
  {
    this( null, null, null );  // 3 FIELDS TO INIT
  }

  CDLL_Node(T data)
  {
    this( data, null, null);
  }

  CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {
  this.data=data;
	this.prev=prev;
  this.next=next;
  }

  public String toString()
  {
	  return ""+data;
  }

} //EOF
