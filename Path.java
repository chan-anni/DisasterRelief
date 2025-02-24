import java.util.*;

/**
 * The Path class represents an unmodifiable relief solution.
 * It provides methods to retrieve the total cost and total helped population
 * of the solution. 
 */
public class Path {

    private List<Region> regions;

    /**
     * Creates a new Path object representing the given regions.
     * @param regions the regions in the solution
     */
    

    private Path(List<Region> regions) {
        this.regions = new ArrayList<>(regions);
    }

    /**
     * Creates a new Path object with no regions in it.
     */
    public Path() {
        this(new ArrayList<>());
    }

    /**
     * Returns a copy of this Path's regions.
     */
    public List<Region> getRegions() {
        return new ArrayList<>(regions);
    }

    /**
     * Returns the starting point of this Path, or null if there are no
     * regions currently in the Path.
     */
    public Region getStart() {
        if (regions.isEmpty()) {
            return null;
        }

        return regions.get(0);
    }

    /**
     * Returns the ending point of this Path, or null if there are no
     * regions currently in the Path.
     */
    public Region getEnd() {
        if (regions.isEmpty()) {
            return null;
        }
        
        return regions.get(regions.size() - 1);
    }

    /**
     * Returns a new Path with the contents of this Path
     * and the passed in region added to the end.
     * @param r Region to be added to the end of the new Path.
     * @return a new Path with r added to it.
     */
    public Path extend(Region r) {
        if (regions.contains(r)) {
            throw new IllegalArgumentException("Path already contains region " + r);
        }
        List<Region> newRegions = new ArrayList<>(regions);
        newRegions.add(r);
        return new Path(newRegions);
    }

    /**
     * Returns a new Path with the contents of this Path
     * and the last region removed from it.
     * @return a new Path with the last region removed from it.
     */
    public Path removeEnd() {
        if (regions.isEmpty()) {
            throw new IllegalStateException("Cannot remove from an empty path.");
        }
        List<Region> newRegions = new ArrayList<>(regions);
        newRegions.remove(regions.size() - 1);
        return new Path(newRegions);
    }

    /**
     * Returns the number of regions in this Path.
     */
    public int size() {
        return regions.size();
    }

    /**
     * Calculates and returns the total population that can be helped
     * by this Path.
     * @return the total population that can be helped by this Path.
     */
    public int totalPeople() {
        int total = 0;
        for (Region r : regions) {
            total += r.getPopulation();
        }
        return total;
    }

    /**
     * Calculates and returns the combined cost of this Path.
     * @return the combined cost of this Path.
     */
    public double totalCost() {
        double total = 0;
        for (int i = 0; i < regions.size() - 1; i++) {
            total += regions.get(i).getCostTo(regions.get(i + 1));
        }
        return total;
    }

    /**
     * Returns a String representation of an Path object in the format:
     * "[Region, ..., Region]" where each Region is in its string representation.
     * @return the String representation of an Path object
     */
    public String toString() {
        return regions.toString();
    }

    /**
     * Compares the specified object with this Path for equality. Returns true if the
     * specified object is also an Path and the two Paths have the same
     * collection of regions.
     * @param other object to be compared for equality with this Path
     * @return true if the specified object is equal to this Path
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Path)) {
            return false;
        }
        Path otherAlloc = (Path)other;
        return this.regions.equals(otherAlloc.getRegions());
    }

    /**
     * Returns the hash code value for this Path
     * @return the hash code value for this Path
     */
    @Override
    public int hashCode() {
        return regions.hashCode();
    }
}
