import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class AgglomerativeClustering<T extends Clusterable<T>> implements Clustering<T> {
    double threshold;

    public AgglomerativeClustering(double threshold) {
        this.threshold = threshold;
    }

    public Set<Set<T>> clusterSet(Set<T> elements) {

        Set<Set<T>> clusters = elements.stream().map(t -> new HashSet<>(Set.of(t))).collect(Collectors.toSet());
        double min = Double.MAX_VALUE;
        List<Set<T>> minsSet = new ArrayList<>();

        while (clusters.size() > 1) {
//            Set<Set<T>> finalClusters = clusters;
//         	clusters.stream().map(x -> finalClusters.stream().map(y -> {
//                if (setsDistance(x, y) < minDistance(minsSet)) {
//                    minsSet.clear();
//                    minsSet.add(x);
//                    minsSet.add(y);
//                }
//                return minsSet;
//            }));

         	for (Set<T> x:clusters){
         	    for (Set<T> y: clusters){

                    if (x!=y&&setsDistance(x, y) < minDistance(minsSet)) {
                        minsSet.clear();
                        minsSet.add(x);
                        minsSet.add(y);
                    }

                }
            }

            if ( minDistance(minsSet) > threshold)
                return clusters;
            else
                clusters = addUnion(clusters, minsSet.get(0), minsSet.get(1));
            minsSet.clear();
        }
        return clusters;
    }

    private ArrayList<Set<T>> sort(ArrayList<Set<T>> list) {

        return list;
    }

    private double setsDistance(Set<T> a, Set<T> b) {
        return a.stream().mapToDouble(x -> setDistance(x, b)).min().getAsDouble();
    }

    private double setDistance(T t, Set<T> set) {
        return set.stream().mapToDouble(x -> x.distance(t)).min().getAsDouble();
    }

    private Set<Set<T>> addUnion(Set<Set<T>> all, Set<T> a, Set<T> b) {
        all.remove(a);
        all.remove(b);
        Set<T> both = new HashSet<T>();
        both.addAll(a);
        both.addAll(b);
        all.add(both);
        return all;
    }

    private double minDistance(List<Set<T>> set) {
        if (set.size() < 2)
            return Double.MAX_VALUE;
        else
            return setsDistance(set.get(0), set.get(1));
    }
}
