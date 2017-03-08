# semanticweb_ws2016_gr16

## Task 1: OWL ontology

## Task 2: Extract TISS data

We extracted courses persons and rooms with the chrome-extension [Dataminer](https://data-miner.io/app). The recipes/scrape-files we used are saved in the scrape-file-folder. 
  * Courses: From the TISS extended course-search (factulty: Informatics, Institute: Computer languages) some simple xapth-specifications where enough to get the data in TSV-Format, Link-HREF-Attributes where included to ease later linking.
  * Persons: ?
  * Rooms: ? 

The resulting csv-files are stored at src/main/resources/csv. 

## Task 3: RDFize your extracted data

We used open-refine (RDFRefine-Extension) to create RDF-Files from them (src/main/resources/rdf). 
  * Courses: The windowId query-parameter was removed with the expression `value.replace(/windowId=.*?&/, "")`

Since we scraped the Links and used them as our URIs we did not need to use reconciliation. 

## Task 4: Load your RDF graph(s) into a triple store

For testing purposes (in the implementation we load the rdf-files programmatically into Jena) we used Jena Fuseki. Just upload the files (university.owl and then the rdf-files in src/main/resources/rdf) to a Jena Fuseki to reproduce that step (all in one dataset). 

## Task 5: Implement a simple application

## Task 6: Suggest a TU Linked Data Application




