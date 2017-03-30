import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


class Test1 extends TestCase{
    public void testEqual(){
        int a = 3;
        assert (a == 3);
    }
}

class Test2 extends TestCase{
    public void testEqual(){
        int a = 3;
        assert (a == 3);
    }
}

@RunWith(Suite.class)
@Suite.SuiteClasses({Test1.class, Test2.class})
public class SuiteDemoTest {
}
