package Utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class CoolHashTable<F> {
    public Entry<F>[] table;
    private int size;

    public CoolHashTable(int length) {
        this.size = 0;
        table = new Entry[length];
        for (int i = 0; i < length; ++i) table[i] = new Entry(-1, "null");
    }

    public int hashFunction(int key) {
        return Math.abs(key % table.length);
    }

    public void clear() {
        this.size = 0;
        for (int i = 0; i < table.length; ++i) table[i] = new Entry(-1, "null");
    }

    public boolean contains(F item) {
        for (Entry<F> entry : table) {
            Entry<F> temp = entry;
            while (temp != null) {
                if (temp.equals(item)) return true;
                temp = temp.next;
            }
        }
        return false;
    }

    public int add(F object) {
        int index = hashFunction(size);
        Entry<F> item = new Entry<>(size, object);
        Entry<F> head = table[index];
        if (head.key != -1) {
            head.previous = item;
            item.next = head;
        }
        table[index] = item;
        return size++;

    }


    public F get(int key) {
        Entry<F> item = getEntry(key);
        return item == null ? null : item.value;
    }

    private Entry<F> getEntry(int key) {
        Entry<F> temp = table[hashFunction(key)];
        while (temp != null) {
            if (temp.key == key) return temp;
            temp = temp.next;
        }
        return null;
    }

    private F removeItem(int key) {

        Entry<F> item = getEntry(key);
        if (item != null) {
            if (table[hashFunction(key)].equals(item)) {
                table[hashFunction(key)] = item.next;
            } else {
                if (item.next != null)
                    item.previous.next = item.next;
                else item.previous.next = null;
            }
            return item.value;
        }
        return null;
    }

    public F remove(int key) {
        F deletedItem = get(key);
        CoolLinkedList<F> temp = new CoolLinkedList<>();
        for (int i = 0; i < size; ++i) {
            if (i != key) temp.add(get(i));
        }
        clear();
        for (F item : temp) {
            ++size;
            add(item);
        }
        return deletedItem;
    }

    public void set(int key, F object) {
        if (getEntry(key) != null)
            getEntry(key).value = object;
    }


    public int size() {
        return size;
    }

    public int size(int key) {
        Entry<F> temp = table[key];
        for (int i = 0; temp != null; ++i, temp = temp.next) {
            if (temp.next == null) return i;
        }
        return -1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder list = new StringBuilder("");
        for (Entry<F> entry : table) {
            if (entry.value != null) {
                list.append("\n" + entry.value);
                Entry<F> temp = entry;
                while (temp != null) {
                    list.append("\n" + temp.value);
                    temp = temp.next;
                }
            }
        }
        return list.toString();
    }


    public CoolLinkedList<F> toCoolLinkedList() {
    CoolLinkedList<F> newList = new CoolLinkedList<>();
    for(int i = 0;i<size;++i){
        newList.add(get(i));
    }
    return newList;
    }

}

class Entry<F> {
    public int key;
    public F value;
    Entry<F> next = null,previous = null;

    public Entry(int key, F value) {
        this.key = key;
        this.value = value;
    }
    public String toString(){
        return "Key = "+key+
                "\nValue = "+ value+
                "\nNext = "+next+"\n";
    }


    @Override
    public int hashCode() {
        return Objects.hash(key, value, next);
    }
}

