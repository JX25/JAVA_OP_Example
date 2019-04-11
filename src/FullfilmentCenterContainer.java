import java.util.*;

public class FullfilmentCenterContainer {
    public Map<String, FullfilmentCenter> mapaMagazynow;

    FullfilmentCenterContainer(){
        mapaMagazynow = new HashMap<>();
    }

    public void addCenter(String nazwa,List<Item> items, double pojemnosc)
    {
        if(mapaMagazynow==null){

        }else{
            FullfilmentCenter mag= new FullfilmentCenter(nazwa,items,pojemnosc);
            mapaMagazynow.put(nazwa,mag);
        }
    }

    public void removeCenter(String nazwa)
    {

        for( Map.Entry<String,FullfilmentCenter> entry : mapaMagazynow.entrySet() )
        {
            if( entry.getKey() == nazwa ) {
                mapaMagazynow.remove(nazwa);
                return;
            }

        }
    }

    public List<FullfilmentCenter> findEmpty()
    {
        List<FullfilmentCenter> listaPustych = new ArrayList<>();
        for( Map.Entry<String,FullfilmentCenter> entry : mapaMagazynow.entrySet() )
        {
            FullfilmentCenter mag = entry.getValue();
            if( mag.getActPojemnosc() == 0 ) listaPustych.add(mag);
        }
        return listaPustych;
    }

    public void summary()
    {
        for( Map.Entry<String,FullfilmentCenter> entry : mapaMagazynow.entrySet() )
        {
            entry.getValue().printMagazyn();
        }
    }

    public FullfilmentCenter getFullfilmentCenter(String nazwa)
    {
        FullfilmentCenter FC= new FullfilmentCenter();
        for( Map.Entry<String,FullfilmentCenter> entry : mapaMagazynow.entrySet())
        {
            if( entry.getKey() == nazwa) FC = entry.getValue();
        }

        return FC;
    }

}
