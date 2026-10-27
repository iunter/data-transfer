# Generic Data Transfer

A small Java project built with SBT to demonstrate generic data stores, source and target connectors, and data transfers.

## Requirements

- Java 21
- SBT

## Run the example

```bash
sbt run
```

## Run tests

```bash
sbt test
```

## Overview

- `DataStore<T>` defines the generic data store contract.
- `InMemoryConnector<T>` provides an in-memory connector with configurable read and write capabilities.
- `Transfer<T>` defines a transfer between a source and a target.
- `TransferService` executes the transfer and returns the number of successfully written elements.