import java.util.Scanner;

public interface SearchTreeTester {
    public static void main(String[] args) {
        SearchTree<Integer, String> tree = new SearchTree<Integer, String>(6, "Sentence");
        System.out.println("Please type a sentence to be analyzed.");
        Scanner scnr1 = new Scanner(System.in);
        String sentence = scnr1.nextLine();
        String withoutspaces = "";
        for (int i = 0; i < sentence.length(); i++) {
            if (!sentence.substring(i, i + 1).equals(" ")) {
                withoutspaces += sentence.substring(i, i + 1);
            }
        }
        for (int i = 0; i < withoutspaces.length(); i++) {
            int randomkey = 1 + (int) (Math.random() * (withoutspaces.length() - i));
            String letter = withoutspaces.substring(i, i + 1);
            tree.add(randomkey, letter);
            System.out.println("Key:\tValue:");
            System.out.println(randomkey + "\t" + letter);
        }
        System.out.println("These are the keys: " + tree.sort());
        System.out.println("Please type the key you would like to find.");
        Scanner scnr2 = new Scanner(System.in);
        int message = scnr2.nextInt();
        String val = tree.get(message);
        if (val != null) {
            System.out.println("The value is " + val + ".");
        } else {
            System.out.println("Not found.");
        }
        scnr1.close();
        scnr2.close();
    }
}
