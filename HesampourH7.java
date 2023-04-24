import java.io.*; 
import java.util.*; 

public class HesampourH7 {

    PrintStream prt = System.out;


    private class node {
        int count;
        int data1, data2; //data1 and data2 can be any valid java type
        node link0, link1, link2, parent; // parent link is needed for root/node split
// node constructor
        node(int x) {
            count = 1;
            data1 = x;
            link0 = link1 = link2 = parent = null;
        } // end node constructor
    } // end class node
    node root = null; // root of 2-3 Tree (initially empty)
//inorder traversal of 2-3 Tree
    private void inorder(){
        prt.printf("\n\tInorder Traversal of 2-3 tree:");
        Inorder(root);
        prt.printf("\n");

    }
}
