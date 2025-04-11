import java.util.*;

public class Biblioteca {
    private List<Libro<String>> biblioteca;
    private Set<String> utenti;
    private Map<String, Stack<Libro<String>>> libriInPrestito;
    private Queue<Libro<String>> libriDaRestituire;

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
        if (libriInPrestito.containsKey(utente)) {
            Stack<Libro<String>> libri = libriInPrestito.get(utente);
            if (!libri.contains(libro)) {
                libri.add(libro);
                libriInPrestito.replace(utente, libri);
            } else throw new IllegalArgumentException("libro già preso in prestito dall'utente " + utente);
        } else throw new IllegalAccessException("utente non registrato nell'archivio");
    }
    public void restituisciLibro(String utente){
        Stack<Libro<String>> libri = libriInPrestito.get(utente);
        Libro<String> libro = libri.pop();
        libriInPrestito.replace(utente, libri);
        libriDaRestituire.add(libro);
    }
}
