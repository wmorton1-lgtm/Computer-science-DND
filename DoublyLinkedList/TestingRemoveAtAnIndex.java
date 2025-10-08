public class TestingRemoveAtAnIndex {
    public static void main(String[] args) {
        Nucleotide[] tides = new Nucleotide[15];
        tides[0] = Nucleotide.G;
        tides[1] = Nucleotide.A;
        tides[2] = Nucleotide.T;
        tides[3] = Nucleotide.T;
        tides[4] = Nucleotide.C;
        tides[5] = Nucleotide.A;
        tides[6] = Nucleotide.T;
        tides[7] = Nucleotide.T;
        DoublyLinkedList xandersNucleotides = new DoublyLinkedList(tides);
        System.out.println(xandersNucleotides.toString());
        // System.out.println(xandersNucleotides.remove(0));
        System.out.println(xandersNucleotides.remove(8));
        System.out.println(xandersNucleotides.toString());
    }
}
