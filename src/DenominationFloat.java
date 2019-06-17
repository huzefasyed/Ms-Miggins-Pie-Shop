
/**
 * Represents a float of a particular money denomination
 * @author Mrs Miggins
 * @version February 2015
 * DenominationFloat will hold a name ("pound"),
 *                  a value (100)
 *                  and a quantity (33)
 */

public class DenominationFloat {
	private UKDenomination name;
	
	private int quantity;
	
	public DenominationFloat(UKDenomination n){
		this(n, 0);
	}
	
	public DenominationFloat(UKDenomination n, int q){
		name = n;
		quantity = q;
	}
	
	public UKDenomination getType() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DenominationFloat other = (DenominationFloat) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return quantity + " x " + name;
	}
	
}