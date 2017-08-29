package com.frame.test.gp.zookeeperAPI.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @CREATE 2017/8/8 21:25
 */
public class CuratorCreateSessionDemo {
	public static void main(String[] args) throws Exception {

		CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
		System.out.println("连接成功.......");
		/**
		 *【curator使用异常】KeeperErrorCode = Unimplemented for /***
		 *Curator的版本过高造成，换成低一点版本即可 （version从3.0.0到2.9.1）
		 */

		//新增节点，所有操作都可以在zkCli.sh 中验证。
		String result = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/curator/curator1/curator11", "123".getBytes());
		System.out.println(result);

		//删除子节点
		curatorFramework.delete().forPath("/curator/curator1/curator11");

		//递归删除节点
		//curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");

		//查询数据，以及状态
		Stat stat = new Stat();
		byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/curator");
		System.out.println(new String(bytes) + "-->state:" + stat);  //值 前面章节讲过；

		//更新
		Stat stat1 = curatorFramework.setData().forPath("/curator", "123".getBytes());
		System.out.println(stat1);


		/**
		 * 特性:
		 * 异步操作
		 */
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		CountDownLatch countDownLatch=new CountDownLatch(1);
		curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
			@Override
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
				//创建节点是有线程池处理的，异步操作
				System.out.println(Thread.currentThread().getName() + "->resultCode:" + event.getResultCode() + "->" + event.getType());
				//countDownLatch.countDown();
			}
		}, executorService).forPath("/sync", "123".getBytes());
		countDownLatch.await();
		executorService.shutdown();


		/**
		 * 事务操作(curator独有的)
		 */
		//在同一个事务，先创建一个transaction节点，然后又修改trans这个节点的值。 这样就会报错，修改的不是同一个节点。然后ls / ，发现zookeeper客户端并没有创建这个节点
		/*Collection<CuratorTransactionResult> resultCollection=curatorFramework.inTransaction().create().forPath("/transaction","transaction".getBytes()).and().setData().forPath("/trans","transaction roll back".getBytes()).and().commit();
		for (CuratorTransactionResult curatorTransactionResult:resultCollection){
			System.out.println(curatorTransactionResult.getForPath()+"->"+curatorTransactionResult.getType());
		}*/

		//在同一个事务，先创建一个transaction节点，然后又修改transaction这个节点的值。这样查看数据的值发现更新了
		Collection<CuratorTransactionResult> resultCollection1=curatorFramework.inTransaction().create().forPath("/transaction","transaction".getBytes()).and().setData().forPath("/transaction","transaction update ".getBytes()).and().commit();
		for (CuratorTransactionResult curatorTransactionResult:resultCollection1){
			System.out.println(curatorTransactionResult.getForPath()+"->"+curatorTransactionResult.getType());
		}
	}
}
