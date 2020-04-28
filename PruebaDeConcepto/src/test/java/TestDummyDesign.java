import org.junit.Assert;
import org.junit.Test;

public class TestDummyDesign {
	
	DummyDesign dummyDesign = new DummyDesign();

	@Test
	public void testIntegrante1() {
		Assert.assertEquals(1, dummyDesign.integrante1());
	}
	@Test 
	public void testIntegrante2() {
		Assert.assertEquals(2,dummyDesign.integrante2());
	}
	@Test
	public void testIntegrante3() {
		Assert.assertEquals(3,dummyDesign.integrante3());
	}
	@Test
	public void testIntegrante4() {
		Assert.assertEquals(4, dummyDesign.integrante4());
	}
	@Test
	public void testIntegrante5() {
		Assert.assertEquals(5, dummyDesign.integrante5());
	}
}