import com.openalpr.jni.Alpr;
import com.openalpr.jni.AlprPlate;
import com.openalpr.jni.AlprPlateResult;
import com.openalpr.jni.AlprResults;

ALPR  ALPR  =  new ALPR （“US” ， “/path/to/openalpr.conf” ， “/path/to/ 
runtime_data” ）;

//设置前N个候选码回到20
alpr.setTopN(20);

//设置模式到qd
alpr.setDefaultRegion("qd");

AlprResults results = alpr.recognize("/path/to/image.jpg");
System.out.format(" %-15s-8s\n", "Plate Number", "Confidence" );
for (AlprPlateResult result: results.getPlates()) {
	for (AlprPlate plate : result.getTopNPlates()) {
		if(plate.isMatchesTemplate())
			System.out.print("  * ");
		else
			System.out.print("  - ");
		System.out.format("%-15s-8f\n", plate.getCharacters(), 
			plate.getOverallConfidence());
	}
}

//make sure to call this to release memory
alpr.unload();
