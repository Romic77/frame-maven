package com.frame.test.gp.zookeeperAPI.zkclient;

import java.io.Serializable;

/**
 * @author Administrator
 * @CREATE 2017/8/11 18:34
 */
public class UserCenter implements Serializable{

	private static final long serialVersionUID = 849049789464592172L;

	private int mc_id; //机器信息

	private String mc_name; //机器信息

	@Override
	public String toString() {
		return "UserCenter{" +
				"mc_id=" + mc_id +
				", mc_name='" + mc_name + '\'' +
				'}';
	}

	public String getMc_name() {
		return mc_name;
	}

	public void setMc_name(String mc_name) {
		this.mc_name = mc_name;
	}

	public int getMc_id() {
		return mc_id;
	}

	public void setMc_id(int mc_id) {
		this.mc_id = mc_id;
	}
}
