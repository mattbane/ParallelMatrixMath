package hw;
import java.util.concurrent.*;

public interface MatrixMath 
{
	void add(int[][]A, int[][]B, int[][]C);
	void multiply(int[][]A, int[][]B, int[][]C);
	void print(String name, int[][]A);
}


