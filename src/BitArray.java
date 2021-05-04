
import java.io.IOException;
import java.lang.reflect.Array;
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


		return 0;
	}

	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		// TODO: Complete. If the file contains bitarrays of different lengths,
		//  retain only those of maximal length
		return null;
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
