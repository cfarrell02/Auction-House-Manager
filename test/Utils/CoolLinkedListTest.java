package Utils;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class CoolLinkedListTest {
    public CoolLinkedList<String> list = new CoolLinkedList<>();
    public CoolHashTable<String, String> hashTable;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        for (int i = 0; i < 10; ++i) {
            list.add("Hello" + i);
        }
    }

//    }
//    @Test
//    void search(){
//        for(String name: list){
//            System.out.println(name);
//            if(name.equals("Testing123")){
//                break;
//            }
//        }
//    }

    @org.junit.jupiter.api.Test
    void add() {
        assertTrue(list.contains("Hello0"));
        assertTrue(list.contains("Hello9"));
        assertFalse(list.contains("Hello11"));
    }

    @org.junit.jupiter.api.Test
    void get() {
        assertEquals("Hello0", list.get(0));
        assertEquals("Hello3", list.get(3));
        assertEquals("Hello4", list.get(4));
        assertEquals("Hello5", list.get(5));
        assertEquals("Hello6", list.get(6));
        assertEquals("Hello8", list.get(8));
        assertEquals("Hello9", list.get(9));

    }

    @Test
    void delete() {
        assertEquals("Hello0", list.get(0));
        list.remove(0);
        assertNotEquals("Hello0", list.get(0));
        assertEquals("Hello5", list.get(4));
        list.remove(4);
        assertNotEquals("Hello5", list.get(4));
        assertEquals("Hello9", list.get(7));
        list.remove(7);
        assertNotEquals("Hello9", list.get(7));

    }

    @org.junit.jupiter.api.Test
    void contains() {

        assertTrue(list.contains("Hello5"));
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void replace() {
        assertEquals("Hello3", list.get(3));
        list.set(3, "New!");
        assertNotEquals("Hello3", list.get(3));
        assertEquals("New!", list.get(3));

        assertEquals("Hello7", list.get(7));
        list.set(7, "New!");
        assertNotEquals("Hello7", list.get(7));
        assertEquals("New!", list.get(7));


    }

    @Test
    void sort() {
        list.mergeSort(Comparator.comparing(String::toString));
        System.out.println("--Sorted--");
        for (String word : list)
            System.out.println(word);
    }

}
