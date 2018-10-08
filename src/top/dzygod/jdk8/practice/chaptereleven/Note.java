package top.dzygod.jdk8.practice.chaptereleven;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/5 16:53
 * @Description: *
 */
public class Note {
    /**
     * 11.6 小结
     * 这一章中，你学到的内容如下。
     *  执行比较耗时的操作时，尤其是那些依赖一个或多个远程服务的操作，使用异步任务可
     * 以改善程序的性能，加快程序的响应速度。
     *  你应该尽可能地为客户提供异步API。使用 CompletableFuture 类提供的特性，你能够
     * 轻松地实现这一目标。
     *  CompletableFuture 类还提供了异常管理的机制，让你有机会抛出/管理异步任务执行
     * 中发生的异常。
     *  将同步API的调用封装到一个 CompletableFuture 中，你能够以异步的方式使用其结果。
     *  如果异步任务之间相互独立，或者它们之间某一些的结果是另一些的输入，你可以将这
     * 些异步任务构造或者合并成一个。
     *  你可以为 CompletableFuture 注册一个回调函数，在 Future 执行完毕或者它们计算的
     * 结果可用时，针对性地执行一些程序。
     *  你可以决定在什么时候结束程序的运行，是等待由 CompletableFuture 对象构成的列表
     * 中所有的对象都执行完毕，还是只要其中任何一个首先完成就中止程序的运行。
     */
}
