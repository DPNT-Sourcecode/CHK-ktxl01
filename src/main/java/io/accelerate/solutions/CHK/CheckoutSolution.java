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
        priceMap.put('E', 40);

        Map<Character, Integer> count = new HashMap<>();
        for(char c: skus.toCharArray()) {
            if(!priceMap.containsKey(c)) {
                return -1;
            }
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        int numE = count.getOrDefault('E', 0);
        int numB = count.getOrDefault('B', 0);

        int freeB = numE / 2;
        int payB = Math.max(0, numB - freeB);
        count.put('B', payB);

        int total = 0;

        for(Map.Entry<Character, Integer> entry: count.entrySet()) {
            char item = entry.getKey();
            int quantity = entry.getValue();

            switch (item) {
                case 'A':
                    total += (quantity / 5) * 200;
                    quantity %= 5;
                    total += (quantity / 3) * 130;
                    quantity %= 3;
                    total += quantity * 50;
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
                case 'E':
                    total += quantity * 40;
                    break;
                default:
                    return -1;
            }
        }

        return total;

        //throw new SolutionNotImplementedException();
    }
}

