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
  * Courses: The windowId query-parameter (which is some kind of session/conversation value or something) and the semester query-parameter (to remove duplicates) was removed with the expression `value.replace(/windowId=.*?&/, "").replace(/&semester=.*/, "")`

Since we scraped the Links and used them as our URIs we did not need to use reconciliation. 

## Task 4: Load your RDF graph(s) into a triple store

For testing purposes (in the implementation we load the rdf-files programmatically into Jena) we used Jena Fuseki. Just upload the files (university.owl and then the rdf-files in src/main/resources/rdf) to a Jena Fuseki to reproduce that step (all in one dataset). 

## Task 5: Implement a simple application

## Task 6: Suggest a TU Linked Data Application

The [Curriculums](http://www.informatik.tuwien.ac.at/studium/angebot/studienplaene/informatik-archiv/informatik-studienplan-2016/StudienplanMasterstudiumSoftwareEngineeringInternetComputing.pdf) could be formulated in an Ontology and linked with the data in TISS. This could be used to help students plan their study. The student could for example would enter the semester he/she wants to be finished and the application would suggest which courses he/she needs to take in which semesters (some are in WS and SS but some are only in one of them) to be able to finish on time. 

Additionally the application could let students enter data about their experiences (work load, difficulty, ...). In turn this could help students to plan their semester to not overload themselves with work, or even better, help them to better distribute courses with high workloads throughout their study. If [VoWi](https://vowi.fsinf.at) used semantic markup the experience-data there could also be used. 

In TISS courses there are usually some requirements listed and the things one will learn. If they would use an open vocabulary one could use that to help students in selecting the courses that fit their interests. 


