import java.util.Comparator;

public class Item implements Comparable<Item>, Comparator<Item>{
    private String nazwa;
    private ItemCondition.condition stan;
    private double masa;
    private int ilosc;

    public Item()
    {
        nazwa = "krzesla";
        stan = ItemCondition.condition.USED;
        masa = 3.75;
        ilosc = 5;
    }

    public Item(String name, ItemCondition.condition con, double weight, int count)
    {
        nazwa = name;
        stan = con;
        masa = weight;
        ilosc = count;
    }


    public String getName()
    {
        return nazwa;
    }


    public ItemCondition.condition getCondition()
    {
        return stan;
    }

    public double getWeight()
    {
        return masa;
    }

    public int getCount()
    {
        return ilosc;
    }

    public void setCount(int count)
    {
        ilosc+=count;
    }

    public void print()
    {
        System.out.println("Dane produktu>>>");
        System.out.println("Nazwa: "+nazwa);
        System.out.println("Stan: "+stan);
        System.out.println("Masa: "+masa);
        System.out.println("Ilosc: "+ilosc);
    }

    @Override
    public int compareTo(Item item)
    {
        return ( this.nazwa.compareTo(item.nazwa) );
    }

    public int compareTo(String nazwa)
    {
        return ( this.nazwa.compareTo(nazwa) );
    }

    @Override
    public int compare(Item i1, Item i2)
    {
        int result = i1.getCount() - i2.getCount();
        if( result == 0 ) return i1.compareTo(i2);
        return result;
    }
}


