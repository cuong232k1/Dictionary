package sample;

public class Dictionary {
    int number_words=10000;//khoi tao gia tri luc dau
    Word word[] = new Word[number_words];

    public Dictionary(){
        for (int i=0;i<number_words;i++){
            word[i]=new Word();
        }
    }
    public Dictionary(int number_words){
        for (int i=0;i<number_words;i++){
            word[i]=new Word();
        }
    }
    public int getNumber_words() {
        return number_words;
    };
    public static boolean sameWord(Word new_w1, Word new_w2) {
        return new_w1.getWord_target()==new_w2.getWord_target() && new_w1.getWord_explain()==new_w2.getWord_explain();
    }
}
