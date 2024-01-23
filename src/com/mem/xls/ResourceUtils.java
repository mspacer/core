package com.mem.xls;

import java.io.InputStream;

public class ResourceUtils {

	public static InputStream resourceInputStream(String localResource) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(localResource);
		if (is == null) throw new RuntimeException("Resource not found: " + localResource);
		return is;
	}
		
}
