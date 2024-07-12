package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> originalList = generateRandomList(100, 101);
        List<Integer> copyList = new ArrayList<>(originalList);

        int randomIndex = new Random().nextInt(originalList.size());
        int removedElement = copyList.remove(randomIndex);

        int missingElement = findMissingElementByHashMap(originalList, copyList);
        System.out.println("Removed element: " + removedElement);
        System.out.println("Missing element: " + missingElement);
    }

    private static List<Integer> generateRandomList(int size, int bound) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(bound));
        }
        return list;
    }

    private static int findMissingElementByHashMap(List<Integer> originalList, List<Integer> copyList) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : originalList) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        for (int num : copyList) {
            countMap.put(num, countMap.get(num) - 1);
            if (countMap.get(num) == 0) {
                countMap.remove(num);
            }
        }
        return countMap.keySet().iterator().next();
    }
}
