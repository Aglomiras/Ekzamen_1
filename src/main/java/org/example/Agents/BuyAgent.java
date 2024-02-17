package org.example.Agents;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import org.example.Behaviours.BuyBehs.OrderBeh;
import org.example.Behaviours.BuyBehs.ParallelProcessingCFPBeh;
import org.example.Behaviours.BuyBehs.ParallelProcessingPROXYBeh;

@Slf4j
public class BuyAgent extends Agent {
    @Override
    protected void setup() {
        log.info(this.getLocalName() + " родился");
        this.addBehaviour(new OrderBeh(this,3000, "Золотая рыбка"));
        this.addBehaviour(new ParallelProcessingCFPBeh());
        this.addBehaviour(new ParallelProcessingPROXYBeh());
    }
}
