package Models;

import Utils.CoolLinkedList;

public class Bidder {
    private String name,address,telephone,email;
    private CoolLinkedList<Bid> bids;

    public Bidder(String name, String address, String telephone, String email) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.bids = new CoolLinkedList<>();
    }

    public CoolLinkedList<Bid> getBids() {
        return bids;
    }

    public void setBids(CoolLinkedList<Bid> bids) {
        this.bids = bids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bid getBid(int index){
        return bids.get(index);
    }
    public void editBid(int oldIndex,Bid bid){
        bids.set(oldIndex,bid);
    }

    public void removeBid(int index){
        bids.remove(index);
    }
    @Override
    public String toString() {
        return "Bidder: " + name + " Address: "+ address + " Phone: " + telephone + " Email: " + email;
    }
}
