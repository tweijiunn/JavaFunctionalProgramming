
package aplcassignment;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    
    private FileInputStream fis1;
    private FileInputStream fis2;
    private XSSFWorkbook wk1;
    private XSSFWorkbook wk2;
    
    //target file
    private String targetFile1;
    private String targetFile2;
    

    public ExcelReader(String targetFile1,String targetFile2) {
        this.targetFile1=targetFile1;
        this.targetFile2=targetFile2;
        try{
            fis1= new FileInputStream(this.targetFile1);
            wk1= new XSSFWorkbook(fis1);
            fis2 = new FileInputStream(this.targetFile2);
            wk2= new XSSFWorkbook(fis2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // get all
    public List<Daerah> getAll(){
        List<Daerah> daerah= new ArrayList<>();
        // get sheet
        XSSFSheet sheet1= wk1.getSheetAt(0);
        XSSFSheet sheet2= wk2.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();
        for(Row row:sheet1){
            TreeMap<Integer,Integer> map = new TreeMap();
            int rowNum= row.getRowNum();
            int year = 2014;
            if(rowNum==0||rowNum==1||rowNum==2||rowNum==3||rowNum>=15)continue;
            
            map.put(year,Integer.parseInt(formatter.formatCellValue(row.getCell(2)).replaceAll(",","")));
            year++;
            map.put(year,Integer.parseInt(formatter.formatCellValue(row.getCell(3)).replaceAll(",","")));
            year++;
            map.put(year,Integer.parseInt(formatter.formatCellValue(row.getCell(4)).replaceAll(",","")));
            year++;
            map.put(year,Integer.parseInt(formatter.formatCellValue(row.getCell(5)).replaceAll(",","")));
            year++;
            
            daerah.add( new Daerah(
                    formatter.formatCellValue(row.getCell(1)),
                    map
            ));
            
        }
        //System.out.println(daerah);
        
        for(Row row: sheet2){
            TreeMap<Integer,Integer> map = new TreeMap();
            int rowNum= row.getRowNum();
            int year = 2017;
            if(rowNum==0||rowNum==1||rowNum==2||rowNum==3||rowNum==4||rowNum>=16)continue;
            map.put(year,Integer.parseInt(formatter.formatCellValue(row.getCell(2)).replaceAll(",","")));
            year++;
            map.put(year,Integer.parseInt(formatter.formatCellValue(row.getCell(3)).replaceAll(",","")));
            year++;
            map.put(year,Integer.parseInt(formatter.formatCellValue(row.getCell(4)).replaceAll(",","")));
            year++;
            
            daerah
                    .stream()
                    .filter(daerahItem -> daerahItem.getDaerah().replaceAll("\\s+","").equalsIgnoreCase(formatter.formatCellValue(row.getCell(1)).replaceAll("\\s+","")))
                    .peek(Daerah -> Daerah.getDate().putAll(map))
                    .collect(Collectors.toList());
        }
        //System.out.println(daerah);
        return daerah;
    }
    
    
    
}
