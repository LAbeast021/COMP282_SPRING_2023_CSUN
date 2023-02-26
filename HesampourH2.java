// CSUN COMP 282 Spring 23, Hwk-2  HesampourH2.java
// External Sorting - using Normal Sort
// Author: G. Dastghaiby Fard

import java.util.*;
import java.io.*;

public class HesampourH2{
	
    //use prt for System.out to save typing	
	PrintStream prt = System.out;	
	
	// print file contents
	private void prtfile(String fn){
		//declare variables
		int i = 0, x;
		prt.printf("\n\t%s:", fn);
	  try{
	    Scanner inf = new Scanner(new File(fn)); 
	    while (inf.hasNext()) {
          //read an input from inf
		  x = inf.nextInt();
		  prt.printf("%4d ", x);
		  i++;
		  if(i % 16 == 0) 
			  prt.printf("\n\t%s:", fn);
	    } // endwhile
		prt.printf("(count=%d)", i);
		// close file
		inf.close();
      }catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
	} // end prtfile

	// print file contents
	private void prtfiles(String fn, int n){
		int i;
		prtfile(fn); // print input file
		String fname ;
		for (i = 1; i <= n; i++){
			fname = "F" + i + ".txt";
			prtfile(fname);
		}
	} // end prtfiles	

	// SortNormal, with arrays of size M
	private int SortNormal(String fn, int M) throws FileNotFoundException{//regular sort
		Scanner inf = new Scanner(new File(fn));
		int i = 0 , j  , k , p;
		while (inf.hasNext()) {
			int arr[] = new int [M+1];
			j = 1 ;
			for (k = 1 ; k <= M && inf.hasNext() ; k++)  {
				arr[k] = inf.nextInt(); 
			}
			i++;
			heapsort(arr, k-1);
			String fname = "F" + i +".txt"; 
			PrintWriter opf = new PrintWriter(new File(fname));
			for (p = 1 ; p <= k-1; p++){ 
				opf.printf("%5d", arr[p]); 
      			if (p %10 == 0) {
					opf.printf("\n"); 
				};	
			}
			opf.close();  
		};
		inf.close(); 
		return i;


	} // end SortNormal

	private void swap(int a[], int i, int j){
		int tmp = a[i];a[i] = a[j];a[j] = tmp;
	} // end swap

	private void heapsort(int a[], int n){
		int m;
		// convert to maxheap
		for(m= n/2; m >= 1; m--) 
			maxheapdn(a, m, n);	
		//end for
		
		for(m= n; m > 1; m--){
			swap(a, 1, m); 
			maxheapdn(a, 1, m-1);
		} //end for
	} // end heapsort
	
	// maxheapdn(a[], int m, int n)
	private void maxheapdn(int a[], int m, int n){
		int lc;
		while (( 2 * m) <= n){
			lc = m*2 ; 
			if ((m*2) + 1 < n+1 && a[(m*2) + 1] > a[lc]) {
				lc = (m*2) + 1 ;
			}
			if (a[m] < a[lc]) {
				swap (a,m, lc); 
				m = lc; 
			}
			else {
				break ;
			}
		};
	}; // end maxheapdn(int a[], int m, int n)
	
   // Normal sort process method using arrays size M
   private void process(String fn, int M) throws FileNotFoundException{ 
		int n;
		prt.print("\n\tExternal Sorting Java program that:\n\t"+
		"gets array size and inputfilename from \n\t"+
		"program argument and creates one or more sorted files." +
		"\n\t\tTo compile: javac HesampourH2.java" +
		"\n\t\tTo execute: java  HesampourH2 M inputfilename"+
		"\n\t\t   Example: java  HesampourH2 5 inputfilename");
		
		// Call SortNormal
		n = SortNormal(fn, M);

		//print input and output files
		prtfiles(fn, n);	    
   } //end process method
				
	public static void main(String[] args) throws Exception{
		//get no. of arguments
		int M, cnt = args.length; 
		String fn;
		if (cnt < 2){
		    System.out.printf("\n\tOOOPS Invalid No. of arguments!"+
			"\n\tTO Execute: java HesampourH2 5 input.txt");
			return;
		} // end if	
			
		// get largest array size
		M = Integer.parseInt(args[0]);
		
		// get input file name
		fn = args[1]; 
			
		System.out.printf("\n\tInput:%s, array size=%d", fn, M);
			
		// create an instance of HesampourH2 class
		HesampourH2 srt = new HesampourH2();
		
		// call process method
		srt.process(fn, M); 
			
		//Replace ????????? with your name in next line
		System.out.printf("\n\n\tAuthor: K. Hesampour Date: %s\n",
		java.time.LocalDate.now());
	}	// end main
} // end HesampourH2