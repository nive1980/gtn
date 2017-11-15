package com.gtn.util;

import java.math.BigDecimal;

public class TestOne {
		
    public static void main(String[] args){
        
        int[] a1={2,1,4,17,6,2,8, 9, 1};
        
        int k = 3;
        
        int maxSum = Integer.MIN_VALUE;
        
        for(int i=0; i<a1.length; i++){
            
            int localSum = 0;
            
            for(int j=i; j<i+k; j++){
                localSum += a1[j];
            }
            if(localSum > maxSum){
                maxSum = localSum;
            }
            
            if(i == a1.length - k){
                break;
            }
        }
        
        System.out.println(maxSum);
        
    }
    
    
    
    public int search(int[] data, int input, int start, int end){
        
        if(start == end){
            return -1;
        }
        
        int middle = (end-start)/2;
        
        if(data[middle] == input){
            return middle;
        }else if(data[middle] < input){
            //search on right sub array
            search(data, input, middle, end);
        }else if(data[middle] > input){
            //search on left sub array            
            search(data, input, start, middle);
        }
        
        return 0;
    }
    
}
