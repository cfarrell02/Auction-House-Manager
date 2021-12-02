package Utils;

import Main.MainController;
import javafx.scene.control.ListView;

import java.util.Iterator;

public class HashTable<K,D> {
    public int size, tableLength;
    private CoolLinkedList[] table;
    private CoolLinkedList[] keys;


    public HashTable(int tableLength) {
        this.size = 0;
        this.tableLength = tableLength;
        this.table = new CoolLinkedList[tableLength];
        this.keys = new CoolLinkedList[tableLength];
        System.out.println(tableLength);
    }
    private int hashFunction(K key) {
        return Math.abs(key.hashCode() % tableLength);
    }
    public boolean add(K key,D item){
  //     if(uniqueKey(key)){
            int index = hashFunction(key);
            if(table[index]!=null)
            table[index].add(0,item);
            if(keys[index]!=null)
            keys[index].add(0,key);
            ++size;
            return true;
   //     }
   //         return false;
    }
    private boolean uniqueKey(K key){
        for(Object currentKey:keys[hashFunction(key)]){
            if(currentKey.equals(key)) return false;
        }
        return true;
    }
    public D get(K key) {
        int index1 = hashFunction(key);
        int index2 = 0;
        for (Object currentKey : keys[index1]) {
            if (currentKey.equals(key)) break;
            ++index2;
        }
        return (D) table[index1].get(index2);
    }
//    public CoolLinkedList<D> toCoolLinkedList(){
//        CoolLinkedList<D> newList = new CoolLinkedList<>();
//        for(CoolLinkedList<D> list: table){
//            for(D item:list) newList.add(item);
//        }
//        return newList;
//    }
//    public void addToListView(ListView<String> newList){
//        for(CoolLinkedList<D> list: table){
//            for(D item:list) newList.getItems().add(item.toString());
//        }
//    }
//    public boolean remove(K key){
//        int index1 = hashFunction(key);
//        int index2 = 0;
//        boolean found= false;
//        for(K currentKey:keys[index1]){
//            if(currentKey.equals(key)){
//                found=true;
//                break;}
//            ++index2;
//        }
//        if(found)
//        table[index1].remove(index2);
//        return found;
//    }


//    public class HashIterator<G> implements Iterator<G> {
//        private HashTable<G> hashTable;
//        private String key;
//        public HashIterator(HashTable<G> hashTable,String key){
//            this.hashTable=hashTable;
//            this.key=key;
//        }
//        @Override
//        public boolean hasNext() {
//            return pos!= null;
//        }
//
//        @Override
//        public G next() {
//            node temp=pos;
//            pos=pos.next;
//            return (G) temp.getContents();
//        }
//    }
}
