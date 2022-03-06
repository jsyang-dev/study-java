package me.study.java.dynamicproxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.bytebuddy.matcher.ElementMatchers.*;

class BookServiceTest {

    @Test
    public void proxy() {
        BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
                new InvocationHandler() {
                    final BookService bookService = new DefaultBookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("rent")) {
                            System.out.println("before");
                            Object invoke = method.invoke(bookService, args);
                            System.out.println("after");
                            return invoke;
                        }
                        return method.invoke(bookService, args);
                    }
                });

        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }

    @Test
    public void cglib() {
        MethodInterceptor handler = new MethodInterceptor() {
            final BookService bookService = new DefaultBookService();

            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("before");
                    Object invoke = method.invoke(bookService, args);
                    System.out.println("after");
                    return invoke;
                }
                return method.invoke(bookService, args);
            }
        };
        DefaultBookService bookService = (DefaultBookService) Enhancer.create(DefaultBookService.class, handler);

        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }

    @Test
    public void byteBuddy() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends DefaultBookService> proxyClass = new ByteBuddy().subclass(DefaultBookService.class)
                .method(named("rent"))
                .intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    final BookService bookService = new DefaultBookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("rent")) {
                            System.out.println("before");
                            Object invoke = method.invoke(bookService, args);
                            System.out.println("after");
                            return invoke;
                        }
                        return method.invoke(bookService, args);
                    }
                }))
                .make()
                .load(DefaultBookService.class.getClassLoader())
                .getLoaded();
        DefaultBookService bookService = proxyClass.getConstructor().newInstance();

        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }

}
