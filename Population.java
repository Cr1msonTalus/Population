import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *	Population - Reading a 2017 database on the cities in the US. Supports
 *  6 queries that sort the cities in some order and output 50 depending
 *  on the query.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Talus Leong
 *	@since	01/17/23
 */
public class Population {
	
	// List of cities
	private List<City> cities = new ArrayList<City>();
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	//Sort methods object
	private SortMethods sm = new SortMethods();
	
	public static void main(String[] args) {
		Population p = new Population();
		p.inputData();
		p.commandCenter();
	}
	
	/**
	 * Method prompts for query and runs all the sort methods
	 * 
	 * @return void - nothing to return
	 */
	public void commandCenter() {
		printIntroduction();
		System.out.println(cities.size() + " cities in our database");
		int selection;
		do {
			printMenu();
			do {
				selection = Prompt.getInt("Enter selection ");
			} while (selection < 1 || selection == 7 || selection == 8 || selection > 10);
			if (selection == 1) ascendingPop();
			if (selection == 2) descendingPop();
			if (selection == 3) ascendingName();
			if (selection == 4) descendingName();
			if (selection == 5) {
				String state;
				do {
					state = Prompt.getString("Enter state name (ie. California) ");
				} while (binarySearch(cities, 0, cities.size(), state) < 0);
				populousCityInState(state);
			}
			if (selection == 6) {
				String city = Prompt.getString("Enter city name ");
				cityByPopulation(city);
			}
			if (selection == 9) System.out.println("Thanks for using Population!");
		} while (selection != 9);	
	}
	
	/**
	 * Read the file and store the data as a list of cities
	 * 
	 * @return void - nothing to return, just read file
	 */
	public void inputData() {
		FileUtils fu = new FileUtils();
		Scanner sc = fu.openToRead(DATA_FILE);
		sc.useDelimiter("[\t\n]");
		while (sc.hasNext()) {
			String state = sc.next();
			String city = sc.next();
			String type = sc.next();
			int pop = sc.nextInt();
			City c = new City(city, state, type, pop);
			cities.add(c);
		}
	}
	
	/**
	 * Query 1, sort the cities in ascending order in terms of population,
	 * print out the top 50 as well as time elapsed
	 * 
	 * @return void - nothing to retun, everything is complete after program
	 * is run
	 */
	public void ascendingPop() {
		//Selection sort
		System.out.println("50 least populous cities");
		long startMilli = System.currentTimeMillis();
		sm.selectionSort(cities);
		long endMilli = System.currentTimeMillis();
		printCities();
		System.out.println("Time elapsed: " + (endMilli - startMilli) + " milliseconds\n");
	}
	
	/**
	 * Query 2, sort the cities in descending order in terms of population,
	 * print out the top 50 as well as time elapsed
	 * 
	 * @return void - nothing to retun, everything is complete after program
	 * is run
	 */
	public void descendingPop() {
		//Merge sort
		System.out.println("50 most populous cities");
		long startMilli = System.currentTimeMillis();
		sm.mergeSort(cities, 0, cities.size() - 1);
		long endMilli = System.currentTimeMillis();
		printCities();
		System.out.println("Time elapsed: " + (endMilli - startMilli) + " milliseconds\n");
	}
	
	/**
	 * Query 3, sort the cities in descending order in terms of name
	 * print out the top 50 as well as time elapsed
	 * 
	 * @return void - nothing to retun, everything is complete after program
	 * is run
	 */
	public void ascendingName() {
		//Insertion sort
		System.out.println("50 cities sorted by name");
		long startMilli = System.currentTimeMillis();
		sm.insertionSort(cities);
		long endMilli = System.currentTimeMillis();
		printCities();
		System.out.println("Time elapsed: " + (endMilli - startMilli) + " milliseconds\n");
	}
	
	/**
	 * Query 4, sort the cities in descending order in terms of name,
	 * print out the top 50 as well as time elapsed
	 * 
	 * @return void - nothing to retun, everything is complete after program
	 * is run
	 */
	public void descendingName() {
		//Merge sort
		System.out.println("Last 50 cities sorted by name");
		long startMilli = System.currentTimeMillis();
		mergeSortName(cities, 0, cities.size() - 1);
		long endMilli = System.currentTimeMillis();
		printCities();
		System.out.println("Time elapsed: " + (endMilli - startMilli) + " milliseconds\n");
	}
	
