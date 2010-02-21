

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author fjfdszj
 *
 */
public class Cmpp2ApiTest extends TestCase {
	
	Cmpp2Api testapi=new Cmpp2Api();

	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
		System.out.print("start\r");
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected void tearDown() throws Exception {
		System.out.print("end\r");
	}
	
	public void testCmpp2Api()
	{
		assertEquals(6, 6);
	}
	
	public void testCmppSend()
	{
//		List<SmsBean> cmppList=new ArrayList();
//		assertEquals(testapi.CmppSend(cmppList),1);
	}
    // main()·½·¨¡£
    public static void main(String[] args) {
       junit.swingui.TestRunner.run(Cmpp2ApiTest.class);
    }
}
