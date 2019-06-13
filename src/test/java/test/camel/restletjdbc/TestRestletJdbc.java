package test.camel.restletjdbc;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class TestRestletJdbc extends BaseTest {
    /*
    Список тестов:
    1. получение списка всех persons
    2. добавление person
    3. изменение person
    4. получение инфо по конкретному человеку
    5. удаление person

    * */


    /* 1. Получение списка всех */
    @Test
    public void getAllPersons() {
        Response response = given()
                .when()
                .get("/persons");

        given()
                .contentType(ContentType.TEXT)
                .when()
                .get("/persons")
                .then()
                .statusCode(200);

        response.prettyPrint();

    }

    @Test
    public void addPerson(){
        /*
        * add person and check that server response is correct
         */
        String firstName = String.format("firstName%s", (int) (Math.random() * 1000));
        String lastName = String.format("lastName%s", (int) (Math.random() * 1000));

        String expectedAnswer = MessageFormat.format("FIRSTNAME={0}, LASTNAME={1}}]", firstName, lastName);

//        given()
//                .when()
//                .param("firstName", firstName)
//                .param("lastName", lastName)
////                .log().all()
//                .post("/persons")
//                .then()
//                .statusCode(200);

        Response response = given()
                .when()
                .param("firstName", firstName)
                .param("lastName", lastName)
//                .log().all()
                .post("/persons");

        assert(response.statusCode() == 200);

        String actualAnswer = response.prettyPrint();
        assert(actualAnswer.contains(expectedAnswer));
        System.out.println("|" + actualAnswer + "|");
        System.out.println("|" + expectedAnswer + "|");
    }


    @Test
    public void updateExistingPerson(){
        /*
         * add person and check that server response is correct
         */
        String firstName = String.format("firstName%s", (int) (Math.random() * 1000));
        String lastName = String.format("lastName%s", (int) (Math.random() * 1000));

        String expectedAnswer = MessageFormat.format("FIRSTNAME={0}, LASTNAME={1}}]", firstName, lastName);

//        given()
//                .when()
//                .param("firstName", firstName)
//                .param("lastName", lastName)
////                .log().all()
//                .put("/persons/5")
//                .then()
//                .statusCode(200);

        Response response = given()
                .when()
                .param("firstName", firstName)
                .param("lastName", lastName)
                .log().all()
                .put("/persons/5");

        //assert(response.statusCode() == 200);
        String actualAnswer = response.prettyPrint();
       // assert(actualAnswer.contains(expectedAnswer));
        System.out.println("|" + actualAnswer + "|");
        System.out.println("|" + expectedAnswer + "|");
    }


    @Test
    public void getPersonInfo(){
        /*
        * getting person info
         */
        Response r = given()
                .when()
                .get("/persons/1");
        assert(r.statusCode() == 200);
        r.prettyPrint();
    }

    @Test
    public void deletePerson(){
        Response r = given()
                .when()
                .delete("/persons/5");
        r.prettyPrint();
    }
}
