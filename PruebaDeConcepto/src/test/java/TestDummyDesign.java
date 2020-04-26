import org.junit.Assert;
import org.junit.Test;

public class TestDummyDesign {
	DummyDesign dummyDesing = new DummyDesign();
	
	@Test
	public void testIntegrante4() {
		Assert.assertEquals(4, dummyDesing.integrante4());
	}
}
