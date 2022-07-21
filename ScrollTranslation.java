import java.util.*;

public class ScrollTranslation
{
	//Comparable scroll class
	public static class Scroll implements Comparable<Scroll>
	{
		//Store type, first translator time, and second translator time
		int type;
		long first, second;
		//Scroll constructor
		public Scroll(long first, long second)
		{
			this.first = first;
			this.second = second;
			//Type 1 for smaller first time
			if(first < second)
			{
				this.type = 1;
			}
			//Type 3 for smaller second time
			if(first > second)
			{
				this.type = 3;
			}
			//Type 2 for same first and second times
			if(first == second)
			{
				this.type = 2;
			}
		}
		//Getter method for type
		public int getType()
		{
			return this.type;
		}
		//Sort by type in ascending order
		@Override
		public int compareTo(Scroll o)
		{
			if(this.type < o.type)
			{
				return -1;
			}
			
			if(this.type > o.type)
			{
				return 1;
			}
			
			return 0;
		}
	}
	//Scroll comparator for ordering values
	public static class ScrollComparator implements Comparator<Scroll>
	{
		public int compare(Scroll s1, Scroll s2)
		{
			//Sort type 1s by ascending order of first time
			if(s1.getType() == 1 && s2.getType() == 1)
			{
				Long first1 = s1.first;
				Long first2 = s2.first;
				return first1.compareTo(first2);
			}
			//Sort type 3s by descending order of second time
			if(s1.getType() == 3 && s2.getType() == 3)
			{
				Long second1 = s1.second;
				Long second2 = s2.second;
				return second2.compareTo(second1);
			}
			//Sort type 2s any way
			return 0;
		}
	}
	//Greedy sweep
	public static long sweep(ArrayList<Scroll> list)
	{
		//First translator time and second translator time
		long aTime = 0;
		long bTime = 0;
		//First translator time for first scroll
		long first = 0;
		//Second translator time for last scroll
		long last = 0;
		//Store first before starting loop
		first = list.get(0).first;
		//Loop through every scroll and sum/update all values
		for(Scroll scroll : list)
		{
			aTime += scroll.first;
			bTime += scroll.second;
			last = scroll.second;
		}
		//Longer or same total second translator time offset by first scroll
		if(aTime <= bTime)
		{
			return bTime + first;
		}
		//Longer total first translator time plus second time of last scroll
		if(aTime > bTime)
		{
			return aTime + last; 
		}
		
		return 0;
	}
	
	public static void main(String[] args)
	{
		//Read input
		Scanner scan = new Scanner(System.in);
		//Number of scrolls
		int n = scan.nextInt();
		ArrayList<Scroll> queue = new ArrayList<Scroll>();
		//Store scrolls in an ArrayList
		while(n --> 0)
		{
			queue.add(new Scroll(scan.nextLong(), scan.nextLong()));
		}
		//Close scanner
		scan.close();
		//Sort by type
		Collections.sort(queue);
		//Sort types by times
		Collections.sort(queue, new ScrollComparator());
		//Sweep + print answer
		System.out.println(sweep(queue));
	}
}
