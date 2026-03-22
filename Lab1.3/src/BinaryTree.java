import java.util.ArrayList;

public class BinaryTree {
    class Node{
        Student data;
        Node right;
        Node left;

        Node(Student data){
            this.data = data;
        }
    }

    private Node root;

    public BinaryTree(){
        root = null;
    }

    public void add(Student student) throws Exception{
        if (contains(root, student.studentId)){
            throw new Exception("Студент з таким ID: " + student.studentId + " вже існує!");
        }

        root = insert(root, student);
    }

    private boolean contains(Node current, int studentId){
        if (current == null){
            return false;
        }

        if (current.data.studentId == studentId){
            return true;
        }

        if (studentId < current.data.studentId)
            return contains(current.left, studentId);

        return contains(current.right, studentId);
    }

    private Node insert(Node current, Student student){
        if (current == null){
            return new Node(student);
        }
        else{
            if (student.studentId < current.data.studentId){
                current.left = insert(current.left, student);
            }
            else if (student.studentId > current.data.studentId){
                current.right = insert(current.right, student);
            }
        }
        return current;
    }

    public void print() {
        preOrderPrint(root, "", true);
    }

    private void preOrderPrint(Node node, String prefix, boolean isLast) {
        if (node == null) return;

        IO.println(prefix + (isLast ? "└── " : "├── ") + node.data);

        String newPrefix = prefix + (isLast ? "    " : "│   ");

        if (node.left != null && node.right != null) {
            preOrderPrint(node.left, newPrefix, false);
            preOrderPrint(node.right, newPrefix, true);
        } else if (node.left != null) {
            preOrderPrint(node.left, newPrefix, true);
        } else if (node.right != null) {
            preOrderPrint(node.right, newPrefix, true);
        }
    }

    public ArrayList<Student> findIdealWeight(){
        ArrayList<Student> list = new ArrayList<Student>();

        if (root == null){
            IO.println("Дерево порожнє!");
        }

        searchInOrder(root, list);

        return list;
    }

    private void searchInOrder(Node current, ArrayList<Student> list){
        if (current == null){
            return;
        }

        searchInOrder(current.left, list);

        if (current.data.height - 110 == current.data.weight){
            list.add(current.data);
        }

        searchInOrder(current.right, list);
    }

    public void deleteIdealWeight(){
        root = deleteIdealWeight(root);
    }

    private Node deleteIdealWeight(Node current) {
        if (current == null) return null;

        current.left = deleteIdealWeight(current.left);
        current.right = deleteIdealWeight(current.right);

        if (current.data.height - 110 == current.data.weight) {

            //Without child
            if (current.left == null && current.right == null) {
                return null;
            }

            //One child
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            //Two child
            current.right = replaceNode(current, current.right);
        }

        return current;
    }

    private Node replaceNode(Node deleteNode, Node current) {
        if (current.left != null) {
            current.left = replaceNode(deleteNode, current.left);
        } else {
            deleteNode.data = current.data;
            current = current.right;
        }
        return current;
    }
}
