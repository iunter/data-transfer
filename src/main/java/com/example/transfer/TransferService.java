package com.example.transfer;

import java.util.List;

public class TransferService {

	public <T> int execute(Transfer<T> transfer) throws Exception {
		List<T> elements = transfer.getSource().read();

		int count = 0;

		for (T element : elements) {
			transfer.getTarget().write(element);
			count++;
		}

		return count;
	}
}
