package hw;

import java.util.concurrent.*;

public class MatrixMathImpl implements MatrixMath
{	
	public void add(int A[][], int B[][], int C[][])
	{
		final CountDownLatch myLatch = new CountDownLatch((C.length * C[0].length));
		final ExecutorService exec = Executors.newFixedThreadPool(C.length * C[0].length);
		try{
			for(int i=0; i<A.length; i++)
			{
				for(int j = 0; j < A[i].length; j++)
				{
					exec.execute(new AdditionRunnable(A[i][j], B[i][j], C, i, j, myLatch));
				}
			}
			myLatch.await();
			
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}finally{
			exec.shutdown();
		}
	}
	
	public void multiply(int A[][], int B[][], int C[][])
	{
		final CountDownLatch myLatch = new CountDownLatch((C.length * C[0].length));
		final ExecutorService exec = Executors.newFixedThreadPool(C.length * C[0].length);
		try{
			for(int i=0; i<C.length; i++)
			{
				for(int j=0; j<C[i].length; j++)
				{
					exec.execute(new MultiplicationRunnable(A, B, C, i, j, B.length, myLatch));
				}
			}
			myLatch.await();
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}finally{
			exec.shutdown();
		}
		
	}
	
	public void print(String name, int A[][])
	{
		System.out.println("The array name is: " + name);
		for(int i = 0; i < A.length; i++)
		{
			for(int j = 0; j < A[i].length; j++)
			{
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}
	}
}

class AdditionRunnable implements Runnable
{
	int A, B;
	int C[][];
	int i, j;
	CountDownLatch myLatch;
	
	public AdditionRunnable(int A, int B, int C[][], int i, int j, CountDownLatch myLatch)
	{
		this.A = A;
		this.B = B;
		this.C = C;
		this.i = i;
		this.j = j;
		this.myLatch = myLatch;
	}
	
	public void run()
	{
		C[i][j] = A + B;
		myLatch.countDown();
	}
}

class MultiplicationRunnable implements Runnable
{
	int [][] A;
	int [][] B;
	int [][] C;
	int i, j, row;
	CountDownLatch myLatch;
	
	public MultiplicationRunnable(int [][] A, int [][] B, int [][] C, int i, int j, int row, CountDownLatch myLatch)
	{
		this.A = A;
		this.B = B;
		this.C = C;
		this.i = i;
		this.j = j;
		this.row = row;
		this.myLatch = myLatch;
	}
	
	public void run()
	{
		for(int x=0; x<row; x++)
		{
			C[i][j] += A[i][x] * B[x][j];
		}
		myLatch.countDown();
	}
}
