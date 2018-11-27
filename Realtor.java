package agency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Realtor is in charge of presenting the options that match the buyers'
 * criteria, and saves any sold properties in archivedOfPropertiesSold.
 */
public class Realtor {

	private Property propertySpecs; // Specifications of the seller's property
	private Property propertySold; // Property selected and bought by user from similarProperties' list.
	private ArrayList<Property> similarProperties; // Properties matching the specifications
	private ArrayList<Property> listing; // All the properties on sale
	/**
	 * constructor to receive the specifications of the property that user wants to buy.
	 */
	public Realtor() {//Property propertySpecs) {
		//this.propertySpecs = propertySpecs;
		similarProperties = new ArrayList<>();
		listing = new ArrayList<>();
	}

	/**
	 * Retrieves properties saved in text file and puts them in an ArrayList to be sorted
	 * later in compareProperties() 
	 */
	public ArrayList<Property> getListing(){
		
		FileReader f = new FileReader();
		listing.addAll(f.getPropertiesToBeSold("PropertiesForSale.xlsx"));
		
		return listing;
	}
	
	/**
	 * Compare properties for sale in HouseListing to the buyer's specifications
	 */
	public void compareProperties() {
		/**
		 * Sorting of files could be made from this class
		 * 
		 * 'if' statements to sort and save similar properties from the HouseListing
		 * that match search criteria in propertySpecs and save in collection similarProperties
		 *
		 * Maybe display: No properties found matching your criteria or exception could
		 * be added
		 */
		for (Property p : listing) {
			if (propertySpecs.getBuiltIn() == p.getBuiltIn()
					&& propertySpecs.getPropertyType().equals(p.getPropertyType())
					&& propertySpecs.getFloors() == p.getFloors() && propertySpecs.getNumBath() == p.getNumBath()
					&& propertySpecs.getNumRooms() == p.getNumBath()
					&& propertySpecs.getSquareFoot() == p.getSquareFoot()
					&& propertySpecs.isRentZoning() == p.isRentZoning()
					&& propertySpecs.isHas_fireplace() == p.isHas_fireplace()
					&& propertySpecs.isHas_garage() == p.isHas_garage() && propertySpecs.isHas_pool() == p.isHas_pool()
					&& propertySpecs.isHas_secSystem() == p.isHas_secSystem()
					&& propertySpecs.getBackyard() < p.getBackyard() && propertySpecs.getValue() < p.getValue())

				similarProperties.add(p);
		}
	}

	/**
	 * Return the properties that match the search
	 */
	public ArrayList<Property> propertyMatched() {
		return similarProperties;
	}

	/**
	 * After user selects a property from similarProperties and buys it, get that Sproperty back
	 */
	public void getPropertySold(Property propertySold) {
		this.propertySold = propertySold;
	}
	
	/**
	 * Save sold property in ArchivedProperties
	 */
	public void saveSoldProperty() throws IOException {

		/**
		 * Data could be stored in an Excel workbook, exceptions could be added here to
		 * handle problems with the file creation
		 */
		BufferedWriter writer = new BufferedWriter(new FileWriter("ArchiveOfPropertiesSold.txt"));
		writer.append(propertySold.propWrite());
		writer.append("\n");
		writer.close();
	}
}
