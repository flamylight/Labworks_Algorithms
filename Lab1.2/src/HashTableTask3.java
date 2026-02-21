public class HashTableTask3 extends HashTableTask1{

    public HashTableTask3(int size){
        super(size);
    }

    public void delete(double maxPerimeter){
        for(int i = 0; i < size; i++){
            if(arr[i] != null && arr[i] != DELETED){
                if(arr[i].perimeter() > maxPerimeter){
                    arr[i] = DELETED;
                }
            }
        }
    }

    @Override
    public boolean insert(Circle circle){
        int hash = hashFuncMul(circle);
        int index = hash;
        int i = 0;

        while(arr[index] != null && arr[index] != DELETED){
            i++;
            if(i >= size){
                IO.println("HashTable full, cannot insert");
                return false;
            }
            index = (hash + i*i) % size;
        }

        arr[index] = circle;
        return true;
    }
}
