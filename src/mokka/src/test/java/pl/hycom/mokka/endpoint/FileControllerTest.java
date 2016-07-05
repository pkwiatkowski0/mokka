package pl.hycom.mokka.endpoint;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.WebIntegrationTest;
import pl.hycom.mokka.AbstractTest;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.expect;

/**
 * @author Mariusz Krysztofowicz (mariusz.krysztofowicz@hycom.pl)
 */
@WebIntegrationTest(randomPort = true)
public class FileControllerTest extends AbstractTest {
    private static final String FILE_NAME = "file.txt";
    private static final String FILE_ID = "file";
    private static final String FILE_EXT = "txt";

    @Value("${local.server.port}")
    protected int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void testFetchFile() throws IOException {
        expect().statusCode(200)
                .when()
                .get("/files/samplefile?ext=txt");
    }


    @Test
    public void testFetchFileHttpNotFound() throws IOException {
        expect().statusCode(404)
                .when()
                .get("/files/mozilladd?ext=pdf");
    }

}