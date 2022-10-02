public class testing {
    public static void main(String[] args) {
        //String str = "aa bb cc aa bb cc aa bb cc aa bb dd ee ff gg hh ii jj";
        //String[] array = str.split("\\s+");



        String str = "aa bb cc aa bb cc aa bb cc aa bb dd ee ff gg hh ii jj";
        String[] array = str.split("\\s+");
        WordGram[] myGrams = new WordGram[array.length-2];
        for(int k=0; k < array.length-2; k++){
            myGrams[k] = new WordGram(array,k,3);
        }

        //String[] source = {"apple", "banana", "cantalope", "dragonfruit"};
        //WordGram myGrams = new WordGram(source, 1, 3);
        System.out.println("myGram[0]");
        //for (String word : myGrams[0].myWords) {
       //     System.out.println(word);
        //}
        System.out.println("Length is " + myGrams[0].length());
        System.out.println("finished");
    
    }
}
