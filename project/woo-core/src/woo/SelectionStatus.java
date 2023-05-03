package woo;

public class SelectionStatus extends ClientStatus{

    public SelectionStatus(Client client){
        super(client);
    }

    public void verifyStatus(){
        if(super.getClient().getPoints() <= 2000){
            super.getClient().setStatus(new NormalStatus(super.getClient()));
        }
        if(super.getClient().getPoints() > 25000){
            super.getClient().setStatus(new EliteStatus(super.getClient()));
        }
    }

    @Override
    @SuppressWarnings("nls")
    public String toString(){
        return "SELECTION";
    }
}