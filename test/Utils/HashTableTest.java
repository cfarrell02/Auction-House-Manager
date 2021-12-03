package Utils;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    public CoolHashTable<String,String> hashTable;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    hashTable = new CoolHashTable<>(100000);
        for (int i = 0; i < 10000; ++i) {
            String key = Utilities.getAlphaNumericString(3);
            String add = "String "+i;
            hashTable.add(key,add);
        }
        hashTable.add("CianFarrell","Testing123");
        System.out.println(hashTable.toString());
//        for(int i=0;i<100;++i)
//            System.out.println("Index "+i+" "+hashTable.size(i));
//        hashTable.add("Hello","Testing2");
//        hashTable.add("Conor","Testing3");
    }

    @Test
    void get() {
       // System.out.println(hashTable.toString());
//        for(int i = 0;i<10000;++i) System.out.println(i+" "+hashTable.size(i));
        assertEquals("Testing123",hashTable.get("CianFarrell"));

//        assertEquals("Testing2",hashTable.get("Hello"));
//        assertEquals("Testing3",hashTable.get("Conor"));

    }
//
//    @Test
//    void add() {
//
//    }
//
//    @Test
//    void delete() {
//
//    }
//
//    @Test
//    void contains() {
//
//    }
//
//    @Test
//    void isEmpty() {
//    }
//
//    @Test
//    void replace() {
//
//
//
//    }

}
