import java.util.*;

/**
 * The Region class represents a geographical region with a name and population, 
 * along with information about the cost associated with travel from this region 
 * to others, and whether another region is reachable from this region.
 */
public class Region {
    private String name;
    private int population;
    private Map<Region, Double> costs;

    /**
     * Creates a new Region object with the given name, population, and costs.
     * @param name the name of the region
     * @param pop the population of the region* @param costs the cost to travel from this region 
     * to various other regions
     */
    public Region(String name, int pop, Map<Region, Double> costs) {
        this.name = name;
        this.population = pop;
        this.costs = costs;
    }

    /**
     * Creates a new Region object with the given name and population.
     * @param name the name of the region
     * @param pop the population of the region
     */
    public Region(String name, int pop) {
        this(name, pop, new HashMap<Region, Double>());
    }

    /**
     * Adds a new connection from this region to other with the specified cost.
     * @param other the region to connect to
     * @param cost the cost to travel from this region to other
     */
    public void addConnection(Region other, double cost) {
        costs.put(other, cost);
    }


    /**
     * Returns the name of the region
     * @return the name of the region
     */
    public String getName() { return this.name; }

    /**
     * Returns the population of the region
     * @return the population of the region
     */
    public int getPopulation() { return this.population; }

    /**
     * Returns the cost of travelling from this region to another region
     * @param other the region to find the cost of travelling to
     * @return the cost of travelling from this region to other 
     */
    public double getCostTo(Region other) {
        if (!costs.containsKey(other)) {
            throw new IllegalArgumentException("Cannot travel from " + this.name + " to " + other.name);
        }

        return costs.get(other);
    }

    /**
     * Checks whether or not another region can be reached from this region
     * @param other the region to check 
     * @return true if other can be reached from this region, false otherwise
     */    
    public boolean canReach(Region other) {
        return costs.containsKey(other);
    }


    /**
     * Returns a String representation of a Region object in the format:
     * "<name>: pop. <population> - [<connection> ($<cost>), ...]"
     * @return the String representation of a Region object
     */
    public String toString() {
        String result = name + ": pop. " + population + " - [";
        for (Region connection : costs.keySet()) {
            result += connection.name + " (" + costs.get(connection) + "), ";
        }
        result = result.substring(0, result.length() - 2) + "]";
        return result;
    }

    /**
     * Compares the specified object with this region for equality. Returns true if the
     * specified object is also a region and the two regions have the
     * same name and population.
     * @param other object to be compared for equality with this region
     * @return true if the specified object is equal to this region
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Region)) {
            return false;
        }
        Region otherLoc = (Region)other;

        return this.name.equals(otherLoc.name) &&
                this.population == otherLoc.population; 
    }

    /**
     * Returns the hash code value for this Region
     * @return the hash code value for this Region
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Integer.hashCode(population);
        return result;
    }
}
