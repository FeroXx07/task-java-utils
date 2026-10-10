# Exercici 1 – AES (Advanced Encryption Standard)

##  Enunciat de l'exercici
En aquest últim nivell t’endinsaràs en un concepte clau per a la seguretat informàtica: l'encriptació de dades.

Et tocarà crear una utilitat que permeti encriptar i desencriptar fitxers, aplicant un dels algorismes més utilitzats en el món real: AES (Advanced Encryption Standard), en modes ECB o CBC, amb l'ompliment PKCS5Padding.

Podràs utilitzar les biblioteques estàndard de Java (javax.crypto) o explorar alternatives més potents com org.apache.commons.crypto.

L’objectiu és entendre com protegir la informació sensible mitjançant criptografia simètrica, i aplicar-ho a casos reals com els fitxers generats en exercicis anteriors. Aquest exercici et prepara per a entorns professionals on la seguretat i la privadesa són essencials.
Exercici 1

Crea una utilitat que encripti i desencripti els fitxers resultants dels nivells anteriors.

Fes servir l'algorisme AES en manera de treball ECB o CBC amb mètode d'ompliment PKCS5Padding. Es pot emprar javax.crypto o bé org.apache.commons.crypto.
Objectius

    Practicaràs l’ús de les llibreries bàsiques de Java (java.io, java.util, java.nio.file, etc.).
    Aprendràs a navegar per directoris, crear arxius, llegir i escriure textos, i a treballar amb fitxers de configuració.
    Coneixeràs el procés de serialització i deserialització d’objectes.
    Descobriràs com protegir dades amb encriptació AES.
    T’asseguraràs que el teu codi sigui portable, utilitzant rutes relatives i File.separator.
    Et familiaritzaràs amb el procés de compilació i execució manual dels teus 

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
   4. run `java -cp out level_3.Main` to run the program

