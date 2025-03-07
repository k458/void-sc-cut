package com.k458.void_gateway.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBottleneckService {

    private final List<Long> usersBeingServed = new ArrayList<>();
    private final Object lock = new Object();

    public boolean tryAllowFor(Long id){
        synchronized (lock){
            if (usersBeingServed.contains(id)){
                return false;
            }
            usersBeingServed.add(id);
            return true;
        }
    }

    public void finishedFor(Long id){
        synchronized (lock){
            usersBeingServed.remove(id);
        }
    }
}
