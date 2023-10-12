package restfulbooker.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static restfulbooker.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Specifications {

    public static RequestSpecification requestSpec = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);

    //CREATE TOKEN
    public static ResponseSpecification createTokenWithStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/create-token-response-schema.json"))
            .build();

    public static RequestSpecification createTokenRequestSpec = with()
            .log().uri()
            .log().method()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);
    public static ResponseSpecification createTokenWithBadCredentials = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/create-token-bad-credentials-response-schema.json"))
            .build();


    //GET BOOKING
    public static RequestSpecification getBookingRequestSpec = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);
    public static ResponseSpecification getBookingIdsWithStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/get-list-of-booking-id-schema.json"))
            .build();

    public static ResponseSpecification getBookingIdsWithStatusCode500 = new ResponseSpecBuilder()
            .expectStatusCode(500)
            .build();
    public static ResponseSpecification getBookingWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/get-booking-response-schema.json"))
            .build();
    public static ResponseSpecification getBookingWithStatusCode404 = new ResponseSpecBuilder()
            .expectContentType("text/plain; charset=utf-8")
            .expectStatusCode(404)
            .build();

    //CREATE BOOKING
    public static ResponseSpecification createBookingWithStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/create-booking-response-schema.json"))
            .build();

    public static ResponseSpecification createBookingWithEmptyData = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/empty-data-booking-response-schema.json"))
            .build();

    //UPDATE BOOKING
    public static ResponseSpecification updateBookingWithStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/update-booking-response-schema.json"))
            .build();
    public static ResponseSpecification partialUpdateBookingWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/patch-booking-response-schema.json"))
            .build();

    //DELETE BOOKING
    public static ResponseSpecification deleteBookingWithStatusCode201 = new ResponseSpecBuilder()
            .expectContentType("text/plain; charset=utf-8")
            .expectStatusCode(201)
            .build();
    public static ResponseSpecification deleteBookingWithStatusCode403 = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectContentType("text/plain; charset=utf-8")
            .expectStatusCode(403)
            .build();
}
