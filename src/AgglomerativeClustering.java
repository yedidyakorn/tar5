import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T>{
	double threshold;
	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}
	public Set<Set<T>> clusterSet(Set<T> elements) {
		Set<Set<T>> clusters =new HashSet<>(IntStream.range(0,elements.size()).Arrays.asList(l)))).collect(Collectors.toSet());
		List<Set<T>> c =new ArrayList<>();
		double min = Double.MAX_VALUE;
		elements.stream().map(l->clusters.add(new HashSet<T>(Arrays.asList(l)))).collect(Collectors.toSet());
		elements.stream().map(l->c.add(new HashSet<T>(Arrays.asList(l)))).collect(Collectors.toSet());

		List<Set<T>> minsSet = null;


		while (clusters.size()>1){
			//min=clusters.stream().mapToDouble(x->clusters.stream().mapToDouble(y->setsDistance(x,y))).min().getAsDouble();
			Set<Set<T>> finalClusters = clusters;
			clusters.stream().map(x-> finalClusters.stream().map(y->{if(setsDistance(x,y)<mindistance(minsSet)){
																	minsSet.clear();
																	minsSet.add(x);
																	minsSet.add(y);
																}
				return minsSet;
			}));

			IntStream.range(0,c.size()).map(x->c.stream().map

//			for (Set<T> c1 :clusters){
//				for (Set<T> c2: clusters){
//					if(setsDistance(c1,c2)<min){
//						min=setsDistance(c1,c2);
//						min1=c1;
//						min2=c2;
//					}
//				}
//			}

			if (min>threshold)
				return clusters;
			else
				clusters=addUnion(clusters,min1,min2);
		}


		// TODO: Complete
		return null;
	}

	private ArrayList<Set<T>> sort (ArrayList<Set<T>> list){

		return list;
	}

	private double setsDistance(Set<T> a ,Set<T> b){
//		Set<T> comb= new HashSet<>(){{addAll(a);addAll(b);}};
//		a.stream().flatMap(x->b.stream().map(y->x.distance(y)<min?min=x.distance(y):min=min)).collect(Collectors.toSet());
//		a.stream().flatMap(x->b.stream().filter(y->a.contains(y))).min(Clusterable::distance);

		return a.stream().mapToDouble(x->setDistance(x,b)).min().getAsDouble();
	}

	private double setDistance (T t,Set<T> set){
		return set.stream().mapToDouble(x->x.distance(t)).min().getAsDouble();
	}

	private Set<Set<T>> addUnion (Set<Set<T>> all, Set<T> a, Set<T> b){
		all.remove(a);
		all.remove(b);
		Set<T> both=new HashSet<T>() ;
		both.addAll(a);
		both.addAll(b);
		all.add(both);
		return all;
	}

	private double mindistance (List<Set<T>> set){
		if(set.size()<2)
			return  Double.MAX_VALUE;
		else
			return setsDistance(set.get(0),set.get(1));
	}
}
