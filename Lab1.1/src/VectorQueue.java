public class VectorQueue {
    private String [] vect;
    private int last;

    public VectorQueue(int size){
        vect = new String[size];
        last = -1;
    }

    private boolean isFull(){
        return (last == vect.length-1);
    }

    private boolean isEmpty(){
        return (last == -1);
    }

    //Add
    public boolean enqueue(String value){
        if (isFull()){
            return false;
        }

        vect [last+1] = value;
        last++;
        return true;
    }

    //Delete
    public String dequeue(){
        if (isEmpty()){
            return null;
        }

        String temp = vect[0];
        for (int i = 0; i < last; i++){
            vect[i] = vect[i+1];
        }

        vect[last--] = null;
        return temp;
    }

    //Show
    public void Show(){
        for (int i = 0; i <= last; i++){
            IO.println(vect[i]);
        }
    }
}
