public class HashTableTask2 extends HashTableTask1 {

    public HashTableTask2(int size){
        super(size);
    }

    @Override
    public boolean insert(Circle circle){
        int hash = hashFuncMul(circle);
        int index = hash;
        int i = 0;

        while (arr[index] != null){
            i++;
            if (i >= size){
                IO.println("HashTable full, cannot insert");
                return false;
            }
            index = (hash + i*i) % size;
        }

        arr[index] = circle;
        return true;
    }
}
