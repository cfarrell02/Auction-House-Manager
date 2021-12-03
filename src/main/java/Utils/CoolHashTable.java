package Utils;

import java.util.Comparator;
import java.util.Objects;

public class CoolHashTable<K,F> {
    public Entry<K,F>[] table;
    private int size,length;

    public CoolHashTable(int length) {
        this.size = 0;
        this.length = length;
        table = new Entry[length];
        for(int i = 0;i<length;++i) table[i]=new Entry("null","null");
    }

    public int hashFunction(K key){
        return Math.abs(key.hashCode() % length);
    }

    public void add(K key,F object) {
            int index = hashFunction(key);
            Entry<K, F> item = new Entry<>(key, object);
            Entry<K, F> head = table[index];
            if(head.key!=null){
                head.previous = item;
                item.next = head;}
                table[index] = item;
            ++size;
        }


//    public F get(K key){
//        Entry<K,F> item = getEntry(key);
//        return item==null ? null: item.value;
//    }

    public F get(K key){
        Entry<K,F> temp = table[hashFunction(key)];
        while(temp!=null){
            if(temp.key.equals(key)) return temp.value;
            temp=temp.next;
        }
        return null;
    }

//public F remove(K key){
//        Entry<K,F> item = getEntry(key);
//        if(table[hashFunction(key)].equals(item)){
//            table[hashFunction(key)] = item.next;
//        }else{
//            if(item.next!=null)
//            item.previous.next=item.next;
//            else item.previous.next=null;
//        }
//        return item.value;
//}


    public int size(){
        return size;}
    public int size(int key){
        Entry<K,F> temp = table[key];
        for(int i=0;temp!=null;++i,temp=temp.next){
        if(temp.next == null) return i;
        }
        return -1;
    }



    public boolean isEmpty(){
        return size == 0;
    }

    public String toString(){
    StringBuilder list = new StringBuilder("");
    for(Entry<K,F> entry:table){
        if(entry.value!=null){list.append("\n"+entry.value);
        Entry<K,F>temp = entry;
        while(temp!=null){
            list.append("\n"+temp.value);
            temp = temp.next;
        }}
    }
    return list.toString();
    }
}



class Entry<K,F> {
    public K key;
    public F value;
    Entry<K,F> next = null,previous = null;

    public Entry(K key, F value) {
        this.key = key;
        this.value = value;
    }
    public String toString(){
        return "Key = "+key+
                "\nValue = "+ value+
                "\nNext = "+next+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return Objects.equals(key, entry.key) && Objects.equals(value, entry.value) && Objects.equals(next, entry.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, next);
    }
}