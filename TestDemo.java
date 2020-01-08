import java.util.Scanner;

public class TestDemo {
	public static void main(String[] args) {
		DataManager dataMgr = DataManager.GetInstance();
		
		Scanner sc = new Scanner(System.in);
		
		int count = 0;
		while (count != 5) {
			String word = dataMgr.GetWordRandomly();
			System.out.printf("word : %s\n", word);
			System.out.println("Please input sentence : ");
			String userAns = sc.next();
			if (dataMgr.CheckAnswer(word, userAns)) {
				System.out.println("----------------- Correct ------------------");
			} else {
				System.out.println("xxxxxxxxxxxxxxxxx Error xxxxxxxxxxxxxxxxxx");
			}
			count++;
		}
	}
}
