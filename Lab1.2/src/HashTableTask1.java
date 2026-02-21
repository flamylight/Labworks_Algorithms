public class HashTableTask1 {
    protected Circle[] arr;
    protected int size;
    protected static final Circle DELETED = new Circle(-1,-1,-1);

    public HashTableTask1(int size){
        arr = new Circle[size];
        this.size = size;
    }

    protected int hashFuncMul(Circle circle){
        double key = circle.area();
        double A = 0.618;
        int hash = (int) ((key*A- (int)(key*A)) * size);
        return hash;
    }

    public boolean insert(Circle circle){
        int index = hashFuncMul(circle);
        if (arr[index] == null){
            arr[index] = circle;
        }
        else{
            IO.println("Collision");
            return false;
        }
        return true;
    }

    public void print(){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != null){
                IO.println(i + " " + arr[i].toString());
            }
        }
    }
}
