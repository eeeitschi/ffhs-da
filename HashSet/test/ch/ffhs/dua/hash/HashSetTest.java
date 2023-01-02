package ch.ffhs.dua.hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class HashSetTest 
{

	@Test
	public void emptyLLHashSetTest()
	{
		ch.ffhs.dua.hash.SetBasic<Object> set = new ch.ffhs.dua.hash.LLHashSet<>();
		assertFalse(set.remove("ha"));
	}
	
	@Test
	public void emptyLSHashSetTest()
	{
		ch.ffhs.dua.hash.SetBasic<Object> set = new ch.ffhs.dua.hash.LSHashSet<>();
		assertEquals(set.size(), 0);
		assertFalse(set.remove("ha"));
		assertEquals(set.size(), 0);
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void testHashSetWithPerson() {
		Person p1 = new Person("Hans", "Muster");
		Person p2 = new Person("Bert", "Hammer");
		Person p3 = new Person("Bert", "Berger");
		
		Person p1Duplicate = new Person("Hans", "Muster");
		
		Set<Person> set = new HashSet<>();
		assertTrue(set.add(p1));
		assertTrue(set.add(p2));
		assertTrue(set.add(p3));
		assertTrue(!set.add(p1Duplicate));
		assertEquals(3, set.size());
		
		assertTrue(set.contains(p1));
		assertTrue(set.contains(p1Duplicate));
		
		assertTrue(set.remove(p1));
		assertTrue(!set.remove(p1Duplicate));
		
		assertEquals(2, set.size());
		
		Iterator<Person> ip = set.iterator();
		int num = 0;
		while (ip.hasNext()) {
			Person p = ip.next();
			assertTrue(p.getName().equals("Hammer") || p.getName().equals("Berger"));
			num++;
		}
		assertEquals(2, num);
		
		assertTrue(set.remove(p2));
		assertEquals(1, set.size());
		assertTrue(set.remove(p3));
		assertEquals(0, set.size());
		
	}
	
	@Test
	public void addRemoveIteratorLLHashSetTest()
	{
		ch.ffhs.dua.hash.SetBasic<String> set = new ch.ffhs.dua.hash.LLHashSet<>();
		testSetImpl(set);
	}
	
	@Test
	public void addRemoveIteratorLSHashSetTest()
	{
		ch.ffhs.dua.hash.SetBasic<String> set = new ch.ffhs.dua.hash.LSHashSet<>();
		// Set<String> set = new HashSet<>();
		testSetImpl(set);
	}
	
	private void testSetImpl(Set<String> set) {
		int numElementsToAdd = 40;
		List<String> elems = new ArrayList<>();
		for (int test = 0; test < numElementsToAdd; test++) {
			String str = generateRandomString();
			elems.add(str);
			assertTrue(set.add(str));
		}
		
		assertEquals(set.size(), elems.size());
		assertEquals(set.size(), numElementsToAdd);
		
		final AtomicInteger numElems = new AtomicInteger(0);
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String val = iter.next();
			System.out.println(val);
			numElems.incrementAndGet();
		}
		//assertEquals(numElementsToAdd, numElems.get());
		
		int numTests = 6;
		List<Integer> indices = new ArrayList<>();
		int numRemoved = 0;
		Random r = new Random();
		for (int test = 0; test < numTests; test++) {
			int index = r.nextInt(numTests);
			boolean removed = set.remove(elems.get(index));
			if (removed) {
				numRemoved++;
				indices.add(index);
			} else {
				assertTrue(indices.contains(index));
			}
		}
		
		numElems.set(0);
		iter = set.iterator();
		while (iter.hasNext()) {
			iter.next();
			numElems.incrementAndGet();
		}		
		assertEquals(numElementsToAdd - numRemoved, numElems.get());
	}
	
	private String generateRandomString() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
	
}
