// CSUN Spring 23 COMP282       HesampourH6.java
// JAVA implementation of Binary Search Tree (BST) 

import java.io.*; 
import java.util.*; 

public class HesampourH6{  
    //root is a pointer to root of the HesampourH6	
    node root = null; 
   
   // use prt for System.out to save typing
    PrintStream prt = System.out;
	
    // class node
    private class node {
		String name ;
		int phone;
		node ll, rl;  //left and right link
		// node  constructor
		node(String nam , int ph) {
			name  = nam ;
			phone = ph;
			rl = ll = null;
		}  //end node constructor
    }  // end class node

	//insert x in the BST
	private void insert(String name ,int x){
		if (root == null){
			//Allocate space and store x
			root = new node(name , x);
			return;
		}// end if 
		// BST is not empty
		insert(name , x, root);
	} 	// end insert(x)

	//insert x in the BST from node t
	private void insert(String name ,int x, node t){
		node tmp, parent = t;
		
		// Allocate space and store x 
		tmp = new node(name , x);
		
		// find parent node for x	  
		while (t != null) { 
			parent = t;   // save t
			if (name.compareTo(t.name) <= 0) 
				t = t.ll;
			else	 
				t = t.rl;
		} // end while
		
		//x should be inserted either to left or right of node parent 
		if (name.compareTo(parent.name) <= 0) 
			parent.ll = tmp;
		else 
			parent.rl = tmp;
		// end if
	}  // end insert(x, t)

	// search for x in the BST 
	private int search(String name){
		prt.printf("\n\tSearch for %s in the BST: ", name); 
		if (root == null){ //x not found 
			prt.printf(" Sorry BST is Empty!");
			return 0;
		} // end if
		return search( name ,root);    
	} // end search (int x)
   
	// search for x in the BST from node t 
	// if found return 1
	private int search(String name , node t){
		//Search the tree  
		while (t != null) { 
			if (name.compareTo(t.name) == 0) { //x not found 
				prt.printf("Found, Phone : %d" , t.phone);
				return t.phone;
			} // end if
			if (name.compareTo(t.name) < 0) 
				t = t.ll; // search left subtree
			else	 
				t = t.rl; // search right subtree
		} // end while
		prt.printf("NOT found");
		return 0;    
	} // end search (x, t)

   

	//preorder traversal of BST
	private void preorder(){
		if (root == null){//BST is empty
			prt.printf(" Tree is Empty!");
			return;
		} // end if		
		// BST is not empty		
		preorder(root);
	}// end preorder()

	//preorder traversal from node t
	private void preorder(node t){
		if (t == null) return;		
		// BST is not empty
		//print content of root of subtree
		prt.printf (" ( %5s : %4d ) ",t.name , t.phone);  
		preorder (t.ll); //preorder left subtree
		preorder (t.rl); //preorder right subtree
	} //end preorder(t)

	//inorder traversal of BST
	private void inorder(){
		if (root == null){//BST is empty
			prt.printf(" Tree is Empty!");
			return;
		} // end if		
		// BST is not empty
		inorder(root);
	}// end inorder()

	//inorder traversal from node t
	private void inorder(node t){
		if (t == null) return;		
		// BST is not empty		
		inorder (t.ll); //inorder left subtree
		//print contenet of root of subtree
		prt.printf (" ( %5s : %4d )",t.name , t.phone); 
		inorder (t.rl); //inorder right subtree
	} //end inorder(t)

	//postorder traversal of BST
	private void postorder(){
		if (root == null){//BST is empty
			prt.printf(" Tree is Empty!");
			return;
		} // end if		
		// BST is not empty		
		postorder(root);
	}// end postorder()

	//postorder traversal from node t
	private void postorder(node t){
		if (t == null) return; //BST is empty
		// BST is not empty				
		postorder (t.ll); //postorder left subtree
		postorder (t.rl); //postorder right subtree
		//print contenet of root of subtree
		prt.printf (" ( %5s : %4d )",t.name , t.phone); 
	} //end postorder(t)

	// process method for BST to insert and search 
	private void process(String fname){
		// local variables
		int j, nIns, nSrch, k, x; 
		String name ;

		prt.printf("\n\tBST program gets input file name from program " + "argument, then reads:"+
		"\n\tinteger No. of elements to insert in BST "+
		"followed by elements to insert"+
		"\n\tNo. of elements to search followed by element to search.\n\tFinally prints BST level by level"+
  		"\n\t\tTo compile: javac HesampourH6.java" +
		"\n\t\tTo execute: java  HesampourH6 inputfilename"); 	  
				  
		try{  
			// open input file
			Scanner inf = new Scanner(new File(fname)); 
						
			//read no. of elements to insert
			nIns = inf.nextInt();						
			prt.printf("\n\n\tInsert %d elements in the BST:\n\t", nIns);
			for(j = 1; j <= nIns; j++){
				name = inf.next() ;
				x = inf.nextInt();   // read x
				prt.printf("(%s:%4d), ",name , x);
				insert(name ,x); //insert x in the BST
			} // end for
			
			//print 3 traversal of BST
			prt.printf("\n\t3 traversal of BST are as follow :");
			prt.printf("\n\tPreorder ->");
			preorder();
					
			prt.printf("\n\tInorder ->");
			inorder();
					
			prt.printf("\n\tPostorder ->");
			postorder();
			
			//read no. of elements to search in BST
			nSrch = inf.nextInt(); 
			prt.printf("\n\n\tSearch for %d elements in the BST.", nSrch);
			for(j = 1; j <= nSrch; j++){
				name = inf.next(); // read x to search
				k = search(name);  // Search for x
			}// end for	

			// close input file 		
			inf.close();   
		}catch (Exception e){prt.printf("\n\tRead Error! %s", e);}
	} // end process(fname)
  
  //  main method
	public static void main(String args[]) throws Exception{
		// declare variables
		int cnt = args.length; // get no. of arguments
		String inf;
	  
		if (cnt < 1){
		    System.out.printf("\n\tOOOPS Invalid  No. of aguments!"+
			"\n\tTO Execute: java HesampourH6 inputfilename");
			return;
		} // end if

		// get input file name
		inf = args[0]; 
						
		System.out.printf("\n\tInput filename: %s", inf);
		
		// create an instance of BST class
		HesampourH6 t = new HesampourH6();

		// call process method
		t.process(inf); 

		//MAKE SURE TO WRITE YOUR NAME IN NEXT LINE		
		System.out.printf("\n\tAuthor: K. Hesampour Date: " +
		java.time.LocalDate.now()); 
   } // end main
} // end class BST