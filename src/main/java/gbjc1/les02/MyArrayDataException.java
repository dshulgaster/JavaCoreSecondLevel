package gbjc1.les02;

public class MyArrayDataException extends Exception{
    public int i;
    public int j;

    MyArrayDataException(int i, int j, String message) {
        super(message);
        this.i = i;
        this.j = j;
    }
}
