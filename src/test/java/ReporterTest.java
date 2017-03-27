import static org.junit.Assert.assertEquals;
import org.junit.Test;

class ReporterTest{
    //We need the sb2 to store it's name
       
    @Test
    public void testFileName(){
        Sb2 sb2 = new Sb2(Utils.getWizardJSONObject(), "WizardProject");
        Reporter reporter = new Reporter("");
        File report = reporter.writeReport(sb2);
        assertEquals(report.getName(), sb2.getName() + ".txt");
    }
}
