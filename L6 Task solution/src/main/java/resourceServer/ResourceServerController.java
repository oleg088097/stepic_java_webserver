package resourceServer;

/**
 * @author a.akbashev
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class ResourceServerController implements ResourceServerControllerMBean {
    private final ResourceServerI resourceServerI;

    public ResourceServerController(ResourceServerI resourceServerI) {
        this.resourceServerI = resourceServerI;
    }

    @Override
    public String getName() {
        return resourceServerI.getName();
    }

    @Override
    public int getAge() {
        return resourceServerI.getAge();
    }
}
