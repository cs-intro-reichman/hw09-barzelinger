/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }
    
    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the CharData of the first element in this list. */
    public CharData getFirst() {
        if (first == null){
            return null;
        }
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        CharData cd = new CharData(chr);
        // create new node with the given char as cd and currnt first as next.
        Node newNode = new Node(cd, first);
        // update first to the new node.
        first = newNode;
        size++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        String result = "(";
        Node curr = first;         
        // go over the nodes.
        while (curr != null) {
            // add the chars of each node to the final string.
            result += curr.cp.toString();   
            // add space between each char.
            if (curr.next != null) {
                result += " ";             
            }
            // go to next node.
            curr = curr.next;       
        }
        result += ")";
        return result;
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        Node curr = first;
        int index = 0;
        // go over all the nodes.
        while (curr != null){
            // if chr equals chr in list return index.
            if (curr.cp.chr == chr){
                return index;
            }
            // go to next node.
            curr = curr.next;
            // increase index.
            index++;
        }
        // if chr is not in list return -1.
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        Node curr = first;
        // go over all the nodes.
        while (curr != null) {
            // if chr exists in list, add to counter
            if (curr.cp.chr == chr) {
                curr.cp.count++;
                return;
            }
            curr = curr.next;
        }
        // if chr not in list create new node for chr and add first.
        addFirst(chr);
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        
        // if list is empty return null.
        if (first == null){
            return false;
        }
        // if the given chr is the first in the list delete it and return true.
        if (first.cp.chr == chr){
            first = first.next;
            size--;
            return true;
        }
        
        Node prev = first;
        Node curr = first.next;
        // go over all the nodes in the list starting from the second one.
        while (curr != null){
            // if current chr equlas to given chr, delete it and return true. 
            if (curr.cp.chr == chr){
                prev.next = curr.next;
                size--;
                return true;
            }
            // go to next chr.
            prev = curr;
            curr = curr.next;
        }
        // if chr not in list return false
        return false;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        
        // if index out of bound return error.
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node curr = first;
        int i = 0;
        // go over the nodes in the list while i < index.
        while (i < index){
            // go to the next node.
            curr = curr.next;
            i++;
        }
        // return the cp from the list in the given index.
        return curr.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}