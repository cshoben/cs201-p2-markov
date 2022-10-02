import java.util.*;



public class HashMarkov implements MarkovInterface {
    
    protected String[] myWords;		// Training text split into array of words 
	protected Random myRandom;		// Random number generator
	protected int myOrder;			// Length of WordGrams used
	protected HashMap<WordGram, List<String>> myMap = new HashMap <WordGram, List<String>>();


    public HashMarkov(int order){
		myOrder = order;
		myRandom = new Random();
	}


    @Override
    public void setTraining(String text) {
        if (!myMap.isEmpty()) {
            myMap.clear();
        }
        
        myWords = text.split("\\s+");
        for (int i = 0; i < myWords.length - myOrder; i++) { // need to have it minus the order value, because that would be the point where there are none after. 
         
            WordGram current = new WordGram(myWords, i, myOrder);
            if (myMap.containsKey(current)) {
                List<String> nextList = myMap.get(current);
                nextList.add(myWords[i+myOrder]);
                myMap.put(current, nextList);
            } else {
                List<String> nextList = new ArrayList<>(); 
                nextList.add(myWords[i+myOrder]);
                myMap.put(current, nextList);
            }
        }
    }


    @Override
    public List<String> getFollows(WordGram wgram) {
        List<String> follows = new ArrayList<String>();
        if (myMap.containsKey(wgram)) {
            follows = myMap.get(wgram);
        }
        return follows;
    }


    @Override
    public String getRandomText(int length) {
        // random Words to output
        ArrayList<String> randomWords = new ArrayList<>(length);

        // randomly draw first wordgram
        int index = myRandom.nextInt(myWords.length - myOrder + 1);
        WordGram current = new WordGram(myWords, index, myOrder);
        randomWords.add(current.toString());

        for (int i=0; i < length-myOrder; i++) { // loop to fill in each word
            List<String> nextOptions = this.getFollows(current); // use hashmap to get list of next options
        
            if (nextOptions.size() == 0) {      //if the list is empty, choose random word.
                int randomIndex = myRandom.nextInt(myWords.length);
                String word = myWords[randomIndex];
                randomWords.add(word);
                current = current.shiftAdd(word);

            } else {                    // if list not empty
                int randomIndex = myRandom.nextInt(nextOptions.size()); // random index by size 
                String word = nextOptions.get(randomIndex); //pick word
                randomWords.add(word);              // add word to randomWords for output
                current = current.shiftAdd(word);   // update to new WordGram. 

            }
        }
        // do this until the length of the arrayList is filled. 
        // then return only a String for all this inside of it .
        return String.join(" ", randomWords);
    }


    @Override
    public int getOrder() {
        return myOrder;
    }


    @Override
    public void setSeed(long seed) {
        myRandom.setSeed(seed);        
    }


}
