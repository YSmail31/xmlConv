package javaapplication21;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Converter {

    static Element racine;
    public static Element ouvrir(String path){
        SAXBuilder sxb = new SAXBuilder();
        try
        {
            Document document = sxb.build(new File(path));
            racine = document.getRootElement();
        }
        catch(Exception e){}
        return racine;
    }
    public static void main(String[] args) {
        ArrayList<String> pile=new ArrayList<>();
        Hashtable<String,String> ht = new Hashtable();
        Element racine = ouvrir("test.xml.xml");
        pile = getChild(racine,ht);
        int i=0;
        remove(pile);
        for(String s:pile)
            System.out.println(s);
        System.out.println(pile.size());
    }
    static ArrayList<String> getChild(Element e,Hashtable<String,String> ht)
    {
        ArrayList<String> res = new ArrayList<>();
        if(!ht.containsKey(print(e).split(" ")[0]))
        {
            ht.put(print(e).split(" ")[0], print(e));
            res.add(print(e));
        }
        for(Element el:e.getChildren())
        {
        
            if(!ht.containsKey(print(el).split(" ")[0]))
            {
                ht.put(print(el).split(" ")[0], print(el));
                res.add(print(el));
            }
            res.addAll(getChild(el,ht));
        }
        return res;
    }
    static String print(Element el)
    {
        String s="";
        s+= el.getName()+" (";
        for(Attribute att:el.getAttributes())
            s+=att.getName()+",";
        s+=")[";
        for(Element child:el.getChildren())
            if(!s.contains(child.getName()+","))
                s+=child.getName()+",";
        s+="]";
        
        return s;
    }

    public static void remove(ArrayList<String> pile) {
        for(int i=0;i<pile.size();i++)
        {
            //System.out.println(pile.get(i));
            for(int j=i+1;j<pile.size();j++)
            {
                if(pile.get(i).equals(pile.get(j)))
                    pile.remove(j);
            }
        }
    }
}
