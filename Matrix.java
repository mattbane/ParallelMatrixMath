package hw;

public class Matrix 
{
	static final int numRowsM = 4;
	static final int numColsN = 5;
	static final int numColsMult = 6;
	public static void main(String[] args) 
	{
		
		int[][] A = new int[numRowsM][numColsN];
		int[][] B = new int[numRowsM][numColsN];
		int[][] C = new int[numRowsM][numColsN];
		int[][] D = new int[numColsN][numColsMult];
		int[][] E = new int[numRowsM][numColsMult];
		int[][] F = new int[numColsMult][numColsN];
		int[][] G = new int[numRowsM][numColsN];
		init2DArray(A, numRowsM, numColsN);
		init2DArray(B, numRowsM, numColsN);
		init2DArray(D, numColsN, numColsMult);
		init2DArray(F, numColsMult, numColsN);
		MatrixMath m = new MatrixMathImpl();
		m.add(A, B, C);
		m.multiply(C, D, E);
		m.multiply(E, F, G);
		m.print("C", C);
		m.print("E", E);
		m.print("G", G);
	}
	
	private static void init2DArray(int[][] A, int numRows, int numCols)
	{
		for(int i=0; i < numRows; i++)
		{
			for(int j=0; j < numCols; j++)
			{
				A[i][j] = 2;
			}
		}
	}
}
