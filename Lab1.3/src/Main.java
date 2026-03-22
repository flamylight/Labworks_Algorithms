void main(){
    BinaryTree bt = new BinaryTree();

    try{
        Student student1 = new Student("Вася", "Пупкін", 180, 70, 123456);
        Student student2 = new Student("Ольга", "Tимчик", 190, 110, 123451);
        Student student3 = new Student("Валера", "Ігнатович", 190, 110, 123457);
        Student student4 = new Student("Оксана", "Козак", 165, 55, 123450);

        bt.add(student1);
        bt.add(student2);
        bt.add(student3);
        bt.add(student4);

    }
    catch (Exception ex){
        IO.println(ex.getMessage());
    }

    bt.print();


    IO.println("\n________FIND IDEAL WEIGHT_________");
    ArrayList<Student> findResult = bt.findIdealWeight();

    for (Student student : findResult){
        IO.println(student);
    }

    IO.println("\n________DELETE IDEAL WEIGHT_________");
    bt.deleteIdealWeight();

    bt.print();
}