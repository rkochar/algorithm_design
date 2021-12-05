package Greedy.string;

public class BracketMatching {

	public int minAddToMakeValid(String s) {
		int open = 0, close = 0, l = s.length();
		for (int i = 0; i < l; i ++) {
			char check = s.charAt(i);
			if (check == '(') {
				open ++;
			} else {
				if (open > 0) {
					open --;
				} else {
					close ++;
				}
			}
		}

	return open + close;
	}
}
