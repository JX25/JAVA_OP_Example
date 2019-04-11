import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Program {
    public static void main( String[] args)
    {
        System.out.println("PROJEKT_02_JAVA --- KOLEKCJE");
        Item item0 = new Item("Zabawki", ItemCondition.condition.USED, 2, 100);
        Item item1 = new Item("Pralki", ItemCondition.condition.NEW, 200, 10);
        Item item2 = new Item("Kuchenka", ItemCondition.condition.USED, 85, 45);
        Item item3 = new Item("Odkurzacz", ItemCondition.condition.NEW, 10, 30);
        Item item4 = new Item("Prysznic", ItemCondition.condition.NEW, 120, 10);
        Item item5 = new Item("Zmywarka", ItemCondition.condition.REFURBISHED, 100, 25);

        List<Item> itemy1 = new ArrayList<>();
        itemy1.add(item1);
        itemy1.add(item3);
        List<Item> itemy2 = new ArrayList<>();
        itemy2.add(item2);
        itemy2.add(item5);
        List<Item> itemy3 = new ArrayList<>();
        itemy3.add(item4);
        itemy3.add(item0);
        itemy3.add(item2);
        itemy3.add(item3);
        itemy3.add(item1);
        List<Item> itemy4 = new ArrayList<>();
        itemy4.add(item3);
        itemy4.add(item2);
        List<Item> itemy5 = new ArrayList<>();
        Item item6 = new Item("Stół",ItemCondition.condition.REFURBISHED,20,40);

        FullfilmentCenterContainer storages = new FullfilmentCenterContainer();
        storages.addCenter("Warszawa",itemy1,3000.0);
        storages.addCenter("Krakow",itemy2,1900.0);
        storages.addCenter("Lublin",itemy3,8000.0);
        storages.addCenter("Gdansk",itemy4,2500.0);
        storages.addCenter("Wroclaw",itemy5,3400.0);
        storages.summary();

        //FullfilmentCenter [] tablicaMagazynow = new FullfilmentCenter[5];


        //Znajdowanie pustego magazynu
        System.out.println("\n----------------------------------------------------------------- PUSTE MAGAZYNY\n");
        List <FullfilmentCenter> emptyFullfilmentCenter = storages.findEmpty();

        System.out.println("\n");

        for( FullfilmentCenter fc : emptyFullfilmentCenter )
        {
            fc.printMagazyn();
        }

        System.out.println("\n----------------------------------------------------------------- USUWANIE MAGAZYNU\n");
        //kasowanie magazynu
        storages.removeCenter("Wroclaw");
        storages.summary();

        //dodanie funkcja add dla magazynu pustego i magazynu w czesci zapelnionego
        System.out.println("\n----------------------------------------------------------------- FUNKCJA ADD\n");
        int it = 0;
        for( Map.Entry<String,FullfilmentCenter> entry : storages.mapaMagazynow.entrySet() )
        {
            if(entry.getKey()=="Krakow") entry.getValue().addProduct(item6);
            if(entry.getKey()=="Warszawa")
            {
                entry.getValue().addProduct(item6);
                entry.getValue().addProduct(item3);
            }
        }
        storages.summary();

        FullfilmentCenter magazyn = storages.getFullfilmentCenter("Lublin");

        //searchPartial
        System.out.println("\n----------------------------------------------------------------- PARTIAL SEARCH\n");
        List<Item> szukane= new ArrayList<>();
        szukane = magazyn.searchPartial("P");
        for(Item i : szukane)
        {
            i.print();
        }
        //getProduct
        System.out.println("\n----------------------------------------------------------------- FUNKCJA getProduct\n");
        System.out.println( "Magazyn przed operacja getProduct");
        magazyn.printMagazyn();
        System.out.println( "Magazyn po operacji getProduct");
        magazyn.getProduct("Prysznic");
        magazyn.printMagazyn();
        //removeProduct
        System.out.println("\n----------------------------------------------------------------- FUNKCJA removeProduct\n");
        System.out.println( "Magazyn przed operacja removeProduct");
        magazyn.printMagazyn();
        System.out.println( "Magazyn po operacji removeProduct");
        magazyn.removeProduct("Prysznic");
        magazyn.printMagazyn();

        //search
        System.out.println("\n----------------------------------------------------------------- FUNKCJA Search\n");
        Item szukany= new Item();
        szukany = magazyn.search("Zabawki");
        szukany.print();



        //countByCondition
        System.out.println("\n----------------------------------------------------------------- FUNKCJA countByCondition\n");
        System.out.println( "LICZBA SPRZETU ZE STATUSEM NEW");
        System.out.println( magazyn.countByCondition(ItemCondition.condition.NEW) );
        System.out.println( );


        System.out.println("Przed sorotwaniem itemow wg alfabetu: ");
        magazyn.printMagazyn();
        //sortByName
        System.out.println("\n----------------------------------------------------------------- FUNKCJA SortByName\n");
        List<Item> list = magazyn.sortByName();
        System.out.println("Po sortowaniem itemow wg alfabetu: ");
        for( Item i : list) i.print();

        //sortByAmount
        System.out.println("\n----------------------------------------------------------------- FUNKCJA SortByAmount\n");
        List<Item> list2 = magazyn.sortByAmount();
        System.out.println("Po sortowaniem itemow wg liczebnosci: ");
        for( Item i : list2) i.print();

        System.out.println("\n");
        magazyn.printMagazyn();

        //itemMax
        System.out.println("\n----------------------------------------------------------------- FUNKCJA ItemMax\n");
        Item max = new Item();
        max = magazyn.max();
        max.print();






       // storages.summary();

    }

}
