
public class FiniteAutomata {
	public static char[] pattern;
	public static char[] text;
	public static int[][] table;
	public static int NumOfChar;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		text = new char[] {'a', 'a', 'b', 'a', 'a', 'c', 'a', 'a', 'd', 'a', 'a', 'b',
				'a', 'a', 'a', 'b', 'a', 'a'};
		pattern = new char [] {'a', 'a', 'b', 'a'};
		NumOfChar = 3;
		
		findPattern();
	}

	static int nextState(int pL, int a, int state){
		
		if (state < pL && a == pattern[state])
		{
			return state+1;
		}
		
		int nextState;
		int index;
		
		for (nextState = state; nextState > 0; nextState--){
			if (pattern[nextState-1] == a){
				for (index = 0; index < nextState-1; index++){
					if (pattern[index] != pattern[state-nextState+1+index]){
						break;
					}
				}
				if (index == nextState - 1){
					return nextState;
				}
			}
		}
		
		return 0;
	}
	
	static void buildTable(int pL){
		int state;
		int a;
		for (state = 0; state <= pL; state++){
			for (a = 0; a < NumOfChar; a++){
				table[state][a] = nextState(pL, a, state);
			}
		}
	}
	
	static void findPattern(){
		int pL = pattern.length;
		
		table = new int[pL+1][NumOfChar];
		
		buildTable(pL);
		
		
		for (int index = 0, state = 0; index < text.length; index++){
			state = table[state][text[index]];
			if (state == pL){
				System.out.printf("Pattern found at index ", index-pL+1);
			}
		}
	}
}
