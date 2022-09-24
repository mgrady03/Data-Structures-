import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place
  @SuppressWarnings("unchecked")
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################


   @SuppressWarnings("unchecked")
	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		int count=0;

		Node<T> curr=head;

		while(curr!=null){

			count++;
			curr=curr.next;
		}


		return count;
	}

  @SuppressWarnings("unchecked")
	public boolean empty()
	{
		return this.size()==0;
	}

  @SuppressWarnings("unchecked")
	public boolean contains( T key )
	{
		return this.search(key)!=null;
	}

  @SuppressWarnings("unchecked")
	public Node<T> search( T key )
	{
		Node<T> curr=head;

		while(curr!=null)
		{
			if(curr.data.equals(key))
			{
				return curr;
			}
			curr=curr.next;
		}
		return null;

		//WALK THE LIST LOOKING FOR 1ST OCCURANCE OF NODE WITH DATA VALUE EQUAL TO KEY
	}



	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		Node<T>newNode= new Node(data,null);

		if(head==null)
		insertAtFront(data);

else
{
		Node<T> curr=head;

		while(curr.next!=null)
		{
			curr=curr.next;
		}
			curr.next=newNode;
}

	}

	@SuppressWarnings("unchecked")  //CAST TO COMPARABLE THROWS WARNING
	public void insertInOrder(T  data)
	{
		Comparable cdata = (Comparable) data;
		if ( head == null ||  (cdata.compareTo( head.data ) < 0 ))
		{
			this.insertAtFront(data);
			return;
		}

		Node<T> curr = head;

		while(curr.next!=null){


			if(cdata.compareTo(curr.next.data)<=0)
			{
				Node<T> newNode=new Node<T>(data, curr.next);
				curr.next=newNode;
				return;
			}
			else if(cdata.compareTo(curr.next.data)>0)
			{
				curr=curr.next;
			}

		}
		this.insertAtTail(data);

		return;

	}

  @SuppressWarnings("unchecked")
	public boolean remove(T key)
	{
		if(this.empty())
		{
			return false;
		}

		if(head.data.equals(key))
		{
			if(head.next==null)
			{
				head=null;
				return true;
			}
			head=head.next;
			return true;
		}

    Node<T> curr=head;

		while(curr.next!=null)
		{
			if(curr.next.data.equals(key))
			{
				curr.next=curr.next.next;
				return true;
			}
			else
			{
				curr=curr.next;
			}
		}
		return false;

	}

  @SuppressWarnings("unchecked")
	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
    if(head==null)
		return false;
		else if(head.next==null)
		{
			head=null;
			return true;
		}
		else
		{
			Node<T> curr=head;
			while(curr.next.next!=null)
			{
				curr=curr.next;
			}
			curr.next=null;
			return true;
		}

	}

  @SuppressWarnings("unchecked")
	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
    if(head==null)
		{
		return false;
	  }
		else
		{
			if(this.size()==1)
			{
				head=null;
				return true;
			}
			else
			{
				Node<T> curr=head;
				curr=head.next;
				head=null;
			}
		}
		return false;

	}

  @SuppressWarnings("unchecked")
	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> union = new LinkedList<T>();

		Node<T> newHead1=this.head;
		Node<T> newHead2;

		while(newHead1!=null)
		{
			union.insertAtTail(newHead1.data);
			newHead1=newHead1.next;
		}

		for(newHead2=other.head; (newHead2!=null); newHead2=newHead2.next)
		{
			if(!union.contains(newHead2.data))
			{
				union.insertInOrder(newHead2.data);
			}
		}
		return union;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter=new LinkedList<T>();

		Node<T> curr;

		for(curr=this.head; !(curr==null); curr=curr.next)
		{
			if(other.contains(curr.data))
			{
				inter.insertInOrder(curr.data);
			}
		}


		return inter;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();

		Node<T> newHead1=this.head;

		while(newHead1!=null)
		{
			if(other.contains(newHead1.data)==false)
			diff.insertInOrder(newHead1.data);

			newHead1=newHead1.next;
		}

		return diff;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> xor( LinkedList<T> other )
	{

	return this.union(other).diff(this.inter(other));

	}

} //END LINKEDLIST CLASS



class Node<T>
{
  T data;
  Node<T> next;

  @SuppressWarnings("unchecked")
  Node()
  {
    this( null, null );
  }
  @SuppressWarnings("unchecked")
  Node(T data)
  {
    this( data, null );
  }
  @SuppressWarnings("unchecked")
  Node(T data, Node<T> next)
  {
    this.data=data;
    this.next=next;
  }

  public String toString()
  {
	  return ""+this.data;
  }

} //EOF

// A D D   N O D E   C L A S S  D O W N   H E R E
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S
// M A K E  T O  S T R I N G  P U B L I C
