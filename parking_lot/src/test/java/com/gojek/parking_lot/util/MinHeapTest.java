package com.gojek.parking_lot.util;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

public class MinHeapTest {

	@Test
	public void testMinHeap() {
		MinHeap<Integer> heap = new MinHeap<Integer>(new Comparator<Integer>() {

			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		}, 10);
		heap.add(1);
		heap.add(7);
		heap.add(3);
		heap.add(6);
		heap.add(5);
		heap.add(4);
		heap.add(8);
		Assert.assertEquals("[1, 5, 3, 7, 6, 4, 8]", heap.display());
		heap.add(2);
		Assert.assertEquals("[1, 2, 3, 5, 6, 4, 8, 7]", heap.display());

		Assert.assertEquals(Integer.valueOf(1), heap.remove());
		Assert.assertEquals("[2, 5, 3, 7, 6, 4, 8]", heap.display());
		Assert.assertEquals(Integer.valueOf(2), heap.remove());
		Assert.assertEquals("[3, 5, 4, 7, 6, 8]", heap.display());
	}

	@Test
	public void testMinHeapWhenAllElementsOfTheHeapAreRemoved() {
		MinHeap<Integer> heap = new MinHeap<Integer>(new Comparator<Integer>() {

			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		}, 10);
		heap.add(1);
		heap.add(7);
		heap.add(3);
		heap.add(6);
		heap.add(5);
		heap.add(4);
		heap.add(8);
		Assert.assertEquals(Integer.valueOf(1), heap.remove());
		Assert.assertEquals(Integer.valueOf(3), heap.remove());
		Assert.assertEquals(Integer.valueOf(4), heap.remove());
		Assert.assertEquals(Integer.valueOf(5), heap.remove());
		Assert.assertEquals(Integer.valueOf(6), heap.remove());
		Assert.assertEquals(Integer.valueOf(7), heap.remove());
		Assert.assertEquals(Integer.valueOf(8), heap.remove());
		Assert.assertNull(heap.remove());
	}
}
