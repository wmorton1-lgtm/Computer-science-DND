import java.util.LinkedList;
import java.util.ListResourceBundle;
public class MyStack<E extends Comparable<E>> {

    private boolean empty;
    private LinkedList<E> internaList;

    public MyStack() {
        this.empty = true;
        this.internaList = new LinkedList<E>();
    }

    public boolean empty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public LinkedList<E> getInternaList() {
        return internaList;
    }

    public void setInternaList(LinkedList<E> internaList) {
        this.internaList = internaList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (empty ? 1231 : 1237);
        result = prime * result + ((internaList == null) ? 0 : internaList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyStack other = (MyStack) obj;
        if (empty != other.empty)
            return false;
        if (internaList == null) {
            if (other.internaList != null)
                return false;
        } else if (!internaList.equals(other.internaList))
            return false;
        return true;
    }

    // methods

    public boolean push(E obj) {
        internaList.addLast(obj);
        empty = false;
        return true;
    }

    public E pop() {
        if (empty) {
            return null;
        }
        E toReturn = peek();
        internaList.removeLast();
        empty = internaList.isEmpty();
        return toReturn;
    }

    public E peek() {
        if (empty) {
            return null;
        }
        return internaList.getLast();
    }
}
