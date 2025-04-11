import java.util.*;

public class Main {
    public static void main (String[] args){

    Biblioteca comunale = new Biblioteca();
    Libro<String> primo = new Libro<>("Il piccolo principe","Antoine De Saint Exupery");
    Libro<String> secondo = new Libro<>("Il conte di Montecristo","Alexandre Dumas");
    Libro<String> terzo = new Libro<>("1984","George Orwell");
    Libro<String> quarto = new Libro<>("Harry Potter","J.K. Rowling");
    Libro<String> quinto = new Libro<>("Il signore degli anelli","J.R.R. Tolkien");
    comunale.aggiungiLibro(primo);
    comunale.aggiungiLibro(secondo);
    comunale.aggiungiLibro(terzo);
    comunale.aggiungiLibro(quarto);
    comunale.aggiungiLibro(quinto);
    System.out.println("---------------Lista libri della biblioteca---------------");
    comunale.stampaLibri();
    System.out.println("---------------Lista utenti registrati---------------");
    String fabrizio  = "Fabrizio";
    String francesca = "Francesca";
    comunale.registraUtente(fabrizio);
    comunale.registraUtente(francesca);
    comunale.stampaUtenti();
    try {
        comunale.prestaLibro(fabrizio, quarto);
        comunale.prestaLibro(fabrizio, quinto);
        comunale.prestaLibro(fabrizio, primo);
        comunale.prestaLibro(francesca, secondo);
        comunale.prestaLibro(francesca, terzo);
    } catch (IllegalAccessException e) {
        System.out.println(e.getMessage());
    }
    comunale.mostraLibriPresi(fabrizio);
    comunale.mostraLibriPresi(francesca);
    comunale.restituisciLibro(fabrizio);
    comunale.mostraLibriPresi(fabrizio);
    comunale.restituisciLibro(francesca);
    comunale.mostraLibriPresi(francesca);
    comunale.mostraLibriInRestituzione();

        Comparator<Libro<String>> confrontoPerTitolo = new Comparator<Libro<String>>() {
            @Override
            public int compare(Libro<String> l1, Libro<String> l2) {
                return l1.getTitolo().compareToIgnoreCase(l2.getTitolo());
            }

        };
        System.out.println("---------------Lista Libri Biblioteca ordinati per titolo: ---------------");
    List<Libro<String>> lista = Biblioteca.getBiblioteca();
    Collections.sort(lista,confrontoPerTitolo);
    comunale.stampaLibri(lista);


    }
}
