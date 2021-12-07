package ba.unsa.etf.rpr;

public abstract class Zivotinja {
    private String id;
    private String ime;

    public Zivotinja(String id, String ime) throws NeispravanFormatIdaException{
        //moze se koristit i metoda trim koja izbacuje praznine.
        if(id.trim().isBlank()) throw new IllegalArgumentException("Id ne moze biti prazno");
        if(ime.trim().isBlank()) throw new IllegalArgumentException("Ime ne moze biti prazno");
        this.id = id;
        this.ime = ime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id.trim().isBlank()) throw new IllegalArgumentException("Id ne moze biti prazno");
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        if(ime.trim().isBlank()) throw new IllegalArgumentException("Ime ne moze biti prazno");
        this.ime = ime;
    }

    public abstract String glas();
}
