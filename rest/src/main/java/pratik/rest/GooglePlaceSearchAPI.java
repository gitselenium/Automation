package pratik.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class GooglePlaceSearchAPI {

  public  void getRequestPlaceSearch()
  {
    RestAssured.baseURI="https://maps.googleapis.com"; 
    given()
    .param("location", "20.6503455,85.5972218")
    .param("radius", "500")
    .param("type", "restaurant") 
    .param("key", "AIzaSyBD13yiw9fOHTS5jto3tkiuorfYmzZAO6I")
    .when() 
    .get("/maps/api/place/nearbysearch/json").then().and().
    assertThat().statusCode(200).and().contentType(ContentType.JSON).and().and().and().and().
    body("results[0].name",equalTo("Hotel Indu"));

  }
  public String postRequestPlaceSearch()
  {

    RestAssured.baseURI="https://maps.googleapis.com"; 

    String placeId= given().queryParam("key", "AIzaSyBD13yiw9fOHTS5jto3tkiuorfYmzZAO6I").
        body("{"+
            "\"location\": {"+ 
            "\"lat\": 20.661636,"+
            "\"lng\": 85.577049 "+
            "},"+
            "\"accuracy\": 50,"+
            "\"name\": \"Pinku's Home\","+
            "\"phone_number\": \"(02) 9374 4000\","+
            "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
            "\"types\": [\"shoe_store\"],"+
            "\"website\": \"http://www.google.com.au/\","+
            "\"language\": \"en-AU\""+
            "} ").when().post("https://maps.googleapis.com/maps/api/place/add/json").body().jsonPath().getString("place_id");
     

    return placeId;

  } 

  public void deleteRequestPlaceSearch()  
  {  	 
    RestAssured.baseURI="https://maps.googleapis.com"; 
    given().
    queryParam("key", "AIzaSyBD13yiw9fOHTS5jto3tkiuorfYmzZAO6I").
    body("{"+"\"place_id\":\""+postRequestPlaceSearch()+"\"}") 
    .when().post("https://maps.googleapis.com/maps/api/place/delete/json").andReturn().then().body("status",equalTo("OK"));

  }

}
