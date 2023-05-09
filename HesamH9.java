import java.io.*;
import java.util.*;
// CSUN Spring 23  COMP282 Hwk-9 Graph Algorithms:
//     Prim’s minimum spanning tree (MST),  
//     Dijkstra’s single source shortest path, 
//     depth first search(DFS), and breadth first search(BFS). 
// Author: G. Dastghaiby Fard
 public class HesamH9{
	//use prt for System.out to save typing
    PrintStream prt = System.out;
	
	int g[][], visit[], nodes, edges, max = 9999;
	
	//Print Graph Matrix	
	private void prtgraph(){
		int i, j;
		// Print weight matrix
		prt.printf("\n\tWeight Matrix is as follows\n\t");
		for (i = 1; i <= nodes; i++){
			for (j = 1; j <= nodes; j++)
				if(g[i][j] == max)
					prt.printf(" --- ");
				else
					prt.printf("%5d", g[i][j]);
			prt.printf("\n\t");
		}
	} //end prtgraph
	
	//DFS from nodex
	private void dfs(int nodex){
		//COMPLETE THIS METHOD (50 POINTS)
		visit[nodex] = 1; 
		prt.printf("%d  " , nodex);
		for(int i = 1; i <= nodes; i++) {
			if(g[nodex][i] != max && visit[i] == 0) {
				dfs(i);
			}
		}
	} // end DFS from nodex

	// DFS from nodex
	private void bfs(int nodex){
		//COMPLETE THIS METHOD (50 POINTS)
	} // end DFS from nodex

	// Prim's MST algorithm 
	private void prim(){
		//COMPLETE THIS METHOD (50 POINTS)
	}// end prim's algorithm
	
	// Dijkstra Shortest Path algorithm from nodex
	private void dijkstra(int nodex){//  shortes
		int i , j;
		//Allocate space for dist[]
		int[] dist = new int[nodes+1]; 
		// initialize dist[]
		for (i = 1; i <= nodes; i++){
		  dist[i] = g[nodex][i];
		}
		int[] visited = new int[nodes + 1];
    	visited[nodex] = 1;

		for (i = 2; i <= nodes; i++) {
			int min_dist = max;
			int v = -1;
			for (j = 1; j <= nodes; j++) {
				if (visited[j] == 0 && dist[j] < min_dist) {
					min_dist = dist[j];
					v = j;
				}
			}
			if (v == -1) {
				break;
			}
			visited[v] = 1;
			for (j = 1; j <= nodes; j++) {
				if (g[v][j] < max && visited[j] == 0 && dist[v] + g[v][j] < dist[j]) {
					dist[j] = dist[v] + g[v][j];
				}
			}
		}
	
		prt.printf("\n\tDijkstra Shortest distance from node %d:", nodex);
		for (i = 1; i <= nodes; i++) 
			prt.printf("%5d ", dist[i]);		
	}// end dijkstra
		
	private void process(String fn) { 
		int i, j, k, wt, n, nodex;	  
		prt.printf("\n\t\tOptional HWK-9 Graph Algorithms:"+
		"\n\tPrim's minimum spanning tree (MST),"+  
		"\n\tDijkstra's single source shortest path,"+ 
		"\n\tdepth first search(DFS), and breadth first search(BFS).\n");

		try{ // read input 
			Scanner inf = new Scanner(new File(fn)); 
			//read no. of nodes
			nodes = inf.nextInt();
			//read no. of edges
			edges = inf.nextInt();
			//Allocate space for graph[][] and visit[]
			g = new int[nodes+1][nodes+1];
			visit = new int[nodes+1];

			// initialize g[][] matrix
			for (i = 1; i <= nodes; i++)
				for (j = 1; j <= nodes; j++)
					g[i][j]= max;
				
			// read graph data			
			for (k = 1; k <= edges; k++){
				i  = inf.nextInt();			
				j  = inf.nextInt();			
				wt = inf.nextInt();
				g[i][j] = g[j][i] = wt;
			}// end for
			
			// print Graph weight matrix g[][]		     
			prtgraph();
			
			//Call Prim's MST and print its edges			
			prt.printf("\n\tPrim's MST edges are:\n\t");
			prim();     
			//read no. of searches
			n = inf.nextInt();
			for (k = 1; k <= n; k++){
				//Read starting node
				nodex = inf.nextInt();
				
		        //Call Dijkstra shortest path from nodex				
				dijkstra(nodex); 
				
				//DFS Graph Traversal from nodex
				for (j = 1; j <= nodes; j++) 
					visit[j] = 0;
				prt.printf("\n\tDFS from (%d):", nodex);
				dfs(nodex);//non recursive

				//BFS Graph Traversal from nodex
				prt.printf("\n\tBFS from (%d):", nodex);
				bfs(nodex);
			}// end for

			inf.close();
		}catch(Exception e){prt.printf("\nI/O Error %s", e );}
	}  // end process method

	// main program
	public static void main(String[] args) throws Exception{
		int cnt = args.length; // get no. of atguments
		String fn;
		if (cnt < 1){
		    System.out.printf("\n\tOOOPS Invalid No. of arguments!" +
			"\n\tTO Execute: java xxxxxH9 inputfilename");
			return;
		} // end if	
		
		// get input file name
		fn = args[0];

		// create an instance of xxxxxH9 class
		HesamH9 g = new HesamH9();
		
		// call process method
		g.process(fn); 
			
		//MAKE SURE TO WRITE YOUR NAME IN NEXT LINE		
		System.out.printf("\n\tAuthor: K. Hesampour Date: " + java.time.LocalDate.now()); 
	}	// end main
} // end xxxxxH9 OPTIONAL