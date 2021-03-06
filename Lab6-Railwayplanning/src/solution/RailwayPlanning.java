package solution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class RailwayPlanning {

	public static class Edge {
		
		private int capacity;
		private int flow;
		public Edge(int capacity,int flow){

			this.capacity=capacity;
			this.flow=flow;
			
		}
		public int getFlow(){
			return flow;
		}
		public int getCapacity(){
			return capacity;
		}

		public void setFlow(int f){
			flow=f;
		}
		
	}

	
	public static class Node implements Comparable<Node>{
		private int idx;
		private boolean visited;
		private Node pred;
		private Map<Node, Edge> neighbours;
		
		
		public Node(int idx){
			this.idx=idx;
			neighbours = new HashMap<Node, Edge>(); 
		}
		
		public int getIndex(){
			return idx;
		}
		
		public Map<Node,Edge> getNeighbours(){
			return neighbours;
		}
		
		public boolean getVisited(){
			return visited;
		}
		
		public void setVisited(boolean visit){
			visited=visit;
		}
		
		public Node getPred(){
			return pred;
		}
		
		public void setPred(Node p){
			pred=p;
		}

		@Override
		public int compareTo(Node node) {
			return idx - node.idx;
		}
		
		public boolean equals(Node node){
			if(idx==node.idx){
				return true;
			}else{
				return false;
			}
		}
	}

	public static int BFS(Node[] nodes, int start, int end){
		if(end==start){
			return 0;
		}
		
		for (Node node :nodes) {
			node.setVisited(false);
		}
		nodes[start].setVisited(true);
		
		Queue<Node> q = new LinkedList<>();
		q.add(nodes[start]);

		while(!q.isEmpty()){
			Node v=q.poll();
			
			for (Node vNeighbour : v.getNeighbours().keySet() ) {
				Edge e = v.getNeighbours().get(vNeighbour);
				if(!vNeighbour.getVisited() && e.getCapacity()>e.getFlow()){
					vNeighbour.setVisited(true);
					q.add(vNeighbour);
					vNeighbour.setPred(v);
					
					if(vNeighbour.getIndex()==end){
						int delta=Integer.MAX_VALUE;
						do{
							Edge edge=vNeighbour.getPred().getNeighbours().get(vNeighbour);
							if (edge.getCapacity()-edge.getFlow()<delta){
								delta=edge.getCapacity()-edge.getFlow();
							}
							vNeighbour=vNeighbour.getPred();
						}while(vNeighbour.getIndex()!=start);
						
						vNeighbour = nodes[end];
						do{
							Edge e1 = vNeighbour.getPred().getNeighbours().get(vNeighbour);
							e1.setFlow(e1.getFlow()+delta);
							vNeighbour=vNeighbour.getPred();
						}while(vNeighbour.getIndex()!=start);
						
						return delta;
					}
				}
			}
		}
		return 0;
	}
		
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int cMin = scan.nextInt();
		int p = scan.nextInt();
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}
		int[][] routes = new int[m][3];
		for (int i = 0; i < m; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();
			int c = scan.nextInt();
			routes[i][0]=u;
			routes[i][1]=v;
			routes[i][2]=c;
			Edge e = new Edge(c,0);
			nodes[u].getNeighbours().put(nodes[v], e);
			nodes[v].getNeighbours().put(nodes[u], e);
			
		}
		
		int[] plan = new int[p];
		for (int i = 0; i < p; i++) {
			plan[i] = scan.nextInt();
			int[] route = routes[plan[i]];
			nodes[route[0]].getNeighbours().remove(nodes[route[1]]);
			nodes[route[1]].getNeighbours().remove(nodes[route[0]]);
			
		}

		scan.close();

		
		int flow = 0;
		for (int j = plan.length-1; j >= 0; j--) {
			int[] route = routes[plan[j]];
			nodes[route[0]].getNeighbours().put(nodes[route[1]], new Edge(route[2],0));
			nodes[route[1]].getNeighbours().put(nodes[route[0]], new Edge(route[2],0));
			
			int delta;
			do {
				delta=BFS(nodes,0,n-1);
				flow+=delta;
			}while(delta!=0);
			
			if (flow>=cMin){
				System.out.println(j + " " + flow);
				return;
			}
		}	
	}
}
