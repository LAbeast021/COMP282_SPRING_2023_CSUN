package HW1;
// CSUN SPRING 23   COMP 282 
 // HesampourH1.java generate n random numbers from a to b and print the k numbers per line  
 // Prepared by: Kiarash Hesampour, Feb 4, 23   
  import java.util.*; 
  import java.io.*;   
  public class HesampourH1  { 

    PrintStream prt = System.out; 

    private void generate(int n, int a, int b, int k) {
        int i , rand ;
        prt.printf("\n");
        for(i = 1; i <= n; i++) {
             rand = (int) (Math.random() * (b - a)) + a;
             prt.printf("\t %4d, " , rand);
             if(i % k == 0){
                 prt.printf("\n") ;
             }
                
        }
    }
    public static void main(String args[]) throws Exception{ 
        // declare local variables 
        int n, a, b, k ;
        int cnt = args.length; // get no. of atguments 
      
        //create an instance of a class 
        HesampourH1 H1 = new HesampourH1();     
      
        // get no. of  arguments     
        cnt = args.length;  
                 
        if (cnt < 4) {
            System.out.printf("\n\n\tOOPS Invalid No. of aguments! EXIT.\n");
            return;
        }

        n = Integer.parseInt(args[0]);
        a = Integer.parseInt(args[1]);
        b = Integer.parseInt(args[2]); 
        k = Integer.parseInt(args[3]);

        System.out.printf("\n\tINPUT: n = %4d, a = %4d, b = %4d, k = %4d", n, a, b, k);
        if (a >= b){ // m should be < n
            System.out.printf("\n\tSorry, a should be < b.\n");
            return;
            } // end if

        H1.generate(n, a, b, k);
         
        //Pleas replace G. Dastghaibyfard with your name in next line     
        System.out.printf("\n\n\tAuthor: K. Hesampour Date: %s\n", 
        java.time.LocalDate.now());  
      } // end main 
    }