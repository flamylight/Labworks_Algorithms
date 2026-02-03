void main() {
    String[] words = {"hello", "world", "string", "ball", "book", "file"};
    Name[] names = {Name.MARIA, Name.IVAN, Name.PETER, Name.Anna, Name.MARIA, Name.VASYA};

    //Task 1
    VectorQueue queue = new VectorQueue(10);

    //Add
    for (String word : words){
        queue.enqueue(word);
    }

    queue.Show();

    //Delete
    queue.dequeue();
    queue.dequeue();

    IO.println("_".repeat(10));
    queue.Show();

    //Task 2
    Stack stack = new Stack();

    //Add
    for (Name name : names){
        stack.push(name);
    }

    IO.println("_".repeat(10));
    stack.Show();

    //Delete
    stack.pop();
    stack.pop();

    IO.println("_".repeat(10));
    stack.Show();
}
