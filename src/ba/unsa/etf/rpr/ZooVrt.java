package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ZooVrt {
    private List<Zivotinja> lista = new ArrayList<>();

    public int broj(){
        return lista.size();
    }

    public String dajTabelu(){
        String rezultat = "";
        for(Zivotinja z : lista){
            rezultat += z.getIme() + " (" + z.vrsta() + ") : " + z.getId() + "\n";
        }
        return rezultat;
    }

    public void dodaj(Class vrstaZivotinje, String ime, String id) throws NeispravanFormatIdaException {
        Zivotinja z = null;
        if(vrstaZivotinje.getName().contains("DomaciPas"))
            z = new DomaciPas(id, ime);
        if(vrstaZivotinje.getName().contains("DomacaMacka"))
            z = new DomacaMacka(id, ime);
        if(vrstaZivotinje.getName().contains("Lav"))
            z = new Lav(id, ime);
        if(vrstaZivotinje.getName().contains("Vuk"))
            z = new Vuk(id, ime);
        if(vrstaZivotinje.getName().contains("Tigar"))
            z = new Tigar(id, ime);
        lista.add(z);
    }

    public void dodaj(Zivotinja zivotinja) throws DvostrukiIdException {
        for(Zivotinja z : lista){
            if(z.getId().equals(zivotinja.getId())) throw new DvostrukiIdException("Životinja sa IDom " + zivotinja.getId() + " već postoji u Zoo vrtu");
        }
        lista.add(zivotinja);
    }

    public void dodaj(Class vrstaZivotinje, String ime) throws NeispravanFormatIdaException {
        int id = nadjiNajveciId()+1;
        String sId = Zivotinja.generisiId(ime) + "-" + id;
        dodaj(vrstaZivotinje, ime, sId);
    }

    private int nadjiNajveciId() {
        int max = 0;
        for(Zivotinja z : lista){
            String id = z.getId();
            int broj = Integer.parseInt(id.substring(id.indexOf("-")+1));
            if(broj > max) max = broj;
        }
        return max;
    }

    public void dodaj(String vrstaZivotinje, String id, String ime, Supplier<String> f) throws NeispravanFormatIdaException, DvostrukiIdException {
        for(Zivotinja z : lista)
            if (z.getId().equals(id))
                throw new DvostrukiIdException("Životinja sa IDom "+id+" već postoji u Zoo vrtu");
        lista.add(new VlastitaZivotinja(id, ime, vrstaZivotinje, f));
    }

    public void obrisi(String id){
        lista.removeIf(z -> z.getId().equals(id));
    }

    public Set<Zivotinja> koToTamoGovori(String glasanje){
        return Arrays.stream(glasanje.split(",")).map(glas ->
                lista.stream().sorted().filter(z -> z.glas().equals(glas)).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Nema tog ID")))
                        .collect(Collectors.toSet());
    }


}
