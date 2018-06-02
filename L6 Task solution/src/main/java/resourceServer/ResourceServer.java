package resourceServer;

import resourceServer.sax.ReadXMLFileSAX;
import resources.TestResource;

/**
 * @author a.akbashev
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class ResourceServer implements ResourceServerI{
    private TestResource testResource;

    @Override
    public void createResource(String path) {
        testResource = (TestResource)ReadXMLFileSAX.readXML(path);
    }

    @Override
    public String getName() {
        return testResource.getName();
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }
}
