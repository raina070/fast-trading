/**
 * 
 */
package es.us.lsi.tdg.fast.core.dataModel;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

/**
 * @author José Antonio
 *
 */
public class AbstractDataComponentTest extends TestCase {

	/**
	 * Test method for {@link es.us.lsi.tdg.fast.core.dataModel.AbstractDataComponent#AbstractDatalComponent()}.
	 */
	public void testAbstractDatalComponent() {
		
	}

	/**
	 * Test method for {@link es.us.lsi.tdg.fast.core.dataModel.AbstractDataComponent#propertiesCount()}.
	 */
	public void testPropertiesCount() {
		AbstractDataComponent dc=new AbstractDataComponent();
		assertEquals(dc.propertiesCount(), 0);
		dc.set("test", "test");
		assertEquals(dc.propertiesCount(),1);
		dc.set("test2", "test2");
		assertEquals(dc.propertiesCount(),2);
		dc.delete("test2");
		assertEquals(dc.propertiesCount(),1);
		dc.delete("test");
		assertEquals(dc.propertiesCount(), 0);
	}

	/**
	 * Test method for {@link es.us.lsi.tdg.fast.core.dataModel.AbstractDataComponent#propertyNames()}.
	 */
	public void testPropertyNames() {
		AbstractDataComponent dc=new AbstractDataComponent();
		Set<String> expectedValue=new HashSet<String>();
		assertEquals(dc.propertyNames(),expectedValue);
		String testName="test";
		dc.set(testName, testName);
		expectedValue.add(testName);
		assertEquals(dc.propertyNames(), expectedValue);
		String testName2="test2";
		dc.set(testName2, testName2);
		expectedValue.add(testName2);
		assertEquals(dc.propertyNames(), expectedValue);
		dc.delete(testName);
		expectedValue.remove(testName);
		assertEquals(dc.propertyNames(), expectedValue);
		dc.delete(testName2);
		expectedValue.remove(testName2);
		assertEquals(dc.propertyNames(), expectedValue);
	}

	/**
	 * Test method for {@link es.us.lsi.tdg.fast.core.dataModel.AbstractDataComponent#isProperty(java.lang.String)}.
	 */
	public void testIsProperty() {
		AbstractDataComponent dc=new AbstractDataComponent();
		String testName="test";
		assertEquals(dc.isProperty(testName),false);
		dc.set(testName, testName);
		assertEquals(dc.isProperty(testName),true);
		String testName2="test2";
		assertEquals(dc.isProperty(testName2),false);
		dc.set(testName2, testName2);
		assertEquals(dc.isProperty(testName2),true);
		dc.delete(testName2);
		assertEquals(dc.isProperty(testName2),false);
		assertEquals(dc.isProperty(testName),true);
		dc.delete(testName);
		assertEquals(dc.isProperty(testName2),false);
	}

	/**
	 * Test method for {@link es.us.lsi.tdg.fast.core.dataModel.AbstractDataComponent#get(java.lang.String)}.
	 */
	public void testGet() {
		AbstractDataComponent dc=new AbstractDataComponent();
		String testName="test";
		assertEquals(dc.get(testName),null);
		dc.set(testName, testName);
		assertEquals(dc.get(testName),testName);
		String testName2="test2";
		assertEquals(dc.get(testName2),null);
		dc.set(testName2, testName2);
		assertEquals(dc.get(testName2),testName2);
		dc.set(testName,testName2);
		assertEquals(dc.get(testName2),testName2);
		assertEquals(dc.get(testName),testName2);
		dc.delete(testName);
		assertEquals(dc.isProperty(testName),null);
	}

	/**
	 * Test method for {@link es.us.lsi.tdg.fast.core.dataModel.AbstractDataComponent#getAsString(java.lang.String)}.
	 */
	public void testGetAsString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link es.us.lsi.tdg.fast.core.dataModel.AbstractDataComponent#set(java.lang.String, java.lang.Object)}.
	 */
	public void testSet() {
		AbstractDataComponent dc=new AbstractDataComponent();
		String testName="test";
		assertEquals(dc.get(testName),null);
		dc.set(testName, testName);
		assertEquals(dc.get(testName),testName);
		String testName2="test2";
		assertEquals(dc.get(testName2),null);
		dc.set(testName2, testName2);
		assertEquals(dc.get(testName2),testName2);
		dc.set(testName,testName2);
		assertEquals(dc.get(testName2),testName2);
		assertEquals(dc.get(testName),testName2);
		dc.delete(testName);
		assertEquals(dc.isProperty(testName),null);
	}

	/**
	 * Test method for {@link es.us.lsi.tdg.fast.core.dataModel.AbstractDataComponent#delete(java.lang.String)}.
	 */
	public void testDelete() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	public void testToString() {
		fail("Not yet implemented");
	}

}
