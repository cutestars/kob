package com.yxl.matchingsystem.service.impl;

import com.yxl.matchingsystem.service.MatchingService;
import com.yxl.matchingsystem.service.impl.utils.MatchingPool;
import org.springframework.stereotype.Service;

@Service
public class MatchingServiceImpl implements MatchingService {
    public final static MatchingPool matchingPool=new MatchingPool();

    @Override
    public String addPlayer(Integer userId, Integer rating) {
        System.out.println("addplayer"+userId+ " " + rating);
        matchingPool.addPlayer(userId,rating);
        return "add player success";
    }

    @Override
    public String removePlayer(Integer userId) {
        System.out.println("remove player: "+userId);
        matchingPool.removePlayer(userId);
        return "remove player success";
    }
}