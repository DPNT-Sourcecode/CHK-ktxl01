package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.*;

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
        priceMap.put('F', 10);
        priceMap.put('G', 20);
        priceMap.put('H', 10);
        priceMap.put('I', 35);
        priceMap.put('J', 60);
        priceMap.put('K', 70);
        priceMap.put('L', 90);
        priceMap.put('M', 15);
        priceMap.put('N', 40);
        priceMap.put('O', 10);
        priceMap.put('P', 50);
        priceMap.put('Q', 30);
        priceMap.put('R', 50);
        priceMap.put('S', 20);
        priceMap.put('T', 20);
        priceMap.put('U', 40);
        priceMap.put('V', 50);
        priceMap.put('W', 20);
        priceMap.put('X', 17);
        priceMap.put('Y', 20);
        priceMap.put('Z', 21);

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

        int numF = count.getOrDefault('F', 0);
        int freeF = numF / 3;
        int payF = numF - freeF;
        count.put('F', payF);

        int numN = count.getOrDefault('N', 0);
        int numM = count.getOrDefault('M', 0);
        int freeM = numN / 3;
        count.put('M', Math.max(0, numM - freeM));

        int numR = count.getOrDefault('R', 0);
        int numQ = count.getOrDefault('Q', 0);
        int freeQ = numR / 3;
        count.put('Q', Math.max(0, numQ - freeQ));

        int numU = count.getOrDefault('U', 0);
        int freeU = numU / 4;
        count.put('U', numU - freeU);

        int total = 0;

        List<Character> groupItems = Arrays.asList('S', 'T', 'X', 'Y', 'Z');
        List<Character> allGroupSKU = new ArrayList<>();
        //List<Integer> groupPrices = new ArrayList<>();
        for(char c: groupItems) {
            int sum = count.getOrDefault(c, 0);
            for(int i = 0; i < sum; i++) {
                allGroupSKU.add(c);
            }
        }

        //groupPrices.sort(Collections.reverseOrder());
        if(!allGroupSKU.isEmpty()) {
            allGroupSKU.sort((a,b) -> priceMap.get(b) - priceMap.get(a));

            int groupTotal = 0;
            int totalGroupItems = allGroupSKU.size();
            int groupsOfThree = totalGroupItems / 3;

            groupTotal += groupsOfThree * 45;

            int leftover = totalGroupItems % 3;
            for(int i = 0; i < leftover; i++) {
                groupTotal += priceMap.get(allGroupSKU.get(groupsOfThree * 3 + i));
            }

            int discountCount = groupsOfThree * 3;
            for(int i = 0; i < discountCount; i++) {
                char sku = allGroupSKU.get(i);
                count.put(sku, count.get(sku) - 1);
            }

        }

//        int remainder = groupPrices.size() % 3;
//        for(int i = 0; i < remainder; i++) {
//            groupTotal += groupPrices.get(groupPrices.size() - 1 - i);
//        }
//
//        int remaining = groupPrices.size() % 3;
//        int remainingGroupItems = groupPrices.size() - remaining;
//        int toRemove = remainingGroupItems;
//
//        for(char c: groupItems) {
//            int current = count.getOrDefault(c, 0);
//            while(current > 0 && toRemove > 0) {
//                current--;
//                toRemove--;
//            }
//            count.put(c, current);
//        }
//
//        total += groupTotal;


        for(Map.Entry<Character, Integer> entry: count.entrySet()) {
            char item = entry.getKey();
            int quantity = entry.getValue();
            if(quantity <= 0) continue;

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
                case 'F':
                    total += quantity * 10;
                    break;
                case 'G':
                    total += quantity * 20;
                    break;
                case 'H':
                    total += (quantity / 10) * 80;
                    quantity %= 10;
                    total += (quantity / 5) * 45;
                    quantity %= 5;
                    total += quantity * 10;
                    break;
                case 'I':
                    total += quantity * 35;
                    break;
                case 'J':
                    total += quantity * 60;
                    break;
                case 'K':
                    total += (quantity / 2) * 120 + (quantity % 2) * 70;
                    break;
                case 'L':
                    total += quantity * 90;
                    break;
                case 'M':
                    total += quantity * 15;
                    break;
                case 'N':
                    total += quantity * 40;
                    break;
                case 'O':
                    total += quantity * 10;
                    break;
                case 'P':
                    total += (quantity / 5) * 200 + (quantity % 5) * 50;
                    break;
                case 'Q':
                    total += (quantity / 3) * 80 + (quantity % 3) * 30;
                    break;
                case 'R':
                    total += quantity * 50;
                    break;

                case 'S':
                case 'T':
                case 'X':
                case 'Y':
                case 'Z':
                    total += quantity * priceMap.get(item);
                    break;
                case 'U':
                    total += quantity * 40;
                    break;
                case 'V':
                    total += (quantity / 3) * 130;
                    quantity %= 3;
                    total += (quantity / 2) * 90;
                    quantity %= 2;
                    total += quantity * 50;
                    break;
                case 'W':
                    total += quantity * 20;
                    break;
                default:
                    total += quantity * priceMap.get(item);
                    return -1;
            }
        }

        return total;

        //throw new SolutionNotImplementedException();
    }
}



