package sample;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {

    final static Scanner scan = new Scanner(System.in);
    final static Dictionary my_words = new Dictionary();
    public Dictionary getMy_words() {
        return my_words;
    }

    public static void insertFromCommandline() {
        System.out.println("number_words is");
        int number_word = scan.nextInt();
        scan.nextLine();

        String input_word_target;
        String input_word_explain;
       for(int i= 0; i < number_word; i++) {

           System.out.println("word_target is");
           input_word_target=scan.nextLine();
           System.out.println("word_explain is");
           input_word_explain = scan.nextLine();
           my_words.word[i] = new Word(input_word_target,input_word_explain);
       }
    }
    public void insertFromFile() throws Exception {
        File My_Dictionary = new File("dic.txt");
        BufferedReader dic = new BufferedReader(new FileReader(My_Dictionary));
        int number_words = 0;
        String get;
        while((get = dic.readLine())!=null){
            String []get_words = get.split("\t",2);//ham split ko the su dung khi get = null len dua vao if
            getMy_words().word[number_words] = new Word(get_words[0],get_words[1]);
            number_words++;
        }
        dic.close();
    }
    public void dictionaryLookup() {
        String input = scan.nextLine();
        for (Word a : my_words.word)
            if (input.equalsIgnoreCase(a.getWord_target())) {
                System.out.println("word's explain is " + a.getWord_explain());
            }
            else System.out.println("NOT HAVE");
    }
    public String dictionarylook(String input) {
        for (Word a : my_words.word)
            if (input.equalsIgnoreCase(a.getWord_target())) {
                return a.getWord_explain();
            }
        return "false;";
    }
    public static void addWord(Word new_word) {//static de ham sua giu lieu bien cuc bo
        for (int i=0; i<my_words.word.length;i++) {
            if (new_word.getWord_target().equalsIgnoreCase(my_words.word[i].getWord_target())) {
                System.out.println("this word is in the dictionary");
                return;
            } else if (my_words.word[i].getWord_target().equalsIgnoreCase("\0")) {
                my_words.word[i] = new Word(new_word);
                break;
            }
        }
    }
    public static void  delete(Word new_word) {
        for (int i=0; i<my_words.word.length;i++) {
            if (new_word.getWord_target().equalsIgnoreCase(my_words.word[i].getWord_target())) {
                my_words.word[i]= new Word();
                return;
            } else if (my_words.word[i].getWord_target().equalsIgnoreCase("\0")) {
                System.out.println("this word is not in the dictionary");
                break;
            }
        }
    }
    public static void repair(Word new_word,int pos)throws Exception {//static de ham sua giu lieu bien cuc bo
        my_words.word[pos-1] = new Word(new_word);
    }
    public static void dictionaryExportToFile()throws Exception {
        File My_Dictionary = new File("dic.txt");
        FileWriter fw = new FileWriter(My_Dictionary,true);
        for (Word i: my_words.word) {
            //fw.write("\n");
            fw.write(i.getWord_target());
            fw.write("\t");
            fw.write(i.getWord_explain()+"\n");
            fw.write("\n");
        }
        fw.close();
    }
    public void add_dictionaryFile(Word word) throws Exception {
        File My_Dictionary = new File("dic.txt");
        FileWriter fw = new FileWriter(My_Dictionary,true);
        for (int i=0; i<my_words.word.length;i++) {
            if (word.getWord_target().equalsIgnoreCase(my_words.word[i].getWord_target())) {
                System.out.println("this word is in the dictionary");
                return;
            }
        }
        fw.write("\n"+word.getWord_target()+"\t"+word.getWord_explain());
        fw.close();
    }

    public void delete_dictionaryFile(String word) throws Exception{
        File My_Dictionary = new File("dic.txt");
        FileWriter fw = new FileWriter(My_Dictionary);
        fw.flush();
        for (Word i :getMy_words().word) {
            if (word.equalsIgnoreCase(i.getWord_target())){
                delete(i);
            }
        }
        for (Word i :getMy_words().word) {
            if (i.getWord_target() != "\0") {
                fw.write(i.getWord_target()+"\t"+i.getWord_explain()+"\n");
            }
        }
        fw.close();
    }
    public static void main(String[] args) throws Exception {
        DictionaryManagement dic = new DictionaryManagement();
        dic.insertFromFile();
    }
}
