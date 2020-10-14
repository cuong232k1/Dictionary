package sample;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
    public DictionaryManagement dic = new DictionaryManagement();
    public void showAllWords() {
        int No=0;
        System.out.println("No\tEnglish\tVietnamese");
        for (Word i : dic.getMy_words().word){
            if (i.getWord_target() != "\0"){
                No++;
                System.out.println(No + "\t" + i.getWord_target() + "\t" + i.getWord_explain());
            }
        }
    }
    public void dictionaryBasic() {
        DictionaryManagement dic1 = new DictionaryManagement();
        showAllWords();
    }
    public void dictionaryAdvanced()throws Exception {
        dic.insertFromFile();
        showAllWords();
        dic.dictionaryLookup();
    }
    public void dictionarySearcher() throws Exception{
        System.out.println("the word,you want to look up:");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        dic.insertFromFile();
        String []cout = new String[dic.getMy_words().number_words];
        for (int i = 0; i < dic.getMy_words().number_words; i++) {
            cout[i] = "\0";
            // System.out.println(dic.getMy_words().word[i].getWord_target().substring(0,input.length()));\
            if (input.length() <= dic.getMy_words().word[i].getWord_target().length()){
                if (input.equalsIgnoreCase(dic.getMy_words().word[i].getWord_target().substring(0, input.length()))){
                    cout[i] = dic.getMy_words().word[i].getWord_target();
                    System.out.println(cout[i]);
                }
            }
        }
    }
    public List<String> dictionarySearch(String input)throws Exception {
        dic.insertFromFile();
        List<String> cout = new ArrayList<String>();
        for (int i = 0; i < dic.getMy_words().number_words; i++) {
            if (input.length() <= dic.getMy_words().word[i].getWord_target().length()){
                if (input.equalsIgnoreCase(dic.getMy_words().word[i].getWord_target().substring(0,input.length()))){
                    cout.add(dic.getMy_words().word[i].getWord_target());
                }
            }
        }
        return cout;
    }

    public static void main(String[] args) throws Exception{
        DictionaryCommandline dic = new DictionaryCommandline();
        for (String i : dic.dictionarySearch("co")){
            System.out.println(i);
        }
    }
}
