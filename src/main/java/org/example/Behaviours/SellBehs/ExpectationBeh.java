package org.example.Behaviours.SellBehs;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExpectationBeh extends Behaviour {
    private MessageTemplate messageTemplate;
    private List<String> books;
    private boolean flag = false;

    public ExpectationBeh(List<String> books) {
        this.books = books;
    }

    @Override
    public void onStart() {
        messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.AGREE);
    }

    @Override
    public void action() {
        ACLMessage receive = myAgent.receive(messageTemplate);

        if (receive != null) {
            String rightBook = receive.getContent();
            if (books.contains(rightBook)) {
                setMsg(receive.getSender());
            } else {
                setMsg1(receive.getSender());
                log.info("Нет нужной книги " + this.myAgent.getLocalName());
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return flag;
    }

    public void setMsg(AID aid) {
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);
        String gold = String.valueOf(getGold());
        log.info("Есть нужная книга, готов продать за " + gold + " " + this.myAgent.getLocalName());
        msg.setContent(gold);
        msg.addReceiver(aid);
        myAgent.send(msg);
    }

    public void setMsg1(AID aid) {
        ACLMessage message = new ACLMessage(ACLMessage.PROXY);
        message.setContent(null);
        message.addReceiver(aid);
        myAgent.send(message);
    }

    public double getGold() {
        return 200 + Math.random() * 200;
    }
}
