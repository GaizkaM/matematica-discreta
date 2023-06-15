import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * L'avaluació consistirà en:
 *
 * - Si el codi no compila, la nota del grup serà de 0.
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - Tendrem en compte la neteja i organització del codi. Un estandard que podeu seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html.  Algunes
 *   consideracions importants: indentació i espaiat consistent, bona nomenclatura de variables,
 *   declarar les variables el més aprop possible al primer ús (és a dir, evitau blocs de
 *   declaracions). També convé utilitzar el for-each (for (int x : ...)) enlloc del clàssic (for
 *   (int i = 0; ...)) sempre que no necessiteu l'índex del recorregut.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Gaizka Medina Gordo
 * - Nom 2: David Gavilla Hernández
 * - Nom 3:
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital que obrirem abans de la data que se us
 * hagui comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més
 * fàcilment les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat,
 * assegurau-vos de que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {

    /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * Els mètodes reben de paràmetre l'univers (representat com un array) i els predicats adients
   * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
   * l'univers, podeu fer-ho com `p.test(x)`, que té com resultat un booleà (true si `P(x)` és
   * cert). Els predicats de dues variables són de tipus `BiPredicate<Integer, Integer>` i
   * similarment s'avaluen com `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats retorneu `true`
   * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
   * petit com per poder provar tots els casos que faci falta).
     */
    static class Tema1 {

        /*
     * És cert que ∀x ∃!y. P(x) -> Q(x,y) ?
         */
        static boolean exercici1(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
            
            // Bucle per a tot x dins l'uivers
            for (int x : universe) {
                // Variable unic que comprova si la condició es comnpleix
                //domés una vegada
                boolean unic = false;
                // Bucle que passa per tot y dins l'univers
                for (int y : universe) {
                    // Comprovació de si P(x) -> Q(x,y) es compleix amb la 
                    //seva negació
                    if (!( p.test(x) && !(q.test(x, y)))) {
                        // Comprovació de si P(x) -> Q(x,y) és únic
                        if (unic) {
                            // S'ha trobat més d'un y que satisfà la condició
                            return false; 
                        }
                        // Asignam unic a vertader per saber que aquesta condició
                        //ja s'ha acomplit i que no pot tornar a sortir
                        unic = true;
                    }
                }
                // Comprovam si ha hagut qualque P(x) que s'ha acomplit i que 
                //no és únic, això vol dir que existeix un x sense cap y que 
                //satisfà la condició
                if (p.test(x) && !unic) {
                    // No s'ha trobat cap y que satisfà la condició
                    return false; 
                }
            }
            // Per a tot x, hi ha exactament un y que satisfà P(x) -> Q(x,y)
            return true; 
        }

        /*
     * És cert que ∃!x ∀y. P(y) -> Q(x,y) ?
         */
        static boolean exercici2(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {

            // Bucle per a tot y dins l'univers
            for (int y : universe) {
                // Variable unic que comprova si la condició es comnpleix
                //domés una vegada
                boolean unic = false;
                // Bucle per a tot x dins l'uivers
                for (int x : universe) {
                    // Comprovació de si P(y) -> Q(x,y) es compleix amb la 
                    //seva negació
                    if (!(p.test(y) && !(q.test(x, y)))) {
                        // Comprovació de si P(y) -> Q(x,y) és únic
                        if (unic) {
                            // S'ha trobat més d'un y que satisfà la condició
                            return false;                             
                        }
                        // Asignam unic a vertader per saber que aquesta condició
                        //ja s'ha acomplit i que no pot tornar a sortir
                        unic = true;
                    }
                }
                // Comprovam si ha hagut qualque P(y) que s'ha acomplit i que 
                //no és únic, això vol dir que existeix un y sense cap x que 
                //satisfà la condició
                if (p.test(y) && !unic) {
                    // No s'ha trobat cap x que satisfà la condició
                    return false; 
                }
            }
            // Hi ha exactament un x que per a tot y satisfà P(y) -> Q(x,y)
            return true; 
           
        }

        /*
     * És cert que ∃x,y ∀z. P(x,z) ⊕ Q(y,z) ?
         */
        static boolean exercici3(int[] universe, BiPredicate<Integer, Integer> p, BiPredicate<Integer, Integer> q) {

            // Variable existeixXY que comprova si la condició es compleix 
            //com a mínim una vegada dins x i y
            boolean existeixXY=false;
            // Bucle per a tot x dins l'univers
            for (int x : universe) {
                // Bucle per a tot y dins l'univers
                for (int y : universe) {
                    // Variable booleana totZ que comprova que la condició
                    //es compleix per a tots el elements de z
                    boolean totZ = true;
                    // Bucle per a tot z dins l'univers
                    for (int z : universe) {
                        // Comprovació de si la condició P(x,z) ⊕ Q(y,z) no es
                        //compleix
                        if ((p.test(x, z) && q.test(y, z))
                            ||  (!(p.test(x, z)) && !(q.test(y, z)))) {
                            // Si la condició es compleix, vol dir que la condició 
                            //P(x,z) ⊕ Q(y,z) no es compleix per a tot z
                            totZ= false;
                            return false;
                        // Comprovació de que la condició es compleix almenys
                        //una vegada
                        }else if ((!(p.test(x, z)) && q.test(y, z))
                            ||  (p.test(x, z) && !(q.test(y, z)))){
                            existeixXY=true;
                        }
                    } 
                }
            }
            // La condició no es compleix cap vegada
            if (!existeixXY){
                return false;
            // La condició P(x,z) ⊕ Q(y,z) existeix dins x i y per a tot z
            }else{
            return true;
            }
        }

        /*
     * És cert que (∀x. P(x)) -> (∀x. Q(x)) ?
         */
        static boolean exercici4(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
            
            // Variable booleana esVaertadera que comprova si la condició es
            //compleix
            boolean esVertadera=false;
            // Bucle per a tot x dins l'univers
            for (int x:universe){
                // Si P(x) es falsa, la condició P(x) -> Q(x) es vertadera sempre
                if (!(p.test(x))){
                    esVertadera=true;
                    }else{
                    // Si P(x) i Q(x) son vertaders, la condició es compleix
                    if (q.test(x)){
                        esVertadera=true;
                    // Si P(x) es vertadera i Q(x) falsa, la condició no es compleix
                    }else{
                        esVertadera=false;
                    }
                }
                if (!esVertadera){
                return false;
                }
            }
            // Si hem sortit del bucle sense cap condició falsa, la condició es
            //compleix per a tot x
            return true;    
        }

        /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
    static void tests() {
      // Exercici 1
      // ∀x ∃!y. P(x) -> Q(x,y) ?

      assertThat(
          exercici1(
              new int[] { 2, 3, 5, 6 },
              x -> x != 4,
              (x, y) -> x == y
          )
      );

      assertThat(
          !exercici1(
              new int[] { -2, -1, 0, 1, 2, 3 },
              x -> x != 0,
              (x, y) -> x * y == 1
          )
      );

      // Exercici 2
      // ∃!x ∀y. P(y) -> Q(x,y) ?

      assertThat(
          exercici2(
              new int[] { -1, 1, 2, 3, 4 },
              y -> y <= 0,
              (x, y) -> x == -y
          )
      );

      assertThat(
          !exercici2(
              new int[] { -2, -1, 1, 2, 3, 4 },
              y -> y < 0,
              (x, y) -> x * y == 1
          )
      );

      // Exercici 3
      // ∃x,y ∀z. P(x,z) ⊕ Q(y,z) ?

      assertThat(
          exercici3(
              new int[] { 2, 3, 4, 5, 6, 7, 8 },
              (x, z) -> z % x == 0,
              (y, z) -> z % y == 1
          )
      );

      assertThat(
          !exercici3(
              new int[] { 2, 3 },
              (x, z) -> z % x == 1,
              (y, z) -> z % y == 1
          )
      );

      // Exercici 4
      // (∀x. P(x)) -> (∀x. Q(x)) ?

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3, 4, 5, 8, 9, 16 },
              x -> x % 2 == 0, // x és múltiple de 2
              x -> x % 4 == 0 // x és múltiple de 4
          )
      );

      assertThat(
          !exercici4(
              new int[] { 0, 2, 4, 6, 8, 16 },
              x -> x % 2 == 0, // x és múltiple de 2
              x -> x % 4 == 0 // x és múltiple de 4
          )
      );
    }
    }

    /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * Per senzillesa tractarem els conjunts com arrays (sense elements repetits). Per tant, un
   * conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam donant el domini
   * int[] a, el codomini int[] b, i f un objecte de tipus Function<Integer, Integer> que podeu
   * avaluar com f.apply(x) (on x és d'a i el resultat f.apply(x) és de b).
     */
    static class Tema2 {

        /*
     * Comprovau si la relació `rel` definida sobre `a` és d'equivalència.
     *
     * Podeu soposar que `a` està ordenat de menor a major.
         */
        static boolean exercici1(int[] a, int[][] rel) {
            
            // Bucle recorregut de tots els elements de a
            for(int x: a){
                // Variables booleanes per comprovar si el conjunt té relació
                //reflexiva, simetrica i transitiva
                boolean es_reflexiu=false;
                boolean es_simetric=false;
                boolean es_transitiu=true;
                
                // Recorregut de cada parella dins rel 
                for(int[] parellaA : rel){
                    // Comprovació de que la parella és reflexiva ( a R a)
                    if ((parellaA[0] == x) 
                        && (parellaA[1]== x)){
                        es_reflexiu= true;
                    }
                    // Comprovació de que la parella és simètrica (si per a tot
                    //a i b, a R b -> b R a
                    if(parellaA[0] == x){
                        // Segon recorregut per a trobar la relació
                        for(int [] parellaB :rel){
                            // Comprovació de a R b -> b R a
                            if (parellaB[0]==parellaA[1] && parellaB[1]==x){
                                es_simetric=true;
                                break;
                            }
                        }
                        // Si hi ha una parella que no es simètrica, el conjunt no és
                        //simètric i per tant no és d'equivalència
                        if(!es_simetric){
                            return false;
                        }
                    }
                    // Comprovació de que la parella és transitiva (si per a tot
                    //a, b i c es compleix a R b & b R c -> a R c
                    //Segon recorregut
                    for (int[] parellaB:rel){
                        // Comprovació de a R b & b R c
                        if((parellaA[1]==parellaB[0])&&(parellaA[0]!=parellaB[1])){
                            // Variable booleana que ens comprova si la condició 
                            //es compleix per no fer recorreguts innecesaris
                            boolean condicio_trans=false;
                            // Tercer recorreut
                            for(int[]parellaC:rel){
                                // Comprovació de a R c
                                if((parellaC[0]==parellaA[0])&&(parellaC[1]==parellaB[1])){
                                // La condició es compleix
                                condicio_trans=true;
                                break;
                                }
                            }
                            // Si la condició no es compleix, el conjunt no és
                            //transitiu
                            if(!condicio_trans){
                                es_transitiu=false;
                            }
                        }
                    }
                }
                // Comprovació de que l'element actual es d'equivalència 
                //(reflexiu, simetric i transitiu)
                if(!es_reflexiu && !es_simetric && !es_transitiu){
                    return false;
                }
            }
            // Si després de totes les condicions no ha donat fals, vol dir que 
            //el conjunt és d'equivalència
            return true;
        }

        /*
     * Comprovau si la relació `rel` definida sobre `a` és d'equivalència. Si ho és, retornau el
     * cardinal del conjunt quocient de `a` sobre `rel`. Si no, retornau -1.
     *
     * Podeu soposar que `a` està ordenat de menor a major.
         */
        static int exercici2(int[] a, int[][] rel) {
            
            // Variable cardinalConjuntQuocient que emprarem per retornar-la si
            //la condició es compleix
            int cardinalConjuntQuocient = a.length + rel.length;
                    
            for(int x: a){
                // Variables booleanes per comprovar si el conjunt té relació
                //reflexiva, simetrica i transitiva
                boolean es_reflexiva=false;
                boolean es_simetrica=false;
                boolean es_transitiva=true;
                
                // Recorregut de cada parella dins rel
                for(int[] parellaA : rel){
                    // Comprovació de que la parella és reflexiva ( a R a)
                    if ((parellaA[0] == x) 
                        && (parellaA[1]== x)){
                        es_reflexiva= true;
                    }
                    // Comprovació de que la parella és simètrica (si per a tot
                    //a i b, a R b -> b R a
                    if(parellaA[0] == x){
                        // Segon recorregut per a trobar la relació
                        for(int [] parellaB :rel){
                            // Comprovació de a R b -> b R a
                            if (parellaB[0]==parellaA[1] && parellaB[1]==x){
                                es_simetrica=true;
                                break;
                            }
                        }
                        // Si hi ha una parella que no es simètrica, el conjunt no és
                        //simètric i per tant no és d'equivalència
                        if(!es_simetrica){
                            return -1;
                        }
                    }
                    // Comprovació de que la parella és transitiva (si per a tot
                    //a, b i c es compleix a R b & b R c -> a R c
                    //Segon recorregut
                    for (int[] parellaB:rel){
                        // Comprovació de a R b & b R c
                        if((parellaA[1]==parellaB[0])&&(parellaA[0]!=parellaB[1])){
                            // Variable booleana que ens comprova si la condició 
                            //es compleix per no fer recorreguts innecesaris
                            boolean condicion_trans=false;
                            // Tercer recorreut
                            for(int[]parellaC:rel){
                                // Comprovació de a R c
                                if((parellaC[0]==parellaA[0])&&(parellaC[1]==parellaB[1])){
                                    // La condició es compleix
                                    condicion_trans=true;
                                    break;
                                }
                            }
                            // Si la condició no es compleix, el conjunt no és
                            //transitiu
                            if(!condicion_trans){
                                es_transitiva=false;
                            }
                        }
                    }
                }
                // Comprovació de que l'element actual es d'equivalència 
                //(reflexiu, simetric i transitiu)
                if(!es_reflexiva && !es_simetrica && !es_transitiva){
                    return -1;
                }
            }
            // Calculam el cardinal del conjunt quocient de a i rel
            for (int[] pareja : rel) {
                // Condició de si la parella es reflexiva (a R a)
                if (pareja[0] == pareja[1]) {
                    // Li restam la parella reflexiva
                    cardinalConjuntQuocient--; 
                }   
            }
            // Si després de totes les condicions no ha donat fals, vol dir que 
            //el conjunt és d'equivalència i per tant retornam el cardinal del
            //quocient de a sobre rel calculat abans
            return cardinalConjuntQuocient;
        }

    /*
    * Comprovau si la relació `rel` definida entre `a` i `b` és una funció.
    *
    * Podeu soposar que `a` i `b` estan ordenats de menor a major.
    */
        
    static boolean exercici3(int[] a, int[] b, int[][] rel) {
            
    // Verificar si la mida de rel coincideix amb la mida de a i b
    if (rel.length != a.length || rel.length != b.length) {
        return false;
    }
    
    // Comprovar si cada element de a té una correspondència única a b
    for (int elementA : a) {
        boolean correspondencia = false;
        
        // Recorregut
        for (int[] pareja : rel) {
            if (pareja[0] == elementA) {
                // Verificar si aquest element ja té una correspondència
                if (correspondencia) {
                    return false; // L'element té múltiples correspondències
                }
                
                correspondencia = true;
                
                // Verificar si la correspondència és vàlida
                if (pareja[1] != b[Arrays.asList(a).indexOf(elementA)]) {                   
                    // La correspondència no és correcta
                    return false; 
                }
            }
        }
        
        // Verificar si l'element té una correspondència
        if (!correspondencia) {
            return false;
        }
    }
    
    // Si s'han superat totes les comprovacions, és una funció vàlida
    return true;

    }

        /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Retornau:
     * - Si és exhaustiva, el màxim cardinal de l'antiimatge de cada element de `codom`.
     * - Si no, si és injectiva, el cardinal de l'imatge de `f` menys el cardinal de `codom`.
     * - En qualsevol altre cas, retornau 0.
     *
     * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major.
        */
        static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
            
        boolean es_exhaustiva = true;
        boolean es_inyectiva = true;
        
        //Cream un array de imatges en funció de la mida del codomini, per
        //poder emprarles per a verificar si la funció es injectiva
        int cardinal_codom = codom.length;
        int[] imatge_f = new int[cardinal_codom];
        int max_cardinal_antiimatge = 0;
        

        // Verificam exhaustivitat
        // Recorregut de tots el elements de codomini
        for (int y : codom) {
            boolean encontrado = false;
            
            // Comporbació de si l'element actual del codomini
            // té antiimatge
            for (int x : dom) {
                if (f.apply(x) == y) {
                    encontrado = true;
                    break;
                }
            }
            
            // Si un element del codomini no té antiimatge, la funció no es 
            //exhaustiva
            if (!encontrado) {
                es_exhaustiva = false;
                break;
            }
        }

        // Verificam injectivitat
        for (int x : dom) {
            int imagen = f.apply(x);

            // Verificam si la imatge ja es troba dins el conjunt d'imatges
            //del codomini, de manera que si qualque imatge del domini es 
            //igual a una del codomini, la funció no pot ser injectiva
            for (int i = 0; i < cardinal_codom; i++) {
                if (imatge_f[i] == imagen) {
                    es_inyectiva = false;
                    break;
                }
            }

        // Si ja sabem que no es injectiva, sortim de la verificació
        if (!es_inyectiva) {
            break;
        }

        // Almacenam la imatge en l'array
        imatge_f[x] = imagen;
    }

        // Comprobam si es exhaustiva, injectiva o cap de les dues
        if (es_exhaustiva) {
            // Calculam el màxim cardinal de la antiimatge de cada element de codom
            for (int y : codom) {
                int cardinal_antiimatge = 0;
                // Per a cada element del codomini amb antiimatge, aumengtam el
                //contador de antiimatges
                for (int x : dom) {
                    if (f.apply(x) == y) {
                        cardinal_antiimatge++;
                    }
                }
                // Calculam el màxim cardinal de l'antiimatge amb la funció max 
                //de la classe Math
                max_cardinal_antiimatge = Math.max(max_cardinal_antiimatge, 
                                                   cardinal_antiimatge);
            }
            // Retornam el màxim cardinal
            return max_cardinal_antiimatge;
   
        } else if (es_inyectiva) {
            
            // Variable cardinal imatge inicialitzada a 0
            int cardinal_imatge = 0;
            
            // Recorregut de tots els cardinals de codom
            for (int i = 0; i < cardinal_codom; i++) {
                // Si la imatge de l'element actual es diferent de 0, la imatge 
                //té cardinal
                if (imatge_f[i] != 0) {
                    cardinal_imatge++;
                }
            }
            // Calculam el cardinal de la imatge de f menys el cardinal de codom
            return cardinal_imatge - cardinal_codom;
        //si no es cap de les dues, retornam 0
        } else {
            return 0;
        }    
    }

        /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
    static void tests() {
      // Exercici 1
      // `rel` és d'equivalencia?

      assertThat(
          exercici1(
              new int[] { 0, 1, 2, 3 },
              new int[][] { {0, 0}, {1, 1}, {2, 2}, {3, 3}, {1, 3}, {3, 1} }
          )
      );

      assertThat(
          !exercici1(
              new int[] { 0, 1, 2, 3 },
              new int[][] { {0, 0}, {1, 1}, {2, 2}, {3, 3}, {1, 2}, {1, 3}, {2, 1}, {3, 1} }
          )
      );

      // Exercici 2
      // si `rel` és d'equivalència, quants d'elements té el seu quocient?

      final int[] int09 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

      assertThat(
          exercici2(
            int09,
            generateRel(int09, int09, (x, y) -> x % 3 == y % 3)
          )
          == 3
      );

      assertThat(
          exercici2(
              new int[] { 1, 2, 3 },
              new int[][] { {1, 1}, {2, 2} }
          )
          == -1
      );

      // Exercici 3
      // `rel` és una funció?

      final int[] int05 = { 0, 1, 2, 3, 4, 5 };

      assertThat(
          exercici3(
            int05,
            int09,
            generateRel(int05, int09, (x, y) -> x == y)
          )
      );

      assertThat(
          !exercici3(
            int05,
            int09,
            generateRel(int05, int09, (x, y) -> x == y/2)
          )
      );

      // Exercici 4
      // el major |f^-1(y)| de cada y de `codom` si f és exhaustiva
      // sino, |im f| - |codom| si és injectiva
      // sino, 0

      assertThat(
          exercici4(
            int09,
            int05,
            x -> x / 4
          )
          == 0
      );

      assertThat(
          exercici4(
            int05,
            int09,
            x -> x + 3
          )
          == int05.length - int09.length
      );

      assertThat(
          exercici4(
            int05,
            int05,
            x -> (x + 3) % 6
          )
          == 1
      );
    }
        /// Genera un array int[][] amb els elements {a, b} (a de as, b de bs) que satisfàn pred.test(a, b)
        static int[][] generateRel(int[] as, int[] bs, BiPredicate<Integer, Integer> pred) {
            ArrayList<int[]> rel = new ArrayList<>();

            for (int a : as) {
                for (int b : bs) {
                    if (pred.test(a, b)) {
                        rel.add(new int[]{a, b});
                    }
                }
            }

            return rel.toArray(new int[][]{});
        }
    }

    /*
   * Aquí teniu els exercicis del Tema 3 (Grafs).
   *
   * Donarem els grafs en forma de diccionari d'adjacència, és a dir, un graf serà un array
   * on cada element i-èssim serà un array ordenat que contendrà els índexos dels vèrtexos adjacents
   * al i-èssim vèrtex. Per exemple, el graf cicle C_3 vendria donat per
   *
   *  int[][] g = {{1,2}, {0,2}, {0,1}}  (no dirigit: v0 -> {v1, v2}, v1 -> {v0, v2}, v2 -> {v0,v1})
   *  int[][] g = {{1}, {2}, {0}}        (dirigit: v0 -> {v1}, v1 -> {v2}, v2 -> {v0})
   *
   * Podeu suposar que cap dels grafs té llaços.
     */
    static class Tema3 {

        /*
        * Retornau l'ordre menys la mida del graf (no dirigit).
        */
        static int exercici1(int[][] g) {
            // Variables ordre que és igual a la longitud del array referent
            //als vértexos
            int ordre = g.length;
            int mida = 0;
            
            // Recorregut dels vertexos per calcular la mida del graf
            for(int [] vertexos : g){
                mida += vertexos.length;
            }
            // Retornam l'ordre menys la mida del graf
            return ordre - mida;
        }

        /*
        * Suposau que el graf (no dirigit) és connex. És bipartit?
         */
        static boolean exercici2(int[][] g) {
            // Array que contintindrà tots el vertexos que ja hem visitat i els
            //hem posat valor
            int[] visitats = new int[g.length];
            // Els inicialitzam sense valor (-1)
            Arrays.fill(visitats, -1);

            // Assignam el primer grup (0) al primer vertex
            visitats[0] = 0; 

            // Array cua que emprarem per guardar el vertex que hem de utilitzar
            int[] cua = new int[g.length];
            
            // Index del primer element de la cua
            int start = 0;
            
            // Index del seguent element de la cua a guardar
            int end = 0;

            cua[end++] = 0;

            while (start < end) {
                
                // Vertex actual a comprovar
                int vertex1 = cua[start++];

                // Variable grup_actual que guarda el valor del grup del 
                //vertex1
                int grup_actual = visitats[vertex1];

                // Recorregut de tots els vertexs veins de vertex1 per a 
                //comprovar a quins grups pertanyen
                for (int i = 0; i < g[vertex1].length; i++) {
                    // Vertex veí atual a comprovar
                    int vertex2 = g[vertex1][i];
                    
                    // El veí encara no té grup assignat
                    if (visitats[vertex2] == -1) {
                        
                        // Assignam el segon grup al vertex veí
                        visitats[vertex2] = 1 - grup_actual;
                        // Guardam el valor del vartex veí dins cua
                        cua[end++] = vertex2;
                        
                    // El grup del veí correspon amb el grup del vertex, això 
                    //ens indica que el graf g no és bipartit
                    } else if (visitats[vertex2] == grup_actual) {
                        return false;
                    }
                }
            }
            // Si cap vertex té relació amb el seu vertex veí, el graf es bipartit
            return true;
        }

        /*
        * Suposau que el graf és un DAG. Retornau el nombre de descendents amb grau de sortida 0 del
        * vèrtex i-èssim.
        */
        static int exercici3(int[][] g, int i) {
            
            // Nombre de descendents amb grau de sortida 0 inicialitzat a 0
            int num_descendents_grau0 = 0;
            
            // Recorregut de totes les columnes de la fila corresponent al 
            //vèrtex i-èssim de la matriu d'adjacència.
            for(int j=0;j<g[i].length;j++){
                
                //Comprovam si té descendents
                if (g[i][j]==1){
                    
                    // Booleà que comprovarà si el descendent del vèrtex té grau
                    //de sortida 0
                    boolean grau0=true;
                    
                    //Recorregut de totes les columnes de la fila corresponent al
                    //descendent del vèrtex i-èssim
                    for(int k=0;j<g[j].length;k++){
                       
                        //Si té qualque descent, vol dir que no té grau de 
                        //sortida 0
                       if(g[j][k]==1){
                           grau0=false;
                       }
                    }
                    // Si no té cap descendent, vol dir que té grau 0 i la condició
                    //es compleix
                    if(grau0){
                        num_descendents_grau0++;
                    }
                } 
            }
            // Retornam el nombre de descendents amb grau 0
            return num_descendents_grau0;
        }

        /*
     * Donat un arbre arrelat (dirigit, suposau que l'arrel es el vèrtex 0), trobau-ne el diàmetre.
     * Suposau que totes les arestes tenen pes 1.
         */
    static int exercici4(int[][] g) {
        
        // Variable arrel que representa el vèrtex 0
        int arrel = 0; 

        // Empram l'algorisme de Dijsktra per trobar 
        int[] desdArrel = dijkstra(g, arrel);

        // Cercam el vèrtex amb la mayor distància des de l'arrel
        int vertexmaxdistancia = trobarVertexMaxDistancia(desdArrel);

        // Empram l'alogisme de Dijkstra amb el vèrtex amb major distància per 
        //trobar la majora distància de totes
        int[] distanciamesenfora = dijkstra(g, vertexmaxdistancia);

        // Cercam la distància màxima entre l'arrel i qualsevol altre vèrtex.
        //Aquesta distància màxima serà el diàmetre del nostre graf
        int diametre = maxDistancia(distanciamesenfora);

        //Retornam el diàmetre del graf
        return diametre;
    }

    // Mètode dijkstra que implementa l'algorisme de Dijkstra a un graf i un vèrtex
    //declarats per paràmetre. El mètode retornara la distància de pes mínim del
    //vertex declarat dins el graf g
    static int[] dijkstra(int[][] g, int vertex) {
        
        int n = g.length;
        // Array distancies on anirem guardant el valor de les distàncies entre 
        //els vèrtexos
        int[] distancies = new int[n];
        Arrays.fill(distancies, Integer.MAX_VALUE);

        // Booleà visitat que emprarem per verificar si ja hem visitat el vèrtex
        boolean[] visitat = new boolean[n];
        distancies[vertex] = 0;

        for (int i = 0; i < n - 1; i++) {
            int minDistancia = Integer.MAX_VALUE;
            int minVertex = -1;

            // Recorregut per trobar el vèrtex no visitat amb la distància mínima
            for (int j = 0; j < n; j++) {
                if (!visitat[j] && distancies[j] < minDistancia) {
                    minDistancia = distancies[j];
                    minVertex = j;
                }
            }
            // Condició que surt del recorregut si hi ha vèrtexos que no es poden
            //arribar a ells
            if (minVertex == -1) {
                break; 
            }

            visitat[minVertex] = true;

            // Actualizar las distancias de los vértices adyacentes no visitados
            // Actualitzam les distancies dels vèrtexos adjancents no visitats
            for (int adjVertex : g[minVertex]) {
                // Si no ha estat visitat
                if (!visitat[adjVertex]) {
                    // Sumam 1 ja que es la distància entre vèrtexos a un arbre
                    int newDistance = distancies[minVertex] + 1; 
                    distancies[adjVertex] = Math.min(distancies[adjVertex], newDistance);
                }
            }
        }
        // El mètode retorna la distància mínima del vèrtex i el graf introduits
        //per paràmetre
        return distancies;
    }

    // Mètode trobarVertexMaxDistancia que ens retorna el vertex amb la major 
    //distància des del valor declarat per paràmetre
    static int trobarVertexMaxDistancia(int[] distancies) {
        int vertexMaxDis = 0;
        int maxDis = 0;

        // Recorregut del array distancies per trobar el vertex amb màxima distància
        for (int i = 0; i < distancies.length; i++) {
            // Comprovació de quin es el vèrtex amb la màxima distància
            if (distancies[i] > maxDis) {
                maxDis = distancies[i];
                vertexMaxDis = i;
            }
        }

        // Retorna el vèrtex 
        return vertexMaxDis;
    }

    // Mètode maxDistancia per trobar la màxima distancia del graf
    static int maxDistancia(int[] distancies) {
        int maxDistancia = 0;

        // Recorregut per trobar la màxima distancia
        for (int distancia : distancies) {
            maxDistancia = Math.max(maxDistancia, distancia);
        }

        // Retornam la màxima distància
        return maxDistancia;
    }

        /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
    static void tests() {
      final int[][] undirectedK6 = {
        { 1, 2, 3, 4, 5 },
        { 0, 2, 3, 4, 5 },
        { 0, 1, 3, 4, 5 },
        { 0, 1, 2, 4, 5 },
        { 0, 1, 2, 3, 5 },
        { 0, 1, 2, 3, 4 },
      };

      /*
         1
      4  0  2
         3
      */
      final int[][] undirectedW4 = {
        { 1, 2, 3, 4 },
        { 0, 2, 4 },
        { 0, 1, 3 },
        { 0, 2, 4 },
        { 0, 1, 3 },
      };

      // 0, 1, 2 | 3, 4
      final int[][] undirectedK23 = {
        { 3, 4 },
        { 3, 4 },
        { 3, 4 },
        { 0, 1, 2 },
        { 0, 1, 2 },
      };

      /*
             7
             0
           1   2
             3   8
             4
           5   6
      */
      final int[][] directedG1 = {
        { 1, 2 }, // 0
        { 3 },    // 1
        { 3, 8 }, // 2
        { 4 },    // 3
        { 5, 6 }, // 4
        {},       // 5
        {},       // 6
        { 0 },    // 7
        {},
      };


      /*
              0
         1    2     3
            4   5   6
           7 8
      */

      final int[][] directedRTree1 = {
        { 1, 2, 3 }, // 0 = r
        {},          // 1
        { 4, 5 },    // 2
        { 6 },       // 3
        { 7, 8 },    // 4
        {},          // 5
        {},          // 6
        {},          // 7
        {},          // 8
      };

      /*
            0
            1
         2     3
             4   5
                6  7
      */

      final int[][] directedRTree2 = {
        { 1 },
        { 2, 3 },
        {},
        { 4, 5 },
        {},
        { 6, 7 },
        {},
        {},
      };

      assertThat(exercici1(undirectedK6) == 6 - 5*6/2);
      assertThat(exercici1(undirectedW4) == 5 - 2*4);

      assertThat(exercici2(undirectedK23));
      assertThat(!exercici2(undirectedK6));

      assertThat(exercici3(directedG1, 0) == 3);
      assertThat(exercici3(directedRTree1, 2) == 3);

      assertThat(exercici4(directedRTree1) == 5);
      assertThat(exercici4(directedRTree2) == 4);
    }
    }
      static class Tema4 {
    /*
     * Donau la solució de l'equació
     *
     *   ax ≡ b (mod n),
     *
     * Els paràmetres `a` i `b` poden ser negatius (`b` pot ser zero), però podeu suposar que n > 1.
     *
     * Si la solució és x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
     */
    static int[] exercici1(int a, int b, int n) {
        
        // Comprovam si b es igual a 0
        if (b == 0) {
            // Si a és divisible amb n, retornam c=0 i m=n
            //Si no és divisible, l'equaicó no té solució
            if (a % n == 0) {
                return new int[] { 0, n };
            } else {
                return null;
            }
        }
        // Calculam el màxim comú divisor de a i n
        int mcd = mcd(Math.abs(a), n);
        // Si b no és divisble amb mcd, l'equació no té solució
        if (b % mcd != 0) {
            return null;
        } else {
            // Calculam m com n dividit amb el mcd
            int m = n / mcd;
            // Calculam c com el residu de b/mcd i mcd més m i l'ajustam
            //per a que doni un nombre entre 0 i m
            int c = ((Math.abs(b) / mcd) % m + m) % m;

            if (a < 0) {
                // Si a es negativa, hem d'ajustar c per a que doni un nombre
                //entre 0 i m
                c = m - c; 
            }

            // Retornam c i m
            return new int[] { c, m };
        }
    }

    /*
     * Donau la solució (totes) del sistema d'equacions
     *
     *  { x ≡ b[0] (mod n[0])
     *  { x ≡ b[1] (mod n[1])
     *  { x ≡ b[2] (mod n[2])
     *  { ...
     *
     * Cada b[i] pot ser negatiu o zero, però podeu suposar que n[i] > 1. També podeu suposar
     * que els dos arrays tenen la mateixa longitud.
     *
     * Si la solució és de la forma x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
     */
        static int[] exercici2a(int[] b, int[] n) {
            
            int length = b.length;
            // Inicialitzam la solució amb x ≡ 0 (mod 1)
            int[] solucio = new int[] {0, 1}; 

            // Recorregut on resolvem, emprant el teorema xinès del residu, cada
            //una de les equacions congruents en funció de i
            for (int i = 0; i < length; i++) {
                // Calculam el residu i el modul actuals
                int residu = b[i] % n[i];
                int modul = n[i];
                // Inicialitzam a (qui multiplica a x) a 1
                int a = 1;
                
                // Cridam al mètode que ens calcula c i m emprant el teorema 
                //xinès dels residus
                solucio = teoremaXinesDelsResidus(solucio, new int[] {a,residu, modul});
                // Comprovam si ha hagut solució
                if (solucio == null) {
                    return null; 
                }
            }

            // Retornam la solució del exercici
            return solucio;
        }  

    /*
     * Donau la solució (totes) del sistema d'equacions
     *
     *  { a[0]·x ≡ b[0] (mod n[0])
     *  { a[1]·x ≡ b[1] (mod n[1])
     *  { a[2]·x ≡ b[2] (mod n[2])
     *  { ...
     *
     * Cada a[i] o b[i] pot ser negatiu (b[i] pot ser zero), però podeu suposar que n[i] > 1. També
     * podeu suposar que els tres arrays tenen la mateixa longitud.
     *
     * Si la solució és de la forma x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
     */
        static int[] exercici2b(int[] a, int[] b, int[] n) {
          
            // Array solucio inicialitzada ambe els primers nombres
            int[] solucio = new int[] { a[0], b[0] };  

            // Recorregut on resolvem, emprant el teorema xinès del residu, cada
            //una de les equacions congruents en funció de i
            for (int i = 1; i < a.length; i++) {
                
                // Cridam al mètode que ens calcula c i m emprant el teorema 
                //xinès dels residus
                solucio = teoremaXinesDelsResidus(solucio, new int[] { a[i], b[i] ,n[i]});  

                // Comprovam si hi ha solucio
                if (solucio == null) {
                    return null;  
                }
            }

            // Retornam la solució de l'exercici
            return solucio;  
        }
            
    /*
     * Suposau que n > 1. Donau-ne la seva descomposició en nombres primers, ordenada de menor a
     * major, on cada primer apareix tantes vegades com el seu ordre. Per exemple,
     *
     * exercici4a(300) --> new int[] { 2, 2, 3, 5, 5 }
     *
     * No fa falta que cerqueu algorismes avançats de factorització, podeu utilitzar la força bruta
     * (el que coneixeu com el mètode manual d'anar provant).
     */
    static ArrayList<Integer> exercici3a(int n) {
        // Retornam el resultat del mètode descomposicioEnPrimers() que ens 
        //realitza la descomposicio
        return descomposicioEnPrimers(n);
    }   

    /*
     * Retornau el nombre d'elements invertibles a Z mòdul n³.
     *
     * Alerta: podeu suposar que el resultat hi cap a un int (32 bits a Java), però n³ no té perquè.
     * De fet, no doneu per suposat que pogueu tractar res més gran que el resultat.
     *
     * No podeu utilitzar `long` per solucionar aquest problema. Necessitareu l'exercici 3a.
     */
    static int exercici3b(int n) {
              
      ArrayList<Integer> descomposicio = descomposicioEnPrimers(n);

      //Emmagatzemam el elements de la descomposició dins una llista
      List<Integer> llistaElements = new ArrayList<>(new HashSet<>(descomposicio));

      // Array num on emmagatzemarem el nombre de vegades que apareix cada 
      //element
      int[] num = new int[llistaElements.size()];
      
      // Recorregut del elements de la llista per calcular quantes vegades 
      //apareixen
      for (Integer i : descomposicio){ 
          num[llistaElements.indexOf(i)]++;
      }
      // Variable funcioFiEuler on calcularem la solució de l'exercici
      int funcioFiEuler = 1;

      //Calculam la funcio fi de euler emprant un recorregut de 0 fins a num
      for (int i = 0; i < num.length; i++) {
        
        // La funcio Fi de Euler es resol amb l'equació p^r (pr) - p^r-1 (prmenys1)  
        num[i] *= 3;
        int pr = pow(llistaElements.get(i), num[i]); 
        int prmenys1 = pow(llistaElements.get(i), num[i] - 1);
        funcioFiEuler *= pr - prmenys1 ;
      }

      // Una vegada hem acabat el bucle, retornam la funcio Fi de Euler
      return funcioFiEuler;
    }
    

    // Mètode mcd que calcula el màxim comú divisor de 2 enters passats per 
    //paràmetre
    static int mcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return mcd(b, a % b);
        }
    }
    
    // Mètode teoremaXinesDelsResidus que ens retorna la solució de 2 equacions
    //passades per paràmetre emprant l'algoritme xinès dels residus
    static int[] teoremaXinesDelsResidus(int[] equacio1, int[] equacio2) {
      // Extreim els coeficients (a), els residus (b) i els mòduls (m) de les 
      //2 equaciones pasades per paràmetre
      int a1 = equacio1[0];
      int b1 = equacio1[1];
      int m1 = equacio1[2];
      int a2 = equacio2[0];
      int b2 = equacio2[1];
      int m2 = equacio2[2];

      // Calculam el màxim comú divisor amb el métode estès d'Euclides
      int[] resultatMCD = algoritmeEstesEuclides(m1, m2);
      int mcd = resultatMCD[0];
      // Extreim t1 i t2 tal que t1*m1 + t2*m2 = mcd
      int t1 = resultatMCD[1];
      int t2 = resultatMCD[2];

      // Verficiam si la diferència de residus és divisble amb mcd
      if ((b2 - b1) % mcd != 0) {
        // No hi ha solució  
        return null; 
      }

      // Calculam x0 
      int x0 = (b1 * t2 * m2 + b2 * t1 * m1) % (m1 * m2);
      // Calculam el módul de la solució 
      int modul = m1 * m2;

      // Retornam x0 i el mòdul dins un Array
      return new int[] { x0, modul };
    }

    // Mètode algoritmeEstesEuclides que calcula el màxim comú divisor de 2 enters
    //passats per paràmetre i retorna el el mcd juntament amb x i y que resolen
    //la seva equació
    static int[] algoritmeEstesEuclides(int a, int b) {
      // Si el primer nombre es 0, retorna b com a mcd, 0 com a x i 1 com a y
      if (a == 0) {
        return new int[] { b, 0, 1 };
      }

      // Calcula l'algoritme estès d'Euclides
      int[] result = algoritmeEstesEuclides(b % a, a);
      // Extreim el màxim comú divisor
      int mcd = result[0];
      // Extreim x1
      int x1 = result[1];
      // Extreim y1
      int y1 = result[2];

      // Calculam x amb l'equació corresponent
      int x = y1 - (b / a) * x1;
      // Y es igual a x1
      int y = x1;

      // Retornam els 3 valors dins un Array
      return new int[] { mcd, x, y };
    }    
       
    // Mètode descomposicioEnPrimers que realitza la descomposicio del nombre 
    //passat per paràmetre. Aquesta descomposició s'emmagatzema en un ArrayList
    //que conté tots els nombres primers, ordenats de menor a major on cada 
    //primer apareix tantes vegades com el seu ordre que descomponen a n
    static ArrayList<Integer> descomposicioEnPrimers(int n) {
        // Inicialitzam l'ArrayList descomposicio on emmagatzemarem la descomposicio
        //en nombres primers de n
        ArrayList <Integer> descomposicio = new ArrayList<>();
      /*
      * Se empieza la descomposición a partir del 2, pero si el número que se introduce es un 1, se devolverá
      * directamente el 1.
      */
      // La descomposició comença en 2
      int factor_primer = 2;
      // Comprovam si n es igual a 1, en cas de ser-ho, només retornam el 1
      if(n == 1) {
        descomposicio.add(n);
      } else {
        /*
        * Mientras el número sea mayor o igual que el factor primo, se añadirá este factor a la descomposición y se
        * dividirá el número por ese factor.
        */
        // Condició de que n sigui major que el factor primer
        while (factor_primer <= n) {
          // Comprovam que n sea divisible amb el factor primer
          while (n % factor_primer == 0) {
            // Emmagatzemam el factor dins l'ArrayList descomposicio
            descomposicio.add(factor_primer);
            // Dividim n entre el factor primer
            n /= factor_primer;
          }
          // Una vegada ja no es divisible, vol dir que hem acabat amb aquest 
          //factor primer, per tant hem d'augmentar el seu valor fins trobar el 
          //següent
          factor_primer++;
        }
      }
      // Una vegada hem acabat, retornam l'ArrayList descomposicio amb tots els 
      //nombres primers de n ordenats de menor a major
      return descomposicio;
    }
    
    static int pow(int nombre, int exponent) {
      int result = nombre;

      for (int i = 0; i < exponent - 1; i++){
          result *= nombre;
      }
      return result;
    }
    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */        
    static void tests() {
      assertThat(Arrays.equals(exercici1(17, 1, 30), new int[] { 23, 30 }));
      assertThat(Arrays.equals(exercici1(-2, -4, 6), new int[] { 2, 3 }));
      assertThat(exercici1(2, 3, 6) == null);

      assertThat(
        exercici2a(
          new int[] { 1, 0 },
          new int[] { 2, 4 }
        )
        == null
      );

      assertThat(
        Arrays.equals(
          exercici2a(
            new int[] { 3, -1, 2 },
            new int[] { 5,  8, 9 }
          ),
          new int[] { 263, 360 }
        )
      );

      assertThat(
        exercici2b(
          new int[] { 1, 1 },
          new int[] { 1, 0 },
          new int[] { 2, 4 }
        )
        == null
      );

      assertThat(
        Arrays.equals(
          exercici2b(
            new int[] { 2,  -1, 5 },
            new int[] { 6,   1, 1 },
            new int[] { 10,  8, 9 }
          ),
          new int[] { 263, 360 }
        )
      );

      assertThat(exercici3a(10).equals(List.of(2, 5)));
      assertThat(exercici3a(1291).equals(List.of(1291)));
      assertThat(exercici3a(1292).equals(List.of(2, 2, 17, 19 )));

      assertThat(exercici3b(10) == 400);

      // Aquí 1292³ ocupa més de 32 bits amb el signe, però es pot resoldre sense calcular n³.
      assertThat(exercici3b(1292) == 961_496_064);

      // Aquest exemple té el resultat fora de rang
      //assertThat(exercici3b(1291) == 2_150_018_490);
    }
  }

    /*
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
     */
    public static void main(String[] args) {
//    Tema1.tests();
//    Tema2.tests();
//    Tema3.tests();
//      Tema1.exercici1();
    }

    /// Si b és cert, no fa res. Si b és fals, llança una excepció (AssertionError).
    static void assertThat(boolean b) {
        if (!b) {
            throw new AssertionError();
        }
    }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
