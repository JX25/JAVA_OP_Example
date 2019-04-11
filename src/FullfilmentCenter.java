import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class FullfilmentCenter{
    private String nazwa;
    private List<Item> listaProduktow;
    private double maxPojemnosc;
    private double actPojemnosc;

    FullfilmentCenter()
    {
        nazwa = "Magazyn 001";
        listaProduktow = new ArrayList<>();
        maxPojemnosc = 1000.0;
    }

    FullfilmentCenter(String name, List<Item>listOfProducts, double maxCapacity)
    {
        nazwa = name;
        maxPojemnosc = maxCapacity;
        actPojemnosc =0;
        for(Item i : listOfProducts )
        {
                actPojemnosc +=i.getWeight()*i.getCount();
        }
        if( actPojemnosc > maxPojemnosc )
        {
            System.err.println("Za duzo towarow. Magazyn przeladowny: "+name);
            actPojemnosc =0;
            listaProduktow = new ArrayList<>();
        }
        else{
            listaProduktow=listOfProducts;
        }
    }

    public double getActPojemnosc()
    {
        return actPojemnosc;
    }

    public void addProduct(Item item)
    {
       if(  willBeOverloaded(item) ) System.err.println("Podany produkt nie zmiesci sie juz w magazynie "+nazwa);
       else {
           Item ifExist = search(item.getName());
           if ( ifExist!=null )
           {
                for(Item i : listaProduktow )
                {
                    if ( i.getName()==item.getName() ) {
                        i.setCount( item.getCount() );
                        actPojemnosc +=item.getWeight()*item.getCount();
                    }
                }
           }
            else{
               listaProduktow.add(item);
               actPojemnosc+=item.getWeight()*item.getCount();
           }
       }
    }

    public void getProduct(String nazwa)
    {
        for(Item i : listaProduktow ) {
            if (i.getName() == nazwa)
            {
                if( ( i.getCount()-1 ) == 0){
                    actPojemnosc -= i.getWeight();
                    listaProduktow.remove(i);
                    return;
                }
                else {
                    i.setCount(-1);
                    actPojemnosc-=i.getWeight();
                    return;
                }
            }
        }
    }

    public void removeProduct(String nazwa)
    {
        for(Item i: listaProduktow)
        {
            if(i.getName()==nazwa) {
                listaProduktow.remove(i);
                actPojemnosc-= (i.getWeight()*i.getCount());
                return;
            }
        }
    }

    public Item search(String nazwaProduktu)
    {
       // for(Item i : listaProduktow) if( i.getName() == nazwaProduktu ) return i;
       // return null;
        for(Item i : listaProduktow) if( i.compareTo(nazwaProduktu) == 0 ) return i;
        return null;
    }

    public List<Item>searchPartial(String fragmentNazwy) {
        List<Item> wynik = new ArrayList<Item>();

        for (Item i : listaProduktow)
        {
            String name = i.getName();
            if( name.toLowerCase().contains(fragmentNazwy.toLowerCase() ) ) wynik.add(i);
        }


        if (wynik.size()!=0 ) return wynik;
        else return null;
    }

    public int countByCondition(ItemCondition.condition con)
    {
        int wynik=0;
        for( Item i : listaProduktow )
        {
            if( i.getCondition() == con) wynik+=i.getCount();
        }
        return wynik;
    }

    public void summary()
    {
        for( Item i : listaProduktow)
        {
            i.print();
        }
    }

    public List<Item> sortByName()
    {
        List<Item> lista = new LinkedList<>(listaProduktow);
        Collections.sort(lista);
        return lista;
    }

    public List<Item> sortByAmount()
    {
        List<Item> lista = new LinkedList<>();
        for( Item i : listaProduktow) lista.add(i);
        Collections.sort(lista, (i1,i2) -> { return Integer.compare(i1.getCount(),i2.getCount());} );
        return lista;
    }

    public Item max()
    {
        Item maxAmount = Collections.max(listaProduktow, (Item o1,Item o2) -> {
            return Integer.compare(o1.getCount(), o2.getCount());
        });
        return maxAmount;
    }

    public boolean willBeOverloaded(Item item)
    {
        if( actPojemnosc+item.getWeight()*item.getCount() > maxPojemnosc) return true;
        return false;
    };

    public void printMagazyn()
    {
        System.out.println(">>>"+nazwa+"<<<");
        double procent = Math.floor(actPojemnosc/maxPojemnosc *100) ;
        System.out.println("Zapelnienie: "+procent+"%");
        this.summary();
        System.out.println("Aktualna pojemnosc: "+actPojemnosc);
        System.out.println();
    }


}
