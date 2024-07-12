package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<Integer> originalList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            originalList.add(random.nextInt(101));
        }

        List<Integer> copyList = new ArrayList<>(originalList);

        int randomIndex = random.nextInt(100);
        int removedElement = copyList.remove(randomIndex);
        System.out.println("Removed element: " + removedElement);

        System.out.println("1. Method: Sum of the elements");
        long startTime = System.nanoTime();
        int missingElement = findMissingElement(originalList, copyList);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Missing element: " + missingElement);
        System.out.println("Duration: " + duration + " ns");
    }

    private static int findMissingElement(List<Integer> originalList, List<Integer> copyList) {
        int originalSum = 0;
        int copySum = 0;

        for (int num : originalList) {
            originalSum += num;
        }

        for (int num : copyList) {
            copySum += num;
        }

        return originalSum - copySum;
    }
}