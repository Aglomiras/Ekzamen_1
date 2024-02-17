package org.example.Agents;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
import org.example.Behaviours.SellBehs.ExpectationBeh;
import org.example.Help.DfHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class SellAgent extends Agent {
    public List<String> books = List.of("Золотая рыбка", "Фауст", "Мороз и солнце", "Лягушка", "Наруто", "Приключение какашки");

    @Override
    protected void setup() {
        log.info(this.getLocalName() + " родился");
        DfHelper.register(this, "selling_books");
        ArrayList myBook = new ArrayList();
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            myBook.add(books.get((random.nextInt(books.size()))));
        }
        System.out.println(this.getLocalName() + " " + myBook);
        this.addBehaviour(new ExpectationBeh(myBook));
    }
}
