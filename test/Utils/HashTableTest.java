package Utils;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    public HashTable<Integer,String> hashTable;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    hashTable = new HashTable<>(100);
        for (int i = 0; i < 10000; ++i) {
            int key =  i;
            String add = Utilities.getAlphaNumericString(6);
            hashTable.add(key,add);
        }
    }

    @Test
    void add() {

    }

    @Test
    void get() {
//        System.out.println(hashTable.get(6324));
    }

    @Test
    void delete() {

    }

    @Test
    void contains() {

    }

    @Test
    void isEmpty() {
    }

    @Test
    void replace() {



    }

}
