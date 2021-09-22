package proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListProvider {

    private static Logger logger = LogManager.getLogger(ListProvider.class);

    public MyList getList() {

        logger.debug("ListProvider.getList() called");

        MyList list = new MyListImpl();
        return (MyList) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[] { MyList.class },
                new DynamicInvocationHandler(list));
    }

    private static class DynamicInvocationHandler implements InvocationHandler {

        private MyList list;

        public DynamicInvocationHandler(MyList list) {
            this.list = list;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {

            var stream = args == null
                    ? Stream.empty()
                    : Arrays.stream(args);

            String argsString = stream
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            logger.debug("%s(%s)".formatted(method.getName(), argsString));

            return method.invoke(list, args);
        }
    }
}
