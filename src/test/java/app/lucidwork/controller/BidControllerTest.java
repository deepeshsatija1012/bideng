package app.lucidwork.controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import bidengine.app.request.CreateBidItemRequest;
import bidengine.app.request.User;
import bidengine.app.response.CreateBidItemResponse;
import bidengine.app.response.CreateUserResponse;

public class BidControllerTest {
	
	
	public CreateUserResponse createUser() throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080//bidengine/webapi/bidservices/user");
	    User req = new User();
	    req.setPassword("deepesh");
	    req.setUsername("deepesh");
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.writeValueAsString(req);
	    String json = objectMapper.writeValueAsString(req);
	    System.out.println("JSON : " + json);
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	 
	    CloseableHttpResponse response = client.execute(httpPost);
	    String responseJson = EntityUtils.toString(response.getEntity());
	    System.out.println("Resp : " + responseJson);
	    CreateUserResponse resp = objectMapper.readValue(responseJson, CreateUserResponse.class);
	    client.close();
	    return resp;
	}
	
	@Test
	public void bidservices_user() 
	  throws ClientProtocolException, IOException {
		CreateUserResponse resp = createUser();
		Assert.assertTrue(resp.getCode()==0 || resp.getCode()==1);
		System.out.println("String User Id : " + resp.getUserId());
	}
	
	@Test
	public void bidservices_createbid() 
	  throws ClientProtocolException, IOException {
		CreateUserResponse userResp =createUser();
	    CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080//bidengine/webapi/bidservices/biditem");
	    CreateBidItemRequest req = new CreateBidItemRequest();
	    req.setItemDescription("ABC"); req.setItemName("FireStick"); req.setMinBid(2500d);
	    req.setCreatedByUser(userResp.getUserId());
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.writeValueAsString(req);
	    String json = objectMapper.writeValueAsString(req);
	    System.out.println("JSON : " + json);
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	 
	    CloseableHttpResponse response = client.execute(httpPost);
	    Assert.assertTrue(response.getStatusLine().getStatusCode()==200 );
	    String jsonResponse = EntityUtils.toString(response.getEntity());
	    System.out.println("Resp : " +jsonResponse);
	    CreateBidItemResponse resp = objectMapper.readValue(jsonResponse, CreateBidItemResponse.class);
	    Assert.assertTrue(resp.getCode()==0);
	    System.out.println("Resp Object : " +resp);
	    client.close();
	}
}
