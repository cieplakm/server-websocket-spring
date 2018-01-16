package com.gft.challenge1.server;

public class Timer {
    TimeListener tl;

    public void start(){

        for (int i = 5; i > 0; i--){
            tl.ticTac(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void addTimeListener(TimeListener listener) {
        tl = listener;
    }
}
