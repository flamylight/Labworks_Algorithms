import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    public Node Root;

    public void Add(Student student) throws Exception {
        Root = Insert(Root, student);
    }

    public void AddWithAmortization(Student student) throws Exception {
        Root = InsertAmortization(Root, student);
    }

    private Node Insert(Node current, Student student) throws Exception {
        if (current == null)
            return new Node(student);

        if (student.MissedClasses < current.Data.MissedClasses) {
            current.Left = Insert(current.Left, student);
            current = RotationRight(current);
        }
        else if (student.MissedClasses > current.Data.MissedClasses) {
            current.Right = Insert(current.Right, student);
            current = RotationLeft(current);
        }
        else {
            throw new Exception("Duplicate");
        }

        return current;
    }

    private Node InsertAmortization(Node current, Student newElem) throws Exception {
        if (current == null) {
            return new Node(newElem);
        }

        if (newElem.MissedClasses == current.Data.MissedClasses) {
            throw new Exception("Duplicate");
        }

        if (newElem.MissedClasses < current.Data.MissedClasses) {

            // один зв'язок
            if (current.Left == null) {
                current.Left = new Node(newElem);
                current = RotationRight(current);
            }
            else {

                // LL
                if (newElem.MissedClasses < current.Left.Data.MissedClasses) {
                    current.Left.Left = InsertAmortization(current.Left.Left, newElem);
                    current = RotationRight(current);
                }
                // LR
                else {
                    current.Left.Right = InsertAmortization(current.Left.Right, newElem);
                    current.Left = RotationLeft(current.Left);
                    current = RotationRight(current);
                }
            }
        }
        else {

            // один зв'язок
            if (current.Right == null) {
                current.Right = new Node(newElem);
                current = RotationLeft(current);
            }
            else {

                // RR
                if (newElem.MissedClasses > current.Right.Data.MissedClasses) {
                    current.Right.Right = InsertAmortization(current.Right.Right, newElem);
                    current = RotationLeft(current);
                }
                // RL
                else {
                    current.Right.Left = InsertAmortization(current.Right.Left, newElem);
                    current.Right = RotationRight(current.Right);
                    current = RotationLeft(current);
                }
            }
        }

        return current;
    }

    private Node RotationRight(Node current){
        Node temp = current.Left;
        current.Left = temp.Right;
        temp.Right = current;
        current = temp;
        return current;
    }

    private Node RotationLeft(Node current){
        Node temp = current.Right;
        current.Right = temp.Left;
        temp.Left = current;
        current = temp;
        return current;
    }

    public Student Search(int key) {
        return Search(Root, key);
    }

    private Student Search(Node current, int key) {
        if (current == null)
            return null;

        if (current.Data.MissedClasses == key)
            return current.Data;

        if (key < current.Data.MissedClasses)
            return Search(current.Left, key);

        return Search(current.Right, key);
    }

    public void Print() {
        if (Root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(Root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                IO.print(current.Data.MissedClasses + " ");

                if (current.Left != null)
                    queue.add(current.Left);

                if (current.Right != null)
                    queue.add(current.Right);
            }

            IO.println("");
        }
    }
}
