package com.mem.xls;

public interface RowItemReader<T> {
	boolean hasNext();
	T next();
}
