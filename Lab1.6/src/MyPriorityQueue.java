public class MyPriorityQueue {
    private int[] heap;
    private int size;

    public MyPriorityQueue(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public void add(int value) {
        heap[size] = value;
        int i = size;
        size++;

        while (i > 0) {
            int parent = (i - 1) / 2;

            if (heap[parent] <= heap[i])
                break;

            swap(i, parent);
            i = parent;
        }
    }

    public int poll() {
        int result = heap[0];

        heap[0] = heap[size - 1];
        size--;

        int i = 0;

        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && heap[left] < heap[smallest])
                smallest = left;

            if (right < size && heap[right] < heap[smallest])
                smallest = right;

            if (smallest == i)
                break;

            swap(i, smallest);
            i = smallest;
        }

        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
