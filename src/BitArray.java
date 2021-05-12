import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BitArray implements Clusterable<BitArray> {

    public ArrayList<Boolean> bits;


    public BitArray(String str) {
        String[] strarr = str.split(",");
        Stream<String> stream = Stream.of(strarr);
        bits = new ArrayList<>();
        stream.map(s -> bits.add(Boolean.valueOf(s))).collect(Collectors.toList());
    }

    public BitArray(boolean[] bits) {
        this.bits = new ArrayList<>(IntStream.range(0, bits.length).mapToObj(i -> bits[i]).collect(Collectors.toList()));
    }

    @Override
    public double distance(BitArray other) {
        return IntStream.range(0, bits.size()).map(i -> bits.get(i) != other.bits.get(i) ? 1 : 0).sum();
    }

    public static Set<BitArray> readClusterableSet(String path) throws IOException {
        List<String> arr = Files.lines(Paths.get(path)).collect(Collectors.toList());
        int max = arr.stream().map(x -> numOfBits(x)).max(Integer::compare).get();
        return arr.stream().filter(x -> numOfBits(x) == max).map(l -> new BitArray(l)).collect(Collectors.toSet());
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

    private static int numOfBits(String str) {
        return str.split(",").length;
    }
}
