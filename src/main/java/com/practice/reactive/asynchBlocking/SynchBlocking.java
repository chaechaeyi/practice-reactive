package com.practice.reactive.asynchBlocking;

import lombok.extern.slf4j.Slf4j;

/**
 * 동기 & Blocking
 */
@Slf4j
public class SynchBlocking {
    
    public static void main(String[] args) {
        log.info("Start main");
        var result = getResult();
        var nextValue = result + 1;
        assert nextValue == 1;
        log.info("Finish main");
    }
    public static int getResult(){
        log.info("Start getResult");;
        try{
            Thread.sleep(1000);;
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        try{
            return 0;
        }finally {
            log.info("Finish getResult");
        }
    }

}


