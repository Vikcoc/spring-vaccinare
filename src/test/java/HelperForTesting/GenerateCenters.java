package HelperForTesting;

import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.model.VaccineType;

import java.util.ArrayList;
import java.util.List;

public class GenerateCenters {

    static public List<VaccineCenter> getCenters(Long startId, Integer count){
        List<VaccineCenter> res = new ArrayList<>();
        for(int i = 1; i <= count; i++){
            VaccineCenter vc = new VaccineCenter();
            vc.setId(startId + i);
            vc.setAddress("O adresa " + i);
            vc.setLongitude(((double) i)/ 10);
            vc.setLatitude(((double) i)/ 10);
            vc.setDosesAvailable((long) i);

            VaccineType vt = new VaccineType();
            vt.setBrand("Some brand " + i);
            vc.setVaccineType(vt);

            res.add(vc);
        }
        return res;
    }

    static public List<VaccineType> getTypes(Long startId, Integer count){
        List<VaccineType> res = new ArrayList<>();
        for(int i = 1; i <= count; i++){

            VaccineType vt = new VaccineType();
            vt.setId(startId + i);
            vt.setBrand("Some brand " + i);
            vt.setDaysBetweenShots(60);

            res.add(vt);
        }
        return res;
    }
}
