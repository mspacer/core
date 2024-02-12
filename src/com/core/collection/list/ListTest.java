package com.core.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        final List<String> bonds = Arrays.asList("BOND_GOS_REG_MAIN", "BOND_GOS_REG_ADD", "BOND_ID_MAIN", "BOND_ID_ADD");
        final List<String> programs = Collections.singletonList("BOND_PROG");

        if (bonds.contains("BOND_ID_MAIN")) {
            System.out.println(bonds.indexOf("BOND_ID_MAIN"));
        }
        if (!programs.contains("BOND_ID_MAIN")) {
            System.out.println("not contains");
        }

        List<String> annulNums = new ArrayList();
        String[] nums = "01-10602-Z; 05-10602-Z; 06-10602-Z ;08-10602-Z".split(";");
        for (String num : nums) {
            if (!num.contains("06-10602-Z")) {
                annulNums.add(num);
            }
        }
        System.out.println(annulNums);

        List<String> annulNums2 = new ArrayList();
       // annulNums2.addAll(Collections.singletonList(nums));
    }
}
