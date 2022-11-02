import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Disjunct> disjuncts = new ArrayList<>();

        Variable x1 = new Variable();
        x1.setName("x1");
        Atom atom1 = new Atom("S", false, Arrays.asList(x1));

        Atom atom2 = new Atom("M", false, Arrays.asList(x1));

        Variable x2 = new Variable();
        x2.setName("x2");
        Atom atom3 = new Atom("M", true, Arrays.asList(x2));

        Constant rain = new Constant("RAIN");
        Atom atom4 = new Atom("L", true, Arrays.asList(x2, rain));


        Variable x3 = new Variable();
        x3.setName("x3");
        Atom atom5 = new Atom("S", true, Arrays.asList(x3));


        Constant snow = new Constant("SNOW");
        Atom atom6 = new Atom("L", false, Arrays.asList(x3, snow));

        Variable y1 = new Variable();
        y1.setName("y1");
        Variable y2 = new Variable();
        y2.setName("y2");
        Constant lena = new Constant("LENA");
        Constant petya = new Constant("PETYA");
        Atom atom7 = new Atom("L", true, Arrays.asList(lena, y1));
        Atom atom8 = new Atom("L", true, Arrays.asList(petya, y1));
        Atom atom9 = new Atom("L", false, Arrays.asList(lena, y2));
        Atom atom10 = new Atom("L", false, Arrays.asList(petya, y2));
        Atom atom11 = new Atom("L", false, Arrays.asList(petya, rain));
        Atom atom12 = new Atom("L", false, Arrays.asList(petya, snow));

        Disjunct disjunct1 = new Disjunct(Arrays.asList(atom1, atom2));
        Disjunct disjunct2 = new Disjunct(Arrays.asList(atom3, atom4));
        Disjunct disjunct3 = new Disjunct(Arrays.asList(atom5, atom6));
        Disjunct disjunct4 = new Disjunct(Arrays.asList(atom7, atom8));
        Disjunct disjunct5 = new Disjunct(Arrays.asList(atom9, atom10));
        Disjunct disjunct6 = new Disjunct(Arrays.asList(atom11));
        Disjunct disjunct7 = new Disjunct(Arrays.asList(atom12));


        disjuncts.add(disjunct1);
        disjuncts.add(disjunct2);
        disjuncts.add(disjunct3);
        disjuncts.add(disjunct4);
        disjuncts.add(disjunct5);
        disjuncts.add(disjunct6);
        disjuncts.add(disjunct7);


        Variable x4 = new Variable();
        x4.setName("x4");
        Atom targetAtom1 = new Atom("M", true, Arrays.asList(x4));
        Atom targetAtom2 = new Atom("S", false, Arrays.asList(x4));
        Disjunct target = new Disjunct(Arrays.asList(targetAtom1, targetAtom2));

        Resolution resolution = new Resolution();
        resolution.resolution(disjuncts, target);

        System.out.println("переменные");
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(x4);
        System.out.println(y1);
        System.out.println(y2);
    }
}
