package com._520.Algorithm.test;

import com._520.Algorithm.array.BasketballPlayer;

public class BasketballTest {
    public static void main(String[] args) {

        BasketballPlayer bs = new BasketballPlayer();
        bs.add(11);
        bs.add(22);

        bs.print();

//        System.out.println(bs.getIndex(22));
//        System.out.println(bs.getPlayer(2));
//        bs.replaceByIndex(2,333);
//        bs.replaceByPlayerNum(22,222);
        bs.remove(1);
        bs.print();
    }
}
