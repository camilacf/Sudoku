import java.util.Date;
import java.util.Random;


public abstract class Solver {
	protected int[][] initialState;
	protected int[] numberBank = new int[9];
	protected Sudoku temp;
	protected int errors;
	
	protected void setInitialState(Sudoku solution){
		initialState = solution.getInitialState();
	}
	
	protected void populateNumberBank(){
		Date date = new Date();
		Random generator = new Random(date.getTime());
		int element;
		
		for(int i = 0; i < 9; i++)
			numberBank[i] = 0;
		
		for(int i = 0; i < 9; i++){
			do{
				element = generator.nextInt(9) + 1;
			} while(existsInArray(numberBank, element));
			
			numberBank[i] = element;
		}

	}
	
	protected void initializeState(int[][] solution){
		populateNumberBank();
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				for(int k = 0; k < 9; k++)
					switch(solution[i][j]){
						case 0:
							if(!existsInArray(solution[i], numberBank[k])){
								solution[i][j] = numberBank[k];
							}
							break;
						
						default: 
							break;	
					}
	}
	
	protected boolean existsInArray(int[] array, int element){
		for(int i = 0; i < 9; i++){
			if(array[i] == element)
				return true;
		}
		return false;	
	}
	
	public abstract int[][] solve(Sudoku puzzle);
}
