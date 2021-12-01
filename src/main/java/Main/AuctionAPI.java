package Main;

import Models.AuctionLot;
import Models.Bid;
import Models.Bidder;
import Utils.CoolLinkedList;

public class AuctionAPI {

    private CoolLinkedList<AuctionLot> auctionLots;
    private CoolLinkedList<Bidder> bidders;

    public AuctionAPI() {
        this.bidders = new CoolLinkedList<>();
        this.auctionLots = new CoolLinkedList<>();
    }
    //Auction Lot Methods
    public CoolLinkedList<AuctionLot> getAuctionLots() {
        return auctionLots;
    }

    public void setAuctionLots(CoolLinkedList<AuctionLot> auctionLots) {
        this.auctionLots = auctionLots;
    }
    public void addAuctionLot(AuctionLot auctionLot){
        auctionLots.add(auctionLot);
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

    //Bidder Methods
    public CoolLinkedList<Bidder> getBidders() {
        return bidders;
    }

    public void setBidders(CoolLinkedList<Bidder> bidders) {
        this.bidders = bidders;
    }
    public Bidder getBidder(int index){
        return bidders.get(index);
    }
    public void addBidder(Bidder bidder){
        bidders.add(bidder);
    }
    public void removeBidder(int index){
        bidders.remove(index);
    }
    public void editBidder(int oldIndex,Bidder bidder){
        bidders.set(oldIndex,bidder);
    }
}
