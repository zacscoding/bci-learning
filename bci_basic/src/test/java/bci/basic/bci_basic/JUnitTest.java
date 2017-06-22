package bci.basic.bci_basic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class JUnitTest {
	String value;
	
	@Before
	public void setUp() {
		value = "test!";
	}
	
	@Test
	public void junitTest() {		
		assertThat(value, is("test!"));
	}
}
