void main(){
    final int N = 100;

    int[] array1 = new int[N];
    int[] array2 = new int[N*N];
    int[] array3 = new int[N*N*N];

    int[] arrayBest = new int[N*N];
    int[] arrayWorst = new int[N*N];

    Random rand = new Random();

    for (int i = 0; i < array1.length; i++){
        array1[i] = rand.nextInt(array1.length);
    }

    for (int i = 0; i < array2.length; i++){
        array2[i] = rand.nextInt(array2.length);
    }

    for (int i = 0; i < array3.length; i++){
        array3[i] = rand.nextInt(array3.length);
    }

    for (int i = 0; i < arrayBest.length; i++){
        arrayBest[i] = i;
    }

    for (int i = 0; i < arrayWorst.length; i++){
        arrayBest[i] = N*N-1;
    }

    IO.println("_".repeat(10) + "LEVEL 1" + "_".repeat(10));
    IO.println("_".repeat(10) + "Array1" + "_".repeat(10));
    MeasureAverageTime(array1, this::sortWithPriorityQueue);
    IO.println("_".repeat(10) + "Array2" + "_".repeat(10));
    MeasureAverageTime(array2, this::sortWithPriorityQueue);
    IO.println("_".repeat(10) + "Array3" + "_".repeat(10));
    MeasureAverageTime(array3, this::sortWithPriorityQueue);

    IO.println("_".repeat(10) + "LEVEL 2" + "_".repeat(10));
    IO.println("_".repeat(10) + "Array1" + "_".repeat(10));
    MeasureAverageTime(array1, this::heapSort);
    IO.println("_".repeat(10) + "Array2" + "_".repeat(10));
    MeasureAverageTime(array2, this::heapSort);
    IO.println("_".repeat(10) + "Array3" + "_".repeat(10));
    MeasureAverageTime(array3, this::heapSort);

    IO.println("_".repeat(10) + "LEVEL 3" + "_".repeat(10));
    IO.println("_".repeat(10) + "Array Best" + "_".repeat(10));
    MeasureAverageTime(arrayBest, this::heapSort);
    IO.println("_".repeat(10) + "Array Worst" + "_".repeat(10));
    MeasureAverageTime(arrayWorst, this::heapSort);
    IO.println("_".repeat(10) + "Array Random" + "_".repeat(10));
    MeasureAverageTime(array2, this::heapSort);
}

public void sortWithPriorityQueue(int[] array){
    MyPriorityQueue pq = new MyPriorityQueue(array.length);

    for (int num : array) {
        pq.add(num);
    }

    int i = 0;
    while (!pq.isEmpty()) {
        array[i++] = pq.poll();
    }
}

public void heapSort(int[] array){
    int length = array.length;

    for (int i = length / 2 - 1; i >= 0; i--){
        heapify(array, length, i);
    }

    for (int i = length - 1; i > 0; i--){
        int temp = array[0];
        array[0] = array[i];
        array[i] = temp;

        heapify(array, i, 0);
    }
}

public void heapify(int[] array, int length, int i){
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < length && array[left] > array[largest]){
        largest = left;
    }

    if (right < length && array[right] > array[largest]){
        largest = right;
    }

    if (largest != i){
        int temp = array[i];
        array[i] = array[largest];
        array[largest] = temp;

        heapify(array, length, largest);
    }
}

public void MeasureAverageTime(int[] array, Consumer<int[]> func){
    double[] times = new double[10];

    for (int i = 0; i < 10; i++){
        long start = System.nanoTime();
        func.accept(array.clone());
        long end = System.nanoTime();

        double time = (double) (end - start)/1000000000;
        times[i] = time;
        IO.println(time);
    }

    double average = 0;

    for (double i : times){
        average += i;
    }

    IO.println("Average: " + average/10);
}
