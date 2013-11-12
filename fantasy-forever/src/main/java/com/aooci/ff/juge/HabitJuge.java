package com.aooci.ff.juge;

import java.util.Map;

public class HabitJuge extends AbstractJuge{

	@Override
	Map<Integer, Float> examine(boolean[][] resultData) {
		boolean hasHabit;
		int habitPeriod;
		for(int dimensionIndex=0; dimensionIndex < resultData[0].length; dimensionIndex++){
			hasHabit = false;
			habitPeriod = 0;
			for(int periodIndex=resultData.length - 1; periodIndex > -1; periodIndex--){
				
				
			}
		}
		return null;
	}
//    public Map<Integer,Integer> habit(int lastResultNumber){
//        Map<Integer,Integer> habits = new TreeMap<Integer, Integer>();
//        for(int recordIndex = 0 ; recordIndex < super.dimension ; recordIndex++){
//            int habitTemplate = 0;
//            boolean habitTemplateCreated = false;
//            int habitCount = 0;
//            int stepCounter = 0;
//            for(int periodIndex = lastResultNumber-1 ; periodIndex >= 0 ; periodIndex--){
//                
//                if(super.binaryResults[periodIndex][recordIndex] == 1){
//                    if(!habitTemplateCreated){
//                        habitTemplateCreated = true;
//                    }else if(habitTemplate == stepCounter){
//                        habitCount++;
//                    }
//                    stepCounter = 0;
//                }else if(!habitTemplateCreated){
//                    habitTemplate++;
//                }else{
//                    stepCounter++;
//                }
//            }
//            habits.put(recordIndex+1, habitCount);
//            //System.out.println((recordIndex +1) +" - Habit Template = " + habitTemplate);
//        }
//    
//        return habits;
//    }
}
