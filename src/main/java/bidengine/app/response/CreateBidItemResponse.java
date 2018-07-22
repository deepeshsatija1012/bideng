package bidengine.app.response;

public class CreateBidItemResponse extends Response {
	private String itemName;
	private String itemDescription;
	private String createdByUser;
	private double minBid;
	private String itemid;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}
	public double getMinBid() {
		return minBid;
	}
	public void setMinBid(double minBid) {
		this.minBid = minBid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateBidItemResponse [itemName=").append(itemName).append(", itemDescription=")
				.append(itemDescription).append(", createdByUser=").append(createdByUser).append(", minBid=")
				.append(minBid).append(", itemid=").append(itemid).append("]");
		return builder.toString();
	}
}
