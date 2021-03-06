package se.bjurr.violations.lib;

import static org.assertj.core.api.Assertions.assertThat;
import static se.bjurr.violations.lib.TestUtils.getRootFolder;
import static se.bjurr.violations.lib.ViolationsReporterApi.violationsReporterApi;
import static se.bjurr.violations.lib.model.SEVERITY.INFO;
import static se.bjurr.violations.lib.reports.Reporter.JCREPORT;

import java.util.List;
import org.junit.Test;
import se.bjurr.violations.lib.model.Violation;

public class JCReportTest {
  @Test
  public void testThatViolationsCanBeParsed() {
    String rootFolder = getRootFolder();

    List<Violation> actual =
        violationsReporterApi() //
            .withPattern(".*/jcreport/.*\\.xml$") //
            .inFolder(rootFolder) //
            .findAll(JCREPORT) //
            .violations();

    assertThat(actual) //
        .hasSize(54);

    assertThat(actual.get(0).getMessage()) //
        .isEqualTo("Type Javadoc comment is missing an @author tag.");
    assertThat(actual.get(0).getFile()) //
        .isEqualTo("D:/projects/fawkez/test/java/org/jcoderz/commons/logging/XmlPrinterTest.java");
    assertThat(actual.get(0).getSeverity()) //
        .isEqualTo(INFO);
    assertThat(actual.get(0).getRule().get()) //
        .isEqualTo("CS_MISSING_TAG(Checkstyle)");
    assertThat(actual.get(0).getStartLine()) //
        .isEqualTo(50);
    assertThat(actual.get(0).getEndLine()) //
        .isEqualTo(50);

    assertThat(actual.get(1).getMessage()) //
        .isEqualTo("Class LogElementHandler should be declared as final.");
  }
}
