package com.example;

import com.example.data.DataStore;
import com.example.data.InMemoryConnector;
import com.example.transfer.Transfer;
import com.example.transfer.TransferService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataStore<String> source = new InMemoryConnector<>(
                List.of("Alice", "Bob", "Charlie"),
                true,
                false
        );

        DataStore<String> target = new InMemoryConnector<>(
                List.of(),
                false,
                true
        );

        Transfer<String> transfer = new Transfer<>(source, target);
        TransferService service = new TransferService();

		int count = 0;
	    try {
			count = service.execute(transfer);
	    } catch (Exception e) {
			throw new RuntimeException(e);
		}

		System.out.println("Transferred elements: " + count);
    }
}
