package woo;

public class EliteStatus extends ClientStatus{

    public EliteStatus(Client client){
        super(client);
    }

    public void verifyStatus(){
        if(super.getClient().getPoints() <= 2000){
            super.getClient().setStatus(new NormalStatus(super.getClient()));
        }
        if(super.getClient().getPoints() > 2000 && super.getClient().getPoints() <= 25000){
            super.getClient().setStatus(new SelectionStatus(super.getClient()));
        }
    }

    @Override
    @SuppressWarnings("nls")
    public String toString(){
        return "ELITE";
    }
}