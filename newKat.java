import java.util.*;


public class newKat{

  public static void main(String [] args)
  {
    /*Scanner scan=new Scanner(System.in);

double [][] A= new double [2][2];

/*
2,3
4,5


for(int row=0; row<2; row++)
   for( int col=0; col<2; col++)
   {
    System.out.println("Enter a number at (" +row+", "+col+"): ");
    double num=scan.nextInt();
    A[row][col]=num;
   }

   System.out.println("You have entered the following values for the matrix: ");

   for(int row=0; row<2; row++)
      for(int col=0; col<2; col++)
      {
        System.out.println(A[row][col]);
      }*/
      int i;
for(i=305; i<=365; i++)
i*=i;

int j;
for(j=1; j<=305; j++)
j*=j;

double sum=(i*j)/(Math.pow(365,60));
System.out.println((int)Math.pow(365,60));
System.out.println(i);
System.out.println(sum);



 }
}
