package com.example.data;

import java.util.List;

public interface DataStore<T> {

	List<T> read() throws Exception;
	void write(T element) throws Exception;

	boolean canRead();
	boolean canWrite();
}
