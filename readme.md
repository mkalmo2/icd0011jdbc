# Jdbc

1. Uurige kuidas käitub **try with resources** konstruktsioon (toorkikus `TryWithResources.java`).

2. Ühenduge andmebaasiga läbi Idea.  
   **Database sakk → + → DataSource → PostgreSQL**

   Vajalikud andmed saate hindamissüsteemist (<https://ci.itcollege.ee/ci/icd0011>) menüüst *Andmebaas*.

3. Looge Idea abil andmebaasi jada (sequence) ja isikute tabel:

   ```sql
   CREATE SEQUENCE seq1 START WITH 1;

   CREATE TABLE person (
      id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('seq1'),
      name VARCHAR(255) NOT NULL,
      age INT
   );
   ```

4. Sisestage läbi Java koodi `person` tabelisse kaks kirjet.  
   Ühenduse andmed saate määrata failis `src/main/resources/application.properties`.  
   Baasiga ühenduse näide on failis `Main.java`.

5. Küsige baasist tabeli **person** sisu ja näidake välja (`System.out`).

6. Tehke meetod, mis võtab **person** tabelist andmed, teeb nende põhjal `Person` objektid ja tagastab listi `Person` objektidega.

7. Tõstke isikute tagastamise meetod `PersonDao` klassi. Kontrollige, et meetod ka uues kohas töötab.

8. Sisestage mitu SQL lauset failist.  
   Sisestatavad laused on failis:

   - `src/main/resources/schema.sql`
   - `src/main/resources/data.sql`

   Failide lugemiseks on projektis klass `FileUtil`.

   ```java
   FileUtil.readFileFromClasspath("schema.sql");
   ```

   Käivitage laused `executeUpdate()` meetodiga (see käivitab ka mitu lauset korraga).

9. Tehke DAO-sse meetod, millelt saab ID järgi isiku küsida.

10. Tehke DAO-sse meetod isiku sisestamiseks. Meetod peaks tagastama sisestatava isiku objekti, millel on `id` väli täidetud.

11. Jälgige, kuidas käitub klass ühenduste pool (`BasicDataSource`).  
    Näide on failis `ConnectionPoolExample.java`. Seal on kasutuses abiklass `ConnectionPoolFactory`, milles on puuli loomine ja initsialiseerimine.

    Jälgige, kuidas muutub aktiivsete ja passiivsete (idle) ühenduste arv.  
    Proovige, mitu ühendust saate puulist küsida ja mis juhtub, kui ühendused otsa saavad.

12. Pange `PersonDao` kasutama ühenduste puuli.

13. Sellest ülesandest võib teil kasu olla, kui soovite teha hw05a lisaülesannet.  

    Ülesande eesmärk on näidata, kuidas töötab Javas dünaamiline proksi. Proksi on objekt, mis vahendab meetodite väljakutseid mingi "päris" objekti vahel. See võimaldab muuta objektide käitumist ilma, et objekti ennast muudaksime.

    [Java proksit tutvustav artikkel](https://www.baeldung.com/java-dynamic-proxies)

    Paketis `proxy` on klass `ListProvider`, millelt saab küsida `MyList` tüüpi objekti.  
    Klassis `Main` on näide, kuidas list küsida ja selle meetodeid välja kutsuda.

    Tehke nii, et `ListProvider` tagastaks hoopis proksi, mis delegeerib kõik väljakutsed päris `MyListImpl` tüüpi objektile aga lisaks sellele logib kõik väljakutsed.

    **Mis on `MyList`, `MyListImpl` ja `ListProvider`?**

    - `MyList` liides on `java.util.List` liides, millel on tüüp fikseeritud (`Integer`). Lihtsam versioon `List`-ist.  
    - `MyListImpl` on selle liidese implementatsioon, mis pärineb `ArrayList`-ist.  
    - `ListProvider` tegeleb objektide loomise delegeerimisega.

    Kui loome uue objekti lihtsalt võtmesõnaga `new`, siis proksit ei saa vahele panna:

    ```java
    MyList list = new MyListImpl();
    ```

    Kui aga delegeerime objekti loomise mingile koodile, siis võib see tagastada kas päris objekti või proksi:

    ```java
    MyList myList = new ListProvider().getList();
    ```

    Muutujas `myList` võib olla ka proksi.

---

**Seletused ja lahendused:** <https://youtu.be/Mkl0EtWGNK8>
