
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;


@QuarkusTest
public class SampleTest {
    @Test
    public void helloTest() {
        given()
                .when().get("/order")
                .then()
                .statusCode(200)
                .body(is("hello 12000"));

    }
}
