import java.util.*;
import java.io.*;

public class Knapsack
{

public static void main(String [] args) throws Exception
{
  int[] set=new int[16];
  int target=0;


  Scanner infile=new Scanner(new File(args[0]));

  for(int i=0; i<set.length; i++)
    set[i]=infile.nextInt();

    target=infile.nextInt();

  for(int i=0; i<set.length; i++)
    System.out.print(set[i]+" ");

    System.out.println();
    System.out.println(target);


 for(int number=0; number < 0xFFFF; number++)
 {
   int sum=0;
   String open="";
   String openUp="";

   for(int i=set.length-1; i>=0; i--)
   {
     if((number>>i)%2==1)
     open+="1";
     else
     open+="0";
   }

   for(int i=set.length-1; i>=0; i--)
   {
     if(Character.toString(open.charAt(i)).equals("1"))
     {
       sum+=set[i];
       //System.out.print(set[i]+" ");
       openUp+=Integer.toString(set[i])+" ";
     }

   }
   if(sum==target)
   System.out.println(openUp);
 }




   }


 }
