package datastructures.vector;

public class Vector<T> {

    private T[] data;

    private int size = 0;

    public Vector(int size) {
        data = (T[]) new Object[size];
    }

    public Vector() {
        this(16);
    }

    public int size() {
        return size;
    }

    public int length() {
        return data.length;
    }

    public void add(T item) {
        // Ensure space
        ensureSpace();
        // Insert the object
        data[size] = item;
        // Increment the 'size'
        size++;
    }

    public void add(T item, int position) {
        ensureSpace();
        if(position > size)
            verifyPosition(position);
        for (int i = size; i >= position; i -= 1) {
            data[i + 1] = data[i];
        }
        size++;
        data[position] = item;
    }


    public void addRange(T[] items) {
        for (T item : items) {
            add(item);
        }
    }

    public T get(int position) {
        verifyPosition(position);
        return data[position];
    }

    public T remove(int position) {
        verifyPosition(position);
        T removed = data[position];
        data[position] = null;
        for (int i = position; i < size- 1; i += 1) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removed;
    }

    public boolean contains(T item) {
        for (T _item : data) {
            if(item.equals(_item))
                return true;
        }
        return false;
    }

    public void printData() {
        for (int i = 0; i < size; i++) {
            System.out.println(String.format("[%d:%s]", i, get(i)));
        }
    }

    private void ensureSpace() {
        // Verify if the resize is necessary
        if (size == data.length) {
            // Do the resize operation
            T[] fresh = (T[]) new Object[data.length * 2];
            copyContent(data, fresh);
            data = fresh;
        }
    }

    private void copyContent(T[] old, T[] fresh) {
        for (int i = 0; i < old.length; i++) {
            fresh[i] = old[i];
        }
    }

    private void verifyPosition(int position) {
        if (position >= size) {
            throw new ArrayIndexOutOfBoundsException(position);
        }
    }

}
