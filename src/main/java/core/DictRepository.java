package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Word;

public class DictRepository
{
    private static DictRepository repository = new DictRepository();

    private Map<String, Dict> newWordsMap;

    private DictRepository()
    {

    }
    
    @SuppressWarnings("unchecked")
	public void init(){
    	File file = new File("dict.dat");
    	try {
			if(file.exists()){
				ObjectInputStream allWordsStream = new ObjectInputStream(
						new FileInputStream(file));
				newWordsMap = (Map<String, Dict>) allWordsStream.readObject();
				allWordsStream.close();
				
			}else{
				newWordsMap = new HashMap<String,Dict>();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static DictRepository getInstance()
    {
        return repository;
    }

    public boolean addDict(String name, List<Word> words)
    {
        Dict dict = newWordsMap.get(name);
        if(dict == null){
        	dict = new Dict(name, words);
        	newWordsMap.put(name, dict);
        	return true;
        }
        return false;
    }

    public Dict getDict(String name)
    {
        return newWordsMap.get(name);
    }
    
    public void storeDict(){
    	File file = new File("dict.dat");
    	
    	try {
			ObjectOutputStream allWordsStream = new ObjectOutputStream(
					new FileOutputStream(file));
			allWordsStream.writeObject(newWordsMap);
			allWordsStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Set<String> getDictNames(){
    	return newWordsMap.keySet();
    }
}
