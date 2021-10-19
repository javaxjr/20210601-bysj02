package com.tjetc.demo.structure.demo01;

/*
* 结构型设计模式：代理模式
*
* */
public class ProxyTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //com.tjetc.demo.structure.demo01.Proxy:全路径名称

        //反射机制  通过反射获取对象
        //1、通过实例化对象的 proxy1.getClass() 获取到字节码文件  没意义 已经new了
        Proxy proxy1 = new Proxy();
        Class<? extends Proxy> aClass1 = proxy1.getClass();
        String name = aClass1.getName();
        System.out.println("name = " + name);

        //2、通过类的 .class 属性获取类的字节码文件   需要导入类的包，这样做依赖性太强，不利于解耦。
        Class<Proxy> proxy = Proxy.class;
        System.out.println(proxy.getName());;

        //3、通过类的全路径名获取Class对象 使用Class.forName("全路径名")来实现   需要提供累的全路径名
        Class<?> aClass = Class.forName("com.tjetc.demo.structure.demo01.Proxy");
        Object o = aClass.newInstance();
        Proxy proxy2 = (Proxy) o;
        proxy2.Request();

        /*Class<?> aClass = Class.forName("Proxy");
            Object object = aClass.newInstance();
            Proxy proxy = (Proxy) object;
            //Proxy proxy = new Proxy();
            proxy.Request();*/


    }
}

//抽象主题
interface Subject{
    void Request();
}

//真实主题
class RealSubject implements Subject{
    @Override
    public void Request() {
        System.out.println("访问真实主题方法。。。");
    }
}

//代理类
class Proxy implements Subject{
    private RealSubject realSubject;
    @Override
    public void Request() {
        if (realSubject == null){
            realSubject = new RealSubject();
        }

        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest(){
        System.out.println("访问真实主题之前的预处理。。。");
    }

    public void postRequest(){
        System.out.println("访问真实主题之后的后续处理");
    }

}
