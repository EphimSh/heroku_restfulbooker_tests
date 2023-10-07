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
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);

    public static RequestSpecification getBookingRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);
    public static RequestSpecification createBookingRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);
    public static ResponseSpecification createBookingWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/create-booking-response-schema.json"))
            .build();
    public static ResponseSpecification createTokenWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/create-token-response-schema.json"))
            .build();

    public static ResponseSpecification getBookingIdsWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification getBookingWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/get-booking-response-schema.json"))
            .build();
    public static RequestSpecification updateBookingRequestSpec = with()
            .log().uri()
            .log().method()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);
    public static ResponseSpecification updateBookingWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/update-booking-response-schema.json"))
            .build();

    public static RequestSpecification partialUpdateBookingRequestSpec = with()
            .log().uri()
            .log().method()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);
    public static ResponseSpecification partialUpdateBookingWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("restfulbooker/schemas/patch-booking-response-schema.json"))
            .build();

    public static RequestSpecification deleteBookingRequestSpec = with()
            .log().uri()
            .log().method()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);
    public static ResponseSpecification deleteBookingWithStatusCode201 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectContentType("text/plain; charset=utf-8")
            .expectStatusCode(201)
            .build();
}
