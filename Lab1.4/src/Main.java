void main(){
    Student[] students = new Student[]{
            new Student("Shevchenko", 12, 5, 2006),
            new Student("Bondarenko", 28, 1, 2007),
            new Student("Tymchik", 6, 12, 2007),
            new Student("Pupkin", 10, 1, 2007), // Однаковий місяць з Bondarenko
            new Student("Dmytrenko", 7, 5, 2007),  // Однаковий місяць з Shevchenko
            new Student("Opanasenko", 17, 3, 2007),
            new Student("Kovalenko", 22, 11, 2006),
            new Student("Moroz", 3, 12, 2005),     // Однаковий місяць з Tymchik
            new Student("Lytvyn", 15, 8, 2007),
            new Student("Tkachenko", 30, 5, 2007)
    };

    IO.println("_".repeat(10) + "Task 1 (Array)" + "_".repeat(10));
    PrintStudentsArray(students);
    IO.println("_".repeat(10) + "Task 1 ( Sorted array)" + "_".repeat(10));
    Student[] sortedArray = CountingSortStudent(students);
    PrintStudentsArray(sortedArray);

    LinkedList linkedList = new LinkedList();
    linkedList.Insert(new Student("Melnyk", 23, 11, 1978));
    linkedList.Insert(new Student("Shevchenko", 1, 2, 2000));
    linkedList.Insert(new Student("Tkachenko ", 9, 6, 1998));
    linkedList.Insert(new Student("Kovalchuk", 17, 6, 1995));

    IO.println("_".repeat(10) + "Task 2 (Linked list)" + "_".repeat(10));
    linkedList.PrintLinkedList();
    IO.println("_".repeat(10) + "Task 2 (Sorted linked list)" + "_".repeat(10));
    LinkedList sortedLinkedList = CountingSortStudent(linkedList);
    sortedLinkedList.PrintLinkedList();

    IO.println("_".repeat(10) + "Task 3 (Array)" + "_".repeat(10));
    PrintStudentsArray(students);
    Student[] sortedStudentsTask3 = QuickSort3Way(students, 0, students.length-1);
    IO.println("_".repeat(10) + "Task 3 (Sorted array)" + "_".repeat(10));
    PrintStudentsArray(sortedStudentsTask3);
}

public Student[] CountingSortStudent(Student[] students){
    int size = students.length;
    if (size == 0){
        return new Student[0];
    }

    int maxValue = 0;
    for (Student student : students){
        maxValue = Math.max(maxValue, student.monthOfBirth);
    }

    int[] count = new int[maxValue+1];

    for (int i = 0; i < size; i++){
        count[students[i].monthOfBirth]++;
    }

    for (int i = 1; i <= maxValue; i++){
        count[i] += count[i - 1];
    }

    Student[] output = new Student[size];
    for (int i = size-1; i >= 0; i--){
        int month = students[i].monthOfBirth;
        output[count[month]-1] = students[i];
        count[month]--;
    }

    return output;
}

public LinkedList CountingSortStudent(LinkedList students){
    LinkedList[] buckets = new LinkedList[13];
    for (int i = 0; i < 13; i++) {
        buckets[i] = new LinkedList();
    }

    LinkedList.Node current = students.head;
    while (current != null) {
        int month = current.data.monthOfBirth;
        buckets[month].Insert(current.data);
        current = current.next;
    }

    LinkedList sortedList = new LinkedList();
    for (int i = 1; i <= 12; i++) {
        LinkedList.Node bucketNode = buckets[i].head;
        while (bucketNode != null) {
            sortedList.Insert(bucketNode.data);
            bucketNode = bucketNode.next;
        }
    }
    return sortedList;
}

public Student[] QuickSort3Way(Student[] students, int lo, int hi) {
    if (hi <= lo) return students;

    int lt = lo;
    int gt = hi;
    int i = lo + 1;
    int pivotMonth = students[lo].monthOfBirth;

    while (i <= gt) {
        int currentMonth = students[i].monthOfBirth;
        if (currentMonth < pivotMonth) {
            swap(students, lt++, i++);
        } else if (currentMonth > pivotMonth) {
            swap(students, i, gt--);
        } else {
            i++;
        }
    }

    QuickSort3Way(students, lo, lt - 1);
    QuickSort3Way(students, gt + 1, hi);

    return students;
}

private static void swap(Student[] a, int i, int j) {
    Student temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

public void PrintStudentsArray(Student[] students){
    for (Student student : students){
        IO.println(student);
    }
}