public class LinkedList {
    public Node head;
    public int Length = 0;

    public class Node{
        public Student data;
        public Node next;

        public Node(Student data){
            this.data = data;
            next = null;
        }
    }

    public void Insert(Student data){
        Node newNode = new Node(data);

        if (head == null){
            head = newNode;
        }
        else{
            Node last = head;
            while (last.next != null){
                last = last.next;
            }

            last.next = newNode;
            Length++;
        }
    }

    public void PrintLinkedList(){
        Node currentNode = head;

        while (currentNode != null){
            IO.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }
}
