public class Libro<T> {
    private T valore;
    private String codice;

    public Libro(T valore) {
        this.valore = valore;
        this. codice = generaCodiceISBN();
    }

    public T getValore() {
        return valore;
    }

    public void setValore(T valore) {
        this.valore = valore;
    }

    public String getCodice() {
        return codice;
    }

    public String generaCodiceISBN () {
        String pre = "978";
        String lingua = "88";
        String edit = String.format("%04d", (int)(Math.random() * 10000));
        String art = String.format("%03d", (int)(Math.random() * 1000));
        String partialCodeISBN = pre + lingua + edit + art;
        int check = calculateCheckDigit(partialCodeISBN);
        String codeISBN = pre + "-" + lingua + "-" + edit + "-" + art + "-" + check;
        return codeISBN;
    }

    // Metodo per calcolare la cifra di controllo secondo algoritmo ISBN-13
    private static int calculateCheckDigit(String number12Digits) {
        int sum = 0;
        for (int i = 0; i < number12Digits.length(); i++) {
            int digit = Character.getNumericValue(number12Digits.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int remainder = sum % 10;
        return (remainder == 0) ? 0 : (10 - remainder);
    }
}
