package bidengine.app.entity;

import java.util.concurrent.TimeUnit;

import bidengine.app.idgenerator.IdGenerator;

public class BidItemObject {
	
	private final String itemId = IdGenerator.getId(BidItemObject.class);
	private final String itemName;
	private final String itemDescription;
	private final String createdByUser;
	private final double minbid;
	private final long bidOpenTime = System.currentTimeMillis();
	private final long bidClosingTime = TimeUnit.HOURS.toMillis(1)+bidOpenTime;
	
	public BidItemObject(String itemName, String itemDescription, double minbid, String createdByUser) {
		super();
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.minbid = minbid;
		this.createdByUser = createdByUser;
	}

	public String getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public double getMinbid() {
		return minbid;
	}

	public long getBidOpenTime() {
		return bidOpenTime;
	}

	public long getBidClosingTime() {
		return bidClosingTime;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

}
