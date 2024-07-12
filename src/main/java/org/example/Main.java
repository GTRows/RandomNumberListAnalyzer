package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> originalList = generateRandomList(100, 101);
        List<Integer> copyList = new ArrayList<>(originalList);

        int randomIndex = new Random().nextInt(originalList.size());
        int removedElement = copyList.remove(randomIndex);
        System.out.println("Removed element: " + removedElement);
        System.out.println("Removed element count: " + originalList.stream().filter(i -> i == removedElement).count());
        System.out.println();

        long sumDuration = measureMethodPerformance("Sum of the elements", Main::findMissingElementBySum, originalList, copyList);
        long sortingDuration = measureMethodPerformance("Sorting and comparing", Main::findMissingElementBySorting, originalList, copyList);
        long mapDuration = measureMethodPerformance("Using HashMap", Main::findMissingElementByHashMap, originalList, copyList);

        determineFastestMethod(sumDuration, sortingDuration, mapDuration);
    }

    private static List<Integer> generateRandomList(int size, int bound) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(bound));
        }
        return list;
    }

    private static long measureMethodPerformance(String methodName, MissingElementFinder method, List<Integer> originalList, List<Integer> copyList) {
        long startTime = System.nanoTime();
        int missingElement = method.findMissingElement(originalList, copyList);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(methodName + ":");
        System.out.println("Missing element: " + missingElement);
        System.out.println("Duration: " + duration + " ns");
        return duration;
    }

    private static void determineFastestMethod(long sumDuration, long sortingDuration, long mapDuration) {
        if (sumDuration < sortingDuration && sumDuration < mapDuration) {
            System.out.println("Sum of the elements is the fastest method");
        } else if (sortingDuration < sumDuration && sortingDuration < mapDuration) {
            System.out.println("Sorting and comparing is the fastest method");
        } else {
            System.out.println("Using HashMap is the fastest method");
        }
    }

    private static int findMissingElementBySum(List<Integer> originalList, List<Integer> copyList) {
        int originalSum = originalList.stream().mapToInt(Integer::intValue).sum();
        int copySum = copyList.stream().mapToInt(Integer::intValue).sum();
        return originalSum - copySum;
    }

    private static int findMissingElementBySorting(List<Integer> originalList, List<Integer> copyList) {
        Collections.sort(originalList);
        Collections.sort(copyList);
        for (int i = 0; i < copyList.size(); i++) {
            if (!originalList.get(i).equals(copyList.get(i))) {
                return originalList.get(i);
            }
        }
        return originalList.get(originalList.size() - 1);
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

    @FunctionalInterface
    interface MissingElementFinder {
        int findMissingElement(List<Integer> originalList, List<Integer> copyList);
    }
}
