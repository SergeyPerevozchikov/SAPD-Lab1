import java.util.Date;
import java.util.ArrayList;
//import java.io;
import java.util.Arrays;
import java.util.Random;

public class Lab1
{
	
	
	public static void main(String[] args)
	{
		int[][] matrix;
		int m, n, min_limit, max_limit;
		try
		{
			if(args.length >= 5)
			{
				m = Integer.parseInt(args[1]);
				n = Integer.parseInt(args[2]);
				min_limit = Integer.parseInt(args[3]);
				max_limit = Integer.parseInt(args[4]);
				matrix = new int[m][n];
			}
			else
			{
				m = 50;
				n = 50;
				min_limit = -250;
				max_limit = 1017;
				matrix = new int[n][m];
			}
			for(int i = 0; i < m; i++)
			{
				for(int j = 0; j < n; j++)
				{
					matrix[i][j] = (int)(Math.random()*(max_limit - min_limit + 1) + min_limit);
				}
			}
			System.out.println(Arrays.deepToString(matrix));
			
			boolean sorted = false;
			if(args[0].equals("selection"))
			{
				sorted = true;
				Date start = new Date();
				selection(matrix);
				Date finish = new Date();
				System.out.print("Time (ms) consumed for sort = ");
				System.out.println(finish.getTime() - start.getTime());
			}
			if(args[0].equals("insertion"))
			{
				sorted = true;
				Date start = new Date();
				insertion(matrix);
				Date finish = new Date();
				System.out.print("Time (ms) consumed for sort = ");
				System.out.println(finish.getTime() - start.getTime());
			}
			if(args[0].equals("bubble"))
			{
				sorted = true;
				Date start = new Date();
				bubble(matrix);
				Date finish = new Date();
				System.out.print("Time (ms) consumed for sort = ");
				System.out.println(finish.getTime() - start.getTime());
			}
			if(args[0].equals("shell"))
			{
				sorted = true;
				Date start = new Date();
				shell(matrix);
				Date finish = new Date();
				System.out.print("Time (ms) consumed for sort = ");
				System.out.println(finish.getTime() - start.getTime());
			}
			if(args[0].equals("tournament"))
			{
				sorted = true;
				Date start = new Date();
				tournament(matrix);
				Date finish = new Date();
				System.out.print("Time (ms) consumed for sort = ");
				System.out.println(finish.getTime() - start.getTime());
			}
			if(args[0].equals("quick"))
			{
				sorted = true;
				Date start = new Date();
				quick(matrix);
				Date finish = new Date();
				System.out.print("Time (ms) consumed for sort = ");
				System.out.println(finish.getTime() - start.getTime());
			}
			if(args[0].equals("heap"))
			{
				sorted = true;
				Date start = new Date();
				heap(matrix);
				Date finish = new Date();
				System.out.print("Time (ms) consumed for sort = ");
				System.out.println(finish.getTime() - start.getTime());
			}
			if(args[0].equals("default"))
			{
				sorted = true;
				Date start = new Date();
				for(int i = 0; i < matrix.length; i++)
					Arrays.sort(matrix[i]);
				System.out.println(Arrays.deepToString(matrix));
				Date finish = new Date();
				System.out.print("Time (ms) consumed for sort = ");
				System.out.println(finish.getTime() - start.getTime());
			}
			if(!sorted)
			{
				System.out.println("Type of sort isn't mentioned or mentioned incorrectly.");
				System.out.println("Types of sort: 'selection','insertion', 'bubble', 'shell', 'tournament', 'quick', 'heap'");
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Arguments error, input form: java Lab1 type_of_sort <count_of_lines> <count_of_columns> <min_limit> <max_limit>");
		}
		
		
	}
	
	public static int[][] selection(int[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				int min = matrix[i][j], index = j, changer;
				for(int k = j; k < matrix[i].length; k++)
				{
					if(matrix[i][k] < min)
					{
						min = matrix[i][k];
						index = k;
					}
				}
				changer = matrix[i][j];
				matrix[i][j] = min;
				matrix[i][index] = changer;
			}
		}
		
		System.out.println(Arrays.deepToString(matrix));
		return matrix;
	}
	
