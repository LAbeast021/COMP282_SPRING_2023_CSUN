
import java.util.*;
import java.io.*;
// CSUN COMP 282 Spring 23, HesampourH5 .java
// Program to merge k sorted files in one sorted file.
// End of each inputfile is 2147483647(Integer.MAX_VALUE). 
// Author: G. Dastghaiby Fard

public class HesampourH5 {
	//use prt for System.out to save typing
	PrintStream prt = System.out;
    int count = 0 ;
	
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
            if (count < i) {
                count = i;
            }
		} catch (IOException e){
			prt.printf("\nOoops! Read Exception: %s", e);}
	} // end prtfile

	private void mergeKway(int k) throws FileNotFoundException{
        int i , p , j , smallVal , smallIdx , countOfIntMax = 0  ;
        int[][] array = new int[k][count]; 
        String fname ;
        for (i = 1; i <= k; i++){
			fname = "F" + i + ".txt";
            Scanner inf = new Scanner(new File(fname));
            j = 0 ;
            while (inf.hasNextInt()) {
                array[i-1][j++] = inf.nextInt();
            }
            array[i-1][j] = Integer.MAX_VALUE; 
		}

        fname = "F" + i + ".txt";
        PrintWriter otf = new PrintWriter(new File(fname));

        smallVal = array[0][0];
        smallIdx = 0;
        while (countOfIntMax != k) {
            for (i = 0 ; i < k ;i++) {
                if (array[i][0] < smallVal && array[i][0] != Integer.MAX_VALUE) {
                    smallVal = array[i][0];
                    smallIdx = i;
                }
                if (array[i][0] == Integer.MAX_VALUE) {
                    countOfIntMax += 1 ;
                }
    
            }
            otf.printf(" %3d " , smallVal);
            for (p = 0 ; p < count ; p++){
                array[smallIdx][p] = array[smallIdx][p+1] ;
            }

        }
        

	} 

	public static void main(String[] args) throws Exception{
		int cnt = args.length , k , i; // get no. of atguments
        String fname ;

		if (cnt < 1){
		    System.out.printf("\n\tOOOPS Invalid No. of aguments!"+
			"\n\tTO Execute: java HesampourH5 ");
			return;
		} // end if	
		// get input file name
		k = Integer.parseInt(args[0]); 

		HesampourH5  srt = new HesampourH5() ;

        for (i = 1; i <= k; i++){
			fname = "F" + i + ".txt";
			srt.prtfile(fname);
		}

		// //Call mergeKway
		srt.mergeKway(k);
		// //Print output file
		// srt.prtfile(fn3);
		
		
		//Replace ????????? with your name in next line
		System.out.printf("\n\n\tAuthor: K. Hesampour Date: %s\n", 
		java.time.LocalDate.now());
	}	// end main
} // end HesampourH5 
