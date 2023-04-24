
// CSUN Spring 23 COMP282       HesampourH8.java

import java.io.*; 
import java.util.*; 

public class HesamH8 {

    // use prt for System.out to save typing
    PrintStream prt = System.out;

     static int[] d ;
     static int[][] M;
     static int[][] F;




    private void showorder(int i, int j){
        int k;
        if ( i == j){
            System.out.printf("A%d ", i);
        }
        else{
            k = F[i][j];
            prt.print("(");
            showorder(i, k);
            prt.print ("*");
            showorder(k+1, j);
            prt.print(")");
        } // end else
    }

    private int dpMCM (int n){
        M = new int [n+1][n+1] ;
        F = new int [n+1][n+1] ;
        int diag , i , j ;
        // for (diag = n ; ;diag += 1) {
        //     for (i = 1 ; i <= n-diag ; i ++ ) {
        //         j = i + diag ;
        //     }

        // }
        for ( i = 1; i <= n; i++) {
                M[i][i] = 0;
            }
        for (int l = 2; l <= n; l++) {
            for ( i = 1; i <= n - l + 1; i++) {
                 j = i + l - 1;
                M[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int tmp = M[i][k] + M[k + 1][j] + d[i - 1] * d[k] * d[j];
                    if (tmp < M[i][j]) {
                        M[i][j] = tmp;
                        F[i][j] = k;
                    }
                }
            }
        }
            return M[1][n];
    }

    private int recMCM (int i, int j){
        if (i == j) return 0;
        int min = Integer.MAX_VALUE , k , m;
        for ( k = i ; k < j ; k++) {
            m = recMCM(i, k) + recMCM(k+1, j) + (d[i-1] * d[i-1] * d[k] * d[j]) ;
            if (m < min) {
                min = m ;
            }
        }
        return min ;
    }

    private void process(String fname){

        // local variables
		int j, numberOfMatrices = 0, x , min1 , min2; 
        try{  
			// open input file
			Scanner inf = new Scanner(new File(fname)); 
						
			//read no. of elements to insert
			numberOfMatrices = inf.nextInt();	
            d = new int[numberOfMatrices + 1];				
			prt.printf("\n\n\tno. of matrices: %d , their dimensions  :\n\t", numberOfMatrices);
			for(j = 0; j <= numberOfMatrices; j++){
				x = inf.nextInt();   // read x
				prt.printf("%2d, ", x);
                d[j] = x ;
			} // end for
			
			// close input file 		
			inf.close();   
		} catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
        min1 = recMCM(1, numberOfMatrices); 
        prt.printf(" \n\n\t %2d, \n ", min1);
        min2 = dpMCM(numberOfMatrices);
    }
   

    public static void main(String args[]) throws Exception{
		// declare variables
		int cnt = args.length; // get no. of arguments
		String inf;
	  
		if (cnt < 1){
		    System.out.printf("\n\tOOOPS Invalid  No. of aguments!"+
			"\n\tTO Execute: java HesamH8 inputfilename");
			return;
		} // end if

		// get input file name
		inf = args[0]; 
						
		System.out.printf("\n\tInput filename: %s", inf);
		
		// create an instance of BST class
		HesamH8 t = new HesamH8();

		// call process method
		t.process(inf); 

		//MAKE SURE TO WRITE YOUR NAME IN NEXT LINE		
		System.out.printf("\n\tAuthor: K. Hesampour Date: " +
		java.time.LocalDate.now()); 
   } // end main
}
