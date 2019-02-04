/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * 
 * @param <T> This is a type parameter. T is used as a class name in the
 *        definition of this class.
 *
 *        When creating a new DoublyLinkedList, T should be instantiated with an
 *        actual class name that extends the class Comparable. Such classes
 *        include String and Integer.
 *
 *        For example to create a new DoublyLinkedList class containing String
 *        data: DoublyLinkedList<String> myStringList = new
 *        DoublyLinkedList<String>();
 *
 *        The class offers a toString() method which returns a comma-separated
 *        sting of all elements in the data structure.
 * 
 *        This is a bare minimum class you would need to completely implement.
 *        You can add additional methods to support your code. Each method will
 *        need to be tested by your jUnit tests -- for simplicity in jUnit
 *        testing introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode {
		public final T data; // this field should never be updated. It gets its
								// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * 
		 * @param theData  : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;
	private int size;

	/**
	 * Constructor of an empty DLL
	 * 
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic running time cost: theta(1)
	 *
	 *         Justification: has a constant running time. No operation
	 *         costs more than theta(1). 
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * 
	 * @param pos  : The integer location at which the new data should be inserted
	 *             in the list. We assume that the first position in the list is 0
	 *             (zero). If pos is less than 0 then add to the head of the list.
	 *             If pos is greater or equal to the size of the list then add the
	 *             element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *         Worst-case asymptotic running time cost: theta(n)
	 *
	 *         Justification: worst case is when you want insert before the last
	 *         element in the list. In this case the program must loop through the
	 *         entire list before inserting it. Therefore, order of growth is
	 *         theta(n)
	 */
	public void insertBefore(int pos, T data) {
		DLLNode newNode;
		if (!isEmpty()) {
			if (pos <= 0) {
				newNode = new DLLNode(data, null, this.head);
				this.head.prev = newNode;
				this.head = newNode;
			} else if (pos > size - 1) {
				newNode = new DLLNode(data, this.tail, null);
				this.tail.next = newNode;
				this.tail = newNode;
			} else {
				int count = 0;
				for (DLLNode search = head; search != null; search = search.next) {
					if (pos == count) {
						newNode = new DLLNode(data, search.prev, search);
						break;
					}
					count++;
				}
			}
		} else { // inserting first node
			newNode = new DLLNode(data, null, null);
			this.head = newNode;
			this.tail = newNode;
		}
	}

	/**
	 * Returns the data stored at a particular position
	 * 
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null
	 *         otherwise.
	 *
	 *         Worst-case asymptotic running time cost: theta(n)
	 *
	 *         Justification: Worst case scenario it has to loop through the entire
	 *         list again. Therefore, the order of growth would be theta(n) if n is
	 *         the size of the DLL.
	 *
	 */
	public T get(int pos) {
		int currentPos = 0;
		T data = null;
		if (pos > size - 1) {
			return data;
		}
		if (pos < 0) {
			return data;
		} else if (pos == 0) {
			return head.data;
		} else {
			DLLNode search = head;
			while (pos > currentPos) {
				search = search.next;
				currentPos++;
			}
			return search.data;
		}
	}

	/**
	 * Deletes the element of the list at position pos. First element in the list
	 * has position 0. If pos points outside the elements of the list then no
	 * modification happens to the list.
	 * 
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified.
	 *
	 *         Worst-case asymptotic running time cost: theta(n)
	 *
	 *         Justification: same justification as previous method ("get"). Worst
	 *         case involves looping through entire linked list of n elements,
	 *         giving a running time of theta(n).
	 */
	public boolean deleteAt(int pos) {
		if (pos > size - 1) {
			return false;
		} else if (isEmpty()) {
			return false;
		} else if (pos < 0) {
			return false;
		} else if (pos == 0) {
			if (size == 1) {
				head = null;
				tail = null;
				size = 0;
			} else {
				head = head.next;
				head.prev = null;
				size--;
			}
			return true;
		} else if (pos == size - 1) {
			tail = tail.prev;
			tail.next = null;
			size--;
			return true;
		} else {
			int currentPos = 0;
			DLLNode search = head;
			while (pos > currentPos) {
				search = search.next;
				currentPos++;
			}
			search.prev.next = search.next;
			search.next.prev = search.prev;
			search.next = null;
			search.prev = null;
			size--;
			return true;
		}
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the method
	 * is called Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: theta(n)
	 *
	 * Justification: Like the last two methods, the worst case scenario involves
	 * looping through the entire list of n elements.
	 */
	public void reverse() {
		if (!isEmpty()) {
			if (size > 1) {
				DLLNode temp = head;
				head = tail;
				tail = temp;
				DLLNode temp2 = head;
				while (temp2 != null) {
					temp = temp2.next;
					temp2.next = temp2.prev;
					temp2.prev = temp;
					temp2 = temp2.next;
				}
			}
		}
	}

	/**
	 * Removes all duplicate elements from the list. The method should remove the
	 * _least_number_ of elements to make all elements uniqueue. If the list
	 * contains "A", "B", "C", "B", "D", "A" before the method is called Then it
	 * should contain "A", "B", "C", "D" after it returns. The relative order of
	 * elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: theta(n^2)
	 *
	 * Justification: The ordr of growth is higher for this method because there is
	 * a second loop involved. In the worst case, both while loops will have to
	 * iterate through the entire list of n elements, giving it a running time of
	 * theta(N^2)
	 */
	public void makeUnique() {
		DLLNode search = head;
		while (search != null) {
			DLLNode search2 = head.next;
			int count = 1;
			while (search2 != null) {
				if (search.data == search2.data) {
					deleteAt(count);
				}
				search2 = search2.next;
				count++;
			}
			search = search.next;
		}
	}

	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item : the item to push on the stack
	 *
	 *             Worst-case asymptotic running time cost: theta(n)
	 *
	 *             Justification: When calculating worst case running time one must
	 *             consider the running time of the methods being called. In this
	 *             case "insertBefore" gives this method a worst-case running time
	 *             of theta(n)
	 */
	public void push(T item) {
		insertBefore(0, item);
		size++;
	}

	/**
	 * This method returns and removes the element that was most recently added by
	 * the push method.
	 * 
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 *         Worst-case asymptotic running time cost: theta(n)
	 *
	 *         Justification: In the worst case scenario, this method calls the
	 *         method "deleteAt", which has a worst case running time of theta(N)
	 */
	public T pop() {
		if (isEmpty()) {
			return null;
		} else {
			T data = head.data;
			head = head.next;
			deleteAt(0);
			return data;
		}

	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item : the item to be enqueued to the stack
	 *
	 *             Worst-case asymptotic running time cost: theta(1)
	 *
	 *             Justification: None of the operations I used have a cost greater
	 *             than theta(1), making theta(1) the worst case running time.
	 */
	public void enqueue(T item) {
		if (isEmpty()) {
			DLLNode newNode = new DLLNode(item, null, null);
			head = newNode;
			tail = newNode;
			size = 1;
		} else {
			DLLNode temp = tail;
			tail = new DLLNode(item, temp, null);
			temp.next = tail;
			size++;
		}
	}

	/**
	 * This method returns and removes the element that was least recently added by
	 * the enqueue method.
	 * 
	 * @return the earliest item inserted with an equeue; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic running time cost: theta(n)
	 *
	 *         Justification: Similar to the "pop" method, the worst case of this
	 *         method involves a call to "deleteAt" which gives it a worst case
	 *         running time of theta(n).
	 */
	public T dequeue() {
		if (isEmpty()) {
			return null;
		} else if (size == 1) {
			T data = head.data;
			head = null;
			tail = null;
			size = 0;
			return data;
		} else if (size == 2) {
			T data = head.data;
			tail = head;
			size--;
			return data;
		} else {
			T data = head.data;
			tail = tail.prev;
			deleteAt(size);
			size--;
			return data;
		}
	}

	/**
	 * @return a string with the elements of the list as a comma-separated list,
	 *         from beginning to end
	 *
	 *         Worst-case asymptotic running time cost: Theta(n)
	 *
	 *         Justification: We know from the Java documentation that
	 *         StringBuilder's append() method runs in Theta(1) asymptotic time. We
	 *         assume all other method calls here (e.g., the iterator methods above,
	 *         and the toString method) will execute in Theta(1) time. Thus, every
	 *         one iteration of the for-loop will have cost Theta(1). Suppose the
	 *         doubly-linked list has 'n' elements. The for-loop will always iterate
	 *         over all n elements of the list, and therefore the total cost of this
	 *         method will be n*Theta(1) = Theta(n).
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			if (!isFirst) {
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

}
