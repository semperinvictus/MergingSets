import it.uniroma3.mat.extendedset.intset.ConciseSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.collect.Lists;

public class ConciseSetOperations {
	
	final MyLogger logger = MyLogger.instance();
	
	public ConciseSetOperations() {}
	
	public ConciseSet SlowSequentialAdd(List<ConciseSet> sets) {
		ConciseSet totalSet = new ConciseSet();
		for (ConciseSet set : sets) {
			totalSet.addAll(set);
		}
		
		return totalSet;
	}
	
	public ConciseSet FastPairwiseMerging(List<ConciseSet> sets) {
		// list of pairs of sets
		List<List<ConciseSet>> mergeableSets = new ArrayList<List<ConciseSet>>();
		// use guava to faciliate the partitioning into sets
		mergeableSets = Lists.partition(sets, 2);
		
		// merge pairs iteratively until there is only one set
		while (mergeableSets.size() > 1) {
			// store merged pairs to be partitioned in the next iteration
			List<ConciseSet> mergedPairs = new ArrayList<ConciseSet>();
			for (List<ConciseSet> pair : mergeableSets) {
				if (pair.size() == 2) {
					pair.get(0).addAll(pair.get(1));
				}
				// sometimes a "pair" will only contain one set
				mergedPairs.add(pair.get(0));
			}
			// partition again
			mergeableSets = Lists.partition(mergedPairs, 2);
		}
		
		ConciseSet totalSet = null;
		// merge the last two sets if we started with an even number
		if (mergeableSets.get(0).size() > 1) {
			mergeableSets.get(0).get(0).addAll(mergeableSets.get(0).get(1));
		}
		totalSet = mergeableSets.get(0).get(0);
		
		return totalSet;
	}
	
	public List<ConciseSet> generateSets(int numberOfSets, int elementsPerSet) {
		List<ConciseSet> sets = new ArrayList<ConciseSet>();
		int thousandcount = 0;
		for (int i = 1; i < numberOfSets+1; i++) {			
			sets.add(generateConciseSet(elementsPerSet));
			if (i > 0 && i%1000 == 0) {
				thousandcount++;
				logger.log("Generated " + thousandcount + " thousand sets", MyLogger.LVL.INFO);
			}
		}
		return sets;
	}
	
	public ConciseSet generateConciseSet(int elementsPerSet) {
		ConciseSet set = new ConciseSet();
		for (int i = 0; i < elementsPerSet; i++) {
			// between one million and ten million
			int randomInt = (int) ThreadLocalRandom.current().nextInt(1000000, 10000000);
			set.add(randomInt);
		}
		return set;
	}
	
}
