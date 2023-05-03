package woo;

public class NormalStatus extends ClientStatus{

    public NormalStatus(Client client){
        super(client);
    }

    public void verifyStatus(){
        if(super.getClient().getPoints() > 2000 && super.getClient().getPoints() <= 25000){
            super.getClient().setStatus(new SelectionStatus(super.getClient()));
        }
        if(super.getClient().getPoints() > 25000){
            super.getClient().setStatus(new EliteStatus(super.getClient()));
        }
    }

    @Override
    @SuppressWarnings("nls")
    public String toString(){
        return "NORMAL";
    }
}