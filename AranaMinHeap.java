public class AranaMinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the heap with a given capacity
    public AranaMinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Function to insert a new element into the heap
    public void insert(int val) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }

        // Insert the element at the end of the heap
        heap[size] = val;
        size++;

        // Heapify the heap by bubbling up the newly inserted element
        heapifyUp(size - 1);
    }

    // Function to heapify the heap from a given index upwards
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;

        // Keep swapping the element with its parent until the heap property is satisfied
        while (index > 0 && heap[parent] > heap[index]) {
            // Swap
            int temp = heap[parent];
            heap[parent] = heap[index];
            heap[index] = temp;

            index = parent;
            parent = (index - 1) / 2;
        }
    }

    // Function to extract the minimum (root) element from the heap
    public int extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }

        // The minimum element is at the root (index 0)
        int min = heap[0];

        // Move the last element to the root
        heap[0] = heap[size - 1];
        size--;

        // Heapify the root element down to restore heap property
        heapifyDown(0);

        return min;
    }

    // Function to heapify the heap from a given index downwards
    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        // Compare with left child
        if (leftChild < size && heap[leftChild] < heap[smallest]) {
            smallest = leftChild;
        }

        // Compare with right child
        if (rightChild < size && heap[rightChild] < heap[smallest]) {
            smallest = rightChild;
        }

        // If the smallest element is not the current index, swap and heapify further
        if (smallest != index) {
            int temp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temp;

            heapifyDown(smallest);
        }
    }

    // Function to return the minimum (root) element without removing it
    public int peek() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }
        return heap[0];
    }

    // Function to print the elements of the heap
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);

        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(5);
        minHeap.insert(30);
        minHeap.insert(15);

        System.out.println("Heap after insertions:");
        minHeap.printHeap();

        System.out.println("Extracting min: " + minHeap.extractMin());
        minHeap.printHeap();

        System.out.println("Peeking min: " + minHeap.peek());
        minHeap.printHeap();
    }
}