	/**
	 * Query 5, sort the cities by population when given the state,
	 * print out the top 50
	 * 
	 * @return void - nothing to retun, everything is complete after program
	 * is run
	 */
	public void populousCityInState(String state) {
		List<City> smallerList = new ArrayList<City>();
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).getState().equals(state)) smallerList.add(cities.get(i));
		}
		System.out.println("50 most populous cities in " + state);
		sm.mergeSort(smallerList, 0, smallerList.size() - 1);
		System.out.println("    State                 City                  Designation      Population");
		for (int i = 1; i <= 50; i++) {
			System.out.printf("%3s " + smallerList.get(i - 1).toString() + "\n", i + ":");
		}
		System.out.println("\n");
	}
	
	/**
	 * Query 6, sort the cities by population when given name of city,
	 * print out as many cities as you can
	 * 
	 * @return void - nothing to retun, everything is complete after program
	 * is run
	 */
	public void cityByPopulation(String city) {
		List<City> smallerList = new ArrayList<City>();
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).getName().equals(city)) smallerList.add(cities.get(i));
		}
		System.out.println("Cities " + city + " by population");
		sm.mergeSort(smallerList, 0, smallerList.size() - 1);
		System.out.println("    State                 City                  Designation      Population");
		for (int i = 1; i <= smallerList.size(); i++) {
			System.out.printf("%3s " + smallerList.get(i - 1).toString() + "\n", i + ":");
		}
		System.out.println("\n");
	}
	
	/**
	 * Prints out the list of 50 cities after sorting is complete, formatted
	 * as well
	 * 
	 * @return void - nothing to return, simply prints the cities
	 */
	public void printCities() {
		System.out.println("    State                 City                  Designation      Population");
		for (int i = 1; i <= 50; i++) {
			System.out.printf("%3s " + cities.get(i - 1).toString() + "\n", i + ":");
		}
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/**
	 * Merge sort method for sorting descending by name
	 * 
	 * @param List<City> arr - the list of cities
	 * @param int l - the left side
	 * @param int r - the right side
	 */
	public void mergeSortName(List<City> arr, int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;
			mergeSortName(arr, l, m);
			mergeSortName(arr, m + 1, r);
			mergeName(arr, l, m, r);
        }
	}
	
	/**
	 * Second part of the merge sort for sorting descending by name
	 * 
	 * @param List<City> arr - the list of cities
	 * @param int l - the left side
	 * @param int r - the right side
	 */
	public void mergeName(List<City> arr, int l, int m, int r) {
		int size1 = m - l + 1;
		int size2 = r - m;
		List<City> left = new ArrayList<City>();
		List<City> right = new ArrayList<City>();
		for (int i = 0; i < size1; ++i) left.add(arr.get(l + i));
		for (int j = 0; j < size2; ++j) right.add(arr.get(m + 1 + j));
		int i = 0, j = 0;
		int k = l;
		while (i < size1 && j < size2) {
			if (left.get(i).getName().compareTo(right.get(j).getName()) > 0) {
				arr.set(k, left.get(i));
				i++;
			}
			else {
				arr.set(k, right.get(j));
				j++;
			}
			k++;
		}
		while (i < size1) {
			arr.set(k, left.get(i));
			i++;
			k++;
		}
		while (j < size2) {
			arr.set(k, right.get(j));
			j++;
			k++;
		}
    }
    
    /**
     * Basic recursive binarySearch to locate whether a state is valid
     * 
	 * @param List<City> arr - the list of cities
	 * @param int l - the left side
	 * @param int r - the right side
	 * @param String state - the inputted string
	 * @return int - either index if found or -1 if it doesn't exist
	 */

    public int binarySearch(List<City> arr, int l, int r, String state) {
		if (r >= l) {
			int mid = l + (r - l) / 2;
			if (arr.get(mid).getState().equals(state)) return mid;
			if (arr.get(mid).getState().compareTo(state) > 0) return binarySearch(arr, l, mid - 1, state);
			return binarySearch(arr, mid + 1, r, state);
		}
		return -1;
    }
}
