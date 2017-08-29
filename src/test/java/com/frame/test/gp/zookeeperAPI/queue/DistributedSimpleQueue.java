package com.frame.test.gp.zookeeperAPI.queue;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Administrator
 * @CREATE 2017/8/12 19:25
 */
public class DistributedSimpleQueue<T> {
	private final ZkClient zkClient;
	private final String root;
	private static final String node_name = "n_";

	public DistributedSimpleQueue(ZkClient zkClient, String root) {
		this.zkClient = zkClient;
		this.root = root;
	}

	//获取队列的大小
	public int size() {
		//获取根节点下的所有子节点
		return zkClient.getChildren(root).size();
	}

	//判断队列是否为空
	public boolean isEmpty() {
		return size() == 0;
	}

	//存入队列
	public boolean offer(T element) {
		try {
			String nodeFullPath = root.concat("/").concat(node_name);
			zkClient.createPersistentSequential(nodeFullPath, element);
		} catch (ZkNoNodeException e) {
			zkClient.createPersistent(root);
			offer(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	//从队列中取出元素
	public T poll() {
		try {
			List<String> list = zkClient.getChildren(root);
			if (list.size() == 0) {
				return null;
			}

			//将队列安装由小到大的顺序排序
			Collections.sort(list, new Comparator<String>() {
				public int compare(String lhs, String rhs) {
					return getNodeNumber(lhs, node_name).compareTo(getNodeNumber(rhs, node_name));
				}
			});

			/**
			 * 将队列中的元素做循环，然后构建完整的路径，在通过这个路径去读取数据
			 */
			for (String nodeName : list) {
				String nodeFullPath = root.concat("/").concat(nodeName);
				try {
					T node = zkClient.readData(nodeFullPath);
					zkClient.delete(nodeFullPath);
					return node;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getNodeNumber(String str, String nodeName) {
		int index = str.lastIndexOf(nodeName);
		if (index >= 0) {
			index += node_name.length();
			return index <= str.length() ? str.substring(index) : "";
		}
		return str;
	}
}

