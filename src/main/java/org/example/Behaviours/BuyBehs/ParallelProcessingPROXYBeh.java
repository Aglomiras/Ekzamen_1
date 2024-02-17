package org.example.Behaviours.BuyBehs;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelProcessingPROXYBeh extends ParallelBehaviour {
    private Behaviour wakeupBeh;
    private ProcessingPROXYBeh receiveBeh;

    public ParallelProcessingPROXYBeh() {
        super(WHEN_ANY);
    }

    @Override
    public void onStart() {
        receiveBeh = new ProcessingPROXYBeh();
        wakeupBeh = new WakerBehaviour(myAgent, 15000) {
            boolean wake = false;

            @Override
            protected void onWake() {
                wake = true;
                log.info("TIME IS UP");
            }

            @Override
            public int onEnd() {
                return wake ? 0 : 1;
            }
        };

        this.addSubBehaviour(receiveBeh);
        this.addSubBehaviour(wakeupBeh);
    }

    @Override
    public int onEnd() {
        if (wakeupBeh.done()) {
            if (receiveBeh.getCount() == 2) {
                log.info("Нет предложений");
                return 0;
            } else {
                return 1;
            }
        } else {
            if (receiveBeh.getCount() == 2) {
                log.info("Нет предложений");
                return 0;
            } else {
                return 1;
            }
        }
    }
}
