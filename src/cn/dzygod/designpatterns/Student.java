package cn.dzygod.designpatterns;

/**
 * @description 装饰设计模式
 */
interface coder {
    public void code();
}

class School implements coder{

    @Override
    public void code() {
        System.out.println("hello world");
    }
}

class Heima implements coder{

    private School s;
    public Heima(School s){
        this.s = s;
    }
    @Override
    public void code() {
        s.code();
        System.out.println("数据库");
        System.out.println("javaee");
        System.out.println("javase");
    }
}