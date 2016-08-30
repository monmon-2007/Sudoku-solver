/*
*	Mina Gadallah
*	Artificial Intellegence
*	Solve 4*4 Sudoku
*	Instructor: Rahul Shome
*	TA		  : Vahid Azizi
*/

public class Sudoku
{
	public static void main(String[] args)
	{

		int grid[][] = 		{	{1, 0, 3, 0},
								{0, 0, 0, 0},
								{2, 0, 4, 0},
								{0, 0, 0, 0},
							};


		if(solveSudoku(grid))
		{
			System.out.println("Sudoku is SOLVED: ");
			System.out.println("_______________");
			for(int i=0; i<4; i++)
			{
				for(int j=0; j<4; j++)
				{
					System.out.print(grid[i][j]+ " | ");
				}
				System.out.println();
			}
			System.out.println("---------------");

		}
		else
		{
			System.out.println("The Sudoku is UNSOLVABLE!!!");
		}


	}

	static int[] emptyGrid(int grid[][])
	{
		int[] cell = new int[2];
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if (grid[i][j] == 0)
				{
					cell[0] = i;
					cell[1] = j;
					return cell;
				}
			}
		}
			cell[0] = -1;
			cell[1] = -1;
			return cell;
	}

	static boolean inRow(int grid[][], int col, int value)
	{
		for(int i=0; i<=3; i++)
		{
			if(grid[i][col] == value)
			{
				return true;
			}
		}
		return false;
	}

	static boolean inCol(int grid[][], int row, int value)
	{
		for(int i=0; i<=3; i++)
		{
			if(grid[row][i] == value)
			{
				return true;
			}
		}
		return false;
	}

	static boolean inBox(int grid[][], int col, int row, int value)
	{
		if(row<=1 && col<=1){row=0;col=0;} //ok!
		else if(row<=1 &&(col==2 || col==3) ){row=0;col=2;} //ok!
		else if((row==2||row==3)&&(col==2||col==3)){row=2;col=2;}
		else if((row==2||row==3)&&col<=1){row=2;col=0;}


		for(int x=row; x<=row+1; x++)
		{
			for(int y=col; y<=col+1; y++)
			{
				if(grid[x][y] == value)
				{
					return true;
				}
			}
		}
		return false;
	}

	static boolean isEmpty(int grid[][]) //if not empty spcese ??
	{
		for(int x=0; x<=3; x++)
		{
			for(int y=0; y<=3; y++)
			{
				if(grid[x][y] == 0)
				{
					return true;
				}
			}
		}
		return false;
	}

	static boolean verifyNum(int grid[][], int col, int row, int value)  //true is good to go
	{
		if(!inBox(grid,col,row,value) && !inCol(grid,row,value) && !inRow(grid,col,value))
		{
			return true;
		}
		else
		return false;
	}

	static boolean solveSudoku(int grid[][])
	{

		int[] emptyCell = emptyGrid(grid);
		int row = emptyCell[0];
		int col = emptyCell[1];

		if (!isEmpty(grid))
		{
			return true;
		}

		for (int i = 1; i <= 4; i++)
		{
			if (verifyNum(grid,col,row,i))
			{
				grid[row][col] = i;

				if (solveSudoku(grid))
				{
					return true;
				}

				grid[row][col] = 0;
			}
		}
		return false;
	}

}