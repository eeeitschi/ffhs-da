package ch.ffhs.dua.hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import ch.ffhs.dua.hash.LLHashSet;
import ch.ffhs.dua.hash.LSHashSet;
import ch.ffhs.dua.hash.SetBasic;
import org.junit.Test;

public class HashSetTest
{

	@Test
	public void emptyLLHashSetTest()
	{
		SetBasic<Object> set = new LLHashSet<>();
		assertFalse(set.remove("ha"));
	}

	@Test
	public void emptyLSHashSetTest()
	{
		SetBasic<Object> set = new LSHashSet<>();
		assertEquals(set.size(), 0);
		assertFalse(set.remove("ha"));
		assertEquals(set.size(), 0);
		assertTrue(set.isEmpty());
	}

	@Test
	public void addRemoveIteratorLLHashSetTest()
	{
		SetBasic<String> set = new LLHashSet<>();
		testSetImpl(set);
	}

	@Test
	public void addRemoveIteratorLSHashSetTest()
	{
		SetBasic<String> set = new LSHashSet<>();
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
		assertEquals(numElementsToAdd, numElems.get());

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