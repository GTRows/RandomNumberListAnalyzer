# RandomNumberListAnalyzer

This project involves creating a list of 100 random numbers in Java and then making a copy of that list. A random number between 0 and 100 is generated, and the element at that index in the copied list is removed. The goal is to develop a method that identifies which element is missing from the copied list compared to the original list.

## Branches

### `performance-comparison`
This branch is used to compare the performance of different algorithms for finding the missing element. The algorithms implemented and tested in this branch are:

1. **Sum of the Elements**:
   - This method calculates the sum of the elements in the original list and the copied list. The difference between the two sums gives the missing element.

2. **Sorting and Comparing**:
   - This method sorts both lists and compares them element by element to find the missing element.

3. **Using HashMap**:
   - This method uses a HashMap to count the occurrences of each element in the original list and then decreases the count based on the copied list to find the missing element.

After testing, it was determined that the **Using HashMap** method is the fastest.

### `find-missing-element-hashmap`
This branch contains a clean and optimized implementation using only the HashMap method to find the missing element. This branch was created after determining that the HashMap method was the most efficient based on the performance tests conducted in the `performance-comparison` branch.

## How to Run

1. **Clone the repository**:
    ```sh
    git clone https://github.com/yourusername/RandomNumberListAnalyzer.git
    ```

2. **Switch to the desired branch**:
    - For performance comparison:
      ```sh
      git checkout performance-comparison
      ```
    - For the optimized HashMap solution:
      ```sh
      git checkout find-missing-element-hashmap
      ```

3. **Compile and run the code**:
    ```sh
    cd RandomNumberListAnalyzer
    javac -d bin src/org/example/Main.java
    java -cp bin org.example.Main
    ```