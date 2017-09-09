package com.gojek.parking_lot.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Heap with ordering based on the specified {@link Comparator} and the given
 * max size.
 * 
 * @author vyasraj
 *
 * @param <T>
 *            type of value to be stored.
 */
public class MinHeap<T> {

	private final Comparator<T> comparator;
	private final int maxSize;

	private List<T> heap;

	public MinHeap(Comparator<T> comparator, int maxSize) {
		this.comparator = comparator;
		this.maxSize = maxSize;
		this.heap = new ArrayList<T>(maxSize);
	}

	public void add(T element) {
		heap.add(element);
		int current = heap.size() - 1;
		while (comparator.compare(heap.get(current), heap.get(parent(current))) < 0) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public T peek() {
		return heap.isEmpty() ? null : heap.get(0);
	}

	public T remove() {
		if (heap.isEmpty()) {
			return null;
		}

		T root = heap.remove(0);
		if (!heap.isEmpty()) {
			T last = heap.remove(heap.size() - 1);
			heap.add(0, last);
			maxHeapify(0);
		}
		return root;
	}

	public String display() {
		return heap.toString();
	}

	private void maxHeapify(int pos) {
		int swapPosition = pos;
		if (!isOutOfBound(leftChild(pos)) && comparator.compare(heap.get(pos), heap.get(leftChild(pos))) > 0) {
			swapPosition = leftChild(pos);
		}

		if (!isOutOfBound(rightChild(pos))
				&& comparator.compare(heap.get(swapPosition), heap.get(rightChild(pos))) > 0) {
			swapPosition = rightChild(pos);
		}

		if (swapPosition != pos) {
			swap(pos, swapPosition);
			maxHeapify(swapPosition);
		}
	}

	private boolean isOutOfBound(int index) {
		if (index >= heap.size()) {
			return true;
		} else {
			return false;
		}
	}

	private void swap(int index1, int index2) {
		T tmp;
		tmp = heap.get(index1);
		heap.set(index1, heap.get(index2));
		heap.set(index2, tmp);
	}

	private int parent(int index) {
		return (index - 1) / 2;
	}

	private int leftChild(int index) {
		return 2 * index + 1;
	}

	private int rightChild(int index) {
		return 2 * index + 2;
	}

}
