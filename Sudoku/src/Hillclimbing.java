import java.util.Date;
import java.util.Random;


public class Hillclimbing extends Solver{
	private int[][] currentState = new int[9][9];
	private int[][] solutionState;
	private int iteration = 0; 
	protected Sudoku temp;
	
	public Hillclimbing(){
		
	}
	
	
	public int[][] solve(Sudoku puzzle){
		Date date = new Date();
		Random generator = new Random(date.getTime());
		int element;
		temp = new Sudoku(puzzle.getInitialState());
		setInitialState(temp);
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				currentState[i][j] = initialState[i][j];
			}
			for(int j = 0; j < 9; j++){
				if (currentState[i][j] == 0){
					element = generator.nextInt(9) + 1;

					do{
						element = generator.nextInt(9) + 1;
					} while(existsInArray(currentState[i], element));
					
					currentState[i][j] = element;

				}
			}
		}
		int[][] iniState = initialState;
		temp = new Sudoku(currentState);
		
		iteration = 0;
		int iquals = 100;

		while(iquals!=0){

				int x, k=0, l=0;
				for(int r=0;r<9;r++){
				for(int j = 0; j < 9; j++){		
					if (iniState[r][j] == 0){

						for (int i = 0; i < 9; i++){
						if (iniState[r][i] == 0){
							currentState = temp.getCurrentState();
							x = currentState[r][j];
							currentState[r][j] = currentState[r][i];
							currentState[r][i] = x;
							
							temp = new Sudoku(currentState);

							if(temp.verify() < iquals){
								iquals = temp.verify();
								k=i; l=j;


							}
							currentState[r][i] = currentState[r][j];
							currentState[r][j] = x;
							
						}
					}
					
				}
					
			}
				x = currentState[r][l];
				currentState[r][l] = currentState[r][k];
				currentState[r][k] = x;
				temp = new Sudoku(currentState);
				iteration++;
				}
				
			if (iteration>200){ iquals=0;

			}
		}

		solutionState = temp.getCurrentState();

		return solutionState;
	}
	public static void main(String[] args) {
		Hillclimbing Hc = new Hillclimbing();
		
		int[][] unsolvedPuzzle = 
			{{0, 0, 0, 7, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 4, 3, 0, 2, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 6},
			{0, 0, 0, 5, 0, 9, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 4, 1, 8},
			{0, 0, 0, 0, 8, 1, 0, 0, 0},
			{0, 0, 2, 0, 0, 0, 0, 5, 0},
			{0, 4, 0, 0, 0, 0, 3, 0, 0}};
		
		Sudoku unsolvedSudoku = new Sudoku(unsolvedPuzzle);
		int[][] result = new int[9][9];; 
		result = Hc.solve(unsolvedSudoku);

		for(int i=0; i<9;i++){
			for(int j=0; j<9; j++){
				System.out.print(result[i][j]);
			}
			System.out.println("");
		}
		
	}
	
}
