package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        List<Integer> originalList = generateRandomList(100, 101);
        List<Integer> copyList = new ArrayList<>(originalList);

        int randomIndex = new Random().nextInt(100);
        int removedElement = copyList.remove(randomIndex);
        System.out.println("Removed element: " + removedElement);

        long sum = measureMethodPerformance("Sum of the elements", Main::findMissingElementBySum, originalList, removedElement);
        long sorting = measureMethodPerformance("Sorting and comparing", Main::findMissingElementBySorting, originalList, removedElement);
        long hash = measureMethodPerformance("Using HashSet", Main::findMissingElementByHashSet, originalList, removedElement);

        // Find the fastest method
        if (sum < sorting && sum < hash) {
            System.out.println("Sum of the elements is the fastest method");
        } else if (sorting < sum && sorting < hash) {
            System.out.println("Sorting and comparing is the fastest method");
        } else {
            System.out.println("Using HashSet is the fastest method");
        }
    }

    private static List<Integer> generateRandomList(int size, int bound) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(bound));
        }
        return list;
    }

    private static long measureMethodPerformance(String methodName, MissingElementFinder method, List<Integer> originalList, int removedElement) {
        List<Integer> copyList = new ArrayList<>(originalList);
        copyList.remove(Integer.valueOf(removedElement));
        long startTime = System.nanoTime();
        int missingElement = method.findMissingElement(originalList, copyList);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println(methodName + ":");
        System.out.println("Missing element: " + missingElement);
        System.out.println("Duration: " + duration + " ns");
        return duration;
    }

    private static int findMissingElementBySum(List<Integer> originalList, List<Integer> copyList) {
        int originalSum = originalList.stream().mapToInt(Integer::intValue).sum();
        int copySum = copyList.stream().mapToInt(Integer::intValue).sum();
        return originalSum - copySum;
    }

    private static int findMissingElementBySorting(List<Integer> originalList, List<Integer> copyList) {
        List<Integer> sortedOriginalList = new ArrayList<>(originalList);
        List<Integer> sortedCopyList = new ArrayList<>(copyList);
        Collections.sort(sortedOriginalList);
        Collections.sort(sortedCopyList);

        for (int i = 0; i < sortedCopyList.size(); i++) {
            if (!sortedOriginalList.get(i).equals(sortedCopyList.get(i))) {
                return sortedOriginalList.get(i);
            }
        }
        return sortedOriginalList.get(sortedOriginalList.size() - 1);
    }

    private static int findMissingElementByHashSet(List<Integer> originalList, List<Integer> copyList) {
        Set<Integer> originalSet = new HashSet<>(originalList);
        Set<Integer> copySet = new HashSet<>(copyList);
        originalSet.removeAll(copySet);
        if (originalSet.isEmpty()) {
            throw new IllegalStateException("No missing element found");
        }
        return originalSet.iterator().next();
    }

    @FunctionalInterface
    interface MissingElementFinder {
        int findMissingElement(List<Integer> originalList, List<Integer> copyList);
    }
}
