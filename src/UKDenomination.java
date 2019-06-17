/**
 * Represents UK denominations
 * @author Mrs Miggins
 * @version 1.0 (23rd February 2015)
 *
 */
public enum UKDenomination {
	
	// These are the enumeration values. It we give
	// them a parameter then that is passed through to a constructor.
	// This is so that we can store the number of pence equivalent
	// that each denomination represents. We can then get that value
	// by calling getValue (see below)
	pence_1(1), 
	pence_2(2), 
	pence_5(5), 
	pence_10(10), 
	pence_20(20), 
	pence_50(50), 
	pound_1(100), 
	pound_2(200), 
	pound_5(500), 
	pound_10(1000), 
	pound_20(2000), 
	pound_50(5000);

	// This is the instance variable that holds the int value
	// for the pence equivalent
	private int value;

	// This is the constructor that gets called from the above
	// enumeration statements. So pence_1(1) is a bit like
	// saying new UKDenomination(1). Notice that it's private
	// so it can only be called within this class. In fact 12
	// objects are created.
	private UKDenomination(int v) {
		value = v;
	}

	/**
	 * Provides the pence equivalent for a UK denomination.
	 * e.g. £1 = 100 pence.
	 * @return The pence equivalent
	 */
	public int getValue() {
		return value;
	}

	/**
	 * A useful utility static method that runs on the enum rather than
	 * on a specific enum object. Returns the enum equivalent for a specific
	 * string version of the denomination, e.g. "£1" would return pound_1
	 * @param strValue The string equivalent to the enum
	 * @return The equivalent enum or null if there is no match
	 */
	public static UKDenomination fromString(String strValue) {
		UKDenomination result = null;
		if (strValue != null) {
			String val = strValue.trim().toLowerCase();
			for (UKDenomination denom : UKDenomination.values()) {
				if (val.equals(denom.toString())) {
					result = denom;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * The string representation of the enum, e.g. "£1" for pound_1
	 */
	public String toString() {
		String result;
		if (value < 100) {
			result = value + "p";
		} else {
			result = "£" + value/100;
		}
		return result;
	}

}
