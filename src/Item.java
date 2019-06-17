/**
 * My first attempt at the Item class
 * @author Mrs Miggins
 * @version February 2015
 *
 * Item represents a single item type on sale and will probably have
 * a unique identifier, a name ("steak and kidney"), a cost (3.99) and a quantity (7)
 *
 */

public class Item {
	private String identifier;
	private String name;
	private int cost;
	private int quanity;
	
	public Item(String id){
		this(id, "", 0, 0);
	}
	
	public Item(String id, String n, int c){
		this(id, n, c, 0);
	}
	
	public Item(String id, String n, int c, int q){
		identifier = id;
		name = n;
		cost = c;
		quanity = q;
	}
	
	public String getIdentifier(){
		return identifier;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String n){
		name = n;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public void decreaseQuantity(){
		quanity--;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return identifier + ", " + name + ", " + cost + ", " + quanity;
	}
}