/**
 * My first attempt at the UKTill 
 * Contains floats of various UK denominations
 * 
 * @author Mrs Miggins
 * @version February 2015
 */

public class UKTill {
	private DenominationFloat[] contents;

	/**
	 * Builds an empty till
	 */
	public UKTill() {
		contents = new DenominationFloat[UKDenomination.values().length];
		

		// Initialise the array with empty floats for each denomination. This is
		// kind of having empty trays for each denomination in the till!
		for (UKDenomination denom : UKDenomination.values()) {
			DenominationFloat denomFloat = new DenominationFloat(denom, 0);
			contents[denom.ordinal()] = denomFloat;
		}
	}

	/**
	 * Enables a user to add a denomination float to the Till. Will add a copy
	 *
	 * @param theFloat
	 *            the denomination float to be added to the Till as a copy
	 */
	public void addFloat(DenominationFloat theFloat) {
		// Add to existing float
		DenominationFloat currentFloat = contents[theFloat.getType().ordinal()];
		currentFloat.setQuantity(currentFloat.getQuantity()
				+ theFloat.getQuantity());
	}

	/**
	 * Enables a user to clear a denomination float from the Till.
	 *
	 * @param denominationType
	 *            Denomination float to be removed
	 */
	public void removeFloat(UKDenomination denominationType) {
		contents[denominationType.ordinal()].setQuantity(0);
	}

	/**
	 * Empties out the till and returns the contents
	 * @return An array containing the contents of the till, with
	 * each element dealing with a given UK denomination. If the denomination
	 * does not exist in the till then returns a DenominationFloat with zero
	 * quantity.
	 */
	public DenominationFloat[] emptyTill() {
		DenominationFloat[] tillContents = new DenominationFloat[contents.length];
		for (int i = 0; i < contents.length; i++) {
			DenominationFloat denom = new DenominationFloat(
					contents[i].getType(), contents[i].getQuantity());
			tillContents[i] = denom;
			contents[i].setQuantity(0);
		}
		return tillContents;
	}

	/**
	 * Calculates the change to be returned from the till and decrements the
	 * till for each denomination
	 * 
	 * @param changeDue
	 *            The amount in pence we wish to extract from the till. 
	 * @return An array of denominations of different values that are in sum equal
	 *         to the provided changeDue value in pence. If there isn't
	 *         enough change in the till then display an error message saying 
	 *         that Mrs Miggins owes the customer some money!
	 */
	public DenominationFloat[] getChange(int changeDue) {
		
		DenominationFloat[] change = null; // Null added so that the class compiles
		// ENTER CODE HERE
		// Skip over empty denomination trays
		// Make sure we only give back change from denomination smaller than changeDue!
		// Keep taking out DenominationFloats of change from the till until we have the
		// right amount to return.
		// Make sure the floats in the till are decreased when we take some out!
		// Tell the user if there isn't enough change or of the right type and that
		// the shop owes them :-)
		return change;
	}

	/**
	 * toString returns a formatted String representing denomination floats in
	 * the Till
	 *
	 * @return String a formatted representation of the Till contents
	 */
	public String toString() {
		// StringBuilder is a more efficient way of building Strings. The
		// problem with the String class is that String objects are immutable, i.e.
		// their contents cannot be changed. So every operation you run on a String object
		// (e.g. concatenation + operation) results in another String object being created
		// that holds the result of the operation. This is slow and takes up lots of memory. 
		// StringBuilder allows you to append to an existing StringBuilder.
		StringBuilder results = new StringBuilder();
		for (DenominationFloat f : contents) {
			results.append(f).append('\n');
		}
		return results.toString();
	}

}
