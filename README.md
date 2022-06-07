# SAD-project

## Pràctica 1
Programació d'una classe EditableBufferedReader que anul·li el mètode readline amb les següents capacitats d'edició:

right, left: caràcter dret, caràcter esquerre amb les fletxes.
home, end: principi, final de línia.
ins: commuta mode inserció/sobre-escriptura.
del, bksp: esborra caràcter actual o caràcter a l'esquerra.

A l'haver de detectar aquestes tecles la consola s'ha de passar de mode cooked, el mode per defecte on els caràcters es llegeixen després de prémer cr, és a dir línia a línia, a mode raw, on els caràcters es llegeixen immediatament després de ser premuda la seva tecla.

La *primera versió* de la pràctica consisteix en les classes TestReadLine, EditableBufferedReader i Line sense MVC. El parser de seqüències d'escape a read es programa llegint caràcter-a-caràcter sense fer ús de la classe Scanner. Line encapsula l'estat i és independent de la presentació.

La *segona versió* de la pràctica es fa servir el patró mvc i Observer/Observable. S'utilitza la classe Line (Model), Console (View) i EditableBufferedReader (Controller) més la de test TestReadLine. S'amplia per poder fer edició de línies multilínia.
