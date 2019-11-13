/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 6/26/2016
* Filename: Maze.java
* Details: CSC115 Assignment 4
*/

public class Maze {
	char[][] mazeData;
	Cell start;
	Cell finish;
	private CellDeque path= new CellDeque();
	
	/* Changes the array of strings passed into the method to 
	2D-array of character. Initializes the start and finish variables.
	@param textmaze-array of strings representing maze.
	start- Cell where the maze starts
	finish- Cell where the maze ends
	*/
	public Maze(java.lang.String[] textmaze, Cell start, Cell finish){
		mazeData = new char[textmaze.length][textmaze[0].length()];
		for(int i=0; i<textmaze.length; i++){
			for(int j=0; j<textmaze[i].length(); j++){
				mazeData[i][j]=textmaze[i].charAt(j);
			}
		}
		
		this.start = start;
		this.finish = finish;
		
		CellDeque path = new CellDeque();
	}
	
	/* Public method which calls the recursive method.
	@returns CellDeque with path from start to end, if any exists.
	*/
	public CellDeque solve(){
		boolean solved=find(start, finish);
			if (solved){
				return path;
			}
			else{
				return null;
			}
		
		
	}
	
	/* Recursive method to solve the maze. Uses a backtracking maze algorithm, attemping each possible path,
	making all possible dead ends. Uses a CellDeque object to keep track of path.
	@param Takes in two Cells, start and finish, which indicates the
	path to be created by the method.
	@return true if a path is found between two cells, false otherwise.
	*/
	private boolean find(Cell start, Cell finish){
		path.insertLast(start);
		mazeData[start.row][start.col] = 'P';
		
		if(start.equals(finish)){
			return true;
			
		} else{
			//DOWN
			if(!isWall(start.row+1,start.col)){
				Cell temp = new Cell(start.row+1, start.col);
				if(find(temp, finish)){
					return true;
				}
			}
			//RIGHT
			if(!isWall(start.row,start.col+1)){
				Cell temp = new Cell(start.row, start.col+1);
				if(find(temp, finish)){
					return true;
				}
			}
			//LEFT
			if(!isWall(start.row,start.col-1)){
				Cell temp = new Cell(start.row, start.col-1);
				if(find(temp, finish)){
					return true;
				}
			}
			//UP
			try{
				if(!isWall(start.row-1,start.col)){
					Cell temp = new Cell(start.row-1, start.col);
					if(find(temp, finish)){
						return true;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Maze cannot be solved.");
					return false;
			}

			path.removeLast();			//Backtracking
				
			mazeData[start.row][start.col]='N'; 	//Indicating dead end.
			return false;
		}
	}
	/* Private helper method to determine if the cell adjacent to current point is
	wall or not.
	@return True if it is wall, false otherwise.
	*/
	private boolean isWall(int row, int col){
		if(mazeData[row][col] == ' '){
			return false;
		} else{
			return true;
		}
	}
	/* @return String with full maze.
	*/
	public java.lang.String toString(){
		String details= "";
		for(int i=0; i<mazeData.length; i++){
			for(int j=0; j<mazeData[0].length; j++){
				details = details + mazeData[i][j];
			}
			details = details + "\n";
		}
		return details;
	}
}