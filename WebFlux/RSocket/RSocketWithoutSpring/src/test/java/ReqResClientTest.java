import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.khasanof.ReqResClient;
import org.khasanof.Server;

/**
 * Author: Nurislom
 * <br/>
 * Date: 04.06.2023
 * <br/>
 * Time: 21:40
 * <br/>
 * Package: PACKAGE_NAME
 */
public class ReqResClientTest {

    @BeforeAll
    static void beforeAll() {
        new Server();
    }

    @Test
    void testRun() {
        ReqResClient reqResClient = new ReqResClient();
        String hello = "JL";

        Assertions.assertEquals("Hello JL", reqResClient.callBlocking(hello));

        reqResClient.dispose();
    }

}
