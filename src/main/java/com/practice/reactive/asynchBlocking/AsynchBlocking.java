package com.practice.reactive.asynchBlocking;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * 비동기 & blocking
 */
@Slf4j
public class AsynchBlocking {

    public static void main(String[] args) {
        log.info("Start main");
        getResult(new Consumer<Integer>(){
            @Override
            public void accept(Integer integer){
                var nextValue = integer + 1;
                assert nextValue ==1;
            }
        });
        log.info("Finish main");
    }
    public static void getResult(Consumer <Integer> cb) {
        log.info("Start getResult");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        var result = 0;
        cb.accept(result);
        log.info("Finish getResult");
    }
}
