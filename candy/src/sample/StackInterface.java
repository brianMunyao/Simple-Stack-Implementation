package sample;

public interface StackInterface {
    int size();
    boolean isEmpty();
    boolean isFull();
    boolean push(int i);
    int pop() throws Exception;
    int peek() throws Exception;
}
