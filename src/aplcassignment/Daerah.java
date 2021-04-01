
package aplcassignment;

import java.util.Map;

public class Daerah {
    
    // Access to the excel file for reading the data
    // File: statistik-kes-denggi-di-negeri-pahang-bagi-tempoh-2014-2017.xlsx
    
    private String daerah;
    private Map<Integer,Integer> date;
    
    //no args constructor
    public Daerah() {
    }
    

    //overloaded constructor
    public Daerah(String daerah, Map<Integer,Integer> date) {
        this.daerah = daerah;
        this.date = date;
        
    }
    
    
    //getter setter methods
    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public Map<Integer, Integer> getDate() {
        return date;
    }

    public void setDate(Map<Integer, Integer> date) {
        this.date = date;
    }



    
    //toString

    @Override
    public String toString() {
        return "Daerah{" + "daerah=" + daerah + ", date=" + date + ""+'}';
    }
     
    
}
