package sample;

public class Word {
    private String word_target;//tu moi
    private String word_explain;//giai nghia
    public Word() {
        word_explain = "\0";
        word_target = "\0";
    }

    public Word(String input_t, String input_e) {
        word_target = input_t;
        word_explain = input_e;
    }
    public Word(Word new_w) {
        this.word_target = new_w.getWord_target();
        this.word_explain = new_w.getWord_explain();
    }
    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_target(String a) {
        this.word_target = a;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
}
