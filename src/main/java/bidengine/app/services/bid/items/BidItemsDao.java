package bidengine.app.services.bid.items;

import bidengine.app.entity.BidItemObject;
import bidengine.app.request.CreateBidItemRequest;

public interface BidItemsDao {
	
	BidItemObject createBidItem(CreateBidItemRequest item);
}
