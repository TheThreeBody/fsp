package com.zhengtong.fsp.commons.utils.file;

import java.util.EmptyStackException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dongwen on 2016/12/9.
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
    ExecutorService pool = Executors.newFixedThreadPool(8);

//    ExecutorService executorService = ThreadPoolExecutor.DiscardOldestPolicy;

    Lock lock = new ReentrantLock();

    private int j;

    public void a(){
//        PreparedStatment preparedStatment = new PreparedStatment();
        pool.execute(new Runnable() {
            @Override
            public void run() {

                synchronized (this) {
					System.out.println("j--=" + j--);
					//这里抛异常了，锁能释放吗？
				}

                lock.lock();
            }
        });
    }

    private Object[] elements=new Object[10];
    private int size = 0;
    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }
    public Object pop(){
        if( size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
    private void ensureCapacity(){
        if(elements.length == size){
            Object[] oldElements = elements;
            elements = new Object[2 * elements.length+1];
            System.arraycopy(oldElements,0, elements, 0, size);
        }
    }




}
