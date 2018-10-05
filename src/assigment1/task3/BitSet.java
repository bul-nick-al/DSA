package assigment1.task3;
/**
 * Created by nicholas on 01/02/2017.
 *
 * this class uses array of longs to save boolean values as bits of long in binary.
 * As long is 64 bits long, index of an element is divided by 64
 */
public class BitSet {
    private long[] data;
    private int size = 0; //for "size" it takes the position of the most significant bit

    public BitSet(){
        data = new long[1];
    }

    /**
     * set the bit at the specified index to true
     * @param index
     */
    public void set(int index){
        manageLength(index);
        data[index/64] = data[index/64] | (1L << index%64);
        setSize(index);
    }

    /**
     * returns the value of the bit with the specified index
     * @param index
     * @return the value of the bit
     */
    public boolean get(int index){
        return (data[index/64] >> index%64)%2 == 1;
    }

    /**
     * expand array if required
     * @param index
     */
    public void manageLength(int index){
        if (index > data.length*64){
            long[] arrayTemp = data;
            data = new long[index/64+1];
            for (int i = 0; i < arrayTemp.length; i++){
                data[i] = arrayTemp[i];
            }
        }
    }

    private void setSize(int index){
        if (index > size){
            size = index;
        }
    }

    /**
     * set the bit specified by the index to false
     * @param index
     */
    public void clear(int index){
        checkIndex(index);
        data[index/64] = data[index/64] - (1L << index%64);
        //changing size if the most significant bit was removed
        if (index == size){
            for (int i = index; i > 0; i--){
                if ((data[i/64] >> i%64)%2 == 1){
                    size = i;
                    break;
                }
                size = 0;
            }
        }
    }

    public int size(){
        return size;
    }

    private void checkIndex (int index){
        if (index < 0 || index >= data.length)
            throw new IndexOutOfBoundsException("wrong index: " + index);
    }

}
