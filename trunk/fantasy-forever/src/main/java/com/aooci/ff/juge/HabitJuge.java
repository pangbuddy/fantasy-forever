package com.aooci.ff.juge;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HabitJuge extends AbstractJuge{

	@Override
	public void examine(Map<Integer, AtomicInteger> scores, boolean[][] resultData) {
		boolean hasHabit, habitRecoded;
		int habitPeriod, periodCounter;
		for(int dimensionIndex=0; dimensionIndex < resultData[0].length; dimensionIndex++){
			hasHabit = false;
			habitRecoded = false;
			habitPeriod = 0;
			periodCounter = 0;
			for(int periodIndex=resultData.length - 1; periodIndex > -1; periodIndex--){
				if(hasHabit){
					break;
				}else if(habitRecoded){
					if(resultData[periodIndex][dimensionIndex]){
						if(habitPeriod == periodCounter){
							hasHabit = true;
						}else{
							periodCounter = 0;
							continue;
						}
					}else{
						periodCounter++;
					}
				}else{
					if(resultData[periodIndex][dimensionIndex]){
						habitRecoded = true;
					}else{
						habitPeriod++;
					}
				}
			}
			if(hasHabit){
				scores.get(dimensionIndex+1).incrementAndGet();
			}
		}
		return;
	}

}
