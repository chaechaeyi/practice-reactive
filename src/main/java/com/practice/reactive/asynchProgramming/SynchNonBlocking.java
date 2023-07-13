package com.practice.reactive.asynchProgramming;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 동기 & Non-blocking
 */
@Slf4j
public class SynchNonBlocking {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log.info("Start main");
        var count = 1;
        Future<Integer> result = getResult();
        while(!result.isDone()){
            log.info("Waiting for result, count: {}", count++);
            Thread.sleep(10);
        }

        var nextValue = result.get() + 1;
        assert nextValue == 1;

        log.info("Finish main");
    }
    public static Future<Integer> getResult(){
        var executor = Executors.newSingleThreadExecutor();
        try{
            return executor.submit(new Callable<Integer>(){
                @Override
                public Integer call() {
                    log.info("Start getResult");
                    try{
                        Thread.sleep(5000);
                    }catch(InterruptedException e){
                       e.printStackTrace();
                    }

                    try{
                        return 0;
                    }finally {
                        log.info("Finish getResult");
                    }
                }
            });
        }finally {
            executor.shutdownNow();
        }
    }

}
