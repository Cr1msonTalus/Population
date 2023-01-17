/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Talus Leong
 *	@since	1/15/2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String state, name, designation;
	private int population;
	// constructor
	public City(String city, String state, String type, int pop) {
		this.name = city;
		this.state = state;
		this.designation = type;
		this.population = pop;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other) {
		if (population != other.population) return this.population - other.population;
		else if (!state.equals(other.state)) return this.state.compareTo(other.state);
		else return this.name.compareTo(other.name);
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other) {
		return (name.equals(other.name)) && (state.equals(other.state));
	}
	
	/**	Accessor methods */
	public String getName() { return this.name; }
	public String getState() { return this.state; }
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
