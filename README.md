# GeneticAlgorithm

Projekt Programowanie Obiektowe \
Termin zajęć: Wtorek 9:15 
## Informacje 
- Kompilowane na JDK wersja: *12.0.1*
- Użyte środowisko automatycznego budowania: *Maven*
- Komenda kompilacji/uruchomienia: **mvn javafx:run**
## Dokumentacja
Kompletna dokumantacja znajduje się pod [**tym adresem**](http://ultux.github.io/GeneticAlgorithm).
## Opis symulacji
Prosty program symulacyjny, przedstawiający uproszczony proces selekcji
naturalnej z zastosowaniem prostego algorytmu genetycznego.  
Celem symulacji jest przeprowadzenie 2 róznie zachowujących się populacji przez
przygotowaną wcześniej planszę na punkt końcowy. Na planszy umieszczone
zostanie kilka przeszkód (osobnych obiektów klasy reprezentującej
przeszkody), które mają zostać ominięte przez populacje. Każdy członek
populacji jest oddzielnym obiektem klasy. Populacje będą różnić się od siebie
o sposób poruszania się (będą miały rózne prędkości średnie). Każdy obiekt
tej klasy posiada własny kod genetyczny będący obiektem oddzielnej klasy
zawierającej tablice wektorów opisujących jak ten obiekt ma się poruszać po
planszy. “DNA” każdego członka populacji składane jest z kodu genetycznego
jego rodziców, dodatkowo ma szansę na mutację czyli na zmianę losowego
elementu tablicy na inną liczbę. Koniec każdej epoki symulacji oznacza
stworzenie nowych obiektów będących potomstwem tych, którym najdalej
udało się dojść w poprzedniej epoce. Dodatkowo możliwa na początku
symulacji będzie zmiana liczności populacji początkowej, szansy na mutację,
zmianę układu przeszkód oraz liczby epok do wykonania.
## Wizualizacja symulacji
![Podgląd](https://raw.githubusercontent.com/ULTUX/GeneticAlgorithm/master/wizualizacja.png)
