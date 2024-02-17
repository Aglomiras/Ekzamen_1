package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.Data;

@Data
public class ProcessingPROXYBeh extends Behaviour {
    private MessageTemplate messageTemplate;
    private int count = 0;
    private boolean flag = false;

    @Override
    public void onStart() {
        this.messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
    }

    @Override
    public void action() {
        ACLMessage receive = myAgent.receive(messageTemplate);
        if (receive != null) {
            count++;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }

}
