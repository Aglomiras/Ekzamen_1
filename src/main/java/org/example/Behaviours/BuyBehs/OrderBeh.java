package org.example.Behaviours.BuyBehs;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import org.example.Help.DfHelper;

public class OrderBeh extends WakerBehaviour {
    private String book;
    public OrderBeh(Agent a, long wakeupDate, String book) {
        super(a, wakeupDate);
        this.book = book;
    }

    @Override
    public void onWake() {
        var ags = DfHelper.search(myAgent, "selling_books");
        ACLMessage aclMessage = new ACLMessage(ACLMessage.AGREE);
        aclMessage.setContent(book);
        ags.forEach(e -> aclMessage.addReceiver(e));
        myAgent.send(aclMessage);
    }
}
