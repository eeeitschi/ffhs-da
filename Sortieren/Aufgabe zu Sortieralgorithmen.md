# Aufgaben zu Sortierverfahren #

1. Implementieren Sie __Sortieren durch Einfügen__ für ein Teilstück eines int-Arrays.
2. Implementieren Sie __Quicksort__ für int-Arrays
3. Testen Sie, für welche Grössen der zu sortierenden Arrays 
'Sortieren durch Einfügen' bzw. Quicksort schneller ist.
4. Modifizieren Sie Quicksort so, dass bei den rekursiven Aufrufen bei kleinen Argumenten 'Sortieren duch 
Einfügen' aufgerufen wird. (Entscheiden Sie auf Grund der Überlegungen 
aus der vorherigen Aufgabe, wann der Wechsel auf 'Sortieren duch 
Einfügen' erfolgen soll.)
5. Testen und beschreiben Sie, wie gross die Verbesserung aus Teilaufgabe 4 
gegnüber des reinen Quicksorts ist.
6. __Quicksort__ ist eines der schnellsten Sortierverfahren, aber nur im Mittel. Das 
Worstcase-Verhalten von Quicksort ist hingegen schlecht:&nbsp;
<i>O</i>(n<sup>2</sup>). <br/>Im Gegensatz dazu ist __Heapsort__ im Mittel etwas langsamer als 
Quicksort, aber das Worstcase-Verhalten von Heapsort ist besser, nämlich
<i>O</i>(n * log<sub>2</sub>(n)).
7. __Introsort__
 ist eine Kombination dieser beiden Verfahren: <br/>
Man beginnt ein Array mit
 __Quicksort__ zu sortieren (oder mit der Variante aus der vorhergehenden 
Aufgabe); wird die Rekursionstiefe jedoch zu gross, dann ruft man nicht 
mehr nicht mehr __Quicksort__ auf, sondern __Heapsort__. <br/>
Eine Möglichkeit für
einen Schwellwert zum Wechseln auf __Heapsort__ ist eine Rekursionstiefe von 
2 * log<sub>2</sub>(n).
8. Implementieren Sie __Heapsort__, um ein Teilstück eines Arrays zu Sortieren.
9. Passen Sie __Quicksort__ so an, dass die maximal zulässige Rekursionstiefe 
als Parameter übergeben wird; bei jedem rekursiven Aufruf wird diese Tiefe um 1 reduziert.
10. Kombinieren Sie diese beiden obigen Teilaufgaben, 
um Introsort zu implementieren (mit dem Schwellwert von 2 * log<sub>2</sub>(n) für die Rekursionstiefe).

Verwenden Sie die Klassenrümpfe aus diesem Package. 
