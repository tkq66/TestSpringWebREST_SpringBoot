package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController marks the class as a controller where every method returns a domain object 
 * instead of a view. It’s shorthand for @Controller and @ResponseBody rolled together.
 */
@RestController
public class GreetingController 
{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
     * The Greeting object must be converted to JSON. Thanks to Spring’s HTTP message 
     * converter support, you don’t need to do this conversion manually. Because Jackson 2
     *  is on the classpath, Spring’s MappingJackson2HttpMessageConverter is automatically 
     *  chosen to convert the Greeting instance to JSON.
     */
    @RequestMapping(value="/greetingJSON", method=RequestMethod.GET)
    public Greeting greetingJSON(@RequestParam(value="name", defaultValue="World") String name) 
    {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }    
    
    /*
     * Uninitialized variables of object should be convertible to JSON as null. Scope shouldn't
     * matter to how the variables are parsed.
     */
    @RequestMapping(value="/greetingMutable", method=RequestMethod.GET)
    public GreetingMutable greetingMutable()
    {
    	GreetingMutable obj = new GreetingMutable();
    	obj.publicVar = 5;
    	return obj;
    }
    
    /*
     * Objects without variables should fail to convert and cause an error
     */
    @RequestMapping(value="/greetingEmpty", method=RequestMethod.GET)
    public GreetingEmpty greetingEmpty()
    {
    	GreetingEmpty obj = new GreetingEmpty();
    	return obj;
    }
}