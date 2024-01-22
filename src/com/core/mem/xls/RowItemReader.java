package com.core.mem.xls;

public interface RowItemReader<T> {
	boolean hasNext();
	T next();
}
