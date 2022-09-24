// MissingNumber.java  skeleton starter file for X/Credit #1 445 Spring 2021
// YOU WRITE IT ALL. NO STARTER CODE EXCEPT THE NAME OF THE FILE MUST BE 'MissingNumber.java'

public class MissingNumber
{

	public static void main( String[] args )
	{
		int [] arr1 = {1, 3, 4, 5, 6, 7, 8, 9, 10 };
		int [] arr2 = {1, 2, 4, 5, 6, 7, 8, 9, 10 };
		int [] arr3 = {1, 2, 3, 5, 6, 7, 8, 9, 10 };
		int [] arr4 = {1, 2, 3, 4, 6, 7, 8, 9, 10 };
	  int [] arr5 = {1, 2, 3, 4, 5, 7, 8, 9, 10 };
		int [] arr6 = {1, 2, 3, 4, 5, 6, 8, 9, 10 };
		int [] arr7 = {1, 2, 3, 4, 5, 6, 7, 9, 10 };
		int [] arr8 = {1, 2, 3, 4, 5, 6, 7, 8, 10 };

		int [] arr9 = {1, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		int [] arr10 = {1, 2, 4, 5, 6, 7, 8, 9, 10, 11};
		int [] arr11 = {1, 2, 3, 5, 6, 7, 8, 9, 10, 11};
    int [] arr12 = {1, 2, 3, 4, 6, 7, 8, 9, 10, 11};
		int [] arr13 = {1, 2, 3, 4, 5, 7, 8, 9, 10, 11};
		int [] arr14 = {1, 2, 3, 4, 5, 6, 8, 9, 10, 11};
		int [] arr15 = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11};
		int [] arr16 = {1, 2, 3, 4, 5, 6, 7, 8, 10, 11};
		int [] arr17 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11};

		System.out.println(resulted(arr1));
		System.out.println(resulted(arr2));
		System.out.println(resulted(arr3));
		System.out.println(resulted(arr4));
		System.out.println(resulted(arr5));
		System.out.println(resulted(arr6));
		System.out.println(resulted(arr7));
		System.out.println(resulted(arr8));
		System.out.println(resulted(arr9));

		System.out.println(resulted(arr10));
		System.out.println(resulted(arr11));
		System.out.println(resulted(arr12));
		System.out.println(resulted(arr13));
		System.out.println(resulted(arr14));
		System.out.println(resulted(arr15));
		System.out.println(resulted(arr16));
		System.out.println(resulted(arr17));



	}

	public static int findMissingNumber(int [] array)
	{
		int currNum=0;
		int farNum=array.length-1;
		int middle=0;

		while((farNum-currNum)>=2)
		{
			middle=(currNum+farNum)/2;

			if((array[currNum]-currNum)!=(array[middle]-middle))
			farNum=middle;
			else if((array[farNum]-farNum)!=(array[middle]-middle))
			currNum=middle;

		}

		return array[currNum]+1;


	}

	public static String resulted(int [] array)
	{
		String result="";
		for(int i=0; i<array.length; i++)
		{
			result+=array[i]+" ";
		}
		result+="missing "+findMissingNumber(array);
		return result;

  }

}
