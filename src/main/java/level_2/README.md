# Exercici 1 – Java Properties / Apache Commons Configurations

##  Enunciat de l'exercici
En aquest nivell aprendràs a parametritzar el comportament de les teves aplicacions, un pas fonamental per fer-les més flexibles, reutilitzables i adaptables a diferents entorns.

Et centraràs a extreure la configuració del codi i traslladar-la a un fitxer extern, com ara un fitxer .properties, molt utilitzat en projectes Java. També tindràs l’opció d’explorar llibreries més avançades com Apache Commons Configuration.

A partir d’un exercici ja resolt del nivell anterior, modificaràs el teu programa per llegir la configuració següent des d’un fitxer:

    Quin directori cal llegir.
    Quin ha de ser el nom i ubicació del fitxer TXT resultant.

Aquesta pràctica t’ajudarà a entendre millor com separar la configuració de la lògica del programa, una habilitat clau en entorns professionals i en el desenvolupament d’aplicacions escalables.
Exercici 1

Executa l'exercici 3 del nivell anterior parametritzant tots els mètodes en un fitxer de configuració.

Pots utilitzar un fitxer Java Properties, o bé la llibreria Apache Commons Configuration si ho prefereixes.

De l'exercici anterior, parametritza el següent:

    Directori a llegir.
    Nom i directori del fitxer TXT resultant.

## 🛠 Tecnologies
- Backend: Java

##  Instal·lació i Execució
1. Clonar el repositori: `git clone ...`
2. Execució de l'aplicació.
3. Proves: Executar el `Main()`.
4. To run the program with simple java (No Maven).
   1. run from root of the project `dir /s /b src\main\java\*.java > sources.txt` (Finds all Java source files)
   2. run `javac -d out @sources.txt` to compile all the java files
   3. run `copy src\main\resources\app.properties out\` to copy properties file to "out" directory
   4. run `java -cp out level_2.Main` to run the program

