/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common_Files;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class cfg {
    public static String cargoType[] = {"By Air","By Sea","Courier","Import By Air","Import By Sea","Stuffing By Sea"};
    String byAir[] = {"Air","Air","Air","Air","Air","Air"};
    String bySea[] = {"Sea","Sea","Sea","Sea","Sea","Sea"};
    String courier[] = {"Courier","Courier","Courier","Courier"};
    String importAir[] = {"importAir","importAir","importAir","importAir","importAir"};
    String importSea[] = {"importSea","importSea","importSea","importSea","importSea"};
    String stuffSea[] = {"StuffSea","StuffSea","StuffSea","StuffSea","StuffSea","StuffSea"};
    public static String inVoice = "Invoice No";
    public static String issueDate = "Issue Date";
    public static String shipperName = "Shipper Name";
    public static String cargoTbl = "Cargo Types";
    public static String cargoTypes = "Cargo Type";
    public static String partyInvoiceNo = "Party Invoice No";
    public static String vesselVoy = "Vessel Voy";
    public static String eid = "EID";
    public static String pol = "POL";
    public static String awbNumber = "awbName";
    public static String podNo = "POD";
    public static String sbNo = "SBNO";
    public static String noOFCTNS = "CTNS";
    public static String name = "name";
    public static String term = "term";
    public static String custName = "custName";
    public static String custAddress = "custAddress";
    public static String companyAddress = "2A/108, Apna Ghar, Saibaba Nagar, Teli Gali Andheri (East), Mumbai";
    public static String companyGSTIN = "27AAJCA8966F2Z7";
    public static String companyEmail = "info@ashirwadshipping.com";
    
    String collArr[][] = {byAir,bySea,courier,importAir,importSea,stuffSea};
    
    public Map<String,String[]> cargoDic = new HashMap<>();
  
    public void makeDictionary(){
        
      for(int i = 0 ;i<cargoType.length;i++){
            cargoDic.put(cargoType[i], collArr[i]);
      }
    }
    
     public static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
