
import java.util.*;
import java.io.*;
// CSUN COMP 282 Spring 23, HesampourH5 .java
// Program to merge k sorted files in one sorted file.
// End of each inputfile is 2147483647(Integer.MAX_VALUE). 
// Author: G. Dastghaiby Fard

public class HesampourH5 {
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

	private void mergeKway(String fn1, String fn2, String fn3) throws FileNotFoundException{
		
	} // end mergeKway

	public static void main(int[] args) throws Exception{
		int cnt = args.length , k; // get no. of atguments

		if (cnt < 1){
		    System.out.printf("\n\tOOOPS Invalid No. of aguments!"+
			"\n\tTO Execute: java HesampourH5  f1 f2 f3");
			return;
		} // end if	
		// get input file name
		k = args[0]; 

		HesampourH5  srt = new HesampourH5() ;
		//Print input files
		// srt.prtfile(fn1);
		// srt.prtfile(fn2);
		// //Call mergeKway
		// srt.mergeKway(fn1, fn2, fn3);
		// //Print output file
		// srt.prtfile(fn3);
		
		
		//Replace ????????? with your name in next line
		System.out.printf("\n\n\tAuthor: K. Hesampour Date: %s\n", 
		java.time.LocalDate.now());
	}	// end main
} // end HesampourH5 
