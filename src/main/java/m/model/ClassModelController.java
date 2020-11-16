/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.model;

/**
 *
 * @author Fabrice
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class ClassModelController {  
    
 private static UseMatrix useMatrix;
 
 public static int[] genotype = new int[Parameters.GENOTYPE_LENGTH];
 
 public  static List<String> attributes = new ArrayList<>();
 public  static  List<String> methods = new ArrayList<>();
 
 public  static ArrayList<Integer> action = new ArrayList<>();
 public  static ArrayList<Integer> datum = new ArrayList<>();
 
 public  static ArrayList<String> Class_1;
 public  static ArrayList<String> Class_2;
 public  static ArrayList<String> Class_3;
 public  static ArrayList<String> Class_4;
 public  static ArrayList<String> Class_5;
      
public static Map <Integer, String> modelMapping = new HashMap<>(); 
    
public static int[] classModel = new int[Parameters.GENOTYPE_LENGTH];; 
    
    public ClassModelController(){
    
   
   useMatrix = new UseMatrix();
   

}

public static ArrayList<ArrayList<String>> classModel(int[]array){
   //read data from file 
        try{

         File actions = new File ("data\\Actions.txt");
         File data = new File ("data\\Data.txt");
         
         Scanner readActions = new Scanner(actions);
         Scanner readData = new Scanner(data);


         
         while (readActions.hasNextLine()){
             String Actions = readActions.nextLine();
             methods.add(Actions);   
         }

          
        while (readData.hasNextLine()){
             String Data = readData.nextLine();
              attributes.add(Data);
          } 
  
        }
      catch(FileNotFoundException e){
          System.out.println("File not found");
          e.printStackTrace();
      }  
        
     for( int i = 0; i < methods.size(); i++ )
                { 
                   action.add(i);  
                }
       
                for( int j = useMatrix.NUMBER_OF_METHODS ; j < useMatrix.NUMBER_OF_METHODS +useMatrix.NUMBER_OF_ATTRIBUTES; j++ )    
                { 
                 datum.add(j); 
                }       
                      for( int i = 0; i < methods.size(); i++ )
            
                      { 
                          modelMapping.put(action.get(i),methods.get(i));   
                      }          
                           for( int i = 0; i < attributes.size(); i++ )
            
                           { 
                               modelMapping.put(datum.get(i),attributes.get(i));   
                           }
       
           List <Integer> Class1 =new ArrayList<>();
           List <Integer> Class2 =new ArrayList<>();
           List <Integer> Class3 =new ArrayList<>();
           List <Integer> Class4 =new ArrayList<>();
           List <Integer> Class5 =new ArrayList<>();

             // Get each class elements index

               for (int i =0; i< genotype.length; i++){
                  int num =array[i];
                  switch (num){
                      case 1:
                          Class1.add(i);
                          break;
                       case 2:
                          Class2.add(i);
                          break; 
                      case 3:
                          Class3.add(i);
                          break; 
                     case 4:
                          Class4.add(i);
                          break;  
                        case 5:
                          Class5.add(i);
                          break;  
                  } 
               }
            ArrayList<ArrayList<String>> classModel = new ArrayList<>();

            List<String> ClassAtt1  = new ArrayList<>();
            List<String> ClassMet1  = new ArrayList<>();

           //  returns elements at index for each class    
            for (int i =0; i< Class1.size(); i++){

                int num= Class1.get(i);
                if (num <methods.size()){ 
                ClassMet1.add(modelMapping.get(num));
                 } else{
                   ClassAtt1.add(modelMapping.get(num)); 
                }
            }
             Class_1 = new ArrayList<>(ClassAtt1);
             Class_1.addAll(ClassMet1);
             classModel.add(Class_1);
            System.out.println(" Class 1 :" +Class_1);

            List<String> ClassAtt2  = new ArrayList<>();
            List<String> ClassMet2  = new ArrayList<>();

           for (int i =0; i< Class2.size(); i++){

                int num= Class2.get(i);
                if (num <methods.size()){ 
                ClassMet2.add(modelMapping.get(num));
                 } else{
                   ClassAtt2.add(modelMapping.get(num)); 
                }
            }
             Class_2 = new ArrayList<>(ClassAtt2);
             Class_2.addAll(ClassMet2);
              classModel.add(Class_2);
            System.out.println(" Class 2 :" +Class_2);

            List<String> ClassAtt3  = new ArrayList<>();
            List<String> ClassMet3  = new ArrayList<>();

           for (int i =0; i< Class3.size(); i++){

                int num= Class3.get(i);
                 if (num <methods.size()){ 
                ClassMet3.add(modelMapping.get(num));
                 } else{
                   ClassAtt3.add(modelMapping.get(num)); 
                }
            }
             Class_3  = new ArrayList<>(ClassAtt3);
             Class_3.addAll(ClassMet3);
             classModel.add(Class_3);
            System.out.println(" Class 3 :" +Class_3);

            List<String> ClassAtt4  = new ArrayList<>();
            List<String> ClassMet4  = new ArrayList<>();
           for (int i =0; i< Class4.size(); i++){

                int num= Class4.get(i);
                 if (num <methods.size()){ 
                ClassMet4.add(modelMapping.get(num));
                 } else{
                   ClassAtt4.add(modelMapping.get(num)); 
                }
            }
             Class_4  = new ArrayList<>(ClassAtt4);
             Class_4.addAll(ClassMet4);
             classModel.add(Class_4);
            System.out.println(" Class 4 :" +Class_4);

             List<String> ClassAtt5  = new ArrayList<>();
             List<String> ClassMet5  = new ArrayList<>();

           for (int i =0; i< Class5.size(); i++){

                int num= Class5.get(i);
                 if (num <methods.size()){ 
                ClassMet5.add(modelMapping.get(num));
                 } else{
                   ClassAtt5.add(modelMapping.get(num)); 
                }
            }
             Class_5  = new ArrayList<>(ClassAtt5);
             Class_5.addAll(ClassMet5);
             classModel.add(Class_5);
            System.out.println(" Class 5 :" +Class_5);

          return classModel;  

        }

}
