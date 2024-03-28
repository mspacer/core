package com.core.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> org.apache.commons.collections4
 */
public class CollectionUtilTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        CollectionUtils.addAll(list, 1, 2, 3, 4, 10, 22, 45, 666);

        CollectionUtils.filter(list, object -> object > 10);
        System.out.println(list);
    }
}
