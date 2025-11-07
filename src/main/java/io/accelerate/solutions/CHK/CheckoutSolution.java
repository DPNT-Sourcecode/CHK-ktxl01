package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if(skus == null) {
            return -1;
        }

        Map<Character, Integer> priceMap = new HashMap<>();
        priceMap.put('A', 50);
        priceMap.put('B', 30);
        priceMap.put('C', 20);
        priceMap.put('D', 15);

        Map<Character, Integer> count = new HashMap<>();
        for(char c: skus.toCharArray()) {
            if(!priceMap.containsKey(c)) {
                return -1;
            }
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        int total = 0;

        for(Map.Entry<Character, Integer> entry: count.entrySet()) {
            char item = entry.getKey();
            int quantity = entry.getValue();

            switch (item) {
                case 'A':
                    total += (quantity / 3) * 130 + (quantity % 3) * 50;
                    break;
                case 'B':
                    total += (quantity / 2) * 45 + (quantity % 2) * 30;
                    break;
                case 'C':
                    total += quantity * 20;
                    break;
                case 'D':
                    total += quantity * 15;
                    break;
            }
        }

        return total;

        //throw new SolutionNotImplementedException();
    }
}

