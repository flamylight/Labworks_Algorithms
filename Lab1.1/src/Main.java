void main() {
    String[] words = {"hello", "world", "string", "ball", "book", "file"};

    //Vector Queue (string)
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
}
