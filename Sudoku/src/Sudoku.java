
public class Sudoku {
	
	private int[][] initialState = new int[9][9];
	
	private int[][] currentState = new int[9][9];
	
	public Sudoku(){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				initialState[i][j] = currentState[i][j] = 0;
	}
	
	public Sudoku(int[][] state){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				initialState[i][j] = currentState[i][j] = state[i][j];
	}
	
	public void printCurrentState(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(currentState[i][j]);
			}
			System.out.println("");
		}
	}
	
	public void printInitialState(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(initialState[i][j]);
			}
			System.out.println("");
		}
	}
	
	private int[][] copyState(int[][] stateToBeCopied){
		int[][] tempState = new int[9][9];
		
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				tempState[i][j] = stateToBeCopied[i][j];
		
		return tempState;
	}
	
	public int[][] getCurrentState(){
		return copyState(currentState);
	}
	
	public int[][] getInitialState(){
		return copyState(initialState);
	}
	
	public int setCurrentState(int[][] state){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				currentState[i][j] = state[i][j];

		return 0;
	}
	
	public int verify(){
		int incorrect = 0; 
		int match = 0;  
		
		for(int col = 0; col < 9; col++){
			for(int row = 0; row < 9; row++){
				if(initialState[row][col] != 0)
					if(initialState[row][col] != currentState[row][col])
						incorrect++;
			}
		}

		for(int col = 0; col < 9; col++){
			for(int row = 0;row < 9; row++){
				if(currentState[col][row] == 0)
					incorrect++;
			}
		}

		/*for(int col = 0; col < 9; col++){
			for(int row = 0; row < 9; row++){
				for(int i = 0; i < 9; i++){
					if(currentState[col][row] == currentState[col][i])
						match++;
				}
				
				if(match > 1){
					incorrect += --match;
					match = 0;
				} else{
					match = 0;
				}
			}
		}*/

		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 9; col++){
				for(int i = 0; i < 9; i++){
					if(currentState[row][col] == currentState[i][col])
						if(row<i) match++;
				}
				
			}
		}

		int[][] tempOne = new int[3][3];
		int[][] tempTwo = new int[3][3];
		int[][] tempThree = new int[3][3];
		int[][] tempFour = new int[3][3];
		int[][] tempFive = new int[3][3];
		int[][] tempSix = new int[3][3];
		int[][] tempSeven = new int[3][3];
		int[][] tempEight = new int[3][3];
		int[][] tempNine = new int[3][3];
		
		for(int row = 0; row < 9; row++){
			switch(row){
				case 0: case 1: case 2:
					for(int col = 0; col < 3; col++){
						tempOne[row][col] = currentState[row][col];	
					}
					
					for(int col = 3; col < 6; col++){
						tempTwo[row][col - 3] = currentState[row][col];							
					}
					
					for(int col = 6; col < 9; col++){
						tempThree[row][col - 6] = currentState[row][col];	
					}
					break;
				case 3: case 4: case 5:
					for(int col = 0; col < 3; col++){
						tempFour[row - 3][col] = currentState[row][col];	
					}
					
					for(int col = 3; col < 6; col++){
						tempFive[row - 3][col - 3] = currentState[row][col];	
					}
					
					for(int col = 6; col < 9; col++){
						tempSix[row - 3][col - 6] = currentState[row][col];	
					}
					break;
				case 6: case 7: case 8:
					for(int col = 0; col < 3; col++){
						tempSeven[row - 6][col] = currentState[row][col];	
					}
					
					for(int col = 3; col < 6; col++){
						tempEight[row - 6][col - 3] = currentState[row][col];	
					}
					
					for(int col = 6; col < 9; col++){
						tempNine[row - 6][col - 6] = currentState[row][col];	
					}
					break;
				default:
					System.out.println("You shouldn't be here...");
					break;
			}
		}
		
		int matchOne = 0;
		int matchTwo = 0;
		int matchThree = 0;
		int matchFour = 0;
		int matchFive = 0;
		int matchSix = 0;
		int matchSeven = 0;
		int matchEight = 0;
		int matchNine = 0;

		/*for(int row = 0; row < 3; row++){
			for(int col = 0; col < 3; col++){
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						if(tempOne[row][col] == tempOne[i][j])
							matchOne++;
						if(tempTwo[row][col] == tempTwo[i][j])
							matchTwo++;
						if(tempThree[row][col] == tempThree[i][j])
							matchThree++;
						if(tempFour[row][col] == tempFour[i][j])
							matchFour++;
						if(tempFive[row][col] == tempFive[i][j])
							matchFive++;
						if(tempSix[row][col] == tempSix[i][j])
							matchSix++;
						if(tempSeven[row][col] == tempSeven[i][j])
							matchSeven++;
						if(tempEight[row][col] == tempEight[i][j])
							matchEight++;
						if(tempNine[row][col] == tempNine[i][j])
							matchNine++;
					}
				}
				
			}
		}*/
		for(int row = 0; row < 3; row++){
			for(int col = 0; col < 3; col++){
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
					if (currentState[row][col]==currentState[i][j]) 
						if(row<i || col<j) matchOne++;
					}
				}
			}
		}
		for(int row = 3; row < 6; row++){
			for(int col = 0; col < 3; col++){
				for(int i = 3; i < 6; i++){
					for(int j = 0; j < 3; j++){
					if (currentState[row][col]==currentState[i][j]) 
						if(row<i || col<j) matchTwo++;
					}
				}
			}	
		}
		for(int row = 6; row < 9; row++){
			for(int col = 0; col < 3; col++){
				for(int i = 6; i < 9; i++){
					for(int j = 0; j < 3; j++){
					if (currentState[row][col]==currentState[i][j])
						if(row<i || col<j) matchThree++;
					}
				}
			}	
		}
		for(int row = 0; row < 3; row++){
			for(int col = 3; col < 6; col++){
				for(int i = 0; i < 3; i++){
					for(int j = 3; j < 6; j++){
					if (currentState[row][col]==currentState[i][j])
						if(row<i || col<j) matchFour++;
					}
				}
			}	
		}
		for(int row = 3; row < 6; row++){
			for(int col = 3; col < 6; col++){
				for(int i = 3; i < 6; i++){
					for(int j = 3; j < 6; j++){
					if (currentState[row][col]==currentState[i][j]) 
						if(row<i || col<j) matchFive++;
					}
				}
			}	
		}
		for(int row = 6; row < 9; row++){
			for(int col = 3; col < 6; col++){
				for(int i = 6; i < 9; i++){
					for(int j = 3; j < 6; j++){
					if (currentState[row][col]==currentState[i][j]) 
						if(row<i || col<j) matchSix++;
					}
				}
			}	
		}
		for(int row = 0; row < 3; row++){
			for(int col = 6; col < 9; col++){
				for(int i = 0; i < 3; i++){
					for(int j = 6; j < 9; j++){
					if (currentState[row][col]==currentState[i][j])
						if(row<i || col<j) matchSeven++;
					}
				}
			}	
		}
		for(int row = 3; row < 6; row++){
			for(int col = 6; col < 9; col++){
				for(int i = 3; i < 6; i++){
					for(int j = 6; j < 9; j++){
					if (currentState[row][col]==currentState[i][j])
						if(row<i || col<j) matchEight++;
					}
				}
			}	
		}
		for(int row = 6; row < 9; row++){
			for(int col = 6; col < 9; col++){
				for(int i = 6; i < 9; i++){
					for(int j = 6; j < 9; j++){
					if (currentState[row][col]==currentState[i][j])
						if(row<i || col<j) matchNine++;
					}
				}
			}	
		}

		return match+matchOne+matchTwo+matchThree+matchFour+matchFive+matchSix+matchSeven+matchEight+matchNine;
	}
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
	
}
