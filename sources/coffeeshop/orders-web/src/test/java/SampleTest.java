import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SampleTest{
    @Test
    public void helloTest(){
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("hello"));

    }
}
