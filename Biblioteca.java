import java.util.*;

public class Biblioteca {
    private static List<Libro<String>> biblioteca = new ArrayList<>();
    private static Set<String> utenti = new HashSet<>();
    private static Map<String, Stack<Libro<String>>> libriInPrestito = new TreeMap<>();
    private static Queue<Libro<String>> libriDaRestituire = new LinkedList<>();

    public void aggiungiLibro(Libro<String> libro) {
        biblioteca.add(libro);
    }

    public void rimuoviLibro(Libro<String> libro) {
        biblioteca.remove(libro);
    }

    public void registraUtente(String nome) {
        utenti.add(nome);
    }

    public void prestaLibro(String utente, Libro<String> libro) throws IllegalAccessException {
        if (utenti.contains(utente)) {
            if (libriInPrestito.containsKey(utente)) {
                Stack<Libro<String>> libri = libriInPrestito.get(utente);
                if (!libri.contains(libro)) {
                    libri.push(libro);
                    libriInPrestito.replace(utente, libri);
                } else throw new IllegalArgumentException("libro già preso in prestito dall'utente " + utente);
            } else {
                Stack<Libro<String>> libriNuovoUtente = new Stack<>();
                libriNuovoUtente.push(libro);
                libriInPrestito.put(utente, libriNuovoUtente);
            }
        } else throw new IllegalAccessException("non si possono prestare libri a utenti non registrati");
    }

    public void restituisciLibro(String utente) {
        Stack<Libro<String>> libri = libriInPrestito.get(utente);
        Libro<String> libro = libri.pop();
        libriInPrestito.replace(utente, libri);
        libriDaRestituire.offer(libro);
    }

    public void stampaLibri() {
        for (Libro<String> libro : biblioteca) {
            System.out.println(libro.getTitolo() + ", " + libro.getAutore());
        }
    }

    //metodo per stampare i libri nella classe astratta dopo aver ordinato la lista
    public void stampaLibri(List<Libro<String>> lista) {
        for (Libro<String> libro : lista) {
            System.out.println(libro.getTitolo() + ", " + libro.getAutore());
        }
    }

    public static List<Libro<String>> getBiblioteca() {
        return biblioteca;
    }

    public void mostraLibriPresi(String utente) {
        System.out.println("---------------Elenco libri presi da " + utente + ": ---------------");
        Stack<Libro<String>> listaLibriPrestati = libriInPrestito.get(utente);
        for (Libro<String> libro : listaLibriPrestati) {
            libro.mostraInfo();
        }
    }

    public void mostraLibriInRestituzione(){
        System.out.println("---------------Lista libri in restituzione: ---------------");
        for (Libro<String> libro : libriDaRestituire) {
            libro.mostraInfo();
        }
    }

    public void stampaUtenti() {
        System.out.println(utenti);
    }
}
