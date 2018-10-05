package assigment2.task2;
/**
 * Created by nicholas on 31/01/2017.
 */
public class ArrayList<E> implements List<E> {
    private E[] data;
    private int size = 0;

    public ArrayList(){
        data = (E[]) new Object[1];
    }

    private void manageSpace(){
        if (size == data.length){
            E[] arrayTemp = data;
            data = (E[]) new Object[arrayTemp.length*2];
            for (int i = 0; i < arrayTemp.length; i++)
                data[i] = arrayTemp[i];
        }
    }

    @Override
    public void add(E element) {
        manageSpace();
        data[size] = element;
        size++;

    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException{
        manageSpace();
        checkIndex(index);
        for (int i = size; i > index; i--)
            data[i] = data[i-1];
        data[index] = element;
        size++;
    }

    @Override
    public void remove(E element) {
        for (int i = 0 ; i < size; i++){
            if (data[i].equals(element)){
                for (int j = i; j < size - 1; j++){
                    data[j] = data[j+1];
                }
                size--;
                break;
            }
        }
    }

    @Override
    public void remove(int index) throws IndexOutOfBoundsException{
        checkIndex(index);
        for (int i = index; index < size - 1; index--){
            data[i] = data[i+1];
        }
        size --;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++)
            if (data[i].equals(element))
                return true;
        return false;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException{
        checkIndex(index);
        return data[index];
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException{
        checkIndex(index);
        E result = data[index];
        data[index] = element;
        return result;
    }


    private void checkIndex (int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("wrong index: " + index);
    }
}
