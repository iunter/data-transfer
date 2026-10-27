package com.example.transfer;

import com.example.data.InMemoryConnector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TransferServiceTest {

	@Test
	public void transfersAllElements() throws Exception {
		var source = new InMemoryConnector<>(
				List.of("Alice", "Bob", "Charlie"),
				true,
				false
		);

		var target = new InMemoryConnector<String>(
				List.of(),
				true,
				true
		);

		var transfer = new Transfer<>(source, target);
		var service = new TransferService();

		int count = service.execute(transfer);

		assertEquals(3, count);
		assertEquals(
				List.of("Alice", "Bob", "Charlie"),
				target.read());
	}

	@Test
	public void transfersZeroElementsWhenSourceIsEmpty() throws Exception {
		var source = new InMemoryConnector<String>(
				List.of(),
				true,
				false
		);

		var target = new InMemoryConnector<String>(
				List.of(),
				true,
				true
		);

		var transfer = new Transfer<>(source, target);
		var service = new TransferService();

		int count = service.execute(transfer);

		assertEquals(0, count);
		assertEquals(List.of(), target.read());
	}

	@Test
	public void propagatesSourceReadFailure() {
		var source = new InMemoryConnector<String>(
				List.of(),
				true,
				false
		) {
			@Override
			public List<String> read() throws Exception {
				throw new Exception("Source failed");
			}
		};

		var target = new InMemoryConnector<String>(
				List.of(),
				false,
				true
		);

		var transfer = new Transfer<>(source, target);
		var service = new TransferService();

		Exception exception = assertThrows(
				Exception.class,
				() -> service.execute(transfer)
		);

		assertEquals("Source failed", exception.getMessage());
	}

	@Test
	public void propagatesTargetWriteFailure() {
		var source = new InMemoryConnector<>(
				List.of("Alice"),
				true,
				false
		);

		var target = new InMemoryConnector<String>(
				List.of(),
				true,
				true
		) {
			@Override
			public void write(String element) throws Exception {
				throw new Exception("Target failed");
			}
		};

		var transfer = new Transfer<>(source, target);
		var service = new TransferService();

		Exception exception = assertThrows(
				Exception.class,
				() -> service.execute(transfer)
		);

		assertEquals("Target failed", exception.getMessage());
	}

	@Test
	public void rejectsSourceThatCannotRead() {
		var source = new InMemoryConnector<String>(
				List.of(),
				false,
				true
		);

		var target = new InMemoryConnector<String>(
				List.of(),
				false,
				true
		);

		assertThrows(
				IllegalArgumentException.class,
				() -> new Transfer<>(source, target)
		);
	}
}
