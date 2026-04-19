void main() {
   Student[] arrayStudents = {
           new Student("Savchenko", 4, "Engineering", 11),
           new Student("Ivanenko", 1, "Computer Science", 2),
           new Student("Stepanenko", 3, "Physics", 18),
           new Student("Melnyk", 1, "Computer Science", 3),
           new Student("Klymenko", 4, "Engineering", 19),
           new Student("Oliynyk", 2, "Mathematics", 0),
           new Student("Bondarenko", 3, "Physics", 4),
           new Student("Romanenko", 1, "Computer Science", 12),
           new Student("Koval", 4, "Engineering", 7),
           new Student("Tkachenko", 2, "Mathematics", 8),
           new Student("Shevchenko", 3, "Physics", 1),
           new Student("Zakharchenko", 1, "Computer Science", 16),
           new Student("Petrenko", 2, "Mathematics", 5),
           new Student("Danylko", 4, "Engineering", 100),
           new Student("Kravchenko", 4, "Engineering", 6),
           new Student("Marchenko", 1, "Computer Science", 9),
           new Student("Hrytsenko", 2, "Mathematics", 13),
           new Student("Polishchuk", 3, "Physics", 10),
           new Student("Yurchenko", 2, "Mathematics", 17),
           new Student("Lysenko", 3, "Physics", 14)
   };

   Student[] arrayStudents2 = {
           new Student("Savchenko", 4, "Engineering", 11),
           new Student("Ivanenko", 1, "Computer Science", 2),
           new Student("Stepanenko", 3, "Physics", 18),
           new Student("Melnyk", 1, "Computer Science", 3),
           new Student("Klymenko", 4, "Engineering", 19),
           new Student("Oliynyk", 2, "Mathematics", 0),
           new Student("Bondarenko", 3, "Physics", 4),
           new Student("Romanenko", 1, "Computer Science", 12),
           new Student("Koval", 4, "Engineering", 7),
   };

   IO.println("_".repeat(20));
   PrintStudentArray(arrayStudents);

   QuickSortArray(arrayStudents, 0, arrayStudents.length-1);

   IO.println("_".repeat(20));
   PrintStudentArray(arrayStudents);

   Student binarySearchResult = BinarySearch(arrayStudents, 100, 0, arrayStudents.length-1);

   IO.println("_".repeat(10) + "BINARY SEARCH RESULT" + "_".repeat(10));
   IO.println(binarySearchResult);

   BinaryTree bt = new BinaryTree();

   for (Student student : arrayStudents2){
      try{
         bt.Add(student);
      }
      catch (Exception ex){
         IO.println(ex.getMessage());
      }
   }

   IO.println("_".repeat(10) + "BINARY TREE" + "_".repeat(10));
   bt.Print();

   Student binaryTreeSearch = bt.Search(7);

   IO.println("_".repeat(10) + "BINARY TREE SEARCH" + "_".repeat(10));
   IO.println(binaryTreeSearch);

   BinaryTree bt2 = new BinaryTree();

   for (Student student : arrayStudents2){
      try{
         bt2.AddWithAmortization(student);
      }
      catch (Exception ex){
         IO.println(ex.getMessage());
      }
   }

   IO.println("_".repeat(10) + "BINARY TREE WITH AMORTIZATION" + "_".repeat(10));
   bt2.Print();
}

public void PrintStudentArray(Student[] students){
   for (Student student : students){
      IO.println(student);
   }
}

public void QuickSortArray(Student[] students, int begin, int end){
   if (begin < end){
      int partitionIndex = partition(students, begin, end);

      QuickSortArray(students, begin, partitionIndex-1);
      QuickSortArray(students, partitionIndex+1, end);
   }
}

public int partition(Student[] arr, int begin, int end){
   Student pivot = arr[end];
   int i = begin - 1;

   for (int j = begin; j < end; j++){
      if (arr[j].MissedClasses <= pivot.MissedClasses){
         i++;

         Student swapTemp = arr[i];
         arr[i] = arr[j];
         arr[j] = swapTemp;
      }
   }

   Student swapTemp = arr[i+1];
   arr[i+1] = arr[end];
   arr[end] = swapTemp;

   return i+1;
}

public Student BinarySearch(Student[] sortedStudents, int key, int low, int high){
   int middle = low + ((high - low)/2);

   if (high < low){
      return null;
   }

   if (key == sortedStudents[middle].MissedClasses){
      return sortedStudents[middle];
   }
   else if (key < sortedStudents[middle].MissedClasses){
      return BinarySearch(sortedStudents, key, low, middle-1);
   }
   else{
      return BinarySearch(sortedStudents, key, middle+1, high);
   }
}
