
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BitArray implements Clusterable<BitArray>{
	private ArrayList<Boolean> bits;

	public BitArray(String str){
		String[] strarr=str.split(",");
		Stream<String> stream=Stream.of(strarr);
		bits= new ArrayList<>();
		stream.map(s->bits.add(Boolean.valueOf(s))).collect(Collectors.toList());
	}
	public BitArray(boolean[] bits) {
		this.bits= new ArrayList<>();
		for (int i=0;i< bits.length;i++) {
			this.bits.add(bits[i]);
		}
	}

	@Override
	public double distance(BitArray other) {
		return IntStream.range(0,bits.size()).map(i->bits.get(i)!=other.bits.get(i) ? 1: 0).sum();
	}

	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		List<String> arr= Files.lines(Paths.get(path)).collect(Collectors.toList());
		int max=Integer.parseInt(arr.stream().max(Comparator.comparing(String::length)).get());
		return arr.stream().filter(l->l.length()==max).map(l-> new BitArray(l)).collect(Collectors.toSet());
	}


	@Override
	public String toString() {
		return bits.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BitArray bitArray = (BitArray) o;
		return bits.equals(bitArray.bits);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bits);
	}
}
