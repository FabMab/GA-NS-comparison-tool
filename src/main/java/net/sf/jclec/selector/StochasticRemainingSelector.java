package net.sf.jclec.selector;

import net.sf.jclec.IIndividual;
import net.sf.jclec.ISystem;

import java.util.Random;
import net.sf.jclec.fitness.IValueFitness;

/*
 * Nombre: SobranteStocasticoSelector
 * Autor: Rafael AyllA?n Iglesias
 * Tipo: Clase publica
 * Extiende: La clase StochasticSelector y la interfaz IIndividual
 * Implementa: Nada
 * Variables de la clase:- serialVersionUID (generado por eclipse)
 * 						 - TotalFitness es un double que almacena el fitness total de la
 * 						 poblacion
 * 						 - i es un entero usado de contador para saber cuantos individuos 
 * 						 han sido analizados.
 *    					 - restantes es un entero usado para controlar en cual de las dos
 *    					 partes del metodo estamos con valor 1 para buscar individuos enteros
 * 						 - numSeleccionados es un entero que almacena el numero de individuos
 * 						 seleccionados hasta el momento
 * Metodos: Protegidos: prepareSelection
 * 						selectNext
 * 			Publicos: Ninguno
 * Objetivo de la clase: Esta clase pretende implementar el selector por el metodo del 
 * 						 sobrante estocastico, con lo cual en esta clase en su metodo  
 *                       prepareSelection se inicianilaran los datos que posteriormente
 *                       el metodo selectNext utilizara para poder seleccionar los 
 *                       individuos de la poblacion
 * 
 */

/**
 * @author Rafael AyllA?n-Iglesias
 * @author SebastiA?n Ventura 
 */

public class StochasticRemainingSelector extends StochasticSelector 
{
	/////////////////////////////////////////////////////////////////
	// --------------------------------------- Serialization constant
	/////////////////////////////////////////////////////////////////

	/** Generated by Eclipse */
	
	private static final long serialVersionUID = -7486679623259737868L;

    /** Fitness total de la poblacion */
	
	protected transient double TotalFitness=0.0;
	
    /** variable de recorrido de la poblacion */
	
	protected transient	int i=0;
	
	/** Variable de control sobre parte del algoritmo en el que te encuentras */
	
	int restantes=1;
	
	/** Variable que mantiene el numero de elemenetos seleccionados */
	
	int numSeleccionados=0;
	
	/////////////////////////////////////////////////////////////////
	// ------------------------------------------------- Constructors
	/////////////////////////////////////////////////////////////////

	/**
	 * Empty constructor
	 */
	
	public StochasticRemainingSelector() 
	{
		super();
		
	}

	/**
	 * Empty constructor
	 */
	
	public StochasticRemainingSelector(ISystem context) 
	{
		super(context);
	}

	/////////////////////////////////////////////////////////////////
	// ------------------------- Overwriting AbstractSelector methods
	/////////////////////////////////////////////////////////////////	
	
	/**
	 * Nombre: prepareSelection 
	 * Autor: Rafael AyllA?n Iglesias.
	 * Tipo funcion: Protegida
	 * Valores de entrada: Ninguna
	 * Valores de salida: Ninguna
	 * Funciones que utiliza: NInguna
	 * Variables: Ninguna
	 * Objetivo: Preparar las variables para que la funcion selectNext pueda realizar 
	 * 			 la seleccion de los individuos de la poblacion
	 * 
	 */
	
	@Override
	protected void prepareSelection() 
	{
		//Inicializo datos en cada generacion
		numSeleccionados=0;		
		
		//Obtengo el fitnes total de la poblacion
		for (IIndividual ind : actsrc) {				
			double FitnessActual= ((IValueFitness) ind.getFitness()).getValue();
			TotalFitness +=  FitnessActual;	
		}
					
	}

	/**
	 * Nombre: selectNext
	 * Autor: Rafael AyllA?n Iglesias.
	 * Tipo funcion: Protegida
	 * Valores de entrada: Ninguno
	 * Valores de salida: El individuo seleccionado
	 * Funciones que utiliza: Ninguna
	 * Variables:- conteo es ena variable que almacena ei del algoritmo
	 * 			 - probabilidad es un numero aleatorio
	 * 			 - ind es el individuo actualmente analizado de la poblacion
	 * 			 - FitnessActual es un double que almacena el fitnes del individuo
	 * 			 actualmente analizado de la poblacion
	 * Objetivo: Esta funcion realiza le eleccion de cual es el siguiente individuo
	 * 			 a seleccionar de la poblacion
	 * 
	 */
	
	@Override
	protected IIndividual selectNext() 
	{	

		//Inicio variables de la tecnica
		double conteo; //El dato ei 
		
		//Parte 1 asigno padres con valores enteros
	    if(restantes != 0){
	    	
	      for(int j=i; j<actsrcsz;j++){
	    	  
	    	//empiezo desde el ultimo elemento analizado en la pasada anterior	
	    	IIndividual ind = actsrc.get(j);	    
	    	double FitnessActual= ((IValueFitness) ind.getFitness()).getValue();
			
	    	//Calculo su valor con respecto a la poblacion
	    	conteo= (FitnessActual*actsrcsz) / TotalFitness;	  
	       
	    	//Se ha revisado la poblacion una vez entero debemos pasar al paso 2
	    	if(i == (actsrcsz-1)) {
	    	   //Se han seleccionado todos los padres con valores enteros
	    	   i=0; //Vuelvo a analizar toda la poblacion
	           restantes=0; // Variable que controla que no se vuelva a entrar en la parte 1	    	   
	    	   break;
	    	   }
	       
	    	//Calculo si es bueno
	        if ((int)conteo > 0) {    	   
				i++;//Prepararo para comprobar siguiente elemento				 
				//Selecciono el elemento actual	
				numSeleccionados++;
				return ind;				
			}
	       i++;
	       }
	    }
	    
	    //Parte 2 de los valores restantes se selecciona probabilisticamente.
	    if(restantes == 0)
	    {	     
	    	for(int j=i; j<actsrcsz;j++){
	    	//empiezo desde el ultimo elemento analizado en la pasada anterior
	    	IIndividual ind = actsrc.get(j);	
	    	double FitnessActual= ((IValueFitness) ind.getFitness()).getValue();
	    	
	    	//Calculo su valor con respecto a la poblacion
	    	conteo= (FitnessActual*actsrcsz) / TotalFitness;	   
	    	 
	    	//Se ha dado una vuelta completa debo dar otra
	    	if(i == (actsrcsz-1)) 
	    		i=-1; 
	    		
	    	//Compruebo que es un elemento de los deseados en esta parte
	        if ((int)conteo < 1) {
			  //Obtenga un aleatorio
	    	  Random rnd = new Random();
	   		  double probabilidad;
	   		
	   	      probabilidad = rnd.nextDouble();
	   	   	
	    	  if (probabilidad <= conteo){	    	  
			    i++;//Prepararo para comprobar siguiente elemento
				//Selecciono el elemento actual
				numSeleccionados++;
				return ind;	
	           }	
			}
	       i++;
	       
           }
	     
	       //Descarto individuoos con muy poco fitness
	       while(numSeleccionados < actsrcsz){
	    	 numSeleccionados++;
	    	 return actsrc.get(0); 
	       }
	     
	   }

	    // Este caso nunca se realizara
		return actsrc.get(0);		
	}		
}
