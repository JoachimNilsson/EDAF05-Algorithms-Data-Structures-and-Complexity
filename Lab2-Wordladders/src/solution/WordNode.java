package solution;
import java.util.*;

public class WordNode implements Comparable<WordNode> {
	private String word;
	private boolean visited;
	private WordNode pred;
	private List<WordNode> neighbours;
	
	
	public WordNode(String word){
		this.word=word;		
	}
	
	public String getWord(){
		return word;
	}
	
	public List<WordNode> getNeighbours(){
		return neighbours;
	}
	
	public void setNeighbours(List<WordNode> neigh){
		neighbours=neigh;
	}
	
	public boolean getVisited(){
		return visited;
	}
	
	public void setVisited(boolean visit){
		visited=visit;
	}
	
	public WordNode getPred(){
		return pred;
	}
	
	public void setPred(WordNode p){
		pred=p;
	}

	@Override
	public int compareTo(WordNode node) {
		return word.compareTo(node.word);
	}
	
	public boolean equals(WordNode node){
		return word.equals(node.word);
	}

}
