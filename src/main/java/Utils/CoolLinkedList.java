package Utils;

import java.util.Iterator;

public class CoolLinkedList<F> implements Iterable<F> {
    public node<F> head;
    public node<F> tail ;
    private int size;

    public boolean add(F e) { //Add element to head of list
        node<F> fn = new node<>();
        fn.setContents(e);
        if(tail != null)
        tail.next = fn;
        fn.previous = tail;
        tail = fn;
        if(size ==0)
            head=fn;
        ++size;
        return true;
    }
    public F set(int oldIndex, F newObject){
        if(oldIndex>=0&&oldIndex<size){
        node<F> oldNode = getNode(oldIndex);
        node<F> newNode = new node<>();
        newNode.setContents(newObject);
        if(size==1){
            head = newNode;
            tail = newNode;
        }else {
            if (oldIndex == 0) {
                newNode.next = head.next;
                head.next.previous = newNode;
                head = newNode;
            } else if (oldIndex == (size - 1)) {
                newNode.previous = newNode;
                tail.previous.next = newNode;
                tail = newNode;
            } else {
                newNode.previous = oldNode.previous;
                oldNode.previous.next = newNode;
                newNode.next = oldNode.next;
                oldNode.next.previous = newNode;
            }
        }
        return oldNode.getContents();
        }
        return null;
    }

    public int size(){
        return size;}
    public void clear() { //Empty list
        head = null;
        tail = null;
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public F remove(int index){
        node<F> deletedNode = getNode(index);

        if(size ==1)
            clear();
        else {
            if (index == 0) {
                head = head.next;
                head.previous = null;
            } else if (index == size - 1) {
                tail = tail.previous;
                tail.next = null;
            } else {
                deletedNode.next.previous = deletedNode.previous;
                deletedNode.previous.next = deletedNode.next;
            }
        }
        size--;
        return deletedNode.getContents();

    }

    private node<F> getNode(int index) {
        if (index <= (size-1)/2) {
            node<F> temp = head;
            for (int i = 0; i <= index && temp != null; ++i) {
                if (i == index)
                    return temp;
                temp = temp.next;
            }
      }
        else {
            node temp = tail;
            for (int i = size-1; i >= index && temp != null; --i) {
                if (i == index)
                    return temp;
                temp = temp.previous;
            }
        }
        return null;
    }
    public F get(int index){
        if(getNode(index)!=null)
        return getNode(index).getContents();
            return null;
    }



    public void add(int index, F element) {
        node<F> fn = new node<>();
        fn.setContents(element);
        if(head==null)
            head = fn;
        if(tail==null)
            tail = fn;
        if(index == 0){
            fn.next = head;
            head = fn;
        }
        else if(index == size-1){
            fn.previous = tail;
            tail = fn;
        }else{
            fn.previous=getNode(index-1);
            fn.next=getNode(index);
            fn.previous.next = fn;
            fn.next.previous = fn;
        }

        size ++;
    }



    public int indexOf(Object o) {
        node<F> temp = head;
        for(int i=0;temp!=null;++i){
            temp = temp.next;
            if(temp.getContents().equals(o))
                return i;
        }
        return -1;
    }




    public boolean contains(F object){
        node<F> temp = head;
        while(temp!=null){
            if(temp.getContents().equals(object))
                return true;

            temp = temp.next;
        }
        return false;
    }


    public Iterator<F> iterator() {
        return new CoolIterator<F>(head);
    }



}