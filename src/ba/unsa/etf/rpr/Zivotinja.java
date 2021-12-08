package ba.unsa.etf.rpr;

public abstract class Zivotinja {
    private String id;
    protected String ime;

    public Zivotinja(String id, String ime) throws NeispravanFormatIdaException{
        //moze se koristit i metoda trim koja izbacuje praznine.
        if(id.trim().isBlank()) throw new IllegalArgumentException("Id ne moze biti prazno");
        if(ime.trim().isBlank()) throw new IllegalArgumentException("Ime ne moze biti prazno");
        if(!provjeriId(id,ime)) throw new NeispravanFormatIdaException("Id nije ok");
        this.id = id;
        this.ime = ime;
    }

    public static String generisiId(String ime){
        String novoIme = "";
        for(int i=0; i<ime.length(); i++){
            char c = ime.charAt(i);
            if(c >= 'a' && c <= 'z') novoIme += c;
            if(c >= 'A' && c <= 'Z') novoIme += (char)(c + 32);
            if(c == 'č' || c == 'Č' || c == 'ć' || c == 'Ć') novoIme += 'c';
            if(c == 'š' || c == 'Š') novoIme += 's';
            if(c == 'đ' || c == 'Đ') novoIme += 'd';
            if(c == 'ž' || c == 'Ž') novoIme += 'z';
        }
        return novoIme;
    }

    private boolean provjeriId(String id, String ime) throws NeispravanFormatIdaException{

        String novoIme = Zivotinja.generisiId(ime);
        if(id.length() <= novoIme.length()+1) throw new NeispravanFormatIdaException("Id je prekratak" + novoIme);
        if(!id.substring(0, novoIme.length()).equals(novoIme)) throw new NeispravanFormatIdaException("Id se ne poklapa");
        if(id.charAt(novoIme.length()) != '-') throw new NeispravanFormatIdaException("znak nije crtica");
        for(int i = novoIme.length()+1; i < id.length(); i++){
            if(id.charAt(i) < '0' || id.charAt(i) > '9') throw new NeispravanFormatIdaException("znak nije slovo"+novoIme);
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws NeispravanFormatIdaException{
        if(id.trim().isBlank()) throw new IllegalArgumentException("Id ne moze biti prazno");
        if(!provjeriId(id,ime)) throw new NeispravanFormatIdaException("Id nije ok");

        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) throws NeispravanFormatIdaException{
        if(ime.trim().isBlank()) throw new IllegalArgumentException("Ime ne moze biti prazno");
        if(!provjeriId(id,ime)) throw new NeispravanFormatIdaException("Id nije ok");

        this.ime = ime;
    }

    public abstract String glas();
    public abstract String vrsta();
}
