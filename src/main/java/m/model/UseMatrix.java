/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * 
 */
public class UseMatrix 
{
    public static final int NUMBER_OF_METHODS = 15;
    public static final int NUMBER_OF_ATTRIBUTES = 16;
    public static final int NUMBER_OF_USES = 39;
    public static final int NUMBER_OF_CLASSES = 5;
    
    public static boolean[ ][ ] uses;
    
    
    public boolean verbose;
    
    public UseMatrix( )
    {
        uses = new boolean[ NUMBER_OF_METHODS ][ NUMBER_OF_ATTRIBUTES ];
     
        for( int i = 0; i < NUMBER_OF_METHODS; i++ )
        {
            for( int j = 0; j < NUMBER_OF_ATTRIBUTES; j++ )
            {
                uses[ i ][ j ] = false;
   
            }
        }
        
        verbose = false;
    }
    
     public static void parseData(String str){

        String key,value;
        int m;
        int a;  
      //Parsing uses data      
       String[]data= str.split("\\|");
        
       key =data[0];
       value =data[1];
       m= Integer.parseInt(key); a= Integer.parseInt(value);
        
// putting uses data into uses matrix
         uses[m][a]=true;  
   }
   public void setUp(){
    
    //Reads Uses file line by line      
      BufferedReader br =null;
    try{
        String strLine;
        br =new BufferedReader(new FileReader("data\\Uses.txt"));
        
        while((strLine=br.readLine())!=null){
         
            parseData(strLine);
        
        }
    }catch(IOException exp){
      System.out.println(" Error while reading file" +exp.getMessage());
    }finally{
        try{
        if (br!= null){
            br.close();
        }       
            
        }catch(IOException e){
          e.printStackTrace();
        }

         
        if( verbose )
        {
            for( int i = 0; i < NUMBER_OF_METHODS; i++ )
            {
                for( int j = 0; j < NUMBER_OF_ATTRIBUTES; j++ )
                {
                    System.out.print( uses[ i ][ j ] + " ");
                }
                System.out.println( "" );
            }
        }
    }
   }
}
