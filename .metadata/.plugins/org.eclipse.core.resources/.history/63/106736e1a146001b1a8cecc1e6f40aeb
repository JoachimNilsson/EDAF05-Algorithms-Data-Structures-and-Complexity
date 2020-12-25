package solution1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class GSMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		int[][] woman = new int[n][n];
		int[][] man = new int[n][n];
		for (int i = 0; i < 2 * n; i++) {
			int[] numbs=new int[n+1];
			for(int j=0;j<n+1;j++){
				numbs[j]=scan.nextInt();
			}

			int idx = numbs[0];
			if (woman[idx - 1][0] == 0) {
				for (int j = 0; j < n; j++) {
					woman[idx - 1][numbs[j + 1]-1] = j + 1;
				}
			} else {
				for (int j = 0; j < n; j++) {
					man[idx - 1][j] = numbs[j + 1];
				}
			}
		}
		Queue<Integer> p = new LinkedList<>();
		int[] nbrOfProposed=new int[n];
		int[] partnerOfWoman=new int[n];
		for (int i = 1; i < n+1; i++) {
			p.add(i);
		}
		while(!p.isEmpty()) {
			int manIdx=p.poll();
			int womanIdx=man[manIdx-1][nbrOfProposed[manIdx-1]];
			nbrOfProposed[manIdx-1]+=1;
			int partner=partnerOfWoman[womanIdx-1];
			if(partner==0) {
				partnerOfWoman[womanIdx-1]=manIdx;
			}
			else if(woman[womanIdx-1][manIdx-1]<woman[womanIdx-1][partner-1]) {
				p.add(partner);
				partnerOfWoman[womanIdx-1]=manIdx;
			}
			else {
				p.add(manIdx);
			}
		}
		for (int i : partnerOfWoman) {
			System.out.println(i);
		}
		scan.close();
	}
}
