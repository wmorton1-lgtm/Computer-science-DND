public class XanderIsSmart {
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
        Nucleotide[] idkWhatToNameThis = new Nucleotide[3];
        idkWhatToNameThis[0] = Nucleotide.A;
        idkWhatToNameThis[1] = Nucleotide.T;
        idkWhatToNameThis[2] = Nucleotide.T;
        DoublyLinkedList segLink = new DoublyLinkedList(idkWhatToNameThis);
        // Nucleotide evan = Nucleotide.A;


        // testing remove 16 after index
        // tides[0] = Nucleotide.G;
        // tides[1] = Nucleotide.G;
        // tides[2] = Nucleotide.A;
        // for (int i = 3; i < 19; i++) {
        // tides[i] = Nucleotide.T;
        // }
        // tides[19] = Nucleotide.A;
        // for (int i = 20; i < tides.length; i++) {
        // tides[i] = Nucleotide.G;
        // }
        // for (int i = 1; i < 18; i++) {
        // tides[i] = Nucleotide.A;
        // }


        // System.out.println(xandersNucleotides.toString());

        // int index = 0;
        // // for (ListNode2<Nucleotide> n = xandersNucleotides.getSentinel().getNext();
        // !n.getNext()
        // .equals(xandersNucleotides.getSentinel()); n = n.getNext()) {
        // if (index == 16) {
        // xandersNucleotides.removeCCCCCCCCGGGGGGGG(n);
        // break;
        // }
        // index++;
        // }

        // xandersNucleotides.removeCCCCCCCCGGGGGGGG(xandersNucleotides.getHead());
        // System.out.println(xandersNucleotides.toString());
        // System.out.println("aaaa");
        // try {
        // xandersNucleotides.removeCCCCCCCCGGGGGGGG(xandersNucleotides.getHead());
        // } catch (Exception e) {
        // System.out.println("tried to remove 16 with small size");
        // }

        // methods

        // System.out.println(xandersNucleotides.indexOf(Nucleotide.C));
        // System.out.println(xandersNucleotides.indexOf(null));
        // System.out.println(xandersNucleotides.get(0));
        // try {
        // System.out.println(xandersNucleotides.get(10));
        // } catch (Exception IndexOutOfBoundsException) {
        // System.out.println("correct - tried get() with index outofbounds");
        // }



        // System.out.println(xandersNucleotides.toString() + "<- before changes");
        // System.out.println("--adding A at index 8--");
        // xandersNucleotides.add(8, evan);
        // System.out.println(xandersNucleotides.toString() + "\n");
        // System.out.println("--setting A at index 0--");
        // xandersNucleotides.set(0, evan);
        // System.out.println(xandersNucleotides.toString());
        // xandersNucleotides.set(7, evan);
        // System.out.println("removing index 4");
        // System.out.println(xandersNucleotides.toString());
        // System.out.println(xandersNucleotides.remove(4));
        // System.out.println(xandersNucleotides.toString());



        System.out.println(xandersNucleotides.toString());
        // xandersNucleotides.deleteSegment(segLink);
        // xandersNucleotides.replaceEveryAWithTAC();
        xandersNucleotides.deleteSegment(segLink);
        System.out.println(xandersNucleotides.toString());
        xandersNucleotides.deleteSegment(segLink);
        System.out.println(xandersNucleotides.toString());

    }

}
