// CSUN COMP 282 Spring 23, Hwk-2  HesampourH2.java
// External Sorting - using Normal Sort
// Author: K Hesampour

import java.util.*;
import java.io.*;

public class HesampourH3{
	
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

	// SortRepSel, with arrays of size M
	private int SortRepSel(String fn, int M) throws FileNotFoundException{//regular sort
		Scanner inf = new Scanner(new File(fn));
		int i = 1 , arr [] = new int[M+1] , j , heapSize , m , k ;
		for (j = 1;  j <=  M && inf.hasNext() ; j++)  {
			arr [j]  = inf.nextInt (); 
		}
		heapSize = --j; 
		while (heapSize > 0) { 
			String fname = "F" + i +".txt";
			PrintWriter opf = new PrintWriter(new File(fname));
			heapsort(arr, heapSize);
			int lastElement = Integer.MIN_VALUE; 
			int inheap = 0; 
			while ( heapSize > 0) {
				if (arr [1] <  lastElement) {
					swap (arr, 1, heapSize);
					heapSize -= 1 ; 
					inheap ++ ;
				}
				else {
					opf.printf("%5d", arr[1]);
					lastElement = arr[1] ; 
					if (!inf.hasNext()){ 
						for ( k = 1; k < heapSize+inheap; k ++) {
							arr[k] = arr[k+1]; 
						}
						heapSize --; 
					}
					else {
						arr[1] = inf.nextInt(); 
					}
				}
				heapsort(arr, heapSize);
			} 
			opf.close(); 
			if (inheap == 0) break;
			heapSize = inheap;
			i++ ; 
		 }
		inf.close(); 
		return i;


	} // end SortRepSel

	private void swap(int a[], int i, int j){
		int tmp = a[i];a[i] = a[j];a[j] = tmp;
	} // end swap

	private void heapsort(int a[], int n){
		int m;
		// convert to maxheap
		for(m= n/2; m >= 1; m--) 
			minheapdn(a, m, n);	
		//end for
		
		for(m= n; m > 1; m--){
			swap(a, 1, m); 
			minheapdn(a, 1, m-1);
		} //end for
	} // end heapsort
	
	// minheapdn(a[], int m, int n)
	private void minheapdn(int a[], int m, int n){
		int lc;
		while (( 2 * m) <= n){
			lc = m*2 ; 
			if ((m*2) + 1 < n+1 && a[(m*2) + 1] < a[lc]) {
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
	}; // end minheapdn(int a[], int m, int n)
	
   // Normal sort process method using arrays size M
   private void process(String fn, int M) throws FileNotFoundException{ 
		int n;
		prt.print("\n\tExternal Sorting Java program that:\n\t"+
		"gets array size and inputfilename from \n\t"+
		"program argument and creates one or more sorted files." +
		"\n\t\tTo compile: javac HesampourH2.java" +
		"\n\t\tTo execute: java  HesampourH2 M inputfilename"+
		"\n\t\t   Example: java  HesampourH2 5 inputfilename");
		
		// Call SortRepSel
		n = SortRepSel(fn, M);

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
		HesampourH3 srt = new HesampourH3();
		
		// call process method
		srt.process(fn, M); 
			
		//Replace ????????? with your name in next line
		System.out.printf("\n\n\tAuthor: K. Hesampour Date: %s\n",
		java.time.LocalDate.now());
	}	// end main
} // end HesampourH2