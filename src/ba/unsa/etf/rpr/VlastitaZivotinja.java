package ba.unsa.etf.rpr;

import java.util.function.Supplier;

public class VlastitaZivotinja extends Zivotinja{

    private String vrstaZivotinje;
    private Supplier<String> glasFunkcija;

    public VlastitaZivotinja(String s, String id, String ime, Supplier<String> f) throws NeispravanFormatIdaException {
        super(id, ime);
    }

    public void setVrstaZivotinje(String vrstaZivotinje) {
        this.vrstaZivotinje = vrstaZivotinje;
    }

    public void setGlasFunkcija(Supplier<String> glasFunkcija) {
        this.glasFunkcija = glasFunkcija;
    }

    @Override
    public String glas() {
        return glasFunkcija.get();
    }

    @Override
    public String vrsta() {
        return vrstaZivotinje;
    }
}
