package solution;
import java.util.*;

public class BFSwords {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int nWords = scan.nextInt();
		int nQueries = scan.nextInt();
		
		Map<String,WordNode> wordMap=new HashMap<>();
		
		for (int i = 0; i < nWords; i++) {
			String word=scan.next();
			wordMap.put(word,new WordNode(word));
		}
		for (String w1 : wordMap.keySet()) {
			String[] letters=w1.substring(1).split("");
			List<WordNode> w1Neighbours=new ArrayList<WordNode>();
			for (String w2 : wordMap.keySet()) {
				if(!w1.equals(w2)){
					boolean ifNeighbour=true;
					String word=new String(w2);
					for (String letter : letters) {
						String wordTemp=new String(word);
						word=word.replaceFirst(letter, "");
						if (wordTemp.equals(word)){
							ifNeighbour=false;
							break;
						}
					}
						
					if(ifNeighbour){
						w1Neighbours.add(wordMap.get(w2));
					}	
				}
			}
			wordMap.get(w1).setNeighbours(w1Neighbours);
		}
		for (int i = 0; i < nQueries; i++) {
			String startWord=scan.next();
			String endWord=scan.next();
			BFS(wordMap,startWord,endWord);
		}
		scan.close();
		

	}
	
	
	public static void BFS(Map<String,WordNode> wordMap, String startWord, String endWord){
		if(startWord.equals(endWord)){
			System.out.println(0);
			return;
		}
		
		for (WordNode node : wordMap.values()) {
			node.setVisited(false);
		}
		wordMap.get(startWord).setVisited(true);
		
		Queue<WordNode> q = new LinkedList<>();
		q.add(wordMap.get(startWord));

		while(!q.isEmpty()){
			WordNode v=q.poll();
			
			for (WordNode vNeighbour : v.getNeighbours()) {
				if(!vNeighbour.getVisited()){
					vNeighbour.setVisited(true);
					q.add(vNeighbour);
					vNeighbour.setPred(v);
					
					if(vNeighbour.equals(wordMap.get(endWord))){
						int levels=1;
						
						while(!vNeighbour.getPred().getWord().equals(startWord)){
							levels+=1;
							vNeighbour=vNeighbour.getPred();
						}
						System.out.println(levels);
						return;
					}
				}
			}
		}
		System.out.println("Impossible");
	}
}
