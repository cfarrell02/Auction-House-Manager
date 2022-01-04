package Main;

import Models.AuctionLot;
import Models.Bid;
import Models.Bidder;
import Utils.CoolHashTable;
import Utils.CoolLinkedList;

public class AuctionAPI {

    private CoolHashTable<AuctionLot> auctionLots;
    private CoolHashTable<Bidder> bidders;

    public AuctionAPI() {
        this.bidders = new CoolHashTable<>(1000);
        this.auctionLots = new CoolHashTable<>(1000);
    }
    //Auction Lot Methods
    public CoolHashTable<AuctionLot> getAuctionLots() {
        return auctionLots;
    }

    public void setAuctionLots(CoolHashTable<AuctionLot> auctionLots) {
        this.auctionLots = auctionLots;
    }
    public void addAuctionLot(AuctionLot auctionLot){
        auctionLots.add(auctionLot.getTitle(),auctionLot);
    }
    public AuctionLot getAuctionLot(int index){
        return auctionLots.get(index);
    }
    public void removeAuctionLot(int index){
        auctionLots.remove(index);
    }
    public void editAuctionLot(int oldIndex,AuctionLot auctionLot){
        auctionLots.set(oldIndex,auctionLot);
    }


    public AuctionLot findLotByName(String auctionTitle){
        for (int i = 0;i<auctionLots.size();++i){
            if(auctionLots.get(i).getTitle().equals(auctionTitle))
                return auctionLots.get(i);
        }return null;
    }

    //Bidder Methods
    public CoolHashTable<Bidder> getBidders() {
        return bidders;
    }

    public void setBidders(CoolHashTable<Bidder> bidders) {
        this.bidders = bidders;
    }
    public Bidder getBidder(int index){
        return bidders.get(index);
    }
    public void addBidder(Bidder bidder){
        bidders.add(bidder.getName(),bidder);
    }
    public void removeBidder(int index){
        bidders.remove(index);
    }
    public void editBidder(int oldIndex,Bidder bidder){
        bidders.set(oldIndex,bidder);
    }
}
