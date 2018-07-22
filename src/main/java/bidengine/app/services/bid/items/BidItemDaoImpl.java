package bidengine.app.services.bid.items;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import bidengine.app.entity.BidItemObject;
import bidengine.app.request.CreateBidItemRequest;

@Repository
public class BidItemDaoImpl implements BidItemsDao {
	private ConcurrentMap<String, BidItemObject> byId = new ConcurrentHashMap<>();

	@Override
	public BidItemObject createBidItem(CreateBidItemRequest item) {
		BidItemObject bidItemObject = new BidItemObject(item.getItemName(), item.getItemDescription(), item.getMinBid(), item.getCreatedByUser());
		byId.put(bidItemObject.getItemId(), bidItemObject);
		return bidItemObject;
	}

}
