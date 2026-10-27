package com.example.transfer;

import com.example.data.DataStore;

import java.util.Objects;

public class Transfer<T> {

	private final DataStore<T> source;
	private final DataStore<T> target;

	public Transfer(DataStore<T> source, DataStore<T> target) {
		this.source = Objects.requireNonNull(source, "Source cannot be null");
		this.target = Objects.requireNonNull(target, "Target cannot be null");

		if (!source.canRead()) {
			throw new IllegalArgumentException(
					"Source must support reading");
		}

		if (!target.canWrite()) {
			throw new IllegalArgumentException(
					"Target must support writing");
		}
	}

	public DataStore<T> getSource() {
		return source;
	}

	public DataStore<T> getTarget() {
		return target;
	}
}
