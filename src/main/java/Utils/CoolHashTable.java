package Utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class CoolHashTable<F> {
    public Entry<F>[] intTable,keyTable;
    private int size;

    public CoolHashTable(int length) {
        this.size = 0;
        intTable = new Entry[length];
        keyTable = new Entry[length];
        for (int i = 0; i < length; ++i) intTable[i] = new Entry("-1", "null");
        for (int i = 0; i < length; ++i) keyTable[i] = new Entry("", "null");
    }

    public int hashFunction(int key) {
        return Math.abs(key % intTable.length);
    }
    public int hashFunction(String key) {
        return Math.abs(key.hashCode() % keyTable.length);
    }

    public void clear() {
        this.size = 0;
//        intTable = new Entry[intTable.length];
//        keyTable = new Entry[keyTable.length];
        for (int i = 0; i < intTable.length; ++i) intTable[i] = new Entry("-1", "null");
        for (int i = 0; i < keyTable.length; ++i) keyTable[i] = new Entry("-1", "null");
    }

    public boolean contains(F item) {
        for (Entry<F> entry : intTable) {
            Entry<F> temp = entry;
            while (temp != null) {
                if (temp.equals(item)) return true;
                temp = temp.next;
            }
        }
        return false;
    }

    public boolean add(String key,F object) {
        if(get(key)==null){
        int index1 = hashFunction(size);
        int index2 = hashFunction(key);
        Entry<F> item1 = new Entry<>(String.valueOf(size), object);
        Entry<F> intHead = intTable[index1];
        if (!intHead.key.equals("-1")) {
            intHead.previous = item1;
            item1.next = intHead;
        }
        intTable[index1] = item1;
        Entry<F> keyHead = keyTable[index2];
        Entry<F> item2 = new Entry<>(key, object);
        //keyTable[index2] = item;
        if (!keyHead.key.equals("")) {
            keyHead.previous = item2;
            item2.next = keyHead;
        }
        keyTable[index2] = item2;
        ++size;
                return true;}
        return false;

    }


    public F get(int key) {
        Entry<F> item = getEntry(key);
        return item == null ? null : item.value;
    }

    private Entry<F> getEntry(int key) {
        Entry<F> temp = intTable[hashFunction(key)];
        while (temp != null) {
            if (Integer.parseInt(temp.key) == key)
                return temp;
            temp = temp.next;
        }
        return null;
    }
    public F get(String key) {

        Entry<F> item = getEntry(key);
        return item == null ? null : item.value;
    }

    private Entry<F> getEntry(String key) {
        Entry<F> temp = keyTable[hashFunction(key)];
        while (temp != null) {
            if (temp.key.equals(key)) return temp;
            temp = temp.next;
        }
        return null;
    }

//    private F removeItem(int key) {
//
//        Entry<F> item = getEntry(key);
//        if (item != null) {
//            if (intTable[hashFunction(key)].equals(item)) {
//                intTable[hashFunction(key)] = item.next;
//            } else {
//                if (item.next != null)
//                    item.previous.next = item.next;
//                else item.previous.next = null;
//            }
//            return item.value;
//        }
//        return null;
//    }

    public F remove(int key) {
        F deletedItem = get(key);
        CoolLinkedList<Entry<F>> temp = new CoolLinkedList<>();
        for (int i = 0; i < size; ++i) {
            if (i != key) temp.add(getEntry(i));
        }
        clear();
        for (Entry<F> item : temp) {
            add(item.key,item.value);
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
        Entry<F> temp = intTable[key];
        for (int i = 0; temp != null; ++i, temp = temp.next) {
            if (temp.next == null) return i;
        }
        return -1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder list = new StringBuilder();
        for (Entry<F> entry : intTable) {
            if (entry.value != null&&!entry.value.equals("null")) {
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
    public String key;
    public F value;
    Entry<F> next = null,previous = null;

    public Entry(String key, F value) {
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

