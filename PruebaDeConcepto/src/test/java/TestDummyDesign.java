import org.junit.Assert;
import org.junit.Test;

public class TestDummyDesign {
	DummyDesign dummyDesing = new DummyDesign();

	@Test
	public void testIntegrante1() {
		Assert.assertEquals(1, dummyDesing.integrante1());
	}
	@Test 
	public void testIntegrante2() {
		Assert.assertEquals(2,dummyDesing.integrante2());
	}
	@Test
	public void testIntegrante4() {
		Assert.assertEquals(4, dummyDesing.integrante4());
	}
	
	@Test
	public void testIntegrante5() {
		Assert.assertEquals(5, dummyDesing.integrante5());
	}
}
