import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;

public class Tasks
{
	public static void main(String[] args)
	{
		//1)
		//perimeter(args);
		//diagonal(args);
		//maximum(args);
	}
	
	public static void perimeter(String[] args)
	{
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i = 0; i < args.length; i++)
		{
			arr.add(Integer.parseInt(args[i]));
		}
		Collections.sort(arr, Collections.reverseOrder());
		int max1 = arr.get(0), max2 = arr.get(1), perimeter = 0;
		arr.remove(0);
		arr.remove(0);
		boolean notFound = true;
		while(notFound)
		{
			if(max1 < max2 + arr.get(0))
			{
				notFound = false;
				perimeter = max1 + max2 + arr.get(0);
			}
			else
			{
				max1 = max2;
				max2 = arr.get(0);
				arr.remove(0);
				if(arr.size() == 0)
				{
					break;
				}
			}
		}
		System.out.println(perimeter);
	}
	
	public static void diagonal(String[] args)
	{
		int[][] matrix;
		int m, n, min_limit, max_limit;
		try
		{
			if(args.length >= 4)
			{
				m = Integer.parseInt(args[0]);
				n = Integer.parseInt(args[1]);
				min_limit = Integer.parseInt(args[2]);
				max_limit = Integer.parseInt(args[3]);
				matrix = new int[m][n];
			}
			else
			{
				m = 50;
				n = 50;
				min_limit = -100;
				max_limit = 100;
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
			for(int i = 0, j = matrix[0].length-2; i < matrix.length-1 || j > 0;)
			{
				for(int k = j, l = i; k < matrix[0].length && l < matrix.length;)
				{
					int min = matrix[l][k], indexJ = k, indexI = l, changer;
					for(int k1 = k, l1 = l; k1 < matrix[0].length && l1 < matrix.length;)
					{
						if(matrix[l1][k1] < min)
						{
							min = matrix[l1][k1];
							indexJ = k1;
							indexI = l1;
						}
						k1++;
						l1++;
					}
					
					changer = matrix[l][k];
					matrix[l][k] = min;
					matrix[indexI][indexJ] = changer;
					
					k++;
					l++;
				}
				if(j - 1 >= 0)
				{
					j--;
				}
				else
				{
					if(i + 1 <= matrix.length-1)
					{
						i++;
					}
				}
			}
			System.out.println(Arrays.deepToString(matrix));
			
		}
		catch(Exception ex)
		{
			System.out.println("Arguments error, input form: java Tasks <count_of_lines> <count_of_columns> <min_limit> <max_limit>");
			ex.printStackTrace();
		}
	}
	
	public static void maximum(String[] args)
	{
		String number = "";
		for(int c = 0; c < args.length; c++)
		{
			for(int i = c; i < args.length-1; i++)
			{
				for(int j = i+1; j < args.length; j++)
				{
					String changer = "";
					if(args[i].length() == args[j].length())
					{
						int compare = args[i].compareTo(args[j]);
						
						if(compare < 0)
						{
							changer = args[i];
							args[i] = args[j];
							args[j] = changer;
						}
					}
					else
					{
						String added = "";
						if(args[i].length() < args[j].length())
						{
							added += args[i];
							char max = added.charAt(0);
							for(int k = 1; k < added.length(); k++)
							{
								if(added.charAt(k) > max)
								{
									max = added.charAt(k);
								}
							}
							for(int k = 0; k < args[j].length() - added.length(); k++)
							{
								added += max;
							}
							int compare = added.compareTo(args[j]);
							if(compare < 0)
							{
								changer = args[i];
								args[i] = args[j];
								args[j] = changer;
							}
						}
						if(args[i].length() > args[j].length())
						{
							added += args[j];
							char max = added.charAt(0);
							for(int k = 1; k < added.length(); k++)
							{
								if(added.charAt(k) > max)
								{
									max = added.charAt(k);
								}
							}
							for(int k = 0; k < args[i].length() - added.length(); k++)
							{
								added += max;
							}
							int compare = args[i].compareTo(added);
							if(compare < 0)
							{
								changer = args[i];
								args[i] = args[j];
								args[j] = changer;
							}
						}
					}
					
				}
			}
			
		}
		for(String s : args)
		{
			number += s;
		}
		System.out.println(number);
	}
}