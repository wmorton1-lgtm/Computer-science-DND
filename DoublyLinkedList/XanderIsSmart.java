public class XanderIsSmart {
    public static void main(String[] args) {
        Nucleotide[] tides = new Nucleotide[10];
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
        Nucleotide evan = Nucleotide.A;

        // methods

        System.out.println(xandersNucleotides.indexOf(Nucleotide.C));
        System.out.println(xandersNucleotides.indexOf(null));
        System.out.println(xandersNucleotides.get(0));
        try {
            System.out.println(xandersNucleotides.get(10));
        } catch (Exception IndexOutOfBoundsException) {
            System.out.println("correct - tried get() with index outofbounds");
        }




        System.out.println(xandersNucleotides.toString() + "<- before changes");
        System.out.println("--adding A at index 8--");
        xandersNucleotides.add(8, evan);
        System.out.println(xandersNucleotides.toString()  + "\n");
        System.out.println("--setting A at index 0--");
        xandersNucleotides.set(0, evan);
        System.out.println(xandersNucleotides.toString());
        xandersNucleotides.set(7, evan);
        System.out.println("removing index 4");
        System.out.println(xandersNucleotides.toString());
        System.out.println(xandersNucleotides.remove(4));
        System.out.println(xandersNucleotides.toString());
        

        // DoublyLinkedList xander = new DoublyLinkedList();
        // System.out.println(xander);
        // for (int i = 0; i < 100; i++) {
        // xander.add(null);
        // }
        // System.out.println(xander);



    }
}
