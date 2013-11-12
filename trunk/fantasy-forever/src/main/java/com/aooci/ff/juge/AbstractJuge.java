package com.aooci.ff.juge;

import java.util.Map;

public abstract class AbstractJuge {

	public final static float scoreStep = (float) 0.1;
	
	abstract Map<Integer, Float> examine(boolean[][] resultData);
	
}