	public static int[][] insertion(int[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 1; j < matrix[i].length; j++)
			{
				int num = matrix[i][j], changer;
				for(int k = j-1; k >= 0; k--)
				{
					if(matrix[i][k] > num)
					{
						changer = matrix[i][k];
						matrix[i][k] = num;
						matrix[i][k+1] = changer;
					}
				}
			}
			
		}
		System.out.println(Arrays.deepToString(matrix));
		return matrix;
	}
	
	public static int[][] bubble(int[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = matrix[i].length; j > 0; j--)
			{
				int changer;
				for(int k = 0; k < j-1; k++)
				{
					if(matrix[i][k] > matrix[i][k+1])
					{
						changer = matrix[i][k+1];
						matrix[i][k+1] = matrix[i][k];
						matrix[i][k] = changer;
						
					}
				}
			}
		}
		System.out.println(Arrays.deepToString(matrix));
		return matrix;
	}
	
	public static int[][] shell(int[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int d = matrix[i].length / 2; d > 1; d = d/2)
			{
				int changer;
				for(int j = 0; j < matrix[i].length - d; j++)
				{
					if(matrix[i][j] > matrix[i][j+d])
					{
						changer = matrix[i][j+d];
						matrix[i][j+d] = matrix[i][j];
						matrix[i][j] = changer;
					}
				}
			}
		}
		return matrix;
	}
	
	public static int[][] tournament(int[][] matrix)
	{
		int[][] array = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				int changer;
				for(int comparator = 1; comparator < matrix[i].length; comparator *= 2)
				{
					for(int k = 0; k*comparator*2 < matrix[i].length; k++)
					{
						if(k*comparator*2 + comparator < matrix[i].length)
						{
							if(matrix[i][k*comparator*2 + comparator] < matrix[i][k*comparator*2])
							{
								changer = matrix[i][k*comparator*2 + comparator];
								matrix[i][k*comparator*2 + comparator] = matrix[i][k*comparator*2];
								matrix[i][k*comparator*2] = changer;
							}
							
						}
					}
				}
				array[i][j] = matrix[i][0];
				matrix[i][0] = Integer.MAX_VALUE;

			}
			
		}
		System.out.println(Arrays.deepToString(array));
		return array;
	}
	
	
	
	public static int[][] quick(int[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			matrix = hoar(matrix, i, 0, matrix[i].length-1);
		}
		System.out.println(Arrays.deepToString(matrix));
		return matrix;
	}
	
	public static int[][] hoar(int[][] matrix, int i, int l, int r)
	{
		int pivot = matrix[i][l], j = l, k = r;
		while(j < k)
		{
			while(matrix[i][k] >= pivot && j < k)
			{
				k--;
			}
			if(j != k)
			{
				matrix[i][j] = matrix[i][k];
				j++;
			}
			while(matrix[i][j] <= pivot && j < k)
			{
				j++;
			}
			if(j != k)
			{
				matrix[i][k] = matrix[i][j];
				k--;
			}
		}
		matrix[i][j] = pivot;
		if(l < j)
		{
			matrix = hoar(matrix, i, l, j-1);
		}
		if(r > j)
		{
			matrix = hoar(matrix, i, j+1, r);
		}
		
		return matrix;
	}
	
	public static int[][] heap(int[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = matrix[i].length-1; j > 0; j--)
			{
				int k = 0, summ = 1, changer;
				while(summ < j+1)
				{
					summ += 2;
					if(summ < j+1)
					{
						k++;
					}
				}
				for(int index = k; index >= 0; index--)
				{
					if(matrix[i][index] < matrix[i][2*index+1])
					{
						changer = matrix[i][index];
						matrix[i][index] = matrix[i][2*index+1];
						matrix[i][2*index+1] = changer;
					}
					if(2*index + 2 < j+1)
					{
						if(matrix[i][index] < matrix[i][2*index+2])
						{
							changer = matrix[i][index];
							matrix[i][index] = matrix[i][2*index+2];
							matrix[i][2*index+2] = changer;
						}
					}
				}
				
				changer = matrix[i][0];
				matrix[i][0] = matrix[i][j];
				matrix[i][j] = changer;
				

			}
		}
		System.out.println(Arrays.deepToString(matrix));
		return matrix;
	}
	
}

