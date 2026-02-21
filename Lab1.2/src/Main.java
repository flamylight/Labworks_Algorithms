void main() {
    //Task 1
    HashTableTask1 table1 = new HashTableTask1(10);
    for (int i = 0; i < 7; i++){
        table1.insert(new Circle());
    }

    table1.print();

    IO.println("________________________________________________");

    //Task 2
    HashTableTask2 table2 = new HashTableTask2(10);
    for (int i = 0; i < 7; i++){
        table2.insert(new Circle());
    }

    table2.print();

    IO.println("________________________________________________");

    //Task 3
    HashTableTask3 table3 = new HashTableTask3(10);
    for (int i = 0; i < 7; i++){
        table3.insert(new Circle());
    }

    table3.print();

    IO.println("____________________DELETE______________________");

    table3.delete(10);
    table3.print();
}
