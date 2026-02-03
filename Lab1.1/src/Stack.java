public class Stack {
    private Node top;
    private int last = -1;

    public boolean push(Name data){
        Node element = new Node(data);
        if ((last+1) % 2 == 0){
            if (data.getGender().equals("FEMALE")){
                element.prev = top;
                top = element;
                last++;
                return true;
            }
        }
        else if ((last+1) % 2 != 0){
            if (data.getGender().equals("MALE")){
                element.prev = top;
                top = element;
                last++;
                return true;
            }
        }
        return false;
    }

    public Name pop(){
        if (top == null){
            return null;
        }

        Name temp = top.data;
        top = top.prev;
        last--;
        return temp;
    }

    public void Show(){
        Node element = top;

        while (element != null){
            IO.println(element.data.name());
            element = element.prev;
        }
    }
}

class Node{
    Name data;
    Node prev;

    Node(Name data){
        this.data = data;
    }
}

