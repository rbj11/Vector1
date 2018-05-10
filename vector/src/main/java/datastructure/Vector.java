package datastructure;

import java.util.Arrays;
import java.util.logging.Logger;

public class Vector {

    private Logger logger = Logger.getLogger(Vector.class.getSimpleName());

    private int[] array;
    private int size;
    private int capacity;

    public Vector() {
        this(10);
    }

    public Vector(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Unexpected array size! It should be >= 1...");
        }
        this.capacity = capacity;
        array = new int[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size() == capacity;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public void add(int number) {
        if (isFull()) {
            logger.info("Doubling vector capacity...");
            resize(capacity * 2);
        }
        array[size++] = number;
    }

    public void add(int index, int number) {
        if (index < 0 || index > size) {
            logger.severe("Invalid index specified...");
            throw new IndexOutOfBoundsException("Invalid index specified...");
        }
        if (isFull()) {
            resize(capacity * 2);
        }

        int i = index + 1;
        for (; i < size + 1; i++) {
            array[i] = array[i - 1];
        }

        array[index] = number;
        size++;
    }

    public int get(int index) {
        if (index < 0 || index >= size()) {
            logger.severe("Invalid index specified...");
            throw new IndexOutOfBoundsException("Invalid index specified...");
        }
        return array[index];
    }


    public void set(int index, int number) {
        if (index < 0 || index >= size()) {
            logger.severe("Invalid index specified...");
            throw new IndexOutOfBoundsException("Invalid index specified...");
        }
        array[index] = number;
    }

    public int indexOf(int number) {
        int index;
        boolean found = false;
        for (index = 0; index < size; index++) {
            if (number == array[index]) {
                found = true;
                break;
            }
        }
        return found ? index : -1;
    }

    public boolean contains(int number) {
        return indexOf(number) != -1;
    }

    public boolean remove(int number) {
        int index = indexOf(number);
        if (index == -1) {
            logger.warning("Specified element not found in the Vector...");
            return false;
        } else {
            int i = index;
            for (; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[i] = 0;
            size--;

            if (size < (capacity / 2)) {
                resize(capacity / 2);
            }

            return true;
        }
    }

    public void clear() {
        Arrays.fill(array, 0);
    }

    private void resize(int newCapacity) {
        if (newCapacity == 0) {
            throw new IllegalArgumentException("Unexpected array size! It should be >= 1");
        }
        int[] newArray = new int[newCapacity];
        // Array copy is faster than looping
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
        capacity = newCapacity;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Arrays.equals(array, vector.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

}
