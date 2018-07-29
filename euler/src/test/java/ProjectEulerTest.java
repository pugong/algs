import org.junit.Test;

/**
 * Created by lpug on 20/09/2017.
 */
public class ProjectEulerTest {

    @Test
    public void testCountPrime() throws Exception {

    }

    @Test
    public void testIsPrime() throws Exception {
        ProjectEuler euler = new ProjectEuler();
        assert (!euler.isPrime(10));
        assert (euler.isPrime(3));
        assert (euler.isPrime(11));
        assert (!euler.isPrime(12));

    }
}