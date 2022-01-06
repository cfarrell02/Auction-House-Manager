package Utils;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    public CoolHashTable<String> hashTable;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    hashTable = new CoolHashTable<>(1000);
       // hashTable.add("CianFarrell","Testing123");
        for (int i = 0; i < 10; ++i) {
            String key = "Hello"+i;
            String add = "Hello"+i;
            hashTable.add(key,add);
        }
//        hashTable.add("Hello","Testing2");
//        hashTable.add("Conor","Testing3");
    }

    @Test
    void get() {
        assertEquals("Testing123",hashTable.get("CianFarrell"));
        assertEquals("Testing2",hashTable.get("Hello"));
        assertEquals("Testing3",hashTable.get("Conor"));

    }

    @Test
    void add() {



    }

    @Test
    void delete() {
        assertEquals("Hello0", hashTable.get(0));
        hashTable.remove(0);
        assertNotEquals("Hello0", hashTable.get(0));
        assertEquals("Hello5", hashTable.get(4));
        hashTable.remove(4);
        assertNotEquals("Hello5", hashTable.get(4));
        assertEquals("Hello9", hashTable.get(7));
        hashTable.remove(7);
        assertNotEquals("Hello9", hashTable.get(7));
    }
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
