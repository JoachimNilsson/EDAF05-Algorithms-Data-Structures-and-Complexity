package solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Gorilla {
	public static final int delta = -4;
	
	public static String backtrackSolution(Map<Character, Integer> lettersMap, String query1, String query2, int[][] optMatrix, int[][] cost){
		int j = query1.length();
		int k = query2.length();
		boolean cond = true;
		while(cond){
			if (j==0){
				String s = new String(new char[k]).replace("\0", "*");
				query1 = s + query1;
				return query1 + " " + query2;
			}else if(k==0){
				String s = new String(new char[j]).replace("\0", "*");
				query2 = s + query2;
				return query1 + " " + query2;
			}	

			int alpha = optMatrix[j-1][k-1] + cost[lettersMap.get(query1.charAt(j-1))][lettersMap.get(query2.charAt(k-1))];
			if(alpha < Math.max(delta + optMatrix[j][k-1], delta + optMatrix[j-1][k])){
				if(optMatrix[j][k-1] > optMatrix[j-1][k]){
					query1 = query1.substring(0, j) + "*" + query1.substring(j, query1.length());
					j+=1;
				}else{
					query2 = query2.substring(0, k) + "*" + query2.substring(k, query2.length());
					k+=1;
				}
			}
			j-=1;
			k-=1;
		}
		return "";
		
		
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String letters = String.join("",scan.nextLine().split(" "));
		Map<Character,Integer> letterMap = new HashMap<Character,Integer>();
		for (int i = 0; i < letters.length(); i++) {
			letterMap.put(letters.charAt(i),i);
		}
		int[][] cost = new int[letters.length()][letters.length()];
		for (int i = 0; i < letters.length(); i++) {
			for (int j = 0; j < letters.length(); j++) {
				cost[i][j] = scan.nextInt();
			}
		}
		int nQueries = scan.nextInt();
		scan.nextLine();

		String[] results = new String[nQueries];
		for (int i = 0; i < nQueries; i++) {
			String queryString = scan.nextLine();
			String[] query = queryString.split(" ");
			String query1 = query[0];
			String query2 = query[1];

			int[][] optMatrix = new int[query1.length() + 1][query2.length() + 1];
			
			for (int k = 0; k < query2.length()+ 1; k++) {
				for (int j = 0; j < query1.length() + 1; j++) {
					if(j==0){
						optMatrix[j][k] = delta*k;
					}else if(k==0){
						optMatrix[j][k] = delta*j;
					}else{
						optMatrix[j][k] = Math.max(cost[letterMap.get(query1.charAt(j-1))][letterMap.get(query2.charAt(k-1))] + optMatrix[j-1][k-1] , delta + Math.max(optMatrix[j-1][k],optMatrix[j][k-1]));
					}
				}
			}

			results[i] = backtrackSolution(letterMap, query1, query2, optMatrix, cost);
		}
		scan.close();
		for (String result : results) {
			System.out.println(result);
		}

	}

}