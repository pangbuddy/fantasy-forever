package com.aooci.ff.juge;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractJuge {
	
	abstract public void examine(Map<Integer, AtomicInteger> scores, boolean[][] resultData);
	
}
