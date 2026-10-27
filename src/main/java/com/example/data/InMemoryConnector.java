
package com.example.data;

import java.util.ArrayList;
import java.util.List;

public class InMemoryConnector<T> implements DataStore<T> {

	private final List<T> elements = new ArrayList<>();
	private final boolean readable;
	private final boolean writable;

	public InMemoryConnector(
			List<T> initialElements,
			boolean readable,
			boolean writable) {

		this.elements.addAll(initialElements);
		this.readable = readable;
		this.writable = writable;
	}

	@Override
	public List<T> read() throws Exception {
		if (!readable) {
			throw new UnsupportedOperationException(
					"This connector does not support reading");
		}

		return List.copyOf(elements);
	}

	@Override
	public void write(T element) throws Exception {
		if (!writable) {
			throw new UnsupportedOperationException(
					"This connector does not support writing");
		}

		elements.add(element);
	}

	@Override
	public boolean canRead() {
		return readable;
	}

	@Override
	public boolean canWrite() {
		return writable;
	}
}

