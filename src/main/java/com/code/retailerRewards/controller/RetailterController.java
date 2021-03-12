package com.code.retailerRewards.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class RetailerController {

    //Map contains customer id's and amount they spent
    private static Map<Integer, Integer> customerMap;

    static {
    	customerMap = new HashMap<>();
    	customerMap.put(1, 222);
    	customerMap.put(2, 420);
    	customerMap.put(3, 655);
    	customerMap.put(4, 55);
    	customerMap.put(5, 99);
    }

    @RequestMapping("/getRewards/{custId}")
    public String getRewardPoints(@PathVariable int custId) {
        String rewards;
        if (customerMap.containsKey(custId)) {
            int custAmount = customerMap.get(custId);
            rewards = String.valueOf(getRewards(custAmount));
            return "CustomerId:" + custId +" eligible for "+rewards + " Reward Points on the spent of " + customerMap.get(custId) + " dollars.";
        } else {
            return "Customer Number " + custId + " not found";
        }
    }

    // Calculate the reward points
    private int getRewards(int amount) {
        if (amount > 100) {
            int twoPointAmount = amount - 100;
            int twoPointRewards = twoPointAmount * 2;
            return twoPointRewards + 50;
        } else if (amount > 50) {
            return 50;
        } else {
            return 0;
        }
    }
}
