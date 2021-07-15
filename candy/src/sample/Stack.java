package sample;

public class Stack implements StackInterface {
    private final int MAX = 5;
    private final int[] stack = new int [MAX];
    private int topIndex = -1;

    public int getTopIndex() { return topIndex; }

    public int[] getStack() { return stack; }

    @Override
    public int size() { return topIndex + 1; }

    @Override
    public boolean isEmpty() { return topIndex == -1; }

    @Override
    public boolean isFull() { return topIndex == MAX-1; }

    @Override
    public boolean push(int x) {
        if (topIndex >= (MAX - 1)) { return false; }
        stack[++topIndex] = x;
        return true;
    }

    @Override
    public int pop() throws Exception {
        if (isEmpty()) { throw new Exception(); }

        int popped = stack[topIndex];
        stack[topIndex--] = 0;
        return popped;
    }

    @Override
    public int peek () throws Exception {

        if (isEmpty()) {
            throw new Exception();
        }
        return stack[topIndex];
    }
}
