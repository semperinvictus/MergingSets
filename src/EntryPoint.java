import static org.junit.Assert.assertEquals;
import it.uniroma3.mat.extendedset.intset.ConciseSet;

import java.util.List;

public class EntryPoint {

	public static void main(String[] args) {
		
		final int NUMBER_OF_SETS = 20000;
		final int ELEMENTS_PER_SET = 2000;
		
		final MyTimer timer = new MyTimer();
		final MyLogger logger = MyLogger.instance();
		
		final ConciseSetOperations ops = new ConciseSetOperations();
		
		timer.start();
		final List<ConciseSet> sets = ops.generateSets(NUMBER_OF_SETS, ELEMENTS_PER_SET);
		logger.log("Time taken to generate sets: " + timer.stop(), MyLogger.LVL.INFO);
		
		timer.start();
		final ConciseSet slowAdded = ops.SlowSequentialAdd(sets);
		logger.log("Time taken to sequentially add: " + timer.stop(), MyLogger.LVL.INFO);
		
		timer.start();
		final ConciseSet fastAdded = ops.FastPairwiseMerging(sets);
		logger.log("Time taken to pairwise merge: " + timer.stop(), MyLogger.LVL.INFO);
		
		assertEquals(slowAdded, fastAdded);
	}
	
}
