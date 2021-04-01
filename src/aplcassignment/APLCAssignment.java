
package aplcassignment;

import java.util.List;

public class APLCAssignment {

    public static void main(String[] args) {
        
        String targetfile1 = "statistik-kes-denggi-di-negeri-pahang-bagi-tempoh-2014-2017.xlsx";
        String targetfile2 = "statistik-kes-denggi-di-negeri-pahang-bagi-tempoh-2018-2019.xlsx";
        ExcelReader reader = new ExcelReader(targetfile1,targetfile2);
        List<Daerah> daerah = reader.getAll();
        //daerah.forEach(System.out::println);
        new DashboardView(daerah).setVisible(true);
    }

}
