package com.example.test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;




public class part1 {
  public static ArrayList<String> arw = new ArrayList<String>();

  public static void printarray(String[] a){
    for(String i: a){
      System.out.print(i+" ");
    }
  }

   public static List<String> wordcount(String f) {

    String line;
     try(FileReader fr = new FileReader(f);){
       BufferedReader br = new BufferedReader(fr);
       while((line = br.readLine()) != null){
         line = line.replaceAll("[^a-zA-Z_\\]\\[\\/\\-\\s]", "");
         line = line.replaceAll("[\\[\\]\\/]|[-]", " ");
         String[] s1 = line.toLowerCase().split("\\s+");
         for(String i: s1){
              if(i.matches("\\S+")){             
                 arw.add(i);  
                } else{ break;}
          
          }
        }
    }
     catch(FileNotFoundException fife){
         fife.printStackTrace();
     }
     catch(IOException e){
       e.printStackTrace();   
     }
     return arw;
}
    public static int repeatcount( List<String> a,String word) {
      int count = 0; 
          for(String i : a){
            if (i.matches(word)){
              count++;
            }
          }
    if (count == 0){
      return -1;
    }
    else{
      return count;
    }
  }

  public static List<Map.Entry<String,Long>> top10(List<String> a) {
    Map<String,Long> map = a.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

     List<Map.Entry<String,Long>> res = map.entrySet().stream()
     .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
     .limit(10).collect(Collectors.toList());
     


     PieChart pc = new PieChartBuilder().title("Top10").build();
     for(Map.Entry<String, Long> i : res){
       String j = i.getKey();
       long k = i.getValue();
       pc.addSeries(j,k);

     }
     new SwingWrapper(pc).displayChart();
     return res;
        }

  public static List<Long> asc(String file){
    String line;
    List<Long> lst= new ArrayList<Long>();
    try(FileReader fr = new FileReader(file);){
      BufferedReader br = new BufferedReader(fr);
      while((line = br.readLine()) != null){
        line = line.replaceAll("[,.&\"\'-()?\\+=:\\/]|[A-Za-z]|[-]", "");
        line = line.replaceAll("[\\[\\]]", " ");
        String[] s1 = line.toLowerCase().split("\\s+");
        
        for(String i: s1){
          if(i.matches("\\d+"))
          lst.add(Long.parseLong(i));
        }

      }
    Collections.sort(lst);
    
    }
    catch(FileNotFoundException fife){
      fife.printStackTrace();
  }
  catch(IOException e){
    e.printStackTrace();
  }
  return lst;
}

public static List<String> extract(String file){
  String line,line2,line3;
  List<String> arex= new ArrayList<String>(); 
  try(FileReader fr = new FileReader(file);){
    BufferedReader br = new BufferedReader(fr);
    while((line = br.readLine()) != null){
      line2 = line.replaceAll("[^a-zA-Z_\\]\\[\\/\\-\\s]", "");
      line2 = line2.replaceAll("[\\[\\]\\/]|[-]", " ");
      String[] s1 = line2.toLowerCase().split("\\s+");
      for(String i : s1){
        i= i.replace("\\W", "");
        if(!arex.contains(i)){
          arex.add(i);
        }
      }
      line3 = line.replaceAll("[^0-9_\\]\\[\\/\\-\\s]", "");
      line3 = line3.replaceAll("[\\[\\]\\/]|[-]", " ");
      String[] s2 = line3.toLowerCase().split("\\s+");
      for(String j: s2){
        if((j.matches("\\d+")) & !arex.contains(j)){
          arex.add(j);
        }


      }
        
    }
    Collections.sort(arex,Collections.reverseOrder());

     }     
    
    
  catch(FileNotFoundException fife){
    fife.printStackTrace();
}
catch(IOException e){
  e.printStackTrace();
}
return arex;
}



    public static void main(String[] args) {
      List<String> s= wordcount("file.txt");
      System.out.println("\nCount of words is"+ s.size());
      

      int  rp= repeatcount(s,"the");
      if(rp == -1){
        System.out.println("\nWord not in file");
      }
      else{
        System.out.println("\nCount of word is" + rp);
    }
     
      System.out.println("word with highest count is" + top10(s));
      System.out.println("\nNumbers in file in ascending order is \n" +asc("file.txt"));

      System.out.println("\nWords and numbers of file in descending order is\n"+ extract("file.txt")); 
      
      
    }


}
