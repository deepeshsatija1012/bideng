package bidengine.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bidengine.app.entity.BidItemObject;
import bidengine.app.entity.UserObject;
import bidengine.app.request.CreateBidItemRequest;
import bidengine.app.request.User;
import bidengine.app.response.CreateBidItemResponse;
import bidengine.app.response.CreateUserResponse;
import bidengine.app.response.status.UserResponseStatusCodes;
import bidengine.app.services.bid.items.BidItemsDao;
import bidengine.app.services.user.UserDao;

@RestController
@RequestMapping("/bidengine/webapi/bidservices")
public class BidengineController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BidItemsDao bidItemDao;
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public CreateUserResponse createUser(@RequestBody User request) {
		CreateUserResponse response = new CreateUserResponse();
		try {
			UserObject user = userDao.tryAndCreateNewUser(request.getUsername(), request.getPassword());
			response.setUserId(user.getUserid());
			response.setUsername(user.getUsername());
		}catch(IllegalArgumentException e) {
			UserObject user = userDao.findByUsername(request.getUsername());
			response.setUserId(user.getUserid());response.setUsername(user.getUsername());
			response.setCode(UserResponseStatusCodes.USER_ALREADY_EXISTS.code());
			response.setMessgage(UserResponseStatusCodes.USER_ALREADY_EXISTS.message());
		}
		return response;
	}

	@RequestMapping(value="/biditem", method=RequestMethod.POST)
	public CreateBidItemResponse createBidItem(@RequestBody CreateBidItemRequest request) {
		CreateBidItemResponse response = new CreateBidItemResponse();
		response.setItemName(request.getItemName());
		response.setItemDescription(request.getItemDescription());
		response.setCreatedByUser(request.getCreatedByUser());
		response.setMinBid(request.getMinBid());
		if(userDao.findByUserid(request.getCreatedByUser())==null) {
			response.setCode(UserResponseStatusCodes.USER_DOES_NOT_EXISTS.code());
			response.setMessgage(UserResponseStatusCodes.USER_DOES_NOT_EXISTS.message());
		}else {
			BidItemObject bidItem = bidItemDao.createBidItem(request);
			response.setItemid(bidItem.getItemId());
		}
		return response;
	}
}
