# GA-NS-comparison-tool
Genetic algorithm &amp; Novelty search algorithm performance comparison tool

Genetic algorithm &amp; Novelty search algorithm performance comparison tool

The GA/NS comparison tool is a proof of concept for the use of a novelty-search based approach to assist the software engineer in conceptual software design, particularly in the creation of object-oriented software class designs.

The GA/NS comparison tool applies an objective search-based algorithm and a novelty-search based algorithm to problem domain data, read from a text file, of a conceptual software design [case study](https://drive.google.com/file/d/1X9H55u2tMg6zmOTWv32VJqq2ViUZeAVp/view?usp=sharing). The application tool then outputs software class models generated from the problem domain data and the application of the algorithms. For comparison purposes, each algorithm performance is displayed on a fitness graph.

The fitness measures calculated for the objective-based algorithm are Coupling Between Objets (CBO) and Numbers Among Classes (NAC) elegance. Both algorithms follow the classic, simple elitist evolutionary schema with binary tournament. The novelty score for the novelty-based algorithm is measured by calculating the average distance of an individual to the rest of individuals in the population; the distance between individuals being the difference between the fitness measures of coupling or elegance. 

The application uses the [JCLEC framework](http://jclec.sourceforge.net/) to implement the objective-based search algorithm. 

### Installation

The application is a Maven Netbeans project. Import the project in the *NetbeansProjects* folder of your Netbeans instalation and launch the application Netbeans, by running `AppMain.java` located in the package `m.view`. In case the application does not run at first instance, perform a clean and build to allow Maven repositories downloads.

#### Further reading

[The Novelty Search Users Page](eplex.cs.ucf.edu/noveltysearch/userspage/)


