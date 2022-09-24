import java.io.*;
import java.util.*;

public class Jumbles
{
	  public static void main(String[] args) throws Exception
	   {
			 //read in the args
	      BufferedReader dictionary= new BufferedReader( new FileReader(args[0]));
	      BufferedReader jumbles= new BufferedReader( new FileReader(args[1]));

        //create sets for jumbles 1 or 2? one for length to match words--then canonicalize the suspect word for matches?
	      TreeSet<Integer> jLength= new TreeSet<Integer>();
				TreeMap<String, String> jMap= new TreeMap<String, String>();

	      TreeMap<String, TreeSet<String>> result= new TreeMap<String, TreeSet<String>>();

        //jumbles file read in
	      while (jumbles.ready())
				{
	         String jum= jumbles.readLine();

	         jMap.put(canon(jum),jum);
	         jLength.add(jum.length());
	         result.put(jum, new TreeSet<String>());
	      }

	      jumbles.close();

        //dictionary file read in match them together
	      while (dictionary.ready())
				{
	        String dic= dictionary.readLine();

	        if (jLength.contains(dic.length()))
					{
	          String jum= jMap.get(canon(dic));
	          if (jum!= null)
						{
	           result.get(jum).add(dic);
					  }
	        }
	      }

	      dictionary.close();

	      for(Map.Entry<String, TreeSet<String>> matched: result.entrySet())
				{
	        System.out.print(matched.getKey() + " ");
	        for (String word : matched.getValue())
					{
	        System.out.print(word + " ");
				  }
	        System.out.println();
	      }

	  }


			static String canon(String key)
			{
	      char [] wordArr= key.toCharArray();
	      Arrays.sort(wordArr);
				String canonresult= new String(wordArr);
	      return canonresult;
	    }

	}
