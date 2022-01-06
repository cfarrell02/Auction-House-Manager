package Main;

import Models.AuctionLot;
import Models.Bidder;
import Utils.CoolLinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuctionAPITest {
    AuctionAPI auctionAPI = new AuctionAPI();
    @BeforeEach
    void setUp() {
        for(int i = 0; i < 10; ++i){
            auctionAPI.addAuctionLot(new AuctionLot("lot" + i,"","","",2000+i,i));
        }
        for(int i = 0; i < 10; ++i){
            auctionAPI.addBidder(new Bidder("bidder" + i,"",100*i+"",""));
        }
    }

    @AfterEach
    void tearDown() {
        auctionAPI.getAuctionLots().clear();
        auctionAPI.getBidders().clear();
    }

    @Test
    void addAuctionLot() {
        assertEquals(10, auctionAPI.getAuctionLots().size() );
        auctionAPI.addAuctionLot(new AuctionLot("lot11", "", "", "", 2001, 1));
        assertEquals(11, auctionAPI.getAuctionLots().size() );
        assertEquals("lot11",auctionAPI.getAuctionLot(10).getTitle());

    }

    @Test
    void getAuctionLot() {
        assertEquals("lot1", auctionAPI.getAuctionLots().get(1).getTitle());
        assertEquals("lot2", auctionAPI.getAuctionLots().get(2).getTitle());
        assertEquals("lot3", auctionAPI.getAuctionLots().get(3).getTitle());
        assertEquals("lot7", auctionAPI.getAuctionLots().get(7).getTitle());
        assertEquals("lot9", auctionAPI.getAuctionLots().get(9).getTitle());

    }

    @Test
    void removeAuctionLot() {
        assertEquals("lot0", auctionAPI.getAuctionLots().get(0).getTitle());
        auctionAPI.getAuctionLots().remove(0);
        assertNotEquals("lot0", auctionAPI.getAuctionLots().get(0).getTitle());
        assertEquals("lot3", auctionAPI.getAuctionLots().get(2).getTitle());

    }

    @Test
    void editAuctionLot() {
        assertEquals("lot0", auctionAPI.getAuctionLots().get(0).getTitle());
        auctionAPI.editAuctionLot(0, new AuctionLot("new", "", "", "", 2001, 20));
        assertEquals("new", auctionAPI.getAuctionLots().get(0).getTitle());
        assertNotEquals("lot0", auctionAPI.getAuctionLots().get(0).getTitle());
        assertEquals("lot6", auctionAPI.getAuctionLots().get(6).getTitle());
        auctionAPI.editAuctionLot(6, new AuctionLot("new2", "", "", "", 2001, 20));
        assertEquals("new2", auctionAPI.getAuctionLots().get(6).getTitle());
        assertNotEquals("lot6", auctionAPI.getAuctionLots().get(6).getTitle());



    }

    @Test
    void findLotByName() {
        assertEquals(2003,auctionAPI.findLotByName("lot3").getYear());
        assertEquals(2008,auctionAPI.findLotByName("lot8").getYear());
    }

    @Test
    void getBidder() {
        assertEquals("bidder0",auctionAPI.getBidder(0).getName());
        assertEquals("bidder3",auctionAPI.getBidder(3).getName());
        assertEquals("bidder5",auctionAPI.getBidder(5).getName());
        assertEquals("bidder9",auctionAPI.getBidder(9).getName());
    }

    @Test
    void addBidder() {
        assertEquals(10,auctionAPI.getBidders().size());
        auctionAPI.addBidder(new Bidder("bidder10","","",""));
        assertEquals(11,auctionAPI.getBidders().size());
        assertEquals("bidder10",auctionAPI.getBidder(10).getName());
    }

    @Test
    void removeBidder() {
        assertEquals(10,auctionAPI.getBidders().size());
        auctionAPI.removeBidder(9);
        assertEquals(9,auctionAPI.getBidders().size());
        assertNull(auctionAPI.getBidder(10));
    }

    @Test
    void editBidder() {
        assertEquals("bidder2",auctionAPI.getBidder(2).getName());
        auctionAPI.editBidder(2,new Bidder("hello","","",""));
        assertNotEquals("bidder2",auctionAPI.getBidder(2).getName());
        assertEquals("hello",auctionAPI.getBidder(2).getName());
        assertEquals("bidder7",auctionAPI.getBidder(7).getName());
        auctionAPI.editBidder(7,new Bidder("hello","","",""));
        assertNotEquals("bidder7",auctionAPI.getBidder(7).getName());
        assertEquals("hello",auctionAPI.getBidder(7).getName());
    }

    @Test
    void findBidderByName() {
        assertEquals("300",auctionAPI.findBidderByName("bidder3").getTelephone());
        assertEquals("800",auctionAPI.findBidderByName("bidder8").getTelephone());

    }
}