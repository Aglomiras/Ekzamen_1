package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.Model.DtoBook;

@Slf4j
@Data
public class ProcessingCFPBeh extends Behaviour {
    private MessageTemplate messageTemplate;
    private boolean flagBook = false;
    private int count = 0;
    private DtoBook dtoBook;

    @Override
    public void onStart() {
        this.messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
        this.dtoBook = new DtoBook();
    }

    @Override
    public void action() {
        ACLMessage receive = myAgent.receive(messageTemplate);
        if (receive != null) {
            count++;
            if (Double.parseDouble(receive.getContent()) <= 350.0) {
                if (dtoBook.getGold() == 0 || Double.parseDouble(receive.getContent()) < dtoBook.getGold() ) {
                    dtoBook.setNameAgent(receive.getSender().getLocalName());
                    dtoBook.setGold(Double.parseDouble(receive.getContent()));

                    log.info("Покупаю!");
                    flagBook = true;
                }
            } else {
                log.info("Слишком дорого...");
                flagBook = false;
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return count == 2;
    }

    @Override
    public int onEnd() {
        if (count == 2) {
            if (flagBook){
                return 0; //Покупаю
            } else {
                return 1; //Не покупаю
            }
        } else {
            if (flagBook){
                return 0;
            } else {
                return 1;
            }
        }
    }
}
