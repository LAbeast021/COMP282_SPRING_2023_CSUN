package HW4;
import java.util.*;
import java.io.*;
// CSUN COMP 282 Spring 23, HesampourH4.java
// Program to merge 2 sorted files in one sorted file.
// End of each inputfile is 2147483647(Integer.MAX_VALUE). 
// Author: G. Dastghaiby Fard

public class HesampourH4{
	//use prt for System.out to save typing
	PrintStream prt = System.out;
	
	// print file contents
	private void prtfile(String fn){
		//declare variables
		int i = 0, x;
		prt.printf("\n%s:", fn);
		try{
			Scanner inf = new Scanner(new File(fn)); 
			while (inf.hasNext()) {
				//read an input from fn
				i++;
				x = inf.nextInt();
				prt.printf(" %4d ", x);
				if(i % 20 == 0) 
					prt.printf("\n%s:", fn);
			} // endwhile
			prt.printf("(count=%d)", i);
			// close file
			inf.close();
		} catch (IOException e){
			prt.printf("\nOoops! Read Exception: %s", e);}
	} // end prtfile

	private void merge2way(String fn1, String fn2, String fn3) throws FileNotFoundException{
		int firstInt , secondInt ,  maxVal = Integer.MAX_VALUE ;
		// int maxVal = 2147483647 ,
		Scanner firstFile = new Scanner(new File(fn1));
        Scanner secondFile = new Scanner(new File(fn2));
        PrintWriter thirdFile = new PrintWriter(new File(fn3));
 
        firstInt = firstFile.nextInt();
        secondInt = secondFile.nextInt();

		while (firstInt != maxVal && secondInt != maxVal) {
			if(firstInt < secondInt) {
				thirdFile.printf(" %3d " , firstInt);
				firstInt = firstFile.nextInt() ;
			}
			else if (firstInt == secondInt) {
				thirdFile.printf(" %3d  %3d" , firstInt , secondInt);
				firstInt = firstFile.nextInt();
				secondInt = secondFile.nextInt();
			}
			else if (firstInt > secondInt) {
				thirdFile.printf(" %3d " , secondInt);
				secondInt = secondFile.nextInt() ;
			}
		}
		if (firstInt == maxVal) {
			while (secondInt != maxVal){
				thirdFile.printf(" %3d " , secondInt);
				secondInt = secondFile.nextInt() ;
			}
		}
		else if (secondInt == 2147483647) {
			while (firstInt != 2147483647){
				thirdFile.printf(" %3d " , firstInt);
				firstInt = firstFile.nextInt() ;
			}
		}

			firstFile.close();
            secondFile.close();
            thirdFile.close();
	} // end merge2way

	public static void main(String[] args) throws Exception{
		int cnt = args.length; // get no. of atguments
		String fn1, fn2, fn3;
		if (cnt < 3){
		    System.out.printf("\n\tOOOPS Invalid No. of aguments!"+
			"\n\tTO Execute: java HesampourH4 f1 f2 f3");
			return;
		} // end if	
		// get input file name
		fn1 = args[0]; 
		fn2 = args[1]; 
		fn3 = args[2]; 

		HesampourH4 srt = new HesampourH4();
		//Print input files
		srt.prtfile(fn1);
		srt.prtfile(fn2);
		//Call merge2way
		srt.merge2way(fn1, fn2, fn3);
		//Print output file
		srt.prtfile(fn3);
		
		
		//Replace ????????? with your name in next line
		System.out.printf("\n\n\tAuthor: K. Hesampour Date: %s\n", 
		java.time.LocalDate.now());
	}	// end main
} // end HesampourH4