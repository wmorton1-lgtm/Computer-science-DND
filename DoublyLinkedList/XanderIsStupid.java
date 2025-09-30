public class XanderIsStupid {
    public static void main(String[] args) {
        Nucleotide[] tides = new Nucleotide[20];
        tides[0] = Nucleotide.G;
        tides[1] = Nucleotide.A;
        tides[2] = Nucleotide.T;
        tides[3] = Nucleotide.T;
        tides[4] = Nucleotide.C;
        tides[5] = Nucleotide.A;
        tides[6] = Nucleotide.T;
        DoublyLinkedList foo = new DoublyLinkedList(tides);
        System.out.println(foo.toString());
        
        
    }
}