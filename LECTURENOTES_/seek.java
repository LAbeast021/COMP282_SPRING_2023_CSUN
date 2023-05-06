// To Compile at the command prompt: javac seek.java
// To Execute at the command prompt: java  seek
import java.io.*;
import java.util.*;
// CSUN Spring 23  COMP282 Random Access Files 
// seek.java program I/O in Random Access Files
// Author : Dr. Gh. Dastghaibyfard
// You have to provide name of a text file
public class seek {
	//use prt for System.out to save typing
    PrintStream prt = System.out;
	
	public static void main(String[] args) throws Exception{
		int cnt = args.length; // get no. of atguments
		String fn;
		if (cnt < 1){
		    System.out.printf("\n\tOOOPS Invalid No. of aguments!"+
			"\n\tTO Execute: java seek file.txt");
			return;
		} // end if	
		
		// get input file name
		fn = args[0];
		seek t = new seek();
		t.readwriteTest(fn);
		//MAKE SURE TO WRITE YOUR NAME IN NEXT LINE		
		System.out.printf("\n\tAuthor: K. Hesampour Date: " + java.time.LocalDate.now()); 
	} // end main
	
	private void readwriteTest(String fname) {
		RandomAccessFile inf;
		long offset; 
		int i, n, k = 30;
		byte[] b = new byte[k];
		String str1, str2;
		try {
		//open file for I/O
		inf = new RandomAccessFile(fname, "rw");

		// print file size	
		prt.printf("\n\tFile Size: %d", inf.length());

		// FOR MOVING THE CURSOR TO THE END OF THE FILE USE INF.LENGTH() FOR THE FRONT IS 0 
		
		offset = 10;  
		inf.seek(offset);
		prt.printf("\n\tLine at offset %4d: ", offset);
		str1 = inf.readLine();
		prt.printf("%s", str1);
		
		// print file Pointer	
		prt.printf("\n\tCurrent File Pointer: %d", inf.getFilePointer());
		
		offset = 8;  
		inf.seek(offset);
		
		str2 = "Testing Random Access here!   ";
		//length     123456789012345678901234567890
		prt.printf("\n\tWriting 30 bytes:\n(%s)\n\t at offset %d:",	str2, offset);
		inf.writeBytes(str2);
		
		// Read what ever has written
		inf.seek(offset);
		prt.printf("\n\tReading %d bytes at offset %d:", b.length, offset);
		inf.read(b); 
		for (i = 0; i < b.length; i++)
			prt.print((char) b[i]);

		offset = 100;  
		inf.seek(offset);
		prt.printf("\n\tReading 2 Bytes at offset %d:", offset);
		n = inf.readShort();//converts 2 bytes ascii to integer
		prt.printf("%d", n);

		offset = 40;  
		inf.seek(offset);
		prt.printf("\n\tReading 4 Bytes at offset %d:", offset);
		n = inf.readInt();//converts 4 bytes ascii to integer
		prt.printf("%d", n);
									
		inf.close();
		}catch(IOException e) {e.printStackTrace();}
	  } // end readwriteTest
} // end seek class